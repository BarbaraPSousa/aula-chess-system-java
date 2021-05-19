package boardgame;

public class Board {
	
	private int row; //Linha
	private int columns; // coluna
	private Piece[][] pieces;//matriz de pecas
	
	public Board(int row, int columns) {
		this.row = row;
		this.columns = columns;
		pieces = new Piece[row][columns]; // matris de pecas vai ser intanciada com a contidade de linha e colonas informadas
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}
	/*Metodos que retorna a posição dada de uma linha e uma coluna */
	
	public Piece piece(int row, int column) {/*retorna a matriz na pieces na linha e coluna  */
		return pieces[row][column];
	}
	
	public Piece piece(Position position) {/*retornando a peca pela posição linhae coluna*/
		return pieces[position.getRow()][position.getColumn()];
	}
	
	public void placePice(Piece piece, Position position) {// informa a posição da peça no tabuleiro
		pieces[position.getRow()][position.getColumn()] = piece;
		piece.position = position;//informando que a peça não é mas null
		
	}
}
