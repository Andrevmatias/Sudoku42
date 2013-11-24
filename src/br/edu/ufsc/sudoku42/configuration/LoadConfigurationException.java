package br.edu.ufsc.sudoku42.configuration;

public class LoadConfigurationException extends RuntimeException {

	private static final long serialVersionUID = -8057947961750043732L;

	public LoadConfigurationException() {
	}

	public LoadConfigurationException(String arg0) {
		super(arg0);
	}

	public LoadConfigurationException(Throwable arg0) {
		super(arg0);
	}

	public LoadConfigurationException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public LoadConfigurationException(String arg0, Throwable arg1,
			boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

}
