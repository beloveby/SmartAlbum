package ruc.team.smartalbum;

import android.content.Context;
import android.database.DataSetObserver;
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
    private Label fatherNode;

    private int itemCount;
    private List<Label> labelList;
    private List<Show> showList;

    public SmartAlbumAdapter(Context context, TextView fatherNode) {
        this.context = context;
        fatherNodeView = fatherNode;
        itemCount = 0;
    }

    public void setData(OrganizeMode mode1, ShowMode mode2, Label fatherNode, int count, List<Show> shows, List<Label> labels) {
        this.mode1 = mode1;
        this.mode2 = mode2;
        this.fatherNode = fatherNode;

        this.itemCount = count;
        this.showList = shows;
        this.labelList = labels;
    }

    @Override
    public int getCount() {
        return itemCount;
    }

    @Override
    public Object getItem(int position) {
        Label Label = labelList.get(position);
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
        ImageLoaderConfiguration configuration = ImageLoaderConfiguration
                .createDefault(context);
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.ic_stub)
                .showImageOnFail(R.drawable.ic_error)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
        ImageLoader.getInstance().init(configuration);
        ImageLoader imageLoader = ImageLoader.getInstance();

        if (itemCount == 0)
            return null;
        else if (itemCount == 1) {
            OneHolder holder = null;

            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.album_many, null);
                holder = new OneHolder();

                holder.gridView = (GridView) convertView.findViewById(R.id.album_many_gridview);

                convertView.setTag(holder);
            } else
                holder = (OneHolder) convertView.getTag();

            ManyGridViewAdapter adapter = new ManyGridViewAdapter(context, imageLoader, options);
            adapter.initData(showList.get(position));
            holder.gridView.setOnScrollListener(new PauseOnScrollListener(imageLoader, true, false));
            holder.gridView.setAdapter(adapter);

            return convertView;
        } else if (itemCount > 1) {
            ManyHolder holder = null;

            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.album_many, null);
                holder = new ManyHolder();

                holder.textView = (TextView) convertView.findViewById(R.id.album_tabs);
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

            return convertView;
        } else
            return null;
    }

    private class ManyHolder {
        public TextView textView;
        public GridView gridView;
    }

    private class OneHolder {
        public GridView gridView;
    }
}
