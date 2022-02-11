package book.store.view;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
// import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.*;

public class MainMenu implements ActionListener {
    
    private final static String TAG = "MainMenu, ";
    private String user = "";

    private JFrame frame;
    private Container mainContainer;
    private JPanel menuPanel;
    private JPanel mainPanel;

    private JButton btnCreate;
    private JButton btnRead;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JButton btnLogout;

    CreateBookPanel createPanel = new CreateBookPanel(user);
    ReadBookPanel readPanel = new ReadBookPanel(user);
    UpdateBookPanel updatePanel = new UpdateBookPanel(user);
    DeleteBookPanel deletePanel = new DeleteBookPanel(user);

    public MainMenu(String user){
        this.user = user;
        SwingUtilities.invokeLater(new Runnable(){

            @Override
            public void run() {
                setupUI();                
            }
            
        });
    }

    private void setupUI(){

        // setup frame
        System.out.println(TAG +"setup UI");
        frame = new JFrame("BOOK APP STORE");
        frame.setSize(800,400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getRootPane().setBorder(BorderFactory.createEmptyBorder(4,4,4,4));
        mainContainer = frame.getContentPane();
        mainContainer.setLayout(new BorderLayout(3,3));

        // panel menu 
        menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(5,1,4,4));
        menuPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));

        // set button
        btnCreate = new JButton("ADD BOOK");
        btnCreate.addActionListener(this);

        btnRead = new JButton("LIST BOOK");
        btnRead.addActionListener(this);

        btnUpdate = new JButton("UPDATE BOOK");
        btnUpdate.addActionListener(this);

        btnDelete = new JButton("DELETE BOOK");
        btnDelete.addActionListener(this);

        btnLogout = new JButton("LOGOUT");
        btnLogout.addActionListener(this);

        // main panel
        mainPanel = new JPanel();
        mainPanel.setBackground(Color.BLUE);

        menuPanel.add(btnCreate);
        menuPanel.add(btnRead);
        menuPanel.add(btnUpdate);
        menuPanel.add(btnDelete);
        menuPanel.add(btnLogout);

        mainContainer.add(menuPanel, BorderLayout.WEST);
        mainContainer.add(mainPanel);
        System.out.println(TAG +"setup UI done\n");
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // create button (add book)
        if (e.getSource() == btnCreate) {
            mainContainer.remove(mainPanel);
            mainContainer.remove(readPanel);
            mainContainer.remove(updatePanel);
            mainContainer.remove(deletePanel);

            mainContainer.add(createPanel, BorderLayout.CENTER);
            mainContainer.repaint();
            mainContainer.revalidate();
            System.out.println(TAG+"open create book panel\n");
        }

        // read button (list Book)
        if (e.getSource() == btnRead) {
            mainContainer.remove(mainPanel);
            mainContainer.remove(createPanel);
            mainContainer.remove(updatePanel);
            mainContainer.remove(deletePanel);

            mainContainer.add(readPanel, BorderLayout.CENTER);
            mainContainer.repaint();
            mainContainer.revalidate();
            System.out.println(TAG+"open read book panel\n");
        }

        // update button (update book)
        if (e.getSource() == btnUpdate) {
            mainContainer.remove(mainPanel);
            mainContainer.remove(createPanel);
            mainContainer.remove(readPanel);;
            mainContainer.remove(deletePanel);;

            mainContainer.add(updatePanel, BorderLayout.CENTER);
            mainContainer.repaint();
            mainContainer.revalidate();
            System.out.println(TAG+"open update book panel\n");
        }

        // delete button (delete book)
        if (e.getSource() == btnDelete) {
            mainContainer.remove(mainPanel);
            mainContainer.remove(createPanel);
            mainContainer.remove(readPanel);
            mainContainer.remove(updatePanel);

            mainContainer.add(deletePanel, BorderLayout.CENTER);
            mainContainer.repaint();
            mainContainer.revalidate();
            System.out.println(TAG+"open delete book panel\n");
        }

        // logout button 
        if (e.getSource() == btnLogout) {
            System.out.println(user +" LOGOUT\n");
            new Login();
            frame.dispose();
        }
    }
}
