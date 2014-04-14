package batalha.model.entidade;

import java.util.Arrays;


public abstract class Ship implements IShip {
	
	private Coordinate[] coordinates;
	private int size = 0;
	
	public Ship(int size) {
		this.size = size;
		this.coordinates = new Coordinate[size];
	}
	
	@Override
	public boolean isLocated(final Coordinate coordinate) {
		if (this.coordinates == null) {
			return false;
		}
		
		if (coordinate == null) {
			return false;
		}
		
		for (Coordinate c : this.coordinates) {
			if (coordinate.equalsCoordinate(c)) {
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public boolean hit(final Coordinate coordinate) {
		if (this.coordinates == null) {
			return false;
		}
		
		if (coordinate == null) {
			return false;
		}
		
		for (Coordinate c : this.coordinates) {
			if (coordinate.equalsCoordinate(c) && !c.isHit()) {
				c.setHit(Boolean.TRUE);
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public boolean buildShip(final int startLine, final int startColumn, final Axis axis) {
		if (this.coordinates == null) {
			return false;
		}
		
		if (startLine > Board.BOARD_LINE_LIMIT) {
			return false;
		}
		
		if (startColumn > Board.BOARD_COLUMN_LIMIT) {
			return false;
		}
		
		if (axis == Axis.HORIZONTAL) {
			if (startColumn + this.size > Board.BOARD_COLUMN_LIMIT) {
				return false;
			}
			
			for (int i = 0; i < this.size; i++) {
				this.coordinates[i] = new Coordinate(startLine, (startColumn + i));
			}
		} else {
			if (startLine + this.size > Board.BOARD_LINE_LIMIT) {
				return false;
			}
			
			for (int i = 0; i < this.size; i++) {
				this.coordinates[i] = new Coordinate((startLine + i), startColumn);
			}
		}
		
		return true;
	}
	
	@Override
	public Coordinate[] getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Coordinate[] coordinates) {
		this.coordinates = coordinates;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(coordinates);
		result = prime * result + size;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ship other = (Ship) obj;
		if (!Arrays.equals(coordinates, other.coordinates))
			return false;
		if (size != other.size)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Ship [coordinates=" + Arrays.toString(coordinates) + ", size=" + size + ", getClass()=" + getClass() + "]";
	}
	
	@Override
	public boolean isDestroyed() {
		for (Coordinate coordinate : this.coordinates) {
			if (!coordinate.isHit()) {
				return false;
			}
		}
		return true;
	}
	
}
