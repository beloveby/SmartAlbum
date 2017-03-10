package ruc.team.smartalbum.ImageProcess;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import org.opencv.core.Mat;

import ruc.team.smartalbum.Util.*;

public class ImageProcessService extends Service {

    public ImageProcessService() {
        Log.v("SERVICE_TEST", "this is service");
    }

    public void fortest(){
        Toast.makeText(this.getApplicationContext(), "Hello World Local Service!", Toast.LENGTH_SHORT).show();
    }

    /**
     * check if the image process is completed
     * @return boolean, if completed, return true; if not, then false
     */
    public boolean StateCheck(){
        //比较系统变量中  最后一张检测的图片id  与  系统相册中 最后一张图片id， 如果不同 开始检测工作
        //from database class  get the comparison
    return false;
}

    public void ProcessImage(){
        //from database, get the last photo id
        //get photo url
        String url = "";
        byte[][][] output = ImgProcessUtil.ImagePreProcess(url);

        //do caffe work

        while(true) {
            //from database,get the param of logistic
            //do logistic - - -
        }

        //save the result to database

        //when finish, update the database
        //update()
        //if(!StateCheck())
         //   ProcessImage();
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
