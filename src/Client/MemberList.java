package Client;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class MemberList extends JFrame implements MouseListener, ActionListener{
   
    Vector v;  
    Vector cols;
    DefaultTableModel model;
    JTable jTable;
    JScrollPane pane;
    JPanel pbtn;
    JButton btnInsert;
    private JTextField textField;
    private JLabel lblNewLabel;
    private JLabel lbf;
    JButton btnNewButton ;
    JComboBox comboBox = new JComboBox();  
    
    private String driver = "oracle.jdbc.driver.OracleDriver";       
	private String url =  "jdbc:oracle:thin:@localhost:1521:hanle";         // @호스트 IP : 포트 : SID
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;   // 리턴받아 사용할 객체 생성 ( select에서 보여줄 때 필요 )
	
    
    LabelImage li;
   
    public MemberList(){
        super("회원관리");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(255, 180, 203));
        setBounds(100, 100, 0, 0);
        //v=getMemberList();
        //MemberDAO
        MemberDAO dao = new MemberDAO();
        v = dao.getMemberList();
        cols = getColumn();
       
        //public DefaultTableModel()
        //public DefaultTableModel(int rowCount, int columnCount)
        //public DefaultTableModel(Vector columnNames, int rowCount)
        //public DefaultTableModel(Object[] columnNames, int rowCount)
        //public DefaultTableModel(Vector data,Vector columnNames)
        //public DefaultTableModel(Object[][] data,Object[] columnNames)
       
        model = new DefaultTableModel(v, cols);
        getContentPane().setLayout(null);
       
        //JTable()
        //JTable(int numRows, int numColumns)
        //JTable(Object[][] rowData, Object[] columnNames)
        //JTable(TableModel dm)
        //JTable(TableModel dm, TableColumnModel cm)
        //JTable(TableModel dm, TableColumnModel cm, ListSelectionModel sm)
        //JTable(Vector rowData, Vector columnNames)
       
        //jTable = new JTable(v,cols);
        jTable = new JTable(model);
        jTable.setBackground(new Color(255,255,255));
        pane = new JScrollPane(jTable);
        pane.setBounds(0, 70, 834, 302);
        getContentPane().add(pane);
       
        pbtn = new JPanel();
        pbtn.setBackground(new Color(252, 187, 209));
        pbtn.setBounds(0, 0, 834, 71);
        pbtn.setLayout(null);
        btnInsert = new JButton("회원가입");
        btnInsert.setBackground(new Color(255, 239, 213));
        btnInsert.setBounds(543, 13, 101, 42);
        pbtn.add(btnInsert);
        getContentPane().add(pbtn);
        
        textField = new JTextField();
        textField.setBounds(250, 13, 195, 42);
        pbtn.add(textField);
        textField.setColumns(10);
        
        btnNewButton = new JButton("검색");
        btnNewButton.setBackground(new Color(255, 239, 213));
        btnNewButton.setBounds(444, 13, 101, 42);
        pbtn.add(btnNewButton);
        
        lblNewLabel = new JLabel("회원검색");
        lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 18));
        lblNewLabel.setBounds(61, 7, 85, 48);
        pbtn.add(lblNewLabel);
        
        lbf = new JLabel(li.Labelimage("C:\\Users\\0\\Desktop\\자바채팅프로그램\\yellowF.png", 50, 50));
        lbf.setBounds(0, 3, 63, 62);
        pbtn.add(lbf);
        
        
        String[] select= {"전체보기", "아이디", "이름" };
		comboBox = new JComboBox(select);
		comboBox.setBackground(new Color(255, 235, 205));
		comboBox.setBounds(152, 13, 101, 42);
        pbtn.add(comboBox);
       
       
        jTable.addMouseListener(this); //리스너 등록
        btnInsert.addActionListener(this); //회원가입버튼 리스너 등록
        btnNewButton.addActionListener(this);
        
        setSize(850,431);
        setVisible(true);
    }
    
    public Vector getColumn(){
        Vector col = new Vector();
        col.add("아이디");
        col.add("비밀번호");
        col.add("이름");
        col.add("생일");
        col.add("우편번호");
        col.add("주소");
        col.add("나머지 주소");
        col.add("이메일");
        col.add("전화");
        col.add("성별");
        col.add("사진경로");
        
        return col;
    }//getColumn
   
    
    public void jTableRefresh(){
        
        MemberDAO dao = new MemberDAO();
        DefaultTableModel model= new DefaultTableModel(dao.getMemberList(), getColumn());
        jTable.setModel(model);    
       
    }
    
    public static void main(String[] args ) {
    	new MemberList();
    }
    
    private void selectid(String id){        // 테이블에 보이기 위해 검색
 
		model.setRowCount(0);		//테이블초기화
		String query = "select * from chatuser1 where id = '" + id + "'";     
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url, "hanle", "0253");
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery(); // 리턴받아와서 데이터를 사용할 객체생성

			while(rs.next()){            // 각각 값을 가져와서 테이블값들을 추가
				model.addRow(new Object[]{rs.getString("id"),rs.getString("pw"),
						rs.getString("name"), rs.getString("birth"), rs.getString("address"),
						rs.getString("address1"), rs.getString("address2"), rs.getString("email"), 
						rs.getString("tel"), rs.getString("gender"), rs.getString("photo")});
			}
		}
		catch(Exception e){
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

    private void selectname(String name){        // 테이블에 보이기 위해 검색
    	 
		model.setRowCount(0);		//테이블초기화
		String query = "select * from chatuser1 where name = '" + name + "'";     
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url, "hanle", "0253");
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery(); // 리턴받아와서 데이터를 사용할 객체생성

			while(rs.next()){            // 각각 값을 가져와서 테이블값들을 추가
				model.addRow(new Object[]{rs.getString("id"),rs.getString("pw"),rs.getString("name"), rs.getString("birth"), rs.getString("address"),
						rs.getString("address1"), rs.getString("address2"), rs.getString("email"), rs.getString("tel"), rs.getString("gender"), rs.getString("photo")});
			}
		}
		catch(Exception e){
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

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnInsert ){
            new MemberProc(this);
           
            /*테스트*/
            //dao = new MemberDAO();           
            //dao.userSelectAll(model);
            //model.fireTableDataChanged();
            //jTable.updateUI();           
            //jTable.requestFocusInWindow();
		}
		if(e.getSource() == btnNewButton ){
			if((String)comboBox.getSelectedItem() == "아이디") {
				selectid(textField.getText());
			}
			if((String)comboBox.getSelectedItem() == "이름") {
				selectname(textField.getText());
			}
			if((String)comboBox.getSelectedItem() == "전체보기") {
				jTableRefresh();
			}
		}
		
	}


	public void mousedbClicked(MouseEvent e) {
		 
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}//end 생성자

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int r = jTable.getSelectedRow();
        String id = (String) jTable.getValueAt(r, 0);
        MemberProc mem = new MemberProc(id,this); //아이디를 인자로 수정창 생성
		
	}
}