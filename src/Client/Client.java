package Client;

import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.font.TextAttribute;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

import Map.Main;
import Map.WeatherByGPSApplication;
import font.FontChooserDialog;

public class Client extends JFrame implements ActionListener, KeyListener, MouseListener {
	// 자동 import ctrl + shift + o


	LabelImage li;

	//Login init변수
	private JFrame Login_GUI = new JFrame();
	private JPanel Login_Panel;
	private JTextField textFieldID; 
	private JPasswordField textFieldPW; 
	private JButton btnLogin = new JButton("로그인"); //접속버튼
	private JButton btnJoin = new JButton("회원가입"); //접속버튼
	JMenuBar menuBar = new JMenuBar();
	JMenu mnNewMenu = new JMenu();
	JMenuItem mntmNewMenuItem = new JMenuItem();
	JMenuItem mntmNewMenuItem_1 = new JMenuItem();
	JLabel lbf;
	JLabel lbf1;
	private JLabel lbfindid;
	private JLabel lbfindpw;
	private JButton btnmem = new JButton("관리자모드");


	//Emo_init변수
	private JFrame Emo_GUI = new JFrame();
	private JPanel Emo_Panel;
	JLabel lbemo = new JLabel(li.Labelimage("C:\\Users\\0\\Desktop\\자바채팅프로그램\\emoticon.png", 65, 65));
	JLabel lbphto = new JLabel(li.Labelimage("C:\\Users\\0\\Desktop\\자바채팅프로그램\\photo.png", 65, 65));
	JLabel lbdocu = new JLabel(li.Labelimage("C:\\Users\\0\\Desktop\\자바채팅프로그램\\document.png", 65, 65));

	//U_init 변수
	//채팅방참여 유저변수
	private JFrame U_GUI = new JFrame();
	private JPanel U_Panel = new JPanel();
	JPanel panelchatuser = new JPanel();
	private JList Chat_list = new JList(); //채팅참여 유저 리스트
	JButton btnchatnote = new JButton("쪽지");

	JButton btnchatuser = new JButton("채팅 참여자");
	JPanel panelwait = new JPanel();
	private JList Wait_list = new JList(); // 초대화면에서의 전체 접속자 List
	JButton btninvite = new JButton("초대");
	JButton btnchatinvite = new JButton("");
	JButton ghfhf1 = new JButton(li.Labelimage("C:\\Users\\0\\Desktop\\자바채팅프로그램\\back.png", 30, 30));
	JButton ghfhf2 = new JButton(li.Labelimage("C:\\Users\\0\\Desktop\\자바채팅프로그램\\check.png", 30, 30));
	JLabel jl = new JLabel(li.Labelimage("C:\\Users\\0\\Desktop\\자바채팅프로그램\\fnehfdmswjd.jpg", 221, 363));

	//Main_init 변수
	static JPanel contentPane;
	JButton lbur = new JButton(li.Labelimage("C:\\Users\\0\\Desktop\\자바채팅프로그램\\iconur.png", 50, 45));
	JButton lbwcr = new JButton(li.Labelimage("C:\\Users\\0\\Desktop\\자바채팅프로그램\\setting.png", 50, 45));
	JButton lbpr = new JButton(li.Labelimage("C:\\Users\\0\\Desktop\\자바채팅프로그램\\iconpr.png", 60, 55));
	JButton lbwr = new JButton(li.Labelimage("C:\\Users\\0\\Desktop\\자바채팅프로그램\\iconwr.png", 50, 45));
	JMenu mnNewMenu1 = new JMenu();
	JMenuItem mntmlogout = new JMenuItem();

	//Chat init변수
	//채팅창 변수
	public static JFrame Chat_GUI = new JFrame();
	private JPanel Chat_Panel;
	JPanel panel = new JPanel();
	JPanel panel_1 = new JPanel();
	JPanel panel_2 = new JPanel();
	JScrollPane scrollPane = new JScrollPane();
	private final JTextPane Chat_area = new JTextPane();
	private JButton btnsend = new JButton(li.Labelimage("C:\\Users\\0\\Desktop\\자바채팅프로그램\\send.png", 45, 45));
	private JTextField textField= new JTextField();
	JButton btnemo = new JButton(li.Labelimage("C:\\Users\\0\\Desktop\\자바채팅프로그램\\monster.png", 42, 45));
	JLabel lbback = new JLabel(li.Labelimage("C:\\Users\\0\\Desktop\\자바채팅프로그램\\back.png", 35, 35));
	JLabel lbselect = new JLabel(li.Labelimage("C:\\Users\\0\\Desktop\\자바채팅프로그램\\setting.png", 35, 35));
	JLabel lbroomname = new JLabel();

	//방목록 변수
	JPanel panelwr = new JPanel();
	private JList Room_list = new JList(); // 전체 방목록 List
	private JButton btnEnter = new JButton("방 입장하기");
	private JButton btnCreate = new JButton("방 만들기");

	//유저 변수
	JPanel panelur = new JPanel();
	JScrollPane scrollPaneur = new JScrollPane();
	private JList User_list = new JList(); // 전체 접속자 List
	private JButton btnnote = new JButton("쪽지보내기");

	//대기실 변수
	JPanel panelwcr = new JPanel();
	private final JButton button = new JButton("내 글꼴변경"); //글꼴 변경
	private final JButton button_1 = new JButton("상대 글꼴변경");
	//private final JButton button_2 = new JButton("3");
	private final JButton button_3 = new JButton("채팅색 변경");
	private final JButton button_4 = new JButton("     ㄴ내 색"); //내 채팅색 변경
	private final JButton button_5 = new JButton("     ㄴ상대색"); //상대 채팅색 변경
	JLabel lblNewLabel23;
	JLabel lbwnum;
	JLabel lblNewLabel_5;
	
	
	//프로필화면 변수
	JPanel panelpr = new JPanel();
	static JTextField ptfname;
	static JTextField ptfid;
	static JPasswordField ptfpw;
	static JTextField ptfbirth;
	static JTextField ptfaddress;
	static JTextField ptfaddress1;
	static JTextField ptfaddress2;
	static JTextField ptfemail;
	static JTextField ptftel;
	static JRadioButton rdbtnman;
	static JRadioButton rdbtnwoman;
	static JTextField ptfphoto;
	static JLabel lbphoto = new JLabel("\uD504\uB85C\uD544\uC0AC\uC9C4");
	static String imagepath;

	JButton btnUpdate = new JButton("수정");
	JButton btnDelete = new JButton("취소");
	JButton btnphupdate = new JButton("변경");
	JButton btnphdelete = new JButton("삭제");



	//네트워크를 위한 자원 변수
	private Socket socket;
	private String ip = "127.0.0.1"; //127.0.0.1 네트워크에서 자기 자신을 가리키는 변수
	private int port = 3000;
	private String id = "";
	private InputStream is;
	private OutputStream os;
	private DataInputStream dis;
	private static DataOutputStream dos;

	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:hanle";
	private static final String USER = "hanle"; // DB ID
	private static final String PASS = "0253";


	//그 외 변수들
	Vector user_list = new Vector();		//전체접속유저리스트
	Vector chat_list = new Vector();		//채팅방접속유저리스트
	Vector wait_list = new Vector();		//대기리스트
	Vector room_list = new Vector();		//방 리스트
	StringTokenizer st;

	ProfileDB pd = new ProfileDB();
	MemberDB md = new MemberDB();
	Emoticon et;

	String phpath;
	String sendphoto;
	static String asdf;

	String sendmap;
	static String asdf1;
	String asdf2= "C:\\Users\\0\\Desktop\\자바채팅프로그램\\map.png";
	/*static InputStream chatis = null;
	static OutputStream chatos = null;
	static int numOfBytes;
	static byte[] imageData;*/

	Color selectedColor = Color.blue;
	Color nselectedColor = Color.red;
	Color chatbackColor = Color.white; //채팅 배경 채팅색 변수

	Font font;
	String fonts = "";
	String fontname = "맑은 고딕"; // 채팅 기본 글씨체
	int fontsize = 12; //기본 채팅방 사이즈
	boolean bold = false; //글씨체 볼드
	boolean italic = false; //글씨체 기울임

	public static String My_Room = null; //현재 나의 방
	String myroom = null;

	Client() //생성자 메소드
	{
		super("CHATTING");
		Login_init(); //Login창 화면 구성 메소드
		Main_init(); //Main창 화면 구성 메소드
		Chat_init();
		Emo_init();
		U_init();
		Main.share_btn.addActionListener(this);
	}


	private void Main_init() {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 334, 555);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {

				JOptionPane.showMessageDialog(null, "로그아웃 하셨습니다", "로그아웃", JOptionPane.INFORMATION_MESSAGE);

				try {
					textFieldID.setText("");
					textFieldPW.setText("");
					textFieldID.grabFocus();
					user_list.remove(id);
					wait_list.remove(id);

					user_list.removeAllElements();
					wait_list.removeAllElements();
					room_list.removeAllElements();

					os.close();
					is.close();
					dos.close();
					dis.close();
					socket.close();

					dispose();

				}
				catch(Exception e2) {

				}

			}


		});


		//라벨모음
		JPanel panellb = new JPanel();
		panellb.setBackground(new Color(250, 235, 215));
		panellb.setBounds(12, 31, 300, 48);
		contentPane.add(panellb);
		panellb.setLayout(null);

		lbwr.setHorizontalAlignment(SwingConstants.CENTER);
		lbwr.setBounds(75, 0, 79, 48);
		lbwr.setBackground(new Color(250, 235, 215));
		panellb.add(lbwr);
		lbwr.addMouseListener(this);
		//lbwr.setContentAreaFilled(false);		//내용영역채우지않기
		lbwr.setFocusPainted(false);		//선택되었을때 테두리 사용않암
		lbwr.setBorderPainted(false);			//외곽선없애기
		//lbwr.setOpaque(false);				//투명하게

		lbur.setHorizontalAlignment(SwingConstants.CENTER);
		lbur.setBounds(0, 0, 79, 48);
		lbur.setBackground(new Color(250, 235, 215));
		panellb.add(lbur);
		lbur.addMouseListener(this);
		lbur.setBorderPainted(false);			//외곽선없애기
		//lbur.setContentAreaFilled(false);		//내용영역채우지않기
		lbur.setFocusPainted(false);		//선택되었을때 테두리 사용않암
		//lbur.setOpaque(false);				//투명하게

		lbwcr.setHorizontalAlignment(SwingConstants.CENTER);
		lbwcr.setBounds(155, 0, 70, 48);
		lbwcr.setBackground(new Color(250, 235, 215));
		panellb.add(lbwcr);
		lbwcr.addMouseListener(this);
		lbwcr.setBorderPainted(false);			//외곽선없애기
		//lbwcr.setContentAreaFilled(false);		//내용영역채우지않기
		lbwcr.setFocusPainted(false);		//선택되었을때 테두리 사용않암
		//lbwcr.setOpaque(false);				//투명하게

		lbpr.setHorizontalAlignment(SwingConstants.CENTER);
		lbpr.setBounds(230, 0, 70, 48);
		lbpr.setBackground(new Color(250, 235, 215));
		panellb.add(lbpr);
		lbpr.addMouseListener(this);
		lbpr.setBorderPainted(false);			//외곽선없애기
		//lbpr.setContentAreaFilled(false);		//내용영역채우지않기
		lbpr.setFocusPainted(false);		//선택되었을때 테두리 사용않암
		//lbpr.setOpaque(false);				//투명하게


		//모든 화면 구성
		menuBar = new JMenuBar();
		menuBar.setBackground(new Color(248, 248, 255));
		menuBar.setBounds(0, 0, 324, 21);
		contentPane.add(menuBar);

		mnNewMenu1 = new JMenu("LOGOUT");
		mnNewMenu1.setFont(new Font("맑은 고딕", Font.PLAIN, 10));
		mnNewMenu1.setMnemonic(KeyEvent.VK_F); //단축키 설정
		mnNewMenu1.addActionListener(this);
		menuBar.add(mnNewMenu1);

		mntmlogout = new JMenuItem("로그아웃", KeyEvent.VK_M);
		mntmlogout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.ALT_MASK));
		mntmlogout.addActionListener(this); //리스너 설정해야 이벤트 처리 가능함
		mnNewMenu1.add(mntmlogout);

		//설정하는 방 wcr
		panelwcr.setBackground(new Color(255, 228, 225));
		panelwcr.setBorder(new TitledBorder(UIManager

				.getBorder("TitledBorder.border"), "설정",

				TitledBorder.LEFT, TitledBorder.TOP, null, null));
		panelwcr.setBounds(12, 89, 300, 422);
		contentPane.add(panelwcr);
		panelwcr.setLayout(null);



		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				FontChooserDialog fontChooser = new FontChooserDialog(Chat_GUI, Chat_GUI.getFont());
				if (fontChooser.isOkPressed()) {
					font = fontChooser.getSelectedFont();
					System.out.println(font+"이거임");
					fonts = buildFontString(font);
					fontsize = Fontsize(font);

					String[] fontall = fonts.split(",");
					System.out.println(fontall[0]+fontall[1]+fontall[2]);
					fontname = fontall[0];
					String fontbold = fontall[1];

					if(fontbold.equals("plain")) {
						bold = false;
						italic = false;
					}

					if(fontbold.equals("bold")) {
						bold = true;
						italic = false;
					}

					if(fontbold.equals("italic")) {
						italic = true;
						bold = false;

					}

					if(fontbold.equals("bold italic")) {
						bold = true;
						italic = true;
					}

				}
			}

			private String buildFontString(Font font) {
				String[] styleTypes = { "plain", "bold", "italic",
				"bold italic" };
				StringBuilder builder = new StringBuilder();
				builder.append("\"");
				builder.append(font.getFamily());
				builder.append("\"");
				builder.append(",");
				builder.append(styleTypes[font.getStyle()]);
				builder.append(",");
				builder.append(font.getSize());
				return builder.toString();
			}

			private int Fontsize(Font font) {

				return font.getSize();
			}

			private String fontbold(Font font) {
				String[] styleTypes = { "plain", "bold", "italic",
				"bold italic" };
				return styleTypes[font.getStyle()];
			}
		});

		button.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		button.setHorizontalAlignment(SwingConstants.LEFT);
		//button.setOpaque(false);
		//button.setFocusPainted(false);
		button.setBorderPainted(false);
		//button.setContentAreaFilled(false);
		button.setBackground(new Color(255, 228, 225));
		button.setBounds(0, 25, 300, 40);
		button.addActionListener(this);
		panelwcr.add(button);


		button_1.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		button_1.setHorizontalAlignment(SwingConstants.LEFT);
		//button_1.setOpaque(false);
		//button_1.setFocusPainted(false);
		button_1.setBorderPainted(false);
		//button_1.setContentAreaFilled(false);
		button_1.setBackground(new Color(255, 228, 225));
		button_1.setBounds(0, 63, 300, 40);
		button_1.addActionListener(this);
		panelwcr.add(button_1);


		button_3.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		button_3.setHorizontalAlignment(SwingConstants.LEFT);
		//button_2.setOpaque(false);
		//button_2.setFocusPainted(false);
		button_3.setBorderPainted(false);
		//button_2.setContentAreaFilled(false);
		button_3.setBackground(new Color(255, 228, 225));
		button_3.setBounds(0, 101, 300, 40);
		button_3.addActionListener(this);
		panelwcr.add(button_3);


		button_4.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		button_4.setHorizontalAlignment(SwingConstants.LEFT);
		button_4.setBorderPainted(false);
		button_4.setBackground(new Color(255, 228, 225));
		button_4.setBounds(0, 139, 300, 29);
		button_4.addActionListener(this);
		panelwcr.add(button_4);


		button_5.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		button_5.setHorizontalAlignment(SwingConstants.LEFT);
		button_5.setBorderPainted(false);
		button_5.setBackground(new Color(255, 228, 225));
		button_5.setBounds(0, 166, 300, 29);
		button_5.addActionListener(this);
		panelwcr.add(button_5);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 192, 203));
		panel.setBounds(0, 321, 300, 91);
		panelwcr.add(panel);
		panel.setLayout(null);
		
		lblNewLabel23 = new JLabel("");
		lblNewLabel23.setBounds(12, 10, 70, 71);
		panel.add(lblNewLabel23);
		
		JLabel lbwtitle = new JLabel("현재 날씨");
		lbwtitle.setHorizontalAlignment(SwingConstants.RIGHT);
		lbwtitle.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		lbwtitle.setBounds(125, 10, 151, 30);
		panel.add(lbwtitle);
		
	    lbwnum = new JLabel("4");
		lbwnum.setFont(new Font("맑은 고딕", Font.BOLD, 47));
		lbwnum.setBounds(94, 25, 41, 56);
		panel.add(lbwnum);
		
		JLabel lblNewLabel_3 = new JLabel("o");
		lblNewLabel_3.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		lblNewLabel_3.setBounds(125, 25, 57, 15);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("c");
		lblNewLabel_4.setFont(new Font("맑은 고딕", Font.PLAIN, 45));
		lblNewLabel_4.setBounds(135, 25, 31, 43);
		panel.add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("현재상태");
		lblNewLabel_5.setBounds(178, 53, 110, 15);
		panel.add(lblNewLabel_5);


		panelwcr.setVisible(false);



		//접속자 화면 모음 ur
		panelur.setBackground(new Color(255, 250, 240));
		panelur.setBorder(new TitledBorder(UIManager

				.getBorder("TitledBorder.border"), "접속자",

				TitledBorder.LEFT, TitledBorder.TOP, null, null));
		panelur.setBounds(12, 89, 300, 422);
		contentPane.add(panelur);
		panelur.setLayout(null);

		User_list.setFont(new Font("맑은 고딕", Font.BOLD, 17));

		User_list.setBounds(17, 22, 263, 345);
		User_list.setListData(user_list);
		User_list.setBackground(new Color(255, 250, 240));
		panelur.add(User_list);

		btnnote.setBackground(new Color(255, 228, 225));
		btnnote.setBounds(12, 372, 276, 40);
		panelur.add(btnnote);
		btnnote.addActionListener(this);


		panelur.setVisible(false);


		//채팅방 화면 모음 wr
		panelwr.setBackground(new Color(255, 255, 240));
		panelwr.setBorder(new TitledBorder(UIManager

				.getBorder("TitledBorder.border"), "채팅방",

				TitledBorder.LEFT, TitledBorder.TOP, null, null));
		panelwr.setBounds(12, 89, 300, 422);
		contentPane.add(panelwr);
		panelwr.setLayout(null);

		Room_list.setFont(new Font("맑은 고딕", Font.BOLD, 17));

		Room_list.setBounds(17, 22, 263, 345);
		panelwr.add(Room_list);
		Room_list.setBackground(new Color(255, 250, 240));
		Room_list.setListData(room_list);

		btnCreate.setBackground(new Color(255, 228, 225));
		btnCreate.setBounds(12, 372, 140, 40);
		panelwr.add(btnCreate);
		btnCreate.addActionListener(this);

		btnEnter.setBackground(new Color(255, 228, 225));
		btnEnter.setBounds(148, 372, 140, 40);
		panelwr.add(btnEnter);
		btnEnter.addActionListener(this);

		//프로필 화면 모음 pr
		panelpr.setBackground(new Color(255, 250, 240));
		panelpr.setBorder(new TitledBorder(UIManager

				.getBorder("TitledBorder.border"), "프로필",

				TitledBorder.LEFT, TitledBorder.TOP, null, null));
		panelpr.setBounds(12, 89, 300, 422);
		contentPane.add(panelpr);
		panelpr.setLayout(null);

		//프로필사진들어가는 라벨
		lbphoto.setBackground(new Color(255, 240, 245));
		lbphoto.setHorizontalAlignment(SwingConstants.CENTER);
		lbphoto.setBounds(12, 26, 134, 115);
		panelpr.add(lbphoto);

		ptfphoto = new JTextField();
		ptfphoto.setBounds(0, 0, 3, 3);
		panelpr.add(ptfphoto);
		ptfphoto.setColumns(10);
		ptfphoto.setVisible(false);

		JLabel lbname = new JLabel("이름");
		lbname.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		lbname.setBounds(168, 26, 57, 15);
		panelpr.add(lbname);

		ptfname = new JTextField();
		ptfname.setBounds(213, 20, 85, 29);
		panelpr.add(ptfname);
		ptfname.setColumns(10);

		JLabel lbid = new JLabel("아이디");
		lbid.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		lbid.setBounds(158, 64, 57, 15);
		panelpr.add(lbid);

		ptfid = new JTextField();
		ptfid.setBounds(213, 58, 85, 29);
		panelpr.add(ptfid);
		ptfid.setColumns(10);

		JLabel lbqw = new JLabel("비밀번호");
		lbqw.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		lbqw.setBounds(155, 100, 57, 15);
		panelpr.add(lbqw);

		ptfpw = new JPasswordField();
		ptfpw.setBounds(213, 94, 85, 29);
		panelpr.add(ptfpw);

		JLabel lblNewLabel = new JLabel("생일");
		lblNewLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		lblNewLabel.setBounds(168, 133, 57, 15);
		panelpr.add(lblNewLabel);

		ptfbirth = new JTextField();
		ptfbirth.setColumns(10);
		ptfbirth.setBounds(213, 127, 85, 29);
		panelpr.add(ptfbirth);

		JLabel label = new JLabel("주소");
		label.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		label.setBounds(50, 205, 57, 15);
		panelpr.add(label);

		ptfaddress = new JTextField();
		ptfaddress.setColumns(10);
		ptfaddress.setBounds(90, 195, 85, 29);
		panelpr.add(ptfaddress);

		ptfaddress1 = new JTextField();
		ptfaddress1.setColumns(10);
		ptfaddress1.setBounds(90, 230, 166, 29);
		panelpr.add(ptfaddress1);

		rdbtnman = new JRadioButton("남");
		rdbtnman.setBackground(new Color(255, 250, 240));
		rdbtnman.setBounds(177, 162, 48, 23);
		panelpr.add(rdbtnman);

		rdbtnwoman = new JRadioButton("여");
		rdbtnwoman.setBackground(new Color(255, 250, 240));
		rdbtnwoman.setBounds(232, 162, 48, 23);
		panelpr.add(rdbtnwoman);

		ptfaddress2 = new JTextField();
		ptfaddress2.setColumns(10);
		ptfaddress2.setBounds(90, 265, 166, 29);
		panelpr.add(ptfaddress2);

		JButton btnPost = new JButton("찾기");
		btnPost.setBackground(new Color(255, 228, 225));
		btnPost.setBounds(187, 195, 69, 29);
		panelpr.add(btnPost);

		JLabel lblNewLabel_11 = new JLabel("이메일");
		lblNewLabel_11.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		lblNewLabel_11.setBounds(38, 317, 57, 15);
		panelpr.add(lblNewLabel_11);

		ptfemail = new JTextField();
		ptfemail.setColumns(10);
		ptfemail.setBounds(90, 311, 166, 29);
		panelpr.add(ptfemail);

		JLabel label_1 = new JLabel("휴대전화");
		label_1.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		label_1.setBounds(21, 356, 57, 15);
		panelpr.add(label_1);

		ptftel = new JTextField();
		ptftel.setColumns(10);
		ptftel.setBounds(90, 350, 166, 29);
		panelpr.add(ptftel);

		btnphdelete.setBackground(new Color(255, 228, 225));
		btnphdelete.setBounds(12, 151, 66, 23);
		panelpr.add(btnphdelete);
		btnphdelete.addActionListener(this);

		btnphupdate.setBackground(new Color(255, 228, 225));
		btnphupdate.setBounds(77, 151, 69, 23);
		panelpr.add(btnphupdate);
		btnphupdate.addActionListener(this);

		btnUpdate.setBounds(100, 389, 75, 30);
		panelpr.add(btnUpdate);
		btnUpdate.setBackground(new Color(255, 228, 225));
		btnUpdate.addActionListener(this);

		btnDelete.setBounds(181, 389, 75, 30);
		panelpr.add(btnDelete);
		btnDelete.setBackground(new Color(255, 228, 225));;
		btnDelete.addActionListener(this);

		panelpr.setVisible(false);

		this.setLocationRelativeTo(null);
		this.setVisible(false);
	}


	private void Chat_init() {
		Chat_GUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Chat_GUI.setBounds(100, 100, 334, 555);
		Chat_Panel = new JPanel();
		Chat_Panel.setBackground(new Color(230, 230, 250));
		Chat_Panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		Chat_GUI.setContentPane(Chat_Panel);
		Chat_Panel.setLayout(null);
		Chat_GUI.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {


				send_message("ExitRoom/"+My_Room );

				wait_list.add(id);

				chat_list.remove(id);
				chat_list.removeAllElements();


				U_GUI.setVisible(false);
				Emoticon.b.setVisible(false);
				My_Room = null;

				JOptionPane.showMessageDialog(Chat_GUI, "퇴장하셨습니다", "퇴장", JOptionPane.INFORMATION_MESSAGE);

				Chat_GUI.setVisible(false);
				btnCreate.setEnabled(true);
				btnEnter.setEnabled(true);
			}
		});

		panel.setBounds(7, 8, 303, 45);
		panel.setBackground(new Color(173, 216, 230));
		Chat_Panel.add(panel);
		panel.setLayout(null);

		lbroomname = new JLabel("방이름");
		lbroomname.setBackground(new Color(255, 250, 205));
		lbroomname.setForeground(new Color(255, 248, 220));
		lbroomname.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 16));
		lbroomname.setHorizontalAlignment(SwingConstants.CENTER);
		lbroomname.setBounds(64, 0, 182, 45);
		lbroomname.addMouseListener(this);
		panel.add(lbroomname);

		lbback.setBounds(10, 5, 35, 35);
		panel.add(lbback);
		lbback.addMouseListener(this);

		lbselect.setBounds(258, 5, 35, 35);
		lbselect.addMouseListener(this);
		panel.add(lbselect);

		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(7, 57, 303, 458);
		Chat_Panel.add(panel_1);
		panel_1.setLayout(null);


		textField.setBounds(46, 407, 206, 45);
		panel_1.add(textField);
		textField.setColumns(10);
		textField.addKeyListener(this);

		btnsend.setBackground(new Color(255, 255, 255));
		btnsend.setBounds(250, 407, 51, 45);
		panel_1.add(btnsend);
		btnsend.setBorderPainted(false);			//외곽선없애기
		btnsend.setFocusPainted(false);		//선택되었을때 테두리 사용않암
		btnsend.addActionListener(this);

		btnemo.setBackground(new Color(255, 255, 255));
		btnemo.setBounds(3, 407, 42, 45);
		panel_1.add(btnemo);
		btnemo.setBorderPainted(false);			//외곽선없애기
		//btnemo.setContentAreaFilled(false);		//내용영역채우지않기
		btnemo.setFocusPainted(false);		//선택되었을때 테두리 사용않암
		//btnemo.setOpaque(false);				//투명하게
		btnemo.addActionListener(this);

		panel_2.setBackground(new Color(255, 182, 193));
		panel_2.setBounds(0, 0, 303, 401);
		panel_1.add(panel_2);
		panel_2.setLayout(null);

		Chat_area.setBackground(Color.WHITE);
		Chat_area.setEditable(false);
		Chat_area.addMouseListener(this);

		scrollPane = new JScrollPane(Chat_area, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(0, 0, 315, 401);
		panel_2.add(scrollPane);
		scrollPane.setViewportView(Chat_area);


		Chat_GUI.setLocationRelativeTo(null);
		Chat_GUI.setVisible(false);

	}

	private void Emo_init() {
		Emo_GUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Emo_GUI.setBounds(100, 100, 287, 150);
		Emo_Panel = new JPanel();
		Emo_Panel.setBackground(new Color(173, 216, 230));
		Emo_Panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		Emo_GUI.setContentPane(Emo_Panel);
		Emo_Panel.setLayout(null);

		lbemo.setBounds(12, 24, 65, 65);
		Emo_Panel.add(lbemo);
		lbemo.addMouseListener(this);

		lbphto.setBounds(106, 24, 65, 65);
		Emo_Panel.add(lbphto);
		lbphto.addMouseListener(this);

		lbdocu.setBounds(200, 24, 65, 65);
		Emo_Panel.add(lbdocu);
		lbdocu.addMouseListener(this);

		Emo_GUI.setLocationRelativeTo(null);
		Emo_GUI.setVisible(false);
	}

	private void U_init() {
		U_GUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		U_GUI.setBounds(100, 100, 221, 363);
		U_Panel = new JPanel();
		U_Panel.setBackground(new Color(228, 245, 252));
		U_Panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		U_GUI.setContentPane(U_Panel);
		U_Panel.setLayout(null);

		JLabel lbsettingname = new JLabel("설정");
		lbsettingname.setHorizontalAlignment(SwingConstants.CENTER);
		lbsettingname.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		lbsettingname.setBounds(12, 4, 181, 34);
		U_Panel.add(lbsettingname);

		panelchatuser = new JPanel();
		panelchatuser.setBackground(new Color(255, 250, 240));
		panelchatuser.setBounds(12, 72, 181, 205);
		U_Panel.add(panelchatuser);
		panelchatuser.setLayout(null);
		panelchatuser.setVisible(false);

		Chat_list.setBounds(12, 10, 157, 160);
		Chat_list.setListData(chat_list);
		Chat_list.setBackground(new Color(255, 250, 240));
		panelchatuser.add(Chat_list);

		btnchatnote = new JButton("쪽지");
		btnchatnote.setBackground(new Color(255, 250, 240));
		btnchatnote.setBounds(12, 173, 157, 32);
		panelchatuser.add(btnchatnote);
		btnchatnote.addActionListener(this);

		btnchatuser = new JButton("채팅 참여자");
		btnchatuser.setHorizontalAlignment(SwingConstants.LEFT);
		btnchatuser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelchatuser.setVisible(true);
				btnchatinvite.setVisible(false);
			}
		});
		btnchatuser.setFocusPainted(false);		//선택되었을때 테두리 사용않암
		btnchatuser.setBorderPainted(false);			//외곽선없애기
		btnchatuser.setBackground(new Color(228, 245, 252));
		btnchatuser.setBounds(12, 45, 181, 28);
		U_GUI.getContentPane().add(btnchatuser);

		panelwait = new JPanel();
		panelwait.setBackground(new Color(255, 250, 240));
		panelwait.setBounds(12, 98, 181, 212);
		U_GUI.getContentPane().add(panelwait);
		panelwait.setLayout(null);
		panelwait.setVisible(false);

		Wait_list.setBounds(12, 10, 157, 166);
		Wait_list.setBackground(new Color(255, 250, 240));
		panelwait.add(Wait_list);
		Wait_list.setListData(wait_list);

		//채팅유저화면에서 초대버튼
		btninvite.setBackground(new Color(255, 250, 240));
		btninvite.setBounds(12, 180, 157, 32);
		btninvite.addActionListener(this);
		panelwait.add(btninvite);

		btnchatinvite = new JButton("대화상대 초대");
		btnchatinvite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelwait.setVisible(true);
			}
		});
		btnchatinvite.setFocusPainted(false);		//선택되었을때 테두리 사용않암
		btnchatinvite.setBorderPainted(false);			//외곽선없애기
		btnchatinvite.setBackground(new Color(228, 245, 252));
		btnchatinvite.setHorizontalAlignment(SwingConstants.LEFT);
		btnchatinvite.setBounds(12, 72, 181, 28);
		U_GUI.getContentPane().add(btnchatinvite);

		ghfhf1.setBounds(12, 10, 30, 30);
		ghfhf1.setFocusPainted(false);		//선택되었을때 테두리 사용않암
		ghfhf1.setBorderPainted(false);			//외곽선없애기
		ghfhf1.setBackground(new Color(228, 245, 252));
		U_Panel.add(ghfhf1);
		ghfhf1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				U_GUI.dispose();
			}
		});

		ghfhf2.setBounds(164, 10, 30, 30);
		ghfhf2.setFocusPainted(false);		//선택되었을때 테두리 사용않암
		ghfhf2.setBorderPainted(false);			//외곽선없애기
		ghfhf2.setBackground(new Color(228, 245, 252));
		U_Panel.add(ghfhf2);
		ghfhf2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelwait.setVisible(false);
				panelchatuser.setVisible(false);
				btnchatinvite.setVisible(true);
				btnchatuser.setVisible(true);

			}
		});


		jl.setBounds(0, 0, 205, 325);
		U_Panel.add(jl);

		U_GUI.setLocationRelativeTo(null);
		U_GUI.setVisible(false);
	}



	private void Login_init() { //Login창 화면 구성 메소드 생성	

		Login_GUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Login_GUI.setBounds(100, 100, 334, 555);
		Login_Panel = new JPanel();
		Login_Panel.setBackground(new Color(230, 230, 250));
		Login_Panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		Login_GUI.setContentPane(Login_Panel);
		Login_Panel.setLayout(null);


		JLabel labelID = new JLabel("ID");
		labelID.setFont(new Font("맑은 고딕", Font.ITALIC, 13));
		labelID.setBounds(62, 214, 46, 15);
		Login_Panel.add(labelID);

		JLabel labelPW = new JLabel("PW");
		labelPW.setFont(new Font("맑은 고딕", Font.ITALIC, 13));
		labelPW.setBounds(64, 285, 46, 15);
		Login_Panel.add(labelPW);

		textFieldID = new JTextField();
		textFieldID.setBounds(60, 236, 183, 39);
		Login_Panel.add(textFieldID);
		textFieldID.setColumns(10);

		textFieldPW = new JPasswordField();
		textFieldPW.setBounds(60, 310, 183, 39);
		Login_Panel.add(textFieldPW);
		textFieldPW.setColumns(10);
		textFieldPW.addMouseListener(this);

		btnLogin.setBackground(new Color(230, 230, 250));
		btnLogin.setBounds(153, 363, 92, 39);
		Login_Panel.add(btnLogin);
		btnLogin.addActionListener(this);

		btnJoin.setBackground(new Color(230, 230, 250));
		btnJoin.setBounds(62, 363, 92, 39);
		Login_Panel.add(btnJoin);
		btnJoin.addActionListener(this);

		menuBar = new JMenuBar();
		menuBar.setBackground(new Color(248, 248, 255));
		menuBar.setBounds(0, 0, 318, 21);
		Login_Panel.add(menuBar);

		mnNewMenu = new JMenu("FILE(F)");
		mnNewMenu.setMnemonic(KeyEvent.VK_F); //단축키 설정
		menuBar.add(mnNewMenu);

		mntmNewMenuItem = new JMenuItem("Manager", KeyEvent.VK_M);
		mntmNewMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.ALT_MASK));
		mntmNewMenuItem.addActionListener(this); //리스너 설정해야 이벤트 처리 가능함
		mnNewMenu.add(mntmNewMenuItem);

		mntmNewMenuItem_1 = new JMenuItem("Exit", KeyEvent.VK_E);
		mntmNewMenuItem_1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.ALT_MASK));
		mntmNewMenuItem_1.addActionListener(this); //리스너 설정해야 이벤트 처리 가능함
		mnNewMenu.add(mntmNewMenuItem_1);

		lbf = new JLabel(li.Labelimage("C:\\Users\\0\\Desktop\\자바채팅프로그램\\purpleF.png", 70, 70));
		lbf.setBounds(212, 362, 70, 70);
		Login_Panel.add(lbf);

		lbf1 = new JLabel(li.Labelimage("C:\\Users\\0\\Desktop\\자바채팅프로그램\\purpleF.png", 70, 70));
		lbf1.setBounds(24, 239, 70, 70);
		Login_Panel.add(lbf1);

		JLabel lblNewLabel = new JLabel(li.Labelimage("C:\\Users\\0\\Desktop\\자바채팅프로그램\\alice2.png", 230, 290));
		lblNewLabel.setBounds(74, 46, 236, 307);
		Login_Panel.add(lblNewLabel);

		lbfindid = new JLabel("아이디");
		lbfindid.setFont(new Font("맑은 고딕", Font.PLAIN, 10));
		lbfindid.setBounds(64, 417, 32, 15);
		lbfindid.addMouseListener(this);
		Login_Panel.add(lbfindid);

		lbfindpw = new JLabel("비밀번호 찾기");
		lbfindpw.setFont(new Font("맑은 고딕", Font.PLAIN, 10));
		lbfindpw.setBounds(97, 417, 103, 15);
		lbfindpw.addMouseListener(this);
		Login_Panel.add(lbfindpw);


		btnmem.setBackground(new Color(230, 230, 250));
		btnmem.setBounds(60, 438, 183, 30);
		btnmem.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		btnmem.setVerticalAlignment(SwingConstants.TOP);
		btnmem.setHorizontalAlignment(SwingConstants.LEFT);
		btnmem.setBorderPainted(false);
		btnmem.addActionListener(this);
		Login_Panel.add(btnmem);

		Login_GUI.setLocationRelativeTo(null);
		Login_GUI.setVisible(true); //true = 화면에 보인다 false = 화면에 보이지 않는다

	}


	private void Network()
	{
		try {
			socket = new Socket(ip,port);

			if(socket != null) //정상적으로 소켓이 연결되었을경우
			{
				Connection();
			}

		} catch (UnknownHostException e) { //소켓이 정상적을 연결되지 않았을 때

			JOptionPane.showMessageDialog(null, "소켓 연결 실패", "알림", JOptionPane.ERROR_MESSAGE);

		} catch (IOException e) {

			JOptionPane.showMessageDialog(null, "소켓 연결 실패", "알림", JOptionPane.ERROR_MESSAGE);
		}

	}


	private void Connection() //실제적인 메소드 연결부분
	{
		try {
			is = socket.getInputStream();
			dis = new DataInputStream(is);

			os = socket.getOutputStream();
			dos = new DataOutputStream(os);
		}
		catch(IOException e) //에러처리부분
		{
			JOptionPane.showMessageDialog(null, "연결 실패", "알림", JOptionPane.ERROR_MESSAGE);
		} //Stream 설정 끝

		this.setVisible(true); // UI 표시
		this.Login_GUI.setVisible(false); //Login UI 닫기

		//처음 접속시에  ID 전송
		send_message(id);

		// User_list에 사용자 추가
		user_list.add(id);
		wait_list.add(id);
		Login(id);

		Thread th = new Thread(new Runnable() {

			@Override
			public void run() {

				while(true){

					try {
						String msg = dis.readUTF(); //메세지 수신

						System.out.println("서버로부터 수신된 메시지 : " + msg);

						inmessage(msg);

					} catch (IOException e) {

						try {
							os.close();
							is.close();
							dos.close();
							dis.close();
							Logout(id);
							socket.close();
							System.out.println("서버와 접속 끊어짐");
							//JOptionPane.showMessageDialog(null, "서버와 접속 끊어짐", "알림", JOptionPane.ERROR_MESSAGE);
						}
						catch(IOException e1) {}
						break;

					}


				} //while문 끝

			}
		});

		th.start();

	}


	public String checkIDImage(String id) {//아이디에 해당하는 프로필사진을 출력
		id = id;
		String str=null;

		Connection con = null;  //view 도 역시 list와 같이 데이터를 불러와야하기 때문에 ResultSet을 준비 한다.
		Statement stmt = null;
		ResultSet rs = null;
		Properties info = null;
		String url = "jdbc:oracle:thin:@localhost:1521:hanle"; 
		String sql = null;


		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); // 알아서 들어간다..conn으로
			info = new Properties();
			info.setProperty("user", "hanle");
			info.setProperty("password", "0253");
			con = DriverManager.getConnection(url, info);
			stmt = con.createStatement(); //db접속

			sql = "select * from chatuser1 where id='" + id + "'";
			rs = stmt.executeQuery(sql); // 입력id와 테이블에 저장된 id와 비교

			while (rs.next() == true) {       
				str =rs.getString(11);       
			}      
		} catch (Exception ee) { //익셉션처리
			System.out.println("문제있음");
			ee.printStackTrace();
		}
		try {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (con != null)
				con.close();
		}
		catch (SQLException e1) {
			e1.printStackTrace();
		}	
		return str; 
	}


	private void inmessage(String str) //서버로부터 들어오는 모든 메세지
	{
		GregorianCalendar calendar = new GregorianCalendar();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int date = calendar.get(Calendar.DATE);
		int amPm = calendar.get(Calendar.AM_PM);
		int hour = calendar.get(Calendar.HOUR);
		int min = calendar.get(Calendar.MINUTE);
		int sec = calendar.get(Calendar.SECOND);
		String sAmPm = amPm == Calendar.AM ? "오전" : "오후";

		st = new StringTokenizer(str,"/");

		String protocol = st.nextToken();
		String Message = st.nextToken();

		System.out.println("프로토콜 : " + protocol);
		System.out.println("내용 : " + Message);

		if(protocol.equals("NewUser")) {  //새로운 접속자 		//새로 로그인햇을 때
			user_list.add(Message);		
			wait_list.add(Message);

		}
		else if(protocol.equals("OldUser")) {					//새로운 애한테 기존애 알려주는거
			user_list.add(Message);
			wait_list.add(Message);
		}

		else if(protocol.equals("NewChatUser")) {		//새로 채팅들어왓을떄
			chat_list.add(Message);
		}

		else if(protocol.equals("OldChatUser")) {
			chat_list.add(Message);
		}

		else if(protocol.equals("Note")) {

			String note = st.nextToken();

			System.out.println(Message + " 사용자로부터 온 쪽지 " + note);

			JOptionPane.showMessageDialog(Chat_GUI, note, Message + "님으로부터 쪽지", JOptionPane.CLOSED_OPTION);

		}

		else if(protocol.equals("Invite")) {

			String inroom = st.nextToken();

			int inok = JOptionPane.showConfirmDialog(this, "\'"+inroom+"\'방에 초대를 받으시겠습니까?", "초대", JOptionPane.OK_CANCEL_OPTION);

			if (inok == JOptionPane.OK_OPTION){


				System.out.println("dd채팅 방이름 뭘가 ?" + inroom);

				send_message("Inviteroom/" + inroom );

				chat_list.add(id);
				wait_list.remove(id);

				if(Message.equals("inroom")) {
					System.out.println("dd1234채팅 방이름 뭘가 ?" + inroom);
				}
			}

		}

		else if(protocol.equals("Inviteroom")) {			
			My_Room = Message;
			lbroomname.setText("\'  "+My_Room+"  \'");
			this.Chat_GUI.setVisible(true);
			JOptionPane.showMessageDialog(Chat_GUI, "채팅방에 입장했습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
		}

		else if(protocol.equals("Sendphoto")) {
			My_Room = Message;
			sendphoto  = st.nextToken();
		}

		else if(protocol.equals("photopath")) {
			asdf = Message;
			
		}

		else if(protocol.equals("mappath")) {
			asdf1 = Message;
		}

		else if(protocol.equals("Sendmap")) {
			My_Room = Message;
			sendmap  = st.nextToken();
		}


		else if(protocol.equals("user_list_update")) {

			User_list.setListData(user_list);
		}

		else if(protocol.equals("chat_list_update")) {
			Chat_list.setListData(chat_list);
		}

		else if(protocol.equals("wait_list_update")) {

			Wait_list.setListData(wait_list);
		}

		else if(protocol.equals("CreateRoom")) {   //새로운 방을 만들었을 때	
			My_Room = Message;
			lbroomname.setText("\'  "+My_Room+"  \'");
		}

		else if(protocol.equals("CreateRoomFail")) { //방 만들기 실패했을 경우
			JOptionPane.showMessageDialog(this, "방 만들기 실패", "알림", JOptionPane.OK_OPTION);
		}

		else if(protocol.equals("New_Room")) {  //새로운 방을 만들었을 때 상대에게 룸이름 쏘기
			room_list.add(Message);
			Room_list.setListData(room_list);
		}

		else if(protocol.equals("WC_Room")) {  //새로운 방을 만들었을 때 상대에게 대기실유저 이름 쏘기
			wait_list.remove(Message);
			Wait_list.setListData(wait_list);
		}

		else if(protocol.equals("WCO_Room")) {  //새로운 방을 만들었을 때 상대에게 대기실유저 이름 쏘기
			wait_list.add(Message);
			Wait_list.setListData(wait_list);
		}

		else if(protocol.equals("OldRoom")) {
			room_list.add(Message);
		}

		else if(protocol.equals("Room_list_update")) {
			Room_list.setListData(room_list);
		}

		else if(protocol.equals("JoinRoom"))	{
			My_Room = Message;
			lbroomname.setText("\'  "+My_Room+"  \'");
			JOptionPane.showMessageDialog(Chat_GUI, "채팅방에 입장했습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);

		}
		else if(protocol.equals("ExitRoom")){

			My_Room = null;
			Chat_area.setText("");


		}
		else if(protocol.equals("User_out"))	{
			user_list.remove(Message);
			wait_list.remove(Message);
		}

		else if(protocol.equals("ChatUser_out"))	{
			chat_list.remove(Message);

		}

		else if(protocol.equals("Chatting"))	{

			//Chat_area.append(Message+ ":" + msg + "\n");
			String msg = st.nextToken(); //msg = 텍스트 내용
			StyledDocument doc = Chat_area.getStyledDocument();
			SimpleAttributeSet smi=new SimpleAttributeSet();
			Style def = StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE);


			Style s =doc.addStyle("red", def);
			StyleConstants.setForeground(s, Color.red); //다른 사용자 색 
			s =doc.addStyle("blue", def);
			StyleConstants.setForeground(s, Color.blue); //자신의 채팅 색 
			s =doc.addStyle("black", def);
			StyleConstants.setForeground(s, Color.black); //알림 컬러 

			s = doc.addStyle("right", def);
			StyleConstants.setAlignment(s, StyleConstants.ALIGN_RIGHT);
			s = doc.addStyle("left", def);
			StyleConstants.setAlignment(s, StyleConstants.ALIGN_LEFT);
			s = doc.addStyle("center", def);
			StyleConstants.setAlignment(s, StyleConstants.ALIGN_CENTER);

			s =doc.addStyle("selected", def);
			StyleConstants.setForeground(s, selectedColor); //내 채팅 색
			StyleConstants.setBold(s, bold);     //폰트 스타일
			StyleConstants.setItalic(s, italic);
			StyleConstants.setFontFamily(s, fontname);
			StyleConstants.setFontSize(s, fontsize);

			s =doc.addStyle("nselected", def);
			StyleConstants.setForeground(s, nselectedColor); //상대 채팅 색
			StyleConstants.setBold(s, bold);     //폰트 스타일
			StyleConstants.setItalic(s, italic);
			StyleConstants.setFontFamily(s, fontname);
			StyleConstants.setFontSize(s, fontsize);


			try {
				String Str=null;

				if(Message.equals(id)) {
					doc.setParagraphAttributes(0, doc.getLength(), smi , false);
					doc.setLogicalStyle(doc.getLength(), doc.getStyle("right"));
					Style profile = doc.addStyle("프로필사진1", null);
					StyleConstants.setIcon(profile, li.Labelimage((checkIDImage(id)), 40, 40));
					doc.insertString(doc.getLength(),Message+" : ", doc.getStyle("selected"));	//아이디
					doc.insertString(doc.getLength(),"ignored text", profile);				//프로필사진
					StyleConstants.setForeground(smi, Color.blue);

					if(msg.equals(et.emo1)||msg.equals("1")){

						ImageIcon aa =  new ImageIcon();
						aa = li.Labelimage(et.emo1, 120, 120);
						StyleConstants.setIcon(smi, aa);      					// smi변수에 이모티콘을 저장한다.
						doc.insertString(doc.getLength(),"\n", null);
						doc.insertString(doc.getLength(),Message,smi);        	// 이미지가 출력된다.
					} 

					else if(msg.equals(et.emo2)||msg.equals("2")){     

						ImageIcon aa2 =  new ImageIcon();
						aa2 = li.Labelimage(et.emo2, 120, 120);
						StyleConstants.setIcon(smi, aa2); 
						doc.insertString(doc.getLength(),"\n", null);
						doc.insertString(doc.getLength(),Message,smi);
					}

					else if(msg.equals(et.emo3)||msg.equals("3")){    
						ImageIcon aa3 =  new ImageIcon();
						aa3 = li.Labelimage(et.emo3, 120, 120);
						StyleConstants.setIcon(smi, aa3); 
						doc.insertString(doc.getLength(),"\n", null);
						doc.insertString(doc.getLength(),Message,smi);
					}

					else if(msg.equals(et.emo4)||msg.equals("4")){     
						ImageIcon aa4 =  new ImageIcon();
						aa4 = li.Labelimage(et.emo4, 120, 120);
						StyleConstants.setIcon(smi, aa4); 
						doc.insertString(doc.getLength(),"\n", null);
						doc.insertString(doc.getLength(),Message,smi);
					} 

					else if(msg.equals(et.emo5)||msg.equals("5")){      
						ImageIcon aa5 =  new ImageIcon();
						aa5 = li.Labelimage(et.emo5, 120, 120);
						StyleConstants.setIcon(smi, aa5); 
						doc.insertString(doc.getLength(),"\n", null);
						doc.insertString(doc.getLength(),Message,smi);
					} 

					else if(msg.equals(et.emo6)||msg.equals("6")){    
						ImageIcon aa6 =  new ImageIcon();
						aa6 = li.Labelimage(et.emo6, 120, 120);
						StyleConstants.setIcon(smi, aa6); 
						doc.insertString(doc.getLength(),"\n", null);
						doc.insertString(doc.getLength(),Message,smi);
					} 

					else if(msg.equals(et.emo7)||msg.equals("7")){      
						ImageIcon aa7 =  new ImageIcon();
						aa7 = li.Labelimage(et.emo7, 120, 120);
						StyleConstants.setIcon(smi, aa7); 
						doc.insertString(doc.getLength(),"\n", null);
						doc.insertString(doc.getLength(),Message,smi);
					} 

					else if(msg.equals(et.emo8)||msg.equals("8")){      
						ImageIcon aa8 =  new ImageIcon();
						aa8 = li.Labelimage(et.emo8, 120, 120);
						StyleConstants.setIcon(smi, aa8); 
						doc.insertString(doc.getLength(),"\n", null);
						doc.insertString(doc.getLength(),Message,smi);
					} 

					else if(msg.equals(et.emo9)||msg.equals("9")){      
						ImageIcon aa9 =  new ImageIcon();
						aa9 = li.Labelimage(et.emo9, 120, 120);
						StyleConstants.setIcon(smi, aa9); 
						doc.insertString(doc.getLength(),"\n", null);
						doc.insertString(doc.getLength(),Message,smi);
					}

					else if(msg.equals("사진전송")){ 		//내가 사진보냈을떄

						ImageIcon aaphoto = new ImageIcon();
						aaphoto = li.Labelimage(asdf, 120, 120);
						StyleConstants.setIcon(smi, aaphoto);
						doc.insertString(doc.getLength(),"\n", null);
						doc.insertString(doc.getLength(),Message,smi);
						doc.insertString(doc.getLength(),"\n", null);

						//이것;
						String textLink = "저장";
						Style regularBlue = doc.addStyle("regularBlue", StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE));
						StyleConstants.setForeground(regularBlue, Color.BLUE);
						StyleConstants.setUnderline(regularBlue, true);
						regularBlue.addAttribute("linkact", new ChatLinkListener(textLink));
						doc.insertString(doc.getLength(), textLink, regularBlue);
					}

					else if(msg.equals("지도전송")){ 		//내가 사진보냈을떄

						ImageIcon aaphoto = new ImageIcon();
						aaphoto = li.Labelimage(asdf2, 120, 120);
						StyleConstants.setIcon(smi, aaphoto);
						doc.insertString(doc.getLength(),"\n", null);
						doc.insertString(doc.getLength(),Message,smi);
						doc.insertString(doc.getLength(),"\n", null);

						//이것;
						String textLink1 = "지도보기";
						Style regularBlue = doc.addStyle("regularBlue", StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE));
						StyleConstants.setForeground(regularBlue, Color.BLUE);
						StyleConstants.setUnderline(regularBlue, true);
						regularBlue.addAttribute("linkact1", new ChatLinkListener1(textLink1));
						doc.insertString(doc.getLength(), textLink1, regularBlue);
					}

					else{
						doc.insertString(doc.getLength(),"\n", null);
						doc.insertString(doc.getLength(),msg, doc.getStyle("selected"));
					}

					doc.insertString(doc.getLength(),"\n", null);
					doc.insertString(doc.getLength()," ("+sAmPm+""+hour+":"+min+") ", doc.getStyle("selected"));	//시간
					doc.insertString(doc.getLength(),"\n", null);
					Chat_area.setCaretPosition(doc.getLength());

				}


				else if(Message.equals("알림")) {

					doc.setLogicalStyle(doc.getLength(), doc.getStyle("center"));
					doc.insertString(doc.getLength(), Message+" : "+msg+"\n", doc.getStyle("black"));
					Chat_area.setCaretPosition(doc.getLength());

				}


				else {

					doc.setLogicalStyle(doc.getLength(), doc.getStyle("left")); //글자 왼쪽 정렬
					doc.setParagraphAttributes(200, doc.getLength(), smi , false);
					Style profile = doc.addStyle("프로필사진2", null);
					StyleConstants.setIcon(profile, li.Labelimage((checkIDImage(Message)), 40, 40));
					doc.insertString(doc.getLength(),"ignored text", profile);	//프로필 사진
					StyleConstants.setForeground(smi, Color.red);
					doc.insertString(doc.getLength(),Message+" : ", doc.getStyle("nselected")); //아이디


					if(msg.equals(et.emo1)||msg.equals("1")){          
						ImageIcon aa1 =  new ImageIcon();
						aa1 = li.Labelimage(et.emo1, 120, 120);
						StyleConstants.setIcon(smi, aa1);     	// smi변수에 이모티콘을 저장한다.
						doc.insertString(doc.getLength(),Message,smi);        	// 이미지가 출력된다.
						doc.insertString(doc.getLength(),"\n", null);// 그 후 엔터가 입력된다.
					} 

					else if(msg.equals(et.emo2)||msg.equals("2")){     
						ImageIcon aa2 =  new ImageIcon();
						aa2 = li.Labelimage(et.emo2, 120, 120);
						StyleConstants.setIcon(smi, aa2); 
						doc.insertString(doc.getLength(),Message,smi);
						doc.insertString(doc.getLength(),"\n", null);

					}
					else if(msg.equals(et.emo3)||msg.equals("3")){     
						ImageIcon aa3 =  new ImageIcon();
						aa3 = li.Labelimage(et.emo3, 120, 120);
						StyleConstants.setIcon(smi, aa3); 
						doc.insertString(doc.getLength(),Message,smi);
						doc.insertString(doc.getLength(),"\n", null);
					}

					else if(msg.equals(et.emo4)||msg.equals("4")){     
						ImageIcon aa4 =  new ImageIcon();
						aa4 = li.Labelimage(et.emo4, 120, 120);
						StyleConstants.setIcon(smi, aa4); 
						doc.insertString(doc.getLength(),Message,smi);
						doc.insertString(doc.getLength(),"\n", null);
					} 

					else if(msg.equals(et.emo5)||msg.equals("5")){      
						ImageIcon aa5 =  new ImageIcon();
						aa5 = li.Labelimage(et.emo5, 120, 120);
						StyleConstants.setIcon(smi, aa5); 
						doc.insertString(doc.getLength(),Message,smi);
						doc.insertString(doc.getLength(),"\n", null);
					} 

					else if(msg.equals(et.emo6)||msg.equals("6")){    
						ImageIcon aa6 =  new ImageIcon();
						aa6 = li.Labelimage(et.emo6, 120, 120);
						StyleConstants.setIcon(smi, aa6); 
						doc.insertString(doc.getLength(),Message,smi);
						doc.insertString(doc.getLength(),"\n", null);
					} 

					else if(msg.equals(et.emo7)||msg.equals("7")){      
						ImageIcon aa7 =  new ImageIcon();
						aa7 = li.Labelimage(et.emo7, 120, 120);
						StyleConstants.setIcon(smi, aa7); 
						doc.insertString(doc.getLength(),Message,smi);
						doc.insertString(doc.getLength(),"\n", null);

					} 

					else if(msg.equals(et.emo8)||msg.equals("8")){      
						ImageIcon aa8 =  new ImageIcon();
						aa8 = li.Labelimage(et.emo8, 120, 120);
						StyleConstants.setIcon(smi, aa8); 
						doc.insertString(doc.getLength(),Message,smi);
						doc.insertString(doc.getLength(),"\n", null);

					} 

					else if(msg.equals(et.emo9)||msg.equals("9")){      
						ImageIcon aa9 =  new ImageIcon();
						aa9 = li.Labelimage(et.emo9, 120, 120);
						StyleConstants.setIcon(smi, aa9); 
						doc.insertString(doc.getLength(),Message,smi);
						doc.insertString(doc.getLength(),"\n", null);

					}

					else if(msg.equals("사진전송")){ 			//남에게 보여지는 사진
						ImageIcon aaphoto = new ImageIcon();
						aaphoto = li.Labelimage(asdf, 120, 120);
						StyleConstants.setIcon(smi, aaphoto);
						doc.insertString(doc.getLength(),"\n", null);
						doc.insertString(doc.getLength(),Message,smi);
						doc.insertString(doc.getLength(),"\n", null);

						//이것;
						String textLink = "저장";
						Style regularBlue = doc.addStyle("regularBlue", StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE));
						StyleConstants.setForeground(regularBlue, Color.BLUE);
						StyleConstants.setUnderline(regularBlue, true);
						regularBlue.addAttribute("linkact", new ChatLinkListener(textLink));
						doc.insertString(doc.getLength(), textLink, regularBlue);

					}

					else if(msg.equals("지도전송")){ 		//내가 사진보냈을떄

						ImageIcon aaphoto = new ImageIcon();
						aaphoto = li.Labelimage(asdf2, 120, 120);
						StyleConstants.setIcon(smi, aaphoto);
						doc.insertString(doc.getLength(),"\n", null);
						doc.insertString(doc.getLength(),Message,smi);
						doc.insertString(doc.getLength(),"\n", null);

						//이것;
						String textLink1 = "지도보기";
						Style regularBlue = doc.addStyle("regularBlue", StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE));
						StyleConstants.setForeground(regularBlue, Color.BLUE);
						StyleConstants.setUnderline(regularBlue, true);
						regularBlue.addAttribute("linkact1", new ChatLinkListener1(textLink1));
						doc.insertString(doc.getLength(), textLink1, regularBlue);
					}

					else{
						doc.insertString(doc.getLength(),"\n", null);
						doc.insertString(doc.getLength(),msg, doc.getStyle("nselected"));

					}

					doc.insertString(doc.getLength(),"\n", null);
					doc.insertString(doc.getLength()," ("+sAmPm+""+hour+":"+min+") ", doc.getStyle("nselected"));
					doc.insertString(doc.getLength(),"\n", null);
					Chat_area.setCaretPosition(doc.getLength());
				}


			}


			catch (Exception e) {
				System.out.println("문제발생");
			}

		}


	}

	
	public void weather_view(){
	      
	      WeatherByGPSApplication weather = new WeatherByGPSApplication();
	      
	      System.out.println("날씨 받아왔당");
	      
	      
	      if(WeatherByGPSApplication.reobj.equals("Clear")) {
	         
	    	  lblNewLabel23.setIcon(li.Labelimage("C:\\Users\\0\\Desktop\\자바채팅프로그램\\맑음.png",50,50));
	         
	      }
	      else if(WeatherByGPSApplication.reobj.equals("Mist")){
	    	  lblNewLabel23.setIcon(li.Labelimage("C:\\Users\\0\\Desktop\\자바채팅프로그램\\흐림.png",50,50));
	      }
	      
	      else {
	    	  lblNewLabel23.setIcon(li.Labelimage("C:\\Users\\0\\Desktop\\자바채팅프로그램\\비.png",50,50));
	      }
	      
	      lbwnum.setText(WeatherByGPSApplication.retemp2);//온도 받아온 걸로 하기
	      
	      lblNewLabel_5.setText(WeatherByGPSApplication.reobj);
	      
	      System.out.println(WeatherByGPSApplication.retemp2);
	      System.out.println(WeatherByGPSApplication.reobj);
	      
	      
	      
	   }

	static void send_message(String str) //서버에게 메세지를 보내는 부분
	{

		try {
			dos.writeUTF(str);
		} catch (IOException e) { //에러처리부분

		}

	}

	public void ChatSaveDB(String id){
		Connection conn= null;
		Statement stmt = null;
		String msg = textField.getText().trim();
		System.out.println(msg);
		long curtime = System.currentTimeMillis();
		SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String time = timeFormat.format(new Date(curtime));
		try {
			Class.forName(DRIVER);
			conn = (Connection) DriverManager.getConnection(URL, USER, PASS);
			stmt = (Statement) conn.createStatement();
			if(msg != null) {
				stmt.executeUpdate("insert into chatsave" + "(Roomname,Id,Text,Time) values('"+My_Room+"'" + ",'"+id+"','"+msg+"','"+time+"')");
			}
		} catch (ClassNotFoundException cnfe) {
			System.out.println("해당 클래스를 찾을수 없습니다." + cnfe.getMessage());
		} catch (SQLException se) {
			System.out.println(se.getMessage());
		} finally {
			try {
				stmt.close();
			} catch (Exception ignored) {
			}
			try {
				conn.close();
			} catch (Exception ignored) {

			}
		}

	}

	public void Login(String id){
		Connection conn= null;
		Statement stmt = null;
		String msg = "로그인";
		System.out.println(msg);
		long curtime = System.currentTimeMillis();
		SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String time = timeFormat.format(new Date(curtime));
		try {
			Class.forName(DRIVER);
			conn = (Connection) DriverManager.getConnection(URL, USER, PASS);
			stmt = (Statement) conn.createStatement();
			if(msg != null) {
				stmt.executeUpdate("insert into chatlogin" + "(id, login, time) values('" +id+"','"+msg+"','"+time+"')");
			}
		} catch (ClassNotFoundException cnfe) {
			System.out.println("해당 클래스를 찾을수 없습니다." + cnfe.getMessage());
		} catch (SQLException se) {
			System.out.println(se.getMessage());
		} finally {
			try {
				stmt.close();
			} catch (Exception ignored) {
			}
			try {
				conn.close();
			} catch (Exception ignored) {

			}
		}

	}

	public void Logout(String id){
		Connection conn= null;
		Statement stmt = null;
		String msg = "로그아웃";
		System.out.println(msg);
		long curtime = System.currentTimeMillis();
		SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String time = timeFormat.format(new Date(curtime));
		try {
			Class.forName(DRIVER);
			conn = (Connection) DriverManager.getConnection(URL, USER, PASS);
			stmt = (Statement) conn.createStatement();
			if(msg != null) {
				stmt.executeUpdate("insert into chatlogin" + "(id, login, time) values( '"+ id+"','"+msg+"','"+time+"')");
			}
		} catch (ClassNotFoundException cnfe) {
			System.out.println("해당 클래스를 찾을수 없습니다." + cnfe.getMessage());
		} catch (SQLException se) {
			System.out.println(se.getMessage());
		} finally {
			try {
				stmt.close();
			} catch (Exception ignored) {
			}
			try {
				conn.close();
			} catch (Exception ignored) {

			}
		}

	}


	@Override
	public void actionPerformed(ActionEvent e) {
		//login_btn = 로그인 버튼(접속버튼)

		if(e.getSource() == btnLogin) {
			try {
				LoginDB ld = new LoginDB();
				int result = ld.checkIDPW(textFieldID.getText(), textFieldPW.getText());	

				if (result == 0) {
					System.out.println("로그인성공");
					id = textFieldID.getText().trim(); //id를 받아오는 부분

					Network();
					weather_view();
				}
				else {
					System.out.println("홀롤ㄹㄹ");
				}
			}

			catch (Exception e1) {
				System.out.println("문제있음");

			}


		}
		else if(e.getSource()== btnJoin) {
			new JoinUI();

		}
		else if(e.getSource()== btnnote) {
			System.out.println("쪽지 보내기 버튼 클릭");
			String user = (String)User_list.getSelectedValue();

			String note = JOptionPane.showInputDialog("보낼메세지");

			if(note != null)
			{
				send_message("Note/" + user + "/" + note);
				//ex) Note/User2/나는 User1이야
			}
			System.out.println("받는 사람 : " + user + "보낼 내용 : " + note);

		}

		else if(e.getSource()== btnchatnote) {
			System.out.println("대화목록의 쪽지보내기 버튼 클릭");
			String user = (String)Chat_list.getSelectedValue();

			String note = JOptionPane.showInputDialog("보낼메세지");

			if(note != null)
			{
				send_message("Note/" + user + "/" + note);
				//ex) Note/User2/나는 User1이야
			}
			System.out.println("받는 사람 : " + user + "|보낼 내용 : " + note);

		}

		else if(e.getSource()== btnchatinvite) {
			U_GUI.setVisible(false);
		}

		else if(e.getSource()== btninvite) {


			System.out.println("초대버튼클릭");
			myroom = My_Room;
			String user = (String)Wait_list.getSelectedValue();


			int x = JOptionPane.showConfirmDialog(U_GUI, "\'"+ user+ "\'님을 \n초대하시겠습니까?", "초대", JOptionPane.YES_NO_OPTION);

			if (x == JOptionPane.OK_OPTION){

				System.out.println("초대할사람 " + user );
				System.out.println("내방이름 " + My_Room );

				send_message("Invite/" + user + "/" + My_Room );
				U_GUI.setVisible(false);
			}

		}

		else if(e.getSource()== btnEnter) {
			String JoinRoom = (String)Room_list.getSelectedValue();
			send_message("JoinRoom/" + JoinRoom);

			chat_list.add(id);
			wait_list.remove(id);

			btnCreate.setEnabled(false);
			btnEnter.setEnabled(false);
			this.Chat_GUI.setVisible(true);

			System.out.println("방 참여 버튼 클릭");
		}

		else if(e.getSource()== btnCreate) {
			String roomname = JOptionPane.showInputDialog(this, "방 이름을 입력하세요");
			if(roomname != null)  {
				send_message("CreateRoom/" + roomname);

				chat_list.add(id);
				wait_list.remove(id);
				btnCreate.setEnabled(false);
				btnEnter.setEnabled(false);
				this.Chat_GUI.setVisible(true);
			}


			System.out.println("방 만들기 버튼 클릭");
		}

		else if(e.getSource()== btnsend) {
			send_message("Chatting/" + My_Room + "/" + textField.getText().trim());
			// Chatting + 방이름 + 내용
			ChatSaveDB(id);
			textField.setText("");
			textField.requestFocus();
		}

		else if(e.getSource()== mntmNewMenuItem  || e.getSource() == btnmem) {
			//관리자모드

			String mempw = JOptionPane.showInputDialog(this, "관리자 비밀번호를 입력하세요" );
			if(mempw != null) {
				if(mempw.equals("0253"))  {
					new MemberUI();
				}
				else 
					JOptionPane.showMessageDialog(this, "비밀번호가 맞지 않습니다");
			}
		}
		else if(e.getSource()== mntmNewMenuItem_1) {
			dispose();
		}

		else if(e.getSource()== mntmlogout) {
			//로그아웃
			System.out.println("로그아웃 클릭");

			int logout = JOptionPane.showConfirmDialog(this, "로그아웃 하시겠습니까?", "로그아웃", JOptionPane.YES_NO_OPTION);

			if (logout == JOptionPane.OK_OPTION){
				try {
					textFieldID.setText("");
					textFieldPW.setText("");
					textFieldID.grabFocus();
					user_list.remove(id);
					wait_list.remove(id);

					user_list.removeAllElements();
					wait_list.removeAllElements();

					room_list.removeAllElements();

					os.close();
					is.close();
					dos.close();
					dis.close();
					socket.close();

					dispose();
					Login_GUI.setVisible(true);


				}
				catch(Exception e2) {

				}
			}


		}

		else if(e.getSource() == btnemo) {
			System.out.println("이모티콘번튼");
			this.Emo_GUI.setVisible(true);
		}

		//프로필 화면의 버튼들
		else if(e.getSource() == btnUpdate) {
			String pw = ptfpw.getText();

			if(pw.length()==0){ //길이가 0이면

				JOptionPane.showMessageDialog(this, "비밀번호를 꼭 입력하세요");
				return; //메소드 끝
			}

			boolean ok = pd.update();

			int x = JOptionPane.showConfirmDialog(this, "수정하시겠습니까?", "프로필 수정", JOptionPane.YES_NO_OPTION);

			if (x == JOptionPane.OK_OPTION){
				if(ok) {

					JOptionPane.showMessageDialog(this, "수정되었습니다.");
				}
				else
					JOptionPane.showMessageDialog(this, "수정실패ㅜㅜ");

			}
			else
				System.out.println("수정취소"); 



		}
		else if(e.getSource() == btnDelete) {
			pd.profile(id);
		}
		else if(e.getSource() == btnphupdate) {
			pd.photoupdate();
		}
		else if(e.getSource() == btnphdelete) {
			pd.photodelete();
		}
		else if(e.getSource() == button) {

		}

		else if(e.getSource() == button_4) {
			JColorChooser chooser = new JColorChooser();

			selectedColor = chooser.showDialog(null,"Color",Color.YELLOW);


		}

		else if(e.getSource() == button_5) {
			JColorChooser chooser = new JColorChooser();

			nselectedColor = chooser.showDialog(null,"Color",Color.YELLOW);
		}

		else if(e.getSource() == Main.share_btn) {
			int x = JOptionPane.showConfirmDialog(Main.panel, "채팅방에 공유하시겠습니까?", "공유", JOptionPane.YES_NO_OPTION);

	         if (x == JOptionPane.OK_OPTION){
	            System.out.println("내방이름 " + Client.My_Room);
	            
	            JOptionPane.showConfirmDialog(Main.panel,Main.mappath, "공유", JOptionPane.YES_NO_OPTION);


	            send_message("Sendmap/" + My_Room + "/" + Main.mappath);
	         }
		}

	}



	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == 10)
		{
			send_message("Chatting/" + My_Room + "/" + textField.getText().trim());
			// Chatting + 방이름 + 내용
			textField.setText("");
			textField.requestFocus();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}


	public static void main(String[] args) {
		new Client(); //익명으로 Client객체 발생

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource()== lbur) {
			panelur.setVisible(true);
			panelwcr.setVisible(false);
			panelwr.setVisible(false);
			panelpr.setVisible(false);
		}

		if(e.getSource()== lbwcr) {
			panelur.setVisible(false);
			panelwcr.setVisible(true);
			panelwr.setVisible(false);
			panelpr.setVisible(false);
		}

		if(e.getSource()== lbwr) {
			panelur.setVisible(false);
			panelwcr.setVisible(false);
			panelwr.setVisible(true);
			panelpr.setVisible(false);
		}

		if(e.getSource()== lbpr) {   //프로필 화면을 클릭하면

			String pwpw = pd.Pwcheck(id);
			String pwcheck = JOptionPane.showInputDialog("비밀번호를 입력하세요");

			if (pwcheck != null) {
				if(pwcheck.equals(pwpw)  ) {
					panelur.setVisible(false);
					panelwcr.setVisible(false);
					panelwr.setVisible(false);
					panelpr.setVisible(true);
					//데이터 불러오기
					pd.profile(id);

				}
				else {
					JOptionPane.showMessageDialog(this, "비밀번호가 일치하지 않습니다");
				}
			}

		}

		if(e.getSource()== lbemo) {   //이모티콘클릭
			Emo_GUI.setVisible(false);
			new Emoticon();
		}

		if(e.getSource()== lbphto) {   //사진전송
			Emo_GUI.setVisible(false);

			FileDialog fdlg = new FileDialog(new JFrame(),"file open",FileDialog.LOAD);
			fdlg.setVisible(true);
			phpath = fdlg.getDirectory()+fdlg.getFile();


			if(fdlg.getFile()==null){      
				JOptionPane.showMessageDialog(Chat_GUI, "파일을 선택하지 않았네요", "주의", JOptionPane.WARNING_MESSAGE);
			}  
			else{
				System.out.println("파일을 선택하셧구뇽");

				System.out.println(phpath);

				send_message("Sendphoto/" + My_Room + "/" + phpath);



			}

		}

		if(e.getSource() == lbdocu) {
			new Main();
		}


		if(e.getSource()== lbback) {   //뒤로가기 클릭
			int exit = JOptionPane.showConfirmDialog(Chat_GUI, "퇴장하시겠습니까?", "채팅방 퇴장", JOptionPane.YES_NO_OPTION);

			if (exit == JOptionPane.OK_OPTION){

				send_message("ExitRoom/"+My_Room );

				wait_list.add(id);

				chat_list.remove(id);
				chat_list.removeAllElements();

				btnCreate.setEnabled(true);
				btnEnter.setEnabled(true);
				Chat_GUI.setVisible(false);
				U_GUI.setVisible(false);
				Emoticon.b.setVisible(false);
				My_Room = null;
			}

		}

		if(e.getSource()== lbselect) {   
			//채팅에 참여한 유저보여주는 화면
			this.U_GUI.setVisible(true);

		}

		if(e.getSource()== lbfindid) {
			new FindID();
		}

		if(e.getSource()== lbfindpw) {
			new FindPW();
		}

		else{
			StyledDocument doc = Chat_area.getStyledDocument();

			javax.swing.text.Element ele = doc.getCharacterElement(Chat_area.viewToModel(e.getPoint()));
			javax.swing.text.AttributeSet as = ele.getAttributes();
			ChatLinkListener fla = (ChatLinkListener)as.getAttribute("linkact");
			
			
			StyledDocument doc1 = Chat_area.getStyledDocument();

			javax.swing.text.Element ele1 = doc1.getCharacterElement(Chat_area.viewToModel(e.getPoint()));
			javax.swing.text.AttributeSet as1 = ele1.getAttributes();
			ChatLinkListener1 fla1 = (ChatLinkListener1)as.getAttribute("linkact1");
			if(fla != null)
			{
				try {
					fla.execute();


				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			if(fla1 != null)
			{
				try {
					fla1.execute();


				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}


	public void mouseEntered(MouseEvent e) {
		if(e.getSource()== lbfindid || e.getSource()== lbfindpw) {

			JLabel j = (JLabel)e.getSource();

			if(e.getSource()== lbfindid)
				j.setText("아이디");
			if(e.getSource()== lbfindpw)
				j.setText("비밀번호 찾기");

			Font font = j.getFont();
			Map attributes = font.getAttributes();
			attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
			j.setFont(font.deriveFont(attributes));
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {

		if(e.getSource()== lbfindid || e.getSource()== lbfindpw) {
			JLabel j = (JLabel)e.getSource();

			if(e.getSource()== lbfindid)
				j.setText("아이디");
			if(e.getSource()== lbfindpw)
				j.setText("비밀번호 찾기");

			Font font = j.getFont();
			Map attributes = font.getAttributes();
			attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE);
			j.setFont(font.deriveFont(attributes));
		}
	}




}


class ChatLinkListener extends AbstractAction {

	private String textLink;
	private InputStream chatis = null;
	private OutputStream chatos = null;
	private int numOfBytes;
	private byte[] imageData;

	ChatLinkListener(String textLink) {
		this.textLink = textLink;
	}
	protected void execute() throws Exception {
		FileDialog fdlg = new FileDialog(new JFrame(),"저장",FileDialog.SAVE);
		fdlg.setVisible(true); 
		String savepath = fdlg.getDirectory() + fdlg.getFile();

		if(fdlg.getDirectory()==null){      
			JOptionPane.showMessageDialog(null, "경로를 선택하지 않았네요", "주의", JOptionPane.WARNING_MESSAGE);
			return;
		}  
		else{

			try {
				chatis = new FileInputStream(Client.asdf);
				chatos = new FileOutputStream(savepath);

				imageData = new byte[chatis.available( )]; //배열크기를 사진의 크기로 정함


				while (chatis.read(imageData, 0, imageData.length) != -1) //
				{
					chatos.write(imageData);

					JOptionPane.showMessageDialog(null, "파일을 저장했습니다", "저장", JOptionPane.INFORMATION_MESSAGE);

				}


			}catch (Exception e)	{
				System.out.println("파일 입출력 에러!!" + e);
			}
			finally 	{
				try 	{
					// 파일 닫기. 여기에도 try/catch가 필요하다.
					chatis.close();
					chatos.close();
				}
				catch (Exception e)	{
					System.out.println("닫기 실패" + e);
				}

			}
		}
	}


	public void actionPerformed(ActionEvent e) {
		try {
			execute();


		} catch (Exception e1) {

			e1.printStackTrace();
		}
	}
}
class ChatLinkListener1 extends AbstractAction {

	private String textLink1;
	private InputStream chatis = null;
	private OutputStream chatos = null;
	private int numOfBytes;
	private byte[] imageData;

	ChatLinkListener1(String textLink1) {
		this.textLink1 = textLink1;
	}
	protected void execute() throws Exception {
		System.out.println(Client.asdf1);

	}


	public void actionPerformed(ActionEvent e) {
		try {
			execute();


		} catch (Exception e1) {

			e1.printStackTrace();
		}
	}
}



