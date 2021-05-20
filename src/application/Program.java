package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import Chess.ChessException;
import Chess.ChessMatch;
import Chess.ChessPiece;
import Chess.ChessPosition;

public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);		
		ChessMatch chessMatch = new ChessMatch();
		
		
		while (true) {//sem logica de partida ainda, ele vai ficar repetindo temporariamente
			try {
			UI.clearScreen();
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
		catch (ChessException e) {
			System.out.println(e.getMessage());
			sc.nextLine();
		}
		catch(InputMismatchException e) {
			System.out.println(e.getMessage());
			sc.hasNextLine();
			}
	
		}
	}
}
