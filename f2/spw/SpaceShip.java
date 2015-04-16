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
	private boolean giantState = false;
	private Timer timer;
	public SpaceShip(int x, int y, int hp) {
		super(x, y, 30, 30);
		this.hp = hp;
		super.setImg("f2/image/spaceship.png");
	}
	
	public void move(int direction){
		if(invisible)
			x += (step * direction)*2;
		else x += (step * direction);
		if(x < 0)
			x = 0;
		if(x > 400 - width)
			x = 400 - width;
	}
	public void moveForward(int direction){
		if(invisible)
			y += (step * direction)*5;
		else y += (step * direction);
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
		timer = new Timer(delay, new ActionListener() {
		      @Override
			public void actionPerformed(ActionEvent e) {
		    	invisible = false;
		    	if(getGiantState()){
		    		getTiny();
		    		giantState = false;
		    	}
		    	timer.stop();
		  }
		});
		
		invisible = true;
		if(!getGiantState()){
			getGiant();
			giantState = true;
			System.out.println("getGiant");
		}
		timer.start();

	}
	public void getGiant(){
		width *= 2;
		height *= 2;
	}
	public void getTiny(){
		width /= 2;
		height /= 2;
	}
	public boolean getGiantState(){
		return giantState;
	}
	public boolean getInvisible(){
		return invisible;
	}
}
