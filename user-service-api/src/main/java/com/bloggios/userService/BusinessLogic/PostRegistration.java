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
import com.bloggios.userService.Entity.RegistrationOtp;
import com.bloggios.userService.Events.KafkaProducer;
import com.bloggios.userService.Events.RegistrationEvent;
import com.bloggios.userService.Payload.PostRegistrationOtpPayload;
import com.bloggios.userService.Repository.RegistrationOtpRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
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

    private final ApplicationEventPublisher applicationEventPublisher;
    private final OTPGenerator otpGenerator;
    private final KafkaProducer kafkaProducer;
    private final RegistrationOtpRepository registrationOtpRepository;

    @Async
    public void registrationDone(Auth auth){
        RegistrationOtp registrationOtp = otpGenerator.otpSupplier(auth);
        kafkaProducer.produceOtp(auth.getEmail(), registrationOtp.getOtp());
//        applicationEventPublisher.publishEvent(new RegistrationEvent(auth));
        registrationOtpRepository.save(registrationOtp);
    }
}
