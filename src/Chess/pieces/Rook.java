/*Peça Torre*/

package Chess.pieces;//sub class de pecas

import Chess.ChessPiece;
import Chess.Color;
import boardgame.Board;

public class Rook extends ChessPiece{

	public Rook(Board board, Color color) {//contrutor passa a informação de quem é tabuleiro, peca e cor
		super(board, color);
	}

	@Override
	public String toString() {//função é apenas uma letra para entra no tabuleiro
		return "R";
	}
}
