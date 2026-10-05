package com.HotelManagement;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.SwingConstants;

import com.dynamicconn.Conn1;

public class LoginFrame extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginFrame() {

        setTitle("Hotel Management System - Login");

        setExtendedState(JFrame.MAXIMIZED_BOTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        // ================= LEFT SIDE =================

        JPanel leftPanel = new JPanel(new BorderLayout());

        leftPanel.setBackground(new Color(35, 47, 62));

        JLabel hotelLabel = new JLabel(
                "HOTEL",
                SwingConstants.CENTER
        );

        hotelLabel.setForeground(Color.WHITE);

        hotelLabel.setFont(
                new Font("Segoe UI", Font.BOLD, 50)
        );

        JLabel managementLabel = new JLabel(
                "MANAGEMENT SYSTEM",
                SwingConstants.CENTER
        );

        managementLabel.setForeground(Color.WHITE);

        managementLabel.setFont(
                new Font("Segoe UI", Font.PLAIN, 25)
        );

        JPanel titlePanel = new JPanel(
                new BorderLayout()
        );

        titlePanel.setOpaque(false);

        titlePanel.add(
                hotelLabel,
                BorderLayout.CENTER
        );

        titlePanel.add(
                managementLabel,
                BorderLayout.SOUTH
        );

        leftPanel.add(
                titlePanel,
                BorderLayout.CENTER
        );

        // ================= RIGHT SIDE =================

        JPanel rightPanel = new JPanel(
                new GridBagLayout()
        );

        rightPanel.setBackground(
                new Color(245, 247, 250)
        );

        JPanel loginCard = new JPanel(
                new GridBagLayout()
        );

        loginCard.setBackground(Color.WHITE);

        loginCard.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(210, 215, 220)
                        ),
                        BorderFactory.createEmptyBorder(
                                40,
                                50,
                                40,
                                50
                        )
                )
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(10, 10, 10, 10);

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        // ================= TITLE =================

        JLabel loginTitle = new JLabel(
                "Employee Login",
                SwingConstants.CENTER
        );

        loginTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        28
                )
        );

        loginTitle.setForeground(
                new Color(35, 47, 62)
        );

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        loginCard.add(
                loginTitle,
                gbc
        );

        // ================= USERNAME =================

        JLabel usernameLabel =
                new JLabel("Username");

        usernameLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        15
                )
        );

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;

        loginCard.add(
                usernameLabel,
                gbc
        );

        usernameField =
                new JTextField();

        usernameField.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        16
                )
        );

        usernameField.setPreferredSize(
                new Dimension(280, 40)
        );

        gbc.gridx = 1;
        gbc.gridy = 1;

        loginCard.add(
                usernameField,
                gbc
        );

        // ================= PASSWORD =================

        JLabel passwordLabel =
                new JLabel("Password");

        passwordLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        15
                )
        );

        gbc.gridx = 0;
        gbc.gridy = 2;

        loginCard.add(
                passwordLabel,
                gbc
        );

        passwordField =
                new JPasswordField();

        passwordField.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        16
                )
        );

        passwordField.setPreferredSize(
                new Dimension(280, 40)
        );

        gbc.gridx = 1;
        gbc.gridy = 2;

        loginCard.add(
                passwordField,
                gbc
        );

        // ================= LOGIN BUTTON =================

        JButton loginButton =
                new JButton("LOGIN");

        loginButton.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        16
                )
        );

        loginButton.setForeground(Color.WHITE);

        loginButton.setBackground(
                new Color(35, 47, 62)
        );

        loginButton.setFocusPainted(false);

        loginButton.setPreferredSize(
                new Dimension(150, 45)
        );

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;

        loginCard.add(
                loginButton,
                gbc
        );

        rightPanel.add(loginCard);

        add(
                leftPanel,
                BorderLayout.WEST
        );

        add(
                rightPanel,
                BorderLayout.CENTER
        );

        // ================= LOGIN ACTION =================

        loginButton.addActionListener(
                e -> login()
        );

        passwordField.addActionListener(
                e -> login()
        );

        setVisible(true);
    }

    // =====================================================
    // LOGIN METHOD
    // =====================================================

    private void login() {

        String username =
                usernameField.getText().trim();

        String password =
                new String(
                        passwordField.getPassword()
                );

        if (username.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter username.",
                    "Login",
                    JOptionPane.WARNING_MESSAGE
            );

            usernameField.requestFocus();

            return;
        }

        if (password.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter password.",
                    "Login",
                    JOptionPane.WARNING_MESSAGE
            );

            passwordField.requestFocus();

            return;
        }

        String sql =
                "SELECT name, role "
                + "FROM employee "
                + "WHERE username = ? "
                + "AND password = ?";

        try {

            Connection con =
                    Conn1.mycon();

            if (con == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Database connection failed."
                );

                return;
            }

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1, username);

            ps.setString(2, password);

            ResultSet rs =
                    ps.executeQuery();

            if (rs.next()) {

                String name =
                        rs.getString("name");

                String role =
                        rs.getString("role");

                System.out.println(
                        "Login successful"
                );

                System.out.println(
                        "Name = " + name
                );

                System.out.println(
                        "Role = " + role
                );

                System.out.println(
                        "Username = " + username
                );

                rs.close();

                ps.close();

                con.close();

                dispose();

                new DashboardFrame(
                        name,
                        role,
                        username
                );

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Invalid username or password.",
                        "Login Failed",
                        JOptionPane.ERROR_MESSAGE
                );

                passwordField.setText("");

                passwordField.requestFocus();

                rs.close();

                ps.close();

                con.close();
            }

        } catch (Exception ex) {

            ex.printStackTrace();

            JOptionPane.showMessageDialog(
                    this,
                    "Login Error:\n"
                    + ex.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =====================================================
    // MAIN METHOD
    // =====================================================

    public static void main(String[] args) {

        new LoginFrame();
    }
}