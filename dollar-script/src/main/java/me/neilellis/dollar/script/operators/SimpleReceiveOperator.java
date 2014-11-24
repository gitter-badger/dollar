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

package me.neilellis.dollar.script.operators;

import me.neilellis.dollar.script.ScriptScope;
import me.neilellis.dollar.script.UnaryOp;
import me.neilellis.dollar.script.exceptions.DollarScriptException;
import me.neilellis.dollar.types.DollarFactory;
import me.neilellis.dollar.var;

/**
 * @author <a href="http://uk.linkedin.com/in/neilellis">Neil Ellis</a>
 */
public class SimpleReceiveOperator extends UnaryOp {


    public SimpleReceiveOperator(ScriptScope scope) {
        super(scope, null);
    }


    @Override
    public var map(var from) {
        try {
            return DollarFactory.fromLambda(v -> {
                try {
                    return DollarFactory.fromURI(from).$receive();
                } catch (Exception e) {
                    return scope.getDollarParser().getErrorHandler().handle(scope, source.get(), e);
                }
            });
        } catch (AssertionError e) {
            return scope.getDollarParser().getErrorHandler().handle(scope, source.get(), e);
        } catch (DollarScriptException e) {
            return scope.getDollarParser().getErrorHandler().handle(scope, source.get(), e);
        } catch (Exception e) {
            return scope.getDollarParser().getErrorHandler().handle(scope, source.get(), e);
        }
    }


}