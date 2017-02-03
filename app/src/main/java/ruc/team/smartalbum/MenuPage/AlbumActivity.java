package ruc.team.smartalbum.MenuPage;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ruc.team.smartalbum.ImageDisplayPage.ImageDisplayActivity;
import ruc.team.smartalbum.R;
import ruc.team.smartalbum.TempData;

public class AlbumActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, manyOnClickListener.CallBack {

    private boolean isInitPage;
    private List<AlbumActivityNode> historyLog;
    private ListView listView;
    private TextView textView;
    private SmartAlbumAdapter smartAlbumAdapter;

    private Uri imageUri;
    private static final int CAMERA = 1;

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

        this.textView = (TextView) findViewById(R.id.classname);

        this.listView = (ListView) findViewById(R.id.smart_album);
        this.smartAlbumAdapter = new SmartAlbumAdapter(this, textView, this);

        this.listView.setAdapter(smartAlbumAdapter);

        AlbumActivityNode label = new AlbumActivityNode();
        label.setOrganizeMode(SmartAlbumAdapter.OrganizeMode.LABEL);
        label.setId(id);
        label.setFatherNode(TempData.setFatherNode(id));
        label.setHasChild(TempData.setHasChild(id));
        label.setName(TempData.setName(id));

        changeShow(label);

        Toast.makeText(this, id + "", Toast.LENGTH_SHORT).show();

        this.isInitPage = true;
        this.historyLog = new ArrayList<AlbumActivityNode>();
        AlbumActivityNode historyPiece = new AlbumActivityNode();
        historyPiece.copy(label);
        this.historyLog.add(historyPiece);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (!this.isInitPage) {
            int size = this.historyLog.size();
            AlbumActivityNode node = this.historyLog.remove(size - 1);

            AlbumActivityNode historyPiece = new AlbumActivityNode();
            historyPiece.copy(this.historyLog.get(size - 2));

            this.smartAlbumAdapter.setData(historyPiece);
            if (size == 2) {
                this.isInitPage = true;
            }
            this.smartAlbumAdapter.notifyDataSetChanged();
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
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
            Date date = new Date(System.currentTimeMillis());
            String filename = format.format(date);
            //创建File对象用于存储拍照的图片 SD卡根目录
            //File outputImage = new File(Environment.getExternalStorageDirectory(),test.jpg);
            //存储至DCIM文件夹
            //File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);File storageDir = this.getExternalFilesDir(null);
            File path = this.getExternalFilesDir(null);
            File outputImage = new File(path, filename + ".jpg");
            try {
                if (outputImage.exists()) {
                    outputImage.delete();
                }
                outputImage.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //将File对象转换为Uri并启动照相程序
            this.imageUri = Uri.fromFile(outputImage);
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE"); //照相
            intent.putExtra(MediaStore.EXTRA_OUTPUT, this.imageUri); //指定图片输出地址
            startActivityForResult(intent, this.CAMERA);
        } else if (id == R.id.nav_label) {

        } else if (id == R.id.nav_file) {

        } else if (id == R.id.nav_time) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void changeShow(AlbumActivityNode fatherNode) {
        this.smartAlbumAdapter.setData(fatherNode);
        this.smartAlbumAdapter.notifyDataSetChanged();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case CAMERA:
                if (resultCode == RESULT_OK) {
                    String picture = this.imageUri.toString();
                    Intent intent = new Intent(this.getApplicationContext(), ImageDisplayActivity.class);

                    Bundle bundle = new Bundle();

                    bundle.putInt("from", ImageDisplayActivity.CAMERA);
                    bundle.putString("uri", picture);
                    intent.putExtras(bundle);
                    this.startActivity(intent);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void trace(AlbumActivityNode albumActivityNode) {
        AlbumActivityNode historyPiece = new AlbumActivityNode();
        historyPiece.copy(albumActivityNode);
        this.historyLog.add(historyPiece);
        this.isInitPage = false;
    }
}
