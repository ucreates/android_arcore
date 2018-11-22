// ======================================================================
// Project Name    : android_foundation
//
// Copyright © 2018 U-CREATES. All rights reserved.
//
// This source code is the property of U-CREATES.
// If such findings are accepted at any time.
// We hope the tips and helpful in developing.
// ======================================================================
package com.frontend.behaviour
import android.content.Context
import com.core.entity.component.Parameter
abstract class BaseBehaviour(context: Context) {
    public var id: Int = 0
        get
        set
    public var destroy: Boolean = false
        get
        set
    public var intersect: Boolean = false
        get
        set
    protected var context: Context = context
        get
        set
    open fun onCreate() {
        return
    }
    open fun onCreate(parameter: Parameter) {
        return
    }
    open fun onUpdate(delta: Float) {
        return
    }
    open fun onRender() {
        return
    }
}