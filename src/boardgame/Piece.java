package boardgame;import java.awt.Point;

public abstract class Piece {

	protected Position position; /*Como a possição e simples de matriz utilizamos  protected para não ser visivel na cama de xadrez*/
	private Board board;
	
	
	public Piece(Board board) { /*Construtor sem posição, pq vai iniciar como null dizendo que pessa não foi colocada no tabuleiro*/
		this.board = board;
		//position = null;
	}

	protected Board getBoard() { /*não vai ser possivel alterar o taubeiro por isso apenas o metodo get mas protect pois assim somente class e subclass do msm pacote vai acessar o tabuleiro de uma piece*/
		return board;
	}
	
	public abstract boolean[][] possibleMoves();//implementada na class das ChessPiece
	
	public boolean possibleMove(Position position) { // testando se a peca pode mover p/ uma determinada posica
		return possibleMoves()[position.getRow()][position.getColumn()];		
	}
	public boolean isThereAnyPossibleMove() {//verifica se existir movimento p/ piece e não travada
		boolean[][] mat = possibleMoves();
		for(int i = 0; i < mat.length; i++) {
			for(int j = 0; j < mat.length; j++) {
				if(mat[i][j]) {//se matriz na linha I e coluna J,for vdd retorna true
					return true;
				}
			}
		}
		return false;
	}
}


