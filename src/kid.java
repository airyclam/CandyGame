//Eric
public class kid {
	private EZImage kidPicture;
	private int x, y;
	static final int KID_SPEED = 7;
	// Constructor for creating a kid.
	public kid(String filename, int posx, int posy) {
		kidPicture = EZ.addImage(filename, posx, posy);
		x = posx;
		y = posy;
	}
	
//Accessor method to retrieve the position of the kid.
	public int getX() {
		return kidPicture.getXCenter();
	}

	public int getY() {
		return kidPicture.getYCenter();
	}

	// Set the position of the kid
	public void setPosition(int posx, int posy) {
		x = posx;
		y = posy;
		setKidImagePosition(x, y);
	}

	private void setKidImagePosition(int posx, int posy) {
		kidPicture.translateTo(posx, posy);
	}

	// Methods for moving the kid.
	public void moveLeft(int step) {
		x = x - step;
		setKidImagePosition(x, y);
	}

	public void moveRight(int step) {
		x = x + step;
		setKidImagePosition(x, y);
	}

	public void moveUp(int step) {
		y = y - step;
		setKidImagePosition(x, y);
	}

	public void moveDown(int step) {
		y = y + step;
		setKidImagePosition(x, y);
	}

	// Keyboard controls for moving the kid.
	public void ControlIt() {
		if (EZInteraction.isKeyDown('w')) {
			moveUp(KID_SPEED);
		} else if (EZInteraction.isKeyDown('a')) {
			moveLeft(KID_SPEED);
		} else if (EZInteraction.isKeyDown('s')) {
			moveDown(KID_SPEED);
		} else if (EZInteraction.isKeyDown('d')) {
			moveRight(KID_SPEED);
		}
	}
	public void hide() {
		kidPicture.hide();
	}
	public boolean isshowing() {
		return kidPicture.isShowing();
	}
}