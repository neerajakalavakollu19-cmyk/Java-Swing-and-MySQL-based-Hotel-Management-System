package com.HotelManagement;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.dynamicconn.Conn1;

public class Settings extends JFrame {

    private JTextField nameField;
    private JTextField roleField;
    private JPasswordField oldPasswordField;
    private JPasswordField newPasswordField;

    private String username;

    private final Color NAVY = new Color(24, 35, 56);
    private final Color BLUE = new Color(45, 99, 200);
    private final Color BACKGROUND = new Color(245, 247, 250);
    private final Color WHITE = Color.WHITE;
    private final Color TEXT = new Color(45, 55, 72);
    private final Color GRAY = new Color(110, 118, 130);
    private final Color GREEN = new Color(39, 174, 96);

    public Settings(
            String employeeName,
            String employeeRole,
            String username) {

        this.username = username;

        setTitle("Hotel Management System - Settings");

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE);

        setExtendedState(
                JFrame.MAXIMIZED_BOTH);

        setMinimumSize(
                new Dimension(1000, 650));

        JPanel mainPanel =
                new JPanel(new BorderLayout());

        mainPanel.setBackground(BACKGROUND);

        // ================= HEADER =================

        JPanel headerPanel =
                new JPanel(new BorderLayout());

        headerPanel.setBackground(NAVY);

        headerPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        20, 30, 20, 30));

        JLabel titleLabel =
                new JLabel("SETTINGS");

        titleLabel.setForeground(WHITE);

        titleLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        26));

        JLabel subtitleLabel =
                new JLabel(
                        "Manage your account settings");

        subtitleLabel.setForeground(
                new Color(210, 220, 235));

        subtitleLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14));

        JPanel titlePanel =
                new JPanel();

        titlePanel.setOpaque(false);

        titlePanel.setLayout(
                new javax.swing.BoxLayout(
                        titlePanel,
                        javax.swing.BoxLayout.Y_AXIS));

        titlePanel.add(titleLabel);

        titlePanel.add(subtitleLabel);

        headerPanel.add(
                titlePanel,
                BorderLayout.WEST);

        mainPanel.add(
                headerPanel,
                BorderLayout.NORTH);

        // ================= CENTER =================

        JPanel centerPanel =
                new JPanel(
                        new GridBagLayout());

        centerPanel.setBackground(BACKGROUND);

        JPanel card =
                new JPanel(
                        new GridBagLayout());

        card.setBackground(WHITE);

        card.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        225,
                                        228,
                                        235)),
                        BorderFactory.createEmptyBorder(
                                30,
                                40,
                                30,
                                40)));

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(
                        10,
                        10,
                        10,
                        10);

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        gbc.weightx = 1;

        // ================= ACCOUNT TITLE =================

        JLabel accountTitle =
                new JLabel(
                        "Employee Account");

        accountTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        20));

        accountTitle.setForeground(TEXT);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        card.add(
                accountTitle,
                gbc);

        // ================= NAME =================

        gbc.gridwidth = 1;

        JLabel nameLabel =
                new JLabel(
                        "Employee Name");

        nameLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14));

        nameLabel.setForeground(TEXT);

        gbc.gridx = 0;
        gbc.gridy = 1;

        card.add(
                nameLabel,
                gbc);

        nameField =
                new JTextField(employeeName);

        nameField.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14));

        nameField.setEditable(false);

        gbc.gridx = 1;
        gbc.gridy = 1;

        card.add(
                nameField,
                gbc);

        // ================= ROLE =================

        JLabel roleLabel =
                new JLabel("Role");

        roleLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14));

        roleLabel.setForeground(TEXT);

        gbc.gridx = 0;
        gbc.gridy = 2;

        card.add(
                roleLabel,
                gbc);

        roleField =
                new JTextField(employeeRole);

        roleField.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14));

        roleField.setEditable(false);

        gbc.gridx = 1;
        gbc.gridy = 2;

        card.add(
                roleField,
                gbc);

        // ================= USERNAME =================

        JLabel usernameLabel =
                new JLabel("Username");

        usernameLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14));

        usernameLabel.setForeground(TEXT);

        gbc.gridx = 0;
        gbc.gridy = 3;

        card.add(
                usernameLabel,
                gbc);

        JTextField usernameField =
                new JTextField(username);

        usernameField.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14));

        usernameField.setEditable(false);

        gbc.gridx = 1;
        gbc.gridy = 3;

        card.add(
                usernameField,
                gbc);

        // ================= PASSWORD TITLE =================

        JLabel passwordTitle =
                new JLabel(
                        "Change Password");

        passwordTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        20));

        passwordTitle.setForeground(TEXT);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;

        gbc.insets =
                new Insets(
                        25,
                        10,
                        10,
                        10);

        card.add(
                passwordTitle,
                gbc);

        // ================= OLD PASSWORD =================

        gbc.gridwidth = 1;

        gbc.insets =
                new Insets(
                        10,
                        10,
                        10,
                        10);

        JLabel oldPasswordLabel =
                new JLabel(
                        "Current Password");

        oldPasswordLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14));

        oldPasswordLabel.setForeground(TEXT);

        gbc.gridx = 0;
        gbc.gridy = 5;

        card.add(
                oldPasswordLabel,
                gbc);

        oldPasswordField =
                new JPasswordField();

        oldPasswordField.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14));

        gbc.gridx = 1;
        gbc.gridy = 5;

        card.add(
                oldPasswordField,
                gbc);

        // ================= NEW PASSWORD =================

        JLabel newPasswordLabel =
                new JLabel(
                        "New Password");

        newPasswordLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14));

        newPasswordLabel.setForeground(TEXT);

        gbc.gridx = 0;
        gbc.gridy = 6;

        card.add(
                newPasswordLabel,
                gbc);

        newPasswordField =
                new JPasswordField();

        newPasswordField.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14));

        gbc.gridx = 1;
        gbc.gridy = 6;

        card.add(
                newPasswordField,
                gbc);

        // ================= BUTTONS =================

        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT));

        buttonPanel.setOpaque(false);

        JButton changeButton =
                createButton(
                        "Change Password",
                        BLUE);

        JButton backButton =
                createButton(
                        "Back",
                        GRAY);

        buttonPanel.add(changeButton);

        buttonPanel.add(backButton);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;

        gbc.anchor =
                GridBagConstraints.EAST;

        card.add(
                buttonPanel,
                gbc);

        centerPanel.add(card);

        mainPanel.add(
                centerPanel,
                BorderLayout.CENTER);

        add(mainPanel);

        // ================= ACTIONS =================

        changeButton.addActionListener(
                e -> changePassword());

        backButton.addActionListener(
                e -> dispose());

        setLocationRelativeTo(null);

        setVisible(true);
    }

    // =========================================================
    // CHANGE PASSWORD
    // =========================================================

    private void changePassword() {

        String oldPassword =
                new String(
                        oldPasswordField
                                .getPassword());

        String newPassword =
                new String(
                        newPasswordField
                                .getPassword());

        // ================= VALIDATION =================

        if (oldPassword.isEmpty()
                || newPassword.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter both passwords.",
                    "Validation",
                    JOptionPane.WARNING_MESSAGE);

            return;
        }

        if (newPassword.length() < 4) {

            JOptionPane.showMessageDialog(
                    this,
                    "New password must contain at least 4 characters.",
                    "Validation",
                    JOptionPane.WARNING_MESSAGE);

            return;
        }

        if (oldPassword.equals(newPassword)) {

            JOptionPane.showMessageDialog(
                    this,
                    "New password must be different from the current password.",
                    "Validation",
                    JOptionPane.WARNING_MESSAGE);

            return;
        }

        // ================= DATABASE =================

        String checkSql =
                "SELECT password "
                + "FROM employee "
                + "WHERE username = ?";

        String updateSql =
                "UPDATE employee "
                + "SET password = ? "
                + "WHERE username = ?";

        try {

            Connection con =
                    Conn1.mycon();

            if (con == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Database connection failed.",
                        "Connection Error",
                        JOptionPane.ERROR_MESSAGE);

                return;
            }

            try {

                // ================= CHECK OLD PASSWORD =================

                try (
                        PreparedStatement ps =
                                con.prepareStatement(
                                        checkSql)
                ) {

                    ps.setString(
                            1,
                            username);

                    try (
                            ResultSet rs =
                                    ps.executeQuery()
                    ) {

                        if (!rs.next()) {

                            JOptionPane.showMessageDialog(
                                    this,
                                    "Employee account not found.",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE);

                            return;
                        }

                        String currentPassword =
                                rs.getString("password");

                        if (!oldPassword.equals(
                                currentPassword)) {

                            JOptionPane.showMessageDialog(
                                    this,
                                    "Current password is incorrect.",
                                    "Password Error",
                                    JOptionPane.ERROR_MESSAGE);

                            oldPasswordField.setText("");

                            return;
                        }
                    }
                }

                // ================= UPDATE PASSWORD =================

                try (
                        PreparedStatement ps =
                                con.prepareStatement(
                                        updateSql)
                ) {

                    ps.setString(
                            1,
                            newPassword);

                    ps.setString(
                            2,
                            username);

                    int rows =
                            ps.executeUpdate();

                    if (rows > 0) {

                        JOptionPane.showMessageDialog(
                                this,
                                "Password changed successfully.",
                                "Success",
                                JOptionPane.INFORMATION_MESSAGE);

                        oldPasswordField.setText("");

                        newPasswordField.setText("");

                    } else {

                        JOptionPane.showMessageDialog(
                                this,
                                "Password was not changed.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }

            } finally {

                con.close();
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error changing password:\n"
                    + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // =========================================================
    // CREATE BUTTON
    // =========================================================

    private JButton createButton(
            String text,
            Color background) {

        JButton button =
                new JButton(text);

        button.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13));

        button.setForeground(WHITE);

        button.setBackground(
                background);

        button.setFocusPainted(false);

        button.setBorderPainted(false);

        button.setPreferredSize(
                new Dimension(
                        150,
                        38));

        return button;
    }

    // =========================================================
    // MAIN
    // =========================================================

    public static void main(
            String[] args) {

        new Settings(
                "Neeraja",
                "Administrator",
                "rajesh");
    }
}