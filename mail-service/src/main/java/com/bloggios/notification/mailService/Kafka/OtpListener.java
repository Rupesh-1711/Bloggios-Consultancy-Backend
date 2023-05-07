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

package com.bloggios.notification.mailService.Kafka;

import com.bloggios.notification.mailService.Payload.PostRegistrationOtpPayload;
import com.bloggios.notification.mailService.Service.MailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author - rohit
 * @project - Bloggios-Learning-Platform-Backend
 * @package - com.bloggios.notification.mailService.Kafka
 * @created_on - May 07-2023
 */

@Component
@RequiredArgsConstructor
@Slf4j
public class OtpListener {

    private final MailService mailService;

    @KafkaListener(topics = "otpMessage")
    public void getOtpListnerPayload(PostRegistrationOtpPayload postRegistrationOtpPayload) throws MessagingException {
        log.warn("Inside KafkaListener");
        log.warn("Otp is : " + postRegistrationOtpPayload.getOtp());
        mailService.sendMail(postRegistrationOtpPayload);
        log.warn("Mail Sent");
    }
}
