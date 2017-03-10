package ruc.team.smartalbum.ImageProcess;

import android.os.Environment;

import java.io.File;

import ruc.team.smartalbum.CaffeLib.CaffeMobile;

/**
 * Created by 边园 on 2017/3/10.
 */

public class CaffeOperation {
    private CaffeMobile caffeMobile;

    File sdcard = Environment.getExternalStorageDirectory();
    String modelDir = sdcard.getAbsolutePath() + "/caffe_mobile/bvlc_reference_caffenet";
    String modelProto = modelDir + "/deploy.prototxt";
    String modelBinary = modelDir + "/bvlc_reference_caffenet.caffemodel";

    static{
        System.loadLibrary("caffe");
        System.loadLibrary("caffe_jni");
    }

    public CaffeOperation(){
        this.caffeMobile = new CaffeMobile();
        caffeMobile.setNumThreads(4);
        caffeMobile.loadModel(modelProto, modelBinary);

        float[] meanValues = {(float) 103.939, (float) 116.779, (float) 123.68};
        caffeMobile.setMean(meanValues);

    }

    public void ExtractFeature(byte[] img){
        this.caffeMobile.extractFeatures(img, 224, 224, "pool5/7x7_s1");
    }
}
