package Client;
import java.awt.FileDialog;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ProfileDB {
	String url = "jdbc:oracle:thin:@localhost:1521:hanle";
	Connection con = null;  //view 도 역시 list와 같이 데이터를 불러와야하기 때문에 ResultSet을 준비 한다.
	Properties info = null;
	Statement stmt = null;
	Statement stmt2 = null;
	ResultSet rs = null;
	ResultSet rs2 = null;
	PreparedStatement pstmt;

	private ImageIcon originalIcon = null;
	private InputStream is;
	private FileInputStream fis;

	String id = "";
	String pw = "";
	String name = "";
	String birth = "";
	String address = "";
	String address1 = "";
	String address2 = "";
	String email = "";
	String tel = "";
	String gender = "";
	String photo = "";

	FindID fip;

	public void profile(String id) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			info = new Properties();
			info.setProperty("user", "hanle");
			info.setProperty("password", "0253");
			con = DriverManager.getConnection(url, info); 

			stmt = con.createStatement();       //sql문 전달
			stmt2 = con.createStatement();

			rs = stmt.executeQuery(         // 해당 칼럼 선택.
					"select id,pw,name,birth,address,address1,address2,email,tel,gender,photo"
					+ " from chatuser1"
					+ " where id='" +  id  + "'");    // 조건은 로그인 한 id와 같은 id를 가진 데이타.

			rs2 = stmt2.executeQuery("select image from chatuser1 where id = '" + id + "'"); 


			if(rs.next()) {
				String pw = rs.getString("pw");
				String name = rs.getString("name");
				String birth = rs.getString("birth");
				String address = rs.getString("address");
				String address1 = rs.getString("address1");
				String address2 = rs.getString("address2");
				String email = rs.getString("email");
				String tel = rs.getString("tel");
				String gender = rs.getString("gender");

				Client.ptfid.setText(id);
				Client.ptfid.setEditable(false); //편집 안되게
				//Client.ptfpw.setText(pw);
				Client.ptfname.setText(name);
				Client.ptfbirth.setText(birth);
				Client.ptfaddress.setText(address);
				Client.ptfaddress1.setText(address1);
				Client.ptfaddress2.setText(address2);
				Client.ptfemail.setText(email);
				Client.ptftel.setText(tel);

				if(gender.equals("남")) {
					Client.rdbtnman.setSelected(true); 
				} else if(gender.equals("여")) {
					Client.rdbtnwoman.setSelected(true); 
				}

				Client.ptfphoto.setText(photo);

				if(rs2.next()) {
					is = rs2.getBinaryStream("image");
					originalIcon = new ImageIcon(ImageIO.read(is));
					Image originalImage = originalIcon.getImage();
					Image resizeImage= originalImage.getScaledInstance(130, 110, Image.SCALE_SMOOTH);
					ImageIcon resizeIcon = new ImageIcon(resizeImage);
					Client.lbphoto.setIcon(resizeIcon); //사이즈 조절한 사진 라벨에 넣기
				}
			}
			else {
				throw new Exception("해당 id의 멤버가 없습니다.");  //
			}     
		} catch (Exception e) {
			e.printStackTrace();
			return;
		} finally {
			try {
				rs.close(); 
				rs2.close();
			} catch (Exception e) {}
			try {
				stmt.close(); 
				stmt2.close();
			} catch (Exception e) {}
			try {
				con.close();
			} catch (Exception e) {}
		}  

	}

	public boolean update() {
		boolean ok = false;

		String id = Client.ptfid.getText();
		String pw = Client.ptfpw.getText();
		String name = Client.ptfname.getText();
		String birth = Client.ptfbirth.getText();
		String address = Client.ptfaddress.getText();
		String address1 = Client.ptfaddress1.getText();
		String address2 = Client.ptfaddress2.getText();
		String email = Client.ptfemail.getText();
		String tel = Client.ptftel.getText();
		String gender = "";
		if(Client.rdbtnman.isSelected()) {
			gender = "남";
		} else {
			gender = "여";
		}

		String photo = Client.ptfphoto.getText();

		System.out.println(Client.ptfphoto.getText()+"DAFASDFA");
		String sql = "update chatuser1 set pw=?, name=?, birth=?, address=?, address1=?, address2=?, email=?, tel=?, gender=?, photo=?, image=?"
				+  "where id = ?";

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");
			info = new Properties();
			info.setProperty("user", "hanle");
			info.setProperty("password", "0253");
			con = DriverManager.getConnection(url, info); 

			File imgfile = new File(originalIcon.toString());
			fis = new FileInputStream(imgfile);
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, pw);
			pstmt.setString(2, name);
			pstmt.setString(3, birth);
			pstmt.setString(4, address);
			pstmt.setString(5, address1);
			pstmt.setString(6, address2);
			pstmt.setString(7, email);
			pstmt.setString(8, tel);
			pstmt.setString(9, gender);
			pstmt.setString(10, photo);
			pstmt.setBinaryStream(11, fis, (int)imgfile.length());
			pstmt.setString(12, id);

			int r = pstmt.executeUpdate();

			if(r>0) 
				ok = true; //수정이 성공되면 ok값을 true로 변경
			else
				System.out.println("실패 ㅜㅜ");


			pstmt.close();
			con.close();

		}
		catch(ClassNotFoundException cnfe){
			cnfe.printStackTrace();
		}
		catch(SQLException se){
			se.printStackTrace();
		}
		catch(FileNotFoundException fnfe){
			fnfe.printStackTrace();
		}
		finally{
			try{
				fis.close();
			}
			catch(Exception e2){
				e2.printStackTrace();

			}
		}

		return ok;
	}


	public void photodelete() {
		originalIcon = new ImageIcon("C:\\Users\\0\\Desktop\\자바채팅프로그램\\프로필사진\\기본이미지.jpg");
		Image defimg = originalIcon.getImage();
		Image defimg1 = defimg.getScaledInstance(130, 110, Image.SCALE_DEFAULT);
		ImageIcon defaultimg1 = new ImageIcon(defimg1);
		Client.imagepath= "C:\\Users\\0\\Desktop\\자바채팅프로그램\\프로필사진\\기본이미지.jpg";
		Client.ptfphoto.setText(Client.imagepath);
		Client.lbphoto.setIcon(defaultimg1);

	}

	public void photoupdate() {
		FileDialog fdlg = new FileDialog(new JFrame(),"file open",FileDialog.LOAD);
		fdlg.setVisible(true); 

		if(fdlg.getFile()==null){      
			JOptionPane.showMessageDialog(null, "파일을 선택하지 않았네요", "주의", JOptionPane.WARNING_MESSAGE);
			return;
		}  
		else{
			originalIcon = new ImageIcon(fdlg.getDirectory()+fdlg.getFile());
			Image originalImage = originalIcon.getImage();
			Image resizeImage = originalImage.getScaledInstance(130, 110, Image.SCALE_SMOOTH);
			ImageIcon resizeIcon = new ImageIcon(resizeImage);
			Client.imagepath=fdlg.getDirectory()+fdlg.getFile();
			Client.ptfphoto.setText(Client.imagepath);
			Client.lbphoto.setIcon(resizeIcon);
		}
	}

	public String Pwcheck(String id) {
		String pwch = "";

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");
			info = new Properties();
			info.setProperty("user", "hanle");
			info.setProperty("password", "0253");
			con = DriverManager.getConnection(url, info); 

			String sql = "select * from chatuser1 where id = '" + id + "'"; 

			pstmt = con.prepareStatement(sql);  
			rs = pstmt.executeQuery();

			if(rs.next()) {
				pwch = rs.getString("pw");
				System.out.println("이ㅐ것은 비밀번호 " + pwch);
				return pwch;
			}
			else
				System.out.println("실패");


		}
		catch(ClassNotFoundException cnfe){
			cnfe.printStackTrace();
		}
		catch(SQLException se){
			se.printStackTrace();
		}

		return pwch;
	}

	
}


