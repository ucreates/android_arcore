// ======================================================================
// Project Name    : android_foundation
//
// Copyright © 2018 U-CREATES. All rights reserved.
//
// This source code is the property of U-CREATES.
// If such findings are accepted at any time.
// We hope the tips and helpful in developing.
// ======================================================================
package com.core.math;
import java.lang.Math;
public class Angle {
    public static float toDegree(float radian) {
        return radian * 180.0f / (float)Math.PI;
    }
    public static float toRadian(float degree) {
        return degree * (float)Math.PI / 180.0f;
    }
}
