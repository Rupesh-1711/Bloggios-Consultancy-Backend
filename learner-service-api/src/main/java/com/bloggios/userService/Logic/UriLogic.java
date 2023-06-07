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

package com.bloggios.userService.Logic;

import com.bloggios.userService.Payload.LearnerResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

/**
 * @author - rohit
 * @project - Bloggios-Learning-Platform-Backend
 * @package - com.bloggios.userService.Logic
 * @created_on - June 07-2023
 */

@Component
public class UriLogic {

    public static URI getUri(String queryKey, String queryValue, String path) {
        URI uri = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path(path)
                .queryParam(queryKey, queryValue)
                .build()
                .toUri();
        return uri;
    }
}
