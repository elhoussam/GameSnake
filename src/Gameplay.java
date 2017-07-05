import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Gameplay extends JPanel implements KeyListener,ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = -190436633005916787L;


	// Attribut 
	private ImageIcon titleImage; 
	private boolean gameover = false ;
	
	// enxpos,enypos represent the position of the Food of the Snakes
	private int [] enxpos = {25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450
			,500,525,550,575,600,625,650,675,700,725,750,775,800,825,850};

	private int [] enypos={
			75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625
			};

	// Variable to pick the position of the Food randomly
	private int score = 0;
	private ImageIcon enImage;
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
	private ImageIcon rightmouth ;
	private ImageIcon leftmouth ;
	private ImageIcon downmouth ;
	private ImageIcon upmouth ;

	private ImageIcon snakeImage; // represent the Tail
	
	private ImageIcon speedUp; // Speed
	private ImageIcon speeDown; // represent the Tail
	
	
	private Timer timer;
	private int delay = 70 ; // speed of the snake
	public int getDelay(){
		return delay;
	}
	public void setDelay(int delay){
		this.delay = delay ;
	}
	
	// Methodes
	
	public Gameplay(){
 
		System.out.println("Gameplay:Constructor");
		addKeyListener(this);
		this.setFocusable(true);
		this.setFocusTraversalKeysEnabled(false);
		// launch the timer
		timer = new Timer(delay,this);
		timer.start();
		
	}
	
	public void paint(Graphics g){

		//System.out.println("Gameplay:paint:Strat");
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
	//	URL url = Main.class.getResource(getClass().getRessource("img/snaketitle.jpg");
		titleImage = new ImageIcon(getClass().getResource("img/snaketitle.jpg"));
		titleImage.paintIcon(this, g, 25, 11);
	
		// draw border for gameplay

		g.drawRect(24, 74, 851, 577);
		// draw background for the gameplay
		g.setColor(Color.BLACK);
		g.fillRect(25, 75, 850, 575);
		


		// draw scores 
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial",Font.PLAIN,14));
		

		
		g.drawString("Scores:"+score,780,25);
		
		// draw the length

		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial",Font.PLAIN,14));
		g.drawString("Length:"+lengthofsnake,780,45);
		
		
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial",Font.PLAIN,14));
		g.drawString("Speed:"+delay/10+"U/S",780,65);
		
		
		
		
		/*speedUp = new ImageIcon(getClass().getResource("img/up.png"));
		
		titleImage.paintIcon(this, g, 150, 57);
		 
		speeDown = new ImageIcon(getClass().getResource("img/down.png"));
		titleImage.paintIcon(this, g, 150, 30); 
		*/
		
		// set the first direction is the "Right"
		rightmouth = new ImageIcon( getClass().getResource("img/rightmouth.png"));
		rightmouth.paintIcon(this,g,snakexlength[0],snakeylength[0]);
		

		for(int a =0 ; a<lengthofsnake;a++){
		
			if ( a==0){ // To Change the Image of the Head
				if ( right){
					// if the user press RIGHT KEY chanage to rightmouth its the same for the others
					rightmouth = new ImageIcon( getClass().getResource("img/rightmouth.png"));
					rightmouth.paintIcon(this,g,snakexlength[a],snakeylength[a])	;
				}else if( left){
					leftmouth = new ImageIcon( getClass().getResource("img/leftmouth.png"));
					leftmouth.paintIcon(this,g,snakexlength[a],snakeylength[a])	;
				}else if ( down){
					downmouth = new ImageIcon( getClass().getResource("img/downmouth.png"));
					downmouth.paintIcon(this,g,snakexlength[a],snakeylength[a])	;
				}else if( up){
					upmouth = new ImageIcon( getClass().getResource("img/upmouth.png"));
					upmouth.paintIcon(this,g,snakexlength[a],snakeylength[a])	;
				}
			
			}else{ //means is not the head of snakes
				snakeImage = new ImageIcon( getClass().getResource("img/snakeimage.png"));
				snakeImage.paintIcon(this,g,snakexlength[a],snakeylength[a])	;
			}
			
			
		}//End for
		//Here The snake Eat the Food if it has the same position with the food
			enImage = new ImageIcon( getClass().getResource("img/enemy.png"));
			if( (enxpos[xpos] == snakexlength[0] && enypos[ypos] == snakeylength[0]) ) {
				lengthofsnake++; 
				score++;
				//Pick new Position
				xpos=random.nextInt(enxpos.length);
				ypos=random.nextInt(enypos.length);
			
			}
			enImage.paintIcon(this,g,enxpos[xpos],enypos[ypos]);
			
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
				gameover = true ;
				g.setFont(new Font("Arial",Font.BOLD,20));
				g.drawString("Press the space key to restart",350,370);
				}
			}

			g.dispose();// its seem finalize 

			//System.out.println("Gameplay:paint:end");
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		//System.out.println("Gameplay:actionPerformed:Strat");
			//timer.start();
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

			//System.out.println("Gameplay:actionPerformed:end");
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {

		System.out.println("Gameplay:KeyPressed:Start");
		System.out.println(e.getKeyCode() );
		
		if( !gameover && e.getKeyCode()== KeyEvent.VK_SUBTRACT ){
			// When we add value to the delay means we slowing down
			
			System.out.print("key_2 [minus]: \nold speed is "+getDelay());
			setDelay( getDelay()+15 );
			System.out.println("new speed is "+getDelay());
			
			timer.setDelay(getDelay());
			timer.restart();
		}
		
		if(  !gameover &&  e.getKeyCode() == KeyEvent.VK_ADD ){
			// When we substract value to the delay means we speeding up
			System.out.print("key_8 [ADD]: \nold speed is "+getDelay());
			setDelay( getDelay()-15 );
			System.out.print("new speed is "+getDelay());

			timer.setDelay(getDelay());
			timer.restart();
		}
		
		if( gameover && e.getKeyCode()== KeyEvent.VK_SPACE){
			System.out.println("Gameplay:KeyPressed:Space");
			moves=0;
			score=0;
			lengthofsnake=3; 
			gameover = false ;
			repaint();
			}

		if(  !gameover &&  e.getKeyCode() == KeyEvent.VK_RIGHT ){
			 
			System.out.println("Gameplay:KeyPressed:RIGHT");
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


		if(  !gameover && e.getKeyCode() == KeyEvent.VK_LEFT ){

			System.out.println("Gameplay:KeyPressed:LEFT");
			moves++;
		left = true;
		if ( !right ) { left=true; }
		else { left = false ;
		       right = true;  
		} 
		up = false ;
		down = false ;
		}


		if(  !gameover && e.getKeyCode() == KeyEvent.VK_UP ){

			System.out.println("Gameplay:KeyPressed:UP");
			moves++;
		up = true;
		if ( !down ) { up=true; }
		else { up = false ;
		       down = true;  
		} 
		left = false ;
		right = false ;
		}


		if(  !gameover && e.getKeyCode() == KeyEvent.VK_DOWN ){

			System.out.println("Gameplay:KeyPressed:DOWN");
			moves++;
		down = true;
		if ( !up ) { down=true; }
		else {  down = false ;
		       up = true;  
		} 
		left = false ;
		right = false ;
		}
		

		System.out.println("Gameplay:KeyPressed:end");
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
