// ======================================================================
// Project Name    : android_arcore
//
// Copyright © 2018 U-CREATES. All rights reserved.
//
// This source code is the property of U-CREATES.
// If such findings are accepted at any time.
// We hope the tips and helpful in developing.
// ======================================================================
package com.ucreates.arcore.frontend.behaviour;
import android.content.Context;
import android.util.Log;
import com.core.entity.component.Parameter;
import com.frontend.behaviour.BaseBehaviour;
import com.frontend.notify.INotifier;
import com.frontend.notify.Notifier;
import com.frontend.notify.NotifyMessage;
import com.frontend.state.FiniteStateMachine;
import com.frontend.state.IFiniteStateMachine;
import com.google.ar.sceneform.Node;
import com.google.ar.sceneform.Scene;
import com.google.ar.sceneform.math.Vector3;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.ucreates.arcore.R;
import com.ucreates.arcore.frontend.behaviour.state.IconBeatState;
import com.ucreates.arcore.frontend.behaviour.state.IconJumpState;
import com.ucreates.arcore.frontend.behaviour.state.IconShowState;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
public class IconBehaviour extends BaseBehaviour implements IFiniteStateMachine<IconBehaviour>, INotifier {
    public static final float ICON_SCALE = 2.0f;
    public ArFragment fragment;
    public Node node;
    public Scene scene;
    public Vector3 defaultPosition;
    public ModelRenderable renderable;
    private FiniteStateMachine<IconBehaviour> stateMachine;
    public IconBehaviour(Context context, Scene scene, ArFragment fragment) {
        super(context);
        this.scene = scene;
        this.fragment = fragment;
        this.stateMachine = new FiniteStateMachine<IconBehaviour>(this);
        this.stateMachine.add("show", new IconShowState());
        this.stateMachine.add("jump", new IconJumpState());
        this.stateMachine.add("beat", new IconBeatState());
        Notifier notifier = Notifier.getInstance();
        notifier.add(this);
        return;
    }
    @Override
    public void onCreate() {
        this.node = new Node();
        Consumer<ModelRenderable> onAcceptCallback = renderable -> {
            this.renderable = renderable;
            return;
        };
        Function<Throwable, Void> onExceptionallyCallback = throwable -> {
            Log.e("ANDROID_FOUNDATION", "Unable to load Renderable.", throwable);
            return null;
        };
        ModelRenderable.Builder builder = new ModelRenderable.Builder();
        builder.setSource(this.context, R.raw.android).build().thenAccept(onAcceptCallback).exceptionally(onExceptionallyCallback);
        return;
    }
    @Override
    public void onUpdate(double delta) {
        this.stateMachine.update(delta);
        return;
    }
    @Override
    public void onNotify(NotifyMessage message, Parameter parameter) {
        if (NotifyMessage.OnTrackingFound == message) {
            this.stateMachine.change("show", parameter);
        } else if (NotifyMessage.OnRaycastHit == message) {
            Random rnd = new Random();
            int i = rnd.nextInt(3);
            if (0 == i % 2) {
                this.stateMachine.change("beat");
            } else {
                this.stateMachine.change("jump");
            }
            this.stateMachine.play();
        }
        return;
    }
    @Override
    public FiniteStateMachine<IconBehaviour> getStateMachine() {
        return this.stateMachine;
    }
}
