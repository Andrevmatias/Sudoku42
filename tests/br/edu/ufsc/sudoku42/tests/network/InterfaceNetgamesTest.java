package br.edu.ufsc.sudoku42.tests.network;

import static org.junit.Assert.*;

import org.junit.Test;

import br.edu.ufsc.sudoku42.model.Tabuleiro;
import br.edu.ufsc.sudoku42.network.InterfaceNetgames;
import br.edu.ufsc.sudoku42.network.NetworkException;

public class InterfaceNetgamesTest {

	@Test
	public void testIniciarNovaPartida() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testReceberJogada() {
		fail("Not yet implemented");
	}

	@Test
	public void testEnviarJogada() throws NetworkException {
		InterfaceNetgames netGames = new InterfaceNetgames(new Tabuleiro());
		netGames.conectar("EU");
		netGames.desconectar();
	}

	@Test
	public void testIniciarPartida() throws NetworkException {
		InterfaceNetgames netGames = new InterfaceNetgames(new Tabuleiro());
		netGames.conectar("EU");
		netGames.iniciarPartida();
		netGames.desconectar();
	}

	@Test
	public void testConectarDesconectar() throws NetworkException {
		InterfaceNetgames netGames = new InterfaceNetgames(new Tabuleiro());
		netGames.conectar("EU");
		netGames.desconectar();
	}

	@Test
	public void testDesconectar() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetNomeJogadorRemoto() {
		fail("Not yet implemented");
	}

}
