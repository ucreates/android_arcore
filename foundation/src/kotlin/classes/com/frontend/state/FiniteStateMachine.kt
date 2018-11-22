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
open class FiniteStateMachine<T>(owner: T) {
    public var finiteStateEntity: FiniteStateEntity<T> = FiniteStateEntity<T>()
    public var stateList: HashMap<String, FiniteState<T>> = HashMap<String, FiniteState<T>>()
    public var persistentStateList: HashMap<String, FiniteState<T>> = HashMap<String, FiniteState<T>>()
    public var parameter: Parameter? = null
        get
        set
    public var owner: T = owner
        get
        set
    public var enableFrameSkip: Boolean = false
        get
        set
    public var frameSkipCounter: Int = 0
        get
        set
    open fun change(stateName: String) {
        this.change(stateName, null)
        return
    }
    open fun change(stateName: String, parameter: Parameter?) {
        this.parameter = parameter
        var nextState: FiniteState<T>? = this.get(stateName)
        if (null == nextState) {
            return
        }
        this.finiteStateEntity.update(stateName, nextState)
        return
    }
    open fun update(delta: Float) {
        if (null != this.finiteStateEntity.state && false == this.finiteStateEntity.state?.complete) {
            if (false != this.finiteStateEntity.isNewState) {
                if (null != this.parameter) {
                    this.finiteStateEntity.state?.create(this.parameter)
                } else {
                    this.finiteStateEntity.state?.create()
                }
                this.finiteStateEntity.isNewState = false
            }
            if (null != this.finiteStateEntity.state && false == this.finiteStateEntity.state?.complete && false == this.finiteStateEntity.state?.wait) {
                this.finiteStateEntity.state?.update(delta)
            }
        }
        this.persistentStateList.forEach { key, state ->
            state.update(delta)
        }
        return
    }
    open fun play() {
        this.stateList.forEach { key, state ->
            state.wait = false
            state.complete = false
        }
        this.persistentStateList.forEach { key, state ->
            state.wait = false
            state.complete = false
        }
        return
    }
    open fun pause() {
        this.stateList.forEach { key, state ->
            state.wait = true
        }
        return
    }
    open fun stop() {
        this.stateList.forEach { key, state ->
            state.wait = true
            state.complete = true
        }
        this.persistentStateList.forEach { key, state ->
            state.wait = true
            state.complete = true
        }
        return
    }
    open fun add(stateName: String, state: FiniteState<T>): Boolean {
        if (false == state.persistent) {
            if (false == this.stateList.containsKey(stateName)) {
                state.owner = this.owner
                state.stateMachine = this
                this.stateList.put(stateName, state)
                return true
            }
        } else {
            if (false == this.persistentStateList.containsKey(stateName)) {
                state.owner = this.owner
                state.stateMachine = this
                this.persistentStateList.put(stateName, state)
                return true
            }
        }
        return false
    }
    private fun get(newStateName: String): FiniteState<T>? {
        var ret: FiniteState<T>? = null
        if (false != this.stateList.containsKey(newStateName)) {
            ret = this.stateList.get(newStateName)
        } else if (false != this.persistentStateList.containsKey(newStateName)) {
            ret = this.persistentStateList.get(newStateName)
        }
        return ret
    }
}