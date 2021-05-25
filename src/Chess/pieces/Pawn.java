package Chess.pieces;

import Chess.ChessMatch;
import Chess.ChessPiece;
import Chess.Color;
import boardgame.Board;
import boardgame.Position;

public class Pawn extends ChessPiece {
	
	private ChessMatch chessMatch;

	public Pawn(Board board, Color color, ChessMatch chessMatch) {
		super(board, color);
		this.chessMatch = chessMatch;		
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
			
			//#specialmove en passant White
			if(position.getRow() == 3) {//testando se peca ta na poição 3 da matriz a esquerda
				Position left = new Position(position.getRow(), position.getColumn() -1); //parametro para testa se tem pecas para captira ao lado esquerdo.
				if(getBoard().positionExists(left) && isTherOppanentPiece(left) && getBoard().piece(left) == chessMatch.getEnPassantVulnerable()) {//verif. se existe um peca do lado esquedo, se ela é adversaria se peca e é apeca que esta vuneravel a en passant
					mat[left.getRow() -1][left.getColumn()] = true;// se sim, peão pode capitura a peca da esquerda, e andanda uma linha a cima 
				}	
				Position right = new Position(position.getRow(), position.getColumn() +1); //parametro para testa se tem pecas para captira ao lado direito.
				if(getBoard().positionExists(right) && isTherOppanentPiece(right) && getBoard().piece(right) == chessMatch.getEnPassantVulnerable()) {//verif. se existe um peca do lado direito, se ela é adversaria e é a peca que esta vuneravel a en passant
					mat[right.getRow() -1][right.getColumn()] = true;// se sim, peão pode capitura a peca da esquerda, e andanda uma linha a cima 
				}				
			}												
		}
		else {//pecas Black
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
			
			//#specialmove en passant Black
			if(position.getRow() == 4) {//testando se peca ta na poição 4 da matriz a esquerda
				Position left = new Position(position.getRow(), position.getColumn() -1); //parametro para testa se tem pecas para captira ao lado esquerdo.
				if(getBoard().positionExists(left) && isTherOppanentPiece(left) && getBoard().piece(left) == chessMatch.getEnPassantVulnerable()) {//verif. se existe um peca do lado esquedo, se ela é adversaria se peca e é apeca que esta vuneravel a en passant
					mat[left.getRow() +1][left.getColumn()] = true;// se sim, peão pode capitura a peca da esquerda, e danda uma linha a cima 
				}	
				Position right = new Position(position.getRow(), position.getColumn() +1); //parametro para testa se tem pecas para captira ao lado direito.
				if(getBoard().positionExists(right) && isTherOppanentPiece(right) && getBoard().piece(right) == chessMatch.getEnPassantVulnerable()) {//verif. se existe um peca do lado direito, se ela é adversaria e é a peca que esta vuneravel a en passant
					mat[right.getRow() +1][right.getColumn()] = true;// se sim, peão pode capitura a peca da esquerda, e andanda uma linha a cima 
				}				
			}			
		}
		return mat;
	}
	
	@Override
	public String toString() {
		return "P";
	}
}


