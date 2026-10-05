package com.HotelManagement;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.dynamicconn.Conn1;

public class CheckOut extends JFrame {

    // Colors
    private static final Color NAVY = new Color(24, 35, 56);
    private static final Color BLUE = new Color(45, 99, 200);
    private static final Color GREEN = new Color(39, 174, 96);
    private static final Color RED = new Color(231, 76, 60);
    private static final Color BACKGROUND = new Color(245, 247, 250);
    private static final Color WHITE = Color.WHITE;
    private static final Color TEXT = new Color(45, 55, 72);
    private static final Color GRAY = new Color(110, 118, 130);

    // Customer details
    private JTextField customerIdField;
    private JTextField customerNameField;
    private JTextField phoneField;
    private JTextField emailField;

    // Room details
    private JTextField roomIdField;
    private JTextField roomNumberField;
    private JTextField roomTypeField;
    private JTextField priceField;

    // Stay details
    private JTextField checkInDateField;
    private JTextField checkOutDateField;
    private JTextField stayDaysField;

    // Amount
    private JTextField totalAmountField;

    public CheckOut() {

        setTitle("Hotelora - Check-Out");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Full screen
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(1000, 650));

        createUI();

        setVisible(true);
    }

    // =========================================================
    // MAIN UI
    // =========================================================

    private void createUI() {

        JPanel mainPanel =
                new JPanel(new BorderLayout());

        mainPanel.setBackground(BACKGROUND);

        mainPanel.add(
                createHeader(),
                BorderLayout.NORTH
        );

        mainPanel.add(
                createContent(),
                BorderLayout.CENTER
        );

        mainPanel.add(
                createBottomPanel(),
                BorderLayout.SOUTH
        );

        add(mainPanel);
    }

    // =========================================================
    // HEADER
    // =========================================================

    private JPanel createHeader() {

        JPanel header =
                new JPanel(new BorderLayout());

        header.setBackground(NAVY);

        header.setBorder(
                BorderFactory.createEmptyBorder(
                        18, 30, 18, 30
                )
        );

        JLabel title =
                new JLabel("Check-Out");

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        26
                )
        );

        title.setForeground(WHITE);

        JLabel subtitle =
                new JLabel(
                        "Complete the customer's stay and checkout process"
                );

        subtitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        subtitle.setForeground(
                new Color(210, 220, 235)
        );

        JPanel titlePanel =
                new JPanel(
                        new GridLayout(
                                2,
                                1,
                                0,
                                5
                        )
                );

        titlePanel.setOpaque(false);

        titlePanel.add(title);
        titlePanel.add(subtitle);

        header.add(
                titlePanel,
                BorderLayout.WEST
        );

        return header;
    }

    // =========================================================
    // CONTENT
    // =========================================================

    private JPanel createContent() {

        JPanel content =
                new JPanel(
                        new GridLayout(
                                1,
                                2,
                                25,
                                0
                        )
                );

        content.setBackground(BACKGROUND);

        content.setBorder(
                BorderFactory.createEmptyBorder(
                        25,
                        35,
                        25,
                        35
                )
        );

        content.add(
                createCustomerCard()
        );

        content.add(
                createStayCard()
        );

        return content;
    }

    // =========================================================
    // CUSTOMER CARD
    // =========================================================

    private JPanel createCustomerCard() {

        JPanel card =
                new JPanel(new BorderLayout());

        card.setBackground(WHITE);

        card.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(225, 230, 238)
                        ),
                        BorderFactory.createEmptyBorder(
                                25,
                                30,
                                25,
                                30
                        )
                )
        );

        JLabel title =
                new JLabel(
                        "Customer Information"
                );

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        21
                )
        );

        title.setForeground(TEXT);

        card.add(
                title,
                BorderLayout.NORTH
        );

        card.add(
                createCustomerForm(),
                BorderLayout.CENTER
        );

        return card;
    }

    // =========================================================
    // CUSTOMER FORM
    // =========================================================

    private JPanel createCustomerForm() {

        JPanel form =
                new JPanel(new GridBagLayout());

        form.setOpaque(false);

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(
                        10,
                        5,
                        10,
                        5
                );

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        gbc.weightx = 1;

        customerIdField =
                createTextField();

        customerNameField =
                createTextField();

        phoneField =
                createTextField();

        emailField =
                createTextField();

        customerNameField.setEditable(false);
        phoneField.setEditable(false);
        emailField.setEditable(false);

        addRow(
                form,
                gbc,
                0,
                "Customer ID",
                customerIdField
        );

        addRow(
                form,
                gbc,
                1,
                "Customer Name",
                customerNameField
        );

        addRow(
                form,
                gbc,
                2,
                "Phone",
                phoneField
        );

        addRow(
                form,
                gbc,
                3,
                "Email",
                emailField
        );

        return form;
    }

    // =========================================================
    // STAY CARD
    // =========================================================

    private JPanel createStayCard() {

        JPanel card =
                new JPanel(new BorderLayout());

        card.setBackground(WHITE);

        card.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(225, 230, 238)
                        ),
                        BorderFactory.createEmptyBorder(
                                25,
                                30,
                                25,
                                30
                        )
                )
        );

        JLabel title =
                new JLabel(
                        "Stay & Billing Information"
                );

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        21
                )
        );

        title.setForeground(TEXT);

        card.add(
                title,
                BorderLayout.NORTH
        );

        card.add(
                createStayForm(),
                BorderLayout.CENTER
        );

        return card;
    }

    // =========================================================
    // STAY FORM
    // =========================================================

    private JPanel createStayForm() {

        JPanel form =
                new JPanel(new GridBagLayout());

        form.setOpaque(false);

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(
                        8,
                        5,
                        8,
                        5
                );

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        gbc.weightx = 1;

        roomIdField =
                createTextField();

        roomNumberField =
                createTextField();

        roomTypeField =
                createTextField();

        priceField =
                createTextField();

        checkInDateField =
                createTextField();

        checkOutDateField =
                createTextField();

        stayDaysField =
                createTextField();

        totalAmountField =
                createTextField();

        roomIdField.setEditable(false);
        roomNumberField.setEditable(false);
        roomTypeField.setEditable(false);
        priceField.setEditable(false);
        checkInDateField.setEditable(false);
        checkOutDateField.setEditable(false);
        totalAmountField.setEditable(false);

        addRow(
                form,
                gbc,
                0,
                "Room ID",
                roomIdField
        );

        addRow(
                form,
                gbc,
                1,
                "Room Number",
                roomNumberField
        );

        addRow(
                form,
                gbc,
                2,
                "Room Type",
                roomTypeField
        );

        addRow(
                form,
                gbc,
                3,
                "Price / Day",
                priceField
        );

        addRow(
                form,
                gbc,
                4,
                "Check-In Date",
                checkInDateField
        );

        addRow(
                form,
                gbc,
                5,
                "Check-Out Date",
                checkOutDateField
        );

        addRow(
                form,
                gbc,
                6,
                "Stay Days",
                stayDaysField
        );

        addRow(
                form,
                gbc,
                7,
                "Total Amount",
                totalAmountField
        );

        return form;
    }

    // =========================================================
    // BOTTOM PANEL
    // =========================================================

    private JPanel createBottomPanel() {

        JPanel bottom =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                15,
                                15
                        )
                );

        bottom.setBackground(WHITE);

        bottom.setBorder(
                BorderFactory.createMatteBorder(
                        1,
                        0,
                        0,
                        0,
                        new Color(225, 230, 238)
                )
        );

        JButton loadButton =
                new JButton("Load Customer");

        JButton calculateButton =
                new JButton("Calculate Bill");

        JButton clearButton =
                new JButton("Clear");

        JButton checkoutButton =
                new JButton("Confirm Check-Out");

        JButton backButton =
                new JButton("Back");

        styleButton(
                loadButton,
                BLUE
        );

        styleButton(
                calculateButton,
                BLUE
        );

        styleButton(
                clearButton,
                GRAY
        );

        styleButton(
                checkoutButton,
                GREEN
        );

        styleButton(
                backButton,
                NAVY
        );

        loadButton.addActionListener(
                e -> loadCustomer()
        );

        calculateButton.addActionListener(
                e -> calculateBill()
        );

        clearButton.addActionListener(
                e -> clearFields()
        );

        checkoutButton.addActionListener(
                e -> confirmCheckout()
        );

        backButton.addActionListener(
                e -> dispose()
        );

        bottom.add(loadButton);
        bottom.add(calculateButton);
        bottom.add(clearButton);
        bottom.add(checkoutButton);
        bottom.add(backButton);

        return bottom;
    }

    // =========================================================
    // LOAD CUSTOMER
    // =========================================================

    private void loadCustomer() {

        String customerIdText =
                customerIdField.getText().trim();

        if (customerIdText.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter Customer ID.",
                    "Validation",
                    JOptionPane.WARNING_MESSAGE
            );

            customerIdField.requestFocus();

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
                    "Customer ID must be a number.",
                    "Validation",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        String sql =
                "SELECT name, phone, email "
                + "FROM customer "
                + "WHERE customer_id = ?";

        try (
                Connection con = Conn1.mycon();
                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setInt(
                    1,
                    customerId
            );

            try (
                    ResultSet rs =
                            ps.executeQuery()
            ) {

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

                    checkOutDateField.setText(
                            new SimpleDateFormat(
                                    "dd-MM-yyyy"
                            ).format(new Date())
                    );

                    JOptionPane.showMessageDialog(
                            this,
                            "Customer details loaded successfully.",
                            "Customer Found",
                            JOptionPane.INFORMATION_MESSAGE
                    );

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Customer ID not found.",
                            "Not Found",
                            JOptionPane.WARNING_MESSAGE
                    );
                }
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to load customer.\n"
                    + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );

            e.printStackTrace();
        }
    }

    // =========================================================
    // CALCULATE BILL
    // =========================================================

    private void calculateBill() {

        String priceText =
                priceField.getText().trim();

        String daysText =
                stayDaysField.getText().trim();

        if (priceText.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter or load room price.",
                    "Validation",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        if (daysText.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter stay days.",
                    "Validation",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        try {

            double price =
                    Double.parseDouble(
                            priceText
                    );

            int days =
                    Integer.parseInt(
                            daysText
                    );

            if (price < 0 || days <= 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Price and days must be valid.",
                        "Validation",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            double total =
                    price * days;

            totalAmountField.setText(
                    String.format(
                            "%.2f",
                            total
                    )
            );

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Price and Stay Days must contain valid numbers.",
                    "Validation",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }

    // =========================================================
    // CONFIRM CHECKOUT
    // =========================================================

    private void confirmCheckout() {

        String customerId =
                customerIdField.getText().trim();

        String customerName =
                customerNameField.getText().trim();

        String roomNumber =
                roomNumberField.getText().trim();

        String total =
                totalAmountField.getText().trim();

        if (customerId.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter Customer ID.",
                    "Validation",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        if (customerName.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please load customer details first.",
                    "Validation",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        if (roomNumber.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter or load room details.",
                    "Validation",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        if (total.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please calculate the bill first.",
                    "Validation",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        int answer =
                JOptionPane.showConfirmDialog(
                        this,
                        "Confirm checkout for "
                        + customerName
                        + "?\n\n"
                        + "Room: "
                        + roomNumber
                        + "\n"
                        + "Total Amount: ₹"
                        + total,
                        "Confirm Check-Out",
                        JOptionPane.YES_NO_OPTION
                );

        if (answer ==
                JOptionPane.YES_OPTION) {

            /*
             * The exact booking/check-in table structure
             * is not fixed yet.
             *
             * After RoomBooking is finalized, this section
             * should:
             *
             * 1. Update booking/check-out record
             * 2. Change room status to Available
             * 3. Store checkout date
             * 4. Pass the final amount to BillPayment
             */

            JOptionPane.showMessageDialog(
                    this,
                    "Check-Out completed successfully!\n\n"
                    + "Customer: "
                    + customerName
                    + "\nRoom: "
                    + roomNumber
                    + "\nAmount: ₹"
                    + total,
                    "Check-Out Successful",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
    }

    // =========================================================
    // CLEAR
    // =========================================================

    private void clearFields() {

        customerIdField.setText("");
        customerNameField.setText("");
        phoneField.setText("");
        emailField.setText("");

        roomIdField.setText("");
        roomNumberField.setText("");
        roomTypeField.setText("");
        priceField.setText("");

        checkInDateField.setText("");
        checkOutDateField.setText("");

        stayDaysField.setText("");
        totalAmountField.setText("");

        customerIdField.requestFocus();
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
                        14
                )
        );

        field.setPreferredSize(
                new Dimension(
                        250,
                        40
                )
        );

        field.setForeground(TEXT);

        field.setBackground(WHITE);

        field.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        210,
                                        215,
                                        225
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
    // ADD FORM ROW
    // =========================================================

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
                        14
                )
        );

        label.setForeground(TEXT);

        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0.35;

        panel.add(
                label,
                gbc
        );

        gbc.gridx = 1;
        gbc.weightx = 0.65;

        panel.add(
                field,
                gbc
        );
    }

    // =========================================================
    // BUTTON STYLE
    // =========================================================

    private void styleButton(
            JButton button,
            Color color) {

        button.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14
                )
        );

        button.setForeground(WHITE);

        button.setBackground(color);

        button.setFocusPainted(false);

        button.setBorder(
                BorderFactory.createEmptyBorder(
                        12,
                        22,
                        12,
                        22
                )
        );
    }

    // =========================================================
    // MAIN
    // =========================================================

    public static void main(String[] args) {

        new CheckOut();
    }
}