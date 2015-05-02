package f2.spw;

import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;

public class MissileBullet extends SpaceShipBullet {

	public MissileBullet(int x, int y) {
		super(x, y);
		super.setImg("f2/image/missile.gif");
	}
	public void shoot(boolean speedState){
		if(speedState)
			y -= step * 4;
		else
			y -= step - 4;
		if(y < 0){
			alive = false;
		}
	}
	public Double getRectangle(int width,int height) {
		return new Rectangle2D.Double(x, y, width, height);
	}
}
