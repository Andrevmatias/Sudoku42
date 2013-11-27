package br.edu.ufsc.sudoku42.view;

import java.awt.Dimension;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PainelJogador extends JPanel{
	
	protected String nome;
	protected JLabel tempo;
	protected JLabel pontuacao;
	
	int minutos = 10;
	int segundos = 0;

	Timer timer;
	
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
		this.bla();
		
	}
	
	public void bla(){
		int interval;
		Timer timer;
		
	    int delay = 1000;
	    int period = 1000;
	    timer = new Timer();

	    timer.scheduleAtFixedRate(new TimerTask() {

	        public void run() {
	            setInterval();

	        }
	    }, delay, period);
	}
	
	


	private void setInterval() {

		if(segundos <= 0){
			minutos--;
			segundos = 59;
			
			
		} else {
			segundos --;
			
		}
		
		tempo.setText(Integer.toString(minutos) + ":" + Integer.toString(segundos));
		 
	}
}
