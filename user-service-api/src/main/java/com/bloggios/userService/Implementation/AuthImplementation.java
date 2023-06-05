/*
 * Copyright 2023 Rohit Parihar and Bloggios
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.bloggios.userService.Implementation;

import com.bloggios.userService.BusinessLogic.DatabaseLogic;
import com.bloggios.userService.BusinessLogic.PostRegistration;
import com.bloggios.userService.Constants.ErrorCodes;
import com.bloggios.userService.Constants.ErrorConstants;
import com.bloggios.userService.Constants.ServiceConstants;
import com.bloggios.userService.Entity.Auth;
import com.bloggios.userService.Entity.RegistrationOtp;
import com.bloggios.userService.Exception.UserServiceException;
import com.bloggios.userService.Payload.ApiResponse;
import com.bloggios.userService.Payload.AuthProvider;
import com.bloggios.userService.Payload.AuthRequest;
import com.bloggios.userService.Payload.OtpPayload;
import com.bloggios.userService.Repository.AuthRepository;
import com.bloggios.userService.Repository.RegistrationOtpRepository;
import com.bloggios.userService.Service.AuthService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;


/**
 * @author - rohit
 * @project - Bloggios-Learning-Platform-Backend
 * @package - com.bloggios.userService.Implementation
 * @created_on - April 29-2023
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthImplementation implements AuthService {

    private final AuthRepository authRepository;
    private final DatabaseLogic databaseLogic;
    private final PostRegistration postRegistration;
    private final RegistrationOtpRepository registrationOtpRepository;

    /**
     * Registering new Local user to Bloggios Learn
     * @param authRequest
     * @return ApiResponse
     */
    @Override
    public ApiResponse registerUser(AuthRequest authRequest) {
        databaseLogic.checkEmailIfExists(authRequest);
        Auth loadedAuth = Auth
                .builder()
                .email(authRequest.getEmail())
                .password(authRequest.getPassword())
                .dateRegistered(new Date())
                .emailVerified(false)
                .isEnabled(false)
                .authProvider(AuthProvider.LOCAL)
                .build();
        log.info("Saving user to MySql database");
        Auth savedAuth = authRepository.save(loadedAuth);
        postRegistration.registrationDone(savedAuth);
        return ApiResponse
                .builder()
                .message(ServiceConstants.REGISTERED)
                .build();
    }

    /**
     *
     * Verify otp of user
     * @param otpPayload
     * @return
     */
    @Override
    @Transactional
    public ApiResponse verifyOtp(OtpPayload otpPayload) {
        RegistrationOtp userOtp = registrationOtpRepository.findByOtp(otpPayload.getOtp())
                .orElseThrow(() -> new UserServiceException(ServiceConstants.INVALID_OTP, ErrorCodes.INVALID_ENTRY, HttpStatus.BAD_REQUEST));
        Auth auth = userOtp.getAuth();
        if (!auth.getEmail().equals(otpPayload.getEmail()))
            throw new UserServiceException("Not Authorized", ErrorCodes.INVALID_ENTRY, HttpStatus.BAD_REQUEST);
        if (userOtp.getExpiry().before(new Date(System.currentTimeMillis()))){
            throw new UserServiceException("OTP is Expired please generate new OTP", ErrorCodes.EXPIRED_ENTRY, HttpStatus.NOT_ACCEPTABLE);
        }
        auth.setIsEnabled(true);
        auth.setEmailVerified(true);
        authRepository.save(auth);
        registrationOtpRepository.delete(userOtp);
        return new ApiResponse(ServiceConstants.EMAIL_VERIFIED);
    }

    /**
     *
     * Resend OTP to registered user
     * @param userId
     * @return
     */
    @Override
    @Transactional
    public ApiResponse resendOtp(String userId) {
        RegistrationOtp registrationOtp = registrationOtpRepository.findByAuth_AuthId(userId)
                .orElseThrow(() -> new UserServiceException(ServiceConstants.OTP_NOT_FOUND, ErrorCodes.NOT_FOUND, HttpStatus.NOT_FOUND));
        Auth auth = authRepository.findById(userId).get();
        if (auth.getEmailVerified())
            throw new UserServiceException(ServiceConstants.EMAIL_ALREADY_VERIFIED, ErrorCodes.DUPLICATE_ENTRY, HttpStatus.ALREADY_REPORTED);
        if (auth.getIsEnabled())
            throw new UserServiceException(ErrorConstants.NOT_ENABLED, ErrorCodes.BLOCKED_ENTRY, HttpStatus.FORBIDDEN);
        registrationOtpRepository.delete(registrationOtp);
        postRegistration.registrationDone(auth);
        return ApiResponse
                .builder()
                .message("OTP sent successfully to your registered mail address")
                .build();
    }
}
