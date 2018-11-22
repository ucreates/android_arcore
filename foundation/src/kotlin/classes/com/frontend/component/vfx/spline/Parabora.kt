// ======================================================================
// Project Name    : android_foundation
//
// Copyright © 2018 U-CREATES. All rights reserved.
//
// This source code is the property of U-CREATES.
// If such findings are accepted at any time.
// We hope the tips and helpful in developing.
// ======================================================================
package com.frontend.component.vfx.spline
import android.renderscript.Float3
open class Parabora {
    public val GRAVITY: Double = 0.98
    public var mass: Double = 1.0
        get
        set
    public var velocity: Double = 1.0
        get
        set
    public var radian: Double = 1.0
        get
        set
    open fun create(currentFrame: Float): Float3 {
        var sin: Double = Math.sin(this.radian)
        var cos: Double = Math.cos(this.radian)
        var x: Double = this.velocity * cos * currentFrame
        var y: Double = (this.velocity * sin * currentFrame) - (GRAVITY * Math.pow(currentFrame.toDouble(), 2.0) / 2.0)
        return Float3(x.toFloat(), y.toFloat(), 0.0f)
    }
}