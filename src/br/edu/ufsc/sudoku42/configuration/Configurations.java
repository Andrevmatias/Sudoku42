package br.edu.ufsc.sudoku42.configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public final class Configurations {

	private static String ipServidor;
	private static int tempoJogador;

	static{
		Properties prop = new Properties();
		 
    	try {
    		prop.load(new FileInputStream("config.properties"));
            ipServidor = prop.getProperty("ipServidor");
            tempoJogador = Integer.parseInt(prop.getProperty("tempoJogador"));
    	} catch (IOException ex) {
    		throw new LoadConfigurationException("Erro ao carregar configurações", ex);
        }
	}
	
	public static String getIpServidor(){
		return ipServidor;
	}
	
	public static int getTempoJogador(){
		return tempoJogador;
	}
}
