package f2.spw;

public class Item extends Sprite{
	private int step = 9;
	public Item(int x, int y,int width,int height) {
		super(x,y,width,height);
	}
	public void drop(){
		y += step;
		if(y > 600){
			alive = false;
		}
	}

}
