package application;

import Chess.ChessMatch;

public class Program {

	public static void main(String[] args) {
		
		ChessMatch chessMatch = new ChessMatch();
		
		/*Função para imprimir as peca*/
		
		UI.printBoard(chessMatch.getPieces());
		
	
	}
}
