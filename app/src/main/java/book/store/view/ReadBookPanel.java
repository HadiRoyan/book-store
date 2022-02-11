package book.store.view;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class ReadBookPanel extends JPanel {
    
    public ReadBookPanel(String user){
        SwingUtilities.invokeLater(this::setupUI);
    }

    private void setupUI(){
        setBackground(Color.CYAN);
    }
}
