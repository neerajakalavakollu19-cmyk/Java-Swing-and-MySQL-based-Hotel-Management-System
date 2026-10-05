package com.HotelManagement;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
import javax.swing.table.DefaultTableModel;

import com.dynamicconn.Conn1;

public class RoomBooking extends JFrame {

    private JTextField customerIdField;
    private JTextField customerNameField;
    private JTextField phoneField;

    private JTextField personsField;
    private JTextField roomTypeField;
    private JTextField budgetField;
    private JTextField acField;
    private JTextField daysField;

    private JComboBox<Integer> dayCombo;
    private JComboBox<Integer> monthCombo;
    private JComboBox<Integer> yearCombo;

    private JTextField checkOutField;

    private JTable roomTable;
    private DefaultTableModel tableModel;

    private JButton loadCustomerButton;
    private JButton bookButton;
    private JButton clearButton;
    private JButton backButton;

    private int requirementPersons = 0;
    private int requirementDays = 0;
    private double requirementBudget = 0;

    private final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public RoomBooking() {

        setTitle("Hotel Management System - Room Booking");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(15, 15));

        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel topPanel = new JPanel(
                new GridLayout(2, 1, 10, 10));

        JPanel customerPanel = new JPanel(
                new FlowLayout(FlowLayout.LEFT, 12, 10));

        customerPanel.setBorder(
                BorderFactory.createTitledBorder(
                        "Customer Details"));

        JLabel customerIdLabel =
                new JLabel("Customer ID:");

        customerIdLabel.setFont(
                new Font("Arial", Font.BOLD, 15));

        customerIdField =
                new JTextField(8);

        loadCustomerButton =
                new JButton("Load Customer");

        JLabel nameLabel =
                new JLabel("Name:");

        nameLabel.setFont(
                new Font("Arial", Font.BOLD, 15));

        customerNameField =
                new JTextField(15);

        customerNameField.setEditable(false);

        JLabel phoneLabel =
                new JLabel("Phone:");

        phoneLabel.setFont(
                new Font("Arial", Font.BOLD, 15));

        phoneField =
                new JTextField(13);

        phoneField.setEditable(false);

        customerPanel.add(customerIdLabel);
        customerPanel.add(customerIdField);
        customerPanel.add(loadCustomerButton);
        customerPanel.add(nameLabel);
        customerPanel.add(customerNameField);
        customerPanel.add(phoneLabel);
        customerPanel.add(phoneField);

        JPanel requirementPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                15,
                                10));

        requirementPanel.setBorder(
                BorderFactory.createTitledBorder(
                        "Customer Requirement"));

        personsField =
                createDisplayField();

        roomTypeField =
                createDisplayField();

        budgetField =
                createDisplayField();

        acField =
                createDisplayField();

        daysField =
                createDisplayField();

        requirementPanel.add(
                new JLabel("Persons:"));

        requirementPanel.add(personsField);

        requirementPanel.add(
                new JLabel("Room Type:"));

        requirementPanel.add(roomTypeField);

        requirementPanel.add(
                new JLabel("Budget:"));

        requirementPanel.add(budgetField);

        requirementPanel.add(
                new JLabel("AC:"));

        requirementPanel.add(acField);

        requirementPanel.add(
                new JLabel("Days:"));

        requirementPanel.add(daysField);

        topPanel.add(customerPanel);
        topPanel.add(requirementPanel);

        mainPanel.add(
                topPanel,
                BorderLayout.NORTH);

        String[] columns = {
                "Room ID",
                "Room Number",
                "Room Type",
                "Capacity",
                "AC",
                "Price / Day",
                "Total Amount"
        };

        tableModel =
                new DefaultTableModel(columns, 0) {

                    @Override
                    public boolean isCellEditable(
                            int row,
                            int column) {
                        return false;
                    }
                };

        roomTable =
                new JTable(tableModel);

        roomTable.setRowHeight(30);

        roomTable.setFont(
                new Font("Arial", Font.PLAIN, 14));

        roomTable.getTableHeader().setFont(
                new Font("Arial", Font.BOLD, 14));

        roomTable.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION);

        JScrollPane tableScrollPane =
                new JScrollPane(roomTable);

        JPanel roomsPanel =
                new JPanel(new BorderLayout());

        roomsPanel.setBorder(
                BorderFactory.createTitledBorder(
                        "Available Matching Rooms"));

        roomsPanel.add(
                tableScrollPane,
                BorderLayout.CENTER);

        mainPanel.add(
                roomsPanel,
                BorderLayout.CENTER);

        JPanel bottomPanel =
                new JPanel(
                        new BorderLayout(15, 10));

        JPanel datePanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                12,
                                10));

        datePanel.setBorder(
                BorderFactory.createTitledBorder(
                        "Booking Dates"));

        JLabel checkInLabel =
                new JLabel("Check-In:");

        checkInLabel.setFont(
                new Font("Arial", Font.BOLD, 15));

        dayCombo =
                new JComboBox<>();

        monthCombo =
                new JComboBox<>();

        yearCombo =
                new JComboBox<>();

        for (int i = 1; i <= 31; i++) {
            dayCombo.addItem(i);
        }

        for (int i = 1; i <= 12; i++) {
            monthCombo.addItem(i);
        }

        int currentYear =
                LocalDate.now().getYear();

        for (int i = currentYear;
             i <= currentYear + 5;
             i++) {
            yearCombo.addItem(i);
        }

        datePanel.add(checkInLabel);
        datePanel.add(dayCombo);
        datePanel.add(monthCombo);
        datePanel.add(yearCombo);

        JLabel checkOutLabel =
                new JLabel("Check-Out:");

        checkOutLabel.setFont(
                new Font("Arial", Font.BOLD, 15));

        checkOutField =
                new JTextField(12);

        checkOutField.setEditable(false);

        checkOutField.setFont(
                new Font("Arial", Font.BOLD, 14));

        checkOutField.setHorizontalAlignment(
                JTextField.CENTER);

        datePanel.add(checkOutLabel);
        datePanel.add(checkOutField);

        bottomPanel.add(
                datePanel,
                BorderLayout.WEST);

        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                12,
                                10));

        bookButton =
                new JButton("Book Selected Room");

        clearButton =
                new JButton("Clear");

        backButton =
                new JButton("Back");

        buttonPanel.add(bookButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(backButton);

        bottomPanel.add(
                buttonPanel,
                BorderLayout.EAST);

        mainPanel.add(
                bottomPanel,
                BorderLayout.SOUTH);

        add(mainPanel);

        loadCustomerButton.addActionListener(
                e -> loadCustomer());

        dayCombo.addActionListener(
                e -> updateCheckOutDate());

        monthCombo.addActionListener(
                e -> updateCheckOutDate());

        yearCombo.addActionListener(
                e -> updateCheckOutDate());

        bookButton.addActionListener(
                e -> bookRoom());

        clearButton.addActionListener(
                e -> clearForm());

        backButton.addActionListener(
                e -> dispose());

        setMinimumSize(
                new Dimension(1000, 650));

        setVisible(true);

        setTodayDate();
    }

    private JTextField createDisplayField() {

        JTextField field =
                new JTextField(10);

        field.setEditable(false);

        return field;
    }

    private void setTodayDate() {

        LocalDate today =
                LocalDate.now();

        dayCombo.setSelectedItem(
                today.getDayOfMonth());

        monthCombo.setSelectedItem(
                today.getMonthValue());

        yearCombo.setSelectedItem(
                today.getYear());

        updateCheckOutDate();
    }

    private void loadCustomer() {

        String customerIdText =
                customerIdField.getText().trim();

        if (customerIdText.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter Customer ID.",
                    "Validation",
                    JOptionPane.WARNING_MESSAGE);

            return;
        }

        int customerId;

        try {

            customerId =
                    Integer.parseInt(customerIdText);

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Customer ID must be a number.",
                    "Validation",
                    JOptionPane.WARNING_MESSAGE);

            return;
        }

        String customerSql =
                "SELECT name, phone "
                + "FROM customer "
                + "WHERE customer_id = ?";

        String requirementSql =
                "SELECT persons, preferred_room_type, "
                + "budget, ac_required, days "
                + "FROM customer_requirement "
                + "WHERE customer_id = ? "
                + "ORDER BY requirement_id DESC "
                + "LIMIT 1";

        try (Connection con = Conn1.mycon();
             PreparedStatement customerPs =
                     con.prepareStatement(customerSql);
             PreparedStatement requirementPs =
                     con.prepareStatement(requirementSql)) {

            customerPs.setInt(1, customerId);

            try (ResultSet rs =
                         customerPs.executeQuery()) {

                if (!rs.next()) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Customer ID not found.",
                            "Customer",
                            JOptionPane.WARNING_MESSAGE);

                    clearCustomerDetails();
                    clearRequirementDetails();
                    tableModel.setRowCount(0);

                    return;
                }

                customerNameField.setText(
                        rs.getString("name"));

                phoneField.setText(
                        rs.getString("phone"));
            }

            requirementPs.setInt(1, customerId);

            try (ResultSet rs =
                         requirementPs.executeQuery()) {

                if (!rs.next()) {

                    JOptionPane.showMessageDialog(
                            this,
                            "No requirement found for this customer.",
                            "Requirement",
                            JOptionPane.WARNING_MESSAGE);

                    clearRequirementDetails();
                    tableModel.setRowCount(0);

                    return;
                }

                requirementPersons =
                        rs.getInt("persons");

                String roomType =
                        rs.getString(
                                "preferred_room_type");

                requirementBudget =
                        rs.getDouble("budget");

                String acRequired =
                        rs.getString("ac_required");

                requirementDays =
                        rs.getInt("days");

                String requiredAcType =
                        getRequiredAcType(
                                acRequired);

                personsField.setText(
                        String.valueOf(
                                requirementPersons));

                roomTypeField.setText(
                        roomType);

                budgetField.setText(
                        String.valueOf(
                                requirementBudget));

                acField.setText(
                        requiredAcType);

                daysField.setText(
                        String.valueOf(
                                requirementDays));

                updateCheckOutDate();

                loadMatchingRooms(customerId);
            }

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error loading customer:\n"
                    + ex.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private String getRequiredAcType(
            String value) {

        if (value == null) {
            return "Non-AC";
        }

        String text =
                value.trim().toLowerCase();

        if (text.equals("ac")
                || text.equals("yes")
                || text.equals("true")
                || text.equals("1")) {

            return "AC";
        }

        return "Non-AC";
    }

    private void loadMatchingRooms(
            int customerId) {

        tableModel.setRowCount(0);

        String sql =
                "SELECT r.room_id, "
                + "r.room_number, "
                + "r.room_type, "
                + "r.capacity, "
                + "r.ac_type, "
                + "r.price_per_day, "
                + "cr.days "
                + "FROM room r "
                + "JOIN customer_requirement cr "
                + "ON cr.customer_id = ? "
                + "WHERE cr.requirement_id = "
                + "(SELECT MAX(cr2.requirement_id) "
                + "FROM customer_requirement cr2 "
                + "WHERE cr2.customer_id = ?) "
                + "AND TRIM(LOWER(r.status)) = 'available' "
                + "AND TRIM(LOWER(r.room_type)) = "
                + "TRIM(LOWER(cr.preferred_room_type)) "
                + "AND TRIM(LOWER(r.ac_type)) = "
                + "TRIM(LOWER(cr.ac_required)) "
                + "AND r.capacity >= cr.persons "
                + "AND r.price_per_day <= cr.budget "
                + "ORDER BY r.room_id";

        try (Connection con = Conn1.mycon();
             PreparedStatement ps =
                     con.prepareStatement(sql)) {

            ps.setInt(1, customerId);
            ps.setInt(2, customerId);

            try (ResultSet rs =
                         ps.executeQuery()) {

                while (rs.next()) {

                    int roomId =
                            rs.getInt("room_id");

                    int roomNumber =
                            rs.getInt("room_number");

                    String roomType =
                            rs.getString("room_type");

                    int capacity =
                            rs.getInt("capacity");

                    String acType =
                            rs.getString("ac_type");

                    double pricePerDay =
                            rs.getDouble("price_per_day");

                    int days =
                            rs.getInt("days");

                    double totalAmount =
                            pricePerDay * days;

                    tableModel.addRow(
                            new Object[]{
                                    roomId,
                                    roomNumber,
                                    roomType,
                                    capacity,
                                    acType,
                                    pricePerDay,
                                    totalAmount
                            });
                }
            }

            if (tableModel.getRowCount() == 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "No matching available rooms found.",
                        "Room Availability",
                        JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error loading rooms:\n"
                    + ex.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateCheckOutDate() {

        if (requirementDays <= 0) {

            checkOutField.setText("");

            return;
        }

        try {

            int day =
                    (Integer) dayCombo.getSelectedItem();

            int month =
                    (Integer) monthCombo.getSelectedItem();

            int year =
                    (Integer) yearCombo.getSelectedItem();

            LocalDate checkIn =
                    LocalDate.of(
                            year,
                            month,
                            day);

            LocalDate checkOut =
                    checkIn.plusDays(
                            requirementDays);

            checkOutField.setText(
                    checkOut.format(formatter));

        } catch (Exception e) {

            checkOutField.setText("");
        }
    }

    private LocalDate getCheckInDate() {

        int day =
                (Integer) dayCombo.getSelectedItem();

        int month =
                (Integer) monthCombo.getSelectedItem();

        int year =
                (Integer) yearCombo.getSelectedItem();

        return LocalDate.of(
                year,
                month,
                day);
    }

    private void bookRoom() {

        if (customerIdField.getText()
                .trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter Customer ID and load customer.",
                    "Validation",
                    JOptionPane.WARNING_MESSAGE);

            return;
        }

        if (requirementDays <= 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid number of days.",
                    "Validation",
                    JOptionPane.WARNING_MESSAGE);

            return;
        }

        int selectedRow =
                roomTable.getSelectedRow();

        if (selectedRow == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a room.",
                    "Validation",
                    JOptionPane.WARNING_MESSAGE);

            return;
        }

        int customerId;

        try {

            customerId =
                    Integer.parseInt(
                            customerIdField
                                    .getText()
                                    .trim());

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid Customer ID.",
                    "Validation",
                    JOptionPane.WARNING_MESSAGE);

            return;
        }

        LocalDate checkIn;

        try {

            checkIn =
                    getCheckInDate();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid Check-In date.",
                    "Validation",
                    JOptionPane.WARNING_MESSAGE);

            return;
        }

        if (checkIn.isBefore(
                LocalDate.now())) {

            JOptionPane.showMessageDialog(
                    this,
                    "Check-In date cannot be before today.",
                    "Validation",
                    JOptionPane.WARNING_MESSAGE);

            return;
        }

        LocalDate checkOut =
                checkIn.plusDays(
                        requirementDays);

        int roomId =
                Integer.parseInt(
                        tableModel.getValueAt(
                                selectedRow,
                                0).toString());

        Connection con = null;

        try {

            con = Conn1.mycon();

            con.setAutoCommit(false);

            String roomSql =
                    "SELECT r.price_per_day "
                    + "FROM room r "
                    + "JOIN customer_requirement cr "
                    + "ON cr.customer_id = ? "
                    + "WHERE cr.requirement_id = "
                    + "(SELECT MAX(cr2.requirement_id) "
                    + "FROM customer_requirement cr2 "
                    + "WHERE cr2.customer_id = ?) "
                    + "AND r.room_id = ? "
                    + "AND TRIM(LOWER(r.status)) = 'available' "
                    + "AND TRIM(LOWER(r.room_type)) = "
                    + "TRIM(LOWER(cr.preferred_room_type)) "
                    + "AND TRIM(LOWER(r.ac_type)) = "
                    + "TRIM(LOWER(cr.ac_required)) "
                    + "AND r.capacity >= cr.persons "
                    + "AND r.price_per_day <= cr.budget "
                    + "FOR UPDATE";

            double pricePerDay;

            try (PreparedStatement ps =
                         con.prepareStatement(roomSql)) {

                ps.setInt(1, customerId);
                ps.setInt(2, customerId);
                ps.setInt(3, roomId);

                try (ResultSet rs =
                             ps.executeQuery()) {

                    if (!rs.next()) {

                        con.rollback();

                        JOptionPane.showMessageDialog(
                                this,
                                "This room is no longer available.",
                                "Room Unavailable",
                                JOptionPane.WARNING_MESSAGE);

                        loadMatchingRooms(customerId);

                        return;
                    }

                    pricePerDay =
                            rs.getDouble(
                                    "price_per_day");
                }
            }

            double totalAmount =
                    pricePerDay * requirementDays;

            String bookingSql =
                    "INSERT INTO booking "
                    + "(customer_id, room_id, check_in, "
                    + "check_out, status) "
                    + "VALUES (?, ?, ?, ?, ?)";

            try (PreparedStatement ps =
                         con.prepareStatement(
                                 bookingSql)) {

                ps.setInt(
                        1,
                        customerId);

                ps.setInt(
                        2,
                        roomId);

                ps.setDate(
                        3,
                        Date.valueOf(checkIn));

                ps.setDate(
                        4,
                        Date.valueOf(checkOut));

                ps.setString(
                        5,
                        "Booked");

                ps.executeUpdate();
            }

            String updateRoomSql =
                    "UPDATE room "
                    + "SET status = 'Booked' "
                    + "WHERE room_id = ? "
                    + "AND TRIM(LOWER(status)) = 'available'";

            try (PreparedStatement ps =
                         con.prepareStatement(
                                 updateRoomSql)) {

                ps.setInt(
                        1,
                        roomId);

                int updated =
                        ps.executeUpdate();

                if (updated != 1) {

                    con.rollback();

                    JOptionPane.showMessageDialog(
                            this,
                            "Room could not be booked.",
                            "Booking Error",
                            JOptionPane.ERROR_MESSAGE);

                    loadMatchingRooms(customerId);

                    return;
                }
            }

            con.commit();

            JOptionPane.showMessageDialog(
                    this,
                    "Room booked successfully!\n\n"
                    + "Customer ID : "
                    + customerId + "\n"
                    + "Room ID     : "
                    + roomId + "\n"
                    + "Check-In    : "
                    + checkIn.format(formatter) + "\n"
                    + "Check-Out   : "
                    + checkOut.format(formatter) + "\n"
                    + "Days        : "
                    + requirementDays + "\n"
                    + "Price / Day : ₹"
                    + pricePerDay + "\n"
                    + "Total Amount: ₹"
                    + totalAmount,
                    "Booking Successful",
                    JOptionPane.INFORMATION_MESSAGE);

            loadMatchingRooms(customerId);

        } catch (Exception ex) {

            if (con != null) {

                try {
                    con.rollback();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            JOptionPane.showMessageDialog(
                    this,
                    "Booking failed:\n"
                    + ex.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);

        } finally {

            if (con != null) {

                try {
                    con.setAutoCommit(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void clearForm() {

        customerIdField.setText("");

        clearCustomerDetails();

        clearRequirementDetails();

        tableModel.setRowCount(0);

        requirementPersons = 0;
        requirementDays = 0;
        requirementBudget = 0;

        checkOutField.setText("");

        setTodayDate();
    }

    private void clearCustomerDetails() {

        customerNameField.setText("");
        phoneField.setText("");
    }

    private void clearRequirementDetails() {

        personsField.setText("");
        roomTypeField.setText("");
        budgetField.setText("");
        acField.setText("");
        daysField.setText("");
    }

    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(
                () -> new RoomBooking());
    }
}