import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.util.Hashtable;
import javax.swing.*;

public class SliderDemo3 implements ChangeListener, ActionListener
{
    JFrame f = null;
    JSlider slider1;
    JSlider slider2;
    JSlider slider3;
    JLabel label1;
    JLabel label2;
    JLabel label3;
	JButton b1 = new JButton("弱");
	JButton b2 = new JButton("有點弱");
	JButton b3 = new JButton("中");
	JButton b4 = new JButton("有點強");
	JButton b5 = new JButton("強");
	b1.addActionListener(new ActionListener()
	{
	public void actionPerformed(ActionEvent e)
	{
		slider2.setValue(0);
	}
	});
	b2.addActionListener(new ActionListener()
	{
	public void actionPerformed(ActionEvent e)
	{
		slider2.setValue(25);
	}
	});
	b3.addActionListener(new ActionListener()
	{
	public void actionPerformed(ActionEvent e)
	{
		slider2.setValue(50);
	}
	});
	b4.addActionListener(new ActionListener()
	{
	public void actionPerformed(ActionEvent e)
	{
		slider2.setValue(75);
	}
	});
	b5.addActionListener(new ActionListener()
	{
	public void actionPerformed(ActionEvent e)
	{
		slider2.setValue(100);
	}
	});
	
	
    public SliderDemo3()
    {
        f = new JFrame("JSlider3 Example");
        Container contentPane = f.getContentPane();

        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayout(2,1));
        slider1 = new JSlider();
        slider1.setPaintTicks(true);//加上刻度線
        slider1.setMajorTickSpacing(20);//大刻度的間距
        slider1.setMinorTickSpacing(10);//小刻度的間距
        slider1.setPaintLabels(true);//加上數字標示
        slider1.setPaintTrack(true);//出現中間的橫桿
        slider1.setSnapToTicks(true);//一次移動一個小刻度
		//slider1.setMouseListener(this);
        label1 = new JLabel("目前刻度："+slider1.getValue());
        panel1.add(label1);
        panel1.add(slider1);
        panel1.setBorder(BorderFactory.createTitledBorder(
        BorderFactory.createEtchedBorder(),"Slider 1",TitledBorder.LEFT,
        TitledBorder.TOP));


        Hashtable table = new Hashtable();
        table.put(new Integer( 0 ),new JLabel(new ImageIcon("1.jpg")));
        table.put(new Integer( 50 ),new JLabel(new ImageIcon("2.jpg")));
        table.put(new Integer( 100 ),new JLabel(new ImageIcon("3.jpg")));
        slider1.setLabelTable(table);
       // slider1.setSize(600,100);

        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(2,1));
        slider2 = new JSlider(JSlider.HORIZONTAL);
        slider2.setMinimum(0);
        slider2.setMaximum(100);
        slider2.setValue(30);
        slider2.setExtent(10);//knob 的大小，會使最大刻度減少
        slider2.setPaintTicks(true);
        slider2.setMajorTickSpacing(10);
        slider2.setMinorTickSpacing(2);
        slider2.setPaintLabels(true);
		slider2.setSnapToTicks(true);
        slider2.putClientProperty("JSlider.isFilled",Boolean.TRUE);
        label2 = new JLabel("目前刻度："+slider2.getValue());
        panel2.add(label2);
        panel2.add(slider2);
        panel2.setBorder(BorderFactory.createTitledBorder(
        BorderFactory.createEtchedBorder(),"Slider 2",TitledBorder.LEFT,
        TitledBorder.TOP));

        table = new Hashtable();
        table.put(new Integer( 0 ),b1);
        table.put(new Integer( 25 ),b2);
        table.put(new Integer( 50 ),b3);
        table.put(new Integer( 75 ),b4);
        table.put(new Integer( 100 ),b5);
        slider2.setLabelTable(table);

        JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayout(2,1));
        slider3 = new JSlider(20,80);
        slider3.setOrientation(JSlider.HORIZONTAL);
        slider3.setPaintTicks(true);
        slider3.setMajorTickSpacing(30);
        slider3.setMinorTickSpacing(10);
        slider3.setPaintLabels(true);
		slider3.setExtent(30);
        slider3.putClientProperty("JSlider.isFilled",Boolean.TRUE);
        label3 = new JLabel("目前刻度："+slider3.getValue());
        panel3.add(label3);
        panel3.add(slider3);
        panel3.setBorder(BorderFactory.createTitledBorder(
        BorderFactory.createEtchedBorder(),"Slider 3",TitledBorder.LEFT,
        TitledBorder.TOP));

        slider1.addChangeListener(this);
        slider2.addChangeListener(this);
        slider3.addChangeListener(this);
		b1.addChangeListener(this) ;
		b2.addChangeListener(this) ;
		b3.addChangeListener(this) ;
		b4.addChangeListener(this) ;
		b5.addChangeListener(this) ;

        panel1.setPreferredSize(new Dimension(300,130));
        panel2.setPreferredSize(new Dimension(300,130));
        panel3.setPreferredSize(new Dimension(150,260));

        contentPane.setLayout(new GridLayout(3,1));
        contentPane.add(panel1);
        contentPane.add(panel2);
        contentPane.add(panel3);

        f.pack();
        f.setVisible(true);

        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    public static void main(String[] args)
    {
        new SliderDemo3();
    }
	
	

    public void stateChanged(ChangeEvent e)
    {
        if ((JSlider)e.getSource() == slider1)
            label1.setText("目前刻度："+slider1.getValue());
        if ((JSlider)e.getSource() == slider2)
            label2.setText("目前刻度："+slider2.getValue());
        if ((JSlider)e.getSource() == slider3)
            label3.setText("目前刻度："+slider3.getValue());
    }
	
}
