package br.edu.ufsc.sudoku42.network;

public class NetworkException extends Exception {

	private static final long serialVersionUID = -8687735283706391279L;

	public NetworkException() {
	}

	public NetworkException(String message) {
		super(message);
	}

	public NetworkException(Throwable cause) {
		super(cause);
	}

	public NetworkException(String message, Throwable cause) {
		super(message, cause);
	}

}
