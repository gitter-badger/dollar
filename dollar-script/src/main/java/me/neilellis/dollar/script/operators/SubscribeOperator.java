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

import me.neilellis.dollar.script.Operator;
import me.neilellis.dollar.script.ScriptScope;
import me.neilellis.dollar.var;
import org.codehaus.jparsec.functors.Binary;

import java.util.function.Supplier;

import static me.neilellis.dollar.DollarStatic.fix;
import static me.neilellis.dollar.script.DollarScriptSupport.wrapReactiveBinary;

/**
 * @author <a href="http://uk.linkedin.com/in/neilellis">Neil Ellis</a>
 */
public class SubscribeOperator implements Binary<var>, Operator {
    private Supplier<String> source;
    private ScriptScope scope;


    public SubscribeOperator(ScriptScope scope) {
        this.scope = scope;
    }


    @Override
    public var map(var lhs, var rhs) {

        return wrapReactiveBinary(scope, lhs, rhs,
                                  () -> lhs.$subscribe(
                                          i -> scope.getDollarParser().inScope("subscribe", scope, newScope -> {
                                      final var it = fix(i, false);
                                      scope.getDollarParser().currentScope().setParameter("1", it);
                                      scope.getDollarParser().currentScope().setParameter("it", it);
                                      return fix(rhs, false);
                                  })));

    }

    @Override
    public void setSource(Supplier<String> source) {
        this.source = source;
    }
}
