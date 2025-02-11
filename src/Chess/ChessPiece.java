//subClass de Piece

package Chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

public abstract class ChessPiece extends Piece {
	
	private Color color;
	private int moveCount;

	public ChessPiece(Board board, Color color) {
		super(board);
		this.color = color;
	}

	public Color getColor() {//somente o get, pois a corda da pe�a n�o vai ser modificada, so pod� ser acessada.
		return color;
	}
	
	public int getMoveCount() {
		return moveCount;
	}
	
	public void increaseMoveCount() {//metodo para soma os pontos
		moveCount++;
	}

	public void decreaseMoveCount() {//metodo para subtrair os pontos
		moveCount--;
	}
	
	public ChessPosition getChessPosition() {//metodo para retorna uma posicao no formato do xadrez na cless piece. 		
		return ChessPosition.FromPosition(position);
	}

	protected boolean isTherOppanentPiece(Position position) { // saber se tem uma piece em uma determinada casa
		ChessPiece p = (ChessPiece)getBoard().piece(position);
		return p != null && p.getColor() != color;
	}	
	
	
}
