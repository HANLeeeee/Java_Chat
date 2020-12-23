package Client;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class MemberUI extends JFrame implements ActionListener{

	LabelImage li;
	
	private JPanel contentPane;
	JButton btnmem = new JButton("회원 관리");
	JButton btnmemchat = new JButton("채팅 관리");
	JButton btnmemlogin = new JButton("<html>로그인<br>&nbsp&nbsp관리<br></html>");
	JLabel lbtitle = new JLabel("관리자 모드");
	
	public MemberUI() {
		init();
	}

	public void init() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 356, 420);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(252, 187, 209));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lbimg = new JLabel(li.Labelimage("C:\\Users\\0\\Desktop\\자바채팅프로그램\\dark.png", 340, 223));
		lbimg.setBounds(0, 66, 340, 223);
		contentPane.add(lbimg);

		//회원관리버튼
		btnmem.setForeground(new Color(0, 0, 0));
		btnmem.setBackground(new Color(255, 230, 240));
		btnmem.setBounds(0, 303, 118, 69);
		btnmem.addActionListener(this);
		contentPane.add(btnmem);

		//채팅관리버튼
		btnmemchat.setForeground(new Color(0, 0, 0));
		btnmemchat.setBackground(new Color(255, 230, 240));
		btnmemchat.setBounds(112, 303, 118, 69);
		btnmemchat.addActionListener(this);
		contentPane.add(btnmemchat);

		//로그인관리버튼
		btnmemlogin.setForeground(new Color(0, 0, 0));
		btnmemlogin.setBackground(new Color(255, 230, 240));
		btnmemlogin.setBounds(222, 303, 118, 69);
		btnmemlogin.addActionListener(this);
		contentPane.add(btnmemlogin);

		//관리자모드 제목 라벨
		lbtitle.setForeground(new Color(0, 0, 0));
		lbtitle.setHorizontalAlignment(SwingConstants.CENTER);
		lbtitle.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		lbtitle.setBounds(97, 10, 164, 48);
		contentPane.add(lbtitle);
		
		setVisible(true);
	}


	public static void main(String[] args) {
		new MemberUI();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnmem) {
			new MemberList();
		}
		
		if(e.getSource() == btnmemchat) {
			new MemberchatDB();
		}
		
		if(e.getSource() == btnmemlogin) {
			new MemberloginDB();
		}
		
	}
}
