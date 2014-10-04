/*
 * Copyright (c) 2014-2014 Cazcade Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.cazcade.dollar;

/**
 * @author <a href="http://uk.linkedin.com/in/neilellis">Neil Ellis</a>
 */
public class DollarNumber extends AbstractDollarSingleValue<Number> {

    public DollarNumber(Number value) {
        super(value);
    }


    @Override
    public Integer $int() {
        return value.intValue();
    }

    @Override
    public Number $number(String key) {
        return value;
    }

    @Override
    public $ copy() {
        return new DollarNumber(value);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DollarNumber) {
            return $().equals(((DollarNumber) obj).$());
        } else {
            return value.toString().equals(obj.toString());
        }
    }

    @Override
    public Number $() {
        return value;
    }


}