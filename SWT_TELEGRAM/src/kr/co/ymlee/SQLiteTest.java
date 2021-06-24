package kr.co.ymlee;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import kr.co.ymlee.util.DateUtil;

public class SQLiteTest {

	public static void main(String[] args) {

		Connection con = null;
		Statement stmt = null;
		Statement stat = null;
		PreparedStatement psmt = null;
		System.out.println("#1");
		try {
			// SQLite JDBC 체크
			Class.forName("org.sqlite.JDBC");
			
			String path = System.getProperty("user.dir");
			System.out.println("Working Directory = " + path);
			String sPath = System.getProperty("java.class.path");
			System.out.println(sPath);

			sPath = new File(".").getAbsolutePath();
			System.out.println(sPath);



			// SQLite 데이터베이스 파일에 연결
			String dbPath = System.getProperty("user.dir")+"\\db";
			File f = new File(dbPath);
			if( !f.exists() ){
				f.mkdirs();
			}
			String dbFile = dbPath + "\\swt_lib.db";
			con = DriverManager.getConnection("jdbc:sqlite:" + dbFile);
			
			
			String sql = "CREATE TABLE IF NOT EXISTS LIB2 (\n"
	                + "	NM TEXT PRIMARY KEY,\n"
	                + "	ST TEXT NOT NULL \n"
	                + ");";
			
			stmt = con.createStatement();
			stmt.execute(sql);
			stmt.close();
			sql = "insert into LIB2(NM, ST) VALUES ('TEST_"+DateUtil.getDateTimeMinSec()+"', 'A');";
			stmt = con.createStatement();
			stmt.execute(sql);
	            
			// SQL 수행
			stat = con.createStatement();
			ResultSet rs = stat.executeQuery("SELECT NM, ST FROM LIB2");
			while(rs.next()) {
				String id = rs.getString("NM");
				String name = rs.getString("ST");
				
				System.out.println(id + "," + name);
			}
			stat.close();
			rs.close();
			// SQL 수행
//			stat = con.createStatement();
			psmt = con.prepareStatement("SELECT COUNT(1) AS CNT FROM LIB2 WHERE NM = ? ");
			psmt.setString(1, "TEST_20210419094843");
			rs = psmt.executeQuery();
//			rs = stat.executeQuery("SELECT COUNT(1) AS CNT FROM LIB2");
			while(rs.next()) {
				String CNT = rs.getString("CNT");
				System.out.println("CNT :"+CNT);
			}
			rs.close();
			psmt.setString(1, "TEST_20210419101558");
			rs = psmt.executeQuery();
			while(rs.next()) {
				String CNT = rs.getString("CNT");
				System.out.println("CNT :"+CNT);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if( stmt != null ) {
				try{ stmt.close(); }catch(Exception ie ){}
			}
			if( psmt != null ) {
				try{ psmt.close(); }catch(Exception ie ){}
			}
			if( stat != null ) {
				try{ stat.close(); }catch(Exception ie ){}
			}
			if(con != null) {
				try {con.close();}catch(Exception e) {}
			}
		}
		System.out.println("#2");
	}
}
 