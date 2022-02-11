package book.store.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import book.store.entity.User;


import java.awt.Color;
import java.awt.event.*;

/**
 * Login
 */
public class Login implements ActionListener {

    private final static String TAG = "Login, ";

    JFrame frame;
    JButton btnLogin;
    JButton btnExit;
    JPanel panel;
    JLabel userlabel;
    JLabel failLabel;
    JTextField inputUsername;
    JLabel passLabel;
    JPasswordField inputPassword;
    public Login(){
        
        System.out.println(TAG+ "start Login");
        SwingUtilities.invokeLater(new Runnable(){

            @Override
            public void run() {
                setupUI();
            }
            
        });

    }

    public void setupUI(){
        System.out.println(TAG +"setup UI");
        frame = new JFrame();
        panel = new JPanel();

        panel.setLayout(null);
        frame.setSize(600,280);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // username
        userlabel = new JLabel("USERNAME");
        userlabel.setBounds(30,40,140,30);
        panel.add(userlabel);

        inputUsername = new JTextField();
        inputUsername.setBounds(250,40,230,30);
        panel.add(inputUsername);

        // password 
        passLabel = new JLabel("PASSWORD");
        passLabel.setBounds(30,80,140,30);
        panel.add(passLabel);

        inputPassword = new JPasswordField();
        inputPassword.setBounds(250,80,230,30);
        panel.add(inputPassword);
        
        // button
        btnLogin = new JButton("LOGIN");
        btnLogin.setBounds(30,130,90,30);
        btnLogin.addActionListener(this);
        // btnLogin.addKeyListener(inputPasswordKeyPressed(evt));
        panel.add(btnLogin);

        btnExit = new JButton("EXIT");
        btnExit.setBounds(350, 130, 90, 30);
        btnExit.addActionListener(this);
        panel.add(btnExit);

        // fail label
        failLabel = new JLabel("");
        failLabel.setBounds(30, 160, 350, 60);
        // failLabel.setForeground(Color.RED);
        panel.add(failLabel);

        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        System.out.println(TAG +"setup UI done");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == btnLogin) {
            String user = inputUsername.getText().toUpperCase();
            char pass[] = inputPassword.getPassword();
            
            if (user.isEmpty()) {
                // failLabel.setText("USERNAME OR PASSWORD IS EMPTY");
                setFailLabelText("USERNAME OR PASSWORD IS EMPTY", Color.RED);
            } else if(pass.length < 8) {
                // failLabel.setText("WRONG USERNAME OR PASSWORD");
                setFailLabelText("PASSWORD MUST BE greater than or equal to 8 CHARACTER", Color.RED);
                inputUsername.setText("");
                inputPassword.setText("");
            } else {
                // success login
                new User("",user, "");
                frame.dispose();
                new MainMenu(user);
            }
           
        }

        if (e.getSource() == btnExit) {
            System.out.println("Exit.....!");
            System.exit(0);
        }
    }

    public void setFailLabelText(String message, Color clr){
        failLabel.setForeground(clr);
        failLabel.setText(message);
    }

   
}