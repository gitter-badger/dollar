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

package com.innowhere.relproxy.impl.jproxy.screngine;

import com.innowhere.relproxy.RelProxyException;
import com.innowhere.relproxy.impl.jproxy.JProxyConfigImpl;
import com.innowhere.relproxy.impl.jproxy.core.JProxyImpl;
import com.innowhere.relproxy.impl.jproxy.core.clsmgr.ClassDescriptorSourceScript;
import com.innowhere.relproxy.impl.jproxy.core.clsmgr.JProxyEngine;
import com.innowhere.relproxy.impl.jproxy.core.clsmgr.SourceScript;
import com.innowhere.relproxy.impl.jproxy.core.clsmgr.SourceScriptInMemory;
import com.innowhere.relproxy.impl.jproxy.core.clsmgr.comp.JProxyCompilationException;
import com.innowhere.relproxy.impl.jproxy.shell.JProxyShellClassLoader;

import javax.script.ScriptContext;
import javax.script.ScriptException;
import java.io.File;

/**
 * @author jmarranz
 */
public class JProxyScriptEngineDelegateImpl extends JProxyImpl {
    protected JProxyScriptEngineImpl parent;
    protected ClassDescriptorSourceScript classDescSourceScript;
    protected long codeBufferModTimestamp = 0;
    protected long lastCodeCompiledTimestamp = 0;

    public JProxyScriptEngineDelegateImpl(JProxyScriptEngineImpl parent, JProxyConfigImpl config) {
        this.parent = parent;

        SourceScript sourceFileScript = SourceScriptInMemory.createSourceScriptInMemory("");

        JProxyShellClassLoader classLoader = null;
        String classFolder = config.getClassFolder();
        if (classFolder != null)
            classLoader = new JProxyShellClassLoader(getDefaultClassLoader(), new File(classFolder));

        this.classDescSourceScript = init(config, sourceFileScript, classLoader);
    }

    @Override
    public Class getMainParamClass() {
        return ScriptContext.class;
    }

    private SourceScriptInMemory getSourceScriptInMemory() {
        return (SourceScriptInMemory) classDescSourceScript.getSourceScript();
    }

    public Object execute(String code, ScriptContext context) throws ScriptException {
        JProxyEngine jproxyEngine = getJProxyEngine();

        Class scriptClass;
        synchronized (jproxyEngine) {
            if (!getSourceScriptInMemory().getScriptCode().equals(code)) {
                this.codeBufferModTimestamp = System.currentTimeMillis();

                getSourceScriptInMemory().setScriptCode(code, codeBufferModTimestamp);
                // Recuerda que cada vez que se obtiene el timestamp se llama a System.currentTimeMillis(), es imposible que el usuario haga algo en menos de 1ms

                ClassDescriptorSourceScript classDescSourceScript2 = null;
                try {
                    classDescSourceScript2 = jproxyEngine.detectChangesInSources();
                } catch (JProxyCompilationException ex) {
                    throw new ScriptException(ex);
                }

                if (classDescSourceScript2 != classDescSourceScript)
                    throw new RelProxyException("Internal Error");

                this.lastCodeCompiledTimestamp = System.currentTimeMillis();
                if (lastCodeCompiledTimestamp == codeBufferModTimestamp) // Demasiado rápido compilando
                {
                    // Aseguramos que el siguiente código se ejecuta si o si con un codeBufferModTimestamp mayor que el timestamp de la compilación
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException ex) {
                        throw new RelProxyException(ex);
                    }
                }
            }

            scriptClass = classDescSourceScript.getLastLoadedClass();
        }

        try {
            return ClassDescriptorSourceScript.callMainMethod(scriptClass, parent, context);
        } catch (Throwable ex) {
            Exception ex2 = (ex instanceof Exception) ? (Exception) ex : new RelProxyException(ex);
            throw new ScriptException(ex2);
        }
    }
}
