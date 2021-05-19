package application;

import Chess.ChessPiece;

public class UI {//User interface

	public static void printBoard(ChessPiece[][] pieces) {
		
		/*Logica para imprimir as posi��es*/
		
		for(int i= 0; i<pieces.length; i++) {//percorre as linhas 
			System.out.print((8 - i) + " ");//imprime a linha com contagem regrecissa aparti de 8 e espa�o
			
			for(int j = 0; j<pieces.length; j++) {//percorre a coluna (length consideramos que � uma matriz quadrada)
				printPiece(pieces[i][j]);//imprime a peca 
			}
			System.out.println();//para ter a quebra de linha ao final de cada impre��o
		}
		System.out.print("  a b c d e f g h");//imprimindo a linha de baixo do tabuleiro
	}
	
	/*logica para imprimir o tabuleiro*/
	
	private static void printPiece(ChessPiece piece) {//imprimindo uma peca
		if(piece == null) {//se for igual a null que dizer que n�o tem peca nesta posi��o do tabuleiro, imprimir um -
			System.out.print("-");
		}
		else {//casso contratio imprimir a peca
			System.out.print(piece);
		}
		System.out.print(" ");//imprimir um espa�o para as pecas n�o ficarem grudadas uma na outras.
	}
}
