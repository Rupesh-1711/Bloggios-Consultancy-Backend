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

package com.bloggios.userService.Entity;

import com.bloggios.userService.Payload.Gender;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.time.LocalDate;
import java.util.Date;

/**
 * @author - rohit
 * @project - Bloggios-Learning-Platform-Backend
 * @package - com.bloggios.userService.Entity
 * @created_on - June 04-2023
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
public class LearnerProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(length = 7000)
    private String about;

    @Column(length = 5000)
    private String imageUrl;
    private Date dateGenerated;
    private LocalDate dob;
    private String tag;

    public LearnerProfile(String name, Gender gender, String about, String imageUrl, Date dateGenerated, LocalDate dob, String tag) {
        this.name = name;
        this.gender = gender;
        this.about = about;
        this.imageUrl = imageUrl;
        this.dateGenerated = new Date(System.currentTimeMillis());
        this.dob = dob;
        this.tag = tag;
    }
}
