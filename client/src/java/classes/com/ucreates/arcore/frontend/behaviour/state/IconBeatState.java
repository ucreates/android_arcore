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
import com.frontend.component.vfx.Bouncy;
import com.frontend.notify.NotifyMessage;
import com.frontend.state.FiniteState;
import com.frontend.notify.Notifier;
import com.google.ar.sceneform.math.Vector3;
import com.ucreates.arcore.frontend.behaviour.IconBehaviour;
public class IconBeatState extends FiniteState<IconBehaviour> {
    private static final float ROTATE_DEGREE = 360.0f * 10.0f;
    private static final float ANIMATION_TIME = 5.0f;
    private Bouncy bouncy;
    public void create() {
        this.bouncy = new Bouncy(0.25f, 0.025f);
        this.timeLine.restore();
        return;
    }
    @Override
    public void update(double delta) {
        if (0.05 > this.bouncy.originVelocity) {
            Vector3 scale = new Vector3(IconBehaviour.ICON_SCALE, IconBehaviour.ICON_SCALE, IconBehaviour.ICON_SCALE);
            this.owner.node.setLocalScale(scale);
            Notifier notifier = Notifier.getInstance();
            notifier.notify(NotifyMessage.OnActionComplete);
            this.complete = true;
            return;
        }
        float rate  = this.bouncy.update();
        if (rate <= 0.0) {
            this.bouncy.restore();
        }
        float scale = IconBehaviour.ICON_SCALE + rate;
        Vector3 localScale = new Vector3(scale, scale, scale);
        this.owner.node.setLocalScale(localScale);
        return;
    }
}
