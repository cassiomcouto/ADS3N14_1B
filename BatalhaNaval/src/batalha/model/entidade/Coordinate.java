package batalha.model.entidade;

public class Coordinate {
	
	private int line;
	private int column;
	private boolean hit;
	
	public Coordinate(int line, int column) {
		this.line = line;
		this.column = column;
		this.hit = Boolean.FALSE;
	}
	
	public int getLine() {
		return line;
	}
	
	public void setLine(int line) {
		this.line = line;
	}
	
	public int getColumn() {
		return column;
	}
	
	public void setColumn(int column) {
		this.column = column;
	}
	
	public boolean isHit() {
		return hit;
	}
	
	public void setHit(boolean hit) {
		this.hit = hit;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + column;
		result = prime * result + (hit ? 1231 : 1237);
		result = prime * result + line;
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
		Coordinate other = (Coordinate) obj;
		if (column != other.column)
			return false;
		if (hit != other.hit)
			return false;
		if (line != other.line)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Coordenada [line=" + line + ", column=" + column + ", hit=" + hit + "]";
	}
	
	public boolean equalsCoordinate(final Coordinate obj) {
		if (obj == null) {
			return false;
		}
		
		if (column != obj.column) {
			return false;
		}
		
		if (line != obj.line) {
			return false;
		}
		
		return true;
	}
}
