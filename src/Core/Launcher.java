package Core;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import MP.MPClient;
import MP.MPServer;

public class Launcher {
	// JFrame related items
	private final String TITLE = "Multiplayer Proof Of Concept";
	private final int WIDTH = 360;
	private final int HEIGHT = 240;
	private final Dimension gameDim = new Dimension(WIDTH, HEIGHT);
	private JFrame frame;

	// Other Items (Buttons, etc)
	private String[] buttonNames = { "Play", "Host Server", "Join Server", "Quit" };
	private JButton[] buttons = new JButton[buttonNames.length];
	private final int buttonWidth = 120, buttonHeight = 30;

	public Launcher() {
		init();
		addButtons();
		addButtonActions();
	}

	public void init() {
		frame = new JFrame(TITLE);
		frame.setSize(gameDim);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
	}

	public void addButtons() {
		for (int i = 0; i < buttonNames.length; i++) {
			buttons[i] = new JButton(buttonNames[i]);
			buttons[i].setBounds((WIDTH / 2) - (buttonWidth / 2), i * 40 + buttonHeight, buttonWidth, buttonHeight);
			frame.add(buttons[i]);
		}
	}

	public void addButtonActions() {
		buttons[0].addActionListener(new ActionListener() { // Play Button
					public void actionPerformed(ActionEvent e) {
						new Game().start();
						frame.dispose();
					}
				});

		buttons[1].addActionListener(new ActionListener() { // Host Server
					public void actionPerformed(ActionEvent e) {
						int serverPort = Integer.parseInt(JOptionPane.showInputDialog(null, "What is the port that you wish to host on? (Remember to Portforward if required)"));

						new MPServer(serverPort);
					}
				});

		buttons[2].addActionListener(new ActionListener() { // Join Server
					public void actionPerformed(ActionEvent e) {
						String serverIP = JOptionPane.showInputDialog(null, "What is the IP of the server host?");
						int serverPort = Integer.parseInt(JOptionPane.showInputDialog(null, "What is the port of the server?"));

						new MPClient(serverIP, serverPort);
					}
				});

		buttons[3].addActionListener(new ActionListener() { // Quit
					public void actionPerformed(ActionEvent e) {
						System.exit(0);
					}
				});
	}

	public static void main(String[] args) {
		new Launcher();
	}
}