package ruc.team.smartalbum.Database;

/**
 * Created by 汪雅婧 on 2017/3/10.
 */

public class Image {
    private int imageId;
    private String imagePath;
    private String imageFeature;
    private String imageStatus;

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImageFeature() {
        return imageFeature;
    }

    public String getImageStatus() {
        return imageStatus;
    }

    public void setImageFeature(String imageFeature) {
        this.imageFeature = imageFeature;
    }

    public void setImageStatus(String imageStatus) {
        this.imageStatus = imageStatus;
    }
}
