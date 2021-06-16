/*
 * Copyright 2017-2020 original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.company.micronaut.kubernetes.configuration;

import io.micronaut.context.annotation.BootstrapContextCompatible;
import io.micronaut.context.annotation.ConfigurationProperties;
import io.micronaut.discovery.DiscoveryConfiguration;
import io.micronaut.core.annotation.NonNull;

import java.util.Collection;
import java.util.Collections;

/**
 * Encapsulates constants for Company Kubernetes configuration.
 *
 * @author Stephane Paulus
 * @since 1.0.0
 */
@ConfigurationProperties(CompanyKubernetesConfiguration.PREFIX)
@BootstrapContextCompatible
public class CompanyKubernetesConfiguration {

    public static final String PREFIX = "company.kubernetes";
    private KubernetesSecretsConfiguration secrets = new KubernetesSecretsConfiguration();
    private KubernetesConfigMapsConfiguration configMaps = new KubernetesConfigMapsConfiguration();
    private String contextPath = "..data";

    /**
     * Default constructor.
     */
    public CompanyKubernetesConfiguration() {
    }

    /**
     * @return the {@link KubernetesSecretsConfiguration}.
     */
    @NonNull
    public KubernetesSecretsConfiguration getSecrets() {
        return secrets;
    }

    /**
     * @param secretsConfiguration the {@link KubernetesSecretsConfiguration}.
     */
    public void setSecrets(KubernetesSecretsConfiguration secretsConfiguration) {
        this.secrets = secretsConfiguration;
    }

    /**
     * @return The config maps configuration properties
     */
    @NonNull
    public KubernetesConfigMapsConfiguration getConfigMaps() {
        return configMaps;
    }

    /**
     * @param configMapsConfiguration The config maps configuration properties
     */
    public void setConfigMaps(KubernetesConfigMapsConfiguration configMapsConfiguration) {
        this.configMaps = configMapsConfiguration;
    }

    /**
     * @return the context path string for hot reload.
     */
    @NonNull
    public String getContextPath() {
        return contextPath;
    }

    /**
     * @param contextPath The config for the context path used for hot reload
     */
    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    @Override
    public String toString() {
        return "CompanyKubernetesConfiguration{" +
                ", secrets=" + secrets +
                ", configMaps=" + configMaps +
                '}';
    }

    /**
     * Base class for other configuration sub-classes.
     */
    private abstract static class AbstractKubernetesConfiguration extends DiscoveryConfiguration {
        static final boolean DEFAULT_ENABLED = false;

        private Collection<String> paths;
        private boolean enabled = DEFAULT_ENABLED;

        /**
         * @return paths where secrets are mounted
         */
        public Collection<String> getPaths() {
            if (paths == null) {
                return Collections.emptySet();
            }
            return paths;
        }

        /**
         * @param paths where secrets are mounted
         */
        public void setPaths(Collection<String> paths) {
            this.paths = paths;
        }

        @Override
        public boolean isEnabled() {
            return enabled;
        }

        /**
         * @param enabled enabled flag.
         */
        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }
    }

    /**
     * Kubernetes secrets configuration properties.
     */
    @ConfigurationProperties(KubernetesSecretsConfiguration.PREFIX)
    @BootstrapContextCompatible
    public static class KubernetesSecretsConfiguration extends AbstractKubernetesConfiguration {
        public static final String PREFIX = "secrets";
    }

    /**
     * Kubernetes config maps configuration properties.
     */
    @ConfigurationProperties(KubernetesConfigMapsConfiguration.PREFIX)
    @BootstrapContextCompatible
    public static class KubernetesConfigMapsConfiguration extends AbstractKubernetesConfiguration {
        public static final String PREFIX = "config-maps";
    }
}
