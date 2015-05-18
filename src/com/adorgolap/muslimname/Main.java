package com.adorgolap.muslimname;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;

public class Main extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static boolean ex = false;
	private JPanel jp = new JPanel(new GridLayout(0, 2, 5, 5));
	public static JButton jbStart = new JButton("Start");
	public static JButton jbStop = new JButton("Stop");
	public static JLabel jlCommand = new JLabel(
			"Press the button to start grabbing name");
	public static JProgressBar jpBar = new JProgressBar(0, 160);
	public static JLabel jlStatus = new JLabel("0 out of 160 page is fetched.");
	private static Main window;
	public static JLabel jlError = new JLabel("");
	public static JLabel jlError2 = new JLabel("");

	public Main() {
		jpBar.setStringPainted(true);
		jbStart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (ex) {
					window.dispose();
				} else {
					try {
						new NameSaver();
						jbStart.setEnabled(false);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});
		jp.setBorder(new EmptyBorder(10, 10, 10, 10));
		jp.add(jlCommand);
		jp.add(jbStart);
		jp.add(jlStatus);
		jp.add(jpBar);
		jp.add(jlError);
		jp.add(jlError2);
		this.add(jp);
	}

	public static void main(String[] args) {
		window = new Main();
		window.setTitle("Muslim Name Collector");
		window.setDefaultCloseOperation(EXIT_ON_CLOSE);

		// window.setSize(300,200);
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);
	}
}
