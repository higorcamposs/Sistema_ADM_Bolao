/**
 * Classe Jogador
 * @author Higor Campos
 * @versao 1.0
 * @ano_pr 2023/1
*/

/*
Jogador
# pix : String
-----------------
+ listarDados()
*/

package sistema_adm_bolao;

//extends utilizado, pois a classe Jogador está herdando de Pessoa
public class Jogador extends Pessoa {
    
    //atributos listados como privado, de acordo com diagrama UML
    private String pix;
    
    public Jogador(String nome, String cpf, String pix) {
        //usei super para passar nome e cpf para o construtor Pessoa
        super(nome, cpf);
        this.pix = pix;
    }
    
    //metodo publico, de acordo com diagrama UML
    public void listarDados() {
        //utilizado super, para acessar o metodo listarDados() da classe Pessoa  
        super.listarDados(); 
        System.out.println("PIX: " + this.pix);
    }

    //metodos gets e sets gerados
    public String getPix() {
        return pix;
    }

    public void setPix(String pix) {
        this.pix = pix;
    }

}
