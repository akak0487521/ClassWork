import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.Rectangle;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import javax.swing.border.EtchedBorder;

public class MyFrame1 extends JFrame {

	private JPanel jContentPane = null;
	private String textA = "TEXT";

	private JButton jButton = null;
	private JButton jButton1 = null;
	private JButton jButton2 = null;
	private JButton jButton3 = null;
	private JButton jButton4 = null;
	private JButton jButton5 = null;
	private JButton jButton6 = null;
	private JLabel jLabel = null;

	/**
	 * This is the default constructor
	 */
	public MyFrame1() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(500, 500);
		this.setContentPane(getJContentPane());
		this.setTitle("JFrame");
	}


	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(50, 182, 123, 45));
                        jLabel.setOpaque(true);
			
			jLabel.setText(textA);
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJButton(), null);
			jContentPane.add(getJButton1(), null);
			jContentPane.add(getJButton2(), null);
			jContentPane.add(getJButton3(), null);
			jContentPane.add(getJButton4(), null);
			jContentPane.add(getJButton5(), null);
			jContentPane.add(jLabel, null);
		}
		return jContentPane;
	}



	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setBounds(new Rectangle(45, 61, 100, 50));
			jButton.setText("SetFrameSize");
			jButton.addActionListener(
                                new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					setSize(300, 300);
					jContentPane.setBackground(Color.LIGHT_GRAY);
					
				}
			       }
                                                 );
		}
		return jButton;
	}

	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setBounds(new Rectangle(45, 120, 100, 50));
			jButton1.setText("SetContentPaneSize");
			jButton1.addActionListener(
                                new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//setSize(300, 300);
					//setBackground(Color.LIGHT_GRAY);
					jContentPane.setBounds(10,10, 280, 250);
					jContentPane.setBackground(Color.GREEN);  //can run
					
				}
			       }
                                                 );
		}
		return jButton1;
	}
	private JButton getJButton2() {
		if (jButton2 == null) {
			jButton2 = new JButton();
			jButton2.setBounds(new Rectangle(45, 2, 100, 50));
			jButton2.setText("SetContentPaneSize");
			jButton2.addActionListener(
                                new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//setSize(300, 300);
					//setBackground(Color.LIGHT_GRAY);
					setSize(500, 300);
					jContentPane.setBackground(Color.BLUE);  //can run
					
				}
			       }
                                                 );
		}
		return jButton2;
	}

	private JButton getJButton3() {
		if (jButton3 == null) {
			jButton3 = new JButton();
			jButton3.setBounds(new Rectangle(150, 2, 100, 50));
			jButton3.setText("ChangeText1");
			jButton3.addActionListener(
                                new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//setSize(300, 300);
					//setBackground(Color.LIGHT_GRAY);
				textA = "ABC";  //can run
				jLabel.setText(textA);	
				}
			       }
                                                 );
		}
		return jButton3;
	}
	
		private JButton getJButton4() {
		if (jButton4 == null) {
			jButton4 = new JButton();
			jButton4.setBounds(new Rectangle(150, 61, 100, 50));
			jButton4.setText("ChangeText2");
			jButton4.addActionListener(
                                new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//setSize(300, 300);
					//setBackground(Color.LIGHT_GRAY);
				textA = "SXXT";  //can run
				jLabel.setText(textA);	
				}
			       }
                                                 );
		}
		return jButton4;
	}

	private JButton getJButton5() {
		if (jButton5 == null) {
			jButton5 = new JButton();
			jButton5.setBounds(new Rectangle(150, 120, 100, 50));
			jButton5.setText("ChangeText3");
			jButton5.addActionListener(
                                new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//setSize(300, 300);
					//setBackground(Color.LIGHT_GRAY);
				textA = "Hello world!";  //can run
				jLabel.setText(textA);	
				}
			       }
                                                 );
		}
		return jButton5;
	}
	
		private JButton getJButton() {
		if (jButton6 == null) {
			jButton6 = new JButton();
			jButton6.setBounds(new Rectangle(150, 120, 100, 50));
			jButton6.setText("ChangeText3");
			jButton6.addActionListener(
                                new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//setSize(300, 300);
					//setBackground(Color.LIGHT_GRAY);
				textA = "Hello world!";  //can run
				jLabel.setText(textA);	
				}
			       }
                                                 );
		}
		return jButton5;
	}



	/**
	 * @param args
	 */
	public static void main(String[] args) {
				MyFrame1 thisClass = new MyFrame1();
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);
	}



}  
