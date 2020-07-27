//Christiaan Bouwer
import java.awt.event.KeyEvent;
import java.awt.Font;

import javax.swing.JOptionPane;

public class Game
{
  public static Game globalVarGame;
  private Grid grid;
  private GameObject gObj;
  private boolean gameOver;
  private int xRes;
  private int yRes;
  private long msElapsed;
  private long score;
  private StdAudio audio;
  
  
  
  
  
  public Game()
  {
	Game.globalVarGame = this;
    grid = new Grid(6, 10, 64);
    xRes = grid.getNumCols() * grid.getCellSize();
    yRes = (grid.getNumRows() + 1) * grid.getCellSize();
    StdDraw.setCanvasSize(xRes,yRes);
    StdDraw.setXscale(0, xRes);
    StdDraw.setYscale(0, yRes);
  }
  
  public void reset() {
    // Reset the game to its initial state.
	/*
	 * Resets all of the variable to default
	 */
    gameOver = false;
    msElapsed = 0;
    score = 0;
    grid.reset();
  }
  
  public void play()
  {
	  /*
	   * While the game isn't over, the board is created and set to the 
	   * specifications. Then ever 300ms the board is populated from the right.
	   */
	  //for(int i=0; i<10; i++)
		  //grid.populateGrid();
	//grid.populateGird("Level1.txt");
	grid.populateGird("Level1.txt");
	
	//audio = new StdAudio();
	//audio.loop("soundtrack.wav");
	
	  
    while (!isGameOver())
    {		
      StdDraw.clear(StdDraw.BLACK);
      
      grid.draw(0, 10);
      grid.moveAll();
      grid.resolveAllActions();
      
      StdDraw.setPenColor(StdDraw.WHITE);
      StdDraw.text(70, 10, "Score " + getScore() + " [Q to quit]");
      StdDraw.show(20);
      
      if(StdDraw.isKeyPressed(KeyEvent.VK_Q) == true)
      {
    	  System.exit(0);
      }

      msElapsed += 20;
    }
  }
  
  public long getScore()
  {
    return score;
  }
  
  public void adjustScore(long scoreDifference)
  {
    score = score + scoreDifference;
  }
  
  public long getTimeElapsed() {
    return msElapsed;
  }
  
  public boolean isGameOver()
  {
    return gameOver;
  }
  
  public void signalGameOver()
  {
    gameOver = true;
  }
  
  public void titleScreen() {
    // Insert code to show the title screen and await a keypress from the player
    // to start the game.
	  	/*
		 * The title screen is displayed until Enter is pressed then displaying the board
		 */
	  while(StdDraw.isKeyPressed(KeyEvent.VK_ENTER) == false){
          StdDraw.text(xRes/2, yRes/2, "Press Enter to play");
      }
  }
 
  
  public void gameOverScreen() {
    // Insert code to show the "game over" screen, and await a keypress from the
    // player before the game is restarted.
	  /*
		 * The game over screen is displayed until Enter is pressed then displaying the board again
		 */
	  JOptionPane.showMessageDialog(null, "DEFEAT GG! \n" +"Your Score " + getScore() + "\nEnter to respawn");
  }

  
  public static void test()
  {
	/*
	 * This runs the game in this specific way
	 */
    Game game = new Game();
    while (true) {
      game.titleScreen();    
      game.play();
      game.gameOverScreen();
      game.reset();
    }
  }
  
  public static void main(String[] args)
  {
    test();
  }
}