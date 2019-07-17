// ======================================================================
// Project Name    : android_arcore
//
// Copyright © 2018 U-CREATES. All rights reserved.
//
// This source code is the property of U-CREATES.
// If such findings are accepted at any time.
// We hope the tips and helpful in developing.
// ======================================================================
package com.ucreates.arcore.frontend.behaviour.state
import com.frontend.component.vfx.Bouncy
import com.frontend.notify.Notifier
import com.frontend.notify.NotifyMessage
import com.frontend.state.FiniteState
import com.google.ar.sceneform.math.Vector3
import com.ucreates.arcore.frontend.behaviour.IconBehaviour
open class IconBeatState : FiniteState<IconBehaviour>() {
    var bouncy: Bouncy? = null
    override fun create() {
        this.bouncy = Bouncy(0.25f, 0.025f)
        this.timeLine.restore()
        return
    }
    override fun update(delta: Float) {
        if (0.05f > this.bouncy?.originVelocity!!) {
            val scale: Vector3 = Vector3(IconBehaviour.ICON_SCALE, IconBehaviour.ICON_SCALE, IconBehaviour.ICON_SCALE)
            this.owner?.node?.localScale = scale
            Notifier.notify(NotifyMessage.OnActionComplete)
            this.complete = true
            return
        }
        val rate: Float = this.bouncy?.update()!!
        if (rate <= 0.0f) {
            this.bouncy?.restore()
        }
        val scale: Float = IconBehaviour.ICON_SCALE + rate
        val localScale: Vector3 = Vector3(scale, scale, scale)
        this.owner?.node?.localScale = localScale
        this.timeLine.next(delta)
        return
    }
}