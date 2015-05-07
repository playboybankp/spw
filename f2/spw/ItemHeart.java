package f2.spw;

public class ItemHeart extends Item{
	public ItemHeart(int x, int y) {
		super(x, y,15,20);
		super.setImg("f2/image/heart.gif");
	}
	public void getHeart(SpaceShip v){
		this.crash();
		v.getHeart();
		
	}
}
