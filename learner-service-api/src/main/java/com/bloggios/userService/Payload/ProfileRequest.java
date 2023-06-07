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

package com.bloggios.userService.Payload;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * @author - rohit
 * @project - Bloggios-Learning-Platform-Backend
 * @package - com.bloggios.userService.Payload
 * @created_on - June 07-2023
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileRequest {
    private String name;
    private String about;
    private Gender gender;
    private LocalDate dob;
    private String tag;
}
