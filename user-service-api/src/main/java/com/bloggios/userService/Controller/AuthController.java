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

package com.bloggios.userService.Controller;

import com.bloggios.userService.Payload.ApiResponse;
import com.bloggios.userService.Payload.AuthRequest;
import com.bloggios.userService.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author - rohit
 * @project - Bloggios-Learning-Platform-Backend
 * @package - com.bloggios.userService.Controller
 * @created_on - April 29-2023
 */

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final AuthService authService;

    //curl -X POST http://localhost:7000/auth/register -H "Content-Type:application/json" -d "{\"email\":\"hello@gmail.com\",\"password\":\"helloworld\"}"
    @PostMapping(value = "/register")
    public ResponseEntity<ApiResponse> registerUser(@RequestBody AuthRequest authRequest){
        logger.info("Entry");
        ApiResponse apiResponse = authService.registerUser(authRequest);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/register")
    public ResponseEntity<String> response(){
        return ResponseEntity.ok("Got Data");
    }
}
