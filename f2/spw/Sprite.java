package f2.spw;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprite {
	int x;
	int y;
	int width;
	int height;
	private Image img;
	protected boolean alive = true;
	public Sprite(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
	}
	
	public void draw(Graphics2D g){
		g.drawImage(img, x, y, width, height, null);
		
	}
	public void setImg(String dir){
		try{
			File file = new File(dir);
			img = ImageIO.read(file);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	public Double getRectangle() {
		return new Rectangle2D.Double(x, y, width, height);
	}
	public boolean isAlive(){
		return alive;
	}
	public void crash() {
		alive = false;
		
	}
}
