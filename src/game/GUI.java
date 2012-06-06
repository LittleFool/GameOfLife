package game;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI extends JFrame {
	private static final long serialVersionUID = 3839540121964989350L;
	private JPanel jPanel = new JPanel(null, true);
	private JLabel infos = new JLabel();
	private JRadioButton jRadioButton1 = new JRadioButton();
	private JRadioButton jRadioButton11 = new JRadioButton();
	private JButton jButton1 = new JButton();

	public GUI(String title) {
		super(title);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		int frameWidth = 350;
		int frameHeight = 380;
		setSize(frameWidth, frameHeight);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (d.width - getSize().width) / 2;
		int y = (d.height - getSize().height) / 2;
		setLocation(x, y);
		setResizable(false);
		Container cp = getContentPane();
		cp.setLayout(null);

		jPanel.setBounds(51, 51, 250, 250);
		jPanel.setBackground(new Color(0xC0C0C0));
		cp.add(jPanel);
		infos.setBounds(55, 15, 227, 25);
		infos.setText("Dies ist ein Beispieltext");
		infos.setFont(new Font("Arial Narrow", Font.BOLD, 20));
		cp.add(infos);
		jRadioButton1.setBounds(160, 317, 70, 17);
		jRadioButton1.setText("Test1");
		cp.add(jRadioButton1);
		jRadioButton11.setBounds(240, 317, 70, 17);
		jRadioButton11.setText("Test2");
		cp.add(jRadioButton11);
		jButton1.setBounds(55, 317, 97, 17);
		jButton1.setText("jButton1");
		jButton1.setMargin(new Insets(2, 2, 2, 2));
		jButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jButton1_ActionPerformed(evt);
			}
		});
		cp.add(jButton1);

		setVisible(true);
	}
	
	public void jButton1_ActionPerformed(ActionEvent evt) {
		// TODO hier Quelltext einfügen
	}
}
