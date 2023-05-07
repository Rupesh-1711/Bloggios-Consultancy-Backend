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
import com.bloggios.userService.Constants.ServiceConstants;
import com.bloggios.userService.Entity.Auth;
import com.bloggios.userService.Payload.ApiResponse;
import com.bloggios.userService.Payload.AuthProvider;
import com.bloggios.userService.Payload.AuthRequest;
import com.bloggios.userService.Payload.OtpPayload;
import com.bloggios.userService.Repository.AuthRepository;
import com.bloggios.userService.Repository.RegistrationOtpRepository;
import com.bloggios.userService.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
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
public class AuthImplementation implements AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthImplementation.class);

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
        logger.info("Saving user to MySql database");
        Auth savedAuth = authRepository.save(loadedAuth);
        postRegistration.registrationDone(savedAuth);
        return ApiResponse
                .builder()
                .message(ServiceConstants.REGISTERED)
                .build();
    }

    @Override
    public ApiResponse verifyOtp(OtpPayload otpPayload) {
        return null;
    }
}
