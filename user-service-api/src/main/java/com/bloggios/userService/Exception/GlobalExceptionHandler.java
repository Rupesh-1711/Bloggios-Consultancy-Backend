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

package com.bloggios.userService.Exception;

import com.bloggios.userService.Payload.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author - rohit
 * @project - Bloggios-Learning-Platform-Backend
 * @package - com.bloggios.userService.Exception
 * @created_on - May 04-2023
 */

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserServiceException.class)
    public ResponseEntity<ApiResponse> UserServiceExceptionHandler(UserServiceException exception){
        return new ResponseEntity<>(new ApiResponse(exception.getMessage(), exception.getErrorCode()), exception.getStatus());
    }

}
