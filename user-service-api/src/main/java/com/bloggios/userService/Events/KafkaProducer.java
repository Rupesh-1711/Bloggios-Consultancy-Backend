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

package com.bloggios.userService.Events;

import com.bloggios.userService.BusinessLogic.PostRegistration;
import com.bloggios.userService.Payload.PostRegistrationOtpPayload;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * @author - rohit
 * @project - Bloggios-Learning-Platform-Backend
 * @package - com.bloggios.userService.Events
 * @created_on - May 08-2023
 */

@RequiredArgsConstructor
@Service
public class KafkaProducer {

    private final KafkaTemplate<String, PostRegistrationOtpPayload> kafkaTemplate;

    public void produceOtp(String email, String otp){
        kafkaTemplate.send("otpMessage", new PostRegistrationOtpPayload(email, otp));
    }
}