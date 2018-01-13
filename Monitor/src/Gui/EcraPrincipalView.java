package Gui;

import Logic.ObservableScreen;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class EcraPrincipalView extends JFrame implements Observer {

    ObservableScreen Observablescren;
    JPanel PainelPrincipal;

    public EcraPrincipalView(ObservableScreen o) {

        super("KSmasher");

        Observablescren = o;
        Observablescren.addObserver(this);

        EcraPrincipal ecraprincipal = new EcraPrincipal(Observablescren);
        IpServidorGestao ipservges = new IpServidorGestao(Observablescren);

        CardLayout cl;

        PainelPrincipal = new JPanel(cl = new CardLayout());
        PainelPrincipal.add(ipservges, "IP Servidor Gestão");
        PainelPrincipal.add(ecraprincipal, "PaginaInical");

        cl.first(PainelPrincipal);

        ecraprincipal.setCardPanel(PainelPrincipal);
        ipservges.setCarPanel(PainelPrincipal);

        addComponents();

        setVisible(true);

        this.setSize(500, 400);
        this.setMinimumSize(new Dimension(850, 400));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        validate();

    }

    @Override
    public void update(Observable o, Object o1) {
        repaint();
    }

    private void addComponents() {
        Container cp = getContentPane();

        cp.setLayout(new BorderLayout());
        cp.add(PainelPrincipal, BorderLayout.CENTER);
    }

}
