/*
 * Copyright 2021 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

 package jqweb;

import java.util.stream.Collectors;
import javax.inject.Singleton;
import com.arakelian.jq.*;

@Singleton
public class JqService {
    private static final JqLibrary jqLib = ImmutableJqLibrary.of();

    String filter(String inputJson, String jqFilter) throws Exception {
        final JqRequest request = ImmutableJqRequest.builder()
            .lib(jqLib)
            .input(inputJson)
            .filter(jqFilter)
            .build();
        
        final JqResponse response = request.execute();

        if(response.hasErrors()) {
            throw new Exception(response.getErrors().stream().collect(Collectors.joining(", ")));
        }

        final String output =  response.getOutput();
        System.out.println("JQ output: " + output);
        return output;
    }
}