/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.predictionio.sdk.java;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.Map;
import org.joda.time.DateTime;

/**
 * Event class for PredictionIO Event objects.
 *
 * @version 0.8.3
 * @since 0.8.0
 */

public class Event {

  // mandatory fields
  private String event;
  private String entityType;
  private String entityId;

  // optional fields
  private String targetEntityType;
  private String targetEntityId;
  private Map<String, Object> properties = Maps.newHashMap();
  private DateTime eventTime;

  /**
   * Instantiate an event object.
   */
  public Event() {
  }

  /**
   * Returns the name of the event.
   */
  public String getEvent() {
    return event;
  }

  /**
   * Returns the entity type. entityType-entityId forms the unique identifier of the entity.
   */
  public String getEntityType() {
    return entityType;
  }

  /**
   * Returns the entity id. entityType-entityId forms the unique identifier of the entity.
   */
  public String getEntityId() {
    return entityId;
  }

  /**
   * Returns the target entity type, or null if the field is not set.
   */
  public String getTargetEntityType() {
    return targetEntityType;
  }

  /**
   * Returns the target entity id, or null if the field is not set.
   */
  public String getTargetEntityId() {
    return targetEntityId;
  }

  /**
   * Returns the set of properties as a map.
   */
  public Map<String, Object> getProperties() {
    return properties;
  }

  /**
   * Returns the event time, or null if the field is not set.
   */
  public DateTime getEventTime() {
    return eventTime;
  }

  // builder methods for convenience

  /**
   * Sets the name of the event.
   */
  public Event event(String event) {
    this.event = event;
    return this;
  }

  /**
   * Sets the entity type. entityType-entityId forms the unique identifier of the entity.
   */
  public Event entityType(String entityType) {
    this.entityType = entityType;
    return this;
  }

  /**
   * Sets the entity id. entityType-entityId forms the unique identifier of the entity.
   */
  public Event entityId(String entityId) {
    this.entityId = entityId;
    return this;
  }

  public Event targetEntityType(String targetEntityType) {
    this.targetEntityType = targetEntityType;
    return this;
  }

  public Event targetEntityId(String targetEntityId) {
    this.targetEntityId = targetEntityId;
    return this;
  }

  public Event property(String key, Object value) {
    this.properties.put(key, value);
    return this;
  }

  public Event properties(Map<String, Object> properties) {
    this.properties.putAll(properties);
    return this;
  }

  public Event eventTime(DateTime eventTime) {
    this.eventTime = eventTime;
    return this;
  }

  // toJsonString and toString methods

  public String toJsonString() {
    return toString();
  }

  @Override
  public String toString() {
    // handle DateTime separately
    GsonBuilder gsonBuilder = new GsonBuilder();
    gsonBuilder.registerTypeAdapter(DateTime.class, new DateTimeAdapter());
    Gson gson = gsonBuilder.create();
    return gson.toJson(this); // works when there are no generic types
  }
}
