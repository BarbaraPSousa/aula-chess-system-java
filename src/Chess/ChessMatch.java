//Class Partida de Xadrez
//Coração do jogo

package Chess;

import Chess.pieces.King;
import Chess.pieces.Rook;
import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

public class ChessMatch {

	private Board board;

	public ChessMatch() {// informando o tamanho do tabuleiro a class em questão 
		board = new Board(8, 8);
		initialStep();
	}
	
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPositon) {//metodo do movimento do xadrez
		Position source = sourcePosition.toPosition();
		Position target = targetPositon.toPosition();
		ValidateSourcePosition(source); // valid. pos. de origim
		ValidateTargetPosition(source,target);
		Piece capturedPice = makeMove(source,target);// res. por mover a peça
		return (ChessPiece)capturedPice;//pec era do tipo Pice trocado para chessPiece		
	}
	private Piece makeMove(Position source,Position target) {//metodo de realizar movimento da peça
		Piece p = board.removePiece(source);
		Piece capturedPiece = board.removePiece(target);
		board.placePice(p, target);
		return capturedPiece;
	}
	
	private void ValidateSourcePosition(Position position) {//validar se existe uma peca na posição de origem
		if(!board.thereIsAPiece(position)) {
			throw new ChessException("There is no piece on source position");
		}
		if(!board.piece(position).isThereAnyPossibleMove()) {//segunda verificação de peça de origem
			throw new ChessException("There is no piece on source position");
		}
	}
	
	private void ValidateTargetPosition(Position source, Position target) {//valida se pos. de destino é valid. em relacao de origim
		if(!board.piece(source).possibleMove(target)) {//se para peca de origim n for movimento possivel, n pode mexe p/ posicao
			throw new ChessException("The chosen piece can't move to target position");			
		}
	}

	public ChessPiece[][] getPieces() {// metodo vai retorna uma matriz de pecas, correspondente apartida.
		ChessPiece[][] mat = new ChessPiece[board.getRow()][board.getColumns()];
		for (int i = 0; i < board.getRow(); i++) {
			for (int j = 0; j < board.getColumns(); j++) {
				mat[i][j] = (ChessPiece) board.piece(i, j);
			}
		}
		return mat;
	}
	
	public boolean[][] possibleMoves(ChessPosition sourcePosition){//metodo para imprimi as possicao possiveis, a patir de uma possicao de origem.
		Position position = sourcePosition.toPosition();
		ValidateSourcePosition(position);
		return board.piece(position).possibleMoves();
	}

	private void placeNewPiece(char column, int row, ChessPiece piece) {/* operação que passa a na cordenada do xadrez e não matriz*/
																		
		board.placePice(piece, new ChessPosition(column, row).toPosition());
	}

	private void initialStep() {/* res. por iniciar a partida de xadrez e coloca a peça no tabuleiro do xadrez*/
								
		placeNewPiece('c', 1, new Rook(board, Color.WHITE));
		placeNewPiece('c', 2, new Rook(board, Color.WHITE));
		placeNewPiece('d', 2, new Rook(board, Color.WHITE));
		placeNewPiece('e', 2, new Rook(board, Color.WHITE));
		placeNewPiece('e', 1, new Rook(board, Color.WHITE));
		placeNewPiece('d', 1, new King(board, Color.WHITE));

		placeNewPiece('c', 7, new Rook(board, Color.BLACK));
		placeNewPiece('c', 8, new Rook(board, Color.BLACK));
		placeNewPiece('d', 7, new Rook(board, Color.BLACK));
		placeNewPiece('e', 7, new Rook(board, Color.BLACK));
		placeNewPiece('e', 8, new Rook(board, Color.BLACK));
		placeNewPiece('d', 8, new King(board, Color.BLACK));
	}
}
