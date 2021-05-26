//Class Partida de Xadrez
//Coração do jogo

package Chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Chess.pieces.Bishop;
import Chess.pieces.King;
import Chess.pieces.Knight;
import Chess.pieces.Pawn;
import Chess.pieces.Queen;
import Chess.pieces.Rook;
import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

public class ChessMatch {

	private int turn;
	private Color currentPlay;
	private Board board;
	private boolean check;
	private boolean checkMate;
	private ChessPiece enPassantVulnerable;
	private ChessPiece promoted;

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

	public boolean getCheckMate() {
		return checkMate;
	}

	public ChessPiece getEnPassantVulnerable() {
		return enPassantVulnerable;
	}
	
	public ChessPiece getPromoted() {
		return promoted;
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

	public boolean[][] possibleMoves(ChessPosition sourcePosition) {// metodo para imprimi as possicao possiveis, a
																	// patir de uma possicao de origem.
		Position position = sourcePosition.toPosition();
		ValidateSourcePosition(position);
		return board.piece(position).possibleMoves();
	}

	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPositon) {											// 																								// xadrez
		Position source = sourcePosition.toPosition();
		Position target = targetPositon.toPosition();
		ValidateSourcePosition(source);
		ValidateTargetPosition(source, target);
		Piece capturedPice = makeMove(source, target);
		
		if (testCheck(currentPlay)) {// testando se jogador atual se colocou em chek
			undoMove(source, target, capturedPice);// desfazendo movimento
			throw new ChessException("You can't put yourselt in check");// informando para usuario que não pode fazer																		// kovimento
		}

		ChessPiece movedPiece = (ChessPiece) board.piece(target);// referencia da peca que se movel

		//#specialmove pormotion
		promoted = null;//a segura que ta fazendo novo tes
		if(movedPiece instanceof Pawn) {//tstse peca é Peao chegou 
			if((movedPiece.getColor() == Color.WHITE && target.getRow() == 0) || (movedPiece.getColor() == Color.BLACK && target.getRow() == 7)){//tst peão n final
				promoted = (ChessPiece)board.piece(target);//tratamento especial
				promoted = replacePromotedPiece("Q");//promovendo o Peao p/ outa peca de maior poder padrao é rainha(Q)			
			}			
		}		
		check = (testCheck(opponent(currentPlay))) ? true : false;// testando se oponente ficou em check

		if (testCheckMate(opponent(currentPlay))) {// testando se o jogo acabou
			checkMate = true;
		} 
		else {// caso contratio chama o proximo turno e partida continua.
			nexTurn();
		}

		// #specialmove en passant
		if (movedPiece instanceof Pawn && (target.getRow() == source.getRow() - 2 || target.getRow() == source.getColumn() + 2)) {// test p/ verif. peca movida foi peao s diferenca de linha 2 p mas ou menos																									 
			enPassantVulnerable = movedPiece;// se positivo en passant recebe peca movida
		} 
		else {
			enPassantVulnerable = null;// não tem ninguem vuneravel.
		}

		return (ChessPiece) capturedPice;// pec era do tipo Pice trocado para chessPiece
	}
	
	public ChessPiece replacePromotedPiece(String type) {//met para troca de do Peao promovido
		if(promoted == null) {//defesa para informa que não tem peca para promover
			throw new IllegalStateException("There is no piece to be promoted");//msg informativa 
		}
		if(!type.equals("B") && !type.equals("N") && !type.equals("R") && !type.equals("Q")) {//defesa p/ comparar se string é B N R ou Q, digitado pelo usuario
			return promoted;//trocado para troca de peca e n excep.			
		}
		Position pos = promoted.getChessPosition().toPosition();//pegando posicao de peca promovida
		Piece p = board.removePiece(pos);//removendo peca da posicao e guardando na variavel p
		piecesOnTheBoard.remove(p);//excluindo peca p da lista do tabuleiro.
		
		ChessPiece newPiece = newPiece(type, promoted.getColor());//instanciando nova peca
		board.placePiece(newPiece, pos);//colocando a peca na posicao da peca promovida
		piecesOnTheBoard.add(newPiece);//adicionando na lista a nova peca que criou
		
		return newPiece;
	}
	
	private ChessPiece newPiece(String type, Color color) {//metodo p/ auxiliar na instanciacao de uma peca especifica
		if(type.equals("B")) return new Bishop(board, color);
		if(type.equals("N")) return new Knight(board, color);
		if(type.equals("Q")) return new Queen(board, color);
		return new Rook(board, color);
	}
	
	private Piece makeMove(Position source, Position target) {// metodo de realizar movimento da peça
		ChessPiece p = (ChessPiece) board.removePiece(source);
		p.increaseMoveCount();// chamando o metodo de soma conforme movimento
		Piece capturedPiece = board.removePiece(target);
		board.placePiece(p, target);

		if (capturedPiece != null) {// se capturou alguma peca
			piecesOnTheBoard.remove(capturedPiece);// removendo pecas do tabuleiro
			capturedPieces.add(capturedPiece);// coloca na lista de peca capturada
		}
		// #specialmove castling kingside rook
		if (p instanceof King && target.getColumn() == source.getColumn() + 2) {// test se movimento é um rock pequeno
			Position sourceT = new Position(source.getRow(), source.getColumn() + 3);// movendo a torre na msm coluna e
																						// linha do rei, origem
			Position targetT = new Position(source.getRow(), source.getColumn() + 1);// movendo a torre na msm coluna e
																						// linha do rei, destino
			ChessPiece rook = (ChessPiece) board.removePiece(sourceT);// retirando a torre do local de origem
			board.placePiece(rook, targetT);// coloca a torre na poicao de destino
			rook.increaseMoveCount();// movendo a torre, inclemente a contidade de movimento.
		}
		// #specialmove castling queenside rook
		if (p instanceof King && target.getColumn() == source.getColumn() - 2) {// test se movimento é um rock grande
			Position sourceT = new Position(source.getRow(), source.getColumn() - 4);// movendo a torre na msm coluna e
																						// linha do rei, origem
			Position targetT = new Position(source.getRow(), source.getColumn() - 1);// movendo a torre na msm coluna e
																						// linha do rei, destino
			ChessPiece rook = (ChessPiece) board.removePiece(sourceT);// retirando a torre do local de origem
			board.placePiece(rook, targetT);// coloca a torre na poicao de destino
			rook.increaseMoveCount();// movendo a torre, inclementa a contidade de movimento.
		}
		// #Specialmove en passant
		if (p instanceof Pawn) {// test se peca movi é um peao
			if (source.getColumn() != target.getColumn() && capturedPiece == null) {// verif. se peão moveu diagonal n capit peca.																		// 
				Position pawPosition;
				if (p.getColor() == Color.WHITE) {// teste s peca movida branca, se sim vai captura peca baixo n matriz
					pawPosition = new Position(target.getRow() + 1, target.getColumn());// posicao d peao q vai ser cap
				} 
				else {
					pawPosition = new Position(target.getRow() - 1, target.getColumn());// posicao do Peão que vai ser capturado// 
				}
				capturedPiece = board.removePiece(pawPosition);// removendo peao do tabuleiro
				capturedPieces.add(capturedPiece);// adicionando na lista de pecas captu.
				piecesOnTheBoard.remove(capturedPiece);// removendo do tabuleito
			}
		}
		return capturedPiece;
	}

	private void undoMove(Position source, Position target, Piece capturedPiece) {// metodo para desfazer movimento

		ChessPiece p = (ChessPiece) board.removePiece(target);
		p.decreaseMoveCount();// chamando o metdodo de subtracao conforme desfaz movimento
		board.placePiece(p, source);

		if (capturedPiece != null) {// devolvendo a peca no tabuleiro na posicao de destino
			board.placePiece(capturedPiece, target);
			capturedPieces.remove(capturedPiece);// retira da lista de peca capt
			piecesOnTheBoard.add(capturedPiece);// add na lista de peca no tabuleiro
		}
		// #specialmove castling kingside rook
		if (p instanceof King && target.getColumn() == source.getRow() + 2) {// test se movimento é um rock pequeno p/
																				// desfazer
			Position sourceT = new Position(source.getRow(), source.getColumn() + 3);// movendo a torre na msm coluna e
																						// linha do rei, origem
			Position targetT = new Position(source.getRow(), source.getColumn() + 1);// movendo a torre na msm coluna e
																						// linha do rei, destino
			ChessPiece rook = (ChessPiece) board.removePiece(targetT);// retirando a torre do local de destino
			board.placePiece(rook, sourceT);// coloca a torre de volta ao local de origem
			rook.decreaseMoveCount();// colocando a torre no lugar de volta e decrementando movimento
		}
		// #specialmove castling queenside rook
		if (p instanceof King && target.getColumn() == source.getColumn() - 2) {// test se movimento é um rock grande p/
																				// desfazer
			Position sourceT = new Position(source.getRow(), source.getColumn() - 4);// movendo a torre na msm coluna e
																						// linha do rei, origem
			Position targetT = new Position(source.getRow(), source.getColumn() - 1);// movendo a torre na msm coluna e
																						// linha do rei, destino
			ChessPiece rook = (ChessPiece) board.removePiece(targetT);// retirando a torre do local de destino
			board.placePiece(rook, sourceT);// coloca a torre na poicao de destino
			rook.decreaseMoveCount();// colocando a torre no lugar devolta e decrementando movimento
		}

		// #specialmove castling kingside rook
		if (p instanceof King && target.getColumn() == source.getColumn() + 2) {// test se movimento é um rock pequeno
			Position sourceT = new Position(source.getRow(), source.getColumn() + 3);// movendo a torre na msm coluna e
																						// linha do rei, origem
			Position targetT = new Position(source.getRow(), source.getColumn() + 1);// movendo a torre na msm coluna e
																						// linha do rei, destino
			ChessPiece rook = (ChessPiece) board.removePiece(sourceT);// retirando a torre do local de origem
			board.placePiece(rook, targetT);// coloca a torre na poicao de destino
			rook.increaseMoveCount();// movendo a torre, inclemente a contidade de movimento.
		}
		// #specialmove castling queenside rook
		if (p instanceof King && target.getColumn() == source.getColumn() - 2) {// test se movimento é um rock grande
			Position sourceT = new Position(source.getRow(), source.getColumn() - 4);// movendo a torre na msm coluna e
																						// linha do rei, origem
			Position targetT = new Position(source.getRow(), source.getColumn() - 1);// movendo a torre na msm coluna e
																						// linha do rei, destino
			ChessPiece rook = (ChessPiece) board.removePiece(sourceT);// retirando a torre do local de origem
			board.placePiece(rook, targetT);// coloca a torre na poicao de destino
			rook.increaseMoveCount();// movendo a torre, inclementa a contidade de movimento.
		}
		// #Specialmove en passant
		if (p instanceof Pawn) {// test se peca movi é um peao
			if (source.getColumn() != target.getColumn() && capturedPiece == enPassantVulnerable) {// verif se peão moveu n diagonal é peca en passant
				ChessPiece paw = (ChessPiece)board.removePiece(target);// removendo peca do lugar errado
				Position pawPosition;
				if (p.getColor() == Color.WHITE) {// test s peca movida é branca,sim vai captura peca baixo n matriz
					pawPosition = new Position(3, target.getColumn());// posicao do peao que vai ser recolocado
				} 
				else {
					pawPosition = new Position(4, target.getColumn());// posicao d Peão q vai ser recolocado
				}
				board.placePiece(paw, pawPosition);// colocando a peca no local corretodo tabuleiro }
			}
		}
	}

	private void ValidateSourcePosition(Position position) {// validar se existe uma peca na posição de origem

		if (!board.thereIsAPiece(position)) {
			throw new ChessException("There is no piece on source position");
		}
		if (currentPlay != ((ChessPiece) board.piece(position)).getColor()) {
			throw new ChessException("The chosen piece is not yours");
		}
		if (!board.piece(position).isThereAnyPossibleMove()) {// segunda verificação de peça de origem
			throw new ChessException("There is no piece on source position");
		}
	}

	private void ValidateTargetPosition(Position source, Position target) {// valida se pos. de destino é valid. em
																			// relacao de origim
		if (!board.piece(source).possibleMove(target)) {// se para peca de origim n for movimento possivel, n pode mexe
														// p/ posicao
			throw new ChessException("The chosen piece can't move to target position");
		}
	}

	private void nexTurn() {// metodo de troca de turno
		turn++;
		currentPlay = (currentPlay == Color.WHITE) ? Color.BLACK : Color.WHITE;// se jp for igual a branco então agora
																				// vai ser branco, caso contrario vai
																				// ser branco
	}


	private Color opponent(Color color) {// metodo p/ devolver o oponente de uma cor
		return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}

	private ChessPiece king(Color color) {// mmetodo que localiza o rei de uma determinada cor
		List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece) x).getColor() == color)
				.collect(Collectors.toList());// filtrando a lista
		for (Piece p : list) {// para cada p na lista
			if (p instanceof King) {// se a peca p é uma intancia de rei
				return (ChessPiece) p;// encontrei o rei
			}
		}
		throw new IllegalStateException("There is no " + color + " King on the board");// não vai ser tratado, pois este
																						// erro não pode aparecer.
	}

	private boolean testCheck(Color color) {// testando se o rei esta em check
		Position kingPosition = king(color).getChessPosition().toPosition();// pegando a posicao do rei em formato de
																			// matriz
		List<Piece> opponetPiece = piecesOnTheBoard.stream().filter(x -> ((ChessPiece) x).getColor() == opponent(color))
				.collect(Collectors.toList()); // lista das pecas do oponente desta cor
		for (Piece p : opponetPiece) {// testando se existe algum moviemnto possivel na lista que leva a posicao do
										// rei
			boolean[][] mat = p.possibleMoves();// matris de moviemntos possiveis da peca p
			if (mat[kingPosition.getRow()][kingPosition.getColumn()]) {// se peca p na linha e coluna do rei for vedd,
																		// rei ta em check
				return true;
			}
		}
		return false;// se esgota o teste, rei nao ta em check
	}

	private boolean testCheckMate(Color color) {// testando se partida esta em check mate
		if (!testCheck(color)) {// pequeno teste para ver se não esta sem chek
			return false;
		}
		List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece) x).getColor() == color)
				.collect(Collectors.toList());// filtrando as pecas do tabuleiro que for da msm cor
		for (Piece p : list) {// se tiver alguma peca p da lista que possia o movimento q tira do chek, é
								// falso
			boolean[][] mat = p.possibleMoves();// verifica possibilidade de movimento da matriz
			for (int i = 0; i < board.getRow(); i++) {// percorre a linha da matrizcriada
				for (int j = 0; j < board.getColumns(); j++) {// percorre a coluna da matriz criada
					if (mat[i][j]) {// testando se posicao da matriz é movimento possivel e tira do check
						Position source = ((ChessPiece) p).getChessPosition().toPosition();// posiccao da peca p
						Position target = new Position(i, j);// posicao i j que é o moviemnto posivel
						Piece capPiece = makeMove(source, target);// movimentando a peca da origem para o destino
						boolean testCheck = testCheck(color);// testando se o rei anda esta sem check
						undoMove(source, target, capPiece);// desfazendo o movimento realizado no teste.
						if (!testCheck) {// se não esta em check, o moviemnto tirou o rei d chek
							return false;
						}
					}
				}
			}
		}
		return true;// se não tiver nem um movimento que tira do check é verdadeiro checkMate
	}

	private void placeNewPiece(char column, int row,
			ChessPiece piece) {/* operação que passa a na cordenada do xadrez e não matriz */
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
		piecesOnTheBoard.add(piece);
	}

	private void initialStep() {/*
								 * res. por iniciar a partida de xadrez e coloca a peça no tabuleiro do xadrez
								 */

		placeNewPiece('a', 1, new Rook(board, Color.WHITE));
		placeNewPiece('b', 1, new Knight(board, Color.WHITE));
		placeNewPiece('c', 1, new Bishop(board, Color.WHITE));
		placeNewPiece('d', 1, new Queen(board, Color.WHITE));
		placeNewPiece('e', 1, new King(board, Color.WHITE, this));// tem que informa a partida
		placeNewPiece('f', 1, new Bishop(board, Color.WHITE));
		placeNewPiece('g', 1, new Knight(board, Color.WHITE));
		placeNewPiece('h', 1, new Rook(board, Color.WHITE));
		placeNewPiece('a', 2, new Pawn(board, Color.WHITE, this));// tem que informa a partida
		placeNewPiece('b', 2, new Pawn(board, Color.WHITE, this));
		placeNewPiece('c', 2, new Pawn(board, Color.WHITE, this));
		placeNewPiece('d', 2, new Pawn(board, Color.WHITE, this));
		placeNewPiece('e', 2, new Pawn(board, Color.WHITE, this));
		placeNewPiece('f', 2, new Pawn(board, Color.WHITE, this));
		placeNewPiece('g', 2, new Pawn(board, Color.WHITE, this));
		placeNewPiece('h', 2, new Pawn(board, Color.WHITE, this));

		placeNewPiece('a', 8, new Rook(board, Color.BLACK));
		placeNewPiece('b', 8, new Knight(board, Color.BLACK));
		placeNewPiece('c', 8, new Bishop(board, Color.BLACK));
		placeNewPiece('d', 8, new Queen(board, Color.BLACK));
		placeNewPiece('e', 8, new King(board, Color.BLACK, this));
		placeNewPiece('f', 8, new Bishop(board, Color.BLACK));
		placeNewPiece('g', 8, new Knight(board, Color.BLACK));
		placeNewPiece('h', 8, new Rook(board, Color.BLACK));
		placeNewPiece('a', 7, new Pawn(board, Color.BLACK, this));
		placeNewPiece('b', 7, new Pawn(board, Color.BLACK, this));
		placeNewPiece('c', 7, new Pawn(board, Color.BLACK, this));
		placeNewPiece('d', 7, new Pawn(board, Color.BLACK, this));
		placeNewPiece('e', 7, new Pawn(board, Color.BLACK, this));
		placeNewPiece('f', 7, new Pawn(board, Color.BLACK, this));
		placeNewPiece('g', 7, new Pawn(board, Color.BLACK, this));
		placeNewPiece('h', 7, new Pawn(board, Color.BLACK, this));

	}
}
