package ruc.team.smartalbum;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

/**
 * Created by 边园 on 2017/1/30.
 */

public class manyOnClickListener implements View.OnClickListener {
    private Context context;
    private Label fatherNode;


    public manyOnClickListener(Context context) {
        this.context = context;
    }

    @Override
    public void onClick(View v) {

        Toast.makeText(this.context, "hello " + v.getTag() + " " + this.fatherNode.getId(), Toast.LENGTH_SHORT).show();
    }

    public Label getFatherNode() {
        return fatherNode;
    }

    public void setFatherNode(Label fatherNode) {
        this.fatherNode = fatherNode;
    }
}
