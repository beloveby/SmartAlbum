package ruc.team.smartalbum.ImageProcess;

import android.graphics.BitmapFactory;

import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

/**
 * Created by 边园 on 2017/3/9.
 */

public class ImgProcessUtil {
    public void ImagePreProcess(String imageUrl){
        Mat input = org.opencv.imgcodecs.Imgcodecs.imread(imageUrl);
        Mat output = new Mat();
        Imgproc.resize(input, output, new Size(224, 224), 0, 0, Imgproc.INTER_CUBIC);
    }
}
