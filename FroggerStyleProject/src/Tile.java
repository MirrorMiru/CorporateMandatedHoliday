import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

//tile class

public class Tile{
	private Image image;
	private AffineTransform tx;
	
	int width, height;
	int x, y;						//position of the object
	double scaleWidth = .5;		//scaled to half of original sprite
	double scaleHeight = .5; 		

	public Tile(int x, int y, int type) {
		if(type == 1) {
			image 	= getImage("/imgs/greyTile.png"); //load the image for tile based on comstructor
		}else if(type == 2) {
			image 	= getImage("/imgs/redTile.png");
		}else if(type == 3) {
			image 	= getImage("/imgs/blackTile.png");
		}else if(type == 4) {
			image 	= getImage("/imgs/yellowTile.png");
		}else if(type == 5) {
			image 	= getImage("/imgs/slab3.png");
		}else {
			System.out.println("oops");//shoudl neevr happen
		}

		width = 256/2;
		height = 128/2;
		this.x = x;
		this.y = x;
		tx = AffineTransform.getTranslateInstance(0, 0);
		init(x, y); 				//initialize the location of the image
									//use your variables
	}

	//just draw the image
	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		init(x,y);

			g2.drawImage(image, tx, null);
	}
	
	//getters and setters
	//the getters are fairly useless here
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
			URL imageURL = Tile.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

}
