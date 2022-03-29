package SimuladorDeCorridas;

import java.util.Scanner;

/**
 *
 * @author Graziela Militão 
 */
public class Simulador {
    
    //método para incluir um novo veiculo na pista de corrida, incluindo ele no vetor de veiculos
    public static void incluirVeiculo(Veiculos [] veiculo, Veiculos veiculo_novo){
        int i = 0;
        
        if(veiculo[i]==null){
            veiculo_novo.gerarCodigo(veiculo);
            veiculo[0] = veiculo_novo;
            int cod = veiculo_novo.returnCodigo()+1;
            System.out.println("Veículo incluido com sucesso. Código:"+cod+"\n");
        }
        else{
            for(i = 0; veiculo[i]!=null; i++){}
            veiculo_novo.gerarCodigo(veiculo);
            veiculo[veiculo_novo.returnCodigo()] = veiculo_novo;
            int cod = veiculo_novo.returnCodigo()+1;
            System.out.println("Veículo incluido com sucesso. Código:"+cod+"\n");
        }
    }
    
    //método para remover um veiculo da pista de corrida
    public static void removerVeiculo(Veiculos [] veiculo, int cod){
        //cod-1 pois o código mostrado será o código do veiculo + 1, assim: i=9, então código será 10.
        veiculo[cod-1] = null;
        System.out.println("Veículo removido com sucesso.");
    }
    
    //menu de opção
    public static void menu(Veiculos [] veiculo){
        int opcao = 0;
	do {
                Scanner sc = new Scanner(System.in);
                
		System.out.println("\n\n###                       Competição de Veículos                        ###");
		System.out.println("                  =============================================");
		System.out.println("                  |1 - Incluir Veículo                         |");
                System.out.println("                  |2 - Remover Veículo                         |");
		System.out.println("                  |3 - Abastecer Veículo                       |");
		System.out.println("                  |4 - Movimentar Veículo                      |");
		System.out.println("                  |5 - Movimentar todos os veículos            |");
                System.out.println("                  |6 - Imprimir todos os dados dos veículos    |");
                System.out.println("                  |7 - Esvaziar um pneu específico             |");
                System.out.println("                  |8 - Definir calibragem de um pneu específico |");
                System.out.println("                  |9 - Definir Calibragem para todos os pneus  |");
                System.out.println("                  |10 - Imprimir pista de corrida              |");
                System.out.println("                  |11 - Pagar IPVA                             |");
                System.out.println("                  |12 - Calibrar todos os pneus                |");
		System.out.println("                  |0 - Sair                                    |");
		System.out.println("                  =============================================\n");
                
		opcao = sc.nextInt();
                
		System.out.print("\n");
                
                int cod, bloco, i, codpneu;
                
		switch (opcao) {
		case 1:
                    
                    Veiculos veiculo_novo = new Veiculos();
                    incluirVeiculo(veiculo, veiculo_novo);
                    
                    menu(veiculo);
                    break;
		case 2:
                    do{
                         System.out.println("Digite o código do veiculo: ");
                         cod=sc.nextInt();
                         
                         if(veiculo[cod-1]==null){
                            System.out.println("\nEste código não está atribuido a nenhum veículo. Tente novamente.\n");
                         }
                    } while(veiculo[cod-1]==null);
                    
                    removerVeiculo(veiculo, cod); 
                    
                    menu(veiculo);
                    break;
                    
		case 3:
                    float quant;
                    do{
                        System.out.println("Digite a quantidade de combustivel que deseja inserir: ");
                        quant = sc.nextFloat();
                        if(quant<=0){
                            System.out.println("\nA quantidade de combustível precisa ser maior que 0.\n");
                         }
                    } while(quant<=0);
                    
                    System.out.println("Digite o código do veiculo que deseja inserir: ");
                    cod=sc.nextInt();
                    cod = cod-1;
                    
                    veiculo[cod].abastecer(quant);
                    System.out.println("Quantidade de combustível atual do veiculo:"+veiculo[cod].quantCombustivel());
                    break;
                    
                case 4:
                    System.out.println("Digite o código do veiculo que deseja movimentar: ");
                    cod = sc.nextInt();
                    
                    System.out.println("Digite a quantidade de blocos que deseja movimentar: ");
                    bloco = sc.nextInt();
                    
                    veiculo[cod-1].mover(veiculo[cod-1], bloco);
                    break;
                case 5:
                    i =0;
                    
                    do{
                        if(veiculo[i]!=null){
                            /*conforme o que foi solicitado, em "movimentar todos os veiculos" 
                            movimenta-se todos em 1 bloco */
                            System.out.println("Veículo código "+(veiculo[i].returnCodigo()+1)+":");
                            veiculo[i].mover(veiculo[i],1);
                        }
                        i++;
                    }while(i<20);
                    
                    break;
                case 6:
                    i = 0;
                    do{
                        if(veiculo[i]!=null){
                            String string  = veiculo[i].toString();
                            System.out.println(""+string+"\n");
                        }
                        i++;
                    }while(i<20);
                    
                    break;
                    
                case 7:
                    
                    do{
                        System.out.println("Digite o código do veiculo: ");
                        cod = sc.nextInt();
                    } while(veiculo[cod-1]==null);
                    
                    do{        
                        System.out.println("Digite o código do pneu: ");
                        codpneu = sc.nextInt();
                    } while(codpneu>4 || codpneu<1);
                    
                    veiculo[cod-1].esvaziarPneu(codpneu-1);
                    
                    break;
                case 8:
                    System.out.println("Digite o código do veiculo: ");
                    cod = sc.nextInt();
                    
                    System.out.println("Digite o código do pneu: ");
                    codpneu = sc.nextInt();
                    
                    //-1 pois os pneus aparecem para o usuario como 1, 2, 3 e 4.
                    veiculo[cod-1].calibrarPneusEspecifico(codpneu-1);
                    
                    break;
                case 9:
                    System.out.println("Digite o código do veiculo: ");
                    cod = sc.nextInt();
                    veiculo[cod-1].calibrarTodasPneus();
                    break;
                case 10:
                    i=0;
                    System.out.println("========== Pista de Corrida ==========");
                    for(i = 0; i<20; i++){
                        if(veiculo[i]!=null){
                            System.out.println("Veículo código "+(veiculo[i].returnCodigo()+1)+":");
                            veiculo[i].desenharCarro();
                            System.out.println("\n");
                        }
                    }
                    
                    break;
                case 11:
                    System.out.println("Digite o código do veiculo: ");
                    cod = sc.nextInt();
                    veiculo[cod-1].pagarIPVA();
                    break;
                case 12:
                    System.out.println("Digite o código do veiculo: ");
                    cod = sc.nextInt();
                    veiculo[cod-1].calibrarTodasPneusTrue();
                    break;
		case 0:
                    System.exit(0);
			break;
		default:
			System.out.println("Opção Inválida!");
			break;
		}
	} while (opcao != 0);
    }
    
    public static void main(String[] args) {
        Veiculos [] veiculo = new Veiculos[20];
        
        menu(veiculo);
    }
}
