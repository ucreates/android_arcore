// ======================================================================
// Project Name    : android_foundation
//
// Copyright © 2018 U-CREATES. All rights reserved.
//
// This source code is the property of U-CREATES.
// If such findings are accepted at any time.
// We hope the tips and helpful in developing.
// ======================================================================
package com.frontend.state;
import java.util.HashMap;
import com.core.entity.component.Parameter;
public class FiniteStateMachine<T> {
    public FiniteStateEntity<T> finiteStateEntity = new FiniteStateEntity<T>();
    public HashMap<String, FiniteState<T>> stateList = new HashMap<String, FiniteState<T>>();
    public HashMap<String, FiniteState<T>> persistentStateList = new HashMap<String, FiniteState<T>>();
    public Parameter paramter;
    public T owner;
    public boolean enableFrameSkip;
    public int frameSkipCounter;
    public FiniteStateMachine(T owner) {
        this.paramter = null;
        this.owner = owner;
        this.enableFrameSkip = false;
        this.frameSkipCounter = 0;
    }
    public void change(String stateName) {
        this.change(stateName, null);
        return;
    }
    public void change(String stateName, Parameter parameter) {
        this.paramter = parameter;
        FiniteState<T> nextState = this.get(stateName);
        if (null == nextState) {
            return;
        }
        this.finiteStateEntity.update(stateName, nextState);
        return;
    }
    public void update(double delta) {
        if (null != this.finiteStateEntity.state && false == this.finiteStateEntity.state.complete) {
            if (false != this.finiteStateEntity.isNewState) {
                if (null != this.paramter) {
                    this.finiteStateEntity.state.create(this.paramter);
                } else {
                    this.finiteStateEntity.state.create();
                }
                this.finiteStateEntity.isNewState = false;
            }
            if (null != this.finiteStateEntity.state && false == this.finiteStateEntity.state.complete && false == this.finiteStateEntity.state.wait) {
                this.finiteStateEntity.state.update(delta);
            }
        }
        for (String stateName : this.persistentStateList.keySet()) {
            FiniteState<T> state = this.persistentStateList.get(stateName);
            state.update(delta);
        }
        return;
    }
    public void play() {
        for (String stateName : this.stateList.keySet()) {
            FiniteState<T> state = this.stateList.get(stateName);
            state.wait = false;
            state.complete = false;
        }
        for (String stateName : this.persistentStateList.keySet()) {
            FiniteState<T> state = this.persistentStateList.get(stateName);
            state.wait = false;
            state.complete = false;
        }
        return;
    }
    public void pause() {
        for (String stateName : this.stateList.keySet()) {
            FiniteState<T> state = this.stateList.get(stateName);
            state.wait = true;
        }
        return;
    }
    public void stop() {
        for (String stateName : this.stateList.keySet()) {
            FiniteState<T> state = this.stateList.get(stateName);
            state.complete = true;
        }
        for (String stateName : this.persistentStateList.keySet()) {
            FiniteState<T> state = this.persistentStateList.get(stateName);
            state.complete = true;
        }
        return;
    }
    public boolean add(String stateName, FiniteState<T> state) {
        if (false == state.persistent)  {
            if (false  == this.stateList.containsKey(stateName)) {
                state.owner = this.owner;
                state.stateMachine = this;
                this.stateList.put(stateName, state);
                return true;
            }
        } else {
            if (false  == this.persistentStateList.containsKey(stateName)) {
                state.owner = this.owner;
                state.stateMachine = this;
                this.persistentStateList.put(stateName, state);
                return true;
            }
        }
        return false;
    }
    private FiniteState<T> get(String newStateName) {
        FiniteState<T> ret = null;
        if (null != this.stateList.get(newStateName)) {
            ret = this.stateList.get(newStateName);
        } else if (null != this.persistentStateList.get(newStateName)) {
            ret = this.persistentStateList.get(newStateName);
        }
        return ret;
    }
}
