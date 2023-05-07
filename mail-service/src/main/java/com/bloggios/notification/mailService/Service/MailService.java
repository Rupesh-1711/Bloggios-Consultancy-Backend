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

package com.bloggios.notification.mailService.Service;

import com.bloggios.notification.mailService.Modal.Auth;
import com.bloggios.notification.mailService.Payload.PostRegistrationOtpPayload;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * @author - rohit
 * @project - Bloggios-Learning-Platform-Backend
 * @package - com.bloggios.notification.mailService.Service
 * @created_on - May 05-2023
 */

@Service
@RequiredArgsConstructor
public class MailService {

    private final TemplateEngine templateEngine;
    private final JavaMailSender javaMailSender;

    public void sendMail(PostRegistrationOtpPayload postRegistrationOtpPayload) throws MessagingException {
        Context context = new Context();
        context.setVariable("otpPayload", postRegistrationOtpPayload);
        String process = templateEngine.process("Email/RegisterMail", context);
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setSubject("Welcome to Bloggios-Learn Platform");
        helper.setText(process, true);
        helper.setTo(postRegistrationOtpPayload.getEmail());
        javaMailSender.send(mimeMessage);
    }
}
