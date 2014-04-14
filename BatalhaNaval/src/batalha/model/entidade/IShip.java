package batalha.model.entidade;

public interface IShip {
	
	boolean hit(Coordinate coordinate);
	
	boolean isLocated(Coordinate coordinate);
	
	Coordinate[] getCoordinates();
	
	boolean buildShip(int startLine, int startColumn, Axis axis);
	
	boolean isDestroyed();
}
