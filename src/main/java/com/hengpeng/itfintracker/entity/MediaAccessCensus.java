package com.hengpeng.itfintracker.entity;

import java.util.Date;

public class MediaAccessCensus {
    private Integer id;

    private String unionlv1;

    private Integer pv;

    private Integer uv;

    private Integer orderpv;

    private Integer secondclickcount;

    private Integer accessdepth;

    private Date date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUnionlv1() {
        return unionlv1;
    }

    public void setUnionlv1(String unionlv1) {
        this.unionlv1 = unionlv1;
    }

    public Integer getPv() {
        return pv;
    }

    public void setPv(Integer pv) {
        this.pv = pv;
    }

    public Integer getUv() {
        return uv;
    }

    public void setUv(Integer uv) {
        this.uv = uv;
    }

    public Integer getOrderpv() {
        return orderpv;
    }

    public void setOrderpv(Integer orderpv) {
        this.orderpv = orderpv;
    }

    public Integer getSecondclickcount() {
        return secondclickcount;
    }

    public void setSecondclickcount(Integer secondclickcount) {
        this.secondclickcount = secondclickcount;
    }

    public Integer getAccessdepth() {
        return accessdepth;
    }

    public void setAccessdepth(Integer accessdepth) {
        this.accessdepth = accessdepth;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}