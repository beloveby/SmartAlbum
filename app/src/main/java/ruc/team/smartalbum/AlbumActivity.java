package ruc.team.smartalbum;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class AlbumActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Bundle bundle = this.getIntent().getExtras();
        int id = bundle.getInt("id");

        TextView textView = (TextView) findViewById(R.id.classname);

        ListView listView = (ListView) findViewById(R.id.smart_album);
        SmartAlbumAdapter smartAlbumAdapter = new SmartAlbumAdapter(this, textView);

        listView.setAdapter(smartAlbumAdapter);

        Label label = new Label();
        label.setId(id);
        label.setFahterNode(TempData.setFatherNode(id));
        label.setHasChild(TempData.setHasChild(id));
        label.setName(TempData.setName(id));

        changeShow(listView, smartAlbumAdapter, textView, SmartAlbumAdapter.OrganizeMode.LABEL, label);

        Toast.makeText(this, id + "", Toast.LENGTH_SHORT).show();
    }

    public void setListViewHeightBasedOnChildren(ListView listView) {
        // 获取ListView对应的Adapter
        SmartAlbumAdapter smartAlbumAdapter = (SmartAlbumAdapter) listView.getAdapter();
        if (smartAlbumAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0, len = smartAlbumAdapter.getCount(); i < len; i++) {
            // listAdapter.getCount()返回数据项的数目
            View listItem = smartAlbumAdapter.getView(i, null, listView);
            // 计算子项View 的宽高
            listItem.measure(0, 0);
            // 统计所有子项的总高度
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight+ (listView.getDividerHeight() * (smartAlbumAdapter.getCount() - 1));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.album, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void changeShow(ListView listView, SmartAlbumAdapter adapter, TextView textView, SmartAlbumAdapter.OrganizeMode organizeMode, Label fatherNode) {

        switch (organizeMode) {
            case LABEL:
                //先手动填充数据，等数据库写完后改
                SmartAlbumAdapter.ShowMode showMode;
                int count = -1;
                List<Show> shows = null;
                List<Label> labels = null;

                if (fatherNode.isHasChild()){
                    showMode = SmartAlbumAdapter.ShowMode.MANY;
                    shows = TempData.manyShow(fatherNode.getId());
                    labels = TempData.manyLabel(fatherNode.getId());
                }
                else{
                    showMode = SmartAlbumAdapter.ShowMode.ONE;
                    shows = TempData.oneShow(fatherNode.getId());
                }
                count = TempData.getCount(fatherNode.getId());

                adapter.setData(SmartAlbumAdapter.OrganizeMode.LABEL, showMode, fatherNode, count, shows, labels);

                textView.setText(fatherNode.getName());
                break;
            case FILE:
                break;
            case TIME:
                break;
        }

        adapter.notifyDataSetChanged();
        setListViewHeightBasedOnChildren(listView);
    }
}
