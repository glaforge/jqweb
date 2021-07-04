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

import io.micronaut.http.annotation.*;
import io.micronaut.http.MediaType;
import javax.inject.Inject;
import com.google.gson.Gson;
import java.util.Optional;
import java.util.Map;

@Controller("/")
public class JqController {

    @Inject
    JqService jqService;

    @Post(uri="/", produces="application/json", consumes=MediaType.APPLICATION_JSON)
    public String index(@QueryValue("filter") Optional<String> jqFilter, @Body String jsonBody) {
        try {
            if (jqFilter.isPresent()) {
                String filter = jqFilter.get();
                return jqService.filter(jsonBody, filter);
            } else {
                return jsonBody;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Gson().toJson(Map.of("error", e.getMessage()));
        }
    }
}