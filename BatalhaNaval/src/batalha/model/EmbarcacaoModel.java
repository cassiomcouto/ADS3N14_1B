package batalha.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import batalha.model.entidade.AircraftCarrier;
import batalha.model.entidade.Axis;
import batalha.model.entidade.Board;
import batalha.model.entidade.Coordinate;
import batalha.model.entidade.Destroyer;
import batalha.model.entidade.Frigate;
import batalha.model.entidade.IBoard;
import batalha.model.entidade.IShip;
import batalha.model.entidade.Submarine;
import batalha.model.entidade.TorpedoBoat;
import batalha.utils.RandomEnumUtils;
import batalha.utils.StringUtils;

public class EmbarcacaoModel {

	private static final int TOTAL_SHIPS = 5;

	private static final int BONUS_SHOTS = 5;
	
	private IBoard board;
	
	private IShip[] ships;
	
	private List<Coordinate> moves;

	public EmbarcacaoModel() throws Exception {
		this.iniciaJogo();
	}
	
	private void buildShipsOnTheBoard() throws Exception {
		if (this.ships == null) {
			return;
		}
		
		for (IShip ship : this.ships) {
			this.buildShipUntilIsUniqueLocalized(ship);
			System.out.println("Ship Localized: " + ship.toString());
		}
	}

	private void buildShipUntilIsUniqueLocalized(final IShip ship) throws Exception {
		boolean buildShip = ship.buildShip((new Random()).nextInt(Board.BOARD_LINE_LIMIT), (new Random()).nextInt(Board.BOARD_COLUMN_LIMIT), RandomEnumUtils.random(Axis.class));
		if ((buildShip && this.isAlreadyLocalizedInThisPlace(ship)) || !buildShip) { 
			buildShipUntilIsUniqueLocalized(ship);
		}
	}

	private boolean isAlreadyLocalizedInThisPlace(IShip ship) {
		for (Coordinate coordinate : ship.getCoordinates()) {
			for (IShip otherShip : this.ships) {
				if (!ship.equals(otherShip) && otherShip.isLocated(coordinate)) {
					return true;
				}
			}
		}
		return false;
	}

	private IShip[] getShips() {
		IShip[] ships = new IShip[TOTAL_SHIPS];
		
		ships[0] = new Submarine();
		ships[1] = new TorpedoBoat();
		ships[2] = new Destroyer();
		ships[3] = new Frigate();
		ships[4] = new AircraftCarrier();
		
		return ships;
	}


	private int[][] iniciaJogo() throws Exception {
		this.board = new Board();
		
		this.ships = this.getShips();
		
		this.buildShipsOnTheBoard();
		
		this.moves = new ArrayList<Coordinate>();
		
		return this.board.getBoard();
	}
	
	private Coordinate jogada(int line, String column) {
		int indexLine = line;
		int indexColumn = Character.isDigit(column.charAt(0)) ? Integer.valueOf(String.valueOf(column.charAt(0))) : StringUtils.toAlphabeticNumber(String.valueOf(column.charAt(0))) - 1;
		
		final Coordinate coordinate = new Coordinate(indexLine, indexColumn);
		this.getMoves().add(coordinate);
		return coordinate;
	}
	
	public boolean testaJogada(int line, String column) {
		final Coordinate coordinate = jogada(line, column);
		
		for (IShip ship : this.ships) {
			
			if (ship.hit(coordinate)) {
				this.board.addShots(BONUS_SHOTS);
				this.board.increaseHits();
				coordinate.setHit(Boolean.TRUE);
				return true;
			}
		}
		
		this.board.decrementShots();
		this.board.increaseErrors();
		return false;
	}

	public boolean testaFim() {
		for (IShip ship : this.ships) {
			if (!ship.isDestroyed()) {
				return false;
			}
		}

		return true;
	}


	public int hits() {
		return this.board.getHits();
	}

	public int errors() {
		return this.board.getErrors();
	}


	public int shots() {
		return this.board.getShots();
	}


	public int[][] atualizaTabuleiro() {
		final Coordinate move = this.getLastMove();
		
		this.board.getBoard()[move.getLine()][move.getColumn()] = move.isHit() ? 1 : 0;
		
		return this.board.getBoard();
	}

	public Coordinate getLastMove() {
		return this.moves.get(this.moves.size() - 1);
	}

	public int[][] getBoard() {
		return this.board.getBoard();
	}

	public List<Coordinate> getMoves() {
		return moves;
	}
}
