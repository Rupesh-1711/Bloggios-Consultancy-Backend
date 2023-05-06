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

import com.bloggios.userService.Entity.Auth;
import com.bloggios.userService.Events.RegistrationEvent;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * @author - rohit
 * @project - Bloggios-Learning-Platform-Backend
 * @package - com.bloggios.userService.BusinessLogic
 * @created_on - May 06-2023
 */

@Service
@RequiredArgsConstructor
public class PostRegistration {

    private static final Logger logger = LoggerFactory.getLogger(PostRegistration.class);

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void registrationDone(Auth auth){
        logger.info("In Event");
        logger.info(auth.getEmail());
        applicationEventPublisher.publishEvent(new RegistrationEvent(auth));
    }
}
