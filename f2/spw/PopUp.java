package f2.spw;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

public class PopUp extends JFrame {
	private JPanel panel;
	private JButton button,button2;
	private JLabel text,text2;
	private boolean close = false;
	public PopUp() {
		super("Space Ship War");
		// Make the main window positioned as 50 pixels from each edge of the
		// screen.
		int inset = 60;
		setBounds(inset, inset, 250, 250);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Add a Window Exit Listener
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		// Create and Set up the GUI.
		panel = new JPanel();
		button = new JButton("Replay");
		button2 = new JButton("Close");
		text2 = new JLabel("Play again??");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("New Main");
				new Main();
				dispose();
			}
		});
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				close = true;
			}
		});
		panel.setLayout(new BorderLayout());
		panel.add(text2,BorderLayout.CENTER);
		panel.add(button,BorderLayout.WEST);
		panel.add(button2,BorderLayout.EAST);
		setContentPane(panel);
	}
	public void setScore(long score){
		text = new JLabel("Your Score is " + score + "!!!");
		panel.add(text,BorderLayout.NORTH);
	}
	public boolean getClose(){
		return close;
	}
}
