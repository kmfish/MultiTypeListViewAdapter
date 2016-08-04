package net.kmfish.sample.model;

/**
 * Created by lijun on 16/8/4.
 */
public class TextModel {

    String name;
    String desc;

    public TextModel(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
