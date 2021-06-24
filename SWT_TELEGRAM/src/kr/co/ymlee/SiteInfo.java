package kr.co.ymlee;

import kr.co.ymlee.util.ConstantSite;

public class SiteInfo {

	private ConstantSite whatSite;

	private String siteCode;
	private String shopCode;

	private String searchGroupCode;
	private String searchYyyyMm;
	private String searchYyyyMmDd;
	private String searchUrl;

	private String findInfo;
	private boolean isReserve; //예약여부

	private String loginId;
	private String loginPwd;
	
	private String chatId;

	public String getSiteCode() {
		return siteCode;
	}
	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}
	public String getShopCode() {
		return shopCode;
	}
	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}
	public String getSearchGroupCode() {
		return searchGroupCode;
	}
	public void setSearchGroupCode(String searchGroupCode) {
		this.searchGroupCode = searchGroupCode;
	}
	public String getSearchYyyyMm() {
		return searchYyyyMm;
	}
	public void setSearchYyyyMm(String searchYyyyMm) {
		this.searchYyyyMm = searchYyyyMm;
	}
	public String getSearchYyyyMmDd() {
		return searchYyyyMmDd;
	}
	public void setSearchYyyyMmDd(String searchYyyyMmDd) {
		this.searchYyyyMmDd = searchYyyyMmDd;
	}
	public String getFindInfo() {
		return findInfo;
	}
	public void setFindInfo(String findInfo) {
		this.findInfo = findInfo;
	}
	public ConstantSite getWhatSite() {
		return whatSite;
	}
	public void setWhatSite(ConstantSite whatSite) {
		this.whatSite = whatSite;
		this.siteCode = whatSite.getSiteCode();
	}
	public boolean isReserve() {
		return isReserve;
	}
	public void setReserve(boolean isReserve) {
		this.isReserve = isReserve;
	}
	public String getSearchUrl() {
		return searchUrl;
	}
	public void setSearchUrl(String searchUrl) {
		this.searchUrl = searchUrl;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getLoginPwd() {
		return loginPwd;
	}
	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}
	public String getChatId() {
		return chatId;
	}
	public void setChatId(String chatId) {
		this.chatId = chatId;
	}




}
