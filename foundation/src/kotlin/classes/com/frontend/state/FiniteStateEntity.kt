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
open class FiniteStateEntity<T> {
    public var isNewState: Boolean = false
        get
        set
    public var state: FiniteState<T>? = null
        get
        set
    public var currentStateName: String = ""
        get
        set
    public var previousStateName: String = ""
        get
        set
    open fun update(stateName: String, nextState: FiniteState<T>) {
        if (this.currentStateName != stateName) {
            this.previousStateName = this.currentStateName
            this.currentStateName = stateName
            this.state = nextState
        }
        this.isNewState = true
        return
    }
}