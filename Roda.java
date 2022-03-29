package SimuladorDeCorridas;

import java.util.Random;

/**
 *
 * @author Paula Graziela Militão Valadares
 */
public class Roda {
    
    private boolean calibragemPneu;
    
    //construtor
    public void Roda(){
    }
    
    //método que retorna a calibragem da roda
    public boolean getCalibragem(){
        return calibragemPneu;
    }
    
    //método que define a calibragem da roda
    public void setCalibragem(){
        Random r = new Random ( ) ;
        int numero = r.nextInt(100);
        //true é quando o pneu está calibrado
        if(numero%2 == 0){
            this.calibragemPneu = true;
        }
        else{
            this.calibragemPneu = false;
        }
    }
    
    //método que força todas as rodas a estarem calibradas
    public void calibrarPneuTrue(){
        this.calibragemPneu = true;
    }
}
