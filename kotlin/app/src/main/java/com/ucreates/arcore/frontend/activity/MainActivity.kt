// ======================================================================
// Project Name    : android_arcore
//
// Copyright © 2018 U-CREATES. All rights reserved.
//
// This source code is the property of U-CREATES.
// If such findings are accepted at any time.
// We hope the tips and helpful in developing.
// ======================================================================
package com.ucreates.arcore.frontend.activity
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.view.MotionEvent
import com.core.entity.component.Parameter
import com.frontend.notify.INotify
import com.google.ar.core.Plane
import com.google.ar.core.Session
import com.google.ar.sceneform.ArSceneView
import com.google.ar.sceneform.FrameTime
import com.google.ar.sceneform.HitTestResult
import com.google.ar.sceneform.Scene
import com.google.ar.sceneform.rendering.PlaneRenderer
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.PlaneDiscoveryController
import com.ucreates.arcore.R
import com.ucreates.arcore.frontend.behaviour.IconBehaviour
import com.frontend.notify.NotifyMessage
import com.frontend.notify.Notifier
class MainActivity : AppCompatActivity(), INotify {
    private var behaviour: IconBehaviour? = null
    private var session: Session? = null
    private var enableTouch: Boolean = false
    private var complete: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.activity_main)
        val manager: FragmentManager = this.supportFragmentManager
        val fragment: ArFragment = manager.findFragmentById(R.id.ux_fragment) as ArFragment
        val view: ArSceneView = fragment.arSceneView
        val scene: Scene = view.scene
        scene.setOnTouchListener(this::onSceneTouch)
        scene.addOnUpdateListener(this::onUpdate)
        this.behaviour = IconBehaviour(this, scene, fragment)
        this.behaviour!!.onCreate()
        Notifier.add(this)
        return
    }
    override fun onResume() {
        super.onResume()
        return
    }
    override fun onPostResume() {
        super.onPostResume()
        val manager: FragmentManager = this.supportFragmentManager
        val fragment: ArFragment = manager.findFragmentById(R.id.ux_fragment) as ArFragment
        val view: ArSceneView = fragment.arSceneView
        val renderer: PlaneRenderer = view.planeRenderer
        val controller: PlaneDiscoveryController = fragment.planeDiscoveryController
        renderer.isEnabled = false
        controller.hide()
        controller.setInstructionView(null)
        this.session = view.session
        return
    }
    private fun onUpdate(frameTime: FrameTime) {
        if (false == this.complete) {
            val trackables: Collection<Plane> = this.session?.getAllTrackables(Plane::class.java)!!
            trackables.forEach { plane ->
                var parameter: Parameter = Parameter()
                parameter.set("detectedPlane", plane)
                Notifier.notify(NotifyMessage.OnTrackingFound, parameter)
                this.complete = true
            }
        }
        this.behaviour?.onUpdate(frameTime.getDeltaSeconds())
        return
    }
    private fun onSceneTouch(var1: HitTestResult, var2: MotionEvent): Boolean {
        if (false == this.enableTouch) {
            return true
        }
        Notifier.notify(NotifyMessage.OnRaycastHit)
        this.enableTouch = false
        return true
    }
    override fun onNotify(message: NotifyMessage, parameter: Parameter?) {
        if (message == NotifyMessage.OnActionComplete) {
            this.enableTouch = true
        }
        return
    }
}