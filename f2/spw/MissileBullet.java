package f2.spw;

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
}
