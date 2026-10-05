package com.HotelManagement;

import java.awt.BorderLayout;
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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import com.dynamicconn.Conn1;

public class BillPayment extends JFrame {

    private JTextField customerIdField;
    private JTextField customerNameField;
    private JTextField bookingIdField;
    private JTextField roomIdField;
    private JTextField roomAmountField;
    private JTextField serviceAmountField;
    private JTextField discountField;
    private JTextField totalAmountField;

    private JComboBox<String> paymentModeBox;
    private JComboBox<String> paymentStatusBox;

    public BillPayment() {
        setTitle("Bill & Payment");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(1000, 650));

        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        JLabel title = new JLabel("BILL & PAYMENT", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 30));

        JLabel subtitle = new JLabel(
                "Hotel Booking Billing and Payment",
                SwingConstants.CENTER
        );
        subtitle.setFont(new Font("Arial", Font.PLAIN, 16));

        JPanel header = new JPanel(new BorderLayout(5, 5));
        header.add(title, BorderLayout.NORTH);
        header.add(subtitle, BorderLayout.CENTER);

        mainPanel.add(header, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(
                BorderFactory.createEmptyBorder(20, 100, 20, 100)
        );

        customerIdField = createField();
        customerNameField = createField();
        bookingIdField = createField();
        roomIdField = createField();
        roomAmountField = createField();
        serviceAmountField = createField();
        discountField = createField();
        totalAmountField = createField();

        customerNameField.setEditable(false);
        roomIdField.setEditable(false);
        roomAmountField.setEditable(false);
        totalAmountField.setEditable(false);

        paymentModeBox = new JComboBox<>(
                new String[] {
                        "Cash",
                        "Card",
                        "UPI",
                        "Net Banking"
                }
        );

        paymentStatusBox = new JComboBox<>(
                new String[] {
                        "Paid",
                        "Pending"
                }
        );

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;

        addRow(formPanel, gbc, 0, "Customer ID", customerIdField);
        addRow(formPanel, gbc, 1, "Customer Name", customerNameField);
        addRow(formPanel, gbc, 2, "Booking ID", bookingIdField);
        addRow(formPanel, gbc, 3, "Room ID", roomIdField);
        addRow(formPanel, gbc, 4, "Room Amount", roomAmountField);
        addRow(formPanel, gbc, 5, "Service Amount", serviceAmountField);
        addRow(formPanel, gbc, 6, "Discount", discountField);
        addRow(formPanel, gbc, 7, "Total Amount", totalAmountField);
        addRow(formPanel, gbc, 8, "Payment Mode", paymentModeBox);
        addRow(formPanel, gbc, 9, "Payment Status", paymentStatusBox);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        JButton loadCustomerButton = new JButton("Load Customer");
        JButton loadBookingButton = new JButton("Load Booking");
        JButton calculateButton = new JButton("Calculate");
        JButton payButton = new JButton("Complete Payment");
        JButton clearButton = new JButton("Clear");
        JButton backButton = new JButton("Back");

        JPanel buttonPanel = new JPanel(
                new FlowLayout(FlowLayout.CENTER, 15, 10)
        );

        buttonPanel.add(loadCustomerButton);
        buttonPanel.add(loadBookingButton);
        buttonPanel.add(calculateButton);
        buttonPanel.add(payButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(backButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);

        loadCustomerButton.addActionListener(e -> loadCustomer());
        loadBookingButton.addActionListener(e -> loadBooking());
        calculateButton.addActionListener(e -> calculateTotal());
        payButton.addActionListener(e -> completePayment());
        clearButton.addActionListener(e -> clearFields());
        backButton.addActionListener(e -> dispose());

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JTextField createField() {
        JTextField field = new JTextField();
        field.setPreferredSize(new Dimension(250, 35));
        field.setFont(new Font("Arial", Font.PLAIN, 15));
        return field;
    }

    private void addRow(
            JPanel panel,
            GridBagConstraints gbc,
            int row,
            String label,
            java.awt.Component component
    ) {
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0.3;

        JLabel jLabel = new JLabel(label);
        jLabel.setFont(new Font("Arial", Font.BOLD, 15));
        panel.add(jLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        panel.add(component, gbc);
    }

    private void loadCustomer() {
        String customerIdText = customerIdField.getText().trim();

        if (customerIdText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter Customer ID");
            return;
        }

        try {
            int customerId = Integer.parseInt(customerIdText);

            Connection con = Conn1.mycon();

            if (con == null) {
                JOptionPane.showMessageDialog(
                        this,
                        "Database connection failed"
                );
                return;
            }

            String sql =
                    "SELECT name FROM customer WHERE customer_id = ?";

            try (PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setInt(1, customerId);

                try (ResultSet rs = ps.executeQuery()) {

                    if (rs.next()) {
                        customerNameField.setText(
                                rs.getString("name")
                        );
                    } else {
                        customerNameField.setText("");
                        JOptionPane.showMessageDialog(
                                this,
                                "Customer not found"
                        );
                    }
                }
            }

            con.close();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Customer ID must be a number"
            );
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Error: " + e.getMessage()
            );
        }
    }

    private void loadBooking() {
        String customerIdText =
                customerIdField.getText().trim();

        String bookingIdText =
                bookingIdField.getText().trim();

        if (customerIdText.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Enter Customer ID"
            );
            return;
        }

        if (bookingIdText.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Enter Booking ID"
            );
            return;
        }

        try {
            int customerId =
                    Integer.parseInt(customerIdText);

            int bookingId =
                    Integer.parseInt(bookingIdText);

            Connection con = Conn1.mycon();

            if (con == null) {
                JOptionPane.showMessageDialog(
                        this,
                        "Database connection failed"
                );
                return;
            }

            String sql =
                    "SELECT b.room_id, r.price_per_day, " +
                    "b.check_in, b.check_out " +
                    "FROM booking b " +
                    "JOIN room r ON b.room_id = r.room_id " +
                    "WHERE b.booking_id = ? " +
                    "AND b.customer_id = ?";

            try (PreparedStatement ps =
                         con.prepareStatement(sql)) {

                ps.setInt(1, bookingId);
                ps.setInt(2, customerId);

                try (ResultSet rs = ps.executeQuery()) {

                    if (rs.next()) {

                        int roomId =
                                rs.getInt("room_id");

                        double pricePerDay =
                                rs.getDouble("price_per_day");

                        roomIdField.setText(
                                String.valueOf(roomId)
                        );

                        long days = 1;

                        if (rs.getDate("check_in") != null
                                && rs.getDate("check_out") != null) {

                            long difference =
                                    rs.getDate("check_out").getTime()
                                    - rs.getDate("check_in").getTime();

                            days = difference /
                                    (1000 * 60 * 60 * 24);

                            if (days <= 0) {
                                days = 1;
                            }
                        }

                        double roomAmount =
                                pricePerDay * days;

                        roomAmountField.setText(
                                String.format(
                                        "%.2f",
                                        roomAmount
                                )
                        );

                    } else {
                        JOptionPane.showMessageDialog(
                                this,
                                "Booking not found for this Customer ID"
                        );
                    }
                }
            }

            con.close();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Customer ID and Booking ID must be numbers"
            );
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Error: " + e.getMessage()
            );
        }
    }

    private void calculateTotal() {

        try {

            double roomAmount =
                    getAmount(roomAmountField);

            double serviceAmount =
                    getAmount(serviceAmountField);

            double discount =
                    getAmount(discountField);

            if (serviceAmount < 0 || discount < 0) {
                JOptionPane.showMessageDialog(
                        this,
                        "Service amount and discount cannot be negative"
                );
                return;
            }

            double total =
                    roomAmount + serviceAmount - discount;

            if (total < 0) {
                total = 0;
            }

            totalAmountField.setText(
                    String.format("%.2f", total)
            );

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Enter valid numeric values"
            );
        }
    }

    private double getAmount(JTextField field) {

        String value = field.getText().trim();

        if (value.isEmpty()) {
            return 0;
        }

        return Double.parseDouble(value);
    }

    private void completePayment() {

        String customerIdText =
                customerIdField.getText().trim();

        String bookingIdText =
                bookingIdField.getText().trim();

        String roomIdText =
                roomIdField.getText().trim();

        String totalText =
                totalAmountField.getText().trim();

        if (customerIdText.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Enter Customer ID"
            );
            return;
        }

        if (bookingIdText.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Enter Booking ID"
            );
            return;
        }

        if (roomIdText.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Load booking details first"
            );
            return;
        }

        if (totalText.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Calculate the total amount first"
            );
            return;
        }

        try {

            int customerId =
                    Integer.parseInt(customerIdText);

            int bookingId =
                    Integer.parseInt(bookingIdText);

            int roomId =
                    Integer.parseInt(roomIdText);

            double roomAmount =
                    getAmount(roomAmountField);

            double serviceAmount =
                    getAmount(serviceAmountField);

            double discount =
                    getAmount(discountField);

            double totalAmount =
                    getAmount(totalAmountField);

            String paymentMode =
                    paymentModeBox
                            .getSelectedItem()
                            .toString();

            String paymentStatus =
                    paymentStatusBox
                            .getSelectedItem()
                            .toString();

            Connection con = Conn1.mycon();

            if (con == null) {
                JOptionPane.showMessageDialog(
                        this,
                        "Database connection failed"
                );
                return;
            }

            String checkSql =
                    "SELECT payment_id FROM payment " +
                    "WHERE booking_id = ?";

            try (PreparedStatement checkPs =
                         con.prepareStatement(checkSql)) {

                checkPs.setInt(1, bookingId);

                try (ResultSet rs =
                             checkPs.executeQuery()) {

                    if (rs.next()) {

                        JOptionPane.showMessageDialog(
                                this,
                                "Payment already exists for this Booking ID"
                        );

                        con.close();
                        return;
                    }
                }
            }

            String insertSql =
                    "INSERT INTO payment " +
                    "(booking_id, room_amount, service_amount, " +
                    "discount, total_amount, payment_mode, " +
                    "payment_status) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement ps =
                         con.prepareStatement(insertSql)) {

                ps.setInt(1, bookingId);
                ps.setDouble(2, roomAmount);
                ps.setDouble(3, serviceAmount);
                ps.setDouble(4, discount);
                ps.setDouble(5, totalAmount);
                ps.setString(6, paymentMode);
                ps.setString(7, paymentStatus);

                int result =
                        ps.executeUpdate();

                if (result > 0) {

                    String updateBooking =
                            "UPDATE booking " +
                            "SET status = 'Completed' " +
                            "WHERE booking_id = ?";

                    try (PreparedStatement bookingPs =
                                 con.prepareStatement(
                                         updateBooking)) {

                        bookingPs.setInt(1, bookingId);
                        bookingPs.executeUpdate();
                    }

                    String updateRoom =
                            "UPDATE room " +
                            "SET status = 'Available' " +
                            "WHERE room_id = ?";

                    try (PreparedStatement roomPs =
                                 con.prepareStatement(
                                         updateRoom)) {

                        roomPs.setInt(1, roomId);
                        roomPs.executeUpdate();
                    }

                    JOptionPane.showMessageDialog(
                            this,
                            "Payment completed successfully"
                    );

                    clearFields();
                }
            }

            con.close();

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Enter valid numeric values"
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Payment failed: " + e.getMessage()
            );
        }
    }

    private void clearFields() {

        customerIdField.setText("");
        customerNameField.setText("");
        bookingIdField.setText("");
        roomIdField.setText("");
        roomAmountField.setText("");
        serviceAmountField.setText("");
        discountField.setText("");
        totalAmountField.setText("");

        paymentModeBox.setSelectedIndex(0);
        paymentStatusBox.setSelectedIndex(0);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(
                () -> new BillPayment()
        );
    }
}