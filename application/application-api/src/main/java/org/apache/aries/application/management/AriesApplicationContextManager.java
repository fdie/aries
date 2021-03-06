/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIESOR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.aries.application.management;

import java.util.Set;

import org.osgi.framework.BundleException;

/**
 * An ApplicationContextManager is responsible for managing Aries applications in the 
 * server's OSGi runtime. We expect that many projects consuming this code will provide
 * their own implementation of this service. 
 */
public interface AriesApplicationContextManager {

  /**
   * Obtain an ApplicationContext for an AriesApplication. Applications are stopped and
   * started via an ApplicationContext. 
   * @param app The applicaton for which to obtain an ApplicationContext. 
   * @return AriesApplicationContext
   * @throws BundleException 
   * @throws ManagementException 
   */
  public AriesApplicationContext getApplicationContext(AriesApplication app) throws BundleException, ManagementException;

  /**
   * @return The set of all AriesApplicationContexts.
   */
  public Set<AriesApplicationContext> getApplicationContexts();

  /**
   * Remove the provided AriesApplicationContext from the running system.
   * 
   * @param app the application to remove.
   */
  public void remove(AriesApplicationContext app) throws BundleException;
}
