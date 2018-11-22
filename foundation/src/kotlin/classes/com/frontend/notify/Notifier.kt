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
open class Notifier {
    companion object {
        private val instance: Notifier = Notifier()
        private var notifierHashMap: HashMap<Int, INotify> = HashMap<Int, INotify>()
        fun getInstance(): Notifier {
            return this.instance
        }
        fun notify(message: NotifyMessage) {
            this.notify(message, null)
            return
        }
        fun notify(message: NotifyMessage, parameter: Parameter?) {
            this.notifierHashMap.forEach { (key, inotify) ->
                inotify.onNotify(message, parameter)
            }
            return
        }
        fun add(notifier: INotify): Boolean {
            val uniqId: Int = System.identityHashCode(notifier)
            if (false == this.notifierHashMap.containsKey(uniqId)) {
                this.notifierHashMap.put(uniqId, notifier)
                return true
            }
            return false
        }
    }
}