package batalha.model.entidade;

public interface IBoard {
	
	void reset();
	
	void addShots(int shots);
	
	void decrementShots();
	
	void increaseHits();
	void increaseErrors();

	int getHits();

	int getErrors();

	int getShots();

	int[][] getBoard();
	
}
