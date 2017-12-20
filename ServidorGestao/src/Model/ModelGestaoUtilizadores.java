package Model;

import classescomunicacao.RegistoUtilizador;
import Model.PesquisasGestaoUtilizadores;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ModelGestaoUtilizadores {

    public ModelGestaoUtilizadores() {

    }

    public boolean AdicionaUtil(RegistoUtilizador res) {
        PesquisasGestaoUtilizadores p = new PesquisasGestaoUtilizadores();

        try {
            p.AdicionaUtilizador(res.getNome(), res.getUsername(), res.getPassword());
            return true;
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ModelGestaoUtilizadores.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

}
