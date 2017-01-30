package ruc.team.smartalbum;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;

public class ScrollingActivity extends AppCompatActivity {

    private SharedPreferences preferences;
    private ServiceConnection serviceConnection = null;
    static final String TAG = "SmartAlbum";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Context context = this;
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            private boolean buttonGroupOpen = false;

            @Override
            public void onClick(View view) {

                FloatingActionButton time = (FloatingActionButton) findViewById(R.id.btn_group_time);
                FloatingActionButton label = (FloatingActionButton) findViewById(R.id.btn_group_label);
                FloatingActionButton file = (FloatingActionButton) findViewById(R.id.btn_group_file);

                if (buttonGroupOpen) {
                    ButtonGroupAnimUtil.closeMenu(time, label, file);
                    buttonGroupOpen = false;
                } else {
                    ButtonGroupAnimUtil.showMenu(time, label, file);
                    buttonGroupOpen = true;
                }
            }
        });

        FloatingActionButton time = (FloatingActionButton) findViewById(R.id.btn_group_time);
        FloatingActionButton file = (FloatingActionButton) findViewById(R.id.btn_group_file);
        FloatingActionButton label = (FloatingActionButton) findViewById(R.id.btn_group_label);

        time.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

            }
        });

        ExpandableListView expandableListView = (ExpandableListView) findViewById(R.id.menu);
        myExpandableListAdapter adapter = new myExpandableListAdapter(expandableListView.getContext());

        initialSystem();

        initData(adapter);

        expandableListView.setAdapter(adapter);
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Intent intent = new Intent(context, AlbumActivity.class);

                Bundle bundle = new Bundle();

                int mid = TempData.getId(groupPosition, childPosition);
                bundle.putInt("id", mid);
                intent.putExtras(bundle);

                context.startActivity(intent);
                return true;
            }
        });

    }

    private void initialSystem() {
        preferences = getSharedPreferences("smart_album", Context.MODE_PRIVATE);
        if (preferences.getBoolean("IsFirstStart", true)) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("isFirstStart", false);
            editor.commit();

            //other operations, start page or the preparation for image processor
        }

        //check database

        //start service
        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.d(TAG, "service connected");
                ImageProcess imageProcess = ((ImageProcess.ImageProcessBinder) service).getService();
                imageProcess.fortest();
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.d(TAG, "service disconnected");
            }
        };

        Intent service = new Intent(this.getApplicationContext(), ImageProcess.class);
        this.bindService(service, this.serviceConnection, Context.BIND_AUTO_CREATE);

    }

    private void initData(myExpandableListAdapter adapter) {
        adapter.init(TempData.logos, TempData.itemlogos, TempData.armTypes, TempData.arms);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
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

    @Override
    public void onStop() {
        super.onStop();
        if (this.serviceConnection != null) {
            this.unbindService(this.serviceConnection);
            this.serviceConnection = null;
        }
    }
}
