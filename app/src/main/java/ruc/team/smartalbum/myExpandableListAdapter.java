package ruc.team.smartalbum;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by 边园 on 2016/5/7.
 */
public class myExpandableListAdapter implements ExpandableListAdapter {
    private int logos[];
    private int itemLogos[][];

    private String[] ImageClass;

    private String[][] ImageTabs;

    private Context context;

    public myExpandableListAdapter(Context context) {
        this.context = context;
    }

    public void init(int[] logos, int[][] itemLogos, String[] imageClass, String[][] imageTabs) {
        this.setLogos(logos);
        this.setItemLogos(itemLogos);
        this.setImageClass(imageClass);
        this.setImageTabs(imageTabs);
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getGroupCount() {
        return ImageClass.length;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return ImageTabs[groupPosition].length;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return ImageClass[groupPosition];
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return ImageTabs[groupPosition][childPosition];
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupHolder groupHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.expendlist_group, null);
            groupHolder = new GroupHolder();
            groupHolder.txt = (TextView) convertView.findViewById(R.id.txt);
            groupHolder.img = (ImageView) convertView.findViewById(R.id.img);
            groupHolder.enter = (ImageView) convertView.findViewById(R.id.enter);
            convertView.setTag(groupHolder);
        } else {
            groupHolder = (GroupHolder) convertView.getTag();
        }

        groupHolder.img.setImageResource(logos[groupPosition]);
        groupHolder.enter.setImageResource(R.drawable.enter);
        groupHolder.txt.setText(getGroup(groupPosition).toString());
        groupHolder.enter.setOnClickListener(new OnClickListenerWithParam(getGroup(groupPosition).toString()){
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(context,Album.class);

                Bundle bundle=new Bundle();

                bundle.putString("name", this.getmParam().toString());
                intent.putExtras(bundle);

                context.startActivity(intent);
            }
        });

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ItemHolder itemHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.expendlist_item, null);
            itemHolder = new ItemHolder();
            itemHolder.txt = (TextView) convertView.findViewById(R.id.txt);
            itemHolder.img = (ImageView) convertView.findViewById(R.id.img);
            convertView.setTag(itemHolder);
        } else {
            itemHolder = (ItemHolder) convertView.getTag();
        }
        itemHolder.txt.setText(getChild(groupPosition, childPosition).toString());
        itemHolder.img.setImageResource(itemLogos[groupPosition][childPosition]);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void onGroupExpanded(int groupPosition) {

    }

    @Override
    public void onGroupCollapsed(int groupPosition) {

    }

    @Override
    public long getCombinedChildId(long groupId, long childId) {
        return 0;
    }

    @Override
    public long getCombinedGroupId(long groupId) {
        return 0;
    }

    public void setImageClass(String[] imageClass) {
        ImageClass = imageClass;
    }

    public void setImageTabs(String[][] imageTabs) {
        ImageTabs = imageTabs;
    }

    private TextView getTextView(Context context) {
        AbsListView.LayoutParams lp = new AbsListView.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, 64);
        TextView textView = new TextView(context);
        textView.setLayoutParams(lp);
        textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
        textView.setPadding(36, 0, 0, 0);
        textView.setTextSize(20);
        return textView;
    }

    public void setLogos(int[] logos) {
        this.logos = logos;
    }

    public void setItemLogos(int[][] itemLogos) {
        this.itemLogos = itemLogos;
    }

    class ItemHolder {
        public ImageView img;
        public TextView txt;
    }

    class GroupHolder {
        public TextView txt;
        public ImageView img;
        public ImageView enter;
    }
}
