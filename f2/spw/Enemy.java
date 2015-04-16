package f2.spw;

public class Enemy extends Sprite{
	public static final int Y_TO_FADE = 400;
	public static final int Y_TO_DIE = 600;
	
	protected int step = 12;
	public Enemy(int x, int y) {
		super(x, y, 15, 20);
		super.setImg("f2/image/enemyWarShip.png");
	}

	public void proceed(boolean speedState){
		if(speedState)
			y += step * 4;
		else
			y += step;
		
		if(y > Y_TO_DIE){
			alive = false;
		}
	}
	
	public boolean isAlive(){
		return alive;
	}
	
	public void crash(){
		alive = false;
	}
}