// ======================================================================
// Project Name    : android_arcore
//
// Copyright © 2018 U-CREATES. All rights reserved.
//
// This source code is the property of U-CREATES.
// If such findings are accepted at any time.
// We hope the tips and helpful in developing.
// ======================================================================
package com.ucreates.arcore.frontend.behaviour
import android.content.Context
import com.core.entity.component.Parameter
import com.frontend.behaviour.BaseBehaviour
import com.frontend.notify.INotify
import com.frontend.notify.Notifier
import com.frontend.notify.NotifyMessage
import com.google.ar.sceneform.Scene
import com.google.ar.sceneform.ux.ArFragment
import com.frontend.state.FiniteStateMachine
import com.frontend.state.IFiniteStateMachine
import com.google.ar.sceneform.Node
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.math.Vector3
import com.ucreates.arcore.R
import com.ucreates.arcore.frontend.behaviour.state.IconShowState
import com.ucreates.arcore.frontend.behaviour.state.IconJumpState
import com.ucreates.arcore.frontend.behaviour.state.IconBeatState
import java.util.*
import java.util.function.Consumer
import java.util.function.Function
open class IconBehaviour(context: Context, scene: Scene, fragment: ArFragment) : BaseBehaviour(context), IFiniteStateMachine<IconBehaviour>, INotify {
    companion object {
        val ICON_SCALE = 2.0f
    }
    var fragment: ArFragment = fragment
    var node: Node? = null
    var scene: Scene = scene
    var defaultPosition: Vector3? = null
    var renderable: ModelRenderable? = null
    private var stateMachine: FiniteStateMachine<IconBehaviour> = FiniteStateMachine<IconBehaviour>(this)
    init {
        this.stateMachine.add("show" , IconShowState())
        this.stateMachine.add("jump" , IconJumpState())
        this.stateMachine.add("beat" , IconBeatState())
        Notifier.add(this)
    }
    override fun onCreate() {
        this.node = Node()
        val onAcceptCallback: Consumer<ModelRenderable> = object : Consumer<ModelRenderable> {
            override fun accept(t: ModelRenderable) {
                renderable = t
            }
        }
        val onExceptionallyCallback: Function<Throwable, Void> = object : Function<Throwable, Void> {
            override fun apply(t: Throwable): Void {
                return t as Void
            }
        }
        val builder: ModelRenderable.Builder = ModelRenderable.Builder()
        builder.setSource(this.context, R.raw.android).build().thenAccept(onAcceptCallback).exceptionally(onExceptionallyCallback)
        return
    }
    override fun onUpdate(delta: Float) {
        this.stateMachine.update(delta)
        return
    }
    override fun onNotify(message: NotifyMessage, parameter: Parameter?) {
        if (NotifyMessage.OnTrackingFound == message) {
            this.stateMachine.change("show", parameter)
        } else if (NotifyMessage.OnRaycastHit == message) {
            val rnd: Random = Random()
            val i = rnd.nextInt(3)
            if (0 == i % 2) {
                this.stateMachine.change("beat")
            } else {
                this.stateMachine.change("jump")
            }
            this.stateMachine.play()
        }
        return
    }
    override fun getStateMachine(): FiniteStateMachine<IconBehaviour> {
        return this.stateMachine
    }
}