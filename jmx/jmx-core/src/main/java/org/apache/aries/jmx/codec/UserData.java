/**
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.apache.aries.jmx.codec;

import java.util.HashMap;
import java.util.Map;

import javax.management.openmbean.CompositeData;
import javax.management.openmbean.CompositeDataSupport;
import javax.management.openmbean.OpenDataException;

import org.osgi.jmx.service.useradmin.UserAdminMBean;
import org.osgi.service.useradmin.User;

/**
 * <p>
 * <tt>UserData</tt> represents User Type @see {@link UserAdminMBean#USER_TYPE}.It is a codec
 * for the <code>CompositeData</code> representing a User.
 * </p>
 * @see RoleData
 *
 * @version $Rev$ $Date$
 */
public class UserData extends RoleData {

    /**
     * Constructs new UserData.
     * 
     * @param name user name.
     * @param type role type.
     */
    public UserData(String name, int type){
        super(name, type);
    }
    
    /**
     * Constructs new UserData from {@link User} object.
     * 
     * @param user {@link User} instance.
     */
    public UserData(User user){
        this(user.getName(), user.getType());
    }
    
    /**
     * Translates UserData to CompositeData represented by
     * compositeType {@link UserAdminMBean#USER_TYPE}.
     * 
     * @return translated UserData to compositeData.
     */
    public CompositeData toCompositeData() {
        try {
            Map<String, Object> items = new HashMap<String, Object>();
            items.put(UserAdminMBean.NAME, name);
            items.put(UserAdminMBean.TYPE, type);
            return new CompositeDataSupport(UserAdminMBean.USER_TYPE, items);
        } catch (OpenDataException e) {
            throw new IllegalStateException("Can't create CompositeData" + e);
        }
    }

    /**
     * Static factory method to create UserData from CompositeData object.
     * 
     * @param data {@link CompositeData} instance.
     * @return UserData instance.
     */
    public static UserData from(CompositeData data) {
        if(data == null){
            return null;
        }
        String name = (String) data.get(UserAdminMBean.NAME);
        int type = (Integer)data.get(UserAdminMBean.TYPE);
        return new UserData(name, type);
    }
}
