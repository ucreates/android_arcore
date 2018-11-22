// ======================================================================
// Project Name    : android_foundation
//
// Copyright © 2018 U-CREATES. All rights reserved.
//
// This source code is the property of U-CREATES.
// If such findings are accepted at any time.
// We hope the tips and helpful in developing.
// ======================================================================
package com.core.entity.component
@Suppress("UNCHECKED_CAST")
open class Parameter {
    private var parameterHashMap: HashMap<String, Any> = HashMap<String, Any>()
        get
        set
    open fun <T> get(parameterName: String): T? {
        if (false != this.parameterHashMap.containsKey(parameterName)) {
            var ret: Any? = this.parameterHashMap.get(parameterName)
            return ret as T?
        }
        return null
    }
    open fun <T> set(parameterName: String, value: T) {
        if (false != this.parameterHashMap.containsKey(parameterName)) {
            return
        }
        this.parameterHashMap.put(parameterName, value as Any)
        return
    }
}
