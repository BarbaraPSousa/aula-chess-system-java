/*Peça Cavalo*/

package Chess.pieces;

import Chess.ChessPiece;
import Chess.Color;
import boardgame.Board;
import boardgame.Position;

public class Knight extends ChessPiece {

	public Knight(Board board, Color color) {
		super(board, color);
	}	
	
	private boolean canMove(Position position) {//metodo para verificar se cavalo pode move p/ determinado lugar
		ChessPiece p = (ChessPiece)getBoard().piece(position);
		return p == null || p.getColor() != getColor();
	}

	@Override
	public boolean[][] possibleMoves() {//metodo de movimento do cavalo, o mesmo anda 2 1		
		boolean[][]mat = new boolean[getBoard().getRow()][getBoard().getColumns()];
		
		Position p = new Position(0,0);
		
		p.seteValue(position.getRow() -1, position.getColumn()-2);
		if(getBoard().positionExists(p) && canMove(p) ) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		p.seteValue(position.getRow() -2, position.getColumn() -1);
		if(getBoard().positionExists(p) && canMove(p) ) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		p.seteValue(position.getRow() -2, position.getColumn() +1);
		if(getBoard().positionExists(p) && canMove(p) ) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		p.seteValue(position.getRow() -1, position.getColumn() +2);
		if(getBoard().positionExists(p) && canMove(p) ) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		p.seteValue(position.getRow() +1, position.getColumn() +2);
		if(getBoard().positionExists(p) && canMove(p) ) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		p.seteValue(position.getRow() +2, position.getColumn() +1);
		if(getBoard().positionExists(p) && canMove(p) ) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		p.seteValue(position.getRow() +2, position.getColumn() -1);
		if(getBoard().positionExists(p) && canMove(p) ) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		p.seteValue(position.getRow() +1, position.getColumn() -2);
		if(getBoard().positionExists(p) && canMove(p) ) {
			mat[p.getRow()][p.getColumn()] = true;
		}		
		return mat;
	}

	@Override
	public String toString() {
		return "N";
	}
}
