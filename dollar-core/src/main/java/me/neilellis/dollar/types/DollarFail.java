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

import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

/**
 * To better understand the rationale behind this class, take a look at http://homepages.ecs.vuw.ac.nz/~tk/publications/papers/void.pdf
 *
 * Dollar does not have the concept of null. Instead null {@link me.neilellis.dollar.var} objects are instances of this class.
 *
 * Void is equivalent to 0,"",null except that unlike these values it has behavior that corresponds to a void object.
 *
 * Therefore actions taken against a void object are ignored. Any method that returns a {@link me.neilellis.dollar.var} will return a {@link DollarFail}.
 *
 * <pre>
 *
 *  var nulled= $void();
 *  nulled.$pipe((i)-&gt;{System.out.println("You'll never see this."});
 *
 * </pre>
 *
 * @author <a href="http://uk.linkedin.com/in/neilellis">Neil Ellis</a>
 */
public class DollarFail extends DollarVoid {

    public enum FailureType {
        INVALID_LIST_OPERATION, INVALID_MAP_OPERATION, INVALID_SINGLE_VALUE_OPERATION, EXCEPTION,
        INVALID_RANGE_OPERATION, INVALID_BOOLEAN_VALUE_OPERATION, ASSERTION, INVALID_STRING_OPERATION,
        INVALID_URI_OPERATION,

        INVALID_OPERATION, EVALUATION_FAILURE, METADATA_IMMUTABLE, INVALID_CAST, EXECUTION_FAILURE, INTERRUPTED,
        VOID_FAILURE
    }
    private FailureType failureType;

    public DollarFail(@NotNull List<Throwable> errors, FailureType failureType) {
        super(errors);
        this.failureType = failureType;
    }


    public DollarFail(FailureType failureType) {

        super(Collections.emptyList());
        this.failureType = failureType;
    }
}
