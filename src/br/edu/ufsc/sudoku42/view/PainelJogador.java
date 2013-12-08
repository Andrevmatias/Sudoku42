package br.edu.ufsc.sudoku42.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class PainelJogador extends JPanel{
	private static final long serialVersionUID = 7663556092007813436L;

	protected static final String TEMPLATE_TIME_1 =    ":";
	protected static final String TEMPLATE_TIME_2 =    ":0";

	protected String nome;

	protected JLabel tempo;
	protected JLabel pontuacao;

	protected int minutos;
	protected int segundos;

	protected Timer timer;

	protected InterfaceJogador interfaceJogador;


	public PainelJogador(String nome, InterfaceJogador interfaceJogador) {
		this.interfaceJogador = interfaceJogador;

		this.nome = nome;
		initialize();
	}

	public void initialize() {
		this.setBorder(BorderFactory.createTitledBorder(this.nome));

		this.tempo = new JLabel("00:00");
		this.pontuacao = new JLabel("00");
		this.add(tempo);
		this.add(pontuacao);
		this.setPreferredSize(new Dimension(100,40));
		inicializarTimer();
	}

	private void inicializarTimer() {
		timer = new Timer(1000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				printTime();
			}
		});

		timer.setInitialDelay(0);  
		timer.setRepeats(true); 
	}

	public void dispararTimer(int segundosRestantes){
		this.converterFormato(segundosRestantes);
		timer.start();  
	}

	public int pausarTimer(){
		timer.stop();
		return ((minutos*60)+ segundos);
	}

	private void converterFormato(int segundosRestantes){
		this.minutos = (int)segundosRestantes /60; 
		this.segundos = segundosRestantes %60; 
	}


	private void printTime() {
		StringBuilder text = new StringBuilder();

		if(segundos <= 0){
			minutos--;
			segundos = 59;
		} else {
			segundos --;
		}

		if(minutos == -1 && segundos == 59){
			this.pausarTimer();
			this.notificarRelogioZerado();
		} else {

			text.append(minutos);

			if(segundos< 10){
				text.append(TEMPLATE_TIME_2);
			} else {
				text.append(TEMPLATE_TIME_1);
			}
			text.append(segundos);

			tempo.setText(text.toString());
		}
	}

	public void configurarPainelJogador(String nome, Color cor){
		this.nome = nome;
		this.setBorder(BorderFactory.createTitledBorder(this.nome));
		this.setBackground(cor);

		this.repaint();
	}

	public String getNome(){
		return this.nome;
	}

	public void setPontuacao(int valor){
		this.pontuacao.setText(String.valueOf(valor));
	}

	private void notificarRelogioZerado(){
		this.interfaceJogador.notificarRelogioZerado();
	}
}
