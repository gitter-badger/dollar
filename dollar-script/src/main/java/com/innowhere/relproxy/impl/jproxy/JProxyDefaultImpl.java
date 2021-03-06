/*
 * Copyright (c) 2014 Neil Ellis
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.innowhere.relproxy.impl.jproxy;

import com.innowhere.relproxy.impl.jproxy.core.JProxyImpl;
import com.innowhere.relproxy.jproxy.JProxyConfig;

/**
 * @author jmarranz
 */
public class JProxyDefaultImpl extends JProxyImpl {
    public JProxyDefaultImpl() {
    }

    public static JProxyConfig createJProxyConfig() {
        return new JProxyConfigImpl();
    }

    public static void initStatic(JProxyConfigImpl config) {
        if (!config.isEnabled()) return;

        checkSingletonNull(SINGLETON);
        SINGLETON = new JProxyDefaultImpl();
        SINGLETON.init(config, null, null);
    }

    public static <T> T createStatic(T obj, Class<T> clasz) {
        if (SINGLETON == null)
            return obj; // No se ha llamado al init o enabled = false

        return SINGLETON.create(obj, clasz);
    }

    public static boolean stopStatic() {
        if (SINGLETON == null)
            return false;

        return SINGLETON.stop();
    }

    public static boolean startStatic() {
        if (SINGLETON == null)
            return false;

        return SINGLETON.start();
    }

    @Override
    public Class getMainParamClass() {
        return null;
    }
}
