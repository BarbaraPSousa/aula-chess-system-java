//Class Partida de Xadrez
//Cora��o do jogo

package Chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Chess.pieces.King;
import Chess.pieces.Rook;
import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

public class ChessMatch {
	
	private int turn;
	private Color currentPlay;
	private Board board;
	private boolean check;
	
	private List<Piece> piecesOnTheBoard = new ArrayList<>();
	private List<Piece> capturedPieces = new ArrayList<>();

	public ChessMatch() {
		board = new Board(8, 8);
		turn = 1;
		currentPlay = Color.WHITE;
		initialStep();
	}
	
	public int getTurn() {
		return turn;
	}
	public Color getCurrentPlay() {
		return currentPlay;
	}
	public boolean getCheck() {
		return check;
	}
	
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPositon) {//metodo do movimento do xadrez
		Position source = sourcePosition.toPosition();
		Position target = targetPositon.toPosition();
		ValidateSourcePosition(source); 
		ValidateTargetPosition(source,target);
		Piece capturedPice = makeMove(source,target);
		
		if(testCheck(currentPlay)) {//testando se jogador atual se colocou em chek
			undoMove(source, target, capturedPice);//desfazendo movimento
			throw new ChessException("You can't yourselt in check");//informando para usuario que n�o pode fazer kovimento
		}
		check = (testCheck(opponent(currentPlay))) ? true:false;//se teste check do oponente for vdd partida em check, se folso na� esta em check
		
		
		nexTurn();
		return (ChessPiece)capturedPice;//pec era do tipo Pice trocado para chessPiece		
	}
	private Piece makeMove(Position source,Position target) {//metodo de realizar movimento da pe�a
		Piece p = board.removePiece(source);//tira peca de origem
		Piece capturedPiece = board.removePiece(target);//tira uma possivel peca cap. na posicao de destino
		board.placePiece(p, target);//coloca no destino
		
		if(capturedPiece != null) {//se capturou alguma peca
			piecesOnTheBoard.remove(capturedPiece);//tira da lista de pca no tabuleiro 
			capturedPieces.add(capturedPiece);//coloca na lista de peca capturada	
		}
		return capturedPiece;		
	}
	private void undoMove(Position souce, Position target, Piece capturedPiece	) {// metodo para desfazer movimento
		Piece p = board.removePiece(target);//tira da peca que moveu do destino
		board.placePiece(p, souce);//devolve para posicao de origem
		
		if(capturedPiece != null) {//devolvendo a peca no tabuleiro na posicao de destino
			board.placePiece(capturedPiece, target);
			capturedPieces.remove(capturedPiece);//retira da lista de peca capt
			piecesOnTheBoard.add(capturedPiece);//add na lista de peca no tabuleiro
		}
	}

	private void ValidateSourcePosition(Position position) {// validar se existe uma peca na posi��o de origem

		if (!board.thereIsAPiece(position)) {
			throw new ChessException("There is no piece on source position");
		}
		if (currentPlay != ((ChessPiece) board.piece(position)).getColor()) {
			throw new ChessException("The chosen piece is not yours");
		}
		if (!board.piece(position).isThereAnyPossibleMove()) {// segunda verifica��o de pe�a de origem
			throw new ChessException("There is no piece on source position");
		}
	}

	private void ValidateTargetPosition(Position source, Position target) {// valida se pos. de destino � valid. em
																			// relacao de origim
		if (!board.piece(source).possibleMove(target)) {// se para peca de origim n for movimento possivel, n pode mexe
														// p/ posicao
			throw new ChessException("The chosen piece can't move to target position");
		}
	}
	
	private void nexTurn() {//metodo de troca de turno
		turn++;
		currentPlay = (currentPlay == Color.WHITE) ? Color.BLACK : Color.WHITE;// se jp for igual a branco ent�o agora vai ser branco, caso contrario vai ser branco
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
	
	private Color opponent(Color color) {//metodo p/ devolver o oponente de uma cor
		return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}
	
	private ChessPiece king(Color color) {//mmetodo que localiza o rei de uma determinada cor
		List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());//filtrando a lista
		for(Piece p : list) {//para cada p na lista 
			if(p instanceof King) {//se a peca p � uma intancia de rei
				return (ChessPiece)p;//encontrei o rei
			}
		}
		throw new IllegalStateException("There is no " + color + " King on the board");//n�o vai ser tratado, pois este erro n�o pode aparecer.	
	}
	
	private boolean testCheck(Color color) {//testando se o rei esta em check
		Position kingPosition = king(color).getChessPosition().toPosition();//pegando a posicao do rei em  formato de matriz
		List<Piece> opponetPiece = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == opponent(color)).collect(Collectors.toList()); //lista das pecas do oponente desta cor
		for(Piece p: opponetPiece) {//testando se existe algum  moviemnto possivel na lista que leva a posicao do rei
			boolean[][] mat = p.possibleMoves();
			if(mat[kingPosition.getRow()][kingPosition.getColumn()]) {//se peca p na linha e coluna do rei for vedd, rei ta em check
				return true;
			}
		}
		return false;//se esgota o teste, rei na� esta em check
	}

	private void placeNewPiece(char column, int row, ChessPiece piece) {/* opera��o que passa a na cordenada do xadrez e n�o matriz*/																		
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
		piecesOnTheBoard.add(piece);
	}

	private void initialStep() {/* res. por iniciar a partida de xadrez e coloca a pe�a no tabuleiro do xadrez*/
								
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
