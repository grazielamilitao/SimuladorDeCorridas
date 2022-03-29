package SimuladorDeCorridas;

import java.util.Random;

/**
 *
 * @author Graziela Militão
 */

public class Veiculos {
    
    private int codigo;
    private int distanciaPercorrida;
    private float combustivel = (float) 3.5;
    private static float valor = 500; //valor do IPVA
    private boolean IPVA;
    private Roda [] rodas;
    private boolean [] statusRoda; //false: não vazias, true: vazias
    
    //construtor
    public Veiculos(){
        infoIPVA();
        //aqui alocamos espaço na memoria para o objeto de roda
        rodas= new Roda[4];
        
        for(int i=0; i<4; i++){
            rodas[i] = new Roda();
        }
       
        //aqui inicializamos os pneus como false, pois NÃO estão vazias
        statusRoda = new boolean[4];
        
        for(int i=0; i<4; i++){
            statusRoda[i] = false;
        }
    }
    
    // esse metodo apenas gera o código, não adiciona o veiculo no vetor
    public void gerarCodigo(Veiculos []veiculo){
         /*o vetor de objetos de Veiculos é para saber quantos objetos já foram gerados e em qual posição
         não há objetos cadastrados*/
        int i = 0;
        
        if(veiculo[i]==null){
            this.codigo = 0;
        }
        else{
            for(i = 0; veiculo[i]!=null; i++){
            } 
            this.codigo = i;
        }
        
        if(i==19){
            System.out.println("Não há mais espaço no vetor.");
        }
    }
    
    /*esse método é usado no método incluirVeiculo na classe Simulador, pois precisa-se saber o cod do veiculo
    para saber em que posição ele vai ser incluido*/
    public int returnCodigo(){
        return codigo;
    }
    
    //metodo para saber se o IPVA já está pago ou não, onde tal informação é definida através de um sorteio aleatorio
    public void infoIPVA(){
        for(int i=0; i<4; i++){
            Random r = new Random ( ) ;
            int numero = r.nextInt(100);
            //true é quando o IPVA está pago
            if(numero%2 == 0)
                this.IPVA = true;
            else
                this.IPVA = false;
        }
    }
    
    //método para forçar o IPVA a ser pago (caso ele não seja pago no sorteio aleatorio)
    public void pagarIPVA(){
        this.IPVA = true;
    }
    
    //metodo que define a calibragem de um pneu atraves do metodo setCalibragem que está na classe Roda
    public void calibrarPneusEspecifico(int posicao){
        rodas[posicao].setCalibragem();
    }
    
    //metodo que define a calibragem de todos os pneus atraves do metodo setCalibragem que está na classe Roda
    public void calibrarTodasPneus(){
        for(int i=0; i<4; i++){
            rodas[i].setCalibragem();
        }
    }
    
    /*esse método é necessário pois raramente o random vai calibrar todos os pneus como true, então ele
    define todos os pneus com calibragem true. */
    public void calibrarTodasPneusTrue(){
        for(int i=0; i<4; i++){
            rodas[i].calibrarPneuTrue();
        }
    }
    
    //método para esvaziar um pneu específico
    public void esvaziarPneu(int cod){
        this.statusRoda[cod] = true;
    }
    
    //método para abaster o veiculo
    public void abastecer(float quant){
        this.combustivel = this.combustivel+quant;
    }
    
    //metodo para retonar a quantidade de combustivel presente no carro
    public float quantCombustivel(){
        return this.combustivel;
    }
    
    //metodo para consumir conbustível conforme o carro andar
    public void consumirCombustivel(int bloco){ 
        float quant = (float)bloco*(float)0.55;
        this.combustivel = this.combustivel - quant;
    }

    //metodo para mover o veiculo
    public void mover(Veiculos v, int bloco){
        /*aqui vamos fazer o carro andar quando ele ainda não andou pela primeira vez, pois o método 
        desenharCarro usado aqui imprime todas as posições do veiculo (conforme sua distanciaTotal percorrida),
        já o metodo desenharCarro usado na outra opção imprime só a posição do veicelo na distancia 
        total percorrida*/
        //logo, a diferença entre das duas opções abaixo é o método desenharCarro utilizado
        if(this.distanciaPercorrida==0){
            if(v.IPVA==true && rodas[0].getCalibragem()==true && rodas[1].getCalibragem()==true && rodas[2].getCalibragem()==true && rodas[3].getCalibragem()==true && 
               v.combustivel>=(bloco*0.55)){
                this.distanciaPercorrida = this.distanciaPercorrida+bloco;
                v.consumirCombustivel(bloco);
                v.desenharCarro(bloco);
            }  
            else if(v.IPVA==false){
                System.out.println("O IPVA não está pago.");
            }
            else if(rodas[0].getCalibragem()==false || rodas[1].getCalibragem()==false || rodas[2].getCalibragem()==false || rodas[3].getCalibragem()==false){
                System.out.println("Alguns pneus ou todos não estão calibrados.");
            }
            else if(v.combustivel>=(bloco*0.55)){
                System.out.println("Não há combustível suficiente.");
            }
        }
        else{
            if(v.IPVA==true && rodas[0].getCalibragem()==true && rodas[1].getCalibragem()==true && rodas[2].getCalibragem()==true && rodas[3].getCalibragem()==true && 
            v.combustivel>=(bloco*0.55)){
                this.distanciaPercorrida = this.distanciaPercorrida+bloco;
                v.consumirCombustivel(bloco);
                v.desenharCarro(); 
            }  
            else if(v.IPVA==false){
                System.out.println("O IPVA não está pago.");
            }
            else if(rodas[0].getCalibragem()==false || rodas[1].getCalibragem()==false || rodas[2].getCalibragem()==false || rodas[3].getCalibragem()==false){
                System.out.println("Alguns pneus ou todos não estão calibrados.");
            }
            else if(v.combustivel>=(bloco*0.55)){
                System.out.println("Não há combustível suficiente.");
            }
        }
    }
    
    //metodo usado para desenhar o carro conforme ele anda, este método é usado no método acima "mover"
    //esse metodo fala que o veiculo está parado e quantos blocos ele vai fazer no seu primeiro movimento
    public void desenharCarro(int blocos){
        System.out.print("Veículo parado:\n");
        System.out.print("    ____\n");
        System.out.print(" __/  |_ \\_\n"); // \_\n");
        System.out.print("|  _     _``-.\n");
        System.out.print("'-(_)---(_)--'\n\n");
        
        System.out.print("Veículo irá se movimentar em "+blocos+" blocos:\n");
        
        String[] carroPopular = {"    ____\n"," __/  |_ \\_\n","|  _     _``-.\n","'-(_)---(_)--'\n\n\n"};

        for(int j=0; j<blocos; j++){
            String giroCarro = "     ";
            for(int i=0;i<carroPopular.length;i++)
            carroPopular[i] = giroCarro + carroPopular[i]; 
            System.out.print(carroPopular[0]);
            System.out.print(carroPopular[1]);
            System.out.print(carroPopular[2]);
            System.out.print(carroPopular[3]);
        }
    }
    
    /*Este método será usado para imprimir a posição do carro na pista de corrida após o carro
    já ter uma certa distancia percorrida, por isso não imprime o carro na posição inicial e 
    não legenda a quantidade de blocos que o veiculo vai fazer pois ele já tem uma 
    certa distancia percorrida */
    public void desenharCarro(){
        String[] carroPopular = {"    ____\n"," __/  |_ \\_\n","|  _     _``-.\n","'-(_)---(_)--'\n\n\n"};
        int i = 0;
        for(int j=0; j<this.distanciaPercorrida; j++){
            String giroCarro = "     ";
            for(i=0;i<carroPopular.length;i++)
            carroPopular[i] = giroCarro + carroPopular[i]; 
            
            if(j+1==this.distanciaPercorrida){
                System.out.print(carroPopular[0]);
                System.out.print(carroPopular[1]);
                System.out.print(carroPopular[2]);
                System.out.print(carroPopular[3]);
            }
        }
    }
    
    //método para imprimir as informações do veiculo
    public String toString(){
        return  "Veículo código "+(codigo+1)+":"+
                "\n*Calibragem das rodas (true para calibrada e false para não calibrada):\nRoda 1:"+rodas[0].getCalibragem()+"\nRoda 2:"+rodas[1].getCalibragem()+
                "\nRoda 3:"+rodas[2].getCalibragem()+"\nRoda 4:"+rodas[3].getCalibragem()+
                "\n*IPVA está pago? (true para sim e false para não): "+IPVA+
                "\n*Quantidade de combustível: "+combustivel+" L"+
                "\n*Status de cada roda (true para vazia e false para cheia):\nRoda 1:"+statusRoda[0]+"\nRoda 2:"+statusRoda[1]
                +"\nRoda 3:"+statusRoda[2]+"\nRoda 4:"+statusRoda[3];
    }
}
