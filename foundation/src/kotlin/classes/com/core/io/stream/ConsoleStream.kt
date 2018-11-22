// ======================================================================
// Project Name    : android_foundation
//
// Copyright © 2018 U-CREATES. All rights reserved.
//
// This source code is the property of U-CREATES.
// If such findings are accepted at any time.
// We hope the tips and helpful in developing.
// ======================================================================
package com.core.io.stream
import android.util.Log
open class ConsoleStream private constructor() {
    companion object {
        const val IDENTIFIER = "ANDROID_FOUNDATION"
        private val instance = ConsoleStream()
        fun getInstance(): ConsoleStream {
            return this.instance
        }
        fun write(value: String) {
            Log.i(ConsoleStream.IDENTIFIER, value)
            return
        }
        fun write(value: Int) {
            Log.i(ConsoleStream.IDENTIFIER, value.toString())
            return
        }
        fun write(value: Float) {
            Log.i(ConsoleStream.IDENTIFIER, value.toString())
            return
        }
        fun write(title: String, value: Int) {
            var content: String = String.format("%s::%d", title, value)
            Log.i(ConsoleStream.IDENTIFIER, content)
            return
        }
        fun write(title: String, value: Float) {
            var content: String = String.format("%s::%f", title, value)
            Log.i(ConsoleStream.IDENTIFIER, content)
            return
        }
    }
}