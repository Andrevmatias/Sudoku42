package br.edu.ufsc.sudoku42.view;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PainelJogador extends JPanel{
	
	protected String nome;
	protected JLabel tempo;
	protected JLabel pontuacao;
	
	public PainelJogador(String nome) {
		this.nome = nome;
		initialize();

	}

	public void initialize() {
		this.setBorder(BorderFactory.createTitledBorder(this.nome));
		
		this.tempo = new JLabel("00:00");
		this.pontuacao = new JLabel("99");
		this.add(tempo);
		this.add(pontuacao);
		this.setPreferredSize(new Dimension(100,40));
	}
	
}
