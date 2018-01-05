
package classescomunicacao;


public class Mensagem {
    
    private String Mensagem;
    private String Distinatario; // QUANDO É PARA TODOS É QUANDO ESTA A NULL
      
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
