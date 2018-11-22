// ======================================================================
// Project Name    : android_foundation
//
// Copyright © 2018 U-CREATES. All rights reserved.
//
// This source code is the property of U-CREATES.
// If such findings are accepted at any time.
// We hope the tips and helpful in developing.
// ======================================================================
package com.frontend.component.vfx.easing
open class Quadratic {
    companion object {
        private val instance = Quadratic()
        fun getInstance(): Quadratic {
            return this.instance
        }
        fun easeIn(currentTime: Float, totalTime: Float, start: Float, end: Float): Float {
            var diff: Float = end - start
            var rate: Float = currentTime / totalTime
            if (rate > 1.0) {
                rate = 1.0f
            }
            return diff * Math.pow(rate.toDouble(), 2.0).toFloat() + start
        }
        fun easeOut(currentTime: Float, totalTime: Float, start: Float, end: Float): Float {
            var diff: Float = end - start
            var rate: Float = currentTime / totalTime
            if (rate > 1.0f) {
                rate = 1.0f
            }
            return -1.0f * diff * rate * (rate - 2.0f) + start
        }
        fun easeInOut(currentTime: Float, totalTime: Float, start: Float, end: Float): Float {
            var switchType: Boolean = currentTime / totalTime >= 0.5f
            if (false == switchType) {
                return this.easeIn(currentTime, totalTime, start, end)
            } else {
                return this.easeOut(currentTime, totalTime, start, end)
            }
        }
    }
}