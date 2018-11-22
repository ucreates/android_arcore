// ======================================================================
// Project Name    : android_foundation
//
// Copyright © 2018 U-CREATES. All rights reserved.
//
// This source code is the property of U-CREATES.
// If such findings are accepted at any time.
// We hope the tips and helpful in developing.
// ======================================================================
package com.core.io.stream;
import android.util.Log;
public class ConsoleStream {
    public final static String IDENTIFIER = "ANDROID_FOUNDATION";
    public static void write(String val) {
        Log.i(ConsoleStream.IDENTIFIER, val);
        return;
    }
    public static void write(int val) {
        Log.i(ConsoleStream.IDENTIFIER, String.valueOf(val));
        return;
    }
    public static void write(float val) {
        Log.i(ConsoleStream.IDENTIFIER, String.valueOf(val));
        return;
    }
    public static void write(String title, String val) {
        String content = String.format("%s::%s", title, val);
        Log.i(ConsoleStream.IDENTIFIER, content);
        return;
    }
    public static void write(String title, float val) {
        String content = String.format("%s::%f", title, val);
        Log.i(ConsoleStream.IDENTIFIER, content);
        return;
    }
}
