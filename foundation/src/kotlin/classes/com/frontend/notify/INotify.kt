// ======================================================================
// Project Name    : android_foundation
//
// Copyright © 2018 U-CREATES. All rights reserved.
//
// This source code is the property of U-CREATES.
// If such findings are accepted at any time.
// We hope the tips and helpful in developing.
// ======================================================================
package com.frontend.notify
import com.core.entity.component.Parameter
interface INotify {
     abstract fun onNotify(message: NotifyMessage, parameter: Parameter?)
}