/**
 * Codigo principal
 * @author Higor Campos
 * @versao 1.0
 * @ano_pr 2023/1
*/

package sistema_adm_bolao;

//importando classes da biblioteca java.util
import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {

    public static void main(String[] args) {
        //criei objeto para receber dados e lista para classe Pessoa
        Scanner input = new Scanner(System.in);
        
        //declarando variaveis
        //op == opções selecionadas pelo usuario no menu
        int op = 0;
        
        //unico objeto criado, conforme escrito na especificacao
        Bolao bolao = new Bolao();
        
        //condição para exibição do menu, bem como as funções disponiveis no sistema
        while (op != 4) {
             
            op = menu(input);
            
            //caso para cada situação
            switch (op) {
                case 1:
                    bolao.cadastrarJogador();
                    break;
                case 2:
                    bolao.cadastrarAposta();
                    break;
                case 3:
                    bolao.inserirSorteio();
                    break;
                case 4:
                   System.out.println("\n >> [SISTEMA ENCERRADO] <<");
                   break; 

                default:
                    System.out.println("Opcao nao identificada. Tente novamente.\n");
                    break;   
            }
        }
    }
    
    //faca um metodo estatico para exibir as opcoes do menu, ler a opc˜ao desejada pelo usuario e retornar a opc˜ao lida, caso seja valida
    //OBS: antes nao tinha metodo static e nem tratamento de exceções, utilizei default no switch case para retornar opcao invalida
    /*
    default:
        System.out.println("Opcao nao identificada. Tente novamente.\n");
        break; 
    */
    public static int menu(Scanner input) {
        int op = 0;
        while (op < 1 || op > 4) {
            System.out.println("\n-------- Sistema de gerenciamento de apostas --------");
                System.out.println("[1] >> Cadastrar jogador");
                System.out.println("[2] >> Cadastrar aposta");
                System.out.println("[3] >> Inserir sorteio e listar vencedores");
                System.out.println("[4] >> Sair");
                
                //inserido o tratamento de exceções
                try {
                    System.out.print("Opcao escolhida: ");
                    op = input.nextInt();
                    input.nextLine();
                    if (op < 1 || op > 4) {
                        System.out.println("\n>> Opcao [" + op + "] nao identificada! <<\n");
                    }
                }
                catch (InputMismatchException e) {
                    System.out.println("\n>> Opcao [" + op + "] nao identificada! <<\n");
                    input.nextLine();
                }
        }
        return op; 
    }
}


   


