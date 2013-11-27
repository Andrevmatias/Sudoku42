package br.edu.ufsc.sudoku42.view;

import java.awt.Dimension;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PainelJogador extends JPanel{
	
	protected static final String TEMPLATE_TIME_1 =    ":";
    protected static final String TEMPLATE_TIME_2 =    ":0";
   
    protected String nome;
    protected JLabel tempo;
    protected JLabel pontuacao;
   
    protected int minutos = 10;
    protected int segundos = 0;

    protected Timer timer;
   
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
