package f2.spw;

import java.awt.Color;
import java.awt.Graphics2D;

public class Bullet extends Enemy {

	public Bullet(int x, int y) {
		super(x, y);
		super.setImg("f2/image/bullet.png");
	}
	
	public void proceed(boolean speedState){
		if(speedState)
			y -= step * 4;
		else
			y -= step;
		if(y < 0){
			alive = false;
		}
	}

}
