//Class Partida de Xadrez
//Coração do jogo

package Chess;

import Chess.pieces.King;
import Chess.pieces.Rook;
import boardgame.Board;

public class ChessMatch {
	
	private Board board;
	
	public ChessMatch() {//informando o tamanho do tabuleiro. a class em questão é responsavel por esta informação. 
		board = new Board(8, 8);
		initialStep();
	}
	
	public ChessPiece[][] getPieces() {//metodo vai retorna uma matriz de pecas, correspondente apartida.
		ChessPiece[][] mat = new ChessPiece[board.getRow()][board.getColumns()];//criando uma variavel temporaria auxiliar para o programa conhecer apenas a camada xadres e a camada de tabuleiro		
		
		for(int i=0; i<board.getRow(); i++ ) {//percorre a linha
			for(int j =0; j<board.getColumns(); j++) {//percorre a colina
				mat[i][j] = (ChessPiece) board.piece(i,j);//para cada posição i j da matriz mat vai receber recebe uma peca(informa que é uma peça de xadrez)
			}		
		}
		return mat;		
	}
	private void placeNewPiece(char column, int row, ChessPiece piece) {//operação que passa a na cordenada do xadrez e não matriz
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
	}
	
	private void initialStep() {//responsavel por iniciar a partida de xadrez e coloca a peça no tabuleiro do xadrez
		placeNewPiece('b', 6, new Rook(board, Color.WHITE));
		placeNewPiece('e', 8, new King(board, Color.BLACK));
		placeNewPiece('e', 1, new King(board, Color.WHITE));
	}
}
