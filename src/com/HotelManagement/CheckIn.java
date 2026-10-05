package com.HotelManagement;

import java.awt.BorderLayout;
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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.dynamicconn.Conn1;

public class CheckIn extends JFrame {

    private JTextField customerIdField;
    private JTextField customerNameField;
    private JTextField phoneField;
    private JTextField emailField;
    private JTextField addressField;

    private JTextField roomIdField;
    private JTextField roomNumberField;
    private JTextField roomTypeField;

    private JTextField checkInDateField;

    private JButton loadCustomerButton;
    private JButton loadRoomButton;
    private JButton clearButton;
    private JButton checkInButton;
    private JButton backButton;

    public CheckIn() {

        setTitle("Hotel Management - Check In");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBorder(
                new EmptyBorder(30, 40, 30, 40)
        );

        JLabel titleLabel = new JLabel("CHECK IN");
        titleLabel.setFont(
                new Font("Arial", Font.BOLD, 30)
        );

        mainPanel.add(
                titleLabel,
                BorderLayout.NORTH
        );

        JPanel centerPanel = new JPanel(
                new GridBagLayout()
        );

        centerPanel.setBorder(
                BorderFactory.createTitledBorder(
                        "Customer & Room Information"
                )
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(10, 15, 10, 15);

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        gbc.weightx = 1;

        customerIdField = createField();
        customerNameField = createField();
        phoneField = createField();
        emailField = createField();
        addressField = createField();

        roomIdField = createField();
        roomNumberField = createField();
        roomTypeField = createField();

        checkInDateField = createField();

        customerNameField.setEditable(false);
        phoneField.setEditable(false);
        emailField.setEditable(false);
        addressField.setEditable(false);

        roomIdField.setEditable(false);
        roomNumberField.setEditable(false);
        roomTypeField.setEditable(false);

        checkInDateField.setEditable(false);

        int row = 0;

        addField(
                centerPanel,
                gbc,
                row++,
                "Customer ID",
                customerIdField
        );

        addField(
                centerPanel,
                gbc,
                row++,
                "Customer Name",
                customerNameField
        );

        addField(
                centerPanel,
                gbc,
                row++,
                "Phone",
                phoneField
        );

        addField(
                centerPanel,
                gbc,
                row++,
                "Email",
                emailField
        );

        addField(
                centerPanel,
                gbc,
                row++,
                "Address",
                addressField
        );

        addField(
                centerPanel,
                gbc,
                row++,
                "Room ID",
                roomIdField
        );

        addField(
                centerPanel,
                gbc,
                row++,
                "Room Number",
                roomNumberField
        );

        addField(
                centerPanel,
                gbc,
                row++,
                "Room Type",
                roomTypeField
        );

        addField(
                centerPanel,
                gbc,
                row++,
                "Check-In Date",
                checkInDateField
        );

        mainPanel.add(
                centerPanel,
                BorderLayout.CENTER
        );

        JPanel bottomPanel = new JPanel();

        loadCustomerButton =
                new JButton("Load Customer");

        loadRoomButton =
                new JButton("Load Booked Room");

        clearButton =
                new JButton("Clear");

        checkInButton =
                new JButton("Confirm Check-In");

        backButton =
                new JButton("Back");

        styleButton(loadCustomerButton);
        styleButton(loadRoomButton);
        styleButton(clearButton);
        styleButton(checkInButton);
        styleButton(backButton);

        bottomPanel.add(loadCustomerButton);
        bottomPanel.add(loadRoomButton);
        bottomPanel.add(clearButton);
        bottomPanel.add(checkInButton);
        bottomPanel.add(backButton);

        mainPanel.add(
                bottomPanel,
                BorderLayout.SOUTH
        );

        add(mainPanel);

        loadCustomerButton.addActionListener(
                e -> loadCustomer()
        );

        loadRoomButton.addActionListener(
                e -> loadBookedRoom()
        );

        clearButton.addActionListener(
                e -> clearFields()
        );

        checkInButton.addActionListener(
                e -> confirmCheckIn()
        );

        backButton.addActionListener(
                e -> dispose()
        );

        setMinimumSize(
                new Dimension(900, 650)
        );

        setLocationRelativeTo(null);

        setVisible(true);
    }

    private JTextField createField() {

        JTextField field =
                new JTextField();

        field.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        16
                )
        );

        field.setPreferredSize(
                new Dimension(300, 35)
        );

        return field;
    }

    private void addField(
            JPanel panel,
            GridBagConstraints gbc,
            int row,
            String label,
            JTextField field) {

        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0.3;

        JLabel lbl =
                new JLabel(label);

        lbl.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        15
                )
        );

        panel.add(lbl, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;

        panel.add(field, gbc);
    }

    private void styleButton(JButton button) {

        button.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        button.setPreferredSize(
                new Dimension(170, 40)
        );
    }

    private void loadCustomer() {

        String customerIdText =
                customerIdField.getText().trim();

        if (customerIdText.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter Customer ID."
            );

            return;
        }

        int customerId;

        try {

            customerId =
                    Integer.parseInt(
                            customerIdText
                    );

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Customer ID must be a number."
            );

            return;
        }

        String sql =
                "SELECT name, phone, email, address " +
                "FROM customer " +
                "WHERE customer_id = ?";

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

            ps.setInt(
                    1,
                    customerId
            );

            ResultSet rs =
                    ps.executeQuery();

            if (rs.next()) {

                customerNameField.setText(
                        rs.getString("name")
                );

                phoneField.setText(
                        rs.getString("phone")
                );

                emailField.setText(
                        rs.getString("email")
                );

                addressField.setText(
                        rs.getString("address")
                );

                checkInDateField.setText(
                        java.time.LocalDate
                                .now()
                                .toString()
                );

                JOptionPane.showMessageDialog(
                        this,
                        "Customer loaded successfully."
                );

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Customer not found."
                );
            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error: " + e.getMessage()
            );

            e.printStackTrace();
        }
    }

    private void loadBookedRoom() {

        String customerIdText =
                customerIdField.getText().trim();

        if (customerIdText.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter Customer ID first."
            );

            return;
        }

        int customerId;

        try {

            customerId =
                    Integer.parseInt(
                            customerIdText
                    );

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Customer ID must be a number."
            );

            return;
        }

        String sql =
                "SELECT b.room_id, " +
                "r.room_number, " +
                "r.room_type " +
                "FROM booking b " +
                "INNER JOIN room r " +
                "ON b.room_id = r.room_id " +
                "WHERE b.customer_id = ? " +
                "AND b.status = 'Booked' " +
                "ORDER BY b.booking_id DESC " +
                "LIMIT 1";

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

            ps.setInt(
                    1,
                    customerId
            );

            ResultSet rs =
                    ps.executeQuery();

            if (rs.next()) {

                roomIdField.setText(
                        String.valueOf(
                                rs.getInt("room_id")
                        )
                );

                roomNumberField.setText(
                        rs.getString("room_number")
                );

                roomTypeField.setText(
                        rs.getString("room_type")
                );

                checkInDateField.setText(
                        java.time.LocalDate
                                .now()
                                .toString()
                );

                JOptionPane.showMessageDialog(
                        this,
                        "Booked room loaded successfully."
                );

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "No booked room found for this customer."
                );

                roomIdField.setText("");
                roomNumberField.setText("");
                roomTypeField.setText("");
            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error: " + e.getMessage()
            );

            e.printStackTrace();
        }
    }

    private void confirmCheckIn() {

        if (customerIdField.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter Customer ID."
            );

            return;
        }

        if (customerNameField.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please load customer details first."
            );

            return;
        }

        if (roomIdField.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please load the booked room first."
            );

            return;
        }

        int choice =
                JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to confirm check-in?",
                        "Confirm Check-In",
                        JOptionPane.YES_NO_OPTION
                );

        if (choice != JOptionPane.YES_OPTION) {

            return;
        }

        JOptionPane.showMessageDialog(
                this,
                "Check-In completed successfully!\n\n"
                        + "Customer: "
                        + customerNameField.getText()
                        + "\nRoom: "
                        + roomNumberField.getText()
                        + "\nCheck-In Date: "
                        + checkInDateField.getText()
        );
    }

    private void clearFields() {

        customerIdField.setText("");
        customerNameField.setText("");
        phoneField.setText("");
        emailField.setText("");
        addressField.setText("");

        roomIdField.setText("");
        roomNumberField.setText("");
        roomTypeField.setText("");

        checkInDateField.setText("");
    }

    public static void main(String[] args) {

        new CheckIn();
    }
}