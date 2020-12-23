package Client;


import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class MemberProc extends JFrame implements ActionListener, KeyListener{

	JPanel contentPane;
	static JTextField textFieldname;
	static JTextField textFieldid;
	static JPasswordField passwordField = new JPasswordField();
	JPasswordField passwordField_1 = new JPasswordField();
	static JTextField textFieldbirth;
	static JTextField textFieldaddress;
	static JTextField textFieldaddress_1;
	static JTextField textFieldaddress_2;
	static JTextField textFieldemail;
	static JTextField textFieldtel;
	JComboBox comboBox = new JComboBox();
	static JComboBox comboBoxemail = new JComboBox();
	static JLabel lbphoto = new JLabel();
	JLabel labelerror = new JLabel();
	JLabel labelerror1 = new JLabel();
	JButton btncheck = new JButton();
	JButton btnreset = new JButton();
	JButton btnadd = new JButton();
	JButton btnJoin = new JButton();
	JButton btncancle = new JButton();
	JButton btnUpdate = new JButton();
	JButton btnDelete = new JButton();
	JButton btnphoto = new JButton();
	JButton btndefaultimg = new JButton();
	ButtonGroup bg = new ButtonGroup();
	static JRadioButton rdbtnman = new JRadioButton();
	static JRadioButton rdbtnwoman = new JRadioButton();
	static JTextField tfImagepath;
	static String Imagepath;

	JLabel lblNewLabel;
	JLabel lbf;
	JLabel lbf1;

	MemberList mList ;
	//private ImageIcon originalIcon;
	private InputStream is;
	private FileInputStream fis;

	Image img=null;

	LabelImage li;
	MemberDB md = new MemberDB();

	public MemberProc(){ //가입용 생성자

		createUI(); // UI작성해주는 메소드
		btnUpdate.setEnabled(false);
		btnUpdate.setVisible(false);
		btnDelete.setEnabled(false);
		btnreset.setVisible(false);


	}//생성자

	public MemberProc(MemberList mList){ //가입용 생성자

		createUI(); // UI작성해주는 메소드
		btnUpdate.setEnabled(false);
		btnUpdate.setVisible(false);
		btnDelete.setEnabled(false);
		btnDelete.setVisible(false);
		btnreset.setVisible(false);
		this.mList = mList;

	}//생성자
	public MemberProc(String id, MemberList mList){ // 수정/삭제용 생성자
		createUI();
		btnJoin.setEnabled(false);
		btnJoin.setVisible(false);
		this.mList = mList;
		/*
		MemberDAO dao = new MemberDAO();
		MemberDTO vMem = dao.getMemberDTO(id);
		viewData(vMem);
		 */
		MemberDB md = new MemberDB();
		md.view(id);


	}//id를 가지고 생성


	//MemberDTO 의 회원 정보를 가지고 화면에 셋팅해주는 메소드
	/*private void viewData(MemberDTO vMem){
		String id = vMem.getId();
		String pw = vMem.getPw();
		String name = vMem.getName();
		String birth = vMem.getBirth();
		String address = vMem.getAddress();
		String address1 = vMem.getAddress1();
		String address2 = vMem.getAddress2();
		String email = vMem.getEmail();
		String tel = vMem.getTel();
		String gender = vMem.getGender();
		String photo = vMem.getPhoto();


		//화면에 세팅
		textFieldid.setText(id);
		textFieldid.setEditable(false); //편집 안되게
		passwordField.setText(""); //비밀번호는 안보여준다.
		textFieldname.setText(name);
		textFieldbirth.setText(birth);
		textFieldaddress.setText(address);
		textFieldaddress_1.setText(address1);
		textFieldaddress_2.setText(address2);
		textFieldemail.setText(email);
		textFieldtel.setText(tel);

		if(gender.equals("남")){
			rdbtnman.setSelected(true);
		}else if(gender.equals("여")){
			rdbtnwoman.setSelected(true);
		}

		tfImagepath.setText(photo);
		try {
			File sourceimage = new File(photo);
			img = ImageIO.read(sourceimage);
			Image resizeImage= img.getScaledInstance(180, 195, Image.SCALE_SMOOTH);
			ImageIcon resizeIcon = new ImageIcon(resizeImage);
			lbphoto.setIcon(resizeIcon);
		}catch (IOException e) {
		}

	}//viewData
*/
	public void createUI() {
		setTitle("회원가입창");

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 624, 509);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(253, 213, 225));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btncheck = new JButton("check");
		btncheck.setBackground(new Color(254, 224, 234));
		btncheck.setBounds(280, 25, 88, 23);
		contentPane.add(btncheck);
		btncheck.addActionListener(this);

		JLabel lableid = new JLabel("아이디");
		lableid.setForeground(new Color(139, 0, 139));
		lableid.setFont(new Font("맑은 고딕", Font.ITALIC, 13));
		lableid.setBounds(206, 28, 57, 15);
		contentPane.add(lableid);

		btnreset = new JButton("초기화");
		btnreset.setBackground(new Color(254, 224, 234));
		btnreset.setBounds(280, 96, 88, 23);
		contentPane.add(btnreset);
		btnreset.addActionListener(this);

		JLabel lblPw = new JLabel("비밀번호");
		lblPw.setForeground(new Color(139, 0, 139));
		lblPw.setFont(new Font("맑은 고딕", Font.ITALIC, 13));
		lblPw.setBounds(206, 99, 57, 15);
		contentPane.add(lblPw);

		JLabel lblPwCheck = new JLabel("비밀번호 재확인");
		lblPwCheck.setForeground(new Color(139, 0, 139));
		lblPwCheck.setFont(new Font("맑은 고딕", Font.ITALIC, 13));
		lblPwCheck.setBounds(206, 177, 102, 15);
		contentPane.add(lblPwCheck);

		JLabel labelname = new JLabel("이름");
		labelname.setForeground(new Color(139, 0, 139));
		labelname.setFont(new Font("맑은 고딕", Font.ITALIC, 13));
		labelname.setBounds(411, 27, 75, 15);
		contentPane.add(labelname);

		JLabel labelbirth = new JLabel("생년월일");
		labelbirth.setForeground(new Color(139, 0, 139));
		labelbirth.setFont(new Font("맑은 고딕", Font.ITALIC, 13));
		labelbirth.setBounds(411, 98, 75, 15);
		contentPane.add(labelbirth);

		JLabel lblAddress = new JLabel("주소");
		lblAddress.setForeground(new Color(139, 0, 139));
		lblAddress.setFont(new Font("맑은 고딕", Font.ITALIC, 13));
		lblAddress.setBounds(206, 256, 57, 15);
		contentPane.add(lblAddress);

		textFieldid = new JTextField();
		textFieldid.setBounds(206, 47, 162, 36);
		contentPane.add(textFieldid);
		textFieldid.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(206, 117, 162, 36);
		contentPane.add(passwordField);
		passwordField.addKeyListener(this);

		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(206, 193, 162, 36);
		contentPane.add(passwordField_1);
		passwordField_1.addKeyListener(this);
		labelerror.setFont(new Font("굴림", Font.PLAIN, 9));

		//labelerror = new JLabel("에러메시지");
		labelerror.setBounds(203, 152, 319, 15);
		contentPane.add(labelerror);
		labelerror.setForeground(Color.RED);
		labelerror1.setFont(new Font("굴림", Font.PLAIN, 9));

		//labelerror1 = new JLabel("에러메시지1");
		labelerror1.setBounds(206, 231, 267, 15);
		contentPane.add(labelerror1);
		labelerror1.setForeground(Color.RED);

		textFieldname = new JTextField();
		textFieldname.setBounds(411, 46, 161, 36);
		contentPane.add(textFieldname);
		textFieldname.setColumns(10);

		textFieldbirth = new JTextField();
		textFieldbirth.setBounds(411, 116, 161, 36);
		textFieldbirth.setColumns(10);
		contentPane.add(textFieldbirth);

		btnadd = new JButton("찾기");
		btnadd.setBackground(new Color(254, 224, 234));
		btnadd.setBounds(280, 252, 88, 23);
		contentPane.add(btnadd);
		btnadd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] args = null;
				ZipSearch.main(args);
			}
		});

		textFieldaddress = new JTextField();
		textFieldaddress.setBounds(205, 275, 163, 36);
		textFieldaddress.setColumns(10);
		contentPane.add(textFieldaddress);

		textFieldaddress_1 = new JTextField();
		textFieldaddress_1.setColumns(10);
		textFieldaddress_1.setBounds(373, 275, 199, 36);
		contentPane.add(textFieldaddress_1);

		textFieldaddress_2 = new JTextField();
		textFieldaddress_2.setColumns(10);
		textFieldaddress_2.setBounds(373, 312, 199, 36);
		contentPane.add(textFieldaddress_2);

		JLabel labelemail = new JLabel("이메일");
		labelemail.setForeground(new Color(139, 0, 139));
		labelemail.setFont(new Font("맑은 고딕", Font.ITALIC, 13));
		labelemail.setBounds(206, 338, 58, 15);
		contentPane.add(labelemail);

		textFieldemail = new JTextField();
		textFieldemail.setColumns(10);
		textFieldemail.setBounds(207, 357, 161, 36);
		contentPane.add(textFieldemail);

		JLabel labeltel = new JLabel("휴대전화");
		labeltel.setForeground(new Color(139, 0, 139));
		labeltel.setFont(new Font("맑은 고딕", Font.ITALIC, 13));
		labeltel.setBounds(411, 176, 58, 15);
		contentPane.add(labeltel);

		textFieldtel = new JTextField();
		textFieldtel.setColumns(10);
		textFieldtel.setBounds(411, 192, 161, 37);
		contentPane.add(textFieldtel);

		JLabel labelphoto = new JLabel("프로필 사진");
		labelphoto.setForeground(new Color(139, 0, 139));
		labelphoto.setFont(new Font("맑은 고딕", Font.ITALIC, 13));
		labelphoto.setBounds(72, 28, 102, 15);
		contentPane.add(labelphoto);

		lbphoto = new JLabel("프로필 사진 불러오기");
		lbphoto.setHorizontalAlignment(SwingConstants.CENTER);
		lbphoto.setVerticalAlignment(SwingConstants.BOTTOM);
		lbphoto.setBounds(22, 47, 172, 224);
		contentPane.add(lbphoto);

		btnphoto = new JButton("불러오기");
		btnphoto.setBackground(new Color(254, 224, 234));
		btnphoto.setBounds(22, 277, 172, 23);
		contentPane.add(btnphoto);
		btnphoto.addActionListener(this);

		btndefaultimg = new JButton("기본이미지로 선택");
		btndefaultimg.setBackground(new Color(254, 224, 234));
		btndefaultimg.setBounds(22, 304, 171, 23);
		contentPane.add(btndefaultimg);
		btndefaultimg.addActionListener(this);

		lblNewLabel = new JLabel("성별");
		lblNewLabel.setForeground(new Color(139, 0, 139));
		lblNewLabel.setFont(new Font("맑은 고딕", Font.ITALIC, 13));
		lblNewLabel.setBounds(414, 255, 57, 15);
		contentPane.add(lblNewLabel);

		rdbtnman = new JRadioButton("남");
		rdbtnman.setBackground(new Color(253, 213, 225));
		rdbtnman.setBounds(467, 252, 40, 23);
		contentPane.add(rdbtnman);

		rdbtnwoman = new JRadioButton("여");
		rdbtnwoman.setBackground(new Color(253, 213, 225));
		rdbtnwoman.setBounds(521, 252, 51, 23);
		contentPane.add(rdbtnwoman);

		bg = new ButtonGroup();
		bg.add(rdbtnman);
		bg.add(rdbtnwoman);

		btnJoin = new JButton("가입");
		btnJoin.setBackground(new Color(254, 224, 234));
		btnJoin.setBounds(206, 421, 162, 40);
		contentPane.add(btnJoin);
		btnJoin.addActionListener(this);

		String[] tels= {"한국 (82)" };
		comboBox = new JComboBox(tels);
		comboBox.setBackground(new Color(254, 224, 234));
		comboBox.setBounds(469, 173, 103, 19);
		contentPane.add(comboBox);

		String[] mails = {"@naver.com", "@google.com", "직접입력"};
		comboBoxemail = new JComboBox(mails);
		comboBoxemail.setBackground(new Color(254, 224, 234));
		comboBoxemail.setBounds(373, 358, 199, 35);
		contentPane.add(comboBoxemail);
		//만앾 직접입력을 선택한ㅁ다면 라벨로 이동 

		btncancle = new JButton("취소");
		btncancle.setBackground(new Color(254, 224, 234));
		btncancle.setBounds(380, 421, 192, 40);
		contentPane.add(btncancle);
		btncancle.addActionListener(this);

		btnUpdate = new JButton("수정");
		btnUpdate.setBackground(new Color(254, 224, 234));
		btnUpdate.setBounds(32, 421, 162, 40);
		contentPane.add(btnUpdate);
		btnUpdate.addActionListener(this);

		btnDelete = new JButton("탈퇴");
		btnDelete.setBackground(new Color(254, 224, 234));
		btnDelete.setBounds(206, 421, 162, 40);
		contentPane.add(btnDelete);
		btnDelete.addActionListener(this);

		tfImagepath = new JTextField();
		tfImagepath.setBounds(22, 47, 172, 21);
		contentPane.add(tfImagepath);
		tfImagepath.setColumns(10);
		tfImagepath.setVisible(false);

		lbf = new JLabel(li.Labelimage("C:\\Users\\0\\Desktop\\자바채팅프로그램\\pinkF.png", 40, 40));
		lbf.setBounds(42, 14, 40, 40);
		contentPane.add(lbf);

		lbf1 = new JLabel(li.Labelimage("C:\\Users\\0\\Desktop\\자바채팅프로그램\\pinkF.png", 40, 40));
		lbf1.setBounds(132, 14, 40, 40);
		contentPane.add(lbf1);

		setResizable(false); //창크기 고정시키는거
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE); //dispose(); //현재창만 닫는다.
	}


	public static void main(String[] args) {
		new MemberProc();
	}

	//JFileChooser choice = new JFileChooser();
	public void actionPerformed(ActionEvent e) {

		PreparedStatement pstmt=null;
		Statement stmt = null;
		ResultSet rs = null;
		String url = "jdbc:oracle:thin:@localhost:1521:hanle"; // 오라클 포트번호1521/@이후에는 IP주소
		String sql = null;
		Properties info = null;
		Connection cnn = null;
		

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); // 알아서 들어간다..conn로
			info = new Properties();
			info.setProperty("user", "hanle");
			info.setProperty("password", "0253");
			cnn = DriverManager.getConnection(url, info); // 연결할 정보를 가지고있는 드라이버매니저를 던진다
			stmt = cnn.createStatement();

			if (e.getSource().equals(btncheck)) {


				sql = "select * from CHATUSER1 where id='" + textFieldid.getText() + "'";
				rs = stmt.executeQuery(sql); 			// 읽어오는거라 다르다 비교해//리턴타입이 ResultSet

				if (rs.next() == true) { // 이미 id가 존재한다면
					JOptionPane.showMessageDialog(this, "중복된 아이디입니다. 다시 입력해주세요");
				} 
				else if((textFieldid.getText().isEmpty()) == true) {
					JOptionPane.showMessageDialog(this, "아이디를 입력해주세요");
				}
				else {
					JOptionPane.showMessageDialog(this, "사용가능한 아이디입니다");
				}
			}

			else if(e.getSource() == btnreset) {
				int x = JOptionPane.showConfirmDialog(this, "비밀번호를 초기화하시겠습니까?", "비밀번호 초기화", JOptionPane.YES_NO_OPTION);

				if (x == JOptionPane.OK_OPTION){ 
					JOptionPane.showMessageDialog(this, "비밀번호를 초기화하였습니다\n 비밀번호 | Asdf!234");
					passwordField.setText("Asdf!234");
					passwordField_1.setText("Asdf!234!");

				}else{
					JOptionPane.showMessageDialog(this, "삭제를 취소하였습니다.");
				} 
			}

			else if(e.getSource() == btnJoin) {
				
				boolean ok = md.insert();

				if(ok){
					JOptionPane.showMessageDialog(this, "가입이 완료되었습니다.");
					dispose();

				}else{
					JOptionPane.showMessageDialog(this, "가입이 정상적으로 처리되지 않았습니다.");
				}
			}
			else if(e.getSource() == btncancle){
				this.dispose(); //창닫기 (현재창만 닫힘)        
			}
			else if(e.getSource() == btnUpdate){
				
				String pw = passwordField.getText();
				if(pw.length()==0){ //길이가 0이면

					JOptionPane.showMessageDialog(this, "비밀번호를 꼭 입력하세요");
					return; //메소드 끝
				}

				boolean ok = md.update();

				int x = JOptionPane.showConfirmDialog(this, "수정하시겠습니까?", "프로필 수정", JOptionPane.YES_NO_OPTION);
				
				if (x == JOptionPane.OK_OPTION){
					if(ok) {
						JOptionPane.showMessageDialog(this, "수정되었습니다.");
						dispose();
				}
					else
						JOptionPane.showMessageDialog(this, "수정실패ㅜㅜ");
					
				}
				else
					System.out.println("수정취소"); 
			}
			else if(e.getSource().equals(btnphoto)) { 
				md.photo();
			}
			else if(e.getSource().equals(btndefaultimg)) {
				md.defaultimg();

			}
			else if(e.getSource() == btnDelete){
				String id = textFieldid.getText();
				String pw = passwordField.getText();
				if(pw.length()==0){ //길이가 0이면

					JOptionPane.showMessageDialog(this, "비밀번호를 꼭 입력하세요!");
					return; 
				}
				
				boolean ok = md.delete(id, pw);
				
				int x = JOptionPane.showConfirmDialog(this, "정말 삭제하시겠습니까?","삭제", JOptionPane.YES_NO_OPTION);

				if (x == JOptionPane.OK_OPTION){ 
					if(ok){
						JOptionPane.showMessageDialog(this, "삭제완료");
						dispose();         

					}else{
						JOptionPane.showMessageDialog(this, "삭제실패");

					}
				}else{
					JOptionPane.showMessageDialog(this, "삭제를 취소하였습니다.");
				} 
				
				

				
			}
		}
		catch (Exception ee) {
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

		mList.jTableRefresh();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		String pwd1 = String.valueOf(passwordField.getPassword());
		String pwd2 = String.valueOf(passwordField_1.getPassword());

		String regExp_symbol = "([0-9].*[!,@,#,^,&,*,(,)])|([!,@,#,^,&,*,(,)].*[0-9])";
		String regExp_alpha = "([a-z].*[A-Z])|([A-Z].*[a-z])";

		Pattern pattern_symbol = Pattern.compile(regExp_symbol);
		Pattern pattern_alpha = Pattern.compile(regExp_alpha);

		Matcher matcher_symbol = pattern_symbol.matcher(pwd1);
		Matcher matcher_alpha = pattern_alpha.matcher(pwd1);

		try {
			if (pwd1.length() < 10 || pwd1.length() > 16) {
				labelerror.setText("10~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요");
			}

			if (matcher_symbol.find() && matcher_alpha.find()) {
				labelerror.setText("");
			}
			else {
				labelerror.setText("10~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요");
				return;
			}
			if (!pwd1.equals(pwd2)) {
				labelerror1.setText("비밀번호가 일치하지 않습니다");
				return;
			} 
			else {
				labelerror1.setText("");
			}

		}

		catch (Exception ee) {
			System.out.println("안됌....");
			ee.printStackTrace();
		}

	}

/*
	private void insertMember(){

		//화면에서 사용자가 입력한 내용을 얻는다.
		MemberDTO dto = getViewData();
		MemberDAO dao = new MemberDAO();       
		boolean ok = dao.insertMember(dto);

		if(ok){

			JOptionPane.showMessageDialog(this, "가입이 완료되었습니다.");
			dispose();

		}else{

			JOptionPane.showMessageDialog(this, "가입이 정상적으로 처리되지 않았습니다.");
		}
	}*/

/*
	private void UpdateMember() {

		String pw = passwordField.getText();
		if(pw.length()==0){ //길이가 0이면

			JOptionPane.showMessageDialog(this, "비밀번호를 꼭 입력하세요");
			return; //메소드 끝
		}

		//1. 화면의 정보를 얻는다.
		MemberDTO dto = getViewData();     
		//2. 그정보로 DB를 수정
		MemberDAO dao = new MemberDAO();
		boolean ok = dao.updateMember(dto);
		System.out.println(ok);

		if(ok){
			JOptionPane.showMessageDialog(this, "수정되었습니다.");
			this.dispose();
		}
		else{
			JOptionPane.showMessageDialog(this, "수정실패ㅜㅜ");
		}
	}
*/

/*
	private void deleteMember() {
		String id = textFieldid.getText();
		String pw = passwordField.getText();
		if(pw.length()==0){ //길이가 0이면

			JOptionPane.showMessageDialog(this, "비밀번호를 꼭 입력하세요!");
			return; //메소드 끝
		}
		//System.out.println(mList);
		MemberDAO dao = new MemberDAO();
		boolean ok = dao.deleteMember(id, pw);

		if(ok){
			JOptionPane.showMessageDialog(this, "삭제완료");
			dispose();         

		}else{
			JOptionPane.showMessageDialog(this, "삭제실패");

		}          

	}*///deleteMember
/*	public MemberDTO getViewData(){

		//화면에서 사용자가 입력한 내용을 얻는다.
		MemberDTO dto = new MemberDTO();
		String id = textFieldid.getText();
		String pw = passwordField.getText();
		String name = textFieldname.getText();
		String birth = textFieldbirth.getText();
		String address = textFieldaddress.getText();
		String address1 = textFieldaddress_1.getText();
		String address2 = textFieldaddress_2.getText();
		String email = textFieldemail.getText();
		String tel = textFieldtel.getText();


		String gender = "";	        
		if(rdbtnman.isSelected()){	         
			gender = "남";	          
		}else if(rdbtnwoman.isSelected()){	          
			gender = "여";	           
		}

		String photo = tfImagepath.getText();           

		//dto에 담는다.	     	     
		dto.setId(id);	     
		dto.setPw(pw);	     
		dto.setName(name);	 
		dto.setBirth(birth);     
		dto.setAddress(address);	     	     
		dto.setAddress1(address1);	     
		dto.setAddress2(address2);	   	     
		dto.setEmail(email);	  
		dto.setTel(tel);	
		dto.setGender(gender);
		dto.setPhoto(photo);

		return dto;

	}
*/

}
