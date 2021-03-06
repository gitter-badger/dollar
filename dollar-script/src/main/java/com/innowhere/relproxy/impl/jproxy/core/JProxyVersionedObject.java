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

package com.innowhere.relproxy.impl.jproxy.core;

import com.innowhere.relproxy.impl.GenericProxyVersionedObject;
import com.innowhere.relproxy.impl.jproxy.core.clsmgr.JProxyEngine;

import java.lang.reflect.Field;

/**
 * @param <T>
 * @author jmarranz
 */
public class JProxyVersionedObject<T> extends GenericProxyVersionedObject<T> {
    protected String className;

    public JProxyVersionedObject(T obj, JProxyInvocationHandler parent) {
        super(obj, parent);
        this.className = obj.getClass().getName();
    }

    @Override
    public T getCurrent() {
        return obj;
    }

    public JProxyInvocationHandler getJProxyInvocationHandler() {
        return (JProxyInvocationHandler) parent;
    }

    @Override
    protected <T> Class<T> reloadClass() {
        JProxyEngine engine = getJProxyInvocationHandler().getJProxyImpl().getJProxyEngine();
        return (Class<T>) engine.findClass(className);
    }

    @Override
    protected boolean ignoreField(Field field) {
        return false; // Todos cuentan (útil en Groovy no en Java)
    }
}