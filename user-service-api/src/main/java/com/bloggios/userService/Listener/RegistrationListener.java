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

package com.bloggios.userService.Listener;

import com.bloggios.userService.BusinessLogic.OTPGenerator;
import com.bloggios.userService.Events.RegistrationEvent;
import com.bloggios.userService.Payload.OtpPayload;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * @author - rohit
 * @project - Bloggios-Learning-Platform-Backend
 * @package - com.bloggios.userService.Listner
 * @created_on - May 06-2023
 */

@RequiredArgsConstructor
@Slf4j
@Component
public class RegistrationListener implements ApplicationListener<RegistrationEvent> {

    private final OTPGenerator otpGenerator;
    private final KafkaTemplate<String, OtpPayload> kafkaTemplate;

    @Override
    public void onApplicationEvent(RegistrationEvent event) {

    }
}
