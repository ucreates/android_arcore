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
import com.core.entity.component.Parameter
import com.frontend.component.vfx.easing.Quadratic
import com.frontend.notify.Notifier
import com.frontend.notify.NotifyMessage
import com.frontend.state.FiniteState
import com.google.ar.core.Anchor
import com.google.ar.core.Plane
import com.google.ar.core.Pose
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.math.Quaternion
import com.google.ar.sceneform.math.Vector3
import com.google.ar.sceneform.ux.*
import com.ucreates.arcore.frontend.behaviour.IconBehaviour
open class IconShowState : FiniteState<IconBehaviour>() {
    val ROTATE_DEGREE: Float = 360.0f * 10.0f
    val ANIMATION_TIME: Float = 5.0f
    override fun create(parameter: Parameter?) {
        val plane: Plane? = parameter?.get<Plane>("detectedPlane")
        val pose: Pose? = plane?.centerPose
        val anchor: Anchor = plane?.createAnchor(pose)!!
        val anchorNode: AnchorNode = AnchorNode(anchor)
        anchorNode.setParent(this.owner?.scene)
        val transformSystem: TransformationSystem = this.owner?.fragment?.transformationSystem!!
        val transform: TransformableNode = TransformableNode(transformSystem)
        transform.isEnabled = true
        transform.setParent(anchorNode)
        transform.select()
        val tcontroller: TranslationController = transform.translationController
        val rcontroller: RotationController = transform.rotationController
        val scontroller: ScaleController = transform.scaleController
        tcontroller.isEnabled = false
        rcontroller.isEnabled = false
        scontroller.isEnabled = false
        this.owner?.node?.setParent(transform)
        this.owner?.node?.renderable = this.owner?.renderable
        this.owner?.node?.localScale = Vector3.one()
        this.owner?.defaultPosition = this.owner?.node?.localPosition
        this.timeLine.restore()
        return
    }
    override fun update(delta: Float) {
        var localScale: Vector3
        var localRotation: Quaternion = Quaternion.identity()
        if (ANIMATION_TIME < this.timeLine.currentTime) {
            localScale = Vector3(IconBehaviour.ICON_SCALE, IconBehaviour.ICON_SCALE, IconBehaviour.ICON_SCALE)
            this.owner?.node?.localScale = localScale
            this.owner?.node?.localRotation = localRotation
            Notifier.notify(NotifyMessage.OnActionComplete)
            this.complete = true
        }
        var scale: Float = Quadratic.easeOut(this.timeLine.currentTime, ANIMATION_TIME, 0.0f, IconBehaviour.ICON_SCALE)
        var rotate: Float = ROTATE_DEGREE - Quadratic.easeOut(this.timeLine.currentTime, ANIMATION_TIME, 0.0f, ROTATE_DEGREE)
        localScale = Vector3(scale, IconBehaviour.ICON_SCALE, scale)
        localRotation = Quaternion.axisAngle(Vector3(0.0f, 1.0f, 0.0f), rotate)
        this.owner?.node?.localScale = localScale
        this.owner?.node?.localRotation = localRotation
        this.timeLine.next(delta)
        return
    }
}