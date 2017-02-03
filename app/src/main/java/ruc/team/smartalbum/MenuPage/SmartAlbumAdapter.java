package ruc.team.smartalbum.MenuPage;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.listener.PauseOnScrollListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ruc.team.smartalbum.R;
import ruc.team.smartalbum.TempData;

/**
 * Created by 边园 on 2016/5/9.
 */
public class SmartAlbumAdapter extends BaseAdapter {
    public enum OrganizeMode {
        LABEL, FILE, TIME;
    } // to determine show in file, label position or time

    public enum ShowMode {
        MANY, ONE;
    }// to determine one-many or just one label

    private TextView fatherNodeView;
    private Context context;

    private OrganizeMode mode1;
    private ShowMode mode2;
    private AlbumActivityNode fatherNode;
    private boolean isShowModeChanged;

    private int itemCount;
    private List<AlbumActivityNode> labelList;
    private List<Show> showList;

    private manyOnClickListener onClickListener;

    private DisplayImageOptions options;
    private ImageLoader imageLoader;

    public SmartAlbumAdapter(final Context context, TextView fatherNode, manyOnClickListener.CallBack callBack) {
        this.context = context;
        fatherNodeView = fatherNode;
        itemCount = 0;
        onClickListener = new manyOnClickListener(context, callBack);
        isShowModeChanged = false;

        ImageLoaderConfiguration configuration = ImageLoaderConfiguration
                .createDefault(context);
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.ic_stub)
                .showImageOnFail(R.drawable.ic_error)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
        ImageLoader.getInstance().init(configuration);
        imageLoader = ImageLoader.getInstance();
    }

    public void setData(AlbumActivityNode fatherNode) {
        this.fatherNode = fatherNode;

        this.updateData();
    }

    @Override
    public int getCount() {
        return itemCount;
    }

    @Override
    public Object getItem(int position) {
        AlbumActivityNode Label = labelList.get(position);
        Show show = showList.get(position);

        Map<String, Object> map = new HashMap<>();
        map.put("Label", Label);
        map.put("show", show);

        return map;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (itemCount == 0)
            return null;
        else if (this.mode2.equals(ShowMode.ONE)) {
            OneHolder holder = null;

            if (convertView == null | this.isShowModeChanged) {
                convertView = LayoutInflater.from(context).inflate(R.layout.album_one, null);
                holder = new OneHolder();

                holder.gridView = (GridView) convertView.findViewById(R.id.album_one_gridview);

                convertView.setTag(holder);
            } else
                holder = (OneHolder) convertView.getTag();

            OneGridViewAdapter adapter = new OneGridViewAdapter(context, imageLoader, options);
            adapter.initData(showList.get(position));
            holder.gridView.setOnScrollListener(new PauseOnScrollListener(imageLoader, true, false));
            holder.gridView.setAdapter(adapter);

            return convertView;
        } else if (this.mode2.equals(ShowMode.MANY)) {
            ManyHolder holder = null;

            if (convertView == null | this.isShowModeChanged) {
                convertView = LayoutInflater.from(context).inflate(R.layout.album_many, null);
                holder = new ManyHolder();

                holder.textView = (TextView) convertView.findViewById(R.id.album_tabs);
                holder.textView.setTag(position);
                onClickListener.setFatherNode(fatherNode);
                holder.gridView = (GridView) convertView.findViewById(R.id.album_many_gridview);

                convertView.setTag(holder);
            } else {
                holder = (ManyHolder) convertView.getTag();
            }

            holder.textView.setText(this.labelList.get(position).getName());

            ManyGridViewAdapter adapter = new ManyGridViewAdapter(context, imageLoader, options);
            adapter.initData(showList.get(position));
            holder.gridView.setOnScrollListener(new PauseOnScrollListener(imageLoader, true, false));
            holder.gridView.setAdapter(adapter);

            onClickListener.setSmartAlbumAdapter(this);
            holder.textView.setOnClickListener(onClickListener);

            return convertView;
        } else
            return null;
    }

    private void updateData() { // supposed to access data in database
        SmartAlbumAdapter.ShowMode showMode;

        if (fatherNode.isHasChild()) {
            showMode = SmartAlbumAdapter.ShowMode.MANY;
            if (!showMode.equals(this.mode2)) {
                this.isShowModeChanged = true;
            }
            this.mode2 = showMode;
            this.showList = TempData.manyShow(fatherNode.getId());
            this.labelList = TempData.manyLabel(fatherNode.getId());
        } else {
            showMode = SmartAlbumAdapter.ShowMode.ONE;
            if (!showMode.equals(this.mode2)) {
                this.isShowModeChanged = true;
            }
            this.mode2 = showMode;
            this.showList = TempData.oneShow(fatherNode.getId());
        }
        this.itemCount = TempData.getCount(fatherNode.getId());
        this.fatherNodeView.setText(this.fatherNode.getName());
    }

    private class ManyHolder {
        public TextView textView;
        public GridView gridView;
    }

    private class OneHolder {
        public GridView gridView;
    }
}
