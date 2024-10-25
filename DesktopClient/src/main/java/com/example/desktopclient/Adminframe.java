package com.example.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.example.service.AdminService; // This is the EJB service you will use
import com.example.entity.CD;

public class AdminFrame extends JFrame {
    private AdminService adminService;  // Injected EJB service

    private JTextField cdIdField;
    private JTextField cdTitleField;
    private JTextField cdAuthorField;

    private JButton addButton;
    private JButton modifyButton;
    private JButton deleteButton;
    private JButton viewBorrowingsButton;

    private JTextArea resultArea;

    public AdminFrame(AdminService adminService) {
        this.adminService = adminService;  // EJB Service injected

        setTitle("Admin CD Management");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(3, 2));

        inputPanel.add(new JLabel("CD ID:"));
        cdIdField = new JTextField();
        inputPanel.add(cdIdField);

        inputPanel.add(new JLabel("CD Title:"));
        cdTitleField = new JTextField();
        inputPanel.add(cdTitleField);

        inputPanel.add(new JLabel("CD Author:"));
        cdAuthorField = new JTextField();
        inputPanel.add(cdAuthorField);

        add(inputPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        addButton = new JButton("Add CD");
        modifyButton = new JButton("Modify CD");
        deleteButton = new JButton("Delete CD");
        viewBorrowingsButton = new JButton("View Borrowings");

        buttonPanel.add(addButton);
        buttonPanel.add(modifyButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(viewBorrowingsButton);

        add(buttonPanel, BorderLayout.CENTER);

        resultArea = new JTextArea();
        add(new JScrollPane(resultArea), BorderLayout.SOUTH);

        // Add Button Action Listener
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = cdTitleField.getText();
                String author = cdAuthorField.getText();
                adminService.addCD(new CD(title, author));  // Call EJB method
                resultArea.append("CD Added: " + title + "\n");
            }
        });

        // Modify Button Action Listener
        modifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int cdId = Integer.parseInt(cdIdField.getText());
                String title = cdTitleField.getText();
                String author = cdAuthorField.getText();
                adminService.modifyCD(cdId, new CD(title, author));  // Call EJB method
                resultArea.append("CD Modified: " + title + "\n");
            }
        });

        // Delete Button Action Listener
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int cdId = Integer.parseInt(cdIdField.getText());
                adminService.deleteCD(cdId);  // Call EJB method
                resultArea.append("CD Deleted: ID " + cdId + "\n");
            }
        });

        // View Borrowings Button Action Listener
        viewBorrowingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resultArea.append("Current Borrowings:\n");
                adminService.viewBorrowings().forEach(borrowing -> {
                    resultArea.append(borrowing.toString() + "\n");
                });
            }
        });
    }

    public static void main(String[] args) {
        // Typically you would look up the EJB service here using JNDI or dependency injection
        AdminService adminService = new AdminService();  // Assume it's initialized correctly
        AdminFrame frame = new AdminFrame(adminService);
        frame.setVisible(true);
    }
}
