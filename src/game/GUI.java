package game;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.swing.*;

import playingField.ReadFile;

public class GUI extends JFrame {
	private static final long serialVersionUID = 3839540121964989350L;
	private JPanel jPanel = new JPanel(null, true);
	private JLabel infos = new JLabel();
	private JRadioButton jRadioButton1 = new JRadioButton();
	private JRadioButton jRadioButton11 = new JRadioButton();
	private JButton set = new JButton();

	public GUI(String title) {
		super(title);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		int frameWidth = 530;
		int frameHeight = 650;
		setSize(frameWidth, frameHeight);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (d.width - getSize().width) / 2;
		int y = (d.height - getSize().height) / 2;
		setLocation(x, y);
		setResizable(false);
		Container cp = getContentPane();
		cp.setLayout(null);

		jPanel.setBounds(10, 45, 500, 500);
		jPanel.setBackground(new Color(0xC0C0C0));
		cp.add(jPanel);
		infos.setBounds(10, 10, 530, 25);
		infos.setText("Dies ist ein Beispieltext");
		infos.setFont(new Font("Arial Narrow", Font.BOLD, 20));
		cp.add(infos);
		jRadioButton1.setBounds(120, 555, 70, 15);
		jRadioButton1.setText("Infinite");
		cp.add(jRadioButton1);
		jRadioButton11.setBounds(200, 555, 100, 15);
		jRadioButton11.setText("Finite:");
		cp.add(jRadioButton11);
		set.setBounds(10, 555, 100, 15);
		set.setText("Set");
		set.setMargin(new Insets(2, 2, 2, 2));
		set.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				set_actionPerformed(evt);
			}
		});
		cp.add(set);

		setVisible(true);
	}
	
	public void set_actionPerformed(ActionEvent evt) {
		Scanner in = new Scanner(System.in);
		char[][] gameField = new char[50][50];
		int n = 0;
		ReadFile rf = new ReadFile("src/playingField/beacon.txt");
		
		try {
			gameField = rf.read();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		System.out.print("How pany steps to prozess? ");
		n=in.nextInt();
		in.close();
		
		NextStep next = new NextStep(gameField);
		ExecutorService executor = Executors.newCachedThreadPool();
		Future<char[][]> result;
		for(int i=0; i <= n; i++) {
			result = executor.submit(next);
			Helper.output(gameField);
			
			try {
				gameField = result.get();
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
			next.reset();
		}
		executor.shutdown();
	}
}
