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

package com.bloggios.userService.Implementation;

import com.bloggios.userService.Configuration.ModelMapperConfiguration;
import com.bloggios.userService.Entity.LearnerProfile;
import com.bloggios.userService.Logic.LogicProcessor;
import com.bloggios.userService.Payload.LearnerResponse;
import com.bloggios.userService.Payload.ProfileRequest;
import com.bloggios.userService.Repository.ProfileRepository;
import com.bloggios.userService.Service.ProfileService;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;

/**
 * @author - rohit
 * @project - Bloggios-Learning-Platform-Backend
 * @package - com.bloggios.userService.Implementation
 * @created_on - June 04-2023
 */

@Service
@Slf4j
public class ProfileImplementation implements ProfileService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    @Transactional
    public LearnerResponse addData(@NotNull ProfileRequest profileRequest) {
        LearnerProfile map = modelMapper.map(profileRequest, LearnerProfile.class);
        map.setName(LogicProcessor.capitalizeLetter(profileRequest.getName()));
        map.setDateGenerated(new Date());
        LearnerProfile save = profileRepository.save(map);
        return LearnerResponse
                .builder()
                .message("Profile successfully saved in database")
                .isCreated(true)
                .id(save.getLearnerProfileId())
                .build();
    }
}
