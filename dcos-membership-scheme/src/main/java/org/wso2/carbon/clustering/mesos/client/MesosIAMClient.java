/*
 * Copyright (c) 2016, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
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
package org.wso2.carbon.clustering.mesos.client;

import feign.Feign;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.Response;
import feign.codec.ErrorDecoder;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.slf4j.Slf4jLogger;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.clustering.mesos.client.utils.MesosException;
import org.wso2.carbon.clustering.mesos.client.utils.ModelUtils;

import static java.util.Arrays.asList;

public class MesosIAMClient {

    private static final Log log = LogFactory.getLog(MesosMarathonClient.class);

    /**
     * The generalized version of the method that allows more in-depth customizations via
     * {@link RequestInterceptor}s.
     *
     * @param endpoint URL of Mesos IAM
     */
    public static MesosIAM getInstance(String endpoint, RequestInterceptor... interceptors) {

        Feign.Builder b = Feign.builder()
                .encoder(new GsonEncoder(ModelUtils.GSON))
                .logger(new Slf4jLogger())
                .decoder(new GsonDecoder(ModelUtils.GSON))
                .errorDecoder(new MesosIAMErrorDecoder());
        if (interceptors != null) {
            b.requestInterceptors(asList(interceptors));
        }
        b.requestInterceptor(new MesosIAMHeadersInterceptor());
        return b.target(MesosIAM.class, endpoint);
    }

    private static class MesosIAMHeadersInterceptor implements RequestInterceptor {

        @Override
        public void apply(RequestTemplate template) {

            if (log.isDebugEnabled()) {
                log.debug(String.format("Mesos DNS [Request] %s", template.request()));
            }
        }
    }

    private static class MesosIAMErrorDecoder implements ErrorDecoder {

        @Override
        public Exception decode(String methodKey, Response response) {

            return new MesosException(methodKey, response);
        }
    }
}
