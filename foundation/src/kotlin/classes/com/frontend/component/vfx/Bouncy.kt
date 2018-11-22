// ======================================================================
// Project Name    : android_foundation
//
// Copyright © 2018 U-CREATES. All rights reserved.
//
// This source code is the property of U-CREATES.
// If such findings are accepted at any time.
// We hope the tips and helpful in developing.
// ======================================================================
package com.frontend.component.vfx
open class Bouncy(velocity: Float, rate: Float) {
    public val GRAVITY: Float = 0.98f
    public val E: Float = 0.5f
    public var gravity: Float = GRAVITY * rate
        get
        private set
    public var velocity: Float = velocity
        get
        private set
    public var originVelocity: Float = velocity
        get
        private set
    open fun update(): Float {
        this.velocity -= this.gravity
        return this.velocity
    }
    open fun restore() {
        this.originVelocity *= E
        this.velocity = this.originVelocity
        return
    }
}