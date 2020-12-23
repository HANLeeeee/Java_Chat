package Client;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class FindID  extends JFrame implements ActionListener{


	LabelImage li;

	private JPanel contentPane;
	JTextField textField;
	JTextField textField_1;
	JTextField textField_2;
	private JLabel lblNewLabel_1;
	private JLabel label;
	private JLabel label_1;
	private JButton btnNewButton;
	private JButton button;

	ProfileDB pd;

	FindID() {

		super("아이디 찾기");
		FindID();
	}

	public void FindID() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 278, 443);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField = new JTextField();
		textField.setBounds(78, 159, 154, 38);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(78, 207, 154, 38);
		contentPane.add(textField_1);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(78, 255, 154, 38);
		contentPane.add(textField_2);

		lblNewLabel_1 = new JLabel("이름");
		lblNewLabel_1.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(12, 159, 57, 38);
		contentPane.add(lblNewLabel_1);

		label = new JLabel("생년월일");
		label.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		label.setBounds(9, 207, 57, 38);
		contentPane.add(label);

		label_1 = new JLabel("전화번호");
		label_1.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		label_1.setBounds(9, 255, 57, 38);
		contentPane.add(label_1);


		button = new JButton("찾기");
		button.setBackground(new Color(214, 221, 247));
		button.setBounds(12, 314, 220, 38);
		contentPane.add(button);
		button.addActionListener(this);

		JLabel lblNewLabel = new JLabel(li.Labelimage("C:\\Users\\0\\Desktop\\자바채팅프로그램\\findid.jpg", 262, 405));
		lblNewLabel.setBounds(0, 0, 262, 405);
		contentPane.add(lblNewLabel);

		setVisible(true);

	}

	public void FindPW() {

	}

	public static void main(String[] args) {
		new FindID();

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource()== button) {		//찾기버튼을 누른다

			String NAME = textField.getText();
			String BIRTH = textField_1.getText();
			String TEL = textField_2.getText();

			String id = IDfind(NAME, BIRTH, TEL);
		}
	}

	public String IDfind(String name, String birth, String tel) {
		System.out.println(name + birth + tel);
		String url = "jdbc:oracle:thin:@localhost:1521:hanle";
		Connection con = null;  
		Properties info = null;
		Statement stmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt;

		String ID = "";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			info = new Properties();
			info.setProperty("user", "hanle");
			info.setProperty("password", "0253");
			con = DriverManager.getConnection(url, info);  

			String sql = "select *"
					+ " from chatuser1"
					+ " where name='" +  name + "' and birth = '" +  birth + "' and tel = '" + tel + "'"  ;   // 조건은 로그인 한 id와 같은 id를 가진 데이터

			pstmt = con.prepareStatement(sql);  
			rs = pstmt.executeQuery();

			if(rs.next()) {
				ID = rs.getString("id");
				System.out.println(ID);

				JOptionPane.showMessageDialog(this, "         찾으시는 아이디는 \n                '" + ID + " '  입니다");
				dispose();
				
				return ID;
			}
			else {
				JOptionPane.showMessageDialog(this, "입력하신 정보와 일치하는\n  아이디가 존재하지 않습니다.");
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
			}
		}
		catch(ClassNotFoundException cnfe){
			cnfe.printStackTrace();
		}
		catch(SQLException se){
			se.printStackTrace();
		}


		return ID;
	}
}

