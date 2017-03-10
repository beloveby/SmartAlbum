package ruc.team.smartalbum.ImageProcess;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class ImageProcessService extends Service {

    public ImageProcessService() {
        Log.v("SERVICE_TEST", "this is service");
    }

    public void fortest(){
        Toast.makeText(this.getApplicationContext(), "Hello World Local Service!", Toast.LENGTH_SHORT).show();
    }

    public void StateCheck(){

    }

    public void ProcessImage(){

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return new ImageProcessBinder();
    }

    public class ImageProcessBinder extends Binder {
        public ImageProcessService getService() {
            // Return this instance of LocalService so clients can call public methods
            return ImageProcessService.this;
        }
    }
}
