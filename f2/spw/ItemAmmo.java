package f2.spw;

public class ItemAmmo extends Item{
	int step = 10;
	public ItemAmmo(int x, int y ){
		super(x,y,20,30);
		super.setImg("f2/image/ammo.png");
	}
	public void fill_ammo(SpaceShip v){
		this.crash();
		System.out.println("Ammo Crash");
		int ammo_type = (int)(Math.random()*3);
		System.out.println("type"+ammo_type);
		switch(ammo_type){
			case 1 : v.fillAmmo(1,(int)(Math.random()*10) + 1);
					System.out.println("Missile" + v.getAmmo(2));
					break;
			default : v.fillAmmo(0,(int)(Math.random()*50) + 1);
					System.out.println("Ammo" + v.getAmmo(1));
					break;
		}
		
	}
}
