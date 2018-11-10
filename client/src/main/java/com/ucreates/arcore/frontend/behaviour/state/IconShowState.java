// ======================================================================
// Project Name    : android_arcore
//
// Copyright © 2018 U-CREATES. All rights reserved.
//
// This source code is the property of U-CREATES.
// If such findings are accepted at any time.
// We hope the tips and helpful in developing.
// ======================================================================
package com.ucreates.arcore.frontend.behaviour.state;
import com.core.entity.component.Parameter;
import com.frontend.component.vfx.easing.Quadratic;
import com.frontend.notify.NotifyMessage;
import com.frontend.state.FiniteState;
import com.frontend.notify.Notifier;
import com.google.ar.core.Anchor;
import com.google.ar.core.Plane;
import com.google.ar.core.Pose;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.Node;
import com.google.ar.sceneform.collision.CollisionShape;
import com.google.ar.sceneform.math.Quaternion;
import com.google.ar.sceneform.math.Vector3;
import com.google.ar.sceneform.ux.RotationController;
import com.google.ar.sceneform.ux.ScaleController;
import com.google.ar.sceneform.ux.TransformableNode;
import com.google.ar.sceneform.ux.TransformationSystem;
import com.google.ar.sceneform.ux.TranslationController;
import com.ucreates.arcore.frontend.behaviour.IconBehaviour;
public class IconShowState extends FiniteState<IconBehaviour> {
    private static final float ROTATE_DEGREE = 360.0f * 10.0f;
    private static final float ANIMATION_TIME = 5.0f;
    public void create(Parameter parameter) {
        Plane plane = parameter.get("detectedPlane");
        Pose pose = plane.getCenterPose();
        Anchor anchor = plane.createAnchor(pose);
        AnchorNode anchorNode = new AnchorNode(anchor);
        anchorNode.setParent(this.owner.scene);
        TransformationSystem transformSystem = this.owner.fragment.getTransformationSystem();
        TransformableNode transform = new TransformableNode(transformSystem);
        transform.setEnabled(true);
        transform.setParent(anchorNode);
        transform.select();
        TranslationController tcontroller = transform.getTranslationController();
        RotationController rcontroller = transform.getRotationController();
        ScaleController scontroller = transform.getScaleController();
        tcontroller.setEnabled(false);
        rcontroller.setEnabled(false);
        scontroller.setEnabled(false);
        this.owner.node.setParent(transform);
        this.owner.node.setRenderable(this.owner.renderable);
        this.owner.node.setLocalScale(Vector3.one());
        this.owner.defaultPosition = this.owner.node.getLocalPosition();
        this.timeLine.restore();
        return;
    }
    @Override
    public void update(double delta) {
        Vector3 localScale = Vector3.one();
        Quaternion localRotation = Quaternion.identity();
        if (IconShowState.ANIMATION_TIME < this.timeLine.currentTime) {
            localScale = new Vector3(IconBehaviour.ICON_SCALE, IconBehaviour.ICON_SCALE, IconBehaviour.ICON_SCALE);
            this.owner.node.setLocalScale(localScale);
            this.owner.node.setLocalRotation(localRotation);
            Notifier notifier = Notifier.getInstance();
            notifier.notify(NotifyMessage.OnActionComplete);
            this.complete = true;
            return;
        }
        float scale = Quadratic.easeOut(this.timeLine.currentTime, IconShowState.ANIMATION_TIME, 0.0f, IconBehaviour.ICON_SCALE);
        float rotate = IconShowState.ROTATE_DEGREE - Quadratic.easeOut(this.timeLine.currentTime, IconShowState.ANIMATION_TIME, 0.0f, IconShowState.ROTATE_DEGREE);
        localScale = new Vector3(scale, IconBehaviour.ICON_SCALE, scale);
        localRotation = Quaternion.axisAngle(new Vector3(0.0f, 1.0f, 0.0f), rotate);
        this.owner.node.setLocalScale(localScale);
        this.owner.node.setLocalRotation(localRotation);
        this.timeLine.next((float)delta);
        return;
    }
}
