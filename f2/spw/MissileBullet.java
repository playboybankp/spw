package f2.spw;

public class MissileBullet extends Bullet {

	public MissileBullet(int x, int y) {
		super(x, y);
		super.setImg("f2/image/missile.gif");
	}
	public void proceed(){
		y -= step/2;
		if(y < 0){
			alive = false;
		}
	}
}
