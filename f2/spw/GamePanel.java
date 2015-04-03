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
	private Image bg,hp,go;
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
		}catch(IOException e){
			e.printStackTrace();
		}
		bi = new BufferedImage(400, 600, BufferedImage.TYPE_INT_ARGB);
		big = (Graphics2D) bi.getGraphics();
		big.drawImage(bg, 0, 0, null);
		big.drawImage(hp, 20, 10, null);
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
	public void GameOver(){
		big.drawImage(go, 80, 280, null);
	}

}
