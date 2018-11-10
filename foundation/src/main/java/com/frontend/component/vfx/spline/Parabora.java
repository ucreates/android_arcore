// ======================================================================
// Project Name    : android_foundation
//
// Copyright © 2018 U-CREATES. All rights reserved.
//
// This source code is the property of U-CREATES.
// If such findings are accepted at any time.
// We hope the tips and helpful in developing.
// ======================================================================
package com.frontend.component.vfx.spline;
import android.renderscript.Float3;
import java.lang.Math;
public class Parabora {
    private static final float GRAVITY = 0.98f;
    public float mass;
    public float velocity;
    public float radian;
    public Parabora() {
        this.velocity = 1.0f;
        this.mass = 1.0f;
        this.radian = 1.0f;
    }
    public Float3 create(float currentFrame) {
        float sin = (float)Math.sin(this.radian);
        float cos = (float)Math.cos(this.radian);
        float x = this.velocity * cos * currentFrame;
        float y = (this.velocity * sin * currentFrame) - (Parabora.GRAVITY * (float)Math.pow(currentFrame, 2.0f) / 2.0f);
        return new Float3(x, y, 0.0f);
    }
}
