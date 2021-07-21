package example.swing.form;


import example.swing.model.UserInfo;
import example.swing.model.validator.InfoValidator;
import example.swing.service.UserInfoRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class UserForm implements ActionListener {
    private UserInfoRepository writer;
    private JFrame frame = new JFrame("User Form");
    private JLabel nameLabel = new JLabel("Name");
    private JLabel emailLabel = new JLabel("Email");
    private JTextField nameText = new JTextField();
    private JTextField emailText = new JTextField();
    private JButton button = new JButton("Enter");



    public UserForm(UserInfoRepository writer) throws IOException {
        this.writer = writer;

        nameLabel.setBounds(100, 60, 50, 30);
        emailLabel.setBounds(100, 100, 50, 30);
        nameText.setBounds(150, 60, 180, 25);
        emailText.setBounds(150, 100, 180, 25);
        button.setBounds(35, 550, 350, 50);


        button.addActionListener(this);
        frame.add(nameText);
        frame.add(emailText);
        frame.add(nameLabel);
        frame.add(emailLabel);
        frame.add(button);
        frame.add(getTableScrollPane());
        frame.setSize(475, 650);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.getContentPane().setBackground(Color.LIGHT_GRAY);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private JScrollPane getTableScrollPane() throws IOException {

        String[] columnNames = {"Email", "Name"};
        Object[][] data = writer.findAll().stream()
                .map(this::infoToObjectArray)
                .toArray(Object[][]::new);

        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);

        scrollPane.setBounds(65, 180, 300, 300);
        table.setFillsViewportHeight(true);
        return scrollPane;
    }

    private Object[] infoToObjectArray(UserInfo info) {
        return new Object[]{info.getEmail(), info.getName()};
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == button) {
            if (nameText.getText().trim().isEmpty() || emailText.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Incorrect form information");
            } else {
                String name;
                boolean statusName = InfoValidator.isValidName(nameText.getText());
                name = nameText.getText();
                boolean statusEmail = InfoValidator.isValidEmail(emailText.getText());
                String email = emailText.getText();
                while (!statusEmail && !statusName) {
                    JOptionPane.showMessageDialog(frame, "Enter a valid email and name");
                    return;

                }
                UserInfo info = new UserInfo(name, email);
                try {
                    writer.write(info);
                    nameText.setText("");
                    emailText.setText("");
                    JOptionPane.showMessageDialog(frame, info.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
