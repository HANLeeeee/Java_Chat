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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ButtonGroup;
import javax.swing.Icon;
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


public class JoinUI extends JFrame implements ActionListener, KeyListener{	//회원가입창
	JPanel contentPane;
	JTextField textFieldname;
	JTextField textFieldid;
	JPasswordField passwordField = new JPasswordField();
	JPasswordField passwordField_1 = new JPasswordField();
	JTextField textFieldbirth;
	static JTextField textFieldaddress;
	static JTextField textFieldaddress_1;
	static JTextField textFieldaddress_2;
	static boolean ziptmp;
	JTextField textFieldemail;
	JTextField textFieldtel;
	JComboBox comboBox = new JComboBox();
	JComboBox comboBoxemail = new JComboBox();
	String email;

	JLabel lbphoto = new JLabel("");
	JLabel labelerror = new JLabel();
	JLabel labelerror1 = new JLabel();
	JButton btncheck = new JButton();
	JButton btnadd = new JButton();
	JButton btnJoin = new JButton();
	JButton btncancle = new JButton();
	JButton btnphoto = new JButton();
	JButton btndefaultimg = new JButton();

	ButtonGroup gender = new ButtonGroup();
	JRadioButton rdbtnman = new JRadioButton();
	JRadioButton rdbtnwoman = new JRadioButton();
	String gendervalue ="";

	JTextField tfImagepath;
	String Imagepath;

	JLabel lblNewLabel;
	JLabel lbf;
	JLabel lbf1;
	private JLabel lblNewLabel_1;
	
	LabelImage li;

	public JoinUI() {
		setTitle("회원가입창");

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 624, 509);
		contentPane = new JPanel();
		contentPane.setForeground(Color.PINK);
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btncheck = new JButton("check");
		btncheck.setBackground(new Color(230, 230, 250));
		btncheck.setBounds(280, 25, 88, 23);
		contentPane.add(btncheck);
		btncheck.addActionListener(this);

		JLabel lableid = new JLabel("아이디");
		lableid.setForeground(new Color(139, 0, 139));
		lableid.setFont(new Font("맑은 고딕", Font.ITALIC, 13));
		lableid.setBounds(206, 28, 57, 15);
		contentPane.add(lableid);

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
		btnadd.setBackground(new Color(230, 230, 250));
		btnadd.setBounds(280, 252, 88, 23);
		contentPane.add(btnadd);
		btnadd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ZipSearch();
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
		labelphoto.setBounds(72, 28, 94, 15);
		contentPane.add(labelphoto);

		lbphoto = new JLabel("프로필 사진 불러오기");
		lbphoto.setVerticalAlignment(SwingConstants.BOTTOM);
		lbphoto.setHorizontalAlignment(SwingConstants.CENTER);
		lbphoto.setBounds(22, 47, 172, 224);
		contentPane.add(lbphoto);

		btnphoto = new JButton("불러오기");
		btnphoto.setBackground(new Color(230, 230, 250));
		btnphoto.setBounds(22, 277, 172, 23);
		contentPane.add(btnphoto);
		btnphoto.addActionListener(this);

		btndefaultimg = new JButton("기본이미지로 선택");
		btndefaultimg.setBackground(new Color(230, 230, 250));
		btndefaultimg.setBounds(22, 304, 171, 23);
		contentPane.add(btndefaultimg);
		btndefaultimg.addActionListener(this);

		lblNewLabel = new JLabel("성별");
		lblNewLabel.setForeground(new Color(139, 0, 139));
		lblNewLabel.setFont(new Font("맑은 고딕", Font.ITALIC, 13));
		lblNewLabel.setBounds(414, 255, 57, 15);
		contentPane.add(lblNewLabel);

		rdbtnman = new JRadioButton("남");
		rdbtnman.setBackground(new Color(230, 230, 250));
		rdbtnman.setBounds(467, 252, 40, 23);
		contentPane.add(rdbtnman);

		rdbtnwoman = new JRadioButton("여");
		rdbtnwoman.setBackground(new Color(230, 230, 250));
		rdbtnwoman.setBounds(521, 252, 51, 23);
		contentPane.add(rdbtnwoman);

		gender = new ButtonGroup();
		gender.add(rdbtnman);
		gender.add(rdbtnwoman);

		btnJoin = new JButton("가입");
		btnJoin.setBackground(new Color(230, 230, 250));
		btnJoin.setBounds(236, 421, 162, 40);
		contentPane.add(btnJoin);
		btnJoin.addActionListener(this);

		String[] tels= {"한국 (82)" };
		comboBox = new JComboBox(tels);
		comboBox.setBackground(new Color(230, 230, 250));
		comboBox.setBounds(469, 173, 103, 19);
		contentPane.add(comboBox);

		String[] mails = {"@naver.com", "@google.com", "직접입력"};
		comboBoxemail = new JComboBox(mails);
		comboBoxemail.setBackground(new Color(230, 230, 250));
		comboBoxemail.setBounds(373, 360, 199, 29);
		contentPane.add(comboBoxemail);
		//만앾 직접입력을 선택한ㅁ다면 라벨로 이동 

		btncancle = new JButton("취소");
		btncancle.setBackground(new Color(230, 230, 250));
		btncancle.setBounds(410, 421, 162, 40);
		contentPane.add(btncancle);
		btncancle.addActionListener(this);

		tfImagepath = new JTextField();
		tfImagepath.setBounds(50, 54, 116, 21);
		contentPane.add(tfImagepath);
		tfImagepath.setColumns(10);
		tfImagepath.setVisible(false);


		lbf = new JLabel(li.Labelimage("C:\\Users\\0\\Desktop\\자바채팅프로그램\\pinkF.png", 40, 40));
		lbf.setBounds(42, 14, 40, 40);
		contentPane.add(lbf);

		lbf1 = new JLabel(li.Labelimage("C:\\Users\\0\\Desktop\\자바채팅프로그램\\pinkF.png", 40, 40));
		lbf1.setBounds(132, 14, 40, 40);
		contentPane.add(lbf1);
		
		lblNewLabel_1 = new JLabel(li.Labelimage("C:\\Users\\0\\Desktop\\자바채팅프로그램\\oh.jpg", 220, 150));
		lblNewLabel_1.setBounds(12, 321, 224, 150);
		contentPane.add(lblNewLabel_1);


		setResizable(false); //창크기 고정시키는거
		setVisible(true);
	}


	

	@Override
	public void actionPerformed(ActionEvent e) {
		Statement stmt = null;
		PreparedStatement pstmt  = null; // 데이터를 읽어오기 위함
		ResultSet rs = null;
		String url = "jdbc:oracle:thin:@localhost:1521:hanle"; // 오라클 포트번호1521/@이후에는 IP주소
		String sql = null;
		Properties info = null;
		Connection cnn = null;
		FileInputStream fis = null;
		ImageIcon originalIcon;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); // 알아서 들어간다..conn로
			info = new Properties();
			info.setProperty("user", "hanle");
			info.setProperty("password", "0253");
			cnn = DriverManager.getConnection(url, info); // 연결할 정보를 가지고있는 드라이버매니저를 던진다
			stmt = cnn.createStatement();

			if (e.getSource().equals(btncheck)) {
				sql = "select * from CHATUSER1 where id='" + textFieldid.getText() + "'";
				rs = stmt.executeQuery(sql); // 읽어오는거라 다르다 비교해//리턴타입이 ResultSet

				if (rs.next() == true || (textFieldid.getText().isEmpty()) == true) { // 이미 id가 존재한다면
					JOptionPane.showMessageDialog(this, "중복된 아이디입니다. 다시 입력해주세요");
				} else {
					JOptionPane.showMessageDialog(this, "사용가능한 아이디입니다");
				}
			}

			
			else if(e.getSource().equals(btnJoin)) {

				System.out.println("회원가입버튼을 클릭");
				sql = "select * from CHATUSER1 where id= '" + textFieldid.getText() + "'";
				rs = stmt.executeQuery(sql); // 읽어오는거라 다르다 비교해	//리턴타입이 ResultSet
				if(rs.next() == true) {
					JOptionPane.showMessageDialog(this, "아이디 중복확인을 해주세요");
				}
				else if(textFieldid.getText().length() == 0){
					JOptionPane.showMessageDialog(null, "ID를 입력해 주세요.");
					textFieldid.requestFocus();
					return;
				}
				else if(passwordField.getText().length() == 0){
					JOptionPane.showMessageDialog(null, "비밀번호를 입력해 주세요.");
					passwordField.requestFocus();
					return;
				}
				else {

					insertMember();
					dispose();		
				}
			}

			else if(e.getSource().equals(btncancle)) {
				dispose();
			}

			else if(e.getSource().equals(btnphoto)) {
				tfImagepath.setText("");
				FileDialog fdlg = new FileDialog(new JFrame(),"file open",FileDialog.LOAD);
				fdlg.setVisible(true); 

				if(fdlg.getFile()==null){      
					JOptionPane.showMessageDialog(null, "파일을 선택하지 않았네요", "주의", JOptionPane.WARNING_MESSAGE);
					return;
				}  
				else{
					originalIcon=new ImageIcon(fdlg.getDirectory()+fdlg.getFile());
					Image originalImage = originalIcon.getImage();
					Image resizeImage = originalImage.getScaledInstance(180, 195, Image.SCALE_SMOOTH);
					ImageIcon resizeIcon = new ImageIcon(resizeImage);
					Imagepath=fdlg.getDirectory()+fdlg.getFile();
					tfImagepath.setText(Imagepath);
					lbphoto.setIcon(resizeIcon);
				}

			}
			else if(e.getSource().equals(btndefaultimg)) {

				originalIcon = new ImageIcon("C:\\Users\\0\\Desktop\\자바채팅프로그램\\프로필사진\\기본이미지.jpg");
				Image defimg = originalIcon.getImage();
				tfImagepath.setText("C:\\Users\\0\\Desktop\\자바채팅프로그램\\프로필사진\\기본이미지.jpg");
				Image defimg1 = defimg.getScaledInstance(180, 220, Image.SCALE_DEFAULT);
				ImageIcon defaultimg1 = new ImageIcon(defimg1);
				lbphoto.setIcon(defaultimg1);
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
			if (pstmt != null)
				pstmt.close();
		}
		catch (SQLException e1) {
			e1.printStackTrace();
		}	

	}

	private void insertMember(){

		//화면에서 사용자가 입력한 내용을 얻는다.
		MemberDTO dto = getViewData();
		MemberDAO dao = new MemberDAO();       
		boolean ok = dao.insertMember(dto);

		if(ok){
			JOptionPane.showMessageDialog(this, "축하합니다  가입성공"); //가입완료 메시지 띄우기
			dispose();

		}else{
			JOptionPane.showMessageDialog(this, "가입 실패ㅜㅜ");
		}
	}

	public MemberDTO getViewData(){

		//화면에서 사용자가 입력한 내용을 얻는다.
		//입력한것을 데이터베이스에 넣는다.
		MemberDTO dto = new MemberDTO();
		String id = textFieldid.getText();
		String pw = passwordField.getText();
		String name = textFieldname.getText();
		String birth = textFieldbirth.getText();
		String address = textFieldaddress.getText();
		String address1 = textFieldaddress_1.getText();
		String address2 = textFieldaddress_2.getText();
		String email = textFieldemail.getText()+(String)comboBoxemail.getSelectedItem();
		String tel = Hyphen.phone(textFieldtel.getText());


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
			ee.printStackTrace();
		}

	}




}
