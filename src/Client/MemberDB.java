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

public class MemberDB {
	String url = "jdbc:oracle:thin:@localhost:1521:hanle";
	Connection con = null;  //view 도 역시 list와 같이 데이터를 불러와야하기 때문에 ResultSet을 준비 한다.
	Properties info = null;
	Statement stmt = null;
	Statement stmt2 = null;
	ResultSet rs = null;
	ResultSet rs2 = null;
	PreparedStatement pstmt=null;

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

	MemberProc mp;
	
	MemberDB() {
		System.out.println("디비열기");
	}
	
	public void view(String id) {
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


				mp.textFieldid.setText(id);
				mp.textFieldid.setEditable(false); //편집 안되게
				//mp.passwordField.setText(pw);
				mp.textFieldname.setText(name);
				mp.textFieldbirth.setText(birth);
				mp.textFieldaddress.setText(address);
				mp.textFieldaddress_1.setText(address1);
				mp.textFieldaddress_2.setText(address2);
				mp.textFieldemail.setText(email);
				mp.textFieldtel.setText(tel);

				if(gender.equals("남")) {
					mp.rdbtnman.setSelected(true); 
				} else if(gender.equals("여")) {
					mp.rdbtnwoman.setSelected(true); 
				}

				mp.tfImagepath.setText(photo);
				
				if(rs2.next()) {
					is = rs2.getBinaryStream("image");
					originalIcon = new ImageIcon(ImageIO.read(is));
					Image originalImage = originalIcon.getImage();
					Image resizeImage= originalImage.getScaledInstance(172, 224, Image.SCALE_SMOOTH);
					ImageIcon resizeIcon = new ImageIcon(resizeImage);
					mp.lbphoto.setIcon(resizeIcon); //사이즈 조절한 사진 라벨에 넣기
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

	public boolean insert() {
		boolean ok = false;
		
		if(mp.textFieldid.getText().length() == 0){
			JOptionPane.showMessageDialog(null, "ID를 입력해 주세요");
			mp.textFieldid.requestFocus();
		}
		else if(mp.passwordField.getText().length() == 0){
			JOptionPane.showMessageDialog(null, "비밀번호를 입력해 주세요");
			mp.passwordField.requestFocus();
		}
		else if(mp.textFieldname.getText().length()==0 || mp.textFieldbirth.getText().length() == 0 
				|| mp.textFieldaddress.getText().length() == 0 ||mp.textFieldemail.getText().length() == 0
				||mp.textFieldaddress_1.getText().length() == 0 ||mp.textFieldaddress_2.getText().length() == 0
				||mp.textFieldtel.getText().length() == 0 ){
			JOptionPane.showMessageDialog(null, "회원 정보를 입력해주세요");
		}
		else if(mp.tfImagepath.getText().length() == 0){
			JOptionPane.showMessageDialog(null, "사진을 선택해주세요");
		}
		else {

			String id = mp.textFieldid.getText();
			String pw = mp.passwordField.getText();
			String name = mp.textFieldname.getText();;
			String birth = mp.textFieldbirth.getText();
			String address = mp.textFieldaddress.getText();
			String address1 = mp.textFieldaddress_1.getText();
			String address2 = mp.textFieldaddress_2.getText();
			String email = mp.textFieldemail.getText()+ mp.comboBoxemail.getSelectedItem();
			String tel = Hyphen.phone(mp.textFieldtel.getText());
			String gender = "";       
			if(mp.rdbtnman.isSelected()){	         
				gender = "남";	          
			}else if(mp.rdbtnwoman.isSelected()){	          
				gender = "여";	           
			}

			String photo = mp.tfImagepath.getText();

			String sql = "insert into chatuser1(" +
					"id,pw,name,birth,address,address1,address2,email,tel,gender,photo,image) "+
					"values(?,?,?,?,?,?,?,?,?,?,?,?)";

			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				info = new Properties();
				info.setProperty("user", "hanle");
				info.setProperty("password", "0253");
				con = DriverManager.getConnection(url, info); 

				File imgfile = new File(originalIcon.toString());
				fis = new FileInputStream(imgfile);
				pstmt = con.prepareStatement(sql);


				pstmt.setString(1, id);
				pstmt.setString(2, pw);
				pstmt.setString(3, name);
				pstmt.setString(4, birth);
				pstmt.setString(5, address);
				pstmt.setString(6, address1);
				pstmt.setString(7, address2);
				pstmt.setString(8, email);
				pstmt.setString(9, tel);
				pstmt.setString(10, gender);
				pstmt.setString(11, photo);
				pstmt.setBinaryStream(12, fis, (int)imgfile.length());

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
		}
		return ok;
	}  //인서트문 끝
	
	public boolean update() {
		
		boolean ok = false;
		
		String id = mp.textFieldid.getText();
		String pw = mp.passwordField.getText();
		String name = mp.textFieldname.getText();;
		String birth = mp.textFieldbirth.getText();
		String address = mp.textFieldaddress.getText();
		String address1 = mp.textFieldaddress_1.getText();
		String address2 = mp.textFieldaddress_2.getText();
		String email = mp.textFieldemail.getText();
		String tel = mp.textFieldtel.getText();
		String gender = "";       
		if(mp.rdbtnman.isSelected()){	         
			gender = "남";	          
		}else if(mp.rdbtnwoman.isSelected()){	          
			gender = "여";	           
		}

		String photo = mp.tfImagepath.getText();
		

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
	
	public boolean delete(String id, String pw) {
		
		boolean ok = false;
		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");
			info = new Properties();
			info.setProperty("user", "hanle");
			info.setProperty("password", "0253");
			con = DriverManager.getConnection(url, info); 

			String sql = "delete from chatuser1 where id=? and pw=?";
		
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			
            int r = pstmt.executeUpdate(); // 실행 -> 삭제
           
            if (r>0) 
            	ok=true; //삭제됨;
           
			
		} catch (Exception e) {
            System.out.println(e + "-> 오류발생");
        }   
		return ok;
	}



	public void photo() {
		mp.tfImagepath.setText("");
		FileDialog fdlg = new FileDialog(new JFrame(),"file open",FileDialog.LOAD);
		fdlg.setVisible(true); 

		if(fdlg.getFile()==null){      
			JOptionPane.showMessageDialog(null, "파일을 선택하지 않았네요", "주의", JOptionPane.WARNING_MESSAGE);
			return;
		}  
		else{
			originalIcon=new ImageIcon(fdlg.getDirectory()+fdlg.getFile());
			Image originalImage = originalIcon.getImage();
			Image resizeImage = originalImage.getScaledInstance(172, 224, Image.SCALE_SMOOTH);
			ImageIcon resizeIcon = new ImageIcon(resizeImage);
			mp.Imagepath=fdlg.getDirectory()+fdlg.getFile();
			mp.tfImagepath.setText(mp.Imagepath);
			mp.lbphoto.setIcon(resizeIcon); 
		}
	}

	public void defaultimg() {
		originalIcon = new ImageIcon("C:\\Users\\0\\Desktop\\자바채팅프로그램\\프로필사진\\기본이미지.jpg");
		Image defimg = originalIcon.getImage();
		Image defimg1 = defimg.getScaledInstance(172, 224, Image.SCALE_DEFAULT);
		ImageIcon defaultimg1 = new ImageIcon(defimg1);
		mp.Imagepath= "C:\\Users\\0\\Desktop\\자바채팅프로그램\\프로필사진\\기본이미지.jpg";
		mp.tfImagepath.setText(mp.Imagepath);
		mp.lbphoto.setIcon(defaultimg1);
	}

}
