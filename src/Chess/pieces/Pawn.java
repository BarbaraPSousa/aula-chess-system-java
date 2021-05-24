package Chess.pieces;

import Chess.ChessPiece;
import Chess.Color;
import boardgame.Board;
import boardgame.Position;

public class Pawn extends ChessPiece {

	public Pawn(Board board, Color color) {
		super(board, color);

	}

	@Override
	public boolean[][] possibleMoves() {// logica de movimento possivel de um Peao
		boolean[][] mat = new boolean[getBoard().getRow()][getBoard().getColumns()];// matriz auxiliar

		Position p = new Position(0, 0);// posicao auxiliar

		if (getColor() == Color.WHITE) {// se a peca for branca move para cima
			p.seteValue(position.getRow() - 1, position.getColumn());
			if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;// regra geral do peao branco, se exixtir uma linha e tiver vazia ele pode mover pa la
			}
			p.seteValue(position.getRow() - 2, position.getColumn());
			Position p2 = new Position(position.getRow() -1 , position.getColumn());
			if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2) && getMoveCount() == 0) {// testando se linha vazia, p2 testa se existe e se ta vazia, testa se movimento é zero																		 
				mat[p.getRow()][p.getColumn()] = true;// regra gegal do peao branco, se exixtir uma linha e tiver vaziaele pode mover pa la
			}
			p.seteValue(position.getRow() - 1, position.getColumn() - 1);//diagonal
			if (getBoard().positionExists(p) && isTherOppanentPiece(p)) {// teste se posicao exite e se tem uma peca adiversaria la
				mat[p.getRow()][p.getColumn()] = true;
			}
			p.seteValue(position.getRow() - 1, position.getColumn() + 1);//diaagonal
			if (getBoard().positionExists(p) && isTherOppanentPiece(p)) {// teste se exiete e se tem peca do oponete la;
				mat[p.getRow()][p.getColumn()] = true;
			}
		} else {
			p.seteValue(position.getRow() + 1, position.getColumn());
			if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;// regra geral do peao branco, se exixtir uma linha e tiver vazia  ele pode mover pa la
			}
			p.seteValue(position.getRow() + 2, position.getColumn());
			Position p2 = new Position(position.getRow() + 1, position.getColumn());
			if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && getBoard().positionExists(p2)
					&& !getBoard().thereIsAPiece(p2) && getMoveCount() == 0) {// testando se linha vazia, p2 testa se existe e se ta vazia, test  movimento é zero																				
				mat[p.getRow()][p.getColumn()] = true;// regra gegal do peao branco, se exixtir uma linha e tiver vazia
														// ele pode mover pa la
			}
			p.seteValue(position.getRow() + 1, position.getColumn() - 1);
			if (getBoard().positionExists(p) && isTherOppanentPiece(p)) {// teste se posicao exite e se tem uma peca adiversaria la;
				mat[p.getRow()][p.getColumn()] = true;
			}
			p.seteValue(position.getRow() + 1, position.getColumn() + 1);
			if (getBoard().positionExists(p) && isTherOppanentPiece(p)) {// teste se exiete e se tem peca do oponete la;
				mat[p.getRow()][p.getColumn()] = true;
			}
		}
		return mat;
	}
	
	@Override
	public String toString() {
		return "P";
	}
}


