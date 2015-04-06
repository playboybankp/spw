package f2.spw;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.Timer;


public class GameEngine implements KeyListener, GameReporter{
	GamePanel gp;
		
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	private ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	private SpaceShip v;	
	private Bullet b;
	
	private Timer timer;
	
	private long score = 0;
	private double difficulty = 0.1;
	
	public GameEngine(GamePanel gp, SpaceShip v) {
		this.gp = gp;
		this.v = v;		
		
		gp.sprites.add(v);
		
		timer = new Timer(50, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				process();
			}
		});
		timer.setRepeats(true);
		
	}
	
	public void start(){
		timer.start();
	}
	
	private void generateEnemy(){
		Enemy e = new Enemy((int)(Math.random()*390), 30);
		gp.sprites.add(e);
		enemies.add(e);
	}
	
	private void generateBullet(){
		b = new Bullet(v.getX()+(v.getWidth()/2),v.getY());
		gp.sprites.add(b);
		bullets.add(b);
		
	}
	
	private void process(){
		if(Math.random() < difficulty){
			generateEnemy();
		}
		
		Iterator<Enemy> e_iter = enemies.iterator();
		Iterator<Bullet> b_iter = bullets.iterator();
		while(e_iter.hasNext()){
			Enemy e = e_iter.next();
			e.proceed();
			
			if(!e.isAlive()){
				e_iter.remove();
				gp.sprites.remove(e);
				score += 100;
			}
			
			while(b_iter.hasNext()){
				b = b_iter.next();
				b.proceed();
				
				if(!b.isAlive()){
					b_iter.remove();
					gp.sprites.remove(b);
					score += 200;
				}
			}
		
		}
		
		gp.updateGameUI(this);
		
		Rectangle2D.Double vr = v.getRectangle();
		Rectangle2D.Double br;
		Rectangle2D.Double er;
		for(Enemy e : enemies){
			
			er = e.getRectangle();
			if(er.intersects(vr)){
				e.crash();
				v.crash();
				if(v.getHp() <= 0){
					gp.updateGameUI(this);
					die();
					return;
				}
			}
			for(Bullet b : bullets){
				br = b.getRectangle();
				if(er.intersects(br)){
					e.crash();
					b.crash();
				}
			}
		}
	}
	
	public void die(){
		gp.GameOver();
		timer.stop();
	}
	
	void controlVehicle(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			v.move(-2);
			break;
		case KeyEvent.VK_RIGHT:
			v.move(2);
			break;
		case KeyEvent.VK_UP:
			v.moveForward(-2);
			break;
		case KeyEvent.VK_DOWN:
			v.moveForward(+2);
			break;
		case KeyEvent.VK_D:
			difficulty += 0.1;
			break;
		case KeyEvent.VK_Z:
			generateBullet();
			break;
		}
	}

	public long getScore(){
		return score;
	}
	public int getHp(){
		return v.getHp();
	}
	@Override
	public void keyPressed(KeyEvent e) {
		controlVehicle(e);
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//do nothing
	}

	@Override
	public void keyTyped(KeyEvent e) {
		//do nothing		
	}
}
