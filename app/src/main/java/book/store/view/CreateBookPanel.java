package book.store.view;

// import java.awt.Color;
import java.awt.*;
import java.awt.event.*;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
// import javax.swing.border.Border;

import book.store.services.BookService;

public class CreateBookPanel extends JPanel implements ActionListener{

    private final static String TAG = "CreateBookPanel, ";

    private JPanel mainPanel;
    private JPanel fieldPanel;
    private JPanel labelPanel;
    private JPanel submitPanel;
    private JPanel btnPanel;

    private JButton btnSubmit;
    
    private JTextField bookField;
    private JTextField authorField;
    private JTextField publisherField;
    private JTextField quantityField;
    
    private JLabel bookLabel;
    private JLabel authorLabel;
    private JLabel publisherLabel;
    private JLabel quantityLabel;
    private JLabel successLabel;
    
    public CreateBookPanel(String user){
        SwingUtilities.invokeLater(new Runnable(){

            @Override
            public void run() {
               setupUI();
            }
            
        });
    }
    
    private void setupUI(){
        
       
        // set the create book panel
        setLayout(new GridLayout(2,1));
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        
        // create & set main panel for place the label panel and field panel
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        
        // set label panel, field panel, and submit panel
        labelPanel = new JPanel(new GridLayout(4, 1, 4, 4));
        labelPanel.setBorder(BorderFactory.createEmptyBorder(4,4,4,4));
        
        fieldPanel = new JPanel(new GridLayout(4, 1, 4, 4));
        fieldPanel.setBorder(BorderFactory.createEmptyBorder(4,4,4,4));
        
        submitPanel = new JPanel();
        submitPanel.setLayout(new GridLayout(2,1));
        btnPanel = new JPanel();
        
        // Name book
        bookLabel = new JLabel("BOOK NAME");
        labelPanel.add(bookLabel);
        
        bookField = new JTextField("");
        fieldPanel.add(bookField);
        
        // Author Book
        authorLabel = new JLabel("AUTHOR");
        labelPanel.add(authorLabel);
        
        authorField = new JTextField("");
        fieldPanel.add(authorField);
        authorField.setFocusable(true);
        
        // book publisher
        publisherLabel = new JLabel("PUBLISHER");
        labelPanel.add(publisherLabel);
        
        publisherField = new JTextField("");
        fieldPanel.add(publisherField);

        // book quantity
        quantityLabel = new JLabel("QUANTITY");
        labelPanel.add(quantityLabel);

        quantityField = new JTextField("");
        fieldPanel.add(quantityField);

        // add label panel and field panel to main panel
        mainPanel.add(labelPanel, BorderLayout.WEST);
        mainPanel.add(fieldPanel, BorderLayout.CENTER);

        // set and add button & success label to button panel
        btnSubmit = new JButton("SUBMIT");
        btnSubmit.addActionListener(this);
        btnPanel.add(btnSubmit);

        successLabel = new JLabel("", SwingConstants.CENTER);

        submitPanel.add(successLabel);
        submitPanel.add(btnPanel);
        // add main panel and submit panel create book panel
        add(mainPanel);
        add(submitPanel);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(TAG +"On Click : submit button");

        String bookName = bookField.getText().trim().toUpperCase();
        String author = authorField.getText().trim().toUpperCase();
        String publisher = publisherField.getText().trim().toUpperCase();
        int quantity = Integer.parseInt(quantityField.getText());

        if (bookName.isEmpty() || author.isEmpty() || publisher.isEmpty() || quantity < 0) {
            successLabel.setForeground(Color.RED);
            successLabel.setText("FAILED TO SAVE THE BOOK");
            System.out.println("all data can't empty\nFailed to save the book!!!\n");
        }else{
            successLabel.setForeground(Color.GREEN);
            successLabel.setText("SUCCESSFULLY SAVE THE BOOK");
            System.out.println(TAG+"book saved\n");
        }
        
        
    }
}
