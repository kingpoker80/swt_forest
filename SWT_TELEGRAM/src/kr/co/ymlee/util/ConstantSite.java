package kr.co.ymlee.util;

public enum ConstantSite {


	//안면도 ID02030086, 강씨봉 ID02030019, 고대산 ID02030001
	
	
	ANMYUN("안면도", "ID02030086"),
	GANGSSI("강씨봉", "ID02030019"),
	GODAE("고대산", "ID02030001"),
	KALBONG("칼봉산", "ID02030099"),
	SINSIDO("신시도", "0301")
	;
	
	private ConstantSite(String siteName, String siteCode){
		this.siteName = siteName;
		this.siteCode = siteCode;
	}	
	private String siteCode;
	private String siteName;
	public String getSiteCode() {
		return siteCode;
	}
	public String getSiteName() {
		return siteName;
	}
	
	
}
