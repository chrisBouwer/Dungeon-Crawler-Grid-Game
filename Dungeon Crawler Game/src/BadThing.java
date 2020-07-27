//Christiaan Bouwer
public class BadThing implements GameObject {
	
    private Location location;
    private Game game;
    private Grid grid;
	
  public void init(Game game, Grid grid, Location location) {
    // Initialize the player object with a reference to the game, grid and the 
    // player's starting location.
	  this.game = game;
      this.location = location;
      this.grid = grid;
  }
  
  public GameObject collision(GameObject otherObject) {
    // What does the object do if it collides with another object? Return the object
    // that remains if these two objects collide. Note that you can use the "instanceof" keyword
    // to test whether otherObject is of a given type.
	  /*
	   * When a collision occurs then the game over screen is displayed
	   */
	  GameObject colls = this;
      if (otherObject instanceof Player) {
          Game.globalVarGame.signalGameOver();
          colls = otherObject;
      }
      return colls;
  }
  
  public void draw(double centerX, double centerY, double cellSize) {
    // Draw the object at location centerX, centerY with 
    // width/height of cellSize by cellSize. You can use avoid.gif as a placeholder
    // image for this.
	  /*
	   * Adds the image of the bad thing(missile)
	   */
	  StdDraw.picture(centerX*cellSize + (cellSize/2), centerY*cellSize + (cellSize), "floor.jpg",cellSize,cellSize);
	  StdDraw.picture(centerX*cellSize + (cellSize/2), centerY*cellSize + (cellSize), "sasuke3.gif",cellSize,cellSize);
  }

  public Location move() {
    // Update the position of this object, and return the new position. This should
    // move the object to the left.
    // It is recommended that you move the object every 300ms as an initial choice.
	  /*
	   * Moves the bad thing to the left every 300ms
	   */

	  
	  if (Game.globalVarGame.getTimeElapsed() % 100 == 0) {
	      int x = location.getRow(), y = location.getCol();
	      Location initialLocat = new Location(location.getRow(), location.getCol());
	      System.out.println("BadLocat: row:"+x+" col:"+y);
		  
	      int direction = (int)(Math.random()*4)+1;
          if(direction==1)
        	  y++;
          if(direction==2)
        	  x++;
          if(direction==3)
        	  y--;
          if(direction==4)
        	  x--;
          
	      Location locat = new Location(x, y);
          
          if (grid.isValid(locat) == true && grid.getObjectAt(locat) instanceof Floor) {
              location = locat;
          }
          else{
        	  location = initialLocat;
          }
          
      }
      return location;
  }

  public void action() {
	System.out.print("Bad Action row:"+location.getRow()+" col:"+location.getCol());
	if(grid.getObjectAt(new Location(location.getRow()+1, location.getCol())) instanceof Player);{
	System.out.print(" Down-"+"row:"+(location.getRow()+1)+"-col:"+(location.getCol()));}
	if(grid.getObjectAt(new Location(location.getRow()-1, location.getCol())) instanceof Player);{
	System.out.print(" Up ");}
	if(grid.getObjectAt(new Location(location.getRow(), location.getCol()+1)) instanceof Player);{
	System.out.print(" Right ");}
	if(grid.getObjectAt(new Location(location.getRow(), location.getCol()-1)) instanceof Player);{
	System.out.print(" Left ");}
	//Game.globalVarGame.signalGameOver();
  }
}