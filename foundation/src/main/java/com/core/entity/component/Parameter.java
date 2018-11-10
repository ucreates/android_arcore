// ======================================================================
// Project Name    : android_foundation
//
// Copyright © 2018 U-CREATES. All rights reserved.
//
// This source code is the property of U-CREATES.
// If such findings are accepted at any time.
// We hope the tips and helpful in developing.
// ======================================================================
package com.core.entity.component;
import java.util.HashMap;
import java.lang.Object;
public class Parameter {
    private HashMap<String, Object> parameterHashMap = new HashMap<String, Object>();
    public <T> T get(String parameterName) {
        if (false != this.parameterHashMap.containsKey(parameterName)) {
            Object ret = this.parameterHashMap.get(parameterName);
            return (T)ret;
        }
        return null;
    }
    public <T> void set(String parameterName,  T value) {
        if (false != this.parameterHashMap.containsKey(parameterName)) {
            return;
        }
        this.parameterHashMap.put(parameterName, value);
        return;
    }
}
