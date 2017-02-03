package ruc.team.smartalbum.StartPage;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.design.widget.FloatingActionButton;
import android.view.View;

/**
 * Created by 边园 on 2017/1/25.
 */

public class ButtonGroupAnimUtil {

    public static void closeMenu(FloatingActionButton time, FloatingActionButton label, FloatingActionButton file) {
        ObjectAnimator objectAnimatorTimeX = ObjectAnimator.ofFloat(time, "TranslationX", 0);
        ObjectAnimator objectAnimatorTimeY = ObjectAnimator.ofFloat(time, "TranslationY", 0);
        ObjectAnimator objectAnimatorTimeVisibility = ObjectAnimator.ofInt(time, "visibility", View.INVISIBLE);

        ObjectAnimator objectAnimatorFileY = ObjectAnimator.ofFloat(file, "TranslationY", 0);
        ObjectAnimator objectAnimatorFileVisibility = ObjectAnimator.ofInt(file, "visibility", View.INVISIBLE);

        ObjectAnimator objectAnimatorLabelX = ObjectAnimator.ofFloat(label, "TranslationX", 0);
        ObjectAnimator objectAnimatorLabelVisibility = ObjectAnimator.ofInt(label, "visibility", View.INVISIBLE);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(objectAnimatorTimeX, objectAnimatorTimeY, objectAnimatorTimeVisibility, objectAnimatorFileY, objectAnimatorFileVisibility, objectAnimatorLabelX, objectAnimatorLabelVisibility);
        animatorSet.setDuration(300);
        animatorSet.start();
    }

    public static void showMenu(FloatingActionButton time, FloatingActionButton label, FloatingActionButton file) {
        ObjectAnimator objectAnimatorTimeX = ObjectAnimator.ofFloat(time, "TranslationX", -160);
        ObjectAnimator objectAnimatorTimeY = ObjectAnimator.ofFloat(time, "TranslationY", -160);
        ObjectAnimator objectAnimatorTimeVisibility = ObjectAnimator.ofInt(time, "visibility", View.VISIBLE);

        ObjectAnimator objectAnimatorFileY = ObjectAnimator.ofFloat(file, "TranslationY", -200);
        ObjectAnimator objectAnimatorFileVisibility = ObjectAnimator.ofInt(file, "visibility", View.VISIBLE);

        ObjectAnimator objectAnimatorLabelX = ObjectAnimator.ofFloat(label, "TranslationX", -200);
        ObjectAnimator objectAnimatorLabelVisibility = ObjectAnimator.ofInt(label, "visibility", View.VISIBLE);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(objectAnimatorTimeX, objectAnimatorTimeY, objectAnimatorTimeVisibility, objectAnimatorFileY, objectAnimatorFileVisibility, objectAnimatorLabelX, objectAnimatorLabelVisibility);
        animatorSet.setDuration(300);
        animatorSet.start();
    }
}
