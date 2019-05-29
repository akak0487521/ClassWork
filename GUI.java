import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.ServerSocket;

import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JProgressBar;
import javax.swing.JInternalFrame;
import javax.swing.ButtonGroup;
import javax.swing.JPopupMenu;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import java.awt.GridLayout;
import java.awt.GridBagConstraints;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JOptionPane;

public class GUI extends JFrame {
	private JPanel contentPane;
	/*sub gui*/
	private JPanel view_swicther,menu_gui,game_gui,ip_gui;
	/*game*/
	private ArrayList<JButton> player1_list= new ArrayList<JButton>();
	private ArrayList<JButton> player2_list= new ArrayList<JButton>();
	private JPopupMenu weapon_bar;
	private JRadioButtonMenuItem weapon1,weapon2;
	private JPanel game_set_area,game_botton_area;
	private JButton check_button,exit_game_button;
	private JLabel player1,player2;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	/*menu*/
	private JButton create_button,search_button,end_button;
	/*ip*/
	private JTextField textField;
	private JLabel connect_ip;
	private JButton ip_check_button1;
	/*option bar*/
	private JMenuBar option_bar;
	private JMenu setting,music_setting,window_setting;
	private JSlider music_slider;
	private JRadioButtonMenuItem window_size1,window_size2;
	private JPanel area1;
	private JPanel area2;
	private JLabel player1_own;
	private JLabel player2_own;
	/*ip is server or client*/
	private boolean isServer = false;
	private int port = 8000;
	
	private ServerSocket m_serverSocket;
	private Socket s_socket;
	private Socket c_socket;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
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
	public GUI() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(".\\temp_guess.jpg"));
		setTitle("Guess");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setPreferredSize(new Dimension(1020, 1080));
		contentPane.setMinimumSize(new Dimension(800, 600));
		contentPane.setMaximumSize(new Dimension(1020, 1080));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		init();
	}
	/*
	*  game page building
	*/
	private JPanel getGame() {
		game_gui = new JPanel();
		game_gui.setLayout(new BorderLayout(0, 0));
		
		/*
		*  popmenu 武器列
		*/
		weapon_bar = new JPopupMenu();
		
		weapon1 = new JRadioButtonMenuItem("Weapon1");
		weapon_bar.add(weapon1);
		
		weapon2 = new JRadioButtonMenuItem("Weapon2");
		weapon_bar.add(weapon2);
		
		/*
		*  玩家格與對手格
		*/
		game_set_area = new JPanel();
		/*
		*  底部畫面顯示與設定
		*/
		game_botton_area = new JPanel();
		FlowLayout fl_game_botton_area = (FlowLayout) game_botton_area.getLayout();
		fl_game_botton_area.setHgap(50);
		
		player1 = getLabel("player1",true,Color.BLUE,Color.WHITE,new Font("新細明體", Font.BOLD, 20));
		player1_own = getLabel("\u9084\u5269\u9918:",true,Color.BLACK,Color.WHITE,new Font("新細明體", Font.BOLD, 20));
		check_button = getMenuButton("\u78BA\u8A8D",false,false,true);
		exit_game_button = getMenuButton("\u4E2D\u96E2",false,false,true);
		player2_own = getLabel("\u9084\u5269\u9918:",true,Color.BLACK,Color.WHITE,new Font("新細明體", Font.BOLD, 20));
		player2 = getLabel("player2",true,Color.RED,Color.WHITE,new Font("新細明體", Font.BOLD, 20));
		
		game_botton_area.add(player1);
		game_botton_area.add(player1_own);
		game_botton_area.add(check_button);
		game_botton_area.add(exit_game_button);
		game_botton_area.add(player2_own);
		game_botton_area.add(player2);
		
		/*game_gui setting*/
		game_gui.add(weapon_bar, BorderLayout.NORTH);
		game_gui.add(game_set_area, BorderLayout.CENTER);
		game_set_area.setLayout(new GridLayout(1, 2, 0, 0));
		
		area1 = getGridPanel(10,10);
		area1.setBackground(Color.BLUE);
		game_set_area.add(area1);
		
		area2 = getGridPanel(10,10);
		area2.setBackground(Color.YELLOW);
		game_set_area.add(area2);
		game_gui.add(game_botton_area, BorderLayout.SOUTH);
		return game_gui;
	}
	/*
	* gameboard for ship
	*/
	private JPanel getGridPanel(int row,int col) {
		JPanel temp = new JPanel();
		temp.setLayout(new GridLayout(row, col, 0, 0));
		for(int i = row;i>=1;i--) {
			for(int j = col;j>=1;j--) {
				JButton temp2 = new JButton();
				temp2.setBackground(Color.RED);
				temp2.setContentAreaFilled(false);
                temp2.setOpaque(true);
				temp.add(temp2);
			}
		}
		return temp;
	}
	
	/*
	*  ip page building
	*/
	private JPanel getIp() {
		ip_gui = new JPanel();
		ip_gui.setLayout(new GridBagLayout());
		
		GridBagConstraints c1 = new GridBagConstraints();
		setC(c1,1,0,1,1,GridBagConstraints.NONE,GridBagConstraints.CENTER);
		
		connect_ip = new JLabel("\u5C0D\u624BIP");
		ip_gui.add(connect_ip,c1);
		
		setC(c1,1,1,3,1,GridBagConstraints.NONE,GridBagConstraints.CENTER);
		textField = new JTextField();
		textField.setColumns(10);
		ip_gui.add(textField,c1);
		
		setC(c1,1,2,1,1,GridBagConstraints.NONE,GridBagConstraints.CENTER);
		ip_check_button1 = new JButton("\u78BA\u8A8D");
		ip_check_button1.addActionListener(new page_changer(this));
		ip_gui.add(ip_check_button1,c1);
		return ip_gui;
	}
	/*
	*  menu page building
	*/
	private JPanel getMenu() {
		menu_gui = new JPanel();
		menu_gui.setLayout(new GridBagLayout());
		create_button = getMenuButton("\u5275\u5EFA\u623F\u9593",false,false,false);
		search_button = getMenuButton("\u5C0B\u627E\u5C0D\u624B",false,false,false);
		end_button = getMenuButton("\u7D50\u675F\u904A\u6232",false,false,false);
		
		GridBagConstraints c1 = new GridBagConstraints();
		setC(c1,3,0,2,2,GridBagConstraints.NONE,GridBagConstraints.CENTER);
		menu_gui.add(create_button,c1);
		setC(c1,3,5,2,2,GridBagConstraints.NONE,GridBagConstraints.CENTER);
		menu_gui.add(search_button,c1);
		setC(c1,3,7,2,2,GridBagConstraints.NONE,GridBagConstraints.CENTER);
		menu_gui.add(end_button,c1);
		return menu_gui;
	}
	/*
	*  menubar building
	*/
	private JMenuBar getOption() {
		option_bar = new JMenuBar();
		option_bar.setOpaque(false);
		option_bar.setBorderPainted(false);
		
		setting = getMenu("\u8A2D\u5B9A");
		music_setting = getMenu("\u97F3\u91CF\u8A2D\u5B9A");
		window_setting = getMenu("\u8996\u7A97\u5927\u5C0F");
		
		music_slider = new JSlider();
		music_setting.add(music_slider);
		
		window_size1 = getRadioMenuItem("800 * 600");
		window_size1.setSelected(true);
		window_size2 = getRadioMenuItem("1020 * 1080");
		
		buttonGroup.add(window_size1);
		buttonGroup.add(window_size2);
		option_bar.add(setting);
		setting.add(music_setting);
		window_setting.add(window_size1);
		window_setting.add(window_size2);
		setting.add(window_setting);
		return option_bar;
	}
	/*
	 * cardlayout setting
	 */
	private void init() {
	view_swicther = new JPanel();
	view_swicther.setLayout(new CardLayout());
	view_swicther.add(getGame(),"game");
	view_swicther.add(getMenu(),"menu");
	view_swicther.add(getIp(),"ip");
	contentPane.add(view_swicther);
	contentPane.add(getOption(), BorderLayout.NORTH);
	((CardLayout)view_swicther.getLayout()).show(view_swicther,"menu");
	}
	/*
	*	actionPerformed for button 轉換頁面
	*/
	public void actionPerformed(ActionEvent event) {
		Object pulse = event.getSource();
		if(pulse == create_button ||pulse == check_button) {
            ((CardLayout)view_swicther.getLayout()).show(view_swicther,"game");
			isServer = true;
			try{
				m_serverSocket = new ServerSocket(port);
				s_socket = m_serverSocket.accept();
				m_serverSocket.close();
			}
			catch (IOException e)
      			{
        		System.out.println(e.getMessage());//出現例外時，捕捉並顯示例外訊息(連線成功不會出現例外)
        		}
            game_gui.repaint();	//等待對手搜尋
		} else if(pulse == ip_check_button1) {
			boolean isOk = false;
			String user_ip = textField.getText();  //獲取ip
			/*
			* 格式確認
			*/
			IP_confirm ipcon = new IP_confirm();
			ipcon.set_ip_content(user_ip);
			isOk=ipcon.ip_format();
			//if ip check ok?isOk=true:showMessageDialog("不合法的IP");
			if(isOk) {
				 try
  				 {
        			 m_socket = new Socket(ip, port);//建立連線。(ip為伺服器端的ip，port為伺服器端開啟的port)
     				 }
     				 catch (IOException e)
      			 	 {
        		  	 System.out.println(e.getMessage());
      			 	 }
				
				((CardLayout)view_swicther.getLayout()).show(view_swicther,"game");
				game_gui.repaint();	//等之後ip確認輸入
			} else {
				JOptionPane.showMessageDialog(null, "錯誤格式的ip", "錯誤顯示",JOptionPane.ERROR_MESSAGE);
			}
		} else if(pulse == search_button) {
            ((CardLayout)view_swicther.getLayout()).show(view_swicther,"ip");
			isServer = false;
		} else if(pulse == end_button) {
            System.exit(0);
		} else if(pulse == exit_game_button) {
			((CardLayout)view_swicther.getLayout()).show(view_swicther,"menu");
		}
		contentPane.validate();
	}	
	/*
	 * 	Label set
	 */
	private JLabel getLabel(String message,boolean o,Color back,Color word,Font word_shape) {
		JLabel a = new JLabel(message);
		a.setOpaque(o);
		a.setBackground(back);
		a.setForeground(word);
		a.setFont(word_shape);
		return a;
	}
	/*
	 * Button in menu page
	*/
	private JButton getMenuButton(String name, Rectangle loc,boolean o,boolean c,boolean b) {
		JButton x = new JButton(name);
		x.addActionListener(new page_changer(this));
		x.setOpaque(o);
		x.setContentAreaFilled(c);
		x.setBorderPainted(b);
		x.setBounds(loc);
		return x;
	}
	/*
	*	Button in menu page overlaoding
	*/
	private JButton getMenuButton(String name,boolean o,boolean c,boolean b) {
		JButton x = new JButton(name);
		x.addActionListener(new page_changer(this));
		x.setOpaque(o);
		x.setContentAreaFilled(c);
		x.setBorderPainted(b);
		return x;
	}
	/*
	*  JRadioButton in menubar
	*/
	private JRadioButtonMenuItem getRadioMenuItem(String message) {
		JRadioButtonMenuItem a = new JRadioButtonMenuItem(message);
		a.setHorizontalAlignment(SwingConstants.CENTER);
		return a;
	}
	/*
	* menubar
	*/
	private JMenu getMenu(String message) {
		JMenu a = new JMenu(message);
		a.setOpaque(false);
		a.setContentAreaFilled(false);
		a.setBorderPainted(true);
		return a;
	}
	/*
	*  GridBagConstraints setting and reuse
	*/
	private void setC(GridBagConstraints a,int x,int y,int gw,int gh, int f,int anc) {
        a.gridx = x;
        a.gridy = y;
        a.gridwidth = gw;
        a.gridheight = gh;
        a.fill = f;
        a.anchor = anc;
	}
	/*
	 * 圖片重新繪製resize
	 * return Image
	 */
	private Image getScaledImage(Image srcImg, int w, int h){
	    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2 = resizedImg.createGraphics();

	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(srcImg, 0, 0, w, h, null);
	    g2.dispose();

	    return resizedImg;
	}
}

class page_changer implements ActionListener {
	private GUI origin;
	public page_changer(GUI temp) {
		origin = temp;
	}
	public void actionPerformed(ActionEvent event) {
		origin.actionPerformed(event);
	}
}
