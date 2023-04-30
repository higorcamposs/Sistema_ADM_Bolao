/**
 * Classe Aposta
 * @author Higor Campos
 * @versao 1.0
 * @ano_pr 2023/1
*/

/*
Aposta
- numeros : ArrayList<int>
- organizador : Jogador
- jogadores : ArrayList<Jogador>
-----------------------------------------------------
+ vencedora(sorteados : ArrayList<int>) : boolean
+ listarVencedores(premio : double
*/

package sistema_adm_bolao;

//importando classes da biblioteca java.util
import java.util.ArrayList;
import java.util.Scanner;

public class Aposta {
    
    //atributos listados como privado, de acordo com diagrama UML
    private ArrayList<Integer> numeros;
    private Jogador organizador;
    private ArrayList<Jogador> jogadores;
    
    public Aposta(ArrayList<Jogador> jogadores, Jogador organizador){
        //inicializando ArrayLists vazios
        this.numeros = new ArrayList<Integer>();
        this.organizador = organizador;
        this.jogadores = jogadores;
    }
    
    //metodos publicos, de acordo com diagrama UML
    public boolean vencedora(ArrayList<Integer> sorteados){
        int acertos = 0;
        for (int numero : numeros) {
            if (sorteados.contains(numero)) {
                acertos++;
            }
        }
        return acertos >= 6;
    }

    public void listarVencedores(double premio){
        //como soh tem 1 organizador por bilhete, adicionei ele a quantidade de jogadores
        double premioPorPessoa = premio / (jogadores.size() + 1); 
        System.out.printf("Organizador: %s - Pix: %s - Premio: R$%.2f\n", organizador.getNome(), organizador.getPix(), premioPorPessoa);
        for (Jogador jogador : jogadores) {
            System.out.printf("Jogador: %s - CPF: %s - Pix: %s - Premio: R$%.2f\n", jogador.getNome(), jogador.getCpf(), jogador.getPix(), premioPorPessoa);
        }
    }
    
    public void inserirNumeros() {
        Scanner input = new Scanner(System.in);
        int q = 6; 
        boolean numerosValidos = false;
        while (!numerosValidos) {
            System.out.print("Digite a quantidade de numeros a serem apostados (minimo 6): ");
            q = input.nextInt();
            if (q < 6) {
                System.out.println("Quantidade invalida. A aposta deve ter no mínimo 6 números.");
            } else {
                numerosValidos = true;
            }
        }

        ArrayList<Integer> novosNumeros = new ArrayList<Integer>();
        int i = 0;
        while (novosNumeros.size() < q) {
            i = i + 1;
            System.out.print("Digite o [" + i + "]: ");
            int numero = input.nextInt();
            if (numero < 1 || numero > 60) {
                System.out.println("Numero invalido. Aceitamos apenas números entre 1 e 60.");
                continue;
            }
            if (numeros.contains(numero) || novosNumeros.contains(numero)) {
                System.out.println("Numero ja apostado. Digite um numero diferente.");
                continue;
            }
            novosNumeros.add(numero);
        }

        numeros.addAll(novosNumeros);
        System.out.println("\n>> Sua aposta: " + numeros + "\n");
    }
    
    public void inserirOrganizador(ArrayList<Jogador> jogadores) {
        System.out.println("Escolha o organizador da aposta: ");
        for (Jogador jogador : jogadores) {
            System.out.println(jogador.getNome() + " - CPF: " + jogador.getCpf());
        }
        Scanner input = new Scanner(System.in);
        String cpfOrganizador = input.nextLine();
        for (Jogador jogador : jogadores) {
            if (jogador.getCpf().equals(cpfOrganizador)) {
                this.organizador = jogador;
                System.out.println("Organizador escolhido: " + jogador.getNome());
                return;
            }
        }
        System.out.println("CPF inválido, tente novamente.");
        inserirOrganizador(jogadores);
    }
    
    public void inserirJogadores(ArrayList<Jogador> jogadoresCadastrados) {
        Scanner input = new Scanner(System.in);
        System.out.print("Digite o número de jogadores do bilhete (além do organizador): ");
        int numJogadores = input.nextInt();

        for (int i = 1; i <= numJogadores; i++) {
            System.out.printf("Selecione o jogador %d/%d (digite o CPF):%n", i, numJogadores);
            String cpf = input.next();

            //busca o jogador cadastrado no sistema pelo CPF informado
            Jogador jogador = null;
            for (Jogador j : jogadoresCadastrados) {
                if (j.getCpf().equals(cpf)) {
                    jogador = j;
                    break;
                }
            }

            if (jogador == null) {
                System.out.println("CPF inválido. Tente novamente.");
                i--;
            } else {
                jogadores.add(jogador);
            }
        }
    }

    //metodos gets e sets gerados
    public ArrayList<Integer> getNumeros() {
        return numeros;
    }
    
    public Jogador getOrganizador() {
        return this.organizador;
    }

    public ArrayList<Jogador> getJogadores() {
        return jogadores;
    }

    public void setNumeros(ArrayList<Integer> numeros) {
        this.numeros = numeros;
    }

    public void setOrganizador(Jogador organizador) {
        this.organizador = organizador;
    }

    public void setJogadores(ArrayList<Jogador> jogadores) {
        this.jogadores = jogadores;
    }
    
}
