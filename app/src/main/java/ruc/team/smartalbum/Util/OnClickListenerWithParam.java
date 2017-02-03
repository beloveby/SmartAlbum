package ruc.team.smartalbum.Util;

import android.view.View;

/**
 * Created by 边园 on 2016/5/7.
 */
public abstract class OnClickListenerWithParam implements View.OnClickListener {
    private Object mParam;

    public OnClickListenerWithParam(Object param) {
        this.mParam = param;
    }

    public Object getmParam() {
        return this.mParam;
    }
}
