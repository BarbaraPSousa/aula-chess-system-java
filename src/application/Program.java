package application;

import Chess.ChessMatch;

public class Program {

	public static void main(String[] args) {
		
		ChessMatch chessMatch = new ChessMatch();
		
		/*Fun��o para imprimir as peca*/
		
		UI.printBoard(chessMatch.getPieces());
		
	
	}
}
