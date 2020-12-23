package Client;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.swing.JOptionPane;


public class LoginDB {
	
	
	String id = null;
	String pw = null;
	
	Statement stmt = null;
	ResultSet rs = null;
	String url = "jdbc:oracle:thin:@localhost:1521:hanle"; // 오라클 포트번호1521/@이후에는 IP주소
	String sql = null;
	Properties info = null;
	Connection cnn = null;
	
	int checkIDPW(String id, String pw) {
		this.id = id;
		this.pw = pw;
		int result = 1;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); // 알아서 들어간다..conn로
			info = new Properties();
			info.setProperty("user", "hanle");
			info.setProperty("password", "0253");
			cnn = DriverManager.getConnection(url, info); // 연결할 정보를 가지고있는 드라이버매니저를 던진다
			stmt = cnn.createStatement();

			sql = "select * from CHATUSER1 where id='" + id + "'";
			rs = stmt.executeQuery(sql); // 읽어오는거라 다르다 비교해	//리턴타입이 ResultSet

			
			if (rs.next() == false || (id.isEmpty()) == true) { // id가 존재x
				result = 1;
				JOptionPane.showMessageDialog(Client.contentPane, "아이디가 존재하지 않습니다");
			} 
			
			else if (id.equals("") || pw.equals("")) {
				result = 1;
				JOptionPane.showMessageDialog(Client.contentPane, "아이디나 비밀번호를 입력하지 않았습니다");
			}
			else {
				sql = "select * from (select * from CHATUSER1 where id='" + id + "')";
				rs = stmt.executeQuery(sql);
				while (rs.next() == true) { 		// 다음값의
					if (rs.getString(2).equals(pw)) { // pw와 같은지 비교
						result = 0; 		// 같으면 로그인 성공
					} else {				// 아이디는같고 pw가 다른경우
						JOptionPane.showMessageDialog(Client.contentPane, "비밀번호가 일치하지 않습니다");
						result = 1;
					}
				}
			}
		} catch (Exception ee) {
			System.out.println("문제있음");
			ee.printStackTrace();
		}
		try {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (cnn != null)
				cnn.close();
			}
		catch (SQLException e1) {
			e1.printStackTrace();
		}	
		
		
		
		return result;
		
	}

}
