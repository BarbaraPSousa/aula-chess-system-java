package application;

import java.util.Scanner;

import Chess.ChessMatch;
import Chess.ChessPiece;
import Chess.ChessPosition;

public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);		
		ChessMatch chessMatch = new ChessMatch();
		
		
		while (true) {//sem logica de partida ainda, ele vai ficar repetindo temporariamente
			UI.printBoard(chessMatch.getPieces());
			System.out.println();
			System.out.println();
			System.out.print("Source: ");
			ChessPosition source = UI.readChessPosition(sc);//lendo possi. de origim
			
			System.out.println();
			System.out.print("Target: ");
			ChessPosition target = UI.readChessPosition(sc);//lendo poss. de destino
			
			ChessPiece capturdChessPiece = chessMatch.performChessMove(source, target);//chamada de movimento
		}	
	}
}
