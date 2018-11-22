// ======================================================================
// Project Name    : android_arcore
//
// Copyright © 2018 U-CREATES. All rights reserved.
//
// This source code is the property of U-CREATES.
// If such findings are accepted at any time.
// We hope the tips and helpful in developing.
// ======================================================================
package com.ucreates.arcore.frontend.activity;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.view.MotionEvent;
import com.core.entity.component.Parameter;
import com.frontend.notify.INotifier;
import com.frontend.notify.Notifier;
import com.frontend.notify.NotifyMessage;
import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.core.Session;
import com.google.ar.core.TrackingState;
import com.google.ar.sceneform.ArSceneView;
import com.google.ar.sceneform.FrameTime;
import com.google.ar.sceneform.Scene;
import com.google.ar.sceneform.rendering.PlaneRenderer;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.PlaneDiscoveryController;
import com.ucreates.arcore.R;
import com.ucreates.arcore.frontend.behaviour.IconBehaviour;
public class MainActivity extends AppCompatActivity implements INotifier {
    private IconBehaviour behaviour;
    private Session session;
    private boolean enableTouch = false;
    private boolean complete = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);
        FragmentManager manager = this.getSupportFragmentManager();
        ArFragment fragment = (ArFragment) manager.findFragmentById(R.id.ux_fragment);
        ArSceneView view = fragment.getArSceneView();
        Scene scene = view.getScene();
        fragment.setOnTapArPlaneListener(this::onTouch);
        scene.addOnUpdateListener(this::onUpdateFrame);
        this.behaviour = new IconBehaviour(this, scene, fragment);
        this.behaviour.onCreate();
        Notifier notifier = Notifier.getInstance();
        notifier.add(this);
        return;
    }
    @Override
    protected void onResume() {
        super.onResume();
        return;
    }
    @Override
    protected void onPostResume() {
        super.onPostResume();
        FragmentManager manager = this.getSupportFragmentManager();
        ArFragment fragment = (ArFragment) manager.findFragmentById(R.id.ux_fragment);
        ArSceneView view = fragment.getArSceneView();
        PlaneRenderer renderer = view.getPlaneRenderer();
        PlaneDiscoveryController controller = fragment.getPlaneDiscoveryController();
        renderer.setEnabled(false);
        controller.hide();
        controller.setInstructionView(null);
        this.session = view.getSession();
        return;
    }
    private void onUpdateFrame(FrameTime frameTime) {
        if (false == this.complete) {
            for (Plane plane : this.session.getAllTrackables(Plane.class)) {
                if (TrackingState.TRACKING == plane.getTrackingState()) {
                    Parameter parameter = new Parameter();
                    parameter.set("detectedPlane", plane);
                    Notifier notifier = Notifier.getInstance();
                    notifier.notify(NotifyMessage.OnTrackingFound, parameter);
                    this.complete = true;
                    break;
                }
            }
        }
        this.behaviour.onUpdate(frameTime.getDeltaSeconds());
        return;
    }
    @Override
    public void onNotify(NotifyMessage message, Parameter parameter) {
        if (message == NotifyMessage.OnActionComplete) {
            this.enableTouch = true;
        }
        return;
    }
    private void onTouch(HitResult hitResult, Plane plane, MotionEvent motionEvent) {
        if (false == this.enableTouch) {
            return;
        }
        Notifier notifier = Notifier.getInstance();
        notifier.notify(NotifyMessage.OnRaycastHit);
        this.enableTouch = false;
        return;
    }
}
