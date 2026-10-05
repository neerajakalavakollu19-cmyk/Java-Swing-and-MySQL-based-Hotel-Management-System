package com.HotelManagement;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.dynamicconn.Conn1;

public class CustomerDetails extends JFrame implements ActionListener {

    private JLabel jl1;
    private JLabel jl2;
    private JLabel jl3;
    private JLabel jl4;
    private JLabel jl5;

    private JTextField jt1;
    private JTextField jt2;
    private JTextField jt3;
    private JTextField jt4;

    private JButton jb1;
    private JButton jb2;
    private JButton jb3;
    private JButton jb4;

    private JTable table;

    private DefaultTableModel model;

    public CustomerDetails() {

        // =====================================================
        // FRAME
        // =====================================================

        setTitle("Hotel Management - Customer Details");

        setExtendedState(JFrame.MAXIMIZED_BOTH);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setLayout(new BorderLayout());

        // =====================================================
        // HEADER
        // =====================================================

        JPanel headerPanel =
                new JPanel(new BorderLayout());

        headerPanel.setBackground(
                new Color(35, 47, 62)
        );

        headerPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        25,
                        35,
                        25,
                        35
                )
        );

        jl1 =
                new JLabel("CUSTOMER DETAILS");

        jl1.setForeground(Color.WHITE);

        jl1.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        30
                )
        );

        JLabel subtitleLabel =
                new JLabel(
                        "Manage hotel customer information"
                );

        subtitleLabel.setForeground(
                new Color(220, 225, 230)
        );

        subtitleLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        15
                )
        );

        JPanel titlePanel =
                new JPanel(
                        new GridLayout(2, 1)
                );

        titlePanel.setOpaque(false);

        titlePanel.add(jl1);

        titlePanel.add(subtitleLabel);

        headerPanel.add(
                titlePanel,
                BorderLayout.WEST
        );

        add(
                headerPanel,
                BorderLayout.NORTH
        );

        // =====================================================
        // MAIN PANEL
        // =====================================================

        JPanel mainPanel =
                new JPanel(
                        new BorderLayout(0, 25)
                );

        mainPanel.setBackground(
                new Color(245, 247, 250)
        );

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        30,
                        50,
                        20,
                        50
                )
        );

        // =====================================================
        // CUSTOMER FORM CARD
        // =====================================================

        JPanel formCard =
                new JPanel(
                        new BorderLayout()
                );

        formCard.setBackground(Color.WHITE);

        formCard.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        220,
                                        225,
                                        230
                                )
                        ),
                        BorderFactory.createEmptyBorder(
                                25,
                                30,
                                25,
                                30
                        )
                )
        );

        JLabel formTitle =
                new JLabel(
                        "Customer Information"
                );

        formTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        22
                )
        );

        formTitle.setForeground(
                new Color(60, 60, 60)
        );

        formCard.add(
                formTitle,
                BorderLayout.NORTH
        );

        // =====================================================
        // FORM
        // =====================================================

        JPanel formPanel =
                new JPanel(
                        new GridLayout(
                                2,
                                4,
                                20,
                                15
                        )
                );

        formPanel.setBackground(Color.WHITE);

        // Name

        jl2 =
                createLabel("Name");

        jt1 =
                createTextField();

        formPanel.add(jl2);
        formPanel.add(jt1);

        // Phone

        jl3 =
                createLabel("Phone");

        jt2 =
                createTextField();

        formPanel.add(jl3);
        formPanel.add(jt2);

        // Email

        jl4 =
                createLabel("Email");

        jt3 =
                createTextField();

        formPanel.add(jl4);
        formPanel.add(jt3);

        // Address

        jl5 =
                createLabel("Address");

        jt4 =
                createTextField();

        formPanel.add(jl5);
        formPanel.add(jt4);

        formCard.add(
                formPanel,
                BorderLayout.CENTER
        );

        // =====================================================
        // BUTTONS
        // =====================================================

        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                15,
                                10
                        )
                );

        buttonPanel.setBackground(Color.WHITE);

        jb1 =
                createButton("Save");

        jb2 =
                createButton("Clear");

        jb3 =
                createButton("Refresh");

        jb4 =
                createButton("Back");

        buttonPanel.add(jb1);
        buttonPanel.add(jb2);
        buttonPanel.add(jb3);
        buttonPanel.add(jb4);

        formCard.add(
                buttonPanel,
                BorderLayout.SOUTH
        );

        mainPanel.add(
                formCard,
                BorderLayout.NORTH
        );

        // =====================================================
        // CUSTOMER TABLE CARD
        // =====================================================

        JPanel tableCard =
                new JPanel(
                        new BorderLayout()
                );

        tableCard.setBackground(Color.WHITE);

        tableCard.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        220,
                                        225,
                                        230
                                )
                        ),
                        BorderFactory.createEmptyBorder(
                                20,
                                25,
                                20,
                                25
                        )
                )
        );

        JLabel tableTitle =
                new JLabel(
                        "Customer List"
                );

        tableTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        22
                )
        );

        tableTitle.setForeground(
                new Color(60, 60, 60)
        );

        tableCard.add(
                tableTitle,
                BorderLayout.NORTH
        );

        // =====================================================
        // TABLE
        // =====================================================

        String[] columns = {
                "Customer ID",
                "Name",
                "Phone",
                "Email",
                "Address"
        };

        model =
                new DefaultTableModel(
                        columns,
                        0
                ) {

                    @Override
                    public boolean isCellEditable(
                            int row,
                            int column
                    ) {
                        return false;
                    }
                };

        table =
                new JTable(model);

        table.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        table.setRowHeight(32);

        table.setSelectionMode(
                javax.swing.ListSelectionModel.SINGLE_SELECTION
        );

        table.getTableHeader()
                .setFont(
                        new Font(
                                "Segoe UI",
                                Font.BOLD,
                                14
                        )
                );

        table.getTableHeader()
                .setPreferredSize(
                        new Dimension(
                                0,
                                35
                        )
                );

        table.setAutoResizeMode(
                JTable.AUTO_RESIZE_ALL_COLUMNS
        );

        JScrollPane scrollPane =
                new JScrollPane(table);

        scrollPane.setBorder(
                BorderFactory.createLineBorder(
                        new Color(
                                220,
                                225,
                                230
                        )
                )
        );

        tableCard.add(
                scrollPane,
                BorderLayout.CENTER
        );

        mainPanel.add(
                tableCard,
                BorderLayout.CENTER
        );

        add(
                mainPanel,
                BorderLayout.CENTER
        );

        // =====================================================
        // ACTION LISTENERS
        // =====================================================

        jb1.addActionListener(this);

        jb2.addActionListener(this);

        jb3.addActionListener(this);

        jb4.addActionListener(this);

        // =====================================================
        // LOAD DATA
        // =====================================================

        loadCustomerData();

        // =====================================================
        // SHOW FRAME
        // =====================================================

        setVisible(true);
    }

    // =========================================================
    // ACTION PERFORMED
    // =========================================================

    @Override
    public void actionPerformed(ActionEvent ae) {

        // =====================================================
        // CLEAR
        // =====================================================

        if (ae.getSource() == jb2) {

            clearFields();
        }

        // =====================================================
        // SAVE
        // =====================================================

        if (ae.getSource() == jb1) {

            String name =
                    jt1.getText().trim();

            String phone =
                    jt2.getText().trim();

            String email =
                    jt3.getText().trim();

            String address =
                    jt4.getText().trim();

            // Check empty fields

            if (name.isEmpty()
                    || phone.isEmpty()
                    || email.isEmpty()
                    || address.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please fill all customer details.",
                        "Customer Details",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            try {

                Connection con =
                        Conn1.mycon();

                String sql =
                        "INSERT INTO customer "
                        + "(name, phone, email, address) "
                        + "VALUES (?, ?, ?, ?)";

                PreparedStatement ps =
                        con.prepareStatement(sql);

                ps.setString(
                        1,
                        name
                );

                ps.setString(
                        2,
                        phone
                );

                ps.setString(
                        3,
                        email
                );

                ps.setString(
                        4,
                        address
                );

                ps.executeUpdate();

                JOptionPane.showMessageDialog(
                        this,
                        "Customer Saved Successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );

                ps.close();

                con.close();

                clearFields();

                loadCustomerData();

            } catch (Exception e) {

                e.printStackTrace();

                JOptionPane.showMessageDialog(
                        this,
                        "Error saving customer:\n"
                        + e.getMessage(),
                        "Database Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }

        // =====================================================
        // REFRESH
        // =====================================================

        if (ae.getSource() == jb3) {

            loadCustomerData();
        }

        // =====================================================
        // BACK
        // =====================================================

        if (ae.getSource() == jb4) {

            dispose();
        }
    }

    // =========================================================
    // LOAD CUSTOMER DATA
    // =========================================================

    public void loadCustomerData() {

        try {

            Connection con =
                    Conn1.mycon();

            String sql =
                    "SELECT customer_id, name, phone, "
                    + "email, address "
                    + "FROM customer "
                    + "ORDER BY customer_id";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery();

            model.setRowCount(0);

            while (rs.next()) {

                Object[] row = {

                    rs.getInt(
                            "customer_id"
                    ),

                    rs.getString(
                            "name"
                    ),

                    rs.getString(
                            "phone"
                    ),

                    rs.getString(
                            "email"
                    ),

                    rs.getString(
                            "address"
                    )
                };

                model.addRow(row);
            }

            rs.close();

            ps.close();

            con.close();

        } catch (Exception e) {

            e.printStackTrace();

            JOptionPane.showMessageDialog(
                    this,
                    "Error loading customer data:\n"
                    + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================================================
    // CLEAR FIELDS
    // =========================================================

    private void clearFields() {

        jt1.setText("");

        jt2.setText("");

        jt3.setText("");

        jt4.setText("");

        jt1.requestFocus();
    }

    // =========================================================
    // CREATE LABEL
    // =========================================================

    private JLabel createLabel(
            String text) {

        JLabel label =
                new JLabel(text);

        label.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        15
                )
        );

        label.setForeground(
                new Color(
                        70,
                        70,
                        70
                )
        );

        return label;
    }

    // =========================================================
    // CREATE TEXT FIELD
    // =========================================================

    private JTextField createTextField() {

        JTextField field =
                new JTextField();

        field.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        15
                )
        );

        field.setPreferredSize(
                new Dimension(
                        250,
                        40
                )
        );

        field.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        205,
                                        210,
                                        215
                                )
                        ),
                        BorderFactory.createEmptyBorder(
                                5,
                                10,
                                5,
                                10
                        )
                )
        );

        return field;
    }

    // =========================================================
    // CREATE BUTTON
    // =========================================================

    private JButton createButton(
            String text) {

        JButton button =
                new JButton(text);

        button.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14
                )
        );

        button.setForeground(Color.WHITE);

        button.setBackground(
                new Color(
                        45,
                        80,
                        120
                )
        );

        button.setFocusPainted(false);

        button.setPreferredSize(
                new Dimension(
                        120,
                        40
                )
        );

        button.setMargin(
                new Insets(
                        5,
                        15,
                        5,
                        15
                )
        );

        return button;
    }
}