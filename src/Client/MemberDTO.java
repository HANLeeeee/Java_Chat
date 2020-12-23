package Client;
import java.sql.Blob;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class MemberDTO {
	private String id;
    private String pw;
    private String name;
    private String birth;
    private String address;
    private String address1;
    private String address2;
    private String email;
    private String tel;
    private String gender;
    private String photo;
    private String image;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	
	 //DTO 객체 확인
    //이클립스팁 : toString() 자동생성: 우클릭 -> source -> Generate toString->[OK]
    @Override
    public String toString() {
        return "MemberDTO [id=" + id + ", pw=" + pw + ", name=" + name
        		+ ", birth=" + birth + ", address=" + address + ", address1=" + address1 + ", address2=" + address2
                + ", email=" + email + ", tel=" + tel + ", gender=" + gender + ", photo=" + photo + "]";
        
    }
 
}
