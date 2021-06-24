package kr.co.ymlee;

import org.openqa.selenium.WebDriver;


/**
 *
 * @author ymlee
 *
 */
public class SiteSearchInfo {

	private int searchTime;
	private int succSleepTime;
	private int errorCount = 0;

	SiteInfo siteInfo = new SiteInfo();
	WebDriver driver = null;

	public int getSearchTime() {
		return searchTime;
	}

	public void setSearchTime(int searchTime) {
		this.searchTime = searchTime;
	}

	public int getSuccSleepTime() {
		return succSleepTime;
	}

	public void setSuccSleepTime(int succSleepTime) {
		this.succSleepTime = succSleepTime;
	}

	public SiteInfo getSiteInfo() {
		return siteInfo;
	}

	public void setSiteInfo(SiteInfo siteInfo) {
		this.siteInfo = siteInfo;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public int getErrorCount() {
		return errorCount;
	}

	public void setErrorCount(int errorCount) {
		this.errorCount = errorCount;
	}
	public void addErrorCount() {
		this.errorCount++;
	}


//	String paramSearchDay = "20150207,20150214"; //검색일
//	String paramSearchYm = "201502"; //검색 년월
//	String paramSearchGroupCode = "0001,0002,0005,0006"; //찾을 그룹 코드
//	String paramSearchPeriod = ""; //예약기간
//	String paramReverseYn = "N"; //예약여부


}
