package br.edu.ufsc.sudoku42.view;

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

   
    public PainelJogador(String nome) {
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
        
//        this.iniciarTimer();
       
    }
   
    public void iniciarTimer(){
    	this.converterFormato(600);
    	
        Timer timer = new Timer(1000, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				printTime();
				
			}
		});

        timer.setInitialDelay(0);  
        timer.setRepeats(true);  
        timer.start();  
    }
    
    public void pausarTimer(){
    	timer.stop();
    }
   
    public void converterFormato(int segundosRestantes){
    	this.minutos = (int)segundosRestantes /60; 
    	this.segundos = segundosRestantes %60; 
    }


    private void printTime() {
        StringBuilder text = new StringBuilder("0");

        if(segundos <= 0){
            minutos--;
            segundos = 59;
        } else {
            segundos --;
        }
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
