package br.edu.ufsc.sudoku42.view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

import br.edu.ufsc.sudoku42.model.CampoOcupadoException;
import br.edu.ufsc.sudoku42.network.NetworkException;

public class PainelTabuleiro extends JPanel implements ActionListener{

	private static final long serialVersionUID = -2264671741976702649L;
	
	
	protected JPanel [] regioes;
	protected BotaoCampo[][] campos;
	protected InterfaceJogador interfaceJogador;
	
	public PainelTabuleiro(InterfaceJogador interfaceJogador) {
		this.interfaceJogador = interfaceJogador;
		this.setLayout(new GridLayout(3, 3));
		this.instanciarCampos();
		this.iniciar();
		
		this.bloquearCampos();
	}
	
	
	private void iniciar(){
		UIManager.put("Button.disabledText", new ColorUIResource(Color.BLACK));
		
		this.regioes = new JPanel[9];
		
		for(int i = 0; i < regioes.length; i++){
			regioes[i] = new JPanel();
			
			regioes[i].setLayout(new GridLayout(3, 3));
			regioes[i].setBorder(BorderFactory.createLineBorder(Color.black));
			
			this.adicionarCampos(regioes[i], i);
			
			this.add(regioes[i]);
		}
		
		
	}
	
	private void instanciarCampos(){
		this.campos = new BotaoCampo[9][9];
		for(int i = 0; i < campos.length; i++){
			for(int j = 0; j < campos[i].length; j++){
				this.campos[i][j] = new BotaoCampo(i, j);
				
				this.campos[i][j].addActionListener(this);
			}
		}
	}
	
	public void bloquearCampos(){
		for(BotaoCampo []i: campos){
			for(BotaoCampo j: i){
				j.setEnabled(false);
				j.setForeground(Color.BLACK);
			}
		}
	}
	
	public void desbloquearCampos(){
		for(BotaoCampo []i: campos){
			for(BotaoCampo j: i){
				j.setEnabled(true);
				
			}
		}
		
		campos[4][4].setEnabled(false);
	}
	
	
	public void ocuparCampo(int linha, int coluna, int valor, Color cor){
		this.campos[linha][coluna].ocupar(valor, cor);
	}
	
	private void adicionarCampos(JPanel painel, int index){
		switch (index) {
		case 0:
			for(int i = 0; i < 3; i++){
				for(int j = 0; j < 3; j++){
					painel.add(campos[i][j]);
				}
			}
			break;

		case 1:
			for(int i = 0; i < 3; i++){
				for(int j = 3; j < 6; j++){
					painel.add(campos[i][j]);
				}
			}
			
			break;
			
		case 2:
			for(int i = 0; i < 3; i++){
				for(int j = 6; j < 9; j++){
					painel.add(campos[i][j]);
				}
			}
			break;

		case 3:
			for(int i = 3; i < 6; i++){
				for(int j = 0; j < 3; j++){
					painel.add(campos[i][j]);
				}
			}
			
			break;
		case 4:
			for(int i = 3; i < 6; i++){
				for(int j = 3; j < 6; j++){
					painel.add(campos[i][j]);
				}
			}
			break;

		case 5:
			for(int i = 3; i < 6; i++){
				for(int j = 6; j < 9; j++){
					painel.add(campos[i][j]);
				}
			}
			
			break;
		case 6:
			for(int i = 6; i < 9; i++){
				for(int j = 0; j < 3; j++){
					painel.add(campos[i][j]);
				}
			}
			break;

		case 7:
			for(int i = 6; i < 9; i++){
				for(int j = 3; j < 6; j++){
					painel.add(campos[i][j]);
				}
			}
			
			break;
			
		case 8:
			for(int i = 6; i < 9; i++){
				for(int j = 6; j < 9; j++){
					painel.add(campos[i][j]);
				}
			}
			
			break;
			
		default:
			break;
		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		BotaoCampo campo = (BotaoCampo)e.getSource();
		System.out.println(campo.getI() + "campo j é"+ campo.getJ());
		try {
			interfaceJogador.realizarLance(campo.getI(), campo.getJ());
			
		} catch (NetworkException | CampoOcupadoException e1) {
			interfaceJogador.notificarErro(e1.getMessage());
		}
	}


	public void limparCampos() {
		for(BotaoCampo []i: campos){
			for(BotaoCampo j: i){
				j.limpar();
			}
		}
	}
	


	

}
