import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

/**
* enemy class
*
* its the enemy
*/

public class EnemyOne{
	private Image enemy;
	private AffineTransform tx;
	
	private int dir = 0; 					//dir never chnages
	private int width, height;
	private int x, y;						//position of the object
	private int vx, vy;						//movement variables
	private double scaleWidth =.5;		//change to scale image
	private double scaleHeight = .5; 		//change to scale image
	private int originX = 0;
	private int originY = 0;
	private int imageNumber;
	private int timer = 0;
	
	public EnemyOne() {
		imageNumber = 1 + (int)(Math.random() * 5);//random number to select 1 of the enemy images
		
		enemy 	= getImage("/imgs/"+"truck"+imageNumber+".png"); //load the image for enemy

		width = 120;
		height = 60;
		x = 0;
		y = 0;
		vx = 128/2;
		vy = 64/4;
		timer = 0;//physics timer
		
		tx = AffineTransform.getTranslateInstance(0, 0);
		
		init(x, y); 				//initialize the location of the image
									//use your variables
	}
	
	/**
	* return true if an object represented by x, y , w , h occupies 
	* any space occupied by object
	*
	* @param none
	* @returns boolean- if rectanges collide
	*/
	public boolean collided(int x, int y, int width, int height) {
		//if scaling images with scale var, make sure w/h reflect what we see on screen
		Rectangle otherObj = new Rectangle(x,y,width,height);
		Rectangle thisObj = new Rectangle(this.x,this.y + this.height/2,this.width,this.height/2);

	return thisObj.intersects(otherObj);
	}
	
	/**
	* altranate collsion function that uses pre made rectange instaid of w, h, x, y
	*
	* @param none
	* @returns boolean- if rectanges collide
	*/
	public boolean collided(Rectangle hitbox) {
		Rectangle otherObj = hitbox;
		Rectangle thisObj = new Rectangle(this.x,this.y + this.height/2,this.width,this.height/2);

		return thisObj.intersects(otherObj);
	}

	//getters and setters
	public int getOriginX() {
		return originX;
	}


	public void setOriginX(int originX) {
		this.originX = originX;
	}


	public int getOriginY() {
		return originY;
	}


	public void setOriginY(int originY) {
		this.originY = originY;
	}

	public Rectangle getHitbox() {
		return new Rectangle(this.x,this.y + this.height/2,this.width,this.height/2);//returns hitbox
	}
	
	
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

	//draw image and apply physics
	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		
			
		
		init(x,y);
		
		timer++;//use timer for velocity
		
		if(timer % 20 == 0) {//dealy velocity to make them dodgable
		x+=vx;
		y+=vy;
		timer = 0;
		}
		
		
		g2.drawImage(enemy, tx, null);

	}
	
	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(scaleWidth, scaleHeight);
	}

	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = EnemyOne.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}
}

	
