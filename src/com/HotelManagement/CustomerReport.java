package com.HotelManagement;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.dynamicconn.Conn1;

public class CustomerReport extends JFrame {

    private JTable customerTable;
    private DefaultTableModel model;
    private JTextField searchField;

    private final Color NAVY = new Color(24, 35, 56);
    private final Color BLUE = new Color(45, 99, 200);
    private final Color BACKGROUND = new Color(245, 247, 250);
    private final Color WHITE = Color.WHITE;
    private final Color TEXT = new Color(45, 55, 72);
    private final Color GRAY = new Color(110, 118, 130);
    private final Color GREEN = new Color(39, 174, 96);

    public CustomerReport() {

        setTitle("Hotel Management System - Customer Report");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setExtendedState(JFrame.MAXIMIZED_BOTH);

        setMinimumSize(new Dimension(1000, 650));

        // ================= MAIN PANEL =================

        JPanel mainPanel = new JPanel(new BorderLayout());

        mainPanel.setBackground(BACKGROUND);

        // ================= HEADER =================

        JPanel headerPanel = new JPanel(new BorderLayout());

        headerPanel.setBackground(NAVY);

        headerPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        18, 30, 18, 30));

        JLabel titleLabel =
                new JLabel("CUSTOMER REPORT");

        titleLabel.setForeground(WHITE);

        titleLabel.setFont(
                new Font("Segoe UI", Font.BOLD, 26));

        JLabel subTitle =
                new JLabel(
                        "View and search registered hotel customers");

        subTitle.setForeground(
                new Color(210, 220, 235));

        subTitle.setFont(
                new Font("Segoe UI", Font.PLAIN, 14));

        JPanel titlePanel = new JPanel();

        titlePanel.setOpaque(false);

        titlePanel.setLayout(
                new javax.swing.BoxLayout(
                        titlePanel,
                        javax.swing.BoxLayout.Y_AXIS));

        titlePanel.add(titleLabel);

        titlePanel.add(subTitle);

        headerPanel.add(
                titlePanel,
                BorderLayout.WEST);

        mainPanel.add(
                headerPanel,
                BorderLayout.NORTH);

        // ================= CONTENT =================

        JPanel contentPanel =
                new JPanel(
                        new BorderLayout(20, 20));

        contentPanel.setBackground(BACKGROUND);

        contentPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        25, 30, 25, 30));

        // ================= SEARCH PANEL =================

        JPanel searchPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                12,
                                12));

        searchPanel.setBackground(WHITE);

        searchPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(225, 228, 235)),
                        BorderFactory.createEmptyBorder(
                                8, 12, 8, 12)));

        JLabel searchLabel =
                new JLabel("Customer ID:");

        searchLabel.setFont(
                new Font("Segoe UI", Font.BOLD, 14));

        searchLabel.setForeground(TEXT);

        searchField =
                new JTextField(15);

        searchField.setFont(
                new Font("Segoe UI", Font.PLAIN, 14));

        JButton searchButton =
                createButton("Search", BLUE);

        JButton refreshButton =
                createButton("Refresh", GREEN);

        JButton clearButton =
                createButton("Clear", GRAY);

        JButton backButton =
                createButton("Back", NAVY);

        searchPanel.add(searchLabel);

        searchPanel.add(searchField);

        searchPanel.add(searchButton);

        searchPanel.add(refreshButton);

        searchPanel.add(clearButton);

        searchPanel.add(backButton);

        contentPanel.add(
                searchPanel,
                BorderLayout.NORTH);

        // ================= TABLE =================

        String[] columns = {
                "Customer ID",
                "Customer Name",
                "Phone",
                "Email",
                "Address"
        };

        model =
                new DefaultTableModel(
                        columns,
                        0) {

                    @Override
                    public boolean isCellEditable(
                            int row,
                            int column) {

                        return false;
                    }
                };

        customerTable =
                new JTable(model);

        customerTable.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14));

        customerTable.setRowHeight(35);

        customerTable.getTableHeader().setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14));

        customerTable.getTableHeader()
                .setBackground(NAVY);

        customerTable.getTableHeader()
                .setForeground(WHITE);

        customerTable.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION);

        customerTable.setGridColor(
                new Color(225, 228, 235));

        customerTable.setShowVerticalLines(false);

        customerTable.setAutoCreateRowSorter(true);

        JScrollPane scrollPane =
                new JScrollPane(customerTable);

        scrollPane.setBorder(
                BorderFactory.createLineBorder(
                        new Color(220, 224, 230)));

        contentPanel.add(
                scrollPane,
                BorderLayout.CENTER);

        // ================= BOTTOM =================

        JPanel bottomPanel =
                new JPanel(
                        new BorderLayout());

        bottomPanel.setBackground(BACKGROUND);

        JLabel countLabel =
                new JLabel(
                        "Total Customers: 0");

        countLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        15));

        countLabel.setForeground(TEXT);

        bottomPanel.add(
                countLabel,
                BorderLayout.WEST);

        contentPanel.add(
                bottomPanel,
                BorderLayout.SOUTH);

        mainPanel.add(
                contentPanel,
                BorderLayout.CENTER);

        add(mainPanel);

        // ================= BUTTON ACTIONS =================

        searchButton.addActionListener(e -> {

            searchCustomers();

            updateCount(countLabel);
        });

        refreshButton.addActionListener(e -> {

            searchField.setText("");

            loadCustomers();

            updateCount(countLabel);
        });

        clearButton.addActionListener(e -> {

            searchField.setText("");

            loadCustomers();

            updateCount(countLabel);

            searchField.requestFocus();
        });

        backButton.addActionListener(e -> {

            dispose();
        });

        // ================= INITIAL LOAD =================

        loadCustomers();

        updateCount(countLabel);

        setLocationRelativeTo(null);

        setVisible(true);
    }

    // =========================================================
    // LOAD ALL CUSTOMERS
    // =========================================================

    private void loadCustomers() {

        model.setRowCount(0);

        String sql =
                "SELECT customer_id, name, phone, email, address "
                + "FROM customer "
                + "ORDER BY customer_id DESC";

        try {

            Connection con = Conn1.mycon();

            if (con == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Database connection failed.",
                        "Connection Error",
                        JOptionPane.ERROR_MESSAGE);

                return;
            }

            try (
                    PreparedStatement ps =
                            con.prepareStatement(sql);

                    ResultSet rs =
                            ps.executeQuery()
            ) {

                while (rs.next()) {

                    Object[] row = {

                            rs.getInt("customer_id"),

                            rs.getString("name"),

                            rs.getString("phone"),

                            rs.getString("email"),

                            rs.getString("address")
                    };

                    model.addRow(row);
                }

            } finally {

                con.close();
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error loading customer report:\n"
                    + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // =========================================================
    // SEARCH CUSTOMER BY ID
    // =========================================================

    private void searchCustomers() {

        String customerId =
                searchField.getText().trim();

        if (customerId.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter Customer ID.",
                    "Search",
                    JOptionPane.WARNING_MESSAGE);

            searchField.requestFocus();

            return;
        }

        int id;

        try {

            id = Integer.parseInt(customerId);

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Customer ID must contain numbers only.",
                    "Invalid Customer ID",
                    JOptionPane.WARNING_MESSAGE);

            searchField.requestFocus();

            return;
        }

        model.setRowCount(0);

        String sql =
                "SELECT customer_id, name, phone, email, address "
                + "FROM customer "
                + "WHERE customer_id = ?";

        try {

            Connection con = Conn1.mycon();

            if (con == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Database connection failed.",
                        "Connection Error",
                        JOptionPane.ERROR_MESSAGE);

                return;
            }

            try (
                    PreparedStatement ps =
                            con.prepareStatement(sql)
            ) {

                ps.setInt(1, id);

                try (
                        ResultSet rs =
                                ps.executeQuery()
                ) {

                    while (rs.next()) {

                        Object[] row = {

                                rs.getInt("customer_id"),

                                rs.getString("name"),

                                rs.getString("phone"),

                                rs.getString("email"),

                                rs.getString("address")
                        };

                        model.addRow(row);
                    }
                }

            } finally {

                con.close();
            }

            if (model.getRowCount() == 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "No customer found with ID: "
                        + id,
                        "Search Result",
                        JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error searching customer:\n"
                    + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // =========================================================
    // UPDATE CUSTOMER COUNT
    // =========================================================

    private void updateCount(
            JLabel countLabel) {

        countLabel.setText(
                "Total Customers: "
                + model.getRowCount());
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

        button.setBackground(background);

        button.setFocusPainted(false);

        button.setBorderPainted(false);

        button.setPreferredSize(
                new Dimension(100, 38));

        return button;
    }

    // =========================================================
    // MAIN
    // =========================================================

    public static void main(
            String[] args) {

        new CustomerReport();
    }
}