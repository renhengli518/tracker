package com.hengpeng.itfintracker.entity;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.hengpeng.itfintracker.commons.utils.ID;
import com.hengpeng.itfintracker.commons.utils.LongDateSerializer;

public class UserBehaviorRecord implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 175368790131682651L;

	private Long id;

	private String buttonposition;

	private String linkposition;

	private String viewtype;

	private String newuserflag;

	private String sessionid;

	private String enduserid;

	private String ip;

	@JsonSerialize(using = LongDateSerializer.class)
	private Date clienttime;

	private String userurgent;

	private String pageurl;

	private String country;

	private String province;

	private String city;

	private String staytime;

	private Long staytimemilseconds;

	private String pagetitle;

	private String fromwhere;

	private String clientsystem;

	private String clientresolution;

	private String clientpagetype;

	private String serachkeywords;

	private String stringdate;

	private String refferpage;

	private String browsertype;

	private String browserversion;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getButtonposition() {
		return buttonposition;
	}

	public void setButtonposition(String buttonposition) {
		this.buttonposition = buttonposition;
	}

	public String getLinkposition() {
		return linkposition;
	}

	public void setLinkposition(String linkposition) {
		this.linkposition = linkposition;
	}

	public String getViewtype() {
		return viewtype;
	}

	public void setViewtype(String viewtype) {
		this.viewtype = viewtype;
	}

	public String getNewuserflag() {
		return newuserflag;
	}

	public void setNewuserflag(String newuserflag) {
		this.newuserflag = newuserflag;
	}

	public String getSessionid() {
		return sessionid;
	}

	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}

	public String getEnduserid() {
		return enduserid;
	}

	public void setEnduserid(String enduserid) {
		this.enduserid = enduserid;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getClienttime() {
		return clienttime;
	}

	public void setClienttime(Date clienttime) {
		this.clienttime = clienttime;
	}

	public String getUserurgent() {
		return userurgent;
	}

	public void setUserurgent(String userurgent) {
		this.userurgent = userurgent;
	}

	public String getPageurl() {
		return pageurl;
	}

	public void setPageurl(String pageurl) {
		this.pageurl = pageurl;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStaytime() {
		return staytime;
	}

	public void setStaytime(String staytime) {
		this.staytime = staytime;
	}

	public Long getStaytimemilseconds() {
		return staytimemilseconds;
	}

	public void setStaytimemilseconds(Long staytimemilseconds) {
		this.staytimemilseconds = staytimemilseconds;
	}

	public String getPagetitle() {
		return pagetitle;
	}

	public void setPagetitle(String pagetitle) {
		this.pagetitle = pagetitle;
	}

	public String getFromwhere() {
		return fromwhere;
	}

	public void setFromwhere(String fromwhere) {
		this.fromwhere = fromwhere;
	}

	public String getClientsystem() {
		return clientsystem;
	}

	public void setClientsystem(String clientsystem) {
		this.clientsystem = clientsystem;
	}

	public String getClientresolution() {
		return clientresolution;
	}

	public void setClientresolution(String clientresolution) {
		this.clientresolution = clientresolution;
	}

	public String getClientpagetype() {
		return clientpagetype;
	}

	public void setClientpagetype(String clientpagetype) {
		this.clientpagetype = clientpagetype;
	}

	public String getSerachkeywords() {
		return serachkeywords;
	}

	public void setSerachkeywords(String serachkeywords) {
		this.serachkeywords = serachkeywords;
	}

	public String getStringdate() {
		return stringdate;
	}

	public void setStringdate(String stringdate) {
		this.stringdate = stringdate;
	}

	public String getRefferpage() {
		return refferpage;
	}

	public void setRefferpage(String refferpage) {
		this.refferpage = refferpage;
	}

	public String getBrowsertype() {
		return browsertype;
	}

	public void setBrowsertype(String browsertype) {
		this.browsertype = browsertype;
	}

	public String getBrowserversion() {
		return browserversion;
	}

	public void setBrowserversion(String browserversion) {
		this.browserversion = browserversion;
	}

	/**
	 * 构造方法
	 * 
	 * @param sessionId
	 * @param uuid
	 * @param memberId
	 * @param refPage
	 * @param firstPage
	 * @param initTime
	 * @param orderId
	 * @param currentTime
	 * @param currentPage
	 * @param isNewVisit
	 */

	public UserBehaviorRecord() {
		super();
	}

	/**
	 * 构造方法
	 * 
	 * @param buttonPosition
	 * @param linkPosition
	 * @param viewType
	 * @param ip
	 * @param sessionId
	 * @param endUserId
	 * @param clientTime
	 * @param newUserFlag
	 * @param userurgent
	 * @param pageUrl
	 * @param country
	 * @param province
	 * @param city
	 * @param stayTime
	 * @param stayTimeMilSeconds
	 * @param pageTitle
	 * @param refferPage
	 * @param clientSystem
	 * @param clientResolution
	 * @param clientPageType
	 * @param fromWhere
	 * @param serachKeyWords
	 * @param stringDate
	 */
	public UserBehaviorRecord(String buttonPosition, String linkPosition, String viewType, String ip, String sessionId, String endUserId,
			Date clientTime, String newUserFlag, String userurgent, String pageUrl, String country, String province, String city, String stayTime,
			Long stayTimeMilSeconds, String pageTitle, String refferPage, String clientSystem, String clientResolution, String clientPageType,
			String fromWhere, String serachKeyWords, String stringDate, String browserType, String browserVersion) {
		super();
		this.id = ID.getInstanse().getID(18);
		this.buttonposition = buttonPosition;
		this.linkposition = linkPosition;
		this.viewtype = viewType;
		this.ip = ip;
		this.sessionid = sessionId;
		this.enduserid = endUserId;
		this.clienttime = clientTime;
		this.newuserflag = newUserFlag;
		this.userurgent = userurgent;
		this.pageurl = pageUrl;
		this.country = country;
		this.province = province;
		this.city = city;
		this.staytime = stayTime;
		this.staytimemilseconds = stayTimeMilSeconds;
		this.pagetitle = pageTitle;
		this.refferpage = refferPage;
		this.clientsystem = clientSystem;
		this.clientresolution = clientResolution;
		this.clientpagetype = clientPageType;
		this.fromwhere = fromWhere;
		this.serachkeywords = serachKeyWords;
		this.stringdate = stringDate;
		this.browsertype = browserType;
		this.browserversion = browserVersion;
	}
}