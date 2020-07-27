//Christiaan Bouwer
public class Wall implements GameObject {

    private Location location;
    private Game game;
    private Grid grid;
    private int row, col;
	
	@Override
	public void init(Game game, Grid grid, Location location) {
		this.game = game;
	    this.grid = grid;
	    this.location = location;
	    row = location.getRow();
	    col = location.getCol();
		
	}

	@Override
	public GameObject collision(GameObject otherObject) {
//		GameObject collis = this;
//		if (otherObject instanceof Player)
//			collis = otherObject;
//		  
//	    return collis;
		return null;
	}

	@Override
	public Location move() {
		// TODO Auto-generated method stub
		return new Location(row, col);
	}

	@Override
	public void draw(double centerX, double centerY, double cellSize) {
		StdDraw.picture(centerX*cellSize + (cellSize/2), centerY*cellSize + (cellSize), "wall.jpg",cellSize,cellSize);
		
	}

	@Override
	public void action() {
		// TODO Auto-generated method stub
		
	}

}
