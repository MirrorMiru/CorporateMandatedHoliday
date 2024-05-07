import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

//CLONE OF ENEMY CLASS

public class Platform{
	private Image enemy;
	private AffineTransform tx;
	
	private int dir = 0; 					
	private int width, height;
	private int x, y;						//position of the object
	private int vx, vy;						//movement variables
	private double scaleWidth =.5;		//change to scale image
	private double scaleHeight = .5; 		//change to scale image
	private int timer;
	private int speed;
	public Platform(int spd) {
		enemy 	= getImage("/imgs/"+"moving.png"); //load the image for platfrom
	

		//alter these
		width = 256/9;
		height = 128/8;
		x = 0;
		y = 0;
		vx = 128/2;
		vy = 64/4;
		timer = 0;
		speed = spd;
		
		tx = AffineTransform.getTranslateInstance(0, 0);
		
		init(x, y); 				//initialize the location of the image
									//use your variables
		
	}
	
	//same collision functions as enemyone class

	public boolean collided(int x, int y, int width, int height) {//uses pos and size

		Rectangle otherObj = new Rectangle(x,y,width,height);
		Rectangle thisObj = new Rectangle(this.x+55,this.y-5,this.width,this.height);

		return thisObj.intersects(otherObj);//check collison and return result
	}
	
	public boolean collided(Rectangle rect) {//uses premade rect
		Rectangle otherObj = rect;
		Rectangle thisObj = new Rectangle(this.x+55,this.y-5,this.width,this.height);

	return thisObj.intersects(otherObj);
	}
	
	public boolean getImpulse() {//check if platofrm would move based on timer
		boolean moved;
		if(timer % speed == 0) {
			 moved = true;
		}else {
		     moved = false;
		}
		return moved;
	}
	
	//getters and setters
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}


	public int getY() {
		return y;
	}


	public void setY(int y) {
		this.y = y;
	}


	public int getVx() {
		return vx;
	}


	public void setVx(int vx) {
		this.vx = vx;
	}


	public int getVy() {
		return vy;
	}


	public void setVy(int vy) {
		this.vy = vy;
	}

	//paint platform image and apply phyisics
	//velocity is applied just like with enemy
	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		timer++;// timer (just like enemy)
		
		if(timer % speed == 0) {//varaible speed
		x+=vx;
		y+=vy;
		timer = 0;
		}
		
		init(x,y);
		
		g2.drawImage(enemy, tx, null);
	}
	
	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(scaleWidth, scaleHeight);
	}

	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Platform.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

}
