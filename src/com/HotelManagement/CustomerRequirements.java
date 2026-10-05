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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.dynamicconn.Conn1;

public class CustomerRequirements extends JFrame {

    private JTextField customerIdField;
    private JTextField customerNameField;
    private JTextField personsField;
    private JTextField budgetField;
    private JTextField daysField;

    private JComboBox<String> roomTypeBox;
    private JComboBox<String> acBox;
    private JComboBox<String> extraBedBox;

    private JTable customerTable;
    private DefaultTableModel tableModel;

    private JButton saveButton;
    private JButton clearButton;
    private JButton refreshButton;
    private JButton backButton;

    public CustomerRequirements() {

        setTitle("Hotel Management System - Customer Requirements");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBackground(new Color(245, 247, 250));
        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(25, 35, 25, 35));

        mainPanel.add(createHeader(), BorderLayout.NORTH);
        mainPanel.add(createCenterPanel(), BorderLayout.CENTER);
        mainPanel.add(createBottomPanel(), BorderLayout.SOUTH);

        add(mainPanel);

        loadCustomers();

        setVisible(true);
    }

    private JPanel createHeader() {

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(35, 47, 62));
        header.setBorder(
                BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JLabel title = new JLabel("CUSTOMER REQUIREMENTS");

        title.setForeground(Color.WHITE);
        title.setFont(
                new Font("Segoe UI", Font.BOLD, 30));

        JLabel subtitle = new JLabel(
                "Select a customer and enter room requirements");

        subtitle.setForeground(
                new Color(215, 220, 225));

        subtitle.setFont(
                new Font("Segoe UI", Font.PLAIN, 15));

        JPanel textPanel =
                new JPanel(new GridBagLayout());

        textPanel.setOpaque(false);

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;

        textPanel.add(title, gbc);

        gbc.gridy = 1;
        gbc.insets = new Insets(5, 0, 0, 0);

        textPanel.add(subtitle, gbc);

        header.add(
                textPanel,
                BorderLayout.WEST);

        return header;
    }

    private JPanel createCenterPanel() {

        JPanel center =
                new JPanel(
                        new GridBagLayout());

        center.setOpaque(false);

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.fill =
                GridBagConstraints.BOTH;

        gbc.weighty = 1;

        gbc.weightx = 0.45;

        gbc.insets =
                new Insets(0, 0, 0, 10);

        center.add(
                createCustomerListCard(),
                gbc);

        gbc.weightx = 0.55;
        gbc.insets =
                new Insets(0, 10, 0, 0);

        center.add(
                createRequirementCard(),
                gbc);

        return center;
    }

    private JPanel createCustomerListCard() {

        JPanel card =
                new JPanel(new BorderLayout(10, 15));

        card.setBackground(Color.WHITE);

        card.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(220, 224, 230)),
                        BorderFactory.createEmptyBorder(
                                25, 25, 25, 25)));

        JPanel heading =
                new JPanel(new BorderLayout());

        heading.setOpaque(false);

        JLabel title =
                new JLabel("CUSTOMERS");

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        21));

        title.setForeground(
                new Color(35, 47, 62));

        JLabel info =
                new JLabel(
                        "Select a customer from the list");

        info.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13));

        info.setForeground(
                new Color(100, 105, 110));

        heading.add(
                title,
                BorderLayout.NORTH);

        heading.add(
                info,
                BorderLayout.SOUTH);

        card.add(
                heading,
                BorderLayout.NORTH);

        tableModel =
                new DefaultTableModel(
                        new String[]{
                                "Customer ID",
                                "Name",
                                "Phone"
                        },
                        0) {

                    @Override
                    public boolean isCellEditable(
                            int row,
                            int column) {

                        return false;
                    }
                };

        customerTable =
                new JTable(tableModel);

        customerTable.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14));

        customerTable.setRowHeight(38);

        customerTable.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION);

        customerTable.setShowGrid(true);

        customerTable.setGridColor(
                new Color(225, 225, 225));

        customerTable.getTableHeader()
                .setFont(
                        new Font(
                                "Segoe UI",
                                Font.BOLD,
                                14));

        customerTable.getTableHeader()
                .setPreferredSize(
                        new Dimension(0, 40));

        customerTable.getColumnModel()
                .getColumn(0)
                .setPreferredWidth(90);

        customerTable.getColumnModel()
                .getColumn(1)
                .setPreferredWidth(180);

        customerTable.getColumnModel()
                .getColumn(2)
                .setPreferredWidth(150);

        JScrollPane scrollPane =
                new JScrollPane(customerTable);

        scrollPane.setBorder(
                BorderFactory.createLineBorder(
                        new Color(215, 220, 225)));

        card.add(
                scrollPane,
                BorderLayout.CENTER);

        customerTable.getSelectionModel()
                .addListSelectionListener(
                        e -> {

                            if (!e.getValueIsAdjusting()) {

                                int row =
                                        customerTable
                                                .getSelectedRow();

                                if (row >= 0) {

                                    customerIdField.setText(
                                            tableModel
                                                    .getValueAt(
                                                            row,
                                                            0)
                                                    .toString());

                                    customerNameField.setText(
                                            tableModel
                                                    .getValueAt(
                                                            row,
                                                            1)
                                                    .toString());
                                }
                            }
                        });

        return card;
    }

    private JPanel createRequirementCard() {

        JPanel card =
                new JPanel(
                        new GridBagLayout());

        card.setBackground(Color.WHITE);

        card.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(220, 224, 230)),
                        BorderFactory.createEmptyBorder(
                                25, 30, 25, 30)));

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        gbc.insets =
                new Insets(8, 5, 8, 5);

        gbc.weightx = 1;

        JLabel title =
                new JLabel("REQUIREMENT DETAILS");

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        21));

        title.setForeground(
                new Color(35, 47, 62));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        card.add(title, gbc);

        JLabel info =
                new JLabel(
                        "Enter the customer's room requirements");

        info.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13));

        info.setForeground(
                new Color(100, 105, 110));

        gbc.gridy = 1;

        card.add(info, gbc);

        customerIdField =
                createTextField();

        customerNameField =
                createTextField();

        customerIdField.setEditable(false);
        customerNameField.setEditable(false);

        addRow(
                card,
                gbc,
                2,
                "Customer ID",
                customerIdField);

        addRow(
                card,
                gbc,
                3,
                "Customer Name",
                customerNameField);

        personsField =
                createTextField();

        addRow(
                card,
                gbc,
                4,
                "Persons",
                personsField);

        roomTypeBox =
                createComboBox(
                        "Select Room Type",
                        "Single",
                        "Double",
                        "Deluxe");

        addRow(
                card,
                gbc,
                5,
                "Room Type",
                roomTypeBox);

        budgetField =
                createTextField();

        addRow(
                card,
                gbc,
                6,
                "Budget",
                budgetField);

        acBox =
                createComboBox(
                        "Select AC Type",
                        "AC",
                        "Non-AC");

        addRow(
                card,
                gbc,
                7,
                "AC Required",
                acBox);

        daysField =
                createTextField();

        addRow(
                card,
                gbc,
                8,
                "Days",
                daysField);

        extraBedBox =
                createComboBox(
                        "Select Extra Bed",
                        "Yes",
                        "No");

        addRow(
                card,
                gbc,
                9,
                "Extra Bed",
                extraBedBox);

        JLabel note =
                new JLabel(
                        "Select a customer from the left list before saving.");

        note.setFont(
                new Font(
                        "Segoe UI",
                        Font.ITALIC,
                        12));

        note.setForeground(
                new Color(120, 120, 120));

        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.gridwidth = 2;

        card.add(note, gbc);

        return card;
    }

    private void addRow(
            JPanel panel,
            GridBagConstraints gbc,
            int row,
            String labelText,
            java.awt.Component component) {

        JLabel label =
                new JLabel(labelText);

        label.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14));

        label.setForeground(
                new Color(65, 70, 75));

        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 1;
        gbc.weightx = 0.35;

        panel.add(label, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.65;

        panel.add(component, gbc);
    }

    private JTextField createTextField() {

        JTextField field =
                new JTextField();

        field.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14));

        field.setPreferredSize(
                new Dimension(
                        250,
                        38));

        field.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(205, 210, 215)),
                        BorderFactory.createEmptyBorder(
                                5, 10, 5, 10)));

        return field;
    }

    private JComboBox<String> createComboBox(
            String first,
            String second,
            String third,
            String fourth) {

        JComboBox<String> box =
                new JComboBox<>(
                        new String[]{
                                first,
                                second,
                                third,
                                fourth
                        });

        box.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14));

        box.setPreferredSize(
                new Dimension(
                        250,
                        38));

        return box;
    }

    private JComboBox<String> createComboBox(
            String first,
            String second,
            String third) {

        JComboBox<String> box =
                new JComboBox<>(
                        new String[]{
                                first,
                                second,
                                third
                        });

        box.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14));

        box.setPreferredSize(
                new Dimension(
                        250,
                        38));

        return box;
    }

    private JPanel createBottomPanel() {

        JPanel panel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                15,
                                5));

        panel.setOpaque(false);

        saveButton =
                createButton(
                        "SAVE REQUIREMENT",
                        new Color(40, 160, 90));

        clearButton =
                createButton(
                        "CLEAR",
                        new Color(100, 100, 100));

        refreshButton =
                createButton(
                        "REFRESH CUSTOMERS",
                        new Color(52, 120, 200));

        backButton =
                createButton(
                        "BACK",
                        new Color(80, 80, 80));

        panel.add(saveButton);
        panel.add(clearButton);
        panel.add(refreshButton);
        panel.add(backButton);

        saveButton.addActionListener(
                e -> saveRequirement());

        clearButton.addActionListener(
                e -> clearForm());

        refreshButton.addActionListener(
                e -> loadCustomers());

        backButton.addActionListener(
                e -> dispose());

        return panel;
    }

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

        button.setForeground(Color.WHITE);

        button.setBackground(background);

        button.setFocusPainted(false);

        button.setPreferredSize(
                new Dimension(
                        175,
                        42));

        return button;
    }

    private void loadCustomers() {

        tableModel.setRowCount(0);

        String sql =
                "SELECT customer_id, name, phone "
                + "FROM customer "
                + "ORDER BY customer_id";

        try (
                Connection con =
                        Conn1.mycon();

                PreparedStatement ps =
                        con.prepareStatement(sql);

                ResultSet rs =
                        ps.executeQuery()) {

            while (rs.next()) {

                tableModel.addRow(
                        new Object[]{
                                rs.getInt("customer_id"),
                                rs.getString("name"),
                                rs.getString("phone")
                        });
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to load customers:\n"
                    + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void saveRequirement() {

        String customerIdText =
                customerIdField.getText().trim();

        String personsText =
                personsField.getText().trim();

        String budgetText =
                budgetField.getText().trim();

        String daysText =
                daysField.getText().trim();

        if (customerIdText.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a customer from the list.",
                    "Validation",
                    JOptionPane.WARNING_MESSAGE);

            return;
        }

        if (personsText.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter number of persons.",
                    "Validation",
                    JOptionPane.WARNING_MESSAGE);

            return;
        }

        if (budgetText.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter budget.",
                    "Validation",
                    JOptionPane.WARNING_MESSAGE);

            return;
        }

        if (daysText.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter number of days.",
                    "Validation",
                    JOptionPane.WARNING_MESSAGE);

            return;
        }

        if (roomTypeBox.getSelectedIndex() == 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select room type.",
                    "Validation",
                    JOptionPane.WARNING_MESSAGE);

            return;
        }

        if (acBox.getSelectedIndex() == 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select AC requirement.",
                    "Validation",
                    JOptionPane.WARNING_MESSAGE);

            return;
        }

        if (extraBedBox.getSelectedIndex() == 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select extra bed requirement.",
                    "Validation",
                    JOptionPane.WARNING_MESSAGE);

            return;
        }

        int customerId;
        int persons;
        double budget;
        int days;

        try {

            customerId =
                    Integer.parseInt(
                            customerIdText);

            persons =
                    Integer.parseInt(
                            personsText);

            budget =
                    Double.parseDouble(
                            budgetText);

            days =
                    Integer.parseInt(
                            daysText);

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter valid numeric values.",
                    "Validation",
                    JOptionPane.WARNING_MESSAGE);

            return;
        }

        if (persons <= 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "Persons must be greater than 0.");

            return;
        }

        if (budget <= 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "Budget must be greater than 0.");

            return;
        }

        if (days <= 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "Days must be greater than 0.");

            return;
        }

        String roomType =
                roomTypeBox
                        .getSelectedItem()
                        .toString();

        String acRequired =
                acBox
                        .getSelectedItem()
                        .toString();

        String extraBed =
                extraBedBox
                        .getSelectedItem()
                        .toString();

        String sql =
                "INSERT INTO customer_requirement "
                + "(customer_id, persons, preferred_room_type, "
                + "budget, ac_required, days, extra_bed) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (
                Connection con =
                        Conn1.mycon();

                PreparedStatement ps =
                        con.prepareStatement(sql)) {

            ps.setInt(1, customerId);
            ps.setInt(2, persons);
            ps.setString(3, roomType);
            ps.setDouble(4, budget);
            ps.setString(5, acRequired);
            ps.setInt(6, days);
            ps.setString(7, extraBed);

            int result =
                    ps.executeUpdate();

            if (result == 1) {

                JOptionPane.showMessageDialog(
                        this,
                        "Customer requirement saved successfully.",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);

                clearForm();
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to save requirement:\n"
                    + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearForm() {

        customerIdField.setText("");
        customerNameField.setText("");
        personsField.setText("");
        budgetField.setText("");
        daysField.setText("");

        roomTypeBox.setSelectedIndex(0);
        acBox.setSelectedIndex(0);
        extraBedBox.setSelectedIndex(0);

        customerTable.clearSelection();

        customerIdField.requestFocus();
    }

    public static void main(String[] args) {

        new CustomerRequirements();
    }
}