
package vistajogo;

import vistajogo.logic.ObservableGame;
import vistajogo.ui.text.TextUI;

public class MainWithTextUI {

    public static void main(String[] args) {
        TextUI textUI = new TextUI(new ObservableGame());
        textUI.run();
    }
}
