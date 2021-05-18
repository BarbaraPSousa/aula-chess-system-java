//Class Partida de Xadrez
//Cora��o do jogo

package Chess;

import boardgame.Board;

public class ChessMatch {
	
	private Board board;
	
	public ChessMatch() {//informando o tamanho do tabuleiro. a class em quest�o � responsavel por esta informa��o. 
		board = new Board(8, 8);
	}
	
	public ChessPiece[][] getPieces() {//metodo vai retorna uma matriz de pecas, correspondente apartida.
		ChessPiece[][] mat = new ChessPiece[board.getRow()][board.getColumns()];//criando uma variavel temporaria auxiliar para o programa conhecer apenas a camada xadres e a camada de tabuleiro		
		
		for(int i=0; i<board.getRow(); i++ ) {//percorre a linha
			for(int j =0; j<board.getColumns(); j++) {//percorre a colina
				mat[i][j] = (ChessPiece) board.piece(i,j);//para cada posi��o i j da matriz mat vai receber recebe uma peca(informa que � uma pe�a de xadrez)
			}		
		}
		return mat;
		
	}
}
