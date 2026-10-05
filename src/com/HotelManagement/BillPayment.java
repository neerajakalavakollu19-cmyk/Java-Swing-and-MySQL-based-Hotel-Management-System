package com.HotelManagement;

import com.dynamicconn.Conn1;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class BillPayment extends JFrame {

    private JTextField customerIdField;
    private JTextField customerNameField;
    private JTextField phoneField;

    private JTextField bookingIdField;
    private JTextField roomIdField;
    private JTextField roomNumberField;
    private JTextField roomTypeField;
    private JTextField priceField;
    private JTextField daysField;

    private JTextField roomAmountField;
    private JTextField serviceAmountField;
    private JTextField discountField;
    private JTextField totalAmountField;

    private JComboBox<String> paymentModeBox;

    public BillPayment() {

        setTitle("Hotel Management System - Bill & Payment");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(245, 247, 250));

        // ================= HEADER =================

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(32, 55, 90));
        header.setBorder(new EmptyBorder(20, 30, 20, 30));

        JLabel title = new JLabel("BILL & PAYMENT");
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setForeground(Color.WHITE);

        JLabel subtitle = new JLabel(
                "Enter booking details and complete customer payment"
        );
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        subtitle.setForeground(Color.WHITE);

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setOpaque(false);

        titlePanel.add(title);
        titlePanel.add(Box.createVerticalStrut(5));
        titlePanel.add(subtitle);

        header.add(titlePanel, BorderLayout.WEST);

        // ================= CENTER =================

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(new Color(245, 247, 250));
        centerPanel.setBorder(
                new EmptyBorder(30, 50, 20, 50)
        );

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;

        // ================= CUSTOMER CARD =================

        JPanel customerCard = createCard();
        customerCard.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(8, 8, 8, 8);
        c.fill = GridBagConstraints.HORIZONTAL;

        JLabel customerTitle =
                createSectionTitle("Customer Details");

        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;

        customerCard.add(customerTitle, c);

        c.gridwidth = 1;

        c.gridx = 0;
        c.gridy = 1;

        customerCard.add(
                createLabel("Customer ID"),
                c
        );

        customerIdField = createField();

        c.gridx = 1;

        customerCard.add(
                customerIdField,
                c
        );

        c.gridx = 0;
        c.gridy = 2;

        customerCard.add(
                createLabel("Customer Name"),
                c
        );

        customerNameField = createField();
        customerNameField.setEditable(false);

        c.gridx = 1;

        customerCard.add(
                customerNameField,
                c
        );

        c.gridx = 0;
        c.gridy = 3;

        customerCard.add(
                createLabel("Phone"),
                c
        );

        phoneField = createField();
        phoneField.setEditable(false);

        c.gridx = 1;

        customerCard.add(
                phoneField,
                c
        );

        JButton loadCustomerButton =
                createButton("Load Customer");

        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 2;

        customerCard.add(
                loadCustomerButton,
                c
        );

        loadCustomerButton.addActionListener(
                e -> loadCustomer()
        );

        // ================= BOOKING CARD =================

        JPanel bookingCard = createCard();
        bookingCard.setLayout(new GridBagLayout());

        GridBagConstraints b = new GridBagConstraints();
        b.insets = new Insets(8, 8, 8, 8);
        b.fill = GridBagConstraints.HORIZONTAL;

        JLabel bookingTitle =
                createSectionTitle("Booking & Room Details");

        b.gridx = 0;
        b.gridy = 0;
        b.gridwidth = 2;

        bookingCard.add(
                bookingTitle,
                b
        );

        b.gridwidth = 1;

        // Booking ID

        b.gridx = 0;
        b.gridy = 1;

        bookingCard.add(
                createLabel("Booking ID"),
                b
        );

        bookingIdField = createField();

        b.gridx = 1;

        bookingCard.add(
                bookingIdField,
                b
        );

        // Load Booking button

        JButton loadBookingButton =
                createButton("Load Booking");

        b.gridx = 0;
        b.gridy = 2;
        b.gridwidth = 2;

        bookingCard.add(
                loadBookingButton,
                b
        );

        loadBookingButton.addActionListener(
                e -> loadBooking()
        );

        b.gridwidth = 1;

        // Room ID

        b.gridx = 0;
        b.gridy = 3;

        bookingCard.add(
                createLabel("Room ID"),
                b
        );

        roomIdField = createField();
        roomIdField.setEditable(false);

        b.gridx = 1;

        bookingCard.add(
                roomIdField,
                b
        );

        // Room Number

        b.gridx = 0;
        b.gridy = 4;

        bookingCard.add(
                createLabel("Room Number"),
                b
        );

        roomNumberField = createField();
        roomNumberField.setEditable(false);

        b.gridx = 1;

        bookingCard.add(
                roomNumberField,
                b
        );

        // Room Type

        b.gridx = 0;
        b.gridy = 5;

        bookingCard.add(
                createLabel("Room Type"),
                b
        );

        roomTypeField = createField();
        roomTypeField.setEditable(false);

        b.gridx = 1;

        bookingCard.add(
                roomTypeField,
                b
        );

        // Price

        b.gridx = 0;
        b.gridy = 6;

        bookingCard.add(
                createLabel("Price / Day"),
                b
        );

        priceField = createField();
        priceField.setEditable(false);

        b.gridx = 1;

        bookingCard.add(
                priceField,
                b
        );

        // Stay Days

        b.gridx = 0;
        b.gridy = 7;

        bookingCard.add(
                createLabel("Stay Days"),
                b
        );

        daysField = createField();
        daysField.setEditable(false);

        b.gridx = 1;

        bookingCard.add(
                daysField,
                b
        );

        // ================= BILL CARD =================

        JPanel billCard = createCard();
        billCard.setLayout(new GridBagLayout());

        GridBagConstraints d = new GridBagConstraints();
        d.insets = new Insets(8, 8, 8, 8);
        d.fill = GridBagConstraints.HORIZONTAL;

        JLabel billTitle =
                createSectionTitle("Bill & Payment Details");

        d.gridx = 0;
        d.gridy = 0;
        d.gridwidth = 2;

        billCard.add(
                billTitle,
                d
        );

        d.gridwidth = 1;

        // Room Amount

        d.gridx = 0;
        d.gridy = 1;

        billCard.add(
                createLabel("Room Amount"),
                d
        );

        roomAmountField = createField();
        roomAmountField.setEditable(false);

        d.gridx = 1;

        billCard.add(
                roomAmountField,
                d
        );

        // Service Amount

        d.gridx = 0;
        d.gridy = 2;

        billCard.add(
                createLabel("Service Amount"),
                d
        );

        serviceAmountField = createField();

        d.gridx = 1;

        billCard.add(
                serviceAmountField,
                d
        );

        // Discount

        d.gridx = 0;
        d.gridy = 3;

        billCard.add(
                createLabel("Discount"),
                d
        );

        discountField = createField();

        d.gridx = 1;

        billCard.add(
                discountField,
                d
        );

        // Total

        d.gridx = 0;
        d.gridy = 4;

        billCard.add(
                createLabel("Total Amount"),
                d
        );

        totalAmountField = createField();
        totalAmountField.setEditable(false);

        totalAmountField.setFont(
                new Font("Segoe UI", Font.BOLD, 18)
        );

        d.gridx = 1;

        billCard.add(
                totalAmountField,
                d
        );

        // Payment Mode

        d.gridx = 0;
        d.gridy = 5;

        billCard.add(
                createLabel("Payment Mode"),
                d
        );

        paymentModeBox = new JComboBox<>();

        paymentModeBox.addItem("Cash");
        paymentModeBox.addItem("UPI");
        paymentModeBox.addItem("Card");
        paymentModeBox.addItem("Net Banking");

        d.gridx = 1;

        billCard.add(
                paymentModeBox,
                d
        );

        // ================= BUTTONS =================

        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                15,
                                10
                        )
                );

        buttonPanel.setOpaque(false);

        JButton calculateButton =
                createButton("Calculate Bill");

        JButton paymentButton =
                createButton("Complete Payment");

        JButton clearButton =
                createButton("Clear");

        JButton backButton =
                createButton("Back");

        buttonPanel.add(calculateButton);
        buttonPanel.add(paymentButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(backButton);

        calculateButton.addActionListener(
                e -> calculateBill()
        );

        paymentButton.addActionListener(
                e -> completePayment()
        );

        clearButton.addActionListener(
                e -> clearFields()
        );

        backButton.addActionListener(
                e -> dispose()
        );

        // ================= ADD CARDS =================

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1;

        centerPanel.add(
                customerCard,
                gbc
        );

        gbc.gridx = 1;

        centerPanel.add(
                bookingCard,
                gbc
        );

        gbc.gridx = 2;

        centerPanel.add(
                billCard,
                gbc
        );

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.weighty = 0;

        centerPanel.add(
                buttonPanel,
                gbc
        );

        mainPanel.add(
                header,
                BorderLayout.NORTH
        );

        mainPanel.add(
                centerPanel,
                BorderLayout.CENTER
        );

        add(mainPanel);

        setVisible(true);
    }

    // =========================================================
    // LOAD CUSTOMER
    // =========================================================

    private void loadCustomer() {

        String customerText =
                customerIdField.getText().trim();

        if (customerText.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter Customer ID."
            );

            return;
        }

        try {

            int customerId =
                    Integer.parseInt(customerText);

            Connection con =
                    Conn1.mycon();

            String sql =
                    "SELECT name, phone " +
                    "FROM customer " +
                    "WHERE customer_id = ?";

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

                JOptionPane.showMessageDialog(
                        this,
                        "Customer loaded successfully."
                );

            } else {

                customerNameField.setText("");
                phoneField.setText("");

                JOptionPane.showMessageDialog(
                        this,
                        "Customer ID not found."
                );
            }

            rs.close();
            ps.close();
            con.close();

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Customer ID must be a number."
            );

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error loading customer:\n"
                            + ex.getMessage()
            );

            ex.printStackTrace();
        }
    }

    // =========================================================
    // LOAD BOOKING
    // =========================================================

    private void loadBooking() {

        String customerText =
                customerIdField.getText().trim();

        String bookingText =
                bookingIdField.getText().trim();

        if (customerText.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter Customer ID and load customer first."
            );

            return;
        }

        if (bookingText.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter Booking ID."
            );

            return;
        }

        try {

            int customerId =
                    Integer.parseInt(customerText);

            int bookingId =
                    Integer.parseInt(bookingText);

            Connection con =
                    Conn1.mycon();

            String sql =
                    "SELECT b.booking_id, " +
                    "b.customer_id, " +
                    "b.room_id, " +
                    "b.check_in, " +
                    "b.status, " +
                    "r.room_number, " +
                    "r.room_type, " +
                    "r.price_per_day " +
                    "FROM booking b " +
                    "INNER JOIN room r " +
                    "ON b.room_id = r.room_id " +
                    "WHERE b.booking_id = ? " +
                    "AND b.customer_id = ?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(
                    1,
                    bookingId
            );

            ps.setInt(
                    2,
                    customerId
            );

            ResultSet rs =
                    ps.executeQuery();

            if (!rs.next()) {

                clearBookingFields();

                JOptionPane.showMessageDialog(
                        this,
                        "Booking ID does not belong to this customer."
                );

                rs.close();
                ps.close();
                con.close();

                return;
            }

            String status =
                    rs.getString("status");

            if (!"Booked".equalsIgnoreCase(status)) {

                clearBookingFields();

                JOptionPane.showMessageDialog(
                        this,
                        "This booking is not currently Booked.\n"
                                + "Current status: "
                                + status
                );

                rs.close();
                ps.close();
                con.close();

                return;
            }

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

            double price =
                    rs.getDouble("price_per_day");

            priceField.setText(
                    String.valueOf(price)
            );

            Date checkIn =
                    rs.getDate("check_in");

            LocalDate checkInDate =
                    checkIn.toLocalDate();

            LocalDate today =
                    LocalDate.now();

            long days =
                    ChronoUnit.DAYS.between(
                            checkInDate,
                            today
                    );

            if (days <= 0) {
                days = 1;
            }

            daysField.setText(
                    String.valueOf(days)
            );

            double roomAmount =
                    price * days;

            roomAmountField.setText(
                    String.format(
                            "%.2f",
                            roomAmount
                    )
            );

            rs.close();
            ps.close();
            con.close();

            JOptionPane.showMessageDialog(
                    this,
                    "Booking loaded successfully."
            );

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Customer ID and Booking ID must be numbers."
            );

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error loading booking:\n"
                            + ex.getMessage()
            );

            ex.printStackTrace();
        }
    }

    // =========================================================
    // CALCULATE BILL
    // =========================================================

    private void calculateBill() {

        if (bookingIdField.getText().trim().isEmpty()
                || roomAmountField.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter Booking ID and load booking first."
            );

            return;
        }

        try {

            double roomAmount =
                    Double.parseDouble(
                            roomAmountField.getText()
                    );

            double serviceAmount = 0;

            double discount = 0;

            if (!serviceAmountField
                    .getText()
                    .trim()
                    .isEmpty()) {

                serviceAmount =
                        Double.parseDouble(
                                serviceAmountField
                                        .getText()
                                        .trim()
                        );
            }

            if (!discountField
                    .getText()
                    .trim()
                    .isEmpty()) {

                discount =
                        Double.parseDouble(
                                discountField
                                        .getText()
                                        .trim()
                        );
            }

            if (serviceAmount < 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Service amount cannot be negative."
                );

                return;
            }

            if (discount < 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Discount cannot be negative."
                );

                return;
            }

            double totalAmount =
                    roomAmount
                            + serviceAmount
                            - discount;

            if (totalAmount < 0) {
                totalAmount = 0;
            }

            totalAmountField.setText(
                    String.format(
                            "%.2f",
                            totalAmount
                    )
            );

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter valid numbers."
            );
        }
    }

    // =========================================================
    // COMPLETE PAYMENT
    // =========================================================

    private void completePayment() {

        String bookingText =
                bookingIdField.getText().trim();

        if (bookingText.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter Booking ID and load booking first."
            );

            return;
        }

        if (totalAmountField
                .getText()
                .trim()
                .isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please calculate the bill first."
            );

            return;
        }

        try {

            int bookingId =
                    Integer.parseInt(bookingText);

            double roomAmount =
                    Double.parseDouble(
                            roomAmountField.getText()
                    );

            double serviceAmount = 0;

            double discount = 0;

            if (!serviceAmountField
                    .getText()
                    .trim()
                    .isEmpty()) {

                serviceAmount =
                        Double.parseDouble(
                                serviceAmountField
                                        .getText()
                                        .trim()
                        );
            }

            if (!discountField
                    .getText()
                    .trim()
                    .isEmpty()) {

                discount =
                        Double.parseDouble(
                                discountField
                                        .getText()
                                        .trim()
                        );
            }

            double totalAmount =
                    Double.parseDouble(
                            totalAmountField.getText()
                    );

            String paymentMode =
                    paymentModeBox
                            .getSelectedItem()
                            .toString();

            int choice =
                    JOptionPane.showConfirmDialog(
                            this,
                            "Booking ID: "
                                    + bookingId
                                    + "\nRoom Amount: ₹"
                                    + String.format(
                                    "%.2f",
                                    roomAmount
                            )
                                    + "\nService Amount: ₹"
                                    + String.format(
                                    "%.2f",
                                    serviceAmount
                            )
                                    + "\nDiscount: ₹"
                                    + String.format(
                                    "%.2f",
                                    discount
                            )
                                    + "\nTotal Amount: ₹"
                                    + String.format(
                                    "%.2f",
                                    totalAmount
                            )
                                    + "\nPayment Mode: "
                                    + paymentMode
                                    + "\n\nComplete payment?",
                            "Confirm Payment",
                            JOptionPane.YES_NO_OPTION
                    );

            if (choice != JOptionPane.YES_OPTION) {
                return;
            }

            Connection con =
                    Conn1.mycon();

            String checkSql =
                    "SELECT payment_id " +
                    "FROM payment " +
                    "WHERE booking_id = ? " +
                    "AND payment_status = 'Paid'";

            PreparedStatement checkPs =
                    con.prepareStatement(checkSql);

            checkPs.setInt(
                    1,
                    bookingId
            );

            ResultSet checkRs =
                    checkPs.executeQuery();

            if (checkRs.next()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Payment already completed for this booking."
                );

                checkRs.close();
                checkPs.close();
                con.close();

                return;
            }

            checkRs.close();
            checkPs.close();

            String insertSql =
                    "INSERT INTO payment " +
                    "(booking_id, room_amount, service_amount, " +
                    "discount, total_amount, payment_mode, payment_status) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement ps =
                    con.prepareStatement(insertSql);

            ps.setInt(
                    1,
                    bookingId
            );

            ps.setDouble(
                    2,
                    roomAmount
            );

            ps.setDouble(
                    3,
                    serviceAmount
            );

            ps.setDouble(
                    4,
                    discount
            );

            ps.setDouble(
                    5,
                    totalAmount
            );

            ps.setString(
                    6,
                    paymentMode
            );

            ps.setString(
                    7,
                    "Paid"
            );

            int rows =
                    ps.executeUpdate();

            if (rows > 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Payment Completed Successfully!\n\n"
                                + "Booking ID: "
                                + bookingId
                                + "\nTotal Amount: ₹"
                                + String.format(
                                "%.2f",
                                totalAmount
                        )
                                + "\nPayment Mode: "
                                + paymentMode
                );

                clearFields();
            }

            ps.close();
            con.close();

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter valid amount values."
            );

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Payment failed:\n"
                            + ex.getMessage()
            );

            ex.printStackTrace();
        }
    }

    // =========================================================
    // CLEAR ALL
    // =========================================================

    private void clearFields() {

        customerIdField.setText("");
        customerNameField.setText("");
        phoneField.setText("");

        bookingIdField.setText("");
        clearBookingFields();

        serviceAmountField.setText("");
        discountField.setText("");
        totalAmountField.setText("");

        paymentModeBox.setSelectedIndex(0);
    }

    // =========================================================
    // CLEAR BOOKING
    // =========================================================

    private void clearBookingFields() {

        roomIdField.setText("");
        roomNumberField.setText("");
        roomTypeField.setText("");
        priceField.setText("");
        daysField.setText("");
        roomAmountField.setText("");
    }

    // =========================================================
    // CARD
    // =========================================================

    private JPanel createCard() {

        JPanel panel = new JPanel();

        panel.setBackground(Color.WHITE);

        panel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(220, 225, 230)
                        ),
                        new EmptyBorder(
                                20,
                                20,
                                20,
                                20
                        )
                )
        );

        return panel;
    }

    // =========================================================
    // LABEL
    // =========================================================

    private JLabel createLabel(String text) {

        JLabel label =
                new JLabel(text);

        label.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        return label;
    }

    // =========================================================
    // SECTION TITLE
    // =========================================================

    private JLabel createSectionTitle(
            String text) {

        JLabel label =
                new JLabel(text);

        label.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        20
                )
        );

        label.setForeground(
                new Color(32, 55, 90)
        );

        return label;
    }

    // =========================================================
    // TEXT FIELD
    // =========================================================

    private JTextField createField() {

        JTextField field =
                new JTextField();

        field.setPreferredSize(
                new Dimension(
                        220,
                        38
                )
        );

        field.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        return field;
    }

    // =========================================================
    // BUTTON
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

        button.setFocusPainted(false);

        button.setPreferredSize(
                new Dimension(
                        170,
                        42
                )
        );

        return button;
    }

    // =========================================================
    // MAIN
    // =========================================================

    public static void main(
            String[] args) {

        SwingUtilities.invokeLater(
                () -> new BillPayment()
        );
    }
}