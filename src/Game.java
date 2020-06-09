// Eric and Scott
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.*;

public class Game {
	//Declaring Essential Variables
	static final int MAX_SCREEN_X = 1440;
	static final int MAX_SCREEN_Y = 900;
	static int score1 = 0;
	static int score2 = 0;
	static EZImage cntscrn;
	static EZText scoreboard1, start, goal;
	static EZText scoreboard2;
	static EZSound ghostSound = EZ.addSound("Azghost.wav");
	static EZSound deathSound, pointSound;
	static Candy candy[] = new Candy[5];
	static Obstacle grave[] = new Obstacle[35];
	static kid myKid;
	static kid2 myOtherKid;
	static ghost ghost[] = new ghost[7];
	
	public static void window() {
		EZ.initialize(MAX_SCREEN_X, MAX_SCREEN_Y);
		EZ.setCurrentWindow(0); //give the window an index so it can close when called
	}
	
	public static void controls() {
		//basic controls screen at the very start of the program
		cntscrn = EZ.addImage("controls.png", 720, 450);
		start = EZ.addText(720, 225, "Press 'Enter' to play", Color.black, 50);
		goal = EZ.addText(720, 125, "Collect the candy and avoid all obstacles", Color.black, 50);
		EZ.setBackgroundColor(new Color(255,255,255));
		
		while(!EZInteraction.wasKeyReleased(KeyEvent.VK_ENTER)) {
			EZ.refreshScreen();
		}
		EZ.closeWindowWithIndex(0);
	}
	
	public static void load() throws FileNotFoundException {	
		// Load Background
		EZ.initialize(MAX_SCREEN_X, MAX_SCREEN_Y);
		EZ.setCurrentWindow(0); //give the window an index so it can close when called
		EZ.setBackgroundColor(new Color(50, 87, 60));
		scoreboard1 = EZ.addText(50, 10 , "Score: " + score1, Color.WHITE, 20);
		scoreboard2 = EZ.addText(1200, 10 , "Score: " + score2, Color.WHITE, 20);
		
		// Load Candy Locations, create candy objects
		Scanner sc = new Scanner(new FileReader("candyData.txt"));
		for (int i = 0; i < 5; i++) {
			int t = sc.nextInt();
			int r = sc.nextInt();
			candy[i] = new Candy("candy.png", t, r);
		}
		sc.close();
		// Load Grave Locations, create grave objects
		Scanner fileScannerGraves = new Scanner(new FileReader("graveData.txt"));
		for (int i = 0; i < 35; i++) {
			int t = fileScannerGraves.nextInt();
			int r = fileScannerGraves.nextInt();
			grave[i] = new Obstacle("grave2.png", t, r);
		}
		fileScannerGraves.close();
		//load ghost locations, create ghost objects
		Scanner scg = new Scanner(new FileReader("ghostxy"));
		for (int i = 0; i<7; i++) {
			int r = scg.nextInt();
			int k = scg.nextInt();
			ghost[i] = new ghost(r, k);
		}
		scg.close();
		
		myKid = new kid("kid2.png", 200, 400);
		myOtherKid = new kid2("kid2.png", 1200, 400);
		pointSound = EZ.addSound("yes-1.wav");
		deathSound = EZ.addSound("death.wav");
		EZ.refreshScreen();
		
	}
	
	public static void points1() {
		//function to give a point to kid1 if it collects candy
		for(int i = 0; i<5; i++) {
			if(candy[i].isInside(myKid.getX(), myKid.getY())) {
				pointSound.play();
				candy[i].doneCandy();
				score1++;
				scoreboard1.setMsg("Score: " + score1);	
			}
		}
	}
		
	public static void points2() {
		//function to give a point to kid2 if it collects candy
		for (int i = 0; i < 5; i++) {
			if(candy[i].isInside(myOtherKid.getX(), myOtherKid.getY())) {
				pointSound.play();
				candy[i].doneCandy();
				score2++;
				scoreboard2.setMsg("Score: " + score2);	
			}
		}
	}
	
	public static void die1() {
		//function to check if kid1 touches the grave
		for (int i = 0; i < 35; i++) {
			if ((grave[i].isInside(myKid.getX(), myKid.getY()))){
				deathSound.play();
				ghostSound.stop();
				for(int t = 0; t<35; t++) {
					grave[t].done();
				}
				scoreboard1.hide();
				scoreboard2.hide();
				myKid.hide();
				myOtherKid.hide();
				for(int j = 0; j<5; j++) {
					candy[j].doneCandy();
				}
				EZ.setBackgroundColor(new Color(0, 0, 0));
				score2 = 5;
			}
		}
	}
	
	public static void die2() {
		//function to check if kid2 touches the grave
		for (int i = 0; i < 35; i++) {
			if ((grave[i].isInside(myOtherKid.getX(), myOtherKid.getY()))){
				deathSound.play();
				ghostSound.stop();
				for(int t = 0; t<35; t++) {
					grave[t].done();
				}
				scoreboard1.hide();
				scoreboard2.hide();
				myKid.hide();
				myOtherKid.hide();
				for(int j = 0; j<5; j++) {
					candy[j].doneCandy();
				}
				EZ.setBackgroundColor(new Color(0, 0, 0));
				score1 = 5;
			}
		}
	}
	
	public static void g1() {
		//function to check if kid1 touches the ghost
		for (int i = 0; i < 7; i++) {
			if ((ghost[i].isInside(myKid.getX(), myKid.getY()))){
				deathSound.play();
				ghostSound.stop();
				for(int k = 0; k<7; k++) {
					ghost[k].done();
				}
				scoreboard1.hide();
				scoreboard2.hide();
				myKid.hide();
				myOtherKid.hide();
				for(int j = 0; j<5; j++) {
					candy[j].doneCandy();
				}
				for(int t = 0; t<35; t++) {
					grave[t].done();
				}
				EZ.setBackgroundColor(new Color(0, 0, 0));
				score2 = 5;
			}
		}		
	}
	
	public static void g2() {
		//function to check if kid2 touches the ghost
		for (int i = 0; i < 7; i++) {
			if ((ghost[i].isInside(myOtherKid.getX(), myOtherKid.getY()))){
				deathSound.play();
				ghostSound.stop();
				for(int k = 0; k<7; k++) {
					ghost[k].done();
				}
				scoreboard1.hide();
				scoreboard2.hide();
				myKid.hide();
				myOtherKid.hide();
				for(int j = 0; j<5; j++) {
					candy[j].doneCandy();
				}
				for(int t = 0; t<35; t++) {
					grave[t].done();
				}
				EZ.setBackgroundColor(new Color(0, 0, 0));
				score1 = 5;
			}
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException{
		// Game Loop
		window();
		controls();
		EZ.refreshScreen();
		
		while(true) {
			ghostSound.loop();//flawlessly loop the sound continuously once the game starts	
				
				if((score1 == 0) && (score2 == 0)) {		
					load();
						
					while(score1+score2 < 5) {
						myKid.ControlIt();
						myOtherKid.ControlIt();
						points1();
						points2();
						die1();
						die2();
						g1();
						g2();
						EZ.refreshScreen();
						
						for(int i = 0; i<7; i++) {
							//initiate ghost movement
							ghost[i].randmove();
						}
					
						for(int i = 0; i<5; i++) {
							//initiate candy movement
							candy[i].moveCandy();
						}
						for (int i = 0; i < 35; i++) {
						// check if candy hits grave, if so change direction. Timer gives delay
							for (int j = 0; j < 5; j++) {
								if((grave[i].isInside(candy[j].getPositionX(), candy[j].getPositionY()))){
									candy[j].directionY = -candy[j].directionY;
									}
								
							}
						}
					}
				}
			
			//If all candies are gone, end game. Winner has more points
			if (score1+score2 >= 5) {
				ghostSound.stop();
				EZ.removeAllEZElements();
				EZ.setBackgroundColor(new Color(0, 0, 0));
				EZ.addText(350, 400, "Press 'r' to restart", Color.white, 10);
				
				if (score1>score2) {
					EZ.addText(350, 250, "Player 1 Wins!", Color.white, 10);
				}
				else EZ.addText(350, 250, "Player 2 Wins!", Color.white, 10);
				
				while(EZInteraction.isKeyDown('r') == false) {
					EZ.refreshScreen();
				}
				for(int q=0;q<100;q++) {
					EZ.closeWindowWithIndex(q);
					score1 = 0;
					score2 = 0;
				}
			}
			EZ.refreshScreen();
		}
	}
}