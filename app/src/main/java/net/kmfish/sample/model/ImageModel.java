package net.kmfish.sample.model;

/**
 * Created by lijun on 16/8/4.
 */
public class ImageModel {
    public ImageModel(String name, int imgResId) {
        this.name = name;
        this.imgResId = imgResId;
    }

    String name;
    int imgResId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImgResId() {
        return imgResId;
    }

    public void setImgResId(int imgResId) {
        this.imgResId = imgResId;
    }
}
