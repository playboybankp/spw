package f2.spw;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

import javax.swing.Timer;

import org.omg.CORBA.PRIVATE_MEMBER;

public class SpaceShip extends Sprite{

	private int step = 8;
	private int hp;
	private boolean invisible = false;
	public SpaceShip(int x, int y, int width, int height, int hp) {
		super(x, y, width, height);
		this.hp = hp;
		super.setImg("f2/image/spaceship.png");
	}

	public void move(int direction){
		x += (step * direction);
		if(x < 0)
			x = 0;
		if(x > 400 - width)
			x = 400 - width;
	}
	public void moveForward(int direction){
		y += (step * direction);
		if(y < 40)
			y = 40;
		if(y > 550 + height)
			y = 550 + height;
	}
	public int getHp(){
		return hp;
	}
	public void crash(){
		if(invisible == false)
			hp -= 1;
	}
	public int centerX(){
		return x + width/2;
	}
	public int centerY(){
		return y;
	}
	public void getHeart(){
		hp += 1;
	}
	public void invisible(int delay){
		invisible = true;
		  ActionListener taskPerformer = new ActionListener() {
		      @Override
			public void actionPerformed(ActionEvent e) {
				invisible = false;
			}
		  };
		  new Timer(delay, taskPerformer).start();
		
	}

}
