package f2.spw;

import java.awt.Color;
import java.awt.Graphics2D;

public class Bullet extends Enemy {

	public Bullet(int x, int y) {
		super(x, y);
	}
	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.RED);
		g.fillRect(x, y, 5, 10);
	}
	public void proceed(){
		y -= step;
		if(y < 0){
			alive = false;
		}
	}

}
