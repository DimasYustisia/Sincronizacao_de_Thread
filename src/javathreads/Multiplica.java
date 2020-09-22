
package javathreads;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Multiplica extends Thread{
    private String nome;
    private int valorMultiplicar;
    private int tempo;
    private boolean parada;
    private boolean fim;
    
    public Multiplica(String nome, int valor){
    this.nome = nome;
    this.valorMultiplicar = valor;
    this.parada = false;
    this.fim = false;
    this.start();
    }
    
    public void run(){
        System.out.println("Nome: " +this.nome);
        
        for(int x=0; x<=10;x++){
            System.out.println(this.valorMultiplicar + " x " + x + " = " + (this.valorMultiplicar * x) );
            try {
                this.sleep(tempo);
                
                while(parada){
                    System.out.println("Parado Aqui: " + this.nome);
                    synchronized (this){
                    wait();
                    } 
                   }
                if(fim){
                        System.out.println("Fechando Thread");
                        interrupt();
                        break;
                }
                
            } catch (InterruptedException ex) {
                Logger.getLogger(Multiplica.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        System.out.println("Fim da Thread! " + this.nome);
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getValorMultiplicar() {
        return valorMultiplicar;
    }

    public void setValorMultiplicar(int valorMultiplicar) {
        this.valorMultiplicar = valorMultiplicar;
    }

    public int getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }
    
    public synchronized void parar(){
        this.parada = true;
        notify();
    }
    
    public synchronized void voltar(){
        this.parada = false;
        notify();
    }
    
     public synchronized void sair(){
        this.fim = true;
        notify();
    }
}
