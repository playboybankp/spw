package f2.spw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
	
	private BufferedImage bi;
	private Image bg,hp,go,pause,circle,bullet1,bullet2;
	String text = new String();
	Graphics2D big;
	ArrayList<Sprite> sprites = new ArrayList<Sprite>();
	
	public GamePanel() {
		try{
			File file = new File("f2/image/bg.jpg");
			bg = ImageIO.read(file);
			File file2 = new File("f2/image/heart.gif");
			hp = ImageIO.read(file2);
			File file3 = new File("f2/image/go.gif");
			go = ImageIO.read(file3);
			File file4 = new File("f2/image/pause.png");
			pause = ImageIO.read(file4);
			File file5 = new File("f2/image/circle.png");
			circle = ImageIO.read(file5);
			File file6 = new File("f2/image/bullet.png");
			bullet1 = ImageIO.read(file6);
			File file7 = new File("f2/image/missile.gif");
			bullet2 = ImageIO.read(file7);
		}catch(IOException e){
			e.printStackTrace();
		}
		bi = new BufferedImage(400, 600, BufferedImage.TYPE_INT_ARGB);
		big = (Graphics2D) bi.getGraphics();
}
	public void updateGameUI(GameReporter reporter){
		big.clearRect(0, 0, 400, 600);
		big = (Graphics2D) bi.getGraphics();
		big.drawImage(bg, 0, 0, null);
		big.drawImage(hp, 20, 10, null);
		big.setColor(Color.WHITE);		
		big.drawString(String.format("%08d", reporter.getScore()), 300, 20);
		big.drawString(String.format("x %d", reporter.getHp()),40 , 20);
		for(Sprite s : sprites){
			s.draw(big);
		}
		
		repaint();
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(bi, null, 0, 0);
	}
	public void gameOver(){
		big.drawImage(go, 80, 280, null);
	}
	public void pause(){
		big.drawImage(pause , 70 , 150 , null);
		repaint();
	}
	public void bulletInfo(int bullet_type){
		//big.drawImage(circle , 15,250 , null);
		if(bullet_type == 1){
			big.drawImage(bullet1,30,100,null);
		}
		else{
			big.drawImage(bullet2,30,100,null);
		}
		repaint();
	}
}
