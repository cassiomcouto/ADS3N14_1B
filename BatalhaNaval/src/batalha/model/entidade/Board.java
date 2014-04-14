package batalha.model.entidade;

public class Board implements IBoard {
	private static final int INITIAL_AVAILABLE_SHOTS = 15;
	
	public static String[] HEADER_COLUMNS = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J" };
	public static final int BOARD_COLUMN_LIMIT = 10;
	public static final int BOARD_LINE_LIMIT = 10;
	
	private int hits, errors, shots;
	
	private int[][] board;

	public Board() {
		reset();
	}

	@Override
	public int getHits() {
		return hits;
	}

	public void setHits(int hits) {
		this.hits = hits;
	}

	@Override
	public int getErrors() {
		return errors;
	}

	public void setErrors(int errors) {
		this.errors = errors;
	}

	@Override
	public int getShots() {
		return shots;
	}

	public void setShots(int shots) {
		this.shots = shots;
	}

	public void setBoard(int[][] board) {
		this.board = board;

	}

	@Override
	public int[][] getBoard() {
		return board;
	}

	@Override
	public void reset() {
		init(); 

		for (int line = 0; line < Board.BOARD_LINE_LIMIT; line++) {
			for (int column = 0; column < Board.BOARD_COLUMN_LIMIT; column++) {
				this.board[line][column] = -1;
			}
		}
	}

	@Override
	public void addShots(int shots) {
		this.shots += shots;
	}
	
	@Override
	public void decrementShots() {
		this.shots--;
	}

	@Override
	public void increaseHits() {
		this.hits++;
	}

	@Override
	public void increaseErrors() {
		this.errors++;
	}

	private void init() {
		hits = 0;
		errors = 0;
		shots = Board.INITIAL_AVAILABLE_SHOTS;
		board = new int[BOARD_LINE_LIMIT][BOARD_COLUMN_LIMIT];
	}
	
}
