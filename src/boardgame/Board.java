package boardgame;

public class Board {

	private int rows; // Linha
	private int columns; // coluna
	private Piece[][] pieces;// matriz de pecas

	public Board(int rows, int columns) {
		if (rows < 1 || columns < 1) {
			throw new BoardException("Error creating borard: there must be at least 1 row and 1 column");
		}
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns]; // matris de pecas vai ser intanciada com a contidade de linha e colonas
											// informadas
	}

	public int getRow() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}
	// retirado sets para n�o ser possivel futuras altera��es

	/* Metodos que retorna a posi��o dada de uma linha e uma coluna */

	public Piece piece(int row, int column) {/* retorna a matriz na pieces na linha e coluna */
		if(!positionExists(row,column)){
			throw new BoardException("Position not on the board");
		}
		return pieces[row][column];
	}

	public Piece piece(Position position) {/* retornando a peca pela posi��o linhae coluna */
		if (!positionExists(position)) {
			throw new BoardException("Position not on the board");
		}
		return pieces[position.getRow()][position.getColumn()];
	}

	public void placePice(Piece piece, Position position) {// informa a posi��o da pe�a no tabuleiro
		if(thereIsAPiece(position)) {
			throw new BoardException("There is already a piece on position " + position);
		}
		pieces[position.getRow()][position.getColumn()] = piece;
		piece.position = position;// informando que a pe�a n�o � mas null

	}
	/* Metodo que valida se a posi��o existe */

	private boolean positionExists(int row, int column) {// verifica exixtem a linha e a coluna
		return row >= 0 && row < rows && column >= 0 && column < columns;
	}

	public boolean positionExists(Position position) {// verifica se existe uma possi��o
		return positionExists(position.getRow(), position.getColumn());
	}

	public boolean thereIsAPiece(Position position) {// verificando se ja tem uma pe�a na posi��o
		if(!positionExists(position)) {
			throw new BoardException("Position not on the board");
		}
		return piece(position) != null;
	}
}

