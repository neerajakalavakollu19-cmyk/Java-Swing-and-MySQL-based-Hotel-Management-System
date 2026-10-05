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
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.dynamicconn.Conn1;

public class BookingReport extends JFrame {

    private JTable bookingTable;
    private DefaultTableModel model;
    private JTextField searchField;

    private final Color NAVY = new Color(24, 35, 56);
    private final Color BLUE = new Color(45, 99, 200);
    private final Color LIGHT_BLUE = new Color(235, 242, 255);
    private final Color BACKGROUND = new Color(245, 247, 250);
    private final Color WHITE = Color.WHITE;
    private final Color TEXT = new Color(45, 55, 72);
    private final Color GRAY = new Color(110, 118, 130);

    public BookingReport() {

        setTitle("Hotel Management System - Booking Report");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(1000, 650));

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(BACKGROUND);

        // ================= HEADER =================

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(NAVY);
        headerPanel.setBorder(
                BorderFactory.createEmptyBorder(18, 30, 18, 30));

        JLabel titleLabel = new JLabel("BOOKING REPORT");
        titleLabel.setForeground(WHITE);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 26));

        JLabel subTitle = new JLabel(
                "View and manage all hotel room bookings");
        subTitle.setForeground(new Color(210, 220, 235));
        subTitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JPanel titlePanel = new JPanel();
        titlePanel.setOpaque(false);
        titlePanel.setLayout(new javax.swing.BoxLayout(
                titlePanel, javax.swing.BoxLayout.Y_AXIS));

        titlePanel.add(titleLabel);
        titlePanel.add(subTitle);

        headerPanel.add(titlePanel, BorderLayout.WEST);

        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // ================= CONTENT =================

        JPanel contentPanel = new JPanel(new BorderLayout(20, 20));
        contentPanel.setBackground(BACKGROUND);
        contentPanel.setBorder(
                BorderFactory.createEmptyBorder(25, 30, 25, 30));

        // ================= SEARCH PANEL =================

        JPanel searchPanel = new JPanel(new FlowLayout(
                FlowLayout.LEFT, 12, 12));

        searchPanel.setBackground(WHITE);
        searchPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(225, 228, 235)),
                        BorderFactory.createEmptyBorder(8, 12, 8, 12)));

        JLabel searchLabel = new JLabel("Customer ID:");
        searchLabel.setFont(
                new Font("Segoe UI", Font.BOLD, 14));
        searchLabel.setForeground(TEXT);

        searchField = new JTextField(15);
        searchField.setFont(
                new Font("Segoe UI", Font.PLAIN, 14));

        JButton searchButton = createButton(
                "Search", BLUE);

        JButton refreshButton = createButton(
                "Refresh", new Color(39, 174, 96));

        JButton clearButton = createButton(
                "Clear", GRAY);

        JButton backButton = createButton(
                "Back", NAVY);

        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(refreshButton);
        searchPanel.add(clearButton);
        searchPanel.add(backButton);

        contentPanel.add(searchPanel, BorderLayout.NORTH);

        // ================= TABLE =================

        String[] columns = {
                "Booking ID",
                "Customer ID",
                "Customer Name",
                "Room ID",
                "Room Number",
                "Room Type",
                "Price/Day",
                "Check-In",
                "Check-Out",
                "Status"
        };

        model = new DefaultTableModel(columns, 0) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        bookingTable = new JTable(model);

        bookingTable.setFont(
                new Font("Segoe UI", Font.PLAIN, 14));

        bookingTable.setRowHeight(32);

        bookingTable.getTableHeader().setFont(
                new Font("Segoe UI", Font.BOLD, 14));

        bookingTable.getTableHeader().setBackground(NAVY);
        bookingTable.getTableHeader().setForeground(WHITE);

        bookingTable.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION);

        bookingTable.setGridColor(
                new Color(225, 228, 235));

        bookingTable.setShowVerticalLines(false);

        JScrollPane scrollPane = new JScrollPane(bookingTable);
        scrollPane.setBorder(
                BorderFactory.createLineBorder(
                        new Color(220, 224, 230)));

        contentPanel.add(scrollPane, BorderLayout.CENTER);

        // ================= BOTTOM =================

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(BACKGROUND);

        JLabel countLabel = new JLabel("Total Bookings: 0");
        countLabel.setFont(
                new Font("Segoe UI", Font.BOLD, 15));
        countLabel.setForeground(TEXT);

        bottomPanel.add(countLabel, BorderLayout.WEST);

        contentPanel.add(bottomPanel, BorderLayout.SOUTH);

        mainPanel.add(contentPanel, BorderLayout.CENTER);

        add(mainPanel);

        // ================= BUTTON ACTIONS =================

        refreshButton.addActionListener(e -> {
            loadBookings();
            updateCount(countLabel);
        });

        searchButton.addActionListener(e -> {
            searchBookings();
            updateCount(countLabel);
        });

        clearButton.addActionListener(e -> {
            searchField.setText("");
            loadBookings();
            updateCount(countLabel);
        });

        backButton.addActionListener(e -> {
            dispose();
        });

        // Load data when window opens
        loadBookings();
        updateCount(countLabel);

        setVisible(true);
    }

    // =========================================================
    // LOAD ALL BOOKINGS
    // =========================================================

    private void loadBookings() {

        model.setRowCount(0);

        String sql =
                "SELECT b.booking_id, "
                + "b.customer_id, "
                + "c.name AS customer_name, "
                + "b.room_id, "
                + "r.room_number, "
                + "r.room_type, "
                + "r.price_per_day, "
                + "b.check_in, "
                + "b.check_out, "
                + "b.status "
                + "FROM booking b "
                + "LEFT JOIN customer c "
                + "ON b.customer_id = c.customer_id "
                + "LEFT JOIN room r "
                + "ON b.room_id = r.room_id "
                + "ORDER BY b.booking_id DESC";

        try (
                Connection con = Conn1.mycon();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {

                Object[] row = {

                        rs.getInt("booking_id"),

                        rs.getInt("customer_id"),

                        rs.getString("customer_name"),

                        rs.getInt("room_id"),

                        rs.getString("room_number"),

                        rs.getString("room_type"),

                        rs.getDouble("price_per_day"),

                        rs.getDate("check_in"),

                        rs.getDate("check_out"),

                        rs.getString("status")
                };

                model.addRow(row);
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error loading booking report:\n"
                    + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // =========================================================
    // SEARCH BOOKINGS BY CUSTOMER ID
    // =========================================================

    private void searchBookings() {

        String customerId = searchField.getText().trim();

        if (customerId.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter Customer ID.",
                    "Search",
                    JOptionPane.WARNING_MESSAGE);

            return;
        }

        try {

            int id = Integer.parseInt(customerId);

            model.setRowCount(0);

            String sql =
                    "SELECT b.booking_id, "
                    + "b.customer_id, "
                    + "c.name AS customer_name, "
                    + "b.room_id, "
                    + "r.room_number, "
                    + "r.room_type, "
                    + "r.price_per_day, "
                    + "b.check_in, "
                    + "b.check_out, "
                    + "b.status "
                    + "FROM booking b "
                    + "LEFT JOIN customer c "
                    + "ON b.customer_id = c.customer_id "
                    + "LEFT JOIN room r "
                    + "ON b.room_id = r.room_id "
                    + "WHERE b.customer_id = ? "
                    + "ORDER BY b.booking_id DESC";

            try (
                    Connection con = Conn1.mycon();
                    PreparedStatement ps =
                            con.prepareStatement(sql)
            ) {

                ps.setInt(1, id);

                try (ResultSet rs = ps.executeQuery()) {

                    while (rs.next()) {

                        Object[] row = {

                                rs.getInt("booking_id"),

                                rs.getInt("customer_id"),

                                rs.getString("customer_name"),

                                rs.getInt("room_id"),

                                rs.getString("room_number"),

                                rs.getString("room_type"),

                                rs.getDouble("price_per_day"),

                                rs.getDate("check_in"),

                                rs.getDate("check_out"),

                                rs.getString("status")
                        };

                        model.addRow(row);
                    }
                }
            }

            if (model.getRowCount() == 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "No bookings found for Customer ID: "
                        + id,
                        "Search Result",
                        JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Customer ID must contain numbers only.",
                    "Invalid Customer ID",
                    JOptionPane.WARNING_MESSAGE);

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error searching bookings:\n"
                    + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // =========================================================
    // UPDATE COUNT
    // =========================================================

    private void updateCount(JLabel countLabel) {

        countLabel.setText(
                "Total Bookings: "
                + model.getRowCount());
    }

    // =========================================================
    // CREATE BUTTON
    // =========================================================

    private JButton createButton(
            String text,
            Color background) {

        JButton button = new JButton(text);

        button.setFont(
                new Font("Segoe UI", Font.BOLD, 13));

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

    public static void main(String[] args) {

        new BookingReport();
    }
}