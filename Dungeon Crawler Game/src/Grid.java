//Christiaan Bouwer
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Queue;
import java.util.Scanner;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;

public class Grid {
	// Add instance variables.
	private Game game;
	private int cellSize;
	private int NumberRows;
	private int NumberCols;
	private GameObject grid[][];
	private BFSnode node[][];

	public Grid(int numRows, int numCols, int gridCellSize) {
		// Initialize a net grid with the given dimensions (rows by columns).
		// Each cell
		// in the grid should be draw with width and height of gridCellSize on
		// the
		// screen. Calls reset() to reset the state of the grid to what it
		// should be at
		// the start of the game.
		/*
		 * Initializes the grid with the specific dimensions that it receives
		 * Then resets the grid to the default
		 */
		this.cellSize = gridCellSize;
		this.NumberRows = numRows;
		this.NumberCols = numCols;
		grid = new GameObject[numRows][numCols];
		reset();
	}

	public void populateGird(String level){
	  try{
		  Scanner scanFile = new Scanner(new File(level));
		  Scanner scanLine = new Scanner(scanFile.nextLine()).useDelimiter(";");
		  NumberRows = scanLine.nextInt();
		  NumberCols = scanLine.nextInt();
		  cellSize = scanLine.nextInt();
		  System.out.println("numb row:"+NumberRows+" numb col:"+NumberCols+" cell size:"+cellSize);
		  
		  grid = new GameObject[NumberRows][NumberCols];
		  
	  while(scanFile.hasNext()){
		  scanLine = new Scanner(scanFile.nextLine()).useDelimiter(";");
		  System.out.println("Object Line");//
		  int tempRow = scanLine.nextInt();
		  int tempCol = scanLine.nextInt();
		  String obj = scanLine.next();
		  System.out.println("row:"+tempRow+" col:"+tempCol+" obj:"+obj);//
		  if(obj.equals("G")){
			  System.out.println("goodThing");//
			  GoodThing goodThing = new GoodThing();
			  goodThing.init(game, this, new Location(tempRow, tempCol));
              setObjectAt(new Location(tempRow, tempCol), goodThing);
		  }
		  if(obj.equals("B")){
			  System.out.println("badThing");//
			  BadThing badThing = new BadThing();
			  badThing.init(game, this, new Location(tempRow, tempCol));
              setObjectAt(new Location(tempRow, tempCol), badThing);
		  }
		  if(obj.equals("P")){
			  System.out.println("player");//
			  Player play = new Player();
			  play.init(game, this, new Location(tempRow, tempCol));
              setObjectAt(new Location(tempRow, tempCol), play);
		  }
		  if(obj.equals("W")){
			  System.out.println("wall");//
			  Wall wall = new Wall();
			  wall.init(game, this, new Location(tempRow, tempCol));
              setObjectAt(new Location(tempRow, tempCol), wall);
		  }
		  if(obj.equals("F")){
			  System.out.println("floor");//
			  Floor floor = new Floor();
			  floor.init(game, this, new Location(tempRow, tempCol));
              setObjectAt(new Location(tempRow, tempCol), floor);
		  }
	  }
	  }
	  catch (FileNotFoundException e) {
          System.out.println("File not found" + e);
      }
	  
	  
  }

	public void reset() {
		// Set up an initial grid of game objects. This could be just an empty
		// grid, but
		// this depends on how you want your game to start off initially.
		// This can be called from outside to reset the board at the start of
		// play after
		// a game over condition has been reached.
		// Remember to include a Player object on this new grid, otherwise you
		// won't
		// have anything to control in the game!
		
		 //Set the default grid with the player object
		 
		for (int i = 0; i < getNumRows(); i++) {
			for (int j = 0; j < getNumCols(); j++) {
				grid[i][j] = null;
			}
		}

		Player player = new Player();
		player.init(game, this, new Location(0, 0));
		setObjectAt(new Location(0, 0), player);

	}

	public void populateGrid() {
		// Place new objects at the rightmost edge of the grid. This function
		// depends
		// entirely on you, but make sure that this results in interesting
		// gameplay.
		
		//This method is used when the objects are randomly generated
		int newX = (int) (Math.random() * NumberRows);
		int newY = (int) (Math.random() * NumberCols);
		int amount = (int) (Math.random() * 10);
		 System.out.println("row: " + newY+" col: "+newX+" amount: "+amount);//

		if (amount < 3) {
			GoodThing goodThing = new GoodThing();
			goodThing.init(game, this, new Location(newX, newY));
			setObjectAt(new Location(newX, newY), goodThing);
		} else if (amount > 2) {
			BadThing badThing = new BadThing();
			badThing.init(game, this, new Location(newX, newY));
			setObjectAt(new Location(newX, newY), badThing);
		}
	}

	public int getNumRows() {
		// Return the number of rows in the grid.
		return NumberRows;
	}

	public int getNumCols() {
		// Return the number of columns in the grid.
		return NumberCols;
	}

	public GameObject getObjectAt(Location loc) {
		// Return the object at location loc.
		return grid[loc.getRow()][loc.getCol()];
	}

	public void setObjectAt(Location loc, GameObject obj) {
		// Set the object at location loc.
		grid[loc.getRow()][loc.getCol()] = obj;
	}

	public int getCellSize() {
		// Return the cell size of the grid.
		return cellSize;
	}

	public void moveAll() {
		// Ask each object in the grid what its new location should be. Make a
		// new array
		// representing the objects in the grid at their new locations. If two
		// objects
		// ask to be moved to the same location, resolve this by calling the
		// collision
		// method on one of them and use the result of that in the new grid's
		// corresponding location.
		/*
		 * Create a 2D array with nested for loops used to move the objects to
		 * the left one step
		 */
		//Checks for the player object to set the location into variables
		int rowOfPlayer = 0, colOfPlayer = 0;
		for (int i = 0; i < getNumRows(); i++) {
			for (int j = 0; j < getNumCols(); j++) {
				if(grid[i][j] instanceof Player){
					System.out.println("PLAYER DRAWN(moveALL) row:"+i+" col:"+j);//
					rowOfPlayer = i;
					colOfPlayer = j;
				}
			}
		}
		//Initiates a new player location when the player has moved
		Location newLocation = grid[rowOfPlayer][colOfPlayer].move();
		int tempRow = newLocation.getRow(), tempCol = newLocation.getCol();
		//sets the position of the player to floor object
		grid[rowOfPlayer][colOfPlayer] = new Floor();
		//sets the new location to the player object
		grid[tempRow][tempCol] = new Player();
		//runs though the gird and moves the object and sets it to the new location
		for(int i = 0; i<getNumRows(); i++){
			for(int j = 0; j<getNumCols(); j++){
				int rowOfObject = 0,colOfObject = 0;
				if(grid[i][j] != null && !(grid[i][j] instanceof Player)){
					GameObject object = this.getObjectAt(new Location(i,j));
					rowOfObject = i;
					colOfObject = j;
					Location newLocationObj = grid[i][j].move();
					int newObjectRow = newLocationObj.getRow(),newObjectCol = newLocationObj.getCol();
					grid[rowOfObject][colOfObject] = new Floor();
					grid[newObjectRow][newObjectCol] = object;
				}
			}
		}
		//Initiates the new objects
		for(int i = 0; i<getNumRows(); i++){
			for(int j = 0; j<getNumCols(); j++){
				if(grid[i][j] != null){
					grid[i][j].init(game, this, new Location(i,j));
				}
			}
		}
	}

	public void draw(int atX, int atY) {
		// Draw every object in the grid, going through every row and column.
		// The origin
		// of the grid should be at drawAtX and drawAtY. You must delegate each
		// object's
		// painting the object itself.
		
		//Checks for the player object to set the location into variables
		int rowOfPlayer = 0, colOfPlayer = 0;
		for (int i = 0; i < getNumRows(); i++) {
			for (int j = 0; j < getNumCols(); j++) {
				if(grid[i][j] instanceof Player){
					System.out.println("PLAYER DRAWN(draw) row:"+i+" col:"+j);
					rowOfPlayer = i;
					colOfPlayer = j;
				}
			}
		}
		//Draws the player in the gird at the center
		grid[rowOfPlayer][colOfPlayer].draw(4, 4, cellSize);
		StdDraw.setPenColor(StdDraw.BLACK);
		//Checks each space next to the player then draws it accordingly 
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if(isValid(new Location(rowOfPlayer-i, colOfPlayer-j))){
					if(grid[rowOfPlayer-i][colOfPlayer-j] != null){
						grid[rowOfPlayer-i][colOfPlayer-j].draw(4-j, 4-i, cellSize);
					}
				}
				if(isValid(new Location(rowOfPlayer-i, colOfPlayer+j))){
					if(grid[rowOfPlayer-i][colOfPlayer+j] != null){
						grid[rowOfPlayer-i][colOfPlayer+j].draw(4+j, 4-i, cellSize);
					}
				}
				if(isValid(new Location(rowOfPlayer+i, colOfPlayer+j))){
					if(grid[rowOfPlayer+i][colOfPlayer+j] != null){
						grid[rowOfPlayer+i][colOfPlayer+j].draw(4+j, 4+i, cellSize);
					}
				}
				if(isValid(new Location(rowOfPlayer+i, colOfPlayer-j))){
					if(grid[rowOfPlayer+i][colOfPlayer-j] != null){
						grid[rowOfPlayer+i][colOfPlayer-j].draw(4-j, 4+i, cellSize);
					}
				}
			}
		}	
	}

	public boolean isValid(Location loc) {
		// Returns true if the location Loc is on the grid, otherwise it returns
		// false.
		//Checks weather or not the location is out of bounds
		if (loc.getRow() >= getNumRows() || loc.getCol() >= getNumCols() || loc.getCol() < 0 || loc.getRow() < 0) {
			return false;
		} else {
			return true;
		}
	}
	
	public void resolveAllActions(){
		
		//For loop to run through every position and if a player objects there it calls the action method
		for(int i = 0; i < getNumRows(); i++){
			for(int j = 0; j < getNumCols(); j++){
				if(grid[i][j] instanceof Player){
					System.out.println("Player found: @ "+i + j);
					grid[i][j].action();
				}
			}
		}
		//For loop to run through every position and if it isn't empty or a player there it calls the action method
		for(int i = 0; i < getNumRows(); i++){
			for(int j = 0; j < getNumCols(); j++){
				if(grid[i][j] != null && !(grid[i][j] instanceof Player)){
					System.out.println("NonPlayer found: @ "+i+j);
					grid[i][j].action();
				}
			}
		}
	}
	
	/*public void updatePathToPlayer(){
		//Initiates the node array
		node = new BFSnode[NumberRows][NumberCols];
		//Loads the arry full of nodes
	      for(int i = 0; i < getNumRows(); i++){
	          for(int j = 0; j < getNumCols(); j++){
	              node[i][j] = new BFSnode();
	          }
	      }
		//Initiates the location of the player
		Location locatOfPlayer = null;
		//Runs through gird and if its a wall or player the node is set to true 
		//and the location of the player is set to that position 
		for(int i=0; i<NumberRows; i++){
			for(int j=0; j<NumberCols; j++){
				if(grid[i][j] instanceof Wall){
					node[i][j].setMark(true);
				}
				else if(grid[i][j] instanceof Player){
	                  node[i][j].setMark(true);
	                  locatOfPlayer = new Location(i,j);
	                  
	              }
					
				}
			}
		
	
	//Create a queue of the nodes
		Queue<BFSnode> nodeQueue = new Queue<BFSnode>();
		nodeQueue.enqueue(node[locatOfPlayer.getRow()][locatOfPlayer.getCol()]);
	      for(int i = -1; i <= 1; i++){
	          for(int j = -1; j <= 1; j++){
	        	  if((node[locatOfPlayer.getRow()+i][locatOfPlayer.getCol()+j].getMark()) == false){
	        		  nodeQueue.enqueue(node[locatOfPlayer.getRow()+i][locatOfPlayer.getCol()+j]);
	                  node[locatOfPlayer.getRow()+i][locatOfPlayer.getCol()+j].setMark(true);
	                  node[locatOfPlayer.getRow()+i][locatOfPlayer.getCol()+j].setPrev(node[locatOfPlayer.getRow()][locatOfPlayer.getCol()]);
	        	  }
	          }
	      }
	}*/
}
	