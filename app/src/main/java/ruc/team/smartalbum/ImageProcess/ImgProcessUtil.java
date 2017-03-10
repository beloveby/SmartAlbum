package ruc.team.smartalbum.ImageProcess;

import android.graphics.BitmapFactory;

import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.utils.Converters;

/**
 * Created by 边园 on 2017/3/9.
 */

public class ImgProcessUtil {
    public static byte[][][] ImagePreProcess(String imageUrl){
        Mat input = org.opencv.imgcodecs.Imgcodecs.imread(imageUrl);
        Mat output = new Mat();
        Imgproc.resize(input, output, new Size(224, 224), 0, 0, Imgproc.INTER_CUBIC);

        //OpenCV use BGR, no need to change

        //Subtract the VGG data set mean; mean = [103.939, 116.779, 123.68];
        double mean[] = {103.939, 116.779, 123.68};
        byte[][][] finalResult = new byte[224][224][3];
        for(int i = 0; i < output.cols(); i++){
            for(int j = 0; j < output.rows(); j++){
                double temp[] = output.get(i, j);
                for(int k = 0; k < 3; k++) {
                    temp[k] = temp[k] - mean[k];
                    //可能有问题！！byte2double
                    finalResult[i][j][k] = (byte) temp[k];
                }
                output.put(i, j, temp);
            }
        }
        return finalResult;
    }
}
