// ======================================================================
// Project Name    : android_foundation
//
// Copyright © 2018 U-CREATES. All rights reserved.
//
// This source code is the property of U-CREATES.
// If such findings are accepted at any time.
// We hope the tips and helpful in developing.
// ======================================================================
package com.frontend.component.vfx;
public class TimeLine {
    public float currentFrame;
    public float currentTime;
    public float rate;
    public TimeLine() {
        this.currentFrame = 0.0f;
        this.currentTime = 0.0f;
        this.rate = 0.0f;
    }
    public void next(float delta) {
        this.next(delta, 1.0f);
        return;
    }
    public void next(float delta, float multipleRate) {
        this.currentFrame += this.rate;
        this.currentTime += delta * multipleRate;
        return;
    }
    public void restore() {
        this.currentFrame = 0.0f;
        this.currentTime = 0.0f;
    }
}
