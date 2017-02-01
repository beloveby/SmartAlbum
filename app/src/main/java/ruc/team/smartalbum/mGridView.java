package ruc.team.smartalbum;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by 边园 on 2017/2/1.
 */

public class mGridView extends GridView {
    public mGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public mGridView(Context context) {
        super(context);
    }

    public mGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
