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

}
