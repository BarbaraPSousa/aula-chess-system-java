/*Peça Rainha*/

package Chess.pieces;

import Chess.ChessPiece;
import Chess.Color;
import boardgame.Board;
import boardgame.Position;

public class Queen extends ChessPiece{

	public Queen(Board board, Color color) {
		super(board, color);
	}
	
	@Override//
	public boolean[][] possibleMoves() {////implementando o movimento da Rainha, a pode mover como o bispo e como a torre
		
		boolean[][]mat = new boolean[getBoard().getRow()][getBoard().getColumns()];
		
		Position p = new Position(0, 0);
		
		//above
		p.seteValue(position.getRow() -1,position.getColumn());
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setRow(p.getRow() - 1);
		}
		if(getBoard().positionExists(p) && isTherOppanentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true; 
		}
		//left
		p.seteValue(position.getRow(), position.getColumn() - 1);
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setColumn(p.getColumn() - 1);
		}
		if(getBoard().positionExists(p) && isTherOppanentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true; 
		}
		// right
		p.seteValue(position.getRow(), position.getColumn() + 1);
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setColumn(p.getColumn() + 1);
		}
		if (getBoard().positionExists(p) && isTherOppanentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		//below
		p.seteValue(position.getRow() + 1, position.getColumn());
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {																				
			mat[p.getRow()][p.getColumn()] = true;
			p.setRow(p.getRow() + 1);
		}
		if (getBoard().positionExists(p) && isTherOppanentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		// nw
		p.seteValue(position.getRow() - 1, position.getColumn() - 1);
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.seteValue(p.getRow() - 1, p.getColumn() - 1);
		}
		if (getBoard().positionExists(p) && isTherOppanentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// ne
		p.seteValue(position.getRow() - 1, position.getColumn() + 1);
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.seteValue(p.getRow() - 1, p.getColumn() + 1);
		}
		if (getBoard().positionExists(p) && isTherOppanentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// se
		p.seteValue(position.getRow() + 1, position.getColumn() + 1);
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.seteValue(p.getRow() + 1, p.getColumn() + 1);
		}
		if (getBoard().positionExists(p) && isTherOppanentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// sw
		p.seteValue(position.getRow() + 1, position.getColumn() - 1);
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.seteValue(p.getRow() + 1, p.getColumn() - 1);
		}
		if (getBoard().positionExists(p) && isTherOppanentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		return mat;
	}

	@Override
	public String toString() {
		return "Q";
	}
}
