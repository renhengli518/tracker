package com.hengpeng.itfintracker.dto;

import java.io.Serializable;
import java.util.Date;

public class MediaAccessCensusDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private String mediaName;
	private int id;
	private String unionlv1;
	private int pv;
	private int uv;
	private int orderpv;
	private int secondclickcount;
	private int accessdepth;
	private Date date;
	private String stringDate;

	public String getMediaName() {
		return mediaName;
	}

	public void setMediaName(String mediaName) {
		this.mediaName = mediaName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUnionlv1() {
		return unionlv1;
	}

	public void setUnionlv1(String unionlv1) {
		this.unionlv1 = unionlv1;
	}

	public int getPv() {
		return pv;
	}

	public void setPv(int pv) {
		this.pv = pv;
	}

	public int getUv() {
		return uv;
	}

	public void setUv(int uv) {
		this.uv = uv;
	}

	public int getOrderpv() {
		return orderpv;
	}

	public void setOrderpv(int orderpv) {
		this.orderpv = orderpv;
	}

	public int getSecondclickcount() {
		return secondclickcount;
	}

	public void setSecondclickcount(int secondclickcount) {
		this.secondclickcount = secondclickcount;
	}

	public int getAccessdepth() {
		return accessdepth;
	}

	public void setAccessdepth(int accessdepth) {
		this.accessdepth = accessdepth;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getStringDate() {
		return stringDate;
	}

	public void setStringDate(String stringDate) {
		this.stringDate = stringDate;
	}

}
