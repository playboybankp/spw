package f2.spw;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpaceShip extends Sprite{

	int step = 8;
	private int hp;
	private Image img;
	public SpaceShip(int x, int y, int width, int height, int hp) {
		super(x, y, width, height);
		this.hp = hp;
		try{
			File file = new File("f2/image/spaceship.png");
			img = ImageIO.read(file);
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	@Override
	public void draw(Graphics2D g) {
		//g.setColor(Color.GREEN);
		//g.fillRect(x, y, width, height);
		g.drawImage(img, x, y, width, height, null);
		
	}

	public void move(int direction){
		x += (step * direction);
		if(x < 0)
			x = 0;
		if(x > 400 - width)
			x = 400 - width;
	}
	public int getHp(){
		return hp;
	}
	public void crash(){
		--hp;
	}

}
