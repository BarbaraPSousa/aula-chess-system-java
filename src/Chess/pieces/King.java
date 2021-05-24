/*Peça Rei*/

package Chess.pieces;

import Chess.ChessPiece;
import Chess.Color;
import boardgame.Board;
import boardgame.Position;

public class King extends ChessPiece {

	public King(Board board, Color color) {
		super(board, color);
	}	
	
	private boolean canMove(Position position) {//metodo para verificar se o rei pode move p/ determ
		ChessPiece p = (ChessPiece)getBoard().piece(position);
		return p == null || p.getColor() != getColor();
	}

	@Override
	public boolean[][] possibleMoves() {//movimento rei	basico 			
		boolean[][]mat = new boolean[getBoard().getRow()][getBoard().getColumns()];
		
		Position p = new Position(0,0);
		
		//above
		p.seteValue(position.getRow() -1, position.getColumn());
		if(getBoard().positionExists(p) && canMove(p) ) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//below
		p.seteValue(position.getRow() +1, position.getColumn());
		if(getBoard().positionExists(p) && canMove(p) ) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//left
		p.seteValue(position.getRow(), position.getColumn() -1);
		if(getBoard().positionExists(p) && canMove(p) ) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//right
		p.seteValue(position.getRow(), position.getColumn() + 1);
		if(getBoard().positionExists(p) && canMove(p) ) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		//nw(NO)
		p.seteValue(position.getRow() -1, position.getColumn() -1);
		if(getBoard().positionExists(p) && canMove(p) ) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		//ne(NE)
		p.seteValue(position.getRow() -1, position.getColumn() +1);
		if(getBoard().positionExists(p) && canMove(p) ) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		//sw(SO)
		p.seteValue(position.getRow() +1, position.getColumn() -1);
		if(getBoard().positionExists(p) && canMove(p) ) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		//se(SE)
		p.seteValue(position.getRow() +1, position.getColumn() +1);
		if(getBoard().positionExists(p) && canMove(p) ) {
			mat[p.getRow()][p.getColumn()] = true;
		}		
		return mat;
	}

	@Override
	public String toString() {
		return "k";
	}
}
