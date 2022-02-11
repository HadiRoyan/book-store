package book.store.view;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class DeleteBookPanel extends JPanel {
    
    public DeleteBookPanel(String user){
        SwingUtilities.invokeLater(new Runnable(){

            @Override
            public void run() {
                setupUI();                
            }
            
        });
    }

    private void setupUI(){
        setBackground(Color.ORANGE);
    }
}
