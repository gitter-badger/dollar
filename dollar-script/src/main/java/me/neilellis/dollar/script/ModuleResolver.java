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

package me.neilellis.dollar.script;

import me.neilellis.dollar.Pipeable;
import me.neilellis.dollar.plugin.ExtensionPoint;
import me.neilellis.dollar.plugin.NoOpProxy;

import java.util.ServiceLoader;

/**
 * @author <a href="http://uk.linkedin.com/in/neilellis">Neil Ellis</a>
 */
public interface ModuleResolver extends ExtensionPoint<ModuleResolver> {

    public static ModuleResolver resolveModule(String scheme) {
        final ServiceLoader<ModuleResolver> loader = ServiceLoader.load(ModuleResolver.class);
        for (ModuleResolver piper : loader) {
            if (piper.getScheme().equals(scheme)) {
                return piper.copy();
            }
        }
        return NoOpProxy.newInstance(ModuleResolver.class);
    }


    String getScheme();

    Pipeable resolve(String uriWithoutScheme, ScriptScope scope) throws Exception;
}
