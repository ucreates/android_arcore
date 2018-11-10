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
import com.core.entity.component.Parameter;
import com.frontend.component.vfx.TimeLine;
public class FiniteState<T> {
    public T owner;
    public FiniteStateMachine<T> stateMachine;
    public TimeLine timeLine;
    public boolean persistent;
    public boolean complete;
    public boolean wait;
    public boolean initialize;
    public boolean emitCallback;
    public boolean dump;
    public FiniteState() {
        this.persistent = false;
        this.complete = false;
        this.wait = false;
        this.initialize = false;
        this.emitCallback = false;
        this.dump = false;
        this.stateMachine = null;
        this.timeLine = new TimeLine();
    }
    public void create() {
        return;
    }
    public void create(Parameter parameter) {
        return;
    }
    public void update(double delta) {
        return;
    }
}
