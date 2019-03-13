//------------------------------------------------------
//範例程式5：利用鍵盤來控制角色移動
//本程式以讀檔方式載入地圖，地圖檔名稱；map1.txt
//預設地圖大小為10*5，使用者可以自行定義
//陣列值說明 : 0=牆壁 ; 1=道路 ; 2:終點
//程式中已實作上下按鈕的程式碼
//程式中使用Judge()來判斷是否碰撞，已實作判斷是否碰到牆壁
//程式中已實作鍵盤上下移動的程式碼
//------------------------------------------------------

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class practice5 extends JFrame {

	private final JLabel actor = new JLabel();
	private final JLabel wallLabel = new JLabel();
	private final JLabel roadLabel = new JLabel();
	private final JButton UpButton = new JButton();
	private final JButton RightButton = new JButton();
	private final JButton LeftButton = new JButton();
	private final JButton DownButton = new JButton();
	private final JButton pass = new JButton();
	private final JLabel successLabel = new JLabel();
	private int wx;
	private int wy;
	private int mapNumber =1;

	Icon sea_icon = new ImageIcon("./png/sea.png");
	Icon tree_icon = new ImageIcon("./png/tree.png");
	Icon road_icon = new ImageIcon("./png/road.png");
	Icon wall_icon = new ImageIcon("./png/wall.png");
	Icon success_icon = new ImageIcon("./png/success.png");
	Icon box_icon = new ImageIcon("./png/box.PNG");
	Icon ActorRight_icon = new ImageIcon("./png/b40right.png");
	Icon ActorLeft_icon = new ImageIcon("./png/b40left.png");
	Icon Box_icon = new ImageIcon("./png/box.png");
	Icon switch_icon = new ImageIcon("./png/switch.png");
	Icon warrior_icon = new ImageIcon("./png/warrior.png");

	private int[][] map;
	int current_x=0, current_y=0;
	private final JLabel messageLabel = new JLabel();
	private final JLabel boxLabel = new JLabel();
	private final JLabel change = new JLabel();
	private final JLabel warrior = new JLabel();
	/**
	 * Launch the application
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			practice5 frame = new practice5();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame
	 */
	public practice5() {
		super();
		setBounds(100, 100, 500, 375);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
			jbInit();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		//
	}
	private void jbInit() throws Exception {
		getContentPane().setLayout(null);
		getContentPane().addKeyListener(new ThisContentPaneKeyListener());
		getContentPane().add(actor);
		getContentPane().add(boxLabel);
		getContentPane().add(warrior);
		getContentPane().add(change);
		change.setVisible(false);
		warrior.setVisible(false);
		change.setIcon(switch_icon);
		warrior.setIcon(warrior_icon);
		actor.setIcon(ActorRight_icon);
		actor.setBounds(0, 0, 40, 40);
		boxLabel.setIcon(Box_icon);
		getContentPane().getFocusListeners();
		
		map=readMap("map"+mapNumber+".txt");//宣告一個二維陣列map來存取readMap回傳的二維陣列值
		mapNumber++;
		boolean box=false;
		for (int i=0; i<5; i++ )
		  for (int j=0; j<10; j++ )
		  {	
			  JLabel tmp = new JLabel();
			getContentPane().add(tmp);
			switch (map[i][j]){
			case 0: 
			    tmp.setIcon(road_icon);
			    break;
			case 1: 
			    tmp.setIcon(wall_icon);
			    break;
			case 2: 
			    tmp.setIcon(success_icon);
			    break;
			case 3:
				tmp.setIcon(tree_icon);
				map[i][j]=1;
				break;
			case 4:
				tmp.setIcon(sea_icon);
				map[i][j]=1;
				break;
			case 5:
				tmp.setIcon(road_icon);
				boxLabel.setBounds(0+j*40, 0+i*40,40,40);
				break;
			case 6:
				change.setBounds(0+j*40, 0+i*40,40,40);
				wx=j;
				wy=i;
				change.setVisible(true);
				break;
			case 7:
				tmp.setIcon(road_icon);
				wx=j;
				wy=i;
				warrior.setBounds(0+j*40, 0+i*40,40,40);
				warrior.setVisible(true);
				break;
			default:
			    tmp.setIcon(road_icon);
			}
			tmp.setBounds(0+j*40, 0+i*40,40, 40);
		  } 

		
		
		
		getContentPane().add(wallLabel);
		wallLabel.setIcon(wall_icon);
		wallLabel.setText("wall");
		wallLabel.setBounds(171, 262, 40, 40);
		
		getContentPane().add(roadLabel);
		roadLabel.setIcon(road_icon);
		roadLabel.setText("road");
		roadLabel.setBounds(217, 262, 40, 40);
		
		getContentPane().add(UpButton);
		UpButton.addActionListener(new UpButtonActionListener());
		UpButton.setText("Up");
		UpButton.setBounds(57, 236, 56, 20);
		
		getContentPane().add(RightButton);
		RightButton.addActionListener(new RightButtonActionListener());
		RightButton.setText("Right");
		RightButton.setBounds(97, 262, 68, 20);
		
		getContentPane().add(LeftButton);
		LeftButton.addActionListener(new LeftButtonActionListener());
		LeftButton.setText("Left");
		LeftButton.setBounds(10, 262, 56, 20);
		
		getContentPane().add(DownButton);
		DownButton.addActionListener(new DownButtonActionListener());
		DownButton.setText("Down");
		DownButton.setBounds(46, 288, 68, 20);
		
		getContentPane().add(pass);
		pass.addActionListener(new PassActionListener());
		pass.setText("pass");
		pass.setBounds(300, 288, 68, 20);
		pass.setVisible(false);
		
		getContentPane().setFocusable(true);
		getContentPane().requestFocus();
	}
	private int[][] readMap(String mapfile) throws FileNotFoundException {
		
		File f = new File(mapfile);			//宣告一個File來讀取地圖檔
		int[][] map=new int[5][10];			//宣告一個二為陣列存取從地圖檔讀近來的值
		Scanner sc = new Scanner(f);		//利用Scanner來讀取地圖檔的值
			for(int i=0;i<5;i++){			//宣告一個巢狀回圈來讀取地圖檔
				for(int j=0;j<10;j++){
					map[i][j]=sc.nextInt();	//將從地圖檔讀取到的值放入陣列
				}
			}
		return map; 						//回傳讀取到的二維陣列值
	}
	
	private class UpButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			upButton_actionPerformed(e);
		}
	}
	private class DownButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			downButton_actionPerformed(e);
		}
	}
	private class RightButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			rightButton_actionPerformed(e);
		}
	}
	private class LeftButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			leftButton_actionPerformed(e);
		}
	}
	
	private class PassActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			PassButton_actionPerformed(e);
		}
	}
	
	private class ThisContentPaneKeyListener extends KeyAdapter {
			
		public void keyPressed(KeyEvent e) {
			thisContentPane_keyPressed(e);
		}
	}

	protected void upButton_actionPerformed(ActionEvent e) {
		if(current_y>0){					//判斷current_y是否超過地圖範圍
			current_y--;					//取得移動角色上方的值
			if (judge()){					//利用judge()來判斷地圖是否能行走
			   int x= actor.getX();			//取得移動角色目前的X座標
			   int y= actor.getY();			//取得移動角色目前的Y座標
			   actor.setLocation(x, y-40);	//移動角色的y座標向上移動40
			}
			else current_y++;				//若judge()判斷地圖不能行走，還原current_y
		}
		getContentPane().requestFocus();
	}
	protected void downButton_actionPerformed(ActionEvent e) {
		if(current_y<4){
			current_y++;
			if (judge())
			{
			   int x= actor.getX();
			   int y= actor.getY();
			   actor.setLocation(x, y+40);
			}
			else current_y--;
		}
		getContentPane().requestFocus();
	}

	protected void rightButton_actionPerformed(ActionEvent e) {
		if(current_x<9){
			current_x++;
			if (judge())
			{
			   actor.setIcon(ActorRight_icon);
			   int x= actor.getX();
			   int y= actor.getY();
			   actor.setLocation(x+40, y);
			}
			else 	current_x--;
		}
		getContentPane().requestFocus();
	}
	protected void leftButton_actionPerformed(ActionEvent e) {
		if(current_x>0){
			current_x--;
			if (judge())
			{
			   actor.setIcon(ActorLeft_icon);
			   int x= actor.getX();
			   int y= actor.getY();
			   actor.setLocation(x-40, y);
			}
			else 	current_x++;
		}
		getContentPane().requestFocus();
	}

	public void downButton_action(ActionEvent e){
		if(current_y<4){
			current_y++;
			if (judge())
			{
			   int x= actor.getX();
			   int y= actor.getY();
			   actor.setLocation(x, y+40);
			}
			else 	current_y--;
		}
		getContentPane().requestFocus();
	}
	
	public void PassButton_actionPerformed(ActionEvent e)
	{
		getContentPane().removeAll();
		getContentPane().repaint();
		current_x=0;
		current_y=0;
		getContentPane().add(actor);
		getContentPane().add(boxLabel);
		getContentPane().add(warrior);
		getContentPane().add(change);
		change.setVisible(false);
		warrior.setVisible(false);
		warrior.setIcon(warrior_icon);
		actor.setIcon(ActorRight_icon);
		change.setIcon(switch_icon);
		actor.setBounds(0, 0, 40, 40);
		boxLabel.setIcon(Box_icon);
		try {
		map=readMap("map"+mapNumber+".txt");
		mapNumber++;
		}
		catch(Throwable ev)
		{
			ev.printStackTrace();
		}
		
		boolean box=false;
		for (int i=0; i<5; i++ )
		  for (int j=0; j<10; j++ )
		  {	
			  JLabel tmp = new JLabel();
			getContentPane().add(tmp);
			switch (map[i][j]){
			case 0: 
			    tmp.setIcon(road_icon);
			    break;
			case 1: 
			    tmp.setIcon(wall_icon);
			    break;
			case 2: 
			    tmp.setIcon(success_icon);
			    break;
			case 3:
				tmp.setIcon(tree_icon);
				map[i][j]=1;
				break;
			case 4:
				tmp.setIcon(sea_icon);
				map[i][j]=1;
				break;
			case 5:
				tmp.setIcon(road_icon);
				boxLabel.setBounds(0+j*40, 0+i*40,40,40);
				break;
			case 6:
				change.setBounds(0+j*40, 0+i*40,40,40);
				change.setVisible(true);
				break;
			case 7:
				tmp.setIcon(road_icon);
				wx=j;
				wy=i;
				warrior.setBounds(0+j*40, 0+i*40,40,40);
				warrior.setVisible(true);
				break;
			default:
			    tmp.setIcon(road_icon);
			}
			tmp.setBounds(0+j*40, 0+i*40,40, 40);
		  } 
		getContentPane().add(wallLabel);
		getContentPane().add(roadLabel);
		getContentPane().add(UpButton);
		getContentPane().add(RightButton);
		getContentPane().add(LeftButton);
		getContentPane().add(DownButton);
		getContentPane().add(pass);
		pass.setVisible(false);
		getContentPane().setFocusable(true);
		getContentPane().requestFocus();
	}
	
	protected boolean judge() {
		boolean result = false;
		//0:road ; 1:wall ; 2:goal
		if(map[current_y][current_x]==6)
		{
			map[wy][wx]=0;
			warrior.setVisible(false);
			result=true;
		}
		else if (map[current_y][current_x]==0)		//若目前位置的map值為0=road
		 {
			result = true;
		}		//回傳可以移動
		else if(map[current_y][current_x]==2)
		{
			actor.setLocation(current_x*40,current_y*40);
			pass.setVisible(true);
		}
		else if(map[current_y][current_x]==5)
		{
			if(current_y==0||current_y==4||current_x==0||current_x==9)
				return result;
				
			int x=boxLabel.getX()-actor.getX();
			if(x>0)
				x=1;
			else if(x<0)
				x=-1;
			else
				x=0;
			int y=boxLabel.getY()-actor.getY();
			if(y>0)
				y=1;
			else if(y<0)
				y=-1;
			else
				y=0;
			
			switch(x)
			{
				case 1:
					if(map[current_y][current_x+1]==0||map[current_y][current_x+1]==2)
					{
						if(map[current_y][current_x+1]==2)
							pass.setVisible(true);
						map[current_y][current_x]=0;
						map[current_y][current_x+1]=5;
						boxLabel.setLocation(boxLabel.getX()+40,boxLabel.getY());
						result=true;
					}
					break;
				case -1:
					if(map[current_y][current_x-1]==0||map[current_y][current_x-1]==2)
					{
						if(map[current_y][current_x-1]==2)
							pass.setVisible(true);
						map[current_y][current_x]=0;
						map[current_y][current_x-1]=5;
						boxLabel.setLocation(boxLabel.getX()-40,boxLabel.getY());
						result=true;
					}
					break;
				default:
					break;
			}
			
			switch(y)
			{
				case 1:
					if(map[current_y+1][current_x]==0||map[current_y+1][current_x]==2)
					{
						if(map[current_y+1][current_x]==2)
							pass.setVisible(true);
						map[current_y][current_x]=0;
						map[current_y+1][current_x]=5;
						boxLabel.setLocation(boxLabel.getX(),boxLabel.getY()+40);
						result=true;
					}
					break;
				case -1:
					if(map[current_y-1][current_x]==0||map[current_y-1][current_x]==2)
					{
						if(map[current_y-1][current_x+1]==2)
							pass.setVisible(true);
						map[current_y][current_x]=0;
						map[current_y-1][current_x]=5;
						boxLabel.setLocation(boxLabel.getX(),boxLabel.getY()-40);
						result=true;
					}
					break;
				default:
					break;
			}
		}
       return result;
	}
	protected void thisContentPane_keyPressed(KeyEvent e) {
		int keyCode=e.getKeyCode();
		if(keyCode==38||keyCode==87)
		{
			if(current_y>0)
			{					//判斷current_y是否超過地圖範圍
				current_y--;					//取得移動角色上方的值
				if (judge())
				{					//利用judge()來判斷地圖是否能行走
				   int x= actor.getX();			//取得移動角色目前的X座標
				   int y= actor.getY();			//取得移動角色目前的Y座標
				   actor.setLocation(x, y-40);	//移動角色的y座標向上移動40
				}
				else current_y++;				//若judge()判斷地圖不能行走，還原current_y
			}
		}
		else if(keyCode==40||keyCode==83)
		{
			if(current_y<4)
			{
				current_y++;
				if (judge())
				{
				   int x= actor.getX();
				   int y= actor.getY();
				   actor.setLocation(x, y+40);
				}
				else 	current_y--;
			}
		}
		else if(keyCode==65||keyCode==37)
		{
			if(current_x>0)
			{
				current_x--;
				if(judge())
				{
					actor.setIcon(ActorLeft_icon);
				   int x= actor.getX();
				   int y= actor.getY();
				   actor.setLocation(x-40, y);
				}
				else
					current_x++;
			}
		}
		else if(keyCode==68||keyCode==39)
		{
			if(current_x<9)
			{
				current_x++;
				if(judge())
				{
					actor.setIcon(ActorRight_icon);
				   int x= actor.getX();
				   int y= actor.getY();
				   actor.setLocation(x+40, y);
				}
				else
					current_x--;
			}
		}
	}
}
