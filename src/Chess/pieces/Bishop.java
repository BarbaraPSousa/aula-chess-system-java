/*Peça Bispo*/

package Chess.pieces;

import Chess.ChessPiece;
import Chess.Color;
import boardgame.Board;
import boardgame.Position;

public class Bishop extends ChessPiece{

	public Bishop(Board board, Color color) {
		super(board, color);
	}

	@Override
	public boolean[][] possibleMoves() {//implementando o movimento do Bispo
		
		boolean[][]mat = new boolean[getBoard().getRow()][getBoard().getColumns()];
		
		Position p = new Position(0, 0);
		
		//nw
		p.seteValue(position.getRow() -1,position.getColumn() -1);
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {//enquanto a posicao existie e não tiver uma peca la é vdd
			mat[p.getRow()][p.getColumn()] = true;
			p.seteValue(p.getRow() -1, p.getColumn() -1);
		}
		if(getBoard().positionExists(p) && isTherOppanentPiece(p)) {//verificando se 
			mat[p.getRow()][p.getColumn()] = true; 
		}
		
		//ne
		p.seteValue(position.getRow() -1, position.getColumn() +1);
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {//enquanto a posicao p existir e não tiver uma peca la,é verdadeira
			mat[p.getRow()][p.getColumn()] = true;
			p.seteValue(p.getRow() -1, p.getColumn() +1);
		}
		if(getBoard().positionExists(p) && isTherOppanentPiece(p)) {//verificando se ainda tem casa e se nela tem uma piece adiversaria, se sim vai marca como verdadeiro
			mat[p.getRow()][p.getColumn()] = true; 
		}
		
		// se
		p.seteValue(position.getRow() +1, position.getColumn() + 1);
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.seteValue(p.getRow() +1, p.getColumn() +1);
		}
		if (getBoard().positionExists(p) && isTherOppanentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//sw
		p.seteValue(position.getRow() + 1, position.getColumn() -1);
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {																				
			mat[p.getRow()][p.getColumn()] = true;
			p.seteValue(p.getRow() + 1, p.getColumn() -1);
		}
		if (getBoard().positionExists(p) && isTherOppanentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}				
		return mat;
	}
	
	@Override
	public String toString() {//função é apenas uma letra para entra no tabuleiro
		return "B";
	}
}
