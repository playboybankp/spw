package f2.spw;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JDesktopPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class PopUp extends JFrame {
	JDesktopPane jdpDesktop;
	static int openFrameCount = 0;
	public PopUp() {
		super("Space Ship War");
		// Make the main window positioned as 50 pixels from each edge of the
		// screen.
		int inset = 60;
		setBounds(inset, inset, 250, 250);
		// Add a Window Exit Listener
		addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		// Create and Set up the GUI.
		jdpDesktop = new JDesktopPane();
		setContentPane(jdpDesktop);
		setJMenuBar(createMenuBar());
		// Make dragging faster by setting drag mode to Outline
		jdpDesktop.putClientProperty("JDesktopPane.dragMode", "outline");
	}
	protected JMenuBar createMenuBar() {
		System.out.println("Get In JMenu");
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Frame");
		menuBar.add(menu);
		return menuBar;
	}
}
