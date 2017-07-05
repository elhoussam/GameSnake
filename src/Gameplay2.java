import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;


import java.awt.Image;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Gameplay2 extends JPanel implements KeyListener,ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	// Attribut 
	private Image titleImage; 
	
	
	// enxpos,enypos represent the position of the Food of the Snakes
	private int [] enxpos = {25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450
			,500,525,550,575,600,625,650,675,700,725,750,775,800,825,850};

	private int [] enypos={
			75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625
			};

	// Variable to pick the position of the Food randomly
	private int score = 0;
	private Image enImage;
	private Random random = new Random();
	private int xpos=random.nextInt(enxpos.length);
	private int ypos=random.nextInt(enypos.length);
	
	// The Snake is the Array (position) the first element represent the Head, and the rest represent the tail
	private int[] snakexlength = new int[750];
	private int[] snakeylength = new int[750];

	private int lengthofsnake = 3 ;
	private int moves =0;
	
	// Booleans represent the current movments
	private boolean left = false ;
	private boolean right = false ;
	private boolean down = false ;
	private boolean up = false ;
	
	// To Change the image the head
	private Image rightmouth ;
	private Image leftmouth ;
	private Image downmouth ;
	private Image upmouth ;

	private Image snakeImage; // represent the Tail
	
	
	private Timer timer;
	private int delay = 500 ; // speed of the snake

	
	// Methodes
	
	public Gameplay2(){
		
		addKeyListener(this);
		this.setFocusable(true);
		this.setFocusTraversalKeysEnabled(false);
		// launch the timer
		timer = new Timer(delay,this);
		timer.start();
		
	}
	
	public void paint(Graphics g){
		//Here we set the first position of the snake
		if ( moves == 0 ){
			//game just start

		snakexlength[2]= 50;
		snakexlength[1]= 75;
		snakexlength[0]= 100; // x position of the head


		snakeylength[2]= 100;
		snakeylength[1]= 100;
		snakeylength[0]= 100;// x position of the head


		}
		// draw title image border
		
		g.setColor(Color.WHITE);
		g.drawRect(24, 10, 851, 55);
		
		// Draw the title image
	//	URL url = Main.class.getResource("snaketitle.jpg");
		titleImage = ResourceLoader.getImage("snaketitle.jpg");
		//titleImage.paintIcon(this, g, 25, 11);

		g.drawImage(titleImage,25,11, null);
		// draw border for gameplay

		g.drawRect(24, 74, 851, 577);
		// draw background for the gameplay
		g.setColor(Color.BLACK);
		g.fillRect(25, 75, 850, 575);
		


		// draw scores 
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial",Font.PLAIN,14));
		g.drawString("Scores:"+score,780,35);

		// draw the length

		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial",Font.PLAIN,14));
		g.drawString("Length:"+lengthofsnake,780,57);

		
		// set the first direction is the "Right"
		rightmouth = ResourceLoader.getImage("rightmouth.png");
		//rightmouth.paintIcon(this,g,snakexlength[0],snakeylength[0]);

		g.drawImage(rightmouth,snakexlength[0],snakeylength[0], null);

		for(int a =0 ; a<lengthofsnake;a++){
		
			if ( a==0){ // To Change the Image of the Head
				if ( right){
					// if the user press RIGHT KEY chanage to rightmouth its the same for the others
					rightmouth = ResourceLoader.getImage("rightmouth.png");
					// #sam Try to add every image into new JFrame
					g.drawImage(rightmouth,snakexlength[a],snakeylength[a], null);
					//rightmouth.paintIcon(this,g,snakexlength[a],snakeylength[a])	;
				}else if( left){
					leftmouth = ResourceLoader.getImage("leftmouth.png");
					g.drawImage(leftmouth,snakexlength[a],snakeylength[a], null);
				}else if ( down){
					downmouth = ResourceLoader.getImage("downmouth.png");
					g.drawImage(downmouth,snakexlength[a],snakeylength[a], null);
				}else if( up){
					upmouth = ResourceLoader.getImage("upmouth.png");
					g.drawImage(upmouth,snakexlength[a],snakeylength[a], null);
				}
			
			}else{ //means is not the head of snakes
				snakeImage = ResourceLoader.getImage("snakeimage.png");

				g.drawImage(snakeImage,snakexlength[a],snakeylength[a], null);
				//snakeImage.paintIcon(this,g,snakexlength[a],snakeylength[a])	;
			}
			
			
		}//End for
		//Here The snake Eat the Food if it has the same position with the food
			enImage = ResourceLoader.getImage("enemy.png");
			if( (enxpos[xpos] == snakexlength[0] && enypos[ypos] == snakeylength[0]) ) {
				lengthofsnake++; 
				score++;
				//Pick new Position
				xpos=random.nextInt(enxpos.length);
				ypos=random.nextInt(enypos.length);
			
			}
			//enImage.paintIcon(this,g,enxpos[xpos],enypos[ypos]);
			g.drawImage(enImage,enxpos[xpos],enypos[ypos], null);
			
			for(int b=1;b<lengthofsnake;b++){
				if( snakexlength[b] == snakexlength[0] &&
					snakeylength[b] == snakeylength[0] ){
				
				right = false ;
				left = false ;
				up = false ;
				down = false ;
				g.setColor(Color.WHITE);
				g.setFont(new Font("Arial",Font.BOLD,50));
				g.drawString("Game Over",300,330);

				g.setFont(new Font("Arial",Font.BOLD,20));
				g.drawString("Press the space key to restart",350,370);
				}
			}

			g.dispose();// its seem finalize 
	}

	@Override
	public void actionPerformed(ActionEvent e) {

			timer.start();
			if( right ){
				for ( int r=lengthofsnake-1;r>=0;r--)
					snakeylength[r+1]=snakeylength[r];
				
				for ( int r=lengthofsnake;r>=0;r--){
				if(r==0)snakexlength[r]=snakexlength[r]+25;
				else snakexlength[r]=snakexlength[r-1];
				if ( snakexlength[r]>850 ) snakexlength[r]=25;
				
				}//for
				repaint();
			}// if right
			if( left ){
				for ( int r=lengthofsnake-1;r>=0;r--)
					snakeylength[r+1]=snakeylength[r];
				
				for ( int r=lengthofsnake;r>=0;r--){
				if(r==0)snakexlength[r]=snakexlength[r]-25;
				else snakexlength[r]=snakexlength[r-1];
				if ( snakexlength[r]<25 ) snakexlength[r]=850;
				
				}//for
				repaint();
			}
			if( up ){
				for ( int r=lengthofsnake-1;r>=0;r--)
					snakexlength[r+1]=snakexlength[r];
				
				for ( int r=lengthofsnake;r>=0;r--){
				if(r==0)snakeylength[r]=snakeylength[r]-25;
				else snakeylength[r]=snakeylength[r-1];

				if ( snakeylength[r]<75 ) snakeylength[r]=625 ;
				}//for
				repaint();
			}
			if( down ){
				for ( int r=lengthofsnake-1;r>=0;r--)
					snakexlength[r+1]=snakexlength[r];
				
				for ( int r=lengthofsnake;r>=0;r--){
				if(r==0)snakeylength[r]=snakeylength[r]+25;
				else snakeylength[r]=snakeylength[r-1];
				if ( snakeylength[r]>625 ) snakeylength[r]=75 ;

				}//for
				repaint();
			}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		System.out.println("Hello");
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if( e.getKeyCode()== KeyEvent.VK_SPACE){
			moves=0;
			score=0;
			lengthofsnake=3;  
			repaint();
			}

		if( e.getKeyCode() == KeyEvent.VK_RIGHT ){
			moves++;
		right = true;
		if ( !left ) { right=true; }
		else { right = false ;
		       left = true;  
		}
		left = false ;
		up = false ;
		down = false ;
		}


		if( e.getKeyCode() == KeyEvent.VK_LEFT ){
			moves++;
		left = true;
		if ( !right ) { left=true; }
		else { left = false ;
		       right = true;  
		} 
		up = false ;
		down = false ;
		}


		if( e.getKeyCode() == KeyEvent.VK_UP ){
			moves++;
		up = true;
		if ( !down ) { up=true; }
		else { up = false ;
		       down = true;  
		} 
		left = false ;
		right = false ;
		}


		if( e.getKeyCode() == KeyEvent.VK_DOWN ){
			moves++;
		down = true;
		if ( !up ) { down=true; }
		else {  down = false ;
		       up = true;  
		} 
		left = false ;
		right = false ;
		}


	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
