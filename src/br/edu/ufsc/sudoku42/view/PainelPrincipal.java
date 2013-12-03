package br.edu.ufsc.sudoku42.view;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class PainelPrincipal extends JPanel {
	
private static final long serialVersionUID = -4274691600196025428L;
	
	protected PainelTabuleiro painelTabuleiro; 
	protected PainelJogador jogador1;
	protected PainelJogador jogador2;
	
	protected JPanel painelTempo;
	protected InterfaceJogador interfaceJogador;
	
	
	public PainelPrincipal( InterfaceJogador interfaceJogador) {
		
		this.interfaceJogador = interfaceJogador;
		this.painelTabuleiro = new PainelTabuleiro();
		this.add(painelTabuleiro);
		
		this.jogador1 = new PainelJogador("Jogador 1");
		this.jogador2 = new PainelJogador("Jogador 2");
		
		this.definirPainelTempo();
		this.add(painelTempo);
		
	}
	
	public void definirPainelTempo(){
		this.painelTempo = new JPanel();
		this.painelTempo.setLayout(new BoxLayout(painelTempo, BoxLayout.PAGE_AXIS));
		
		this.painelTempo.add(jogador1);
		this.painelTempo.add(jogador2);
	}
}
