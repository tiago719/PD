
package classescomunicacao;

import java.io.Serializable;


public class Mensagem implements Serializable{
    
    private String Mensagem;
    private String Distinatario; // QUANDO É PARA TODOS É QUANDO ESTA A NULL
    private String Remetente;

    public Mensagem(String Mensagem, String Distinatario, String Remetente) {
        this.Mensagem = Mensagem;
        this.Distinatario = Distinatario;
        this.Remetente = Remetente;
    }
     public Mensagem() {

    }

    public String getRemetente() {
        return Remetente;
    }

    public void setRemetente(String Remetente) {
        this.Remetente = Remetente;
    }
    
    public String getMensagem() {
        return Mensagem;
    }

    public void setMensagem(String Mensagem) {
        this.Mensagem = Mensagem;
    }

    public String getDistinatario() {
        return Distinatario;
    }

    public void setDistinatario(String Distinatario) {
        this.Distinatario = Distinatario;
    }
    
}
