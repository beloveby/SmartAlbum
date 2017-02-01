package ruc.team.smartalbum;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by 边园 on 2017/1/30.
 */

public class manyOnClickListener implements View.OnClickListener {
    private Context context;
    private Label fatherNode;

    private SmartAlbumAdapter smartAlbumAdapter;


    public manyOnClickListener(Context context) {
        this.context = context;
    }

    @Override
    public void onClick(View v) {
        int clickId = TempData.getChildId(fatherNode.getId(), (Integer) v.getTag());
        this.fatherNode.setFahterNode(this.fatherNode.getId());
        this.fatherNode.setId(clickId);
        this.fatherNode.setHasChild(TempData.setHasChild(this.fatherNode.getId()));
        this.fatherNode.setName(TempData.setName(this.fatherNode.getId()));

        this.smartAlbumAdapter.setData(SmartAlbumAdapter.OrganizeMode.LABEL, fatherNode);
        this.smartAlbumAdapter.notifyDataSetChanged();
    }

    public Label getFatherNode() {
        return fatherNode;
    }

    public void setFatherNode(Label fatherNode) {
        this.fatherNode = fatherNode;
    }

    public SmartAlbumAdapter getSmartAlbumAdapter() {
        return smartAlbumAdapter;
    }

    public void setSmartAlbumAdapter(SmartAlbumAdapter smartAlbumAdapter) {
        this.smartAlbumAdapter = smartAlbumAdapter;
    }
}
