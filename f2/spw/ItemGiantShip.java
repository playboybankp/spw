package f2.spw;

public class ItemGiantShip extends Item {
	int step = 15;
	public ItemGiantShip(int x, int y) {
		super(x, y,20,20);
		super.setImg("f2/image/giantship.gif");
	}
	public void getGiantShip(SpaceShip v){
		v.invisible(5000);
		this.crash();
	}
}
