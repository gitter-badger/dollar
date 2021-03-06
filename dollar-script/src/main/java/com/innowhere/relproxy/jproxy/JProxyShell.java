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

package com.innowhere.relproxy.jproxy;

import com.innowhere.relproxy.impl.jproxy.shell.JProxyShellImpl;

/**
 * Is the main class to execute shell scripting based on Java.
 *
 * <p>You are not going to use directly this class, use instead <code>jproxysh</code> command line.</p>
 *
 * @author Jose Maria Arranz Santamaria
 */
public class JProxyShell {
    /**
     * The main method.
     *
     * @param args arguments with the necessary data to initialize and executing the provided script.
     */
    public static void main(String[] args) {
        JProxyShellImpl.main(args);
    }
}
