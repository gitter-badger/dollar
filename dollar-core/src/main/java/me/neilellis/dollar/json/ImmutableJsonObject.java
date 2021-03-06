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

package me.neilellis.dollar.json;

import java.util.Map;
import java.util.Set;

/**
 * @author <a href="http://uk.linkedin.com/in/neilellis">Neil Ellis</a>
 */
public class ImmutableJsonObject {

  private final JsonObject json;

  public ImmutableJsonObject(JsonObject json) {
    this.json = json;
  }

  public JsonArray asArray() {
    return json.asArray().copy();
  }

  public JsonObject asObject() {
    return json.copy().asObject();
  }

  public boolean containsField(String fieldName) {
    return json.containsField(fieldName);
  }

  public String encode() {
    return json.encode();
  }

  public String encodePrettily() {
    return json.encodePrettily();
  }

  public JsonArray getArray(String fieldName, JsonArray def) {
    return json.getArray(fieldName, def);
  }

  public JsonArray getArray(String fieldName) {
    return json.getArray(fieldName);
  }

  public byte[] getBinary(String fieldName) {
    return json.getBinary(fieldName);
  }

  public byte[] getBinary(String fieldName, byte[] def) {
    return json.getBinary(fieldName, def);
  }

  public Boolean getBoolean(String fieldName) {
    return json.getBoolean(fieldName);
  }

  public boolean getBoolean(String fieldName, boolean def) {
    return json.getBoolean(fieldName, def);
  }

  public JsonElement getElement(String fieldName) {
    return json.getElement(fieldName);
  }

  public JsonElement getElement(String fieldName, JsonElement def) {
    return json.getElement(fieldName, def);
  }

  public <T> T getField(String fieldName) {
    return json.getField(fieldName);
  }

  public Set<String> getFieldNames() {
    return json.getFieldNames();
  }

  public Integer getInteger(String fieldName) {
    return json.getInteger(fieldName);
  }

  public Integer getInteger(String fieldName, int def) {
    return json.getInteger(fieldName, def);
  }

  public Long getLong(String fieldName) {
    return json.getLong(fieldName);
  }

  public Long getLong(String fieldName, long def) {
    return json.getLong(fieldName, def);
  }

  public Number getNumber(String fieldName, int def) {
    return json.getNumber(fieldName, def);
  }

  public Number getNumber(String fieldName) {
    return json.getNumber(fieldName);
  }

  public JsonObject getObject(String fieldName, JsonObject def) {
    return json.getObject(fieldName, def);
  }

  public JsonObject getObject(String fieldName) {
    return json.getObject(fieldName);
  }

  public String getString(String fieldName) {
    return json.getString(fieldName);
  }

  public String getString(String fieldName, String def) {
    return json.getString(fieldName, def);
  }

  public <T> T getValue(String fieldName) {
    return json.getValue(fieldName);
  }

  @Override
  public int hashCode() {
    return json.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof JsonObject) {
      return json.equals(obj);
    }
    if (obj instanceof ImmutableJsonObject) {
      return ((ImmutableJsonObject) obj).json.equals(((ImmutableJsonObject) obj).json);
    }
    return false;
  }

  @Override
  public String toString() {
    return json.toString();
  }

  public boolean isArray() {
    return json.isArray();
  }

  public boolean isObject() {
    return json.isObject();
  }

  public JsonObject mutable() {
    return json.copy();
  }

  public JsonObject putNumber(String fieldName, Number value) {
    return json.putNumber(fieldName, value);
  }

  public int size() {
    return json.size();
  }

  public Map<String, Object> toMap() {
    return json.toMap();
  }
}
