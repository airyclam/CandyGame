//Eric and Scott worked on this but Eric's code was used
public class Candy {
	private EZImage candyPicture;
	private int x;
	private int y;
	int directionX = 5;		// Stores the x direction of the candy
	int directionY = 5;
	
	public Candy(String filename, float posx, float posy) {
		candyPicture = EZ.addImage(filename, (int)posx, (int)posy);
	}
		
// Set the image position of the candy.
	public void moveCandy() {
		//If statements make the candy bounce off walls
		x = candyPicture.getXCenter();
		y = candyPicture.getYCenter();
		if (x > 1440) {
			directionX = -directionX;
		}
		if (x < 0) {
			directionX = -directionX;
		}

		if (y > 900 ) {		
			directionY = -directionY;
		}
		if (y < 0) {
			directionY = -directionY;
		}
		candyPicture.translateBy(directionX, directionY);
	}
	// Used to check if something is inside candy
	public boolean isInside(int posx, int posy) {
			return candyPicture.isPointInElement(posx, posy);
	}
	// Used to find location of candy
	public int getPositionX() {
		return x;
	}
	
	public int getPositionY() {
		return y;
	}
	// Used to displace candy when done
	public void doneCandy() {
		candyPicture.translateTo(-1000, -1000);
		candyPicture.translateBy(0, 0);
	}
}

