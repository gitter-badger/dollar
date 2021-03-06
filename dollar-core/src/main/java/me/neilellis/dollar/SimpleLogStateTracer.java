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

package me.neilellis.dollar;

import me.neilellis.dollar.types.DollarList;
import me.neilellis.dollar.types.DollarMap;
import me.neilellis.dollar.types.DollarVoid;

/**
 * Created by neil on 10/15/14.
 */
public class SimpleLogStateTracer implements StateTracer {
    @Override
    public <R> R trace(Object before, R after, Operations operationType, Object... values) {
        String beforeStr = "";
        String afterStr = "";
        String afterNotes = "";
        String beforeNotes = "";
        if (after instanceof var) {
            if (((var) after).hasErrors()) {
                afterNotes += "*ERROR*";
            }
            afterStr = format(after);
        } else {
            if (after != null) {
                afterStr = String.format("%s(%s)", after.toString(), after.getClass().getName());
            }
        }
        if (before instanceof var) {
            if (((var) before).hasErrors()) {
                beforeNotes += "*ERROR*";
            }
            beforeStr = format(before);
        } else {
            if (before != null) {
                beforeStr = String.format("%s(%s)", before.toString(), before.getClass().getName());
            }
        }
        if ((before instanceof DollarVoid || before == null) && (after instanceof DollarVoid || after == null)) {
            DollarStatic.log(String.format("%s%s: %s->%s",
                    operationType,
                    toDescription(values),
                    beforeNotes,
                    afterNotes));
        } else if (before instanceof DollarVoid || before == null) {
            DollarStatic.log(String.format("%s%s: %s%s", operationType, toDescription(values), afterStr, afterNotes));
        } else {
            DollarStatic.log(String.format("%s%s: %s%s -> %s%s",
                    operationType,
                    toDescription(values),
                    beforeStr,
                    beforeNotes,
                    afterStr,
                    afterNotes));
        }
        return after;
    }

    private String format(Object value) {
        Object unwrapped;
        String formatted;
        if (value instanceof var) {
            unwrapped = ((var) value)._unwrap();
        } else {
            unwrapped = value;
        }
        if (value instanceof var &&
            (((var) unwrapped).isLambda() || unwrapped instanceof DollarList || unwrapped instanceof DollarMap)) {
            formatted = "<" + unwrapped.getClass().getSimpleName() + ">";
        } else {
            formatted = String.valueOf(value);
        }
        return formatted;
    }

    private String toDescription(Object[] values) {
        String result = "[";
        for (Object value : values) {
            Object unwrapped;
            String formatted;
            formatted = format(value);
            result += formatted + ", ";
        }
        result += "]";
        return result;
    }
}
