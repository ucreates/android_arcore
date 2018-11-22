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
import android.renderscript.Float3
import com.core.math.Angle
import com.frontend.component.vfx.spline.Parabora
import com.frontend.notify.Notifier
import com.frontend.notify.NotifyMessage
import com.frontend.state.FiniteState
import com.google.ar.sceneform.math.Vector3
import com.ucreates.arcore.frontend.behaviour.IconBehaviour
open class IconJumpState : FiniteState<IconBehaviour>() {
    val parabora: Parabora = Parabora()
    override fun create() {
        this.parabora.velocity = 1.0
        this.parabora.mass = 1.0
        this.parabora.radian = Angle.toRadian(90.0f).toDouble()
        this.timeLine.restore()
        this.timeLine.rate = 0.1f
        return
    }
    override fun update(delta: Float) {
        if (null == this.owner?.defaultPosition) {
            return
        }
        var position: Float3 = this.parabora.create(this.timeLine.currentFrame)
        if (0.0 > position.y) {
            this.owner?.node?.localPosition = this.owner?.defaultPosition
            Notifier.notify(NotifyMessage.OnActionComplete)
            return
        } else {
            var currentPosition: Vector3 = this.owner?.node?.localPosition!!
            var nx: Float = currentPosition.x
            var ny: Float = position.y
            var nz: Float = currentPosition.z
            this.owner?.node?.localPosition = Vector3(nx , ny , nz)
        }
        this.timeLine.next(delta)
        return
    }
}