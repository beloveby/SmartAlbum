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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AlbumActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, manyOnClickListener.CallBack {

    private boolean isInitPage;
    private List<AlbumActivityNode> historyLog;
    private ListView listView;
    private TextView textView;
    private SmartAlbumAdapter smartAlbumAdapter;

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

    public void changeShow(AlbumActivityNode fatherNode) {
        this.smartAlbumAdapter.setData(fatherNode);
        this.smartAlbumAdapter.notifyDataSetChanged();
    }

    @Override
    public void trace(AlbumActivityNode albumActivityNode){
        AlbumActivityNode historyPiece = new AlbumActivityNode();
        historyPiece.copy(albumActivityNode);
        this.historyLog.add(historyPiece);
        this.isInitPage = false;
    }
}
