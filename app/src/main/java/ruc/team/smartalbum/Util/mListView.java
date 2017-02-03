package ruc.team.smartalbum.Util;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by 边园 on 2017/2/1.
 */

public class mListView extends ListView {
    public mListView(Context context) {
        super(context);
    }

    public mListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public mListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public mListView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
        int expandSpec = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
