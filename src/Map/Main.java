package Map;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.sql.ClientInfoStatus;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.SystemColor;


public class Main extends JFrame implements KeyListener, ActionListener, MouseListener{

   JTextField textField = new JTextField(30);
   JButton button = new JButton("검색");
   public static JButton share_btn = new JButton("위치공유");
   public static JPanel panel = new JPanel();
   ImageIcon mapicon;
   public static String mappath =  GoogleAPI.imageURL;
   private GoogleAPI googleAPI = new GoogleAPI(); 
   private JLabel googleMap = new JLabel(); //지도 이미지 넣는 곳
  

   public void setMap(String location) {
      googleAPI.download(location);
      googleMap.setIcon(googleAPI.getMap(location)); //googleAPI.getMap(location) = 지도 이미지
      googleAPI.fileDelete(location);
      mapicon = googleAPI.getMap(location);
      
      
      getContentPane().add(BorderLayout.SOUTH, googleMap);
      pack();
   }

   public Main() {
      getContentPane().setLayout(new BorderLayout());
      
      setResizable(false);
      setTitle("GoogleMap");
      textField.grabFocus();
      setLocationRelativeTo(null);
      setVisible(true);
      
      button.setBackground(new Color(255, 248, 220));
      share_btn.setBackground(new Color(255, 248, 220));
      share_btn.setVisible(false);
      
      panel.add(textField);
      panel.add(button);
      panel.add(share_btn);
      
      button.addMouseListener(this);
      textField.addKeyListener(this);
      

      getContentPane().add(BorderLayout.NORTH, panel);

      pack();

   }
   
   
   public static void main(String[] args) {
         new Main();

      }

   @Override
   public void keyPressed(KeyEvent e) {
      if(e.getSource() == textField) {
         if(e.getKeyCode() == 10)
         {
            button.doClick();
         }
      }

   }

   @Override
   public void keyReleased(KeyEvent e) {
      // TODO Auto-generated method stub

   }

   @Override
   public void keyTyped(KeyEvent e) {
      // TODO Auto-generated method stub

   }

   @Override
   public void actionPerformed(ActionEvent e) {
	   
      
   }

   @Override
   public void mouseClicked(MouseEvent arg0) {
      setMap(textField.getText());
      
   }

   @Override
   public void mouseEntered(MouseEvent arg0) {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void mouseExited(MouseEvent arg0) {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void mousePressed(MouseEvent arg0) {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void mouseReleased(MouseEvent arg0) {
      // TODO Auto-generated method stub
      
   }

}

