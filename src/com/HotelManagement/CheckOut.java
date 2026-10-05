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
import java.sql.Date;
import java.time.temporal.ChronoUnit;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.dynamicconn.Conn1;

public class CheckOut extends JFrame {

    private JTextField customerIdField;
    private JTextField customerNameField;
    private JTextField phoneField;
    private JTextField emailField;

    private JTextField roomIdField;
    private JTextField roomNumberField;
    private JTextField roomTypeField;
    private JTextField priceField;
    private JTextField checkInField;
    private JTextField checkOutField;
    private JTextField stayDaysField;
    private JTextField totalAmountField;

    private JButton loadButton;
    private JButton calculateButton;
    private JButton clearButton;
    private JButton confirmButton;
    private JButton backButton;

    private int bookingId;
    private int roomId;

    public CheckOut() {

        setTitle("Hotel Management System - Check-Out");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBackground(new Color(245, 247, 250));
        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        25, 40, 25, 40));

        JPanel headerPanel =
                new JPanel(new BorderLayout());

        headerPanel.setBackground(
                new Color(35, 47, 62));

        headerPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        20, 30, 20, 30));

        JLabel titleLabel =
                new JLabel("CHECK-OUT");

        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        30));

        JLabel subtitleLabel =
                new JLabel(
                        "Complete the customer's stay and checkout process");

        subtitleLabel.setForeground(
                new Color(215, 220, 225));

        subtitleLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        15));

        JPanel headerText =
                new JPanel(new GridBagLayout());

        headerText.setOpaque(false);

        GridBagConstraints headerGbc =
                new GridBagConstraints();

        headerGbc.gridx = 0;
        headerGbc.gridy = 0;
        headerGbc.anchor =
                GridBagConstraints.WEST;

        headerText.add(
                titleLabel,
                headerGbc);

        headerGbc.gridy = 1;
        headerGbc.insets =
                new Insets(5, 0, 0, 0);

        headerText.add(
                subtitleLabel,
                headerGbc);

        headerPanel.add(
                headerText,
                BorderLayout.WEST);

        mainPanel.add(
                headerPanel,
                BorderLayout.NORTH);

        JPanel cardsPanel =
                new JPanel(
                        new GridBagLayout());

        cardsPanel.setOpaque(false);

        GridBagConstraints cardGbc =
                new GridBagConstraints();

        cardGbc.fill =
                GridBagConstraints.BOTH;

        cardGbc.weighty = 1.0;

        cardGbc.weightx = 1.0;

        cardGbc.insets =
                new Insets(0, 0, 0, 10);

        JPanel customerCard =
                createCustomerCard();

        cardsPanel.add(
                customerCard,
                cardGbc);

        cardGbc.insets =
                new Insets(0, 10, 0, 0);

        JPanel stayCard =
                createStayCard();

        cardsPanel.add(
                stayCard,
                cardGbc);

        mainPanel.add(
                cardsPanel,
                BorderLayout.CENTER);

        JPanel bottomPanel =
                new JPanel(
                        new BorderLayout());

        bottomPanel.setOpaque(false);

        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                15,
                                5));

        buttonPanel.setOpaque(false);

        loadButton =
                createButton(
                        "Load Customer",
                        new Color(52, 120, 200));

        calculateButton =
                createButton(
                        "Calculate Bill",
                        new Color(52, 120, 200));

        clearButton =
                createButton(
                        "Clear",
                        new Color(100, 100, 100));

        confirmButton =
                createButton(
                        "Confirm Check-Out",
                        new Color(40, 160, 90));

        backButton =
                createButton(
                        "Back",
                        new Color(90, 90, 90));

        buttonPanel.add(loadButton);
        buttonPanel.add(calculateButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(confirmButton);
        buttonPanel.add(backButton);

        bottomPanel.add(
                buttonPanel,
                BorderLayout.CENTER);

        mainPanel.add(
                bottomPanel,
                BorderLayout.SOUTH);

        add(mainPanel);

        loadButton.addActionListener(
                e -> loadCustomer());

        calculateButton.addActionListener(
                e -> calculateBill());

        confirmButton.addActionListener(
                e -> confirmCheckOut());

        clearButton.addActionListener(
                e -> clearForm());

        backButton.addActionListener(
                e -> dispose());

        setVisible(true);
    }

    private JPanel createCustomerCard() {

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

        gbc.insets =
                new Insets(10, 5, 10, 5);

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        gbc.weightx = 1;

        JLabel title =
                new JLabel(
                        "Customer Information");

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

        card.add(
                title,
                gbc);

        customerIdField =
                createField(true);

        customerNameField =
                createField(false);

        phoneField =
                createField(false);

        emailField =
                createField(false);

        addRow(
                card,
                gbc,
                1,
                "Customer ID",
                customerIdField);

        addRow(
                card,
                gbc,
                2,
                "Customer Name",
                customerNameField);

        addRow(
                card,
                gbc,
                3,
                "Phone",
                phoneField);

        addRow(
                card,
                gbc,
                4,
                "Email",
                emailField);

        return card;
    }

    private JPanel createStayCard() {

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

        gbc.insets =
                new Insets(10, 5, 10, 5);

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        gbc.weightx = 1;

        JLabel title =
                new JLabel(
                        "Stay & Billing Information");

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

        card.add(
                title,
                gbc);

        roomIdField =
                createField(false);

        roomNumberField =
                createField(false);

        roomTypeField =
                createField(false);

        priceField =
                createField(false);

        checkInField =
                createField(false);

        checkOutField =
                createField(false);

        stayDaysField =
                createField(false);

        totalAmountField =
                createField(false);

        addRow(
                card,
                gbc,
                1,
                "Room ID",
                roomIdField);

        addRow(
                card,
                gbc,
                2,
                "Room Number",
                roomNumberField);

        addRow(
                card,
                gbc,
                3,
                "Room Type",
                roomTypeField);

        addRow(
                card,
                gbc,
                4,
                "Price / Day",
                priceField);

        addRow(
                card,
                gbc,
                5,
                "Check-In Date",
                checkInField);

        addRow(
                card,
                gbc,
                6,
                "Check-Out Date",
                checkOutField);

        addRow(
                card,
                gbc,
                7,
                "Stay Days",
                stayDaysField);

        addRow(
                card,
                gbc,
                8,
                "Total Amount",
                totalAmountField);

        return card;
    }

    private void addRow(
            JPanel panel,
            GridBagConstraints gbc,
            int row,
            String labelText,
            JTextField field) {

        JLabel label =
                new JLabel(labelText);

        label.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14));

        label.setForeground(
                new Color(70, 75, 80));

        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 1;
        gbc.weightx = 0.25;

        panel.add(
                label,
                gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.75;

        panel.add(
                field,
                gbc);
    }

    private JTextField createField(
            boolean editable) {

        JTextField field =
                new JTextField();

        field.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        15));

        field.setPreferredSize(
                new Dimension(280, 38));

        field.setEditable(editable);

        field.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(210, 215, 220)),
                        BorderFactory.createEmptyBorder(
                                5, 10, 5, 10)));

        return field;
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
                        14));

        button.setForeground(Color.WHITE);
        button.setBackground(background);
        button.setFocusPainted(false);

        button.setPreferredSize(
                new Dimension(160, 42));

        return button;
    }

    private void loadCustomer() {

        String idText =
                customerIdField.getText().trim();

        if (idText.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter Customer ID.");

            return;
        }

        int customerId;

        try {

            customerId =
                    Integer.parseInt(idText);

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Customer ID must be a number.");

            return;
        }

        String sql =
                "SELECT b.booking_id, "
                + "c.name, c.phone, c.email, "
                + "r.room_id, r.room_number, "
                + "r.room_type, r.price_per_day, "
                + "b.check_in, b.check_out "
                + "FROM booking b "
                + "JOIN customer c "
                + "ON b.customer_id = c.customer_id "
                + "JOIN room r "
                + "ON b.room_id = r.room_id "
                + "WHERE b.customer_id = ? "
                + "AND LOWER(TRIM(b.status)) = 'booked' "
                + "ORDER BY b.booking_id DESC "
                + "LIMIT 1";

        Connection con = null;

        try {

            con = Conn1.mycon();

            if (con == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Database connection failed.");

                return;
            }

            try (PreparedStatement ps =
                         con.prepareStatement(sql)) {

                ps.setInt(
                        1,
                        customerId);

                try (ResultSet rs =
                             ps.executeQuery()) {

                    if (!rs.next()) {

                        JOptionPane.showMessageDialog(
                                this,
                                "No active booking found for Customer ID "
                                + customerId);

                        return;
                    }

                    bookingId =
                            rs.getInt("booking_id");

                    roomId =
                            rs.getInt("room_id");

                    customerNameField.setText(
                            rs.getString("name"));

                    phoneField.setText(
                            rs.getString("phone"));

                    emailField.setText(
                            rs.getString("email"));

                    roomIdField.setText(
                            String.valueOf(roomId));

                    roomNumberField.setText(
                            String.valueOf(
                                    rs.getInt(
                                            "room_number")));

                    roomTypeField.setText(
                            rs.getString(
                                    "room_type"));

                    priceField.setText(
                            String.valueOf(
                                    rs.getDouble(
                                            "price_per_day")));

                    Date checkIn =
                            rs.getDate("check_in");

                    Date checkOut =
                            rs.getDate("check_out");

                    checkInField.setText(
                            checkIn.toLocalDate()
                                    .toString());

                    checkOutField.setText(
                            checkOut.toLocalDate()
                                    .toString());

                    long days =
                            ChronoUnit.DAYS.between(
                                    checkIn.toLocalDate(),
                                    checkOut.toLocalDate());

                    stayDaysField.setText(
                            String.valueOf(days));

                    totalAmountField.setText("");

                    JOptionPane.showMessageDialog(
                            this,
                            "Customer booking loaded successfully.");

                }
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error loading customer:\n"
                    + e.getMessage());

        } finally {

            try {

                if (con != null) {
                    con.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void calculateBill() {

        if (roomIdField.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please load customer first.");

            return;
        }

        try {

            double price =
                    Double.parseDouble(
                            priceField.getText());

            long days =
                    Long.parseLong(
                            stayDaysField.getText());

            double total =
                    price * days;

            totalAmountField.setText(
                    String.format(
                            "%.2f",
                            total));

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to calculate bill.");
        }
    }

    private void confirmCheckOut() {

        if (roomIdField.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please load customer first.");

            return;
        }

        if (totalAmountField.getText().trim().isEmpty()) {

            calculateBill();

            if (totalAmountField.getText().trim().isEmpty()) {
                return;
            }
        }

        int answer =
                JOptionPane.showConfirmDialog(
                        this,
                        "Confirm check-out for Customer ID "
                        + customerIdField.getText()
                        + "?",
                        "Confirm Check-Out",
                        JOptionPane.YES_NO_OPTION);

        if (answer != JOptionPane.YES_OPTION) {
            return;
        }

        Connection con = null;

        try {

            con = Conn1.mycon();

            if (con == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Database connection failed.");

                return;
            }

            con.setAutoCommit(false);

            String bookingSql =
                    "UPDATE booking "
                    + "SET status = 'Checked-Out' "
                    + "WHERE booking_id = ? "
                    + "AND LOWER(TRIM(status)) = 'booked'";

            try (PreparedStatement ps =
                         con.prepareStatement(
                                 bookingSql)) {

                ps.setInt(
                        1,
                        bookingId);

                int updated =
                        ps.executeUpdate();

                if (updated != 1) {

                    con.rollback();

                    JOptionPane.showMessageDialog(
                            this,
                            "Check-Out failed.");

                    return;
                }
            }

            String roomSql =
                    "UPDATE room "
                    + "SET status = 'Available' "
                    + "WHERE room_id = ?";

            try (PreparedStatement ps =
                         con.prepareStatement(roomSql)) {

                ps.setInt(
                        1,
                        roomId);

                ps.executeUpdate();
            }

            con.commit();

            JOptionPane.showMessageDialog(
                    this,
                    "Check-Out completed successfully.\n\n"
                    + "Customer ID: "
                    + customerIdField.getText()
                    + "\nRoom Number: "
                    + roomNumberField.getText()
                    + "\nTotal Amount: ₹"
                    + totalAmountField.getText());

            clearForm();

        } catch (Exception e) {

            try {

                if (con != null) {
                    con.rollback();
                }

            } catch (Exception ignored) {
            }

            JOptionPane.showMessageDialog(
                    this,
                    "Check-Out failed:\n"
                    + e.getMessage());

        } finally {

            try {

                if (con != null) {
                    con.setAutoCommit(true);
                    con.close();
                }

            } catch (Exception ignored) {
            }
        }
    }

    private void clearForm() {

        customerIdField.setText("");

        customerNameField.setText("");
        phoneField.setText("");
        emailField.setText("");

        roomIdField.setText("");
        roomNumberField.setText("");
        roomTypeField.setText("");
        priceField.setText("");
        checkInField.setText("");
        checkOutField.setText("");
        stayDaysField.setText("");
        totalAmountField.setText("");

        bookingId = 0;
        roomId = 0;

        customerIdField.requestFocus();
    }

    public static void main(String[] args) {

        new CheckOut();
    }
}