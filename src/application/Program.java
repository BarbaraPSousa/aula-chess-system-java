package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import Chess.ChessException;
import Chess.ChessMatch;
import Chess.ChessPiece;
import Chess.ChessPosition;

public class Program {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		ChessMatch chessMatch = new ChessMatch();
		List<ChessPiece> captured = new ArrayList<>();

		while (!chessMatch.getCheckMate()) {// enquanto minha partida não tiver em checkMate
			try {
				UI.clearScreen();
				UI.printMatch(chessMatch, captured);
				System.out.println();

				System.out.print("Source: ");
				ChessPosition source = UI.readChessPosition(sc);// lendo possi. de origim

				boolean[][] possibleMoves = chessMatch.possibleMoves(source);// chamando tabuleiro
				UI.clearScreen();// limpando tela
				UI.printBoard(chessMatch.getPieces(), possibleMoves);
				System.out.println();
				System.out.println();
				System.out.print("Target: ");
				ChessPosition target = UI.readChessPosition(sc);// lendo poss. de destino

				ChessPiece capturdPiece = chessMatch.performChessMove(source, target);// chamada de movimento

				if (capturdPiece != null) {//add na lista de pecas captura
					captured.add(capturdPiece);
				}

			} catch (ChessException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			} catch (InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.hasNextLine();
			} 
		}//aconteu um check matri limpa a tela e mostrar a partida finalizada
		UI.clearScreen();
		UI.printMatch(chessMatch, captured);
	}
}
