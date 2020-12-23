package Client;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class MemberDAO {
	 
    private static final String DRIVER
        = "oracle.jdbc.driver.OracleDriver";
    private static final String URL
        = "jdbc:oracle:thin:@localhost:1521:hanle";
   
    private static final String USER = "hanle"; //DB ID
    private static final String PASS = "0253"; //DB 패스워드
    MemberList mList;
   
    public MemberDAO() {
    	   
    }
   
    public MemberDAO(MemberList mList){
        this.mList = mList;
    }
    
    /**DB연결 메소드*/
    public Connection getConn(){
        Connection con = null;
       
        try {
            Class.forName(DRIVER); //1. 드라이버 로딩
            con = DriverManager.getConnection(URL,USER,PASS); //2. 드라이버 연결
           
        } catch (Exception e) {
            e.printStackTrace();
        }
       
        return con;
    }
   
    public MemberDTO getMemberDTO(String id){
        
        MemberDTO dto = new MemberDTO();
       
        Connection con = null;       //연결
        PreparedStatement ps = null; //명령
        ResultSet rs = null;         //결과
        ResultSet rs2 = null;
        Statement stmt2 = null;
        
    	ImageIcon originalIcon;
    	InputStream is;
    	FileInputStream fis;
       
        try {
           
            con = getConn();
            String sql = "select * from chatuser1 where id=?";
            ps = con.prepareStatement(sql);
            stmt2 = con.createStatement();
            ps.setString(1, id);
           
            rs = ps.executeQuery();
            rs2 = stmt2.executeQuery("select image from chatuser1 where id = '" + id + "'"); 

            
            if(rs.next()){
            	dto.setId(rs.getString("id"));
                dto.setPw(rs.getString("pw"));
                dto.setName(rs.getString("name"));
                dto.setBirth(rs.getString("birth"));
                dto.setAddress(rs.getString("address"));
                dto.setAddress1(rs.getString("address1"));
                dto.setAddress2(rs.getString("address2"));
                dto.setEmail(rs.getString("email"));
                dto.setTel(Hyphen.phone(rs.getString("tel")));
                dto.setGender(rs.getString("gender"));
                dto.setPhoto(rs.getString("photo"));

				
			}
        } catch (Exception e) {
            e.printStackTrace();
        }      
       
        return dto;    
    }
    
    /**멤버리스트 출력*/
    public Vector getMemberList(){
       
        Vector data = new Vector();  //Jtable에 값을 쉽게 넣는 방법 1. 2차원배열   2. Vector 에 vector추가
       
           
        Connection con = null;       //연결
        PreparedStatement ps = null; //명령
        ResultSet rs = null;         //결과
       
        try{
           
            con = getConn();
            String sql = "select * from chatuser1 order by name asc";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
           
            while(rs.next()){
            	String id = rs.getString("id");
                String pw = rs.getString("pw");
                String name = rs.getString("name");
                String birth = rs.getString("birth");
                String address = rs.getString("address");
                String address1 = rs.getString("address1");
                String address2 = rs.getString("address2");
                String email = rs.getString("email");
                String tel = Hyphen.phone(rs.getString("tel"));
                String gender = rs.getString("gender");
                String photo = rs.getString("photo");
                
               
                Vector row = new Vector();
                row.add(id);
                row.add(pw);
                row.add(name);
                row.add(birth);
                row.add(address);
                row.add(address1);
                row.add(address2);
                row.add(email);
                row.add(tel);
                row.add(gender);
                row.add(photo);
                
               
                data.add(row);             
            }//while
        }catch(Exception e){
            e.printStackTrace();
        }
        return data;
    }//getMemberList()
   
    /**회원 등록*/
    public boolean insertMember(MemberDTO dto){
       
        boolean ok = false;
       
        Connection con = null;       //연결
        PreparedStatement ps = null; //명령
       
        try{
        	File f = new File(dto.getPhoto());
        	FileInputStream fis = new FileInputStream(f);
           
            con = getConn();
            String sql = "insert into chatuser1(" +
                    "id,pw,name,birth,address,address1,address2,email,tel,gender,photo,image) "+
                    "values(?,?,?,?,?,?,?,?,?,?,?,?)";
       
            ps = con.prepareStatement(sql);
            
            ps.setString(1, dto.getId());
            ps.setString(2, dto.getPw());
            ps.setString(3, dto.getName());
            ps.setString(4, dto.getBirth());
            ps.setString(5, dto.getAddress());
            ps.setString(6, dto.getAddress1());
            ps.setString(7, dto.getAddress2());
            ps.setString(8, dto.getEmail());  
        	ps.setString(9, Hyphen.phone(dto.getTel()));
       		ps.setString(10, dto.getGender());
       		ps.setString(11, dto.getPhoto());
       		ps.setBinaryStream(12, fis, (int)f.length());
       		
       		int r = ps.executeUpdate(); //실행 -> 저장
    
           
            if(r>0){
                System.out.println("가입 성공");   
                ok=true;
            }else{
                System.out.println("가입 실패");
            }
           
               
           
        }catch(Exception e){
            e.printStackTrace();
        }
       
        return ok;
    }//insertMmeber
    
    /**회원정보 수정*/
    public boolean updateMember(MemberDTO vMem){
        
        boolean ok = false;
        
        Connection con = null;
        PreparedStatement ps = null;
        try{
        	
        	File f = new File(vMem.getPhoto());
        	FileInputStream fis = new FileInputStream(f);
        	
            con = getConn();           
            String sql = "update chatuser1 set name=?, birth=?, address=?, address1=?, address2=?, email=?, tel=?, gender=?, photo=?, image=?, pw=?"
                    + "where id=?";
           
            ps = con.prepareStatement(sql);
        
            ps.setString(1, vMem.getName());
            ps.setString(2, vMem.getBirth());
            ps.setString(3, vMem.getAddress());
            ps.setString(4, vMem.getAddress1());
            ps.setString(5, vMem.getAddress2());
            ps.setString(6, vMem.getEmail());  
            ps.setString(7, vMem.getTel());
            ps.setString(8, vMem.getGender());
            ps.setString(9, vMem.getPhoto());
            ps.setBinaryStream(10, fis, (int)f.length());
            ps.setString(11, vMem.getPw());
            ps.setString(12, vMem.getId());
            
            int r = ps.executeUpdate(); //실행 -> 수정
            // 1~n: 성공 , 0 : 실패
           
            if(r>0) 
            	ok = true; //수정이 성공되면 ok값을 true로 변경
            else
            	System.out.println("실패 ㅜㅜ");
           
        }catch(Exception e){
            e.printStackTrace();
        }
       
        return ok;
    }
    
    /**회원정보 삭제 :
     *tip: 실무에서는 회원정보를 Delete 하지 않고 탈퇴여부만 체크한다.*/
    public boolean deleteMember(String id, String pw){
       
        boolean ok =false ;
        Connection con =null;
        PreparedStatement ps =null;
       
        try {
            con = getConn();
            String sql = "delete from chatuser1 where id=? and pw=?";
           
            ps = con.prepareStatement(sql);
            ps.setString(1, id);
            ps.setString(2, pw);
            int r = ps.executeUpdate(); // 실행 -> 삭제
           
            if (r>0) 
            	ok=true; //삭제됨;
           
        } catch (Exception e) {
            System.out.println(e + "-> 오류발생");
        }      
        return ok;
    }
   
    /**DB데이터 다시 불러오기*/   
    public void userSelectAll(DefaultTableModel model) {
       
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
       
        try {
            con = getConn();
            String sql = "select * from chatuser1 order by name asc";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
           
            // DefaultTableModel에 있는 데이터 지우기
            for (int i = 0; i < model.getRowCount();) {
                model.removeRow(0);
            }
 
            while (rs.next()) {
                Object data[] = { 
                		rs.getString(1), 
                		rs.getString(2),
                        rs.getString(3), 
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        Hyphen.phone(rs.getString(9)),
                        rs.getString(10),
                        rs.getString(11)};
 
                model.addRow(data);                
            }
 
        } catch (SQLException e) {
            System.out.println(e + "=> userSelectAll fail");
        } finally{
           
            if(rs!=null)
                try {
                    rs.close();
                } catch (SQLException e2) {
                    // TODO Auto-generated catch block
                    e2.printStackTrace();
                }
            if(ps!=null)
                try {
                    ps.close();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            if(con!=null)
                try {
                    con.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        }
    }
   

}
