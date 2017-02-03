package ruc.team.smartalbum.MenuPage;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.ByteArrayOutputStream;

import ruc.team.smartalbum.ImageDisplayPage.ImageDisplayActivity;
import ruc.team.smartalbum.R;
import ruc.team.smartalbum.Util.ImageProcessUtil;

/**
 * Created by 边园 on 2017/1/24.
 */

public class OneGridViewAdapter extends BaseAdapter implements View.OnClickListener {
    private Context context;
    private ImageLoader imageLoader;
    private DisplayImageOptions options;

    private Show show;

    public OneGridViewAdapter(Context context, ImageLoader imageLoader, DisplayImageOptions options) {
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
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView view = (ImageView) v;
                Toast.makeText(context, "path is " + view.getDrawable(), Toast.LENGTH_LONG).show();

                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                Intent intent = new Intent(context, ImageDisplayActivity.class);
                Bundle bundle = new Bundle();

                bundle.putInt("from", ImageDisplayActivity.MENU);
                bundle.putString("drawable", ImageProcessUtil.drawableToByte(view.getDrawable()));
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        return imageView;
    }

    @Override
    public void onClick(View v) {
        ImageView view = (ImageView) v;
        Toast.makeText(context, "path is " + view.getDrawable(), Toast.LENGTH_LONG);
    }
}
