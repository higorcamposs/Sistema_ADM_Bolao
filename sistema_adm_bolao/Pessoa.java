/**
 * Classe Pessoa
 * @author Higor Campos
 * @versao 1.0
 * @ano_pr 2023/1
*/

/*
Pessoa
# nome : String
# cpf : String
----------------------
+ listarDados()
*/

package sistema_adm_bolao;

//importando classes da biblioteca java.util
import java.util.Scanner;

public class Pessoa {
    
    //atributos listados como privado, de acordo com diagrama UML
    private String nome;
    private String cpf;
    
    public Pessoa(String nome, String cpf){
        this.nome = nome;
        this.cpf = cpf;
    }
    
    //metodo publico, de acordo com diagrama UML
    public void listarDados(){
        System.out.println("Nome: " + this.nome);
        System.out.println("CPF: " + this.cpf);
    }

    //metodos gets e sets gerados
    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
}
