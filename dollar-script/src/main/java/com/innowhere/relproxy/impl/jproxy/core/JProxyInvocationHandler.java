
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

import com.innowhere.relproxy.impl.GenericProxyInvocationHandler;

/**
 * @author jmarranz
 */
public class JProxyInvocationHandler<T> extends GenericProxyInvocationHandler {
    public JProxyInvocationHandler(T obj, JProxyImpl root) {
        super(root);
        this.verObj = new JProxyVersionedObject<T>(obj, this);
    }

    public JProxyImpl getJProxyImpl() {
        return (JProxyImpl) root;
    }

}