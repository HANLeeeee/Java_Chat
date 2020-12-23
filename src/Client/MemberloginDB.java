package Client;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

public class MemberloginDB extends JFrame implements ActionListener{

	// DB에서 스윙 화면으로 테이블 값 가져오기(select) , 저장하기(insert), 수정하기(update), 삭제하기(delete)
	//private static final long serialVersionUID = 1L;
	private JFrame frame = new JFrame();
	private JPanel panel_1;
	private JTextField textField;
	private JButton btnNewButton = new JButton("검색");
	private JButton button; //새로고침버튼
	private JLabel lbf;
	
	LabelImage li;

	private JButton jBtnAddRow = null;    // 테이블 한줄 추가 버튼
	private JButton jBtnSaveRow = null;    // 테이블 한줄 저장 버튼
	private JButton jBtnEditRow = null;    // 테이블 한줄 저장 버튼
	private JButton jBtnDelRow = null;        // 테이블 한줄 삭제 벅튼

	private JTable table;    
	private JScrollPane scrollPane;        // 테이블 스크롤바 자동으로 생성되게 하기

	private String driver = "oracle.jdbc.driver.OracleDriver";        
	private String url = "jdbc:oracle:thin:@localhost:1521:hanle";        // @호스트 IP : 포트 : SID
	private String colNames[] = {"아이디","로그인/로그아웃","시간"};  // 테이블 컬럼 값들
	private DefaultTableModel model = new DefaultTableModel(colNames, 0); //  테이블 데이터 모델 객체 생성

	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;   // 리턴받아 사용할 객체 생성 ( select에서 보여줄 때 필요 )
	


	public MemberloginDB() {
		frame.setTitle("로그인 관리 내역");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setBounds(100, 100, 760, 442);
		panel_1 =new JPanel();
		panel_1.setBackground(new Color(255, 180, 203));

		setBackground(new Color(255, 250, 240));
		frame.setContentPane(panel_1);
		panel_1.setLayout(null); // 레이아웃 배치관리자 삭제
		table = new JTable(model);  // 테이블에 모델객체 삽입
		table.addMouseListener(new JTableMouseListener());        // 테이블에 마우스리스너 연결
		scrollPane = new JScrollPane(table);            // 테이블에 스크롤 생기게 하기
		scrollPane.setLocation(3, 67);
		scrollPane.setSize(749, 349);

		panel_1.add(scrollPane);         

		JLabel lblNewLabel = new JLabel("로그인 관리 내역");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 22));
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setBounds(50, 10, 210, 40);
		panel_1.add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(254, 17, 193, 35);
		panel_1.add(textField);
		textField.setColumns(10);
		btnNewButton.setBackground(new Color(255, 235, 205));

		//검색버튼
		btnNewButton.setBounds(448, 17, 97, 35);
		btnNewButton.addActionListener(this);
		panel_1.add(btnNewButton);

		lbf = new JLabel(li.Labelimage("C:\\Users\\0\\Desktop\\자바채팅프로그램\\yellowF.png", 50, 50));
		lbf.setBounds(0, 3, 63, 62);
		panel_1.add(lbf);
		
		button = new JButton("새로고침");
		button.setBackground(new Color(255, 235, 205));
		button.setBounds(543, 17, 97, 35);
		button.addActionListener(this);
		panel_1.add(button);

		frame.setVisible(true); //윈도우창에 보이기 지정
		frame.setResizable(false); //윈도우창 크기 고정
		select();

	}

	private class JTableMouseListener implements MouseListener{    // 마우스로 눌려진값확인하기
		public void mouseClicked(java.awt.event.MouseEvent e) {    // 선택된 위치의 값을 출력

			JTable jtable = (JTable)e.getSource();
			int row = jtable.getSelectedRow();                // 선택된 테이블의 행값
			int col = jtable.getSelectedColumn();         // 선택된 테이블의 열값

			System.out.println(model.getValueAt(row, col));   // 선택된 위치의 값을 얻어내서 출력

		}
		public void mouseEntered(java.awt.event.MouseEvent e) {
		}
		public void mouseExited(java.awt.event.MouseEvent e) {    
		}
		public void mousePressed(java.awt.event.MouseEvent e) {
		}
		public void mouseReleased(java.awt.event.MouseEvent e) {
		}
	}

	private void select(){        // 테이블에 보이기 위해 검색

		String query = "select * from chatlogin";     
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url, "hanle", "0253");
			pstmt = con.prepareStatement(query);
			System.out.println(query);
			rs = pstmt.executeQuery(); // 리턴받아와서 데이터를 사용할 객체생성

			while(rs.next()){            // 각각 값을 가져와서 테이블값들을 추가
				System.out.println("은정이는 홀로롤");
				model.addRow(new Object[]{rs.getString("id"), rs.getString("login"), rs.getString("time")});
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}finally{
			try {
				rs.close(); 
				pstmt.close(); 
				con.close();   // 객체 생성한 반대 순으로 사용한 객체는 닫아준다.
			} catch (Exception e2) {}
		}
	}
	
	private void selectid(String id){        // 테이블에 보이기 위해 검색

		model.setRowCount(0);		//테이블초기화
		String query = "select * from chatlogin";     
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url, "hanle", "0253");
			pstmt = con.prepareStatement(query);
			System.out.println(query);
			rs = pstmt.executeQuery(); // 리턴받아와서 데이터를 사용할 객체생성

			while(rs.next()){            // 각각 값을 가져와서 테이블값들을 추가
				System.out.println("은정이는 홀로롤");
				model.addRow(new Object[]{rs.getString("id"), rs.getString("login"), rs.getString("time")});
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		finally{
			try {
				rs.close(); 
				pstmt.close(); 
				con.close();   // 객체 생성한 반대 순으로 사용한 객체는 닫아준다.
			} catch (Exception e2) {}
		}
	}


	public static void main(String[] args) {

		new MemberloginDB();
	}


	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == btnNewButton) {
			selectid(textField.getText());
		}
		
	}
}