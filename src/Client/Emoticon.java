package Client;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.FileDialog;


public class Emoticon extends JFrame {
	static JFrame b = new JFrame("이모티콘박스");
	private JPanel a = new JPanel();
	static String emo1 = "C:\\Users\\0\\Desktop\\자바채팅프로그램\\이모티콘\\1.jpg";
	static String emo2 = "C:\\Users\\0\\Desktop\\자바채팅프로그램\\이모티콘\\2.jpg";
	static String emo3 = "C:\\Users\\0\\Desktop\\자바채팅프로그램\\이모티콘\\3.jpg";
	static String emo4 = "C:\\Users\\0\\Desktop\\자바채팅프로그램\\이모티콘\\4.jpg";
	static String emo5 = "C:\\Users\\0\\Desktop\\자바채팅프로그램\\이모티콘\\5.jpg";
	static String emo6 = "C:\\Users\\0\\Desktop\\자바채팅프로그램\\이모티콘\\6.jpg";
	static String emo7 = "C:\\Users\\0\\Desktop\\자바채팅프로그램\\이모티콘\\7.jpg";
	static String emo8 = "C:\\Users\\0\\Desktop\\자바채팅프로그램\\이모티콘\\8.jpg";
	static String emo9 = "C:\\Users\\0\\Desktop\\자바채팅프로그램\\이모티콘\\9.jpg";
	
	
	LabelImage li;
	
	Emoticon(){
		Emoticon_init();

	}

	void Emoticon_init() {
		b.setBounds(550, 420, 244, 362);
		a.setBackground(new Color(230, 230, 250));
		a.setBorder(new EmptyBorder(5, 5, 5, 5));
		a.setLayout(null);
		b.getContentPane().add(a);
		JButton Image1 = new JButton(li.Labelimage(emo1, 60, 60));
		Image1.setHorizontalAlignment(SwingConstants.CENTER);
		Image1.setBounds(12, 106, 60, 60);
		a.add(Image1);

		JButton Image2 = new JButton(li.Labelimage(emo2, 60, 60));
		Image2.setHorizontalAlignment(SwingConstants.CENTER);
		Image2.setBounds(84, 106, 60, 60);
		a.add(Image2);

		JButton Image3 = new JButton(li.Labelimage(emo3, 60, 60));
		Image3.setHorizontalAlignment(SwingConstants.CENTER);
		Image3.setBounds(156, 106, 60, 60);
		a.add(Image3);

		JButton Image4 = new JButton(li.Labelimage(emo4, 60, 60));
		Image4.setHorizontalAlignment(SwingConstants.CENTER);
		Image4.setBounds(12, 176, 60, 60);
		a.add(Image4);

		JButton Image5 = new JButton(li.Labelimage(emo5, 60, 60));
		Image5.setHorizontalAlignment(SwingConstants.CENTER);
		Image5.setBounds(84, 176, 60, 60);
		a.add(Image5);

		JButton Image6 = new JButton(li.Labelimage(emo6, 60, 60));
		Image6.setHorizontalAlignment(SwingConstants.CENTER);
		Image6.setBounds(156, 176, 60, 60);
		a.add(Image6);

		JButton Image7 = new JButton(li.Labelimage(emo7, 60, 60));
		Image7.setHorizontalAlignment(SwingConstants.CENTER);
		Image7.setBounds(12, 246, 60, 60);
		a.add(Image7);

		JButton Image8 = new JButton(li.Labelimage(emo8, 60, 60));
		Image8.setHorizontalAlignment(SwingConstants.CENTER);
		Image8.setBounds(84, 246, 60, 60);
		a.add(Image8);
		
		JButton Image9 = new JButton(li.Labelimage(emo9, 60, 60));
		Image9.setHorizontalAlignment(SwingConstants.CENTER);
		Image9.setBounds(156, 246, 60, 60);
		a.add(Image9);

		JLabel lblNewLabel = new JLabel("\uC774 \uBAA8 \uD2F0 \uCF58");
		lblNewLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(50, 10, 123, 35);
		a.add(lblNewLabel);
		
		JLabel label = new JLabel(li.Labelimage("C:\\Users\\0\\Desktop\\자바채팅프로그램\\ogu.png", 50, 50));
		label.setBounds(12, 5, 50, 50);
		a.add(label);
		
		JLabel label_1 = new JLabel(li.Labelimage("C:\\Users\\0\\Desktop\\자바채팅프로그램\\ogu1.png", 50, 50));
		label_1.setBounds(166, 5, 50, 50);
		a.add(label_1);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 248, 255));
		panel.setBounds(0, 55, 228, 41);
		a.add(panel);
		panel.setLayout(null);
		
		JLabel label_2 = new JLabel("New label");
		label_2.setBounds(12, 0, 40, 40);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("New label");
		label_3.setBounds(64, 0, 40, 40);
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("New label");
		label_4.setBounds(119, 0, 40, 40);
		panel.add(label_4);

		Image1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				Client.send_message("Chatting/"+Client.My_Room+"/"+emo1);
			}  
		}); 
		Image2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				Client.send_message("Chatting/"+Client.My_Room+"/"+emo2);
			}  
		});
		Image3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				Client.send_message("Chatting/"+Client.My_Room+"/"+emo3);

			}
		});
		Image4.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				Client.send_message("Chatting/"+Client.My_Room+"/"+emo4);
			} 
		});
		Image5.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				Client.send_message("Chatting/"+Client.My_Room+"/"+emo5);
			}
		});
		Image6.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				Client.send_message("Chatting/"+Client.My_Room+"/"+emo6);
			}
		});
		Image7.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				Client.send_message("Chatting/"+Client.My_Room+"/"+emo7);
			}
		});
		Image8.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				Client.send_message("Chatting/"+Client.My_Room+"/"+emo8);
			}
		});
		Image9.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				Client.send_message("Chatting/"+Client.My_Room+"/"+emo9);
			}
		});
		
		

		b.setLocation(Client.Chat_GUI.getLocationOnScreen().x - 245, Client.Chat_GUI.getLocationOnScreen().y + 192);
		b.setVisible(true);

	}

	public static void main(String[] args) {

		new Emoticon();

	}
	
	
	
}