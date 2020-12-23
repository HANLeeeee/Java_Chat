package Client;


import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class ZipSearch extends JFrame {

	private JPanel contentPane;
    private JTable table;
    private JComboBox comboBox;
    private JComboBox comboBox_1;
    private JComboBox comboBox_2;
   
    private Connection conn = null;
    private PreparedStatement pstmt = null;      
    private ResultSet rs = null;         
    private JScrollPane scrollPane;
    private JPanel panel;
    private JTextField tfDong;
   
    /**
     * Launch the application.
     */
    public static void main(String[] args) {   
 
           EventQueue.invokeLater(new Runnable() {
                   public void run() {
                          try {
                                  ZipSearch frame = new ZipSearch();
                                  frame.setVisible(true);
                          } catch (Exception e) {
                                  e.printStackTrace();
                          }
                   }
           });
    }

    /**
     * Create the frame.
     */
    public ZipSearch() {
          
           setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           setBounds(100, 100, 628, 515);
           contentPane = new JPanel();
           contentPane.setBackground(new Color(230, 230, 250));
           contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
           setContentPane(contentPane);
           contentPane.setLayout(null);
          
           panel = new JPanel();
           panel.setBackground(new Color(230, 230, 250));
           panel.setBorder(new TitledBorder(null, "우편번호 검색", TitledBorder.LEADING, TitledBorder.TOP, null, null));
           panel.setBounds(6, 10, 594, 70);
           contentPane.add(panel);
           panel.setLayout(null);
          
           scrollPane = new JScrollPane();
           scrollPane.setBounds(12, 153, 588, 313);
           contentPane.add(scrollPane);
          
           table = new JTable();
           table.setBackground(new Color(255, 228, 225));
           table.setModel(new DefaultTableModel(
                   new Object[][] {
                          {" ", " ", " ", " ", " ", " ", " ", " "},
                   },
                   new String[] {
                          "일련번호", "우편번호", "시도", "구.군", "동", "리", "빌딩", "번지"
                   }
           ) {
                   boolean[] columnEditables = new boolean[] {
                          false, false, false, false, false, false, false, false
                   };
                   public boolean isCellEditable(int row, int column) {
                          return columnEditables[column];
                   }	//더블클릭하면 편집이 불가능하게함
           });
          
           scrollPane.setViewportView(table);
           
           table.addMouseListener(new MouseAdapter() {
        	   @Override
        	   public void mouseClicked(MouseEvent e) {
        		   if (e.getClickCount() == 2 && !e.isConsumed()) {
        			   if(JoinUI.ziptmp==true) {
        				   JoinUI.textFieldaddress.setText((String) table.getValueAt(table.getSelectedRow(), 1));

        				   String sido = (String) table.getValueAt(table.getSelectedRow(), 2);
        				   String gugun = (String) table.getValueAt(table.getSelectedRow(), 3);
        				   String dong = (String) table.getValueAt(table.getSelectedRow(), 4);
        				   String ri = (String) table.getValueAt(table.getSelectedRow(), 5);
        				   String bldg = (String) table.getValueAt(table.getSelectedRow(), 6);

        				   if(ri == null) {
        					   ri = (" ");
        					   if(bldg == null) { 
        						   bldg = (" ");
        						   JoinUI.textFieldaddress_1.setText(sido + " " + gugun + " " + dong + " " + ri + " " + bldg);
        					   }
        					   JoinUI.textFieldaddress_1.setText(sido + " " + gugun + " " + dong + " " + ri + " " + bldg);
        				   }
        				   else {
        					   if(bldg == null) {
        						   bldg = (" ");
        						   JoinUI.textFieldaddress_1.setText(sido + " " + gugun + " " + dong + " " + ri + " " + bldg);
        					   }
        					   else 
        						   JoinUI.textFieldaddress_1.setText(sido + " " + gugun + " " + dong + " " + ri + " " + bldg);
        				   }
        				   
        				   JoinUI.ziptmp = false;
        			   }
        			   else {
        				   MemberProc.textFieldaddress.setText((String) table.getValueAt(table.getSelectedRow(), 1));

        				   String sido = (String) table.getValueAt(table.getSelectedRow(), 2);
        				   String gugun = (String) table.getValueAt(table.getSelectedRow(), 3);
        				   String dong = (String) table.getValueAt(table.getSelectedRow(), 4);
        				   String ri = (String) table.getValueAt(table.getSelectedRow(), 5);
        				   String bldg = (String) table.getValueAt(table.getSelectedRow(), 6);

        				   if(ri == null) {
        					   ri = (" ");
        					   if(bldg == null) { 
        						   bldg = (" ");
        						   MemberProc.textFieldaddress_1.setText(sido + " " + gugun + " " + dong + " " + ri + " " + bldg);
        					   }
        					   MemberProc.textFieldaddress_1.setText(sido + " " + gugun + " " + dong + " " + ri + " " + bldg);
        				   }
        				   else {
        					   if(bldg == null) {
        						   bldg = (" ");
        						   MemberProc.textFieldaddress_1.setText(sido + " " + gugun + " " + dong + " " + ri + " " + bldg);
        					   }
        					   else 
        						   MemberProc.textFieldaddress_1.setText(sido + " " + gugun + " " + dong + " " + ri + " " + bldg);
        				   }


        			   }
        			
        			dispose();
        		  }
        	   }
           });

          
          
           //첫번째 combobox 생성
           comboBox = new JComboBox(); 
           comboBox.setBackground(new Color(255, 250, 250));
           
           comboBox.setBounds(103, 21, 100, 33);
           panel.add(comboBox);
           comboBox.addItem("시.도 선택");
          
           displaySido();
           //시.도 콤보박스=============================================
           comboBox.addItemListener(new ItemListener() {
                   public void itemStateChanged(ItemEvent e) {
           if(e.getStateChange()==ItemEvent.SELECTED)
                   selectSido(comboBox.getSelectedItem().toString());
                         
                   }
           });
           comboBox.setToolTipText("");
          
          
           JLabel label = new JLabel("시.도");
           label.setBounds(12, 27, 100, 20);
           panel.add(label);
           label.setHorizontalAlignment(SwingConstants.CENTER);
          
           //구.군 ComboBox=============================================
           comboBox_1 = new JComboBox();
           comboBox_1.setBackground(new Color(255, 250, 250));
           comboBox_1.setBounds(278, 21, 100, 33);
           panel.add(comboBox_1);
          
           JLabel label_1 = new JLabel("구.군");
           label_1.setBounds(193, 27, 100, 20);
           panel.add(label_1);
           label_1.setHorizontalAlignment(SwingConstants.CENTER);
          
           comboBox_1.addItemListener(new ItemListener() {
                   public void itemStateChanged(ItemEvent e) {
                          if(e.getStateChange()==ItemEvent.SELECTED)
                                  selectGugun(comboBox.getSelectedItem().toString() ,comboBox_1.getSelectedItem().toString());
                   }
           });
          
           //동 ComboBox=============================================
           comboBox_2 = new JComboBox();
           comboBox_2.setBackground(new Color(255, 250, 250));
           comboBox_2.setBounds(441, 21, 100, 33);
           panel.add(comboBox_2);
          
           JLabel label_2 = new JLabel("동");
           label_2.setBounds(370, 27, 100, 20);
           panel.add(label_2);
           label_2.setHorizontalAlignment(SwingConstants.CENTER);
           
           comboBox_2.addItemListener(new ItemListener() {
               public void itemStateChanged(ItemEvent e) {
                      if(e.getStateChange()==ItemEvent.SELECTED)
                     
                      //table에 집어넣기 실행=====================================
                      selectDong(comboBox.getSelectedItem().toString(), comboBox_1.getSelectedItem().toString(), comboBox_2.getSelectedItem().toString());
               }             
       });
           
//           동이름 검색 패널
           JPanel panel_1 = new JPanel();
           panel_1.setBackground(new Color(230, 230, 250));
           panel_1.setBorder(new TitledBorder(null, "동이름 우편번호 검색", TitledBorder.LEADING, TitledBorder.TOP, null, null));
           panel_1.setBounds(6, 86, 594, 57);
           contentPane.add(panel_1);
           panel_1.setLayout(null);
           
           JLabel lblNewLabel = new JLabel("동이름");
           lblNewLabel.setBounds(131, 26, 36, 15);
           panel_1.add(lblNewLabel);
           
           tfDong = new JTextField(20);
           tfDong.setBounds(184, 17, 199, 34);
           tfDong.setBackground(new Color(255, 250, 250));
           panel_1.add(tfDong);
           tfDong.setColumns(10);
           
           JButton btnDongSearce = new JButton("검색");
           btnDongSearce.setBounds(384, 16, 73, 35);
           btnDongSearce.setBackground(new Color(255, 192, 203));
           panel_1.add(btnDongSearce);
           btnDongSearce.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				searchDong(tfDong.getText());
			}
		});
           
    }

   
    
    
    
    
//    동이름으로 검색하는 메소드 
    public void searchDong(String dongName){
    	//선언
        ZipDao controller = new ZipDao();
        //DB연결
        controller.connection();   
        
        //
        ArrayList<ZipDto> addressList = controller.searchKeyDong(dongName);
        Object[][] arrAdd = new Object[addressList.size()][8];
        for(int i = 0 ; i < addressList.size() ; i++){
                ZipDto zipcode = addressList.get(i);
                
                arrAdd[i][0] = zipcode.getSeq();
                arrAdd[i][1] = zipcode.getZipcode();
                arrAdd[i][2] = zipcode.getSido();
                arrAdd[i][3] = zipcode.getGugun();
                arrAdd[i][4] = zipcode.getDong();
                arrAdd[i][5] = zipcode.getRi();
                arrAdd[i][6] = zipcode.getBldg();
                arrAdd[i][7] = zipcode.getBunji();
               
                table.setModel(new ZipTableModel(arrAdd));
                
        }             
        //DB연결 해제
        controller.disconnection();
    }
    
    //프로그램 시작시 시.도 보여주기====================================================================
    public void displaySido(){
           //선언
           ZipDao controller = new ZipDao();
           //DB연결
           controller.connection();             
           //
           ArrayList<ZipDto> sidoList = controller.searchSido();
           for(int i = 0 ; i < sidoList.size() ; i++){
                   ZipDto zipcode = sidoList.get(i);
                   comboBox.addItem(zipcode.getSido());
           }             
           //DB연결 해제
           controller.disconnection();
    }
    //sido 선택(gugun 출력)====================================================================
    public void selectSido(String sido){

           ZipDao controller = new ZipDao();
           //DB연결
           controller.connection();             
           //
           ArrayList<ZipDto> gugunList = controller.searchGugun(sido);
           comboBox_1.removeAllItems();
           comboBox_2.removeAllItems();
           comboBox_1.addItem("구.군 선택");
           for(int i = 0 ; i < gugunList.size() ; i++){
                   ZipDto zipcode = gugunList.get(i);
                   comboBox_1.insertItemAt(zipcode.getGugun(), i);
           }
           table.setModel(new ZipTableModel());
           //DB연결 해제
           controller.disconnection();
    }      
    //gugun 선택(dong 출력)====================================================================
    public void selectGugun(String sido, String gugun){
         
           ZipDao controller = new ZipDao();
           //DB연결
           controller.connection();             
           //
           ArrayList<ZipDto> dongList = controller.searchDong(sido, gugun);
           comboBox_2.removeAllItems();
           comboBox_2.addItem("동 선택");
           for(int i = 0 ; i < dongList.size() ; i++){
                   ZipDto zipcode = dongList.get(i);
                   comboBox_2.insertItemAt(zipcode.getDong(),i);
           }
           table.setModel(new ZipTableModel());
           //DB연결 해제
           controller.disconnection();                 
    }
   
    //마지막 Dong 선택(테이블에 출력)====================================================================
    public void selectDong(String sido, String gugun, String dong){
    	
           ZipDao controller = new ZipDao();
           //DB연결
           controller.connection();             
           //
           ArrayList<ZipDto> addressList = controller.searchAddress(sido, gugun, dong);
          
           Object[][] arrAdd = new Object[addressList.size()][8];
          
           for(int i = 0 ; i < addressList.size() ; i++){
                   ZipDto zipcode = addressList.get(i);
                   //출력!
                   //System.out.println(zipcode.getSeq() + " " + zipcode.getZipcode()+ " " +zipcode.getSido()+ " " +zipcode.getGugun()+ " " +zipcode.getDong() + " " + zipcode.getRi() + " " + zipcode.getBldg() + " " + zipcode.getBunji());                      
                   //테이블에 넣기!
                   arrAdd[i][0] = zipcode.getSeq();
                   arrAdd[i][1] = zipcode.getZipcode();
                   arrAdd[i][2] = zipcode.getSido();
                   arrAdd[i][3] = zipcode.getGugun();
                   arrAdd[i][4] = zipcode.getDong();
                   arrAdd[i][5] = zipcode.getRi();
                   arrAdd[i][6] = zipcode.getBldg();
                   arrAdd[i][7] = zipcode.getBunji();
                  
                   table.setModel(new ZipTableModel(arrAdd));
                   //System.out.println("table Setting ");
           }
           //DB연결 해제
           controller.disconnection();
          
    }

	
}
