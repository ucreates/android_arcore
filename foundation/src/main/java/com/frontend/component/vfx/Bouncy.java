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
public class Bouncy {
    private static float GRAVITY = 0.98f;
    private static float E = 0.5f;
    public float gravity;
    public float velocity;
    public float originVelocity;
    public Bouncy(float velocity, float rate) {
        this.velocity = velocity;
        this.originVelocity = velocity;
        this.gravity = Bouncy.GRAVITY * rate;
    }
    public float update() {
        this.velocity -= this.gravity;
        return this.velocity;
    }
    public void restore() {
        this.originVelocity *= Bouncy.E;
        this.velocity = this.originVelocity;
        return;
    }
}
