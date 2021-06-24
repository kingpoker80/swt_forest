package kr.co.ymlee.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 상수 정의
 * @author ymlee
 *
 */
public class Constants {


	//도덕산
	public static final String CONNECT_URL_DODUKSAN = "https://reserve.gmfmc.or.kr";
	public static final String CONNECT_URL_DODUKSAN_LOGIN = CONNECT_URL_DODUKSAN + "/user/login/login.do?menuFlag=C";
	public static final String CONNECT_URL_DODUKSAN_LOGIN_PROC = CONNECT_URL_DODUKSAN + "/user/login/doLogin.do?ID=%s&PASS=%s";
	public static final String CONNECT_URL_DODUKSAN_LIST = CONNECT_URL_DODUKSAN + "/user/camp/campReservation.do?menu=d&menuFlag=C";
	/*
https://reserve.gmfmc.or.kr/user/login/doLogin.do?ID=id&PASS=pwd
https://reserve.gmfmc.or.kr/user/camp/campReservation.do?menu=d&menuFlag=C
	 */
	public static final String SITE_CODE_DODUKSAN = "G006";
	public static final String[] SITE_TYPE_DODUKSAN_SEARCH_TEXT_LIST = new String[]{
			"A구역", "B구역"
	};
	public static final String[] SITE_TYPE_DODUKSAN_VIEW_TEXT_LIST = new String[]{
			"A구역", "B구역"
	};



	public static String getSiteName(String siteType){
		String resultValue = "없네~";
		if( siteType.equals(SITE_CODE_DODUKSAN) ){ //도덕산
			resultValue = "도덕산";
//		}else if( siteType.equals(SITE_CODE_TOHAM) ){ //토함산
//			resultValue = "토함산";
		}
		return resultValue;
	}
	@SuppressWarnings("unchecked")
	public static Map<String, String>[] getSiteTypeMap(String siteType){
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();

		String[] code = null;
		String[] value = null;

		if( siteType.equals(SITE_CODE_DODUKSAN) ){ //도덕산
			code = SITE_TYPE_DODUKSAN_SEARCH_TEXT_LIST;
			value = SITE_TYPE_DODUKSAN_VIEW_TEXT_LIST;
//		}else if( siteType.equals(SITE_CODE_TOHAM) ){ //토함산
//			code = SITE_TYPE_TOHAM_SEARCH_TEXT_LIST;
//			value = SITE_TYPE_TOHAM_VIEW_TEXT_LIST;
		}

		if( code != null && value != null ){
			for(int i=0; i < code.length; i++){
				Map<String, String> map = new HashMap<String, String>();
				map.put("code", code[i]);
				map.put("value", value[i]);
				list.add(map);
			}
		}

		return (Map[])list.toArray(new HashMap[list.size()]);
	}


	//FCM SERVER 키정보
	public static final String FCM_SERVER_KEY = "AIzaSyAzg-w6jf2WOB9Wvq4ZTQ0oG0j1FOR3LMc";
}
