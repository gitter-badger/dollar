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

package me.neilellis.dollar.types;

import com.github.oxo42.stateless4j.StateMachine;
import com.google.common.collect.ImmutableList;
import me.neilellis.dollar.DollarEval;
import me.neilellis.dollar.Pipeable;
import me.neilellis.dollar.Type;
import me.neilellis.dollar.collections.ImmutableMap;
import me.neilellis.dollar.guard.*;
import me.neilellis.dollar.json.JsonArray;
import me.neilellis.dollar.json.JsonObject;
import me.neilellis.dollar.var;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Special collection type, the single value. This is not a single value list or map, it is a collection of one element.
 * In DollarScript this is useful for mathematical functions as it behaves like a true lambda.
 *
 * @author <a href="http://uk.linkedin.com/in/neilellis">Neil Ellis</a>
 */
public class DollarBlockCollection implements var {
    private List<var> value;


    public DollarBlockCollection(List<var> value) {
        this.value = value;
    }

    @Override @NotNull @Guarded(ChainGuard.class) public var $(@NotNull String key, @Nullable Object value) {
        return this.getValue().$(key, value);
    }

    @Override @NotNull @Guarded(ChainGuard.class) public var $(@NotNull var key, @Nullable Object value) {
        return this.getValue().$(key, value);
    }

    @Override @Nullable public <R> R $() {return getValue().$();}

    @Override @NotNull @Guarded(ChainGuard.class) @Guarded(NotNullParametersGuard.class) public var $(
            @NotNull Pipeable lambda) {
        return getValue().$(lambda);
    }

    @Override @NotNull @Guarded(NotNullParametersGuard.class) public var $(@NotNull Number n) {return getValue().$(n);}

    @Override @NotNull @Guarded(ChainGuard.class) @Guarded(NotNullParametersGuard.class) public var $(
            @NotNull var rhs) {
        return getValue().$(rhs);
    }

    @Override @NotNull @Guarded(ChainGuard.class) public Stream<var> $children() {return getValue().$children();}

    @Override @NotNull @Guarded(ChainGuard.class) @Guarded(NotNullParametersGuard.class) public Stream<var> $children(
            @NotNull String key) {
        return getValue().$children(key);
    }

    @Override @NotNull @Guarded(ChainGuard.class) @Guarded(NotNullParametersGuard.class) public var $contains(
            @NotNull var value) {
        return this.getValue().$contains(value);
    }

    @Override @Guarded(ChainGuard.class) @NotNull @Guarded(NotNullParametersGuard.class) public var $containsValue(
            @NotNull var value) {
        return this.getValue().$containsValue(value);
    }

    @Override @NotNull @Guarded(ChainGuard.class) @Guarded(ReturnVarOnlyGuard.class) @Guarded(NotNullParametersGuard
            .class)
    public var $default(Object o) {return getValue().$default(o);}

    @Override @NotNull @Guarded(ChainGuard.class) @Guarded(NotNullParametersGuard.class) public var $get(
            @NotNull String key) {
        return getValue().$get(key);
    }

    @Override @NotNull public var $(@NotNull String key) {return getValue().$(key);}

    @Override @NotNull @Guarded(NotNullParametersGuard.class) @Guarded(ChainGuard.class) public var $get(
            @NotNull Object key) {
        return getValue().$get(key);
    }

    @Override @NotNull @Guarded(NotNullParametersGuard.class) public var $has(@NotNull String key) {
        return getValue().$has(key);
    }

    @Override @NotNull @Guarded(ChainGuard.class) public var $isEmpty() {return getValue().$isEmpty();}

    @Override @NotNull @Guarded(ChainGuard.class) public var $list() {return getValue().$list();}

    @Override @NotNull @Guarded(ChainGuard.class) public var $match(@NotNull String key, @Nullable String value) {
        return this.getValue().$match(key, value);
    }

    @Override @Nullable @Guarded(NotNullParametersGuard.class) public String S(@NotNull String key) {
        return getValue().S(key);
    }

    @Override @NotNull @Guarded(ChainGuard.class) public var $mimeType() {return getValue().$mimeType();}

    @Override @NotNull public var $rm(@NotNull String key) {return getValue().$rm(key);}

    @Override @NotNull public var $set(@NotNull String key, @Nullable Object value) {
        return this.getValue().$set(key, value);
    }

    @Override @NotNull @Guarded(ChainGuard.class) public var $size() {return getValue().$size();}

    @Override @NotNull public Stream<var> $stream(boolean parallel) {return getValue().$stream(parallel);}

    @Override @NotNull @Guarded(NotNullParametersGuard.class) @Guarded(ChainGuard.class) public var $void(
            @NotNull Callable<var> handler) {
        return getValue().$void(handler);
    }

    @Override public void clear() {getValue().clear();}

    @Override @NotNull @Guarded(ChainGuard.class) @Guarded(NotNullParametersGuard.class) public boolean containsKey(
            Object key) {
        return getValue().containsKey(key);
    }

    @Override @NotNull @Guarded(ChainGuard.class) @Guarded(NotNullCollectionGuard.class)
    public Set<Map.Entry<String, var>> entrySet() {return getValue().entrySet();}

    @Override @NotNull @Guarded(ChainGuard.class) public var err() {return getValue().err();}

    @Override @NotNull @Guarded(ChainGuard.class) public var out() {return getValue().out();}

    @Override @NotNull @Guarded(ChainGuard.class) public <R> R remove(Object value) {
        return this.getValue()
                   .remove(value);
    }

    @Override @NotNull @Guarded(ChainGuard.class) @Guarded(NotNullCollectionGuard.class)
    public Collection<var> values() {return getValue().values();}

    public var getValue() {
        for (int i = 0; i < value.size() - 1; i++) {
            value.get(i)._fixDeep(false);
        }
        return value.get(value.size() - 1);
    }

    @Override @NotNull @Guarded(NotNullGuard.class) public String $S() {return getValue().$S();}

    @Override public String S() {return getValue().S();}

    @Override public var $as(Type type) {return getValue().$as(type);}

    @Override @NotNull @Guarded(NotNullGuard.class) public Double D() {return getValue().D();}

    @Override @NotNull @Guarded(NotNullGuard.class) public Integer I() {return getValue().I();}

    @Override @NotNull @Guarded(NotNullGuard.class) public Integer I(@NotNull String key) {return getValue().I(key);}

    @Override @Guarded(NotNullGuard.class) @NotNull public Long L() {return getValue().L();}

    @Override @Guarded(NotNullGuard.class) @NotNull public Number N() {return getValue().N();}

    @Override @Guarded(NotNullGuard.class) public String getPairKey() {return getValue().getPairKey();}

    @Override @Guarded(NotNullGuard.class) @Nullable public Map<String, Object> toMap() {return getValue().toMap();}

    @Override @Guarded(NotNullGuard.class) public var getPairValue() {return getValue().getPairValue();}

    @Override @NotNull @Guarded(NotNullGuard.class) @Guarded(AllVarMapGuard.class)
    public ImmutableMap<String, var> $map() {return getValue().$map();}

    @Override @Guarded(NotNullGuard.class) public boolean is(@NotNull Type... types) {return getValue().is(types);}

    @Override public boolean isCollection() {return true;}

    @Override public boolean isDecimal() {return getValue().isDecimal();}

    @Override public boolean isInteger() {return getValue().isInteger();}

    @Override public boolean isLambda() {return getValue().isLambda();}

    @Override public boolean isList() {return getValue().isList();}

    @Override public boolean isMap() {return getValue().isMap();}

    @Override public boolean isNumber() {return getValue().isNumber();}

    @Override public boolean isPair() {return getValue().isPair();}

    @Override public boolean isSingleValue() {return getValue().isSingleValue();}

    @Override public boolean isString() {return getValue().isString();}

    @Override public boolean isUri() {return getValue().isUri();}

    @Override @Nullable public JsonObject json(@NotNull String key) {return getValue().json(key);}

    @Override @NotNull @Guarded(NotNullGuard.class) public JsonArray jsonArray() {return getValue().jsonArray();}

    @Override @Guarded(NotNullCollectionGuard.class) @Guarded(AllVarCollectionGuard.class) @NotNull
    public ImmutableList<var> toList() {return getValue().toList();}

    @Override public boolean isVoid() {return getValue().isVoid();}

    @Override @Nullable public JSONObject orgjson() {return getValue().orgjson();}

    @Override @Nullable public JsonObject json() {return getValue().json();}

    @Override @Nullable public ImmutableList<String> strings() {return getValue().strings();}

    @Override @Guarded(NotNullGuard.class) @NotNull public InputStream toStream() {return getValue().toStream();}

    @Override @NotNull @Guarded(NotNullGuard.class) @Guarded(ChainGuard.class)
    public var $abs() {return getValue().$abs();}

    @Override @Guarded(NotNullGuard.class) @Guarded(ChainGuard.class) @NotNull
    public var $dec() {return getValue().$dec();}

    @Override @NotNull @Guarded(NotNullParametersGuard.class) @Guarded(ChainGuard.class) public var $minus(
            @NotNull var value) {
        return this.getValue().$minus(value);
    }

    @Override @NotNull @Guarded(NotNullGuard.class) @Guarded(ChainGuard.class) public var $divide(
            @NotNull var v) {
        return getValue().$divide(v);
    }

    @Override @NotNull @Guarded(NotNullGuard.class) @Guarded(ChainGuard.class)
    public var $inc() {return getValue().$inc();}

    @Override @NotNull @Guarded(ChainGuard.class) public var $plus(@Nullable var value) {
        return this.getValue().$plus(value);
    }

    @Override @NotNull @Guarded(NotNullGuard.class) @Guarded(ChainGuard.class) public var $modulus(
            @NotNull var v) {
        return getValue().$modulus(v);
    }

    @Override @NotNull @Guarded(NotNullGuard.class) @Guarded(ChainGuard.class) public var $multiply(
            @NotNull var v) {
        return getValue().$multiply(v);
    }

    @Override @NotNull @Guarded(NotNullGuard.class) @Guarded(ChainGuard.class)
    public var $negate() {return getValue().$negate();}

    @Override @Guarded(ChainGuard.class) public var $all() {return getValue().$all();}

    @Override @Guarded(ChainGuard.class) @Guarded(NotNullParametersGuard.class) public var $dispatch(
            var lhs) {
        return getValue().$dispatch(lhs);
    }

    @Override @Guarded(NotNullParametersGuard.class) @Guarded(ChainGuard.class) public var $write(var value,
                                                                                                  boolean blocking,
                                                                                                  boolean mutating) {
        return this.getValue().$write(value, blocking, mutating);
    }

    @Override @Guarded(ChainGuard.class) public var $drain() {return getValue().$drain();}

    @Override @Guarded(ChainGuard.class) @Guarded(NotNullParametersGuard.class) public var $give(
            var lhs) {
        return getValue().$give(lhs);
    }

    @Override public var $listen(Pipeable pipeable) {return getValue().$listen(pipeable);}

    @Override @Guarded(NotNullParametersGuard.class) @Guarded(ChainGuard.class)
    public var $notify() {return getValue().$notify();}

    @Override @Guarded(ChainGuard.class) public var $peek() {return getValue().$peek();}

    @Override @Guarded(NotNullParametersGuard.class) @Guarded(ChainGuard.class) public var $read(boolean blocking,
                                                                                                 boolean mutating) {
        return getValue().$read(blocking, mutating);
    }

    @Override @Guarded(ChainGuard.class) public var $poll() {return getValue().$poll();}

    @Override @Guarded(ChainGuard.class) public var $pop() {return getValue().$pop();}

    @Override @Guarded(ChainGuard.class) @Guarded(NotNullParametersGuard.class) public var $publish(
            var lhs) {
        return getValue().$publish(lhs);
    }

    @Override @Guarded(ChainGuard.class) @Guarded(NotNullParametersGuard.class) public var $push(
            var lhs) {
        return getValue().$push(lhs);
    }

    @Override @Guarded(NotNullParametersGuard.class) @Guarded(ChainGuard.class)
    public var $read() {return getValue().$read();}

    @Override @Guarded(NotNullParametersGuard.class) @Guarded(ChainGuard.class) public var $write(
            var value) {
        return this.getValue().$write(value);
    }

    @Override @Guarded(ChainGuard.class) @Guarded(NotNullParametersGuard.class) public var $subscribe(
            Pipeable subscription) {
        return getValue().$subscribe(subscription);
    }

    @Override public var $listen(Pipeable pipeable, String id) {return getValue().$listen(pipeable, id);}

    @Override @Guarded(ChainGuard.class) @Guarded(NotNullParametersGuard.class) public var $subscribe(
            Pipeable subscription, String key) {
        return getValue().$subscribe(subscription, key);
    }

    @Override @Guarded(NotNullGuard.class) @Guarded(ChainGuard.class) public var $choose(var map) {
        return getValue().$choose(map);
    }

    @Override @Guarded(NotNullGuard.class) @Guarded(ChainGuard.class) public var $each(
            Pipeable pipe) {
        return getValue().$each(pipe);
    }

    @Override @NotNull @Guarded(ChainGuard.class) public var $copy() {return getValue().$copy();}

    @Override @NotNull @Guarded(ChainGuard.class) public var _fix(boolean parallel) {return _fix(1, parallel);}

    @Override public var _fix(int depth, boolean parallel) {
        if (depth <= 1) {
            return this;
        } else {
            return getValue()._fix(depth - 1, parallel);
        }
    }

    @Override @Guarded(ChainGuard.class) public var _fixDeep(boolean parallel) {
        return _fix(Integer.MAX_VALUE, parallel);
    }

    @Override @Guarded(NotNullGuard.class) public void _src(String src) {getValue()._src(src);}

    @Override @Guarded(NotNullGuard.class) public String _src() {return getValue()._src();}

    @Override @Guarded(ChainGuard.class) @NotNull public var _unwrap() {return getValue()._unwrap();}

    @Override @Guarded(ChainGuard.class) @Guarded(NotNullGuard.class) @Guarded(NotNullCollectionGuard.class) @NotNull
    public var copy(@NotNull ImmutableList<Throwable> errors) {return getValue().copy(errors);}

    @Override @NotNull @Guarded(NotNullGuard.class) public var $create() {return getValue().$create();}

    @Override @NotNull @Guarded(NotNullGuard.class) public var $destroy() {return getValue().$destroy();}

    @Override @NotNull @Guarded(NotNullGuard.class) public var $pause() {return getValue().$pause();}

    @Override @Guarded(NotNullGuard.class) public void $signal(@NotNull Signal signal) {getValue().$signal(signal);}

    @Override @NotNull @Guarded(NotNullGuard.class) public var $start() {return getValue().$start();}

    @Override @NotNull @Guarded(NotNullGuard.class) public var $state() {return getValue().$state();}

    @Override @NotNull @Guarded(NotNullGuard.class) public var $stop() {return getValue().$stop();}

    @Override @NotNull @Guarded(NotNullGuard.class) public var $unpause() {return getValue().$unpause();}

    @Override @NotNull @Guarded(NotNullGuard.class)
    public StateMachine<ResourceState, Signal> getStateMachine() {return getValue().getStateMachine();}

    @Override @NotNull @Guarded(NotNullGuard.class) @Guarded(ChainGuard.class) @Deprecated public var $dec(
            @NotNull String key, @NotNull var amount) {
        return getValue().$dec(key, amount);
    }

    @Override @NotNull @Guarded(NotNullGuard.class) @Guarded(ChainGuard.class) @Deprecated public var $dec(
            @NotNull var key, @NotNull var amount) {
        return getValue().$dec(key, amount);
    }

    @Override @NotNull @Guarded(NotNullGuard.class) @Guarded(ChainGuard.class) @Deprecated public var $dec(
            @NotNull String key, @NotNull Number amount) {
        return getValue().$dec(key, amount);
    }

    @Override @NotNull @Guarded(NotNullGuard.class) @Guarded(ChainGuard.class) @Deprecated public var $dec(
            @NotNull Number amount) {
        return getValue().$dec(amount);
    }

    @Override @NotNull @Guarded(NotNullGuard.class) @Guarded(ChainGuard.class) @Deprecated public var $dec(
            @NotNull var amount) {
        return getValue().$dec(amount);
    }

    @Override @Guarded(NotNullGuard.class) @Guarded(ChainGuard.class) @NotNull @Deprecated public var $inc(
            @NotNull String key, @NotNull var amount) {
        return getValue().$inc(key, amount);
    }

    @Override @NotNull @Guarded(NotNullGuard.class) @Guarded(ChainGuard.class) @Deprecated public var $inc(
            @NotNull var key, @NotNull var amount) {
        return getValue().$inc(key, amount);
    }

    @Override @NotNull @Guarded(NotNullGuard.class) @Guarded(ChainGuard.class) @Deprecated public var $inc(
            @NotNull String key, @NotNull Number amount) {
        return getValue().$inc(key, amount);
    }

    @Override @NotNull @Guarded(NotNullGuard.class) @Guarded(ChainGuard.class) @Deprecated public var $inc(
            @NotNull Number amount) {
        return getValue().$inc(amount);
    }

    @Override @NotNull @Guarded(NotNullGuard.class) @Deprecated @Guarded(ChainGuard.class) public var $inc(
            @NotNull var amount) {
        return getValue().$inc(amount);
    }

    @Override @NotNull @Deprecated @Guarded(NotNullGuard.class) @Guarded(ChainGuard.class) public var $pipe(
            @NotNull String classModule) {
        return getValue().$pipe(classModule);
    }

    @Override @Deprecated @Guarded(NotNullGuard.class) @Guarded(ChainGuard.class) public var eval(String label,
                                                                                                  DollarEval eval) {
        return getValue().eval(label, eval);
    }

    @Override @Deprecated @Guarded(NotNullGuard.class) @Guarded(ChainGuard.class) public var eval(
            DollarEval eval) {
        return getValue().eval(eval);
    }

    @Override @Deprecated @Guarded(NotNullGuard.class) @Guarded(ChainGuard.class) public var eval(
            Class clazz) {
        return getValue().eval(clazz);
    }

    @Override @NotNull @Deprecated @Guarded(NotNullGuard.class)
    public Stream<String> keyStream() {return getValue().keyStream();}

    @Override @NotNull @Guarded(NotNullGuard.class) @Guarded(NotNullCollectionGuard.class) @Deprecated
    public Stream<Map.Entry<String, var>> kvStream() {return getValue().kvStream();}

    @Override @Nullable @Deprecated public Number number(@NotNull String key) {return getValue().number(key);}

    @Override @NotNull @Guarded(NotNullParametersGuard.class) @Guarded(ChainGuard.class) public var $error(
            @NotNull String errorMessage) {
        return getValue().$error(errorMessage);
    }

    @Override @NotNull @Guarded(NotNullParametersGuard.class) @Guarded(ChainGuard.class) public var $error(
            @NotNull Throwable error) {
        return getValue().$error(error);
    }

    @Override @NotNull @Guarded(ChainGuard.class) public var $error() {return getValue().$error();}

    @Override @NotNull @Guarded(NotNullParametersGuard.class) @Guarded(ChainGuard.class)
    public var $errors() {return getValue().$errors();}

    @Override @NotNull @Guarded(NotNullParametersGuard.class) @Guarded(ChainGuard.class) public var $fail(
            @NotNull Consumer<List<Throwable>> handler) {
        return getValue().$fail(handler);
    }

    @Override @NotNull @Guarded(NotNullParametersGuard.class) @Guarded(ChainGuard.class) public var $invalid(
            @NotNull String errorMessage) {
        return getValue().$invalid(errorMessage);
    }

    @Override @NotNull @Guarded(NotNullParametersGuard.class) @Guarded(ChainGuard.class) public var $error(
            @NotNull String errorMessage, @NotNull ErrorType type) {
        return getValue().$error(errorMessage, type);
    }

    @Override @NotNull @Guarded(ChainGuard.class) public var clearErrors() {return getValue().clearErrors();}

    @Override @NotNull @Guarded(NotNullCollectionGuard.class)
    public List<String> errorTexts() {return getValue().errorTexts();}

    @Override @NotNull @Guarded(NotNullCollectionGuard.class)
    public ImmutableList<Throwable> errors() {return getValue().errors();}

    @Override public boolean hasErrors() {return getValue().hasErrors();}

    @Override @NotNull @Guarded(NotNullGuard.class) @Guarded(ChainGuard.class) public var $eval(
            @NotNull String js) {
        return getValue().$eval(js);
    }

    @Override @NotNull @Guarded(NotNullGuard.class) @Guarded(ChainGuard.class) public var $pipe(
            @NotNull Pipeable pipe) {
        return getValue().$pipe(pipe);
    }

    @Override @NotNull @Guarded(NotNullGuard.class) @Guarded(ChainGuard.class) public var $pipe(@NotNull String label,
                                                                                                @NotNull Pipeable
                                                                                                        pipe) {
        return getValue().$pipe(label, pipe);
    }

    @Override @NotNull @Guarded(NotNullGuard.class) @Guarded(ChainGuard.class) public var $pipe(@NotNull String label,
                                                                                                @NotNull String js) {
        return getValue().$pipe(label, js);
    }

    @Override @NotNull @Guarded(NotNullGuard.class) @Guarded(ChainGuard.class) public var $pipe(
            @NotNull Class<? extends Pipeable> clazz) {
        return getValue().$pipe(clazz);
    }

    @Override public var assertNotVoid(String message) throws AssertionError {return getValue().assertNotVoid(message);}

    @Override public var assertTrue(Function<var, Boolean> assertion, String message) throws AssertionError {
        return getValue().assertTrue(assertion, message);
    }

    @Override public var assertFalse(Function<var, Boolean> assertion, String message) throws AssertionError {
        return getValue().assertFalse(assertion, message);
    }

    @Override public int compareTo(var o) {return getValue().compareTo(o);}

    @Override @Guarded(ChainGuard.class) @Guarded(NotNullGuard.class) public var debugf(String message,
                                                                                        Object... values) {
        return getValue().debugf(message, values);
    }

    @Override @Guarded(ChainGuard.class) @Guarded(NotNullGuard.class) public var debug(
            Object message) {
        return getValue().debug(message);
    }

    @Override @Guarded(ChainGuard.class) public var debug() {return getValue().debug();}

    @Override @Guarded(ChainGuard.class) @Guarded(NotNullGuard.class) public var infof(String message,
                                                                                       Object... values) {
        return getValue().infof(message, values);
    }

    @Override @Guarded(ChainGuard.class) @Guarded(NotNullGuard.class) public var info(
            Object message) {
        return getValue().info(message);
    }

    @Override @Guarded(ChainGuard.class) public var info() {return getValue().info();}

    @Override @Guarded(ChainGuard.class) @Guarded(NotNullGuard.class) public var errorf(String message,
                                                                                        Object... values) {
        return getValue().errorf(message, values);
    }

    @Override @Guarded(ChainGuard.class) @Guarded(NotNullGuard.class) public var error(
            Throwable exception) {
        return getValue().error(exception);
    }

    @Override @Guarded(ChainGuard.class) @Guarded(NotNullGuard.class) public var error(
            Object message) {
        return getValue().error(message);
    }

    @Override @Guarded(ChainGuard.class) public var error() {return getValue().error();}

    @Override public boolean isBoolean() {return getValue().isBoolean();}

    @Override public boolean isTrue() {return getValue().isTrue();}

    @Override public boolean isTruthy() {return getValue().isTruthy();}

    @Override public boolean isFalse() {return getValue().isFalse();}

    @Override public boolean isNeitherTrueNorFalse() {return getValue().isNeitherTrueNorFalse();}

    @Override public void setMetaAttribute(String key, String value) {this.getValue().setMetaAttribute(key, value);}

    @Override public String getMetaAttribute(String key) {return getValue().getMetaAttribute(key);}

    @NotNull @Guarded(NotNullGuard.class) @Override public String toString() {return getValue().toString();}
}
