// ======================================================================
// Project Name    : android_foundation
//
// Copyright © 2018 U-CREATES. All rights reserved.
//
// This source code is the property of U-CREATES.
// If such findings are accepted at any time.
// We hope the tips and helpful in developing.
// ======================================================================
package com.frontend.component.vfx.easing;
import java.lang.Math;
public class Quadratic {
    public static float easeIn(float currentTime, float totalTime, float start, float end) {
        float diff = end - start;
        float rate = currentTime / totalTime;
        if (rate > 1.0f) {
            rate = 1.0f;
        }
        return diff * (float)Math.pow(rate, 2.0f) + start;
    }
    public static float easeOut(float currentTime, float totalTime, float start, float end) {
        float diff = end - start;
        float rate = currentTime / totalTime;
        if (rate > 1.0f) {
            rate = 1.0f;
        }
        return -1.0f * diff * rate * (rate - 2.0f) + start;
    }
    public static float easeInOut(float currentTime, float totalTime, float start, float end) {
        boolean switchType = currentTime / totalTime >= 0.5f;
        if (false == switchType) {
            return Quadratic.easeIn(currentTime, totalTime, start, end);
        } else {
            return Quadratic.easeOut(currentTime, totalTime, start, end);
        }
    }
}
