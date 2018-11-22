// ======================================================================
// Project Name    : android_foundation
//
// Copyright © 2018 U-CREATES. All rights reserved.
//
// This source code is the property of U-CREATES.
// If such findings are accepted at any time.
// We hope the tips and helpful in developing.
// ======================================================================
package com.core.math
open class Angle {
    companion object {
        const val IDENTIFIER = "ANDROID_FOUNDATION"
        private val instance = Angle()
        fun getInstance(): Angle {
            return this.instance
        }
        fun toDegree(radian: Float): Float {
            return radian * 180.0f / Math.PI.toFloat()
        }
        fun toRadian(degree: Float): Float {
            return degree * Math.PI.toFloat() / 180.0f
        }
    }
}