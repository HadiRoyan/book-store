package book.store.gui;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class UpdateBookPanel extends JPanel {
    
    public UpdateBookPanel(String user){
        SwingUtilities.invokeLater(new Runnable(){

            @Override
            public void run() {
                setupUI();                
            }
            
        });
    }

    private void setupUI(){
        setBackground(Color.GREEN);
    }
}
