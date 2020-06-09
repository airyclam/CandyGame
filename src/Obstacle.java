//Eric
public class Obstacle {
	
	private EZImage gravePicture;
	private static int x;
	private static int y;

	public Obstacle(String filename, float posx, float posy) {
		gravePicture = EZ.addImage(filename, (int)posx, (int)posy);
	}

// Set the position of the obstacle.
	public void setPosition(int posx, int posy) {
		x = posx;
		y = posy;
		setGraveImagePosition(x, y);
	}
	
// Set the image position of the obstacle.
	private void setGraveImagePosition(int posx, int posy) {
		gravePicture.translateTo(posx, posy);
	}
	
	public boolean isInside(int posx, int posy) {
		return gravePicture.isPointInElement(posx, posy);
	}
	
	public void done() {
		gravePicture.translateTo(2000, 2000);
	}
}
