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
public class FiniteStateEntity<T> {
    public boolean isNewState;
    public FiniteState<T> state;
    public String currentStateName;
    public String previousStateName;
    public FiniteStateEntity() {
        this.isNewState = false;
        this.state = null;
        this.currentStateName = "";
        this.previousStateName = "";
    }
    public void update(String stateName, FiniteState<T> nextState) {
        if (this.currentStateName != stateName) {
            this.previousStateName = this.currentStateName;
            this.currentStateName = stateName;
            this.state = nextState;
        }
        this.isNewState = true;
        return;
    }
}
