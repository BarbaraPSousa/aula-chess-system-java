package boardgame;

import javax.swing.border.Border;

public class Piece {

	protected Position position; /*Como a possi��o e simples de matriz utilizamos  protected para n�o ser visivel na cama de xadrez*/
	private Board board;
	
	
	public Piece(Board board) { /*Construtor sem posi��o, pq vai iniciar como null dizendo que pessa n�o foi colocada no tabuleiro*/
		this.board = board;
		//position = null;
	}

	protected Board getBoard() { /*n�o vai ser possivel alterar o taubeiro por isso apenas o metodo get mas protect pois assim somente class e subclass do msm pacote vai acessar o tabuleiro de uma piece*/
		return board;
	}

	
	
	
	
	






}


