// ======================================================================
// Project Name    : android_foundation
//
// Copyright © 2018 U-CREATES. All rights reserved.
//
// This source code is the property of U-CREATES.
// If such findings are accepted at any time.
// We hope the tips and helpful in developing.
// ======================================================================
package com.frontend.notify;
import com.core.entity.component.Parameter;
import java.util.HashMap;
public class Notifier {
    private static Notifier instance = null;
    private HashMap<Integer, INotifier> notifierHashMap = new HashMap<Integer, INotifier>();
    public static Notifier getInstance() {
        if (null == Notifier.instance) {
            Notifier.instance = new Notifier();
        }
        return Notifier.instance;
    }
    public void notify(NotifyMessage message) {
        this.notify(message, null);
        return;
    }
    public void notify(NotifyMessage message, Parameter parameter) {
        for (Integer id : this.notifierHashMap.keySet()) {
            INotifier inotifier = this.notifierHashMap.get(id);
            inotifier.onNotify(message, parameter);
        }
        return;
    }
    public boolean add(INotifier inotifier) {
        int uniqId = System.identityHashCode(inotifier);
        if (false == this.notifierHashMap.containsKey(uniqId)) {
            this.notifierHashMap.put(uniqId, inotifier);
            return true;
        }
        return false;
    }
}
