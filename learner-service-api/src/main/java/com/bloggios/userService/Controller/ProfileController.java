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

import com.bloggios.userService.Logic.UriLogic;
import com.bloggios.userService.Payload.LearnerResponse;
import com.bloggios.userService.Payload.ProfileRequest;
import com.bloggios.userService.Service.ProfileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

/**
 * @author - rohit
 * @project - Bloggios-Learning-Platform-Backend
 * @package - com.bloggios.userService.Controller
 * @created_on - June 04-2023
 */

@RestController
@RequestMapping("/learner-service")
@Slf4j
public class ProfileController {
    
    @Autowired
    private ProfileService profileService;

    @PostMapping
    public ResponseEntity<?> addProfile(@RequestBody ProfileRequest profileRequest){
        log.warn(profileRequest.getDob().toString());
        LearnerResponse learnerResponse = profileService.addData(profileRequest);
        URI uri = UriLogic.getUri("id", learnerResponse.getId(), "/learner");
        return ResponseEntity.created(uri).body(learnerResponse);
    }
}
