package f2.spw;

public class SpaceShipBullet extends Bullet {

	public SpaceShipBullet(int x, int y) {
		super(x, y);
		super.setImg("f2/image/bullet.png");
	}
	@Override
	public void shoot(boolean speedState){
		if(speedState)
			y -= step * 4;
		else
			y -= step;
		if(y < 0){
			alive = false;
		}
	}
}
