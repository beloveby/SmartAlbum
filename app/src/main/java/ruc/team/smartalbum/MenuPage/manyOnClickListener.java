package ruc.team.smartalbum.MenuPage;

import android.content.Context;
import android.view.View;

import ruc.team.smartalbum.TempData;

/**
 * Created by 边园 on 2017/1/30.
 */

public class manyOnClickListener implements View.OnClickListener {
    private Context context;
    private AlbumActivityNode fatherNode;

    private SmartAlbumAdapter smartAlbumAdapter;
    private CallBack mCallBack;

    public interface CallBack {
        void trace(AlbumActivityNode albumActivityNode);
    }


    public manyOnClickListener(Context context, CallBack callBack) {
        this.context = context;
        this.mCallBack = callBack;
    }

    @Override
    public void onClick(View v) {
        int clickId = TempData.getChildId(fatherNode.getId(), (Integer) v.getTag());
        this.fatherNode.setFatherNode(this.fatherNode.getId());
        this.fatherNode.setId(clickId);
        this.fatherNode.setHasChild(TempData.setHasChild(this.fatherNode.getId()));
        this.fatherNode.setName(TempData.setName(this.fatherNode.getId()));

        this.smartAlbumAdapter.setData(fatherNode);
        this.smartAlbumAdapter.notifyDataSetChanged();

        this.mCallBack.trace(this.fatherNode);
    }

    public AlbumActivityNode getFatherNode() {
        return fatherNode;
    }

    public void setFatherNode(AlbumActivityNode fatherNode) {
        this.fatherNode = fatherNode;
    }

    public SmartAlbumAdapter getSmartAlbumAdapter() {
        return smartAlbumAdapter;
    }

    public void setSmartAlbumAdapter(SmartAlbumAdapter smartAlbumAdapter) {
        this.smartAlbumAdapter = smartAlbumAdapter;
    }
}
