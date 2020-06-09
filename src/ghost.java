//Scott
import java.util.Random;

public class ghost {
	
	private EZImage ghost;
	int  rx;
	int x = 720;
	int y = 450;
			
	public ghost(int r, int k) { //create ghost obstacles
		x = r;
		y = k;
		ghost = EZ.addImage("ghost1.png", r, k);
		ghost.scaleTo(0.23);
	}
	
	private int random() { //get a random int from 0 to 4
		Random randomgenerator = new Random();
		rx = randomgenerator.nextInt(4);
		return rx;
	}

	public void randmove() { //from the random function determines what direction the ghost will move
		switch (rx) {
		case 0:
			random();
			x+=15;
			setdestination();
			break;
		case 1:
			random();
			x-=15;
			setdestination();
			break;
		case 2:
			random();
			y+=15;
			setdestination();
			break;
		case 3:
			random();
			y-=15;
			setdestination();
			break;
		default:
			random();
			break;
		}
	}
	
	public void setdestination() { //moves the ghost. if the ghost is off screen move it to the center
		if((x<1440)&&(y<900)) {
			ghost.translateTo(x, y);
		}else {
			x= 720;
			y= 450;
			ghost.translateTo(x, y);
		}
	}
	
	public int getgX() { //get x coord
		x = ghost.getXCenter();
		return x;
	}
	public int getgY() { //get y coord
		y = ghost.getYCenter();
		return y;
	}
	public boolean isInside(int posx, int posy) { //check if ghost is inside something
		return ghost.isPointInElement(posx, posy);
	}
	public void done() { //scale ghosts to 0 to remove them from the game
		ghost.scaleTo(0);
	}
}
