/*
 * Copyright (c) 2021, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
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

package org.wso2.carbon.membership.scheme.azure.resolver;

import org.apache.axis2.description.Parameter;
import org.wso2.carbon.membership.scheme.azure.Constants;
import org.wso2.carbon.membership.scheme.azure.exceptions.AzureMembershipSchemeException;
import org.wso2.carbon.utils.xml.StringUtils;

import java.util.Map;
import java.util.Set;

/**
 * Abstraction for resolving networks addresses
 */
public abstract class AddressResolver {

    private final Map<String, Parameter> parameters;
    private String kubernetesNamespace;
    private String kubernetesServices;
    private String[] kubernetesServicesArray;

    AddressResolver(final Map<String, Parameter> parameters) throws AzureMembershipSchemeException {
        this.parameters = parameters;
        initialize();
    }

    /**
     * Initialize AddressResolver instance
     *
     * @throws AzureMembershipSchemeException if an error occurred while initializing
     */
    private void initialize() throws AzureMembershipSchemeException {

    }

    /**
     * Resolve the addresses of the members
     *
     * @return {@link Set} of addresses
     * @throws AzureMembershipSchemeException if an error occurred while resolving the addresses
     */
    public abstract Set<String> resolveAddresses() throws AzureMembershipSchemeException;

    String getParameterValue(String parameterName, String defaultValue)
            throws AzureMembershipSchemeException {
        Parameter azureServicesParam = parameters.get(parameterName);
        if (azureServicesParam == null) {
            if (defaultValue == null) {
                throw new AzureMembershipSchemeException(parameterName + " parameter not found");
            } else {
                return defaultValue;
            }
        }
        return (String) azureServicesParam.getValue();
    }

    public Map<String, Parameter> getParameters() {
        return parameters;
    }
}