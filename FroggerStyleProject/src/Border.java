import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

//tile with collision

public class Border{
	private Image image;
	private AffineTransform tx;
	
	private int width, height;
	private int x, y;						//position of the object
	private double scaleWidth = .5;		//change to scale image
	private double scaleHeight = .5; 		//change to scale image

	private int modifier = 0;//assigns int to corealte with enemy for brakers and makers
	
	public Border(int x, int y) {
	
		image 	= getImage("/imgs/redTile.png");//dosent really matter (will be invisible)
		
		width = 60;
		height = 5;
		this.x = x;
		this.y = x;
		tx = AffineTransform.getTranslateInstance(0, 0);
		init(x, y); 				//initialize the location of the image
									//use your variables
	}

	//never called. redundant code
	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		init(x,y);

		g2.drawImage(image, tx, null);
	}
	
	/*
	 * return true if an object represented by x, y , w , h occupies 
	 * any space occupied by object
	 */
	public boolean collided(int x, int y, int width, int height) {
		
		Rectangle otherObj = new Rectangle(x,y,width,height);
		Rectangle thisObj = new Rectangle(this.x,this.y,this.width,this.height);

		return thisObj.intersects(otherObj);
	}
	
	//getters and setters
	
	public int getModifier() {
		return modifier;//Modifier for enemy logic
	}

	public void setModifier(int modifier) {
		this.modifier = modifier;
	}

	public Rectangle getHitbox() {
		return new Rectangle(this.x,this.y,this.width,this.height);
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

	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(scaleWidth, scaleHeight);
	}

	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Border.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

}
