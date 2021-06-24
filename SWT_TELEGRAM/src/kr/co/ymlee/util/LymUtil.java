package kr.co.ymlee.util;

import java.io.File;
import java.io.Reader;
import java.util.Random;


/**
 *
 * <pre>
 *  서브시스템 : 공통모듈
 *       설명 : 공통 Utility 클래스
 *   최초 작성 : LYM (2009. 04. 06)
 *   변경 작성 :
 * </pre>
 */
public class LymUtil {

    /**
     * CLOB Type의 Data를 Table에서 읽어 온다.
     * @param reader ResultSet에서 읽어온 Clob 객체 ( rs.getCharacterStream(int index) )
     * @return CLOB Data
     */
    public static String getClob(Reader rd)
    	throws Exception{
    	 // CLOB column에 대한 스트림을 얻는다.
		StringBuffer sb = new StringBuffer();
        char[] buf = new char[1024];
        int readcnt;

        if (rd != null) {
	        while ((readcnt = rd.read(buf, 0, 1024)) != -1) {
				// 스트림으로부터 읽어서 스트링 버퍼에 넣는다.
				sb.append(buf, 0, readcnt);
	        }
	        rd.close();
        }

        return sb.toString();

    }

    /**
     * 복수의 파일 삭제
     * @param String path 	삭제할 파일의 경로
     * @param String[] fileName 삭제할 파일명
     */
    public static void deleteFiles(String path, String[] fileName) {
	    for (int i=0 ; i < fileName.length; i++) {
	        deleteFiles(path, fileName[i]);
	    }
    }

    /**
     * 파일 삭제
     * @param String path 	삭제할 파일의 경로
     * @param String fileName 삭제할 파일명
     */
    public static void deleteFiles(String path, String fileName) {
        File file = new File(path+File.separator+fileName);
        file.delete();
    }


	/**
     * null 값이 아닌 string을 주어진 길이 만큼 space 문자를 채운후 반환
     * @param 문자열
     * @param 길이
     * @return 변경된 문자열
     */
	public static String fixString( String str, int len )
	{
		String 		temp1;
		StringBuffer temp;
		int	i,j;

		i = str.length();
		temp = new StringBuffer( str );
		if ( i < len ){
			for ( j = 1; j <= ( len - i ); j ++ ){
				temp.append(" ");
			}
			temp1 = new String(temp);
		} else {
			if ( i > len ) {
				temp1 = str.substring( 0, len );
			} else	temp1 =  str;
		}

		return temp1;
	}


    /**
     * 문자열대 문자열로 바꿔준다.
     * @param line
     * @param oldString
     * @param newString
     * @return
     */
    public static String replace(String line, String oldString, String newString)
    {
        line = getValue(line);
        for(int index = 0; (index = line.indexOf(oldString, index)) >= 0; index += newString.length())
            line = line.substring(0, index) + newString + line.substring(index + oldString.length());

        return line;
    }

    /**
     * 문자열을 받아서 null이면 공백 문자열로 리턴
     * @param str
     * @return
    */
    public static boolean isNull(String str)
    {
        if ((str == null) || (str.trim().equals("")) || (str.trim().equals("null")))
            return true;
        else
            return false;
    }

    public static boolean isNull1(String str)
    {
        if ((str == null) || (str.trim().equals("")) || (str.trim().equals("null")))
            return true;
        else
            return false;
    }


	public static String right(String str, int length) {
		String rtnStr = "";
		if (!isNull1(str) && length > 0 && (str.length() - length >= 0)) {
			rtnStr = str.substring(str.length() - length);
		}
		return rtnStr;
	}
	public static boolean startWith(String str, String chr) {
		if (str.indexOf(chr) == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 입력받은 문자열이 null 이거나 NULL 이거나 "" 이면 원하는 문자열로 변환
	 * @param value 입력받은 문자열
	 * @param defaultValue 변환할 문자열
	 * @return 변환처리된 문자열
	 */
	public static String getValue(Object value) {
		if(value == null) {
			return "";
		} else if(value.toString().equals("null")) {
			return "";
		} else if(value.toString().equals("NULL")) {
			return "";
		} else if(value.toString().equals("")) {
			return "";
		} else {
			return value.toString();
		}
	}
	public static String getValue(String value) {
		if(value == null) {
			return "";
		}
		else if(value.equals("null")) {
			return "";
		}
		else if(value.equals("NULL")) {
			return "";
		}
		else if(value.equals("")) {
			return "";
		}
		else {
			return value;
		}
	}
	public static String getValue(Object value, String defaultValue) {
		if(value == null) {
			return defaultValue;
		}
		else if(value.toString().equals("null")) {
			return defaultValue;
		}
		else if(value.toString().equals("NULL")) {
			return defaultValue;
		}
		else if(value.toString().equals("")) {
			return defaultValue;
		}
		else {
			return value.toString();
		}
	}
	public static String getValue(String value, String defaultValue) {
		if(value == null) {
			return defaultValue;
		}
		else if(value.equals("null")) {
			return defaultValue;
		}
		else if(value.equals("NULL")) {
			return defaultValue;
		}
		else if(value.equals("")) {
			return defaultValue;
		}
		else {
			return value;
		}
	}
	/**
	 * 입력받은 문자열이 NULL, 0이면 넘어온 숫자값으로 대체 한다.
	 * @param value
	 * @param defaultValue
	 * @return
	 */
	public static int getIntValue(Object value) {

		if (value == null ||
			value.equals("null") ||
			value.equals("NULL") ||
			value.equals("") ||
			value.equals("0"))
			return 0;
		else{
			return Integer.parseInt(value.toString());
		}
	}
	public static int getIntValue(String value ) {

		if (value == null ||
			value.equals("null") ||
			value.equals("NULL") ||
			value.equals("") ||
			value.equals("0"))
			return 0;
		else{
			return Integer.parseInt(value.toString());
		}
	}
	public static int getIntValue(String value, int defaultValue ) {

		if (value == null ||
			value.equals("null") ||
			value.equals("NULL") ||
			value.equals("") ||
			value.equals("0"))
			return defaultValue;
		else{
			return Integer.parseInt(value.toString());
		}
	}
	public static int getIntValue(int value, int defaultValue ) {

		if (value == 0)
			return defaultValue;
		else{
			return value;
		}
	}


    /**
     * 문자형 한자리 숫자에 0 + 숫자 리턴한다.
     * @param 문자열
     * @return 변경된 문자열
     */
    public static String plusZero(String number) {

		if(number == null ||
				number.equals("null") ||
				number.equals("NULL") ||
				number.equals("") ||
				number.length() == 0) {
			return "";
		} else {

	        if (number.length() == 1) {
	            return "0"+number;
	        } else {
	            return number;
	        }
		}

    }
    public static String minusZero(String number) {

		if(number == null ||
				number.equals("null") ||
				number.equals("NULL") ||
				number.equals("") ||
				number.length() == 0) {
			return "";
		} else {

	        if (number.length() == 2) {
	        	if(number.substring(0, 1).equals("0"))
	        		return number.substring(1);
	        	else return number;
	        } else {
	            return number;
	        }
		}

    }
    /**
     * 특정 자릿수 만큼 0을 더하여 리턴
     * @param number
     * @param length
     * @return
     *
     * @author LYM
     */
    public static String plusZero(String number, int length) {

		if(number == null ||
				number.equals("null") ||
				number.equals("NULL") ||
				number.equals("") ||
				number.length() == 0) {
			return "";
		} else {

	        if (number.length() == length) {
	        	return number;
	        } else {

	        	String tmpStr = "";
	        	for(int i=0; i< length-number.length();i++)
	        		tmpStr += "0";
	        	return tmpStr+number;
	        }
		}
    }

    /**
     * 문자형 한자리 숫자에 0 + 숫자 리턴한다.
     * @param int
     * @return 변경된 문자열
     */
    public static String plusZero(int number) {
        String temp = String.valueOf(number);
        if (temp.length() == 1) {
            return "0"+temp;
        } else {
            return temp;
        }
    }


    /**
     * 해당 글자의 자리수 0을 채워 반환 하는 메소드
     * @param str
     * @param len
     * @return
     */
	public static String getFillZero(String str, int len){

		if( str == null ) return "";

		String temp1 = "";
		int	i,j;

		i = str.length();
		if ( i < len ){
			for ( j = 1; j <= ( len - i ); j ++ ){
				temp1 += "0";
			}
		}

		return temp1+str;
	}

	/**
	 * 무작위 글자를 원하는 길이 만큼 구하는 메소드
	 * @param mode 알파벳만인지
	 * @param strLength
	 * @return
	 */
	public static String getRandomString(String mode, int strLength){

		Random rnd =new Random();

		StringBuffer buf =new StringBuffer();

		if( "A".equals(mode) ){
			for(int i=0; i < strLength; i++){
				buf.append((char)((int)(rnd.nextInt(26))+65)); //대문자
			}
		}else if( "N".equals(mode) ){
			for(int i=0; i < strLength; i++){
				buf.append((rnd.nextInt(10))); //숫자만
			}
		}else{
			for(int i=0; i < strLength; i++){
			    if(rnd.nextBoolean()){
			    	buf.append((char)((int)(rnd.nextInt(26))+65));
			    }else{
			        buf.append((rnd.nextInt(10)));
			    }
			}
		}


		return buf.toString();

	}



    /**
     * 좌표 데이터를 Geo데이터로 바꿔주는 함수.
     * @param str
     * @param len
     * @return
     */
	public static int getGeoPointData(double location){
		double def = 1e6;
		return (int)(location*def);
	}
    /**
     * 좌표 데이터를 Geo데이터로 바꿔주는 함수.
     * @param str
     * @param len
     * @return
     */
	public static int getGeoPointData(String location){
		return getGeoPointData(Double.parseDouble(location));
	}


}