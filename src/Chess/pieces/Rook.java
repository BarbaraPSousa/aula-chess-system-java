/*Peça Torre*/

package Chess.pieces;

import Chess.ChessPiece;
import Chess.Color;
import boardgame.Board;
import boardgame.Position;

public class Rook extends ChessPiece{

	public Rook(Board board, Color color) {
		super(board, color);
	}
	
	@Override
	public boolean[][] possibleMoves() {//implementando o movimento da torre
		
		boolean[][]mat = new boolean[getBoard().getRow()][getBoard().getColumns()];
		
		Position p = new Position(0, 0);
		
		//above
		p.seteValue(position.getRow() -1,position.getColumn());
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {//enquanto a posicao existie e não tiver uma peca la é vdd
			mat[p.getRow()][p.getColumn()] = true;
			p.setRow(p.getRow() - 1);//andando mas uma posicao
		}
		if(getBoard().positionExists(p) && isTherOppanentPiece(p)) {//verificando se 
			mat[p.getRow()][p.getColumn()] = true; 
		}
		//left
		p.seteValue(position.getRow(), position.getColumn() - 1);
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {//enquanto a posicao p existir e não tiver uma peca la,é verdadeira
			mat[p.getRow()][p.getColumn()] = true;
			p.setColumn(p.getColumn() - 1);
		}
		if(getBoard().positionExists(p) && isTherOppanentPiece(p)) {//verificando se ainda tem casa e se nela tem uma piece adiversaria, se sim vai marca como verdadeiro
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
		return mat;
	}

	@Override
	public String toString() {//função é apenas uma letra para entra no tabuleiro
		return "R";
	}
}
