/*
 * Copyright (c) 2018, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
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
package org.wso2.carbon.clustering.mesos.client.model.iam.auth;

import org.wso2.carbon.clustering.mesos.client.utils.ModelUtils;

public class Credentials {

    private String uid;
    private Integer exp;
    private String password;
    private String token;

    public Credentials() {

    }

    public Credentials(String uid, String password) {

        this.uid = uid;
        this.password = password;
    }

    public String getUId() {

        return uid;
    }

    public void setUId(String uid) {

        this.uid = uid;
    }

    public Integer getExp() {

        return exp;
    }

    public void setExp(Integer exp) {

        this.exp = exp;
    }

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    public String getToken() {

        return token;
    }

    public void setToken(String token) {

        this.token = token;
    }

    @Override
    public String toString() {

        return ModelUtils.toString(this);
    }
}
