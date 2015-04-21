package f2.spw;

public class Item extends Sprite{
	protected int step = 9;
	public Item(int x, int y,int width,int height) {
		super(x,y,width,height);
	}
	public void drop(boolean speedState){
		if(speedState)
			y += step * 4;
		else
			y += step;
		
		if(y > 600){
			alive = false;
		}
	}

}
