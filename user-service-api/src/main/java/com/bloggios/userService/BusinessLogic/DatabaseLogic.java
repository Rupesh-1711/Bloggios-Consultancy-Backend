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

package com.bloggios.userService.BusinessLogic;

import com.bloggios.userService.Constants.ErrorCodes;
import com.bloggios.userService.Constants.ErrorConstants;
import com.bloggios.userService.Exception.UserServiceException;
import com.bloggios.userService.Payload.AuthRequest;
import com.bloggios.userService.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * @author - rohit
 * @project - Bloggios-Learning-Platform-Backend
 * @package - com.bloggios.userService.BusinessLogic
 * @created_on - May 04-2023
 */

@Component
@RequiredArgsConstructor
public class DatabaseLogic {

    private final AuthRepository authRepository;

    public void checkEmailIfExists(AuthRequest authRequest) {
        if (authRepository.existsByEmail(authRequest.getEmail())){
            throw new UserServiceException(
                    ErrorConstants.EMAIL_EXISTS,
                    ErrorCodes.DUPLICATE_ENTRY,
                    HttpStatus.CONFLICT
            );
        }
    }
}
