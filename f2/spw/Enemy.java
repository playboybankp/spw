package f2.spw;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Enemy extends Sprite{
	public static final int Y_TO_FADE = 400;
	public static final int Y_TO_DIE = 600;
	
	protected int step = 12;
	protected boolean alive = true;
	private Image img;
	public Enemy(int x, int y) {
		super(x, y, 15, 20);
		try{
			File file = new File("f2/image/enemyWarShip.png");
			img = ImageIO.read(file);
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	@Override
	public void draw(Graphics2D g) {
//		if(y < Y_TO_FADE)
//			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
//		else{
//			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 
//					(float)(Y_TO_DIE - y)/(Y_TO_DIE - Y_TO_FADE)));
//		}
//		g.setColor(Color.RED);
//		g.fillRect(x, y, width, height);
		g.drawImage(img, x, y, width, height, null);
	}

	public void proceed(){
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