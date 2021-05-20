/*Pe�a Torre*/

package Chess.pieces;//sub class de pecas

import Chess.ChessPiece;
import Chess.Color;
import boardgame.Board;

public class Rook extends ChessPiece{

	public Rook(Board board, Color color) {//contrutor passa a informa��o de quem � tabuleiro, peca e cor
		super(board, color);
	}

	@Override
	public String toString() {//fun��o � apenas uma letra para entra no tabuleiro
		return "R";
	}
	@Override
	public boolean[][] possibleMoves() {//movimento da torre basico
		boolean[][]mat = new boolean[getBoard().getRow()][getBoard().getColumns()];
		return mat;
	}
}
