package Cliente.ui.gui.EcraInicial;


import java.awt.Dimension;
import java.awt.Toolkit;

public class Constants
{   
    public static final int DIM_X_REGISTO=200;
    public static final int DIM_Y_REGISTO=400;
    
    public static final int DIM_X_LOGIN=200;
    public static final int DIM_Y_LOGIN=200;
    
    public static final int DIM_X_ECRA_PRINCIPAL=1000;
    public static final int DIM_Y_ECRA_PRINCIPAL=500;
    
    public static final int DIM_X_TEXT_FIELD=50;
    public static final int DIM_Y_TEXT_FIELD=400;
    
    static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    
    static final int DIM_X_FRAME = (int) screenSize.getWidth();
    static final int DIM_Y_FRAME = (int) screenSize.getHeight();
}
