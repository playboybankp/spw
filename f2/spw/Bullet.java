package f2.spw;

public abstract class Bullet extends Sprite{
	protected int step = 12;
	public Bullet(int x, int y) {
		super(x, y, 15, 20);
		// TODO Auto-generated constructor stub
	}
	public abstract void shoot(boolean speedState);
	
	
}
