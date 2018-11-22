// ======================================================================
// Project Name    : android_foundation
//
// Copyright © 2018 U-CREATES. All rights reserved.
//
// This source code is the property of U-CREATES.
// If such findings are accepted at any time.
// We hope the tips and helpful in developing.
// ======================================================================
package com.frontend.state
import com.core.entity.component.Parameter
import com.frontend.component.vfx.TimeLine
open class FiniteState<T> {
    public var owner: T? = null
        get
        set
    public var stateMachine: FiniteStateMachine<T>? = null
        get
        set
    public var timeLine: TimeLine = TimeLine()
        get
        set
    public var persistent: Boolean = false
        get
        set
    public var complete: Boolean = false
        get
        set
    public var wait: Boolean = false
        get
        set
    public var initialize: Boolean = false
        get
        set
    public var emitCallback: Boolean = false
        get
        set
    public var dump: Boolean = false
        get
        set
    open fun create() {
        return
    }
    open fun create(parameter: Parameter?) {
        return
    }
    open fun update(delta: Float) {
        return
    }
}