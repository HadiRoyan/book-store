package book.store.view;

import javax.swing.*;

import book.store.entity.User;


import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Login
 */
//@Slf4j
public class Login implements ActionListener {

    private final Logger log = LoggerFactory.getLogger(Login.class);
    
    JFrame frame;
    JButton btnLogin;
    JButton btnExit;

    JPanel panel;
    JPanel userNamePanel;
    JPanel passwordPanel;
    JPanel buttonPanel;
    JPanel failPanel;

    JLabel userlabel;
    JLabel failLabel;

    JTextField inputUsername;
    JLabel passLabel;
    JPasswordField inputPassword;

    public Login(){
        log.info("start login");
        SwingUtilities.invokeLater(this::setupUI);

    }

    public void setupUI(){
        log.debug("setup UI");
        frame = new JFrame();
        panel = new JPanel();
        userNamePanel = new JPanel(new GridLayout(1, 2, 4, 4));
        passwordPanel = new JPanel();
        buttonPanel = new JPanel();
        failPanel = new JPanel();


        panel.setLayout(new GridLayout(4, 1));
        frame.setSize(600,280);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // username
        userlabel = new JLabel("USERNAME");
        userNamePanel.add(userlabel, BorderLayout.WEST);

        inputUsername = new JTextField();
        userNamePanel.add(inputUsername, BorderLayout.CENTER);

        // password 
        passLabel = new JLabel("PASSWORD");
//        passLabel.setBounds(30,80,140,30);
        passwordPanel.add(passLabel);

        inputPassword = new JPasswordField();
//        inputPassword.setBounds(250,80,230,30);
        passwordPanel.add(inputPassword);
        
        // button
        btnLogin = new JButton("LOGIN");
        btnLogin.setAlignmentX(SwingConstants.LEFT);
//        btnLogin.setBounds(30,130,90,30);
        btnLogin.addActionListener(this);
        // btnLogin.addKeyListener(inputPasswordKeyPressed(evt));
        buttonPanel.add(btnLogin);

        btnExit = new JButton("EXIT");
        btnLogin.setAlignmentX(SwingConstants.RIGHT);
//        btnExit.setBounds(350, 130, 90, 30);
        btnExit.addActionListener(this);
        buttonPanel.add(btnExit);

        // fail label
        failLabel = new JLabel("");
//        failLabel.setBounds(30, 160, 350, 60);
        // failLabel.setForeground(Color.RED);
        failPanel.add(failLabel);

        panel.add(userNamePanel);
        panel.add(passwordPanel);
        panel.add(buttonPanel);
        panel.add(failPanel);

        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        log.debug("setup UI done");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == btnLogin) {
            String user = inputUsername.getText().toUpperCase();
            char[] pass = inputPassword.getPassword();
            
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
                new User(user, pass);
                frame.dispose();
                new MainMenu(user);
            }
           
        }

        if (e.getSource() == btnExit) {
            log.info("exit app");
            System.exit(0);
        }
    }

    public void setFailLabelText(String message, Color clr){
        failLabel.setForeground(clr);
        failLabel.setText(message);
    }
   
}