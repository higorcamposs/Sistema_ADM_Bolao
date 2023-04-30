/**
 * Codigo principal
 * @author Higor Campos
 * @versao 1.0
 * @ano_pr 2023/1
*/

/*
Main
- apostas : ArrayList<Aposta>
- jogadores : ArrayList<Jogador>
----------------------------------------
+ cadastrarJogador()
+ cadastrarAposta()
- vencedoras(sorteados : ArrayList<int>) : ArrayList<Aposta>
+ inserirSorteio()
*/

package sistema_adm_bolao;

//importando classes da biblioteca java.util
import java.util.ArrayList;
import java.util.Scanner;

public class Bolao {
    
    //atributos listados como privado, de acordo com diagrama UML
    private ArrayList<Aposta> apostas;
    private ArrayList<Jogador> jogadores;
    
    Scanner input = new Scanner(System.in);
    
    
    public Bolao() {
        this.jogadores = new ArrayList<Jogador>();
        this.apostas = new ArrayList<Aposta>();
    }
    
    
    //metodos publicos, de acordo com diagrama UML
    public void cadastrarJogador(){
        
        System.out.print("\nDigite o nome do jogador: ");
        String nome = input.nextLine();

        System.out.print("Digite o CPF do jogador: ");
        String cpf = input.nextLine();
        for (Jogador jogador : jogadores) {
            if (jogador.getCpf().equals(cpf)) {
                System.out.println("\n>> [ACAO INVALIDA] <<");
                System.out.println("Este CPF ja esta cadastrado no sistema!");
                return;
            }
        }
               
        System.out.print("Digite o PIX do jogador: ");
        String pix = input.nextLine();
        for (Jogador jogador : jogadores) {
            if (jogador.getPix().equals(pix)) {
                System.out.println("\n>> [ACAO INVALIDA] <<");
                System.out.println("Este PIX ja esta cadastrado no sistema!");
                return; // Impede o cadastro do novo jogador
            }
        }

        Jogador novoJogador = new Jogador(nome, cpf, pix);
        jogadores.add(novoJogador);

        System.out.println("\n>> Jogador cadastrado com sucesso! <<");

    }
    
    public void cadastrarAposta(){
        //antes de criar a aposta, quero garantir que seja cumprido: 
        //- cada aposta tera ao menos dois jogadores participando
        //- um deles seraa o organizador do bilhete
        
        int qtdJogadores = jogadores.size();
        
        if (qtdJogadores < 2){
            System.out.println("\n>> Necessario no minimo 2 jogadores cadastrados! <<");
            return;
        }

        System.out.println("\n----------------- Cadastro de nova aposta -----------------");
        System.out.print("\nDigite o CPF do organizador: ");
        String cpfOrganizador = input.nextLine();
        
        Jogador organizador = null;
        for (Jogador jogador : jogadores) {
            if (jogador.getCpf().equals(cpfOrganizador)) {
                organizador = jogador;
                break;
            }
        }
        if (organizador == null) {
            System.out.println(">> Jogador nao encontrado! << ");
            //coloquei um return para caso nao tenho organizador, logo voltamos para o menu.
            return;
        }
        
        //lista para adicionar os jogadores que vao apostar
        ArrayList<Jogador> jogadoresAposta = new ArrayList<Jogador>();
        
        //ja adicionando o organizador na lista
        jogadoresAposta.add(organizador);

        //verificar com usuario quantos jogadores vao participar
        System.out.print("Digite a quantidade de jogadores que vao participar da aposta: ");
        int qtdJogadoresAposta = input.nextInt();
        input.nextLine();
        
        //comeca com 1, pois organizador ja foi cadastrado
        int numJogadores = 1; 
        while (numJogadores <= qtdJogadoresAposta) {
            System.out.print("Digite o CPF do jogador: ");
            String cpfJogador = input.nextLine();

            Jogador jogador = null;
            for (Jogador j : jogadores) {
                if (j.getCpf().equals(cpfJogador)) {
                    jogador = j;
                    break;
                }
            }
            if (jogador == null) {
                System.out.println(">> Jogador nao encontrado. <<");
            } else {
                jogadoresAposta.add(jogador);
                numJogadores++;
            }
        }
        
        //nova aposta vazia [OBSERVACAO]
        Aposta novaAposta = new Aposta(jogadoresAposta, organizador);
        
        
        //e utilizando os m´etodos auxiliares da classe Aposta
        novaAposta.inserirNumeros();
        
        
        //insere esta nova aposta no ArrayList de apostas
        this.apostas.add(novaAposta);
        
        System.out.println(">> Aposta cadastrada com sucesso! <<");
    }
    
    public void inserirSorteio(){
        
        //segui a sugestao
        //fazendo a leitura dos 6 numeros sorteados
        ArrayList<Integer> numerosSorteados = new ArrayList<Integer>();
        System.out.println("--- Insira os 6 numeros sorteados ---");
        for (int i = 0; i < 6; i++) {
            System.out.printf(">> Numero [" + (i+1) + "]: ");
            int numero = input.nextInt();
            numerosSorteados.add(numero);
        }

        //leitura do premio total a ser dividido entre os bilhetes premiados
        System.out.print("\n>>Informe o valor do premio total: R$");
        double premioTotal = input.nextDouble();

        //criacao do ArrayList auxiliar contendo as apostas vencedoras
        ArrayList<Aposta> vencedoras = vencedoras(numerosSorteados);
        
        //calculo do premio a ser recebido por cada bilhete premiado
        double premioPorBilhete = premioTotal / vencedoras.size();

        //listagem das apostas vencedoras
        System.out.println("Apostas vencedoras: ");
        for (Aposta aposta : vencedoras) {
            aposta.listarVencedores(premioPorBilhete);
        }
    }
    
    //metodo privado, de acordo com diagrama UML
    private ArrayList<Aposta> vencedoras(ArrayList<Integer> numerosSorteados){
        
        ArrayList<Aposta> apostasPremiadas = new ArrayList<Aposta>();
        
        for (Aposta aposta : this.apostas) {
            boolean vencedora = aposta.vencedora(numerosSorteados);
            if (vencedora == true){
                apostasPremiadas.add(aposta);
            }
        }
    return apostasPremiadas;
    }

    //metodos gets e sets gerados
    public ArrayList<Aposta> getApostas() {
        return apostas;
    }

    public ArrayList<Jogador> getJogadores() {
        return jogadores;
    }

    public Scanner getInput() {
        return input;
    }

    public void setApostas(ArrayList<Aposta> apostas) {
        this.apostas = apostas;
    }

    public void setJogadores(ArrayList<Jogador> jogadores) {
        this.jogadores = jogadores;
    }

    public void setInput(Scanner input) {
        this.input = input;
    }
    
    
}
