package application;

import java.util.InputMismatchException;
import java.util.Scanner;

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
		try {//para evitar qualquer problema de formato
			String s = sc.nextLine();//lendo a posi.
			char column = s.charAt(0);//lendo a coluna como caracter
			int row = Integer.parseInt(s.substring(1));//recortando a string na posição 1 e convertendo para inteiro
			return new ChessPosition(column, row);
		}
		catch(RuntimeException e ) {//tratamento de erro
			throw new InputMismatchException("Erro reading ChassPosition. Valid values are from a1 to h8");//sig erro de entrada de dados
		}
	}

	public static void printBoard(ChessPiece[][] pieces) {

		/* Logica para imprimir as posições */

		for (int i = 0; i < pieces.length; i++) {// percorre as linhas
			System.out.print((8 - i) + " ");// imprime a linha com contagem regrecissa aparti de 8 e espaço

			for (int j = 0; j < pieces.length; j++) {// percorre a coluna (length consideramos que é uma matriz
														// quadrada)
				printPiece(pieces[i][j]);// imprime a peca
			}
			System.out.println();// para ter a quebra de linha ao final de cada impreção
		}
		System.out.print("  a b c d e f g h");// imprimindo a linha de baixo do tabuleiro
	}

	/* logica para imprimir o tabuleiro */

	private static void printPiece(ChessPiece piece) {//metodo trocado para a função para imprimir com a cor
		if (piece == null) {
			System.out.print("-");
		} else {
			if (piece.getColor() == Color.WHITE) {
				System.out.print(ANSI_WHITE + piece + ANSI_RESET);
			} else {
				System.out.print(ANSI_YELLOW + piece + ANSI_RESET);
			}
		}
		System.out.print(" ");
	}

}
