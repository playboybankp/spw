package f2.spw;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class SpaceShip extends Sprite{
	private int step = 8;
	private int hp;
	private boolean invisible = false;
	private boolean giantState = false;
	int missile;
	int ammo;
	private Timer timer;
	public SpaceShip(int x, int y, int hp, int ammo) {
		super(x, y, 30, 30);
		this.hp = hp;
		this.ammo = ammo;
		missile = 0;
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
	public int getAmmo(int ammo_type){
		switch(ammo_type){
		case 0 : return ammo;
		case 1 : return missile;
		}
		return 0;
	}
	public void fillAmmo(int ammo_type,int ammo){
		switch(ammo_type){
			case 0 : this.ammo += ammo;
					break;
			case 1 : this.missile += ammo;
					break;
		}
	}
	public void shootBullet(int ammo_type){
		switch(ammo_type){
		case 0 : --ammo;
				break;
		case 1 : --missile;
				break;
		}
	}
}
