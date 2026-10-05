package com.HotelManagement;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.time.LocalDate;

import javax.swing.BorderFactory;
import javax.swing.JButton;
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

public class RoomBooking extends JFrame {

    private JTextField customerIdField;
    private JTextField checkInField;
    private JTextField checkOutField;

    private JLabel customerNameLabel;
    private JLabel customerPhoneLabel;

    private JTable roomTable;
    private DefaultTableModel model;

    public RoomBooking() {

        setTitle("Hotel Management System - Room Booking");

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setExtendedState(
                JFrame.MAXIMIZED_BOTH
        );

        createUI();

        loadAvailableRooms();

        setVisible(true);
    }

    private void createUI() {

        JPanel mainPanel =
                new JPanel(new BorderLayout(15, 15));

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        25, 30, 25, 30
                )
        );

        // =========================
        // TITLE
        // =========================

        JLabel titleLabel =
                new JLabel(
                        "ROOM BOOKING",
                        SwingConstants.CENTER
                );

        titleLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        28
                )
        );

        mainPanel.add(
                titleLabel,
                BorderLayout.NORTH
        );

        // =========================
        // CUSTOMER PANEL
        // =========================

        JPanel customerPanel =
                new JPanel(
                        new GridLayout(
                                2,
                                4,
                                10,
                                10
                        )
                );

        customerPanel.setBorder(
                BorderFactory.createTitledBorder(
                        "Customer & Booking Details"
                )
        );

        JLabel customerIdLabel =
                new JLabel("Customer ID");

        customerIdLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14
                )
        );

        customerIdField =
                new JTextField();

        customerIdField.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        JButton loadCustomerButton =
                new JButton("Load Customer");

        loadCustomerButton.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        customerNameLabel =
                new JLabel("Customer Name: -");

        customerNameLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14
                )
        );

        customerPhoneLabel =
                new JLabel("Phone: -");

        customerPhoneLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14
                )
        );

        JLabel checkInLabel =
                new JLabel("Check-In");

        checkInLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14
                )
        );

        checkInField =
                new JTextField();

        checkInField.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        checkInField.setText(
                LocalDate.now().toString()
        );

        JLabel checkOutLabel =
                new JLabel("Check-Out");

        checkOutLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14
                )
        );

        checkOutField =
                new JTextField();

        checkOutField.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        customerPanel.add(customerIdLabel);
        customerPanel.add(customerIdField);
        customerPanel.add(loadCustomerButton);
        customerPanel.add(customerNameLabel);

        customerPanel.add(customerPhoneLabel);
        customerPanel.add(checkInLabel);
        customerPanel.add(checkInField);
        customerPanel.add(checkOutLabel);

        JPanel topPanel =
                new JPanel(new BorderLayout(10, 10));

        topPanel.add(
                customerPanel,
                BorderLayout.CENTER
        );

        JPanel datePanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                10,
                                5
                        )
                );

        datePanel.add(
                new JLabel("Check-Out Date:")
        );

        datePanel.add(checkOutField);

        topPanel.add(
                datePanel,
                BorderLayout.SOUTH
        );

        // =========================
        // ROOM TABLE
        // =========================

        String[] columns = {
                "Room ID",
                "Room Number",
                "Room Type",
                "Price/Day",
                "Status"
        };

        model =
                new DefaultTableModel(
                        columns,
                        0
                ) {

                    @Override
                    public boolean isCellEditable(
                            int row,
                            int column) {

                        return false;
                    }
                };

        roomTable =
                new JTable(model);

        roomTable.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        roomTable.setRowHeight(32);

        roomTable.getTableHeader()
                .setFont(
                        new Font(
                                "Segoe UI",
                                Font.BOLD,
                                14
                        )
                );

        roomTable.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        JScrollPane scrollPane =
                new JScrollPane(roomTable);

        scrollPane.setBorder(
                BorderFactory.createTitledBorder(
                        "Available Rooms"
                )
        );

        // =========================
        // BUTTON PANEL
        // =========================

        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                15,
                                10
                        )
                );

        JButton refreshButton =
                new JButton("Refresh Rooms");

        JButton bookButton =
                new JButton("Book Selected Room");

        JButton clearButton =
                new JButton("Clear");

        JButton backButton =
                new JButton("Back");

        buttonPanel.add(refreshButton);
        buttonPanel.add(bookButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(backButton);

        JPanel centerPanel =
                new JPanel(
                        new BorderLayout(
                                10,
                                10
                        )
                );

        centerPanel.add(
                topPanel,
                BorderLayout.NORTH
        );

        centerPanel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        centerPanel.add(
                buttonPanel,
                BorderLayout.SOUTH
        );

        mainPanel.add(
                centerPanel,
                BorderLayout.CENTER
        );

        add(mainPanel);

        // =========================
        // ACTIONS
        // =========================

        loadCustomerButton.addActionListener(
                e -> loadCustomer()
        );

        refreshButton.addActionListener(
                e -> loadAvailableRooms()
        );

        bookButton.addActionListener(
                e -> bookRoom()
        );

        clearButton.addActionListener(
                e -> clearFields()
        );

        backButton.addActionListener(
                e -> dispose()
        );
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
                    "Please enter Customer ID."
            );

            return;
        }

        try {

            int customerId =
                    Integer.parseInt(
                            customerIdText
                    );

            String sql =
                    "SELECT name, phone "
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

                        customerNameLabel.setText(
                                "Customer Name: "
                                + rs.getString("name")
                        );

                        customerPhoneLabel.setText(
                                "Phone: "
                                + rs.getString("phone")
                        );

                    } else {

                        customerNameLabel.setText(
                                "Customer Name: -"
                        );

                        customerPhoneLabel.setText(
                                "Phone: -"
                        );

                        JOptionPane.showMessageDialog(
                                this,
                                "Customer ID not found."
                        );
                    }
                }

            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Customer ID must contain numbers only."
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error loading customer:\n"
                    + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================================================
    // LOAD AVAILABLE ROOMS
    // =========================================================

    private void loadAvailableRooms() {

        model.setRowCount(0);

        String sql =
                "SELECT room_id, "
                + "room_number, "
                + "room_type, "
                + "price_per_day, "
                + "status "
                + "FROM room "
                + "WHERE status = 'Available'";

        try (
                Connection con = Conn1.mycon();

                PreparedStatement ps =
                        con.prepareStatement(sql);

                ResultSet rs =
                        ps.executeQuery()
        ) {

            while (rs.next()) {

                Object[] row = {

                        rs.getInt("room_id"),

                        rs.getString(
                                "room_number"
                        ),

                        rs.getString(
                                "room_type"
                        ),

                        rs.getDouble(
                                "price_per_day"
                        ),

                        rs.getString(
                                "status"
                        )
                };

                model.addRow(row);
            }

            if (model.getRowCount() == 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "No available rooms found."
                );
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error loading rooms:\n"
                    + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================================================
    // BOOK ROOM
    // =========================================================

    private void bookRoom() {

        String customerIdText =
                customerIdField.getText().trim();

        String checkInText =
                checkInField.getText().trim();

        String checkOutText =
                checkOutField.getText().trim();

        if (customerIdText.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter Customer ID."
            );

            return;
        }

        if (customerNameLabel.getText()
                .equals("Customer Name: -")) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please load the customer first."
            );

            return;
        }

        if (checkInText.isEmpty()
                || checkOutText.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter Check-In and Check-Out dates."
            );

            return;
        }

        int selectedRow =
                roomTable.getSelectedRow();

        if (selectedRow == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a room."
            );

            return;
        }

        try {

            int customerId =
                    Integer.parseInt(
                            customerIdText
                    );

            int roomId =
                    Integer.parseInt(
                            roomTable.getValueAt(
                                    selectedRow,
                                    0
                            ).toString()
                    );

            Date checkIn =
                    Date.valueOf(
                            checkInText
                    );

            Date checkOut =
                    Date.valueOf(
                            checkOutText
                    );

            if (!checkOut.after(checkIn)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Check-Out date must be after Check-In date."
                );

                return;
            }

            Connection con =
                    Conn1.mycon();

            try {

                // =========================
                // CHECK CUSTOMER
                // =========================

                String customerSql =
                        "SELECT customer_id "
                        + "FROM customer "
                        + "WHERE customer_id = ?";

                try (
                        PreparedStatement ps =
                                con.prepareStatement(
                                        customerSql
                                )
                ) {

                    ps.setInt(
                            1,
                            customerId
                    );

                    try (
                            ResultSet rs =
                                    ps.executeQuery()
                    ) {

                        if (!rs.next()) {

                            JOptionPane.showMessageDialog(
                                    this,
                                    "Customer does not exist."
                            );

                            return;
                        }
                    }
                }

                // =========================
                // CHECK ROOM
                // =========================

                String roomSql =
                        "SELECT status "
                        + "FROM room "
                        + "WHERE room_id = ?";

                try (
                        PreparedStatement ps =
                                con.prepareStatement(
                                        roomSql
                                )
                ) {

                    ps.setInt(
                            1,
                            roomId
                    );

                    ps.setInt(
                            1,
                            roomId
                    );

                    try (
                            ResultSet rs =
                                    ps.executeQuery()
                    ) {

                        if (!rs.next()) {

                            JOptionPane.showMessageDialog(
                                    this,
                                    "Room does not exist."
                            );

                            return;
                        }

                        String status =
                                rs.getString(
                                        "status"
                                );

                        if (!status.equalsIgnoreCase(
                                "Available")) {

                            JOptionPane.showMessageDialog(
                                    this,
                                    "This room is no longer available."
                            );

                            loadAvailableRooms();

                            return;
                        }
                    }
                }

                // =========================
                // INSERT BOOKING
                // =========================

                String bookingSql =
                        "INSERT INTO booking "
                        + "(customer_id, room_id, check_in, check_out, status) "
                        + "VALUES (?, ?, ?, ?, ?)";

                try (
                        PreparedStatement ps =
                                con.prepareStatement(
                                        bookingSql
                                )
                ) {

                    ps.setInt(
                            1,
                            customerId
                    );

                    ps.setInt(
                            2,
                            roomId
                    );

                    ps.setDate(
                            3,
                            checkIn
                    );

                    ps.setDate(
                            4,
                            checkOut
                    );

                    ps.setString(
                            5,
                            "Booked"
                    );

                    ps.executeUpdate();
                }

                // =========================
                // UPDATE ROOM
                // =========================

                String updateRoomSql =
                        "UPDATE room "
                        + "SET status = 'Booked' "
                        + "WHERE room_id = ?";

                try (
                        PreparedStatement ps =
                                con.prepareStatement(
                                        updateRoomSql
                                )
                ) {

                    ps.setInt(
                            1,
                            roomId
                    );

                    ps.executeUpdate();
                }

                JOptionPane.showMessageDialog(
                        this,
                        "Room booked successfully!"
                );

                loadAvailableRooms();

                clearFields();

            } finally {

                con.close();
            }

        } catch (IllegalArgumentException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Date format must be:\n"
                    + "YYYY-MM-DD\n\n"
                    + "Example:\n"
                    + "2026-10-05"
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Booking failed:\n"
                    + e.getMessage(),
                    "Booking Error",
                    JOptionPane.ERROR_MESSAGE
            );

            e.printStackTrace();
        }
    }

    // =========================================================
    // CLEAR
    // =========================================================

    private void clearFields() {

        customerIdField.setText("");

        checkInField.setText(
                LocalDate.now().toString()
        );

        checkOutField.setText("");

        customerNameLabel.setText(
                "Customer Name: -"
        );

        customerPhoneLabel.setText(
                "Phone: -"
        );

        roomTable.clearSelection();
    }
}