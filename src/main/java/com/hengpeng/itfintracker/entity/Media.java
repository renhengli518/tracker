package com.hengpeng.itfintracker.entity;

public class Media {
    private Integer id;

    private String medianame;

    private String mediacode;

    private String unionlv1;

    private String unionlv2;

    private String unionlv3;

    private Integer type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMedianame() {
        return medianame;
    }

    public void setMedianame(String medianame) {
        this.medianame = medianame;
    }

    public String getMediacode() {
        return mediacode;
    }

    public void setMediacode(String mediacode) {
        this.mediacode = mediacode;
    }

    public String getUnionlv1() {
        return unionlv1;
    }

    public void setUnionlv1(String unionlv1) {
        this.unionlv1 = unionlv1;
    }

    public String getUnionlv2() {
        return unionlv2;
    }

    public void setUnionlv2(String unionlv2) {
        this.unionlv2 = unionlv2;
    }

    public String getUnionlv3() {
        return unionlv3;
    }

    public void setUnionlv3(String unionlv3) {
        this.unionlv3 = unionlv3;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}