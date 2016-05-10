package ruc.team.smartalbum;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by 边园 on 2016/5/9.
 */
public class ManyGridViewAdapter extends BaseAdapter {
    private Context context;
    private ImageLoader imageLoader;
    private DisplayImageOptions options;

    private Show show;

    public ManyGridViewAdapter(Context context, ImageLoader imageLoader, DisplayImageOptions options) {
        this.context = context;
        this.imageLoader = imageLoader;
        this.options = options;
    }

    public void initData(Show show) {
        this.show = show;
    }

    @Override
    public int getCount() {
        return show.getCount();
    }

    @Override
    public Object getItem(int position) {
        return show.getPath(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.album_imageview, null);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.album_image);

        this.imageLoader.displayImage(show.getPath(position), imageView, this.options);
        return imageView;
    }
}
