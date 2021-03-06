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
	PopUp pop;
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	private ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	private ArrayList<Item> items = new ArrayList<Item>();
	private SpaceShip v;	
	
	private Timer timer;
	private Boolean play = true;
	
	private long score = 0;
	private double difficulty = 0.1;
	private double dropRate = 0.1 * difficulty;
	private double originalDiff = 0.1;
	private double maxDiff = 2;
	private int selectBullet = 0;

	int i,j;
	
	public GameEngine(GamePanel gp, SpaceShip v ,PopUp pop) {
		this.gp = gp;
		this.v = v;		
		this.pop = pop;
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
	private void generateItem(int type){
		Item i = null;
		switch(type){
		case 1 : i = new ItemHeart((int)(Math.random()*390), 30);
				break;
		case 2 : i = new ItemGiantShip((int)(Math.random()*390), 30);
				break;
		default : i = new ItemAmmo((int)(Math.random()*390), 30);
				break;
		}
		if(i != null){
			gp.sprites.add(i);
			items.add(i);
		}
		
	}
	private void generateBullet(int type){
		Bullet b = null;
		switch(type){
			case 0 : b = new SpaceShipBullet(v.centerX()-7,v.centerY());
					break;
			case 1 : b = new MissileBullet(v.centerX()-7,v.centerY());
					break;
		}
		if(b != null){
			gp.sprites.add(b);
			bullets.add(b);
		}
		
	}
	private void process(){
		gp.bulletInfo(selectBullet);
		if(v.getInvisible())
			difficulty = maxDiff;
		else
			difficulty = originalDiff;
		
		if(Math.random() < difficulty){
			generateEnemy();
		}
		if(Math.random() < dropRate){
			if(!v.getInvisible())
				generateItem((int)(Math.random()*5));
		}
		
		Iterator<Enemy> e_iter = enemies.iterator();
		Iterator<Bullet> b_iter = bullets.iterator();
		Iterator<Item> i_iter = items.iterator();
		while(e_iter.hasNext()){
			Enemy e = e_iter.next();
			e.proceed(v.getInvisible());
			
			if(!e.isAlive()){
				e_iter.remove();
				gp.sprites.remove(e);
			}
		}
		while(b_iter.hasNext()){
			Bullet b = b_iter.next();
			b.shoot(v.getInvisible());
				
			if(!b.isAlive()){
				b_iter.remove();
				gp.sprites.remove(b);
			}
		}
		while(i_iter.hasNext()){
			Item i = i_iter.next();
			i.drop(v.getInvisible());
				
			if(!i.isAlive()){
				i_iter.remove();
				gp.sprites.remove(i);
			}
		}
		
		gp.updateGameUI(this);
		
		Rectangle2D.Double vr = v.getRectangle();
		Rectangle2D.Double br;
		Rectangle2D.Double er;
		Rectangle2D.Double ir;
		for(Enemy e : enemies){
			
			er = e.getRectangle();
			if(er.intersects(vr)){
				e.crash();
				v.crash();
				score += 100;
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
					score += 200;
				}
			}
			for(Item i : items){
				ir = i.getRectangle();
				if(vr.intersects(ir)){
					if(i instanceof ItemHeart){
						ItemHeart H = (ItemHeart)i;	
						H.getHeart(v);
					}
					if(i instanceof ItemGiantShip){
						ItemGiantShip G = (ItemGiantShip)i;
						G.getGiantShip(v);
					}
					if(i instanceof ItemAmmo){
						ItemAmmo A = (ItemAmmo)i;
						A.fill_ammo(v);
					}
				}
			}
		}
	}
	public void die(){
		play = false;
		System.out.println("Score =  "+ score );
		pop.setScore(getScore());
		pop.setVisible(true);
		timer.stop();
	}
	public boolean getPlay(){
		return pop.getClose();
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
			if(difficulty < 2){
				difficulty += 0.1;
				originalDiff += 0.1;
			}
			break;
		case KeyEvent.VK_Z:
			if(!v.getInvisible() && (v.getAmmo(selectBullet) > 0)){
//				System.out.println(v.getAmmo(selectBullet));
				v.shootBullet(selectBullet);
				generateBullet(selectBullet);
			}
			break;
		case KeyEvent.VK_P:
			if(timer.isRunning()){
				gp.pause();
				timer.stop();
			}
			else timer.start();
			break;
		case KeyEvent.VK_X:
			if(selectBullet == 0){
				selectBullet = 1;
			}
			else {
				selectBullet = 0;
			}
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
		if(play){
			controlVehicle(e);
		}
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
