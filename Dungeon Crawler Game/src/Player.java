//Christiaan Bouwer
import java.awt.event.KeyEvent;

public class Player implements GameObject {
	
	private Game game;
	private Grid grid;
	private Location location;
    private int row, col;
	
  public void init(Game game, Grid grid, Location location) {
    // Initialize the player object with a reference to the game, grid and the 
    // player's starting location.
	  this.game = game;
      this.grid = grid;
      this.location = location;
      row = location.getRow();
	  col = location.getCol();
  }
  
  public GameObject collision(GameObject otherObject) {
    // Handle a player object's collision with another object. Return the object that
    // remains if this collision occurs. Note that you can use the "instanceof" keyword
    // to test whether otherObject is of a given type.
	  /*
	   * if statement to check what happens when the player collides with an object
	   */
	  
	  GoodThing goodThing = new GoodThing();
	  Wall wall = new Wall();

	  if (otherObject instanceof GoodThing){
		  Game.globalVarGame.adjustScore(1);
		  return otherObject;
    	}

        
      if(otherObject instanceof BadThing)
        Game.globalVarGame.signalGameOver();
      if(otherObject instanceof Wall){
		  	otherObject = wall;
	  		this.init(game, grid, location);
	  	    grid.setObjectAt(location, this);
      	}
    	//otherObject = locat;
      	//set Player back to initial position
      
      return otherObject;
  }
  
  public Location move() {
    // Calculate the player's new location. Here, you should handle
    // keyboard input from the player. It is suggested that you update
    // the player's location every 100ms.
	  /*
	   * Moves the player according to the input from the user
	   */
	  /*@@@if (Game.globalVarGame.getTimeElapsed() % 100 == 0) {
          int x = location.getCol(), y = location.getRow();
          
          if (StdDraw.isKeyPressed(KeyEvent.VK_W)) {
              y++;
          } else 
        	  if (StdDraw.isKeyPressed(KeyEvent.VK_S)) {
        		  y--;
          }
          
          Location locat = new Location(y, x);
          
          if (grids.isValid(locat) == true) {
              location = locat;
          }
      }
      return location;@@@*/
	  
		  int x = location.getRow(), y = location.getCol();
	      
	      if (StdDraw.isKeyPressed(KeyEvent.VK_W))
	          x++;
	      if (StdDraw.isKeyPressed(KeyEvent.VK_S))
	    	  x--;
	      if(StdDraw.isKeyPressed(KeyEvent.VK_A))
	    	  y--;
	      if(StdDraw.isKeyPressed(KeyEvent.VK_D))
	    	  y++;
	      
	      Location locat = new Location(x, y);
	      
	      if (grid.isValid(locat) == true && grid.getObjectAt(locat) instanceof Floor) {
	          location = locat;
	      }

      return location;
	  
  }
  
  public void draw(double centerX, double centerY, double cellSize) {
    // Draw the player at location x and y, with width/height of cellSize.
    // You can use user.gif as a test image for this.
	  StdDraw.picture(centerX*cellSize + (cellSize/2), centerY*cellSize + (cellSize), "floor.jpg",cellSize,cellSize);
	  StdDraw.picture(centerX*cellSize + (cellSize/2), centerY*cellSize + (cellSize), "GifPlayer.gif-c200",cellSize,cellSize);
	  //StdDraw.picture(centerX*cellSize + (cellSize/2), centerY*cellSize + (cellSize), "Shark.png");
  }

  public void action() {
	// TODO Auto-generated method stub
	  if(StdDraw.isKeyPressed(KeyEvent.VK_SPACE)==true){
		  System.out.println("works!!!!!!!!!!!!!!!");
		  
//	      Location topLocat = new Location(location.getCol(), location.getRow()-1);
//	      BadThing badThing = new BadThing();
//		  if(grid.isValid(topLocat) == true && topLocat.equals(badThing))
//		  {
//			  System.out.println("works");
//		  }
		  
	  }
	
  }
}