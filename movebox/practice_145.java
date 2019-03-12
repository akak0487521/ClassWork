//------------------------------------------------------
//�d�ҵ{��5�G�Q����L�ӱ���Ⲿ��
//���{���HŪ�ɤ覡���J�a�ϡA�a���ɦW�١Fmap1.txt
//�w�]�a�Ϥj�p��10*5�A�ϥΪ̥i�H�ۦ�w�q
//�}�C�Ȼ��� : 0=��� ; 1=�D�� ; 2:���I
//�{�����w��@�W�U���s���{���X
//�{�����ϥ�Judge()�ӧP�_�O�_�I���A�w��@�P�_�O�_�I�����
//�{�����w��@��L�W�U���ʪ��{���X
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

public class practice_145 extends JFrame {

	private final JLabel actor = new JLabel();
	private final JLabel wallLabel = new JLabel();
	private final JLabel roadLabel = new JLabel();
	private final JButton UpButton = new JButton();
	private final JButton RightButton = new JButton();
	private final JButton LeftButton = new JButton();
	private final JButton DownButton = new JButton();
	private final JButton pass = new JButton();
	private final JButton prev = new JButton();
	private final JButton next = new JButton();
	private final JLabel successLabel = new JLabel();
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

	private int[][] map;
	int current_x=0, current_y=0;
	private final JLabel messageLabel = new JLabel();
	private final JLabel boxLabel = new JLabel();
	/**
	 * Launch the application
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			practice_145 frame = new practice_145();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame
	 */
	public practice_145() {
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
		actor.setIcon(ActorRight_icon);
		actor.setBounds(0, 0, 40, 40);
		boxLabel.setIcon(Box_icon);
		getContentPane().getFocusListeners();
		
		map=readMap("map1.txt");//�ŧi�@�ӤG���}�Cmap�Ӧs��readMap�^�Ǫ��G���}�C��
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

		getContentPane().add(prev);
		prev.addActionListener(new PrevActionListener());
		prev.setText("prev");
		prev.setBounds(300, 260, 68, 20);
		prev.setVisible(true);

		getContentPane().add(next);
		next.addActionListener(new NextActionListener());
		next.setText("next");
		next.setBounds(300, 232, 68, 20);
		next.setVisible(true);
		
		getContentPane().setFocusable(true);
		getContentPane().requestFocus();
	}
	private int[][] readMap(String mapfile) throws FileNotFoundException {
		
		File f = new File(mapfile);			//�ŧi�@��File��Ū���a����
		int[][] map=new int[5][10];			//�ŧi�@�ӤG���}�C�s���q�a����Ū��Ӫ���
		Scanner sc = new Scanner(f);		//�Q��Scanner��Ū���a���ɪ���
			for(int i=0;i<5;i++){			//�ŧi�@�ӱ_���^���Ū���a����
				for(int j=0;j<10;j++){
					map[i][j]=sc.nextInt();	//�N�q�a����Ū���쪺�ȩ�J�}�C
				}
			}
		return map; 						//�^��Ū���쪺�G���}�C��
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
	
	private class PrevActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			PrevButton_actionPerformed(e);
		}
	}
	
	private class NextActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			NextButton_actionPerformed(e);
		}
	}
	
	private class ThisContentPaneKeyListener extends KeyAdapter {
			
		public void keyPressed(KeyEvent e) {
			thisContentPane_keyPressed(e);
		}
	}

	protected void upButton_actionPerformed(ActionEvent e) {
		if(current_y>0){					//�P�_current_y�O�_�W�L�a�Ͻd��
			current_y--;					//���o���ʨ���W�誺��
			if (judge()){					//�Q��judge()�ӧP�_�a�ϬO�_��樫
			   int x= actor.getX();			//���o���ʨ���ثe��X�y��
			   int y= actor.getY();			//���o���ʨ���ثe��Y�y��
			   actor.setLocation(x, y-40);	//���ʨ��⪺y�y�ЦV�W����40
			}
			else current_y++;				//�Yjudge()�P�_�a�Ϥ���樫�A�٭�current_y
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
		actor.setIcon(ActorRight_icon);
		actor.setBounds(0, 0, 40, 40);
		boxLabel.setIcon(Box_icon);
		try {
		++mapNumber;
		map=readMap("map"+Integer.toString(mapNumber)+".txt");
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
	
		getContentPane().add(prev);
		prev.addActionListener(new PrevActionListener());
		prev.setText("prev");
		prev.setBounds(300, 260, 68, 20);
		prev.setVisible(true);


		getContentPane().add(next);
		next.addActionListener(new NextActionListener());
		next.setText("next");
		next.setBounds(300, 232, 68, 20);
		next.setVisible(true);

		getContentPane().setFocusable(true);
		getContentPane().requestFocus();
	}

	public void PrevButton_actionPerformed(ActionEvent e)
	{
		getContentPane().removeAll();
		getContentPane().repaint();
		current_x=0;
		current_y=0;
		getContentPane().add(actor);
		getContentPane().add(boxLabel);
		actor.setIcon(ActorRight_icon);
		actor.setBounds(0, 0, 40, 40);
		boxLabel.setIcon(Box_icon);
		try {
		mapNumber = mapNumber - 1;
		map=readMap("map"+Integer.toString(mapNumber)+".txt");
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

		getContentPane().add(prev);
		prev.addActionListener(new PrevActionListener());
		prev.setText("prev");
		prev.setBounds(300, 260, 68, 20);
		prev.setVisible(true);


		getContentPane().add(next);
		next.addActionListener(new NextActionListener());
		next.setText("next");
		next.setBounds(300, 232, 68, 20);
		next.setVisible(true);

		getContentPane().setFocusable(true);
		getContentPane().requestFocus();
	}

	public void NextButton_actionPerformed(ActionEvent e)
	{
		getContentPane().removeAll();
		getContentPane().repaint();
		current_x=0;
		current_y=0;
		getContentPane().add(actor);
		getContentPane().add(boxLabel);
		actor.setIcon(ActorRight_icon);
		actor.setBounds(0, 0, 40, 40);
		boxLabel.setIcon(Box_icon);
		try {
		mapNumber = mapNumber + 1;
		map=readMap("map"+Integer.toString(mapNumber)+".txt");
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

		getContentPane().add(prev);
		prev.addActionListener(new PrevActionListener());
		prev.setText("prev");
		prev.setBounds(300, 260, 68, 20);
		prev.setVisible(true);

		getContentPane().add(next);
		next.addActionListener(new NextActionListener());
		next.setText("next");
		next.setBounds(300, 232, 68, 20);
		next.setVisible(true);

		getContentPane().setFocusable(true);
		getContentPane().requestFocus();
	}

	
	protected boolean judge() {
		boolean result = false;
		//0:road ; 1:wall ; 2:goal
		if (map[current_y][current_x]==0)		//�Y�ثe��m��map�Ȭ�0=road
		 {
			result = true;
		}		//�^�ǥi�H����
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
			{					//�P�_current_y�O�_�W�L�a�Ͻd��
				current_y--;					//���o���ʨ���W�誺��
				if (judge())
				{					//�Q��judge()�ӧP�_�a�ϬO�_��樫
				   int x= actor.getX();			//���o���ʨ���ثe��X�y��
				   int y= actor.getY();			//���o���ʨ���ثe��Y�y��
				   actor.setLocation(x, y-40);	//���ʨ��⪺y�y�ЦV�W����40
				}
				else current_y++;				//�Yjudge()�P�_�a�Ϥ���樫�A�٭�current_y
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
