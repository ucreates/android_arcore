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
import android.renderscript.Float3;
import com.core.math.Angle;
import com.frontend.component.vfx.spline.Parabora;
import com.frontend.notify.NotifyMessage;
import com.frontend.state.FiniteState;
import com.frontend.notify.Notifier;
import com.google.ar.sceneform.math.Vector3;
import com.ucreates.arcore.frontend.behaviour.IconBehaviour;
public class IconJumpState extends FiniteState<IconBehaviour> {
    private Parabora parabora;
    public void create() {
        this.parabora = new Parabora();
        this.parabora.velocity = 1.0f;
        this.parabora.mass = 1.0f;
        this.parabora.radian = Angle.toRadian(90.0f);
        this.timeLine.restore();
        this.timeLine.rate = 0.1f;
        return;
    }
    @Override
    public void update(double delta) {
        if (null == this.owner.defaultPosition) {
            return;
        }
        Float3 position = this.parabora.create(this.timeLine.currentFrame);
        if (0.0 > position.y) {
            this.owner.node.setLocalPosition(this.owner.defaultPosition);
            Notifier notifier = Notifier.getInstance();
            notifier.notify(NotifyMessage.OnActionComplete);
            this.complete = true;
            return;
        } else {
            Vector3 currentPostion = this.owner.node.getLocalPosition();
            float nx = currentPostion.x;
            float ny = position.y;
            float nz = currentPostion.z;
            this.owner.node.setLocalPosition(new Vector3(nx, ny, nz));
        }
        this.timeLine.next((float)delta);
        return;
    }
}
