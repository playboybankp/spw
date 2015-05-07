package f2.spw;

import java.awt.BorderLayout;

import javax.swing.JFrame;
public class Main extends JFrame {

	public Main(){
		super("Space War");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 640); // change from 400,650
		getContentPane().setLayout(new BorderLayout());
		
		PopUp pop = new PopUp();
		SpaceShip v = new SpaceShip(180, 550, 3 , 20);
		GamePanel gp = new GamePanel();
		GameEngine engine = new GameEngine(gp,v,pop);
		addKeyListener(engine);
		getContentPane().add(gp, BorderLayout.CENTER);
		setVisible(true);
		
		engine.start();
		
	}
	public static void main(String[] args){
		new Main();
	}
}
