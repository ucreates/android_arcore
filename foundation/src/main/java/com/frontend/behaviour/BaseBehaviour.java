// ======================================================================
// Project Name    : android_foundation
//
// Copyright © 2018 U-CREATES. All rights reserved.
//
// This source code is the property of U-CREATES.
// If such findings are accepted at any time.
// We hope the tips and helpful in developing.
// ======================================================================
package com.frontend.behaviour;
import android.content.Context;
import com.core.entity.component.Parameter;
public abstract class BaseBehaviour {
    public int id;
    public boolean destroy;
    public boolean intersect;
    protected Context context;
    public BaseBehaviour(Context context) {
        this.context = context;
        this.destroy = false;
        this.intersect = false;
    }
    public void onCreate() {
        return;
    }
    public void onCreate(Parameter parameter) {
        return;
    }
    public void onUpdate(double delta) {
        return;
    }
    public void onRender() {
        return;
    }
}
