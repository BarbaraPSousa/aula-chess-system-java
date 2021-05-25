/*Peça Rei*/

package Chess.pieces;

import Chess.ChessMatch;
import Chess.ChessPiece;
import Chess.Color;
import boardgame.Board;
import boardgame.Position;

public class King extends ChessPiece {
	
	private ChessMatch chessMatch;

	public King(Board board, Color color, ChessMatch chessMatch) {
		super(board, color);
		this.chessMatch = chessMatch;
	}	
	
	private boolean canMove(Position position) {//metodo para verificar se o rei pode move p/ determ
		ChessPiece p = (ChessPiece)getBoard().piece(position);
		return p == null || p.getColor() != getColor();
	}
	
	public boolean testRookCastling(Position position) {//testa se a torre esta apta para o rock
		ChessPiece p = (ChessPiece)getBoard().piece(position);
		return p != null && p instanceof Rook && p.getColor() == getColor() && p.getMoveCount() == 0;//se a peca for diferente de nulo e se é uma torre e se a cor da torre é igual do rei e se movimento é igual a zero.
	}

	@Override
	public boolean[][] possibleMoves() {//movimento rei	basico 			
		boolean[][]mat = new boolean[getBoard().getRow()][getBoard().getColumns()];
		
		Position p = new Position(0,0);
		
		//above
		p.seteValue(position.getRow() -1, position.getColumn());
		if(getBoard().positionExists(p) && canMove(p) ) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//below
		p.seteValue(position.getRow() +1, position.getColumn());
		if(getBoard().positionExists(p) && canMove(p) ) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//left
		p.seteValue(position.getRow(), position.getColumn() -1);
		if(getBoard().positionExists(p) && canMove(p) ) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//right
		p.seteValue(position.getRow(), position.getColumn() + 1);
		if(getBoard().positionExists(p) && canMove(p) ) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		//nw(NO)
		p.seteValue(position.getRow() -1, position.getColumn() -1);
		if(getBoard().positionExists(p) && canMove(p) ) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		//ne(NE)
		p.seteValue(position.getRow() -1, position.getColumn() +1);
		if(getBoard().positionExists(p) && canMove(p) ) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		//sw(SO)
		p.seteValue(position.getRow() +1, position.getColumn() -1);
		if(getBoard().positionExists(p) && canMove(p) ) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		//se(SE)
		p.seteValue(position.getRow() +1, position.getColumn() +1);
		if(getBoard().positionExists(p) && canMove(p) ) {
			mat[p.getRow()][p.getColumn()] = true;
		}	
		
		//#specialmove castling
		 if (getMoveCount() == 0 && !chessMatch.getCheckMate()){//test se o movimento do rei é igual a zero e se partida não esta em check
			 //Specialmove castling Kingside rook
			 Position positionT1 = new Position(position.getRow(), position.getColumn() +3);//instanciando a posicao da torre do rei a direita
			 if(testRookCastling(positionT1)) {//test se nesta posicao realmente tem uma torre liberada para rock 
				 Position p1 = new Position(position.getRow(), position.getColumn() +1);//verificando 1 casa direita esta vazia
				 Position p2 = new Position(position.getRow(), position.getColumn() +2);//verificando 2 casa direita esta vazia
				 if(getBoard().piece(p1) == null && getBoard().piece(p2) == null) {//tes para saber se tem pecas a direita
					 mat[positionT1.getRow()][position.getColumn() +2] = true;//incluindo na matriz de movimento posivel do rei. 
				 }			 
			 }
				 //Specialmove castling queenside rook
				 Position positionT2 = new Position(position.getRow(), position.getColumn() -4);//instanciando a posicao da torre do rei a esquedar
				 if(testRookCastling(positionT2)) {//test se nesta posicao realmente tem uma torre liberada para rock na esquerda
					 Position p1 = new Position(position.getRow(), position.getColumn() -1);//verificando 1 casa esquerda
					 Position p2 = new Position(position.getRow(), position.getColumn() -2);//verificando 2 casa esquerda
					 Position p3 = new Position(position.getRow(), position.getColumn() -3);//verificando 3 casa esquerda
					 if(getBoard().piece(p1) == null && getBoard().piece(p2) == null && getBoard().piece(p3) == null){//tes para saber se tem pecas a esquerda
						 mat[positionT1.getRow()][position.getColumn() -2] = true;//incluindo na matriz de movimento posivel do rei a esquerda 
					 }
				 } 
			}			 
		return mat;
	}

	@Override
	public String toString() {
		return "k";
	}
}
