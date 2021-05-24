package application;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import Chess.ChessMatch;
import Chess.ChessPiece;
import Chess.ChessPosition;
import Chess.Color;

public class UI {

	// https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println

	/* cod. imprimir o texto */
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	/* cod. do fundo */
	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

	// https://stackoverflow.com/questions/2979383/java-clear-the-console

	public static void clearScreen() {// metodo de limpa a tela
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}
		
	public static ChessPosition readChessPosition(Scanner sc) {/*metodo que ler a posição do usuario*/ 		
		try {
			String s = sc.nextLine();
			char column = s.charAt(0);
			int row = Integer.parseInt(s.substring(1));
			return new ChessPosition(column, row);
		}
		catch(RuntimeException e ) {
			throw new InputMismatchException("Erro reading ChassPosition. Valid values are from a1 to h8");//sig erro de entrada de dados
		}
	}
	public static void printMatch(ChessMatch chessMatch, List<ChessPiece> captured) {//imprimindo a partida
		printBoard(chessMatch.getPieces());
		System.out.println();
		System.out.println();		
		printCapturedPieces(captured);
		System.out.println();		
		System.out.println("Turn: " + chessMatch.getTurn());
		
		if(!chessMatch.getCheckMate()) {//testando primeiro se não estiver em checkMate
			System.out.println("Waiting player: " + chessMatch.getCurrentPlay());
			if (chessMatch.getCheck()) {//caso for necessario vai passa a informação de check
				System.out.println("CHECK!");
			}
		}
		else {//se não mostrar informação de vencedor
			System.out.println("CHECKMATE!");
			System.out.println("Winner: " + chessMatch.getCurrentPlay());
		}
	}

	public static void printBoard(ChessPiece[][] pieces) {//imprime sem o fundo colorido.
		for (int i = 0; i < pieces.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j = 0; j < pieces.length; j++) {
				printPiece(pieces[i][j], false);
			}
			System.out.println();
		}
		System.out.print("  a b c d e f g h");
	}
	
	public static void printBoard(ChessPiece[][] pieces, boolean[][] possibleMoves) {//impr. tabul. fundo colorido considerando movi. possiveis
		for (int i = 0; i < pieces.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j = 0; j < pieces.length; j++) {
				printPiece(pieces[i][j], possibleMoves[i][j]);
			}
			System.out.println();		
			
			}
		System.out.print("  a b c d e f g h");
	}
	
	private static void printPiece(ChessPiece piece, boolean blackgraund) {//metodo vai ou não imprim o fundo de azul dependendo da variavel blacg.
		if(blackgraund) {
			System.out.print(ANSI_BLUE_BACKGROUND);
		}
		if (piece == null) {
			System.out.print("-" + ANSI_RESET);
		} else {
			if (piece.getColor() == Color.WHITE) {
				System.out.print(ANSI_WHITE + piece + ANSI_RESET);
			} else {
				System.out.print(ANSI_YELLOW + piece + ANSI_RESET);
			}
		}
		System.out.print(" ");
	}
	private static void printCapturedPieces(List<ChessPiece> captured ) {//Metodo imprimir a lista de pecas capturadas
		List<ChessPiece> white = captured.stream().filter(x -> x.getColor() == Color.WHITE).collect(Collectors.toList());//filtrando na lista todos que seja branco
		List<ChessPiece> black = captured.stream().filter(x -> x.getColor() == Color.BLACK).collect(Collectors.toList()); //filtrando na lista todos que seja Pretas

		System.out.println("Captured pieces:");
		System.out.print("White: ");
		System.out.print(ANSI_WHITE);
		System.out.println(Arrays.toString(white.toArray()));//padrao de imprimir lista de arrays no java.
		System.out.print(ANSI_RESET);
		
		System.out.print("Black: ");
		System.out.print(ANSI_YELLOW);
		System.out.println(Arrays.toString(black.toArray()));
		System.out.print(ANSI_RESET);
	}

}
