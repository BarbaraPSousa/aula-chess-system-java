package boardgame; //pacote correspondente a camada de tabuleiro

public class Position {
	
	private int row;
	private int column;
	
	public Position(int row, int column) { 
		this.row = row;
		this.column = column;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}
	
	public void seteValue(int row, int column) {//recebe novos valor para linha e coluna
		this.row = row;
		this.column = column;
	}
	
	@Override
	public String toString() { 
		return row + ", " + column;
	}
	

}
