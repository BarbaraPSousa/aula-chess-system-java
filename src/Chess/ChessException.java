package Chess;

import boardgame.BoardException;

public class ChessException extends BoardException{//trocado para facilitar a captura de erros.
	private static final long serialVersionUID = 1L;
	
	public ChessException(String msg) {
		super(msg);
	}

}
