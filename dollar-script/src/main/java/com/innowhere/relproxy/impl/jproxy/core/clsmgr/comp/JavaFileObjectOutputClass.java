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

package com.innowhere.relproxy.impl.jproxy.core.clsmgr.comp;

import com.innowhere.relproxy.RelProxyException;

import javax.tools.SimpleJavaFileObject;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

/**
 * http://www.javablogging.com/dynamic-in-memory-compilation/
 *
 * @author jmarranz
 */
public class JavaFileObjectOutputClass extends SimpleJavaFileObject {

    /**
     * Byte code created by the compiler will be stored in this
     * ByteArrayOutputStream so that we can later get the
     * byte array out of it
     * and put it in the memory as an instance of our class.
     */
    protected final ByteArrayOutputStream bos = new ByteArrayOutputStream();
    protected String binaryName;

    /**
     * Registers the compiled class object under URI
     * containing the class full name
     *
     * @param name Full name of the compiled class
     * @param kind Kind of the data. It will be CLASS in our case
     */
    public JavaFileObjectOutputClass(String name, Kind kind) {
        super(URI.create("string:///" + name.replace('.', '/') + kind.extension), kind);

        if (!Kind.CLASS.equals(kind)) throw new RelProxyException("Unexpected");
        this.binaryName = name;
    }

    public String binaryName() {
        return binaryName;
    }

    public byte[] getBytes() {
        return bos.toByteArray();
    }

    @Override
    public OutputStream openOutputStream() throws IOException {
        return bos;
    }

}
