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

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author - rohit
 * @project - Bloggios-Learning-Platform-Backend
 * @package - com.bloggios.userService.Logic
 * @created_on - June 07-2023
 */

@Service
public class LogicProcessor {

    public static String capitalizeLetter(String input) {
        StringBuffer name = new StringBuffer();
        Arrays
                .stream(input
                        .replaceAll("\\s+", " ")
                        .trim()
                        .split(" "))
                .toList()
                .forEach(e -> {
                    String finalName = e.substring(0,1).toUpperCase() + e.substring(1);
                    name.append(finalName).append(" ");
                });
        return name
                .toString()
                .trim();
    }
}
