package net.kmfish.sample.model

/**
 * Created by lijun on 16/8/4.
 */
class ImageModel(var name: String, var imgResId: Int) {

    override fun toString(): String {
        return "ImageModel{" +
                "name='" + name + '\'' +
                ", imgResId=" + imgResId +
                '}'
    }
}
