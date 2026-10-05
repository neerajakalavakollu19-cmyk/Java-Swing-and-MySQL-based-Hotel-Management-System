package com.HotelManagement;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class DashboardFrame extends JFrame {

    private String employeeName;
    private String employeeRole;
    private String username;

    public DashboardFrame(
            String employeeName,
            String employeeRole,
            String username) {

        this.employeeName = employeeName;
        this.employeeRole = employeeRole;
        this.username = username;

        setTitle(
                "Hotel Management System - Dashboard"
        );

        setExtendedState(
                JFrame.MAXIMIZED_BOTH
        );

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setLayout(
                new BorderLayout()
        );

        // =================================================
        // HEADER
        // =================================================

        JPanel headerPanel =
                new JPanel(
                        new BorderLayout()
                );

        headerPanel.setBackground(
                new Color(35, 47, 62)
        );

        headerPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        30,
                        20,
                        30
                )
        );

        JLabel titleLabel =
                new JLabel(
                        "HOTEL MANAGEMENT SYSTEM"
                );

        titleLabel.setForeground(
                Color.WHITE
        );

        titleLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        28
                )
        );

        JLabel welcomeLabel =
                new JLabel(
                        "Welcome, "
                        + employeeName
                        + "   |   Role: "
                        + employeeRole
                );

        welcomeLabel.setForeground(
                Color.WHITE
        );

        welcomeLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        16
                )
        );

        welcomeLabel.setHorizontalAlignment(
                SwingConstants.RIGHT
        );

        headerPanel.add(
                titleLabel,
                BorderLayout.WEST
        );

        headerPanel.add(
                welcomeLabel,
                BorderLayout.EAST
        );

        add(
                headerPanel,
                BorderLayout.NORTH
        );

        // =================================================
        // CENTER
        // =================================================

        JPanel centerPanel =
                new JPanel(
                        new BorderLayout()
                );

        centerPanel.setBackground(
                new Color(245, 247, 250)
        );

        centerPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        30,
                        50,
                        30,
                        50
                )
        );

        JLabel dashboardTitle =
                new JLabel(
                        "Dashboard",
                        SwingConstants.CENTER
                );

        dashboardTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        30
                )
        );

        dashboardTitle.setForeground(
                new Color(35, 47, 62)
        );

        centerPanel.add(
                dashboardTitle,
                BorderLayout.NORTH
        );

        // =================================================
        // BUTTON PANEL
        // =================================================

        JPanel buttonPanel =
                new JPanel(
                        new GridLayout(
                                3,
                                4,
                                25,
                                25
                        )
                );

        buttonPanel.setBackground(
                new Color(245, 247, 250)
        );

        buttonPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        35,
                        20,
                        35,
                        20
                )
        );

        // =================================================
        // BUTTONS
        // =================================================

        JButton customerDetailsButton =
                createButton(
                        "Customer Details"
                );

        JButton customerRequirementsButton =
                createButton(
                        "Customer Requirements"
                );

        JButton roomBookingButton =
                createButton(
                        "Room Booking"
                );

        JButton checkInButton =
                createButton(
                        "Check-In"
                );

        JButton checkOutButton =
                createButton(
                        "Check-Out"
                );

        JButton billPaymentButton =
                createButton(
                        "Bill & Payment"
                );

        JButton customerReportButton =
                createButton(
                        "Customer Report"
                );

        JButton bookingReportButton =
                createButton(
                        "Booking Report"
                );

        JButton excelReportButton =
                createButton(
                        "Excel Report"
                );

        JButton settingsButton =
                createButton(
                        "Settings"
                );

        JButton logoutButton =
                createButton(
                        "Logout"
                );

        JPanel emptyPanel =
                new JPanel();

        emptyPanel.setOpaque(false);

        // =================================================
        // ADD BUTTONS
        // =================================================

        buttonPanel.add(
                customerDetailsButton
        );

        buttonPanel.add(
                customerRequirementsButton
        );

        buttonPanel.add(
                roomBookingButton
        );

        buttonPanel.add(
                checkInButton
        );

        buttonPanel.add(
                checkOutButton
        );

        buttonPanel.add(
                billPaymentButton
        );

        buttonPanel.add(
                customerReportButton
        );

        buttonPanel.add(
                bookingReportButton
        );

        buttonPanel.add(
                excelReportButton
        );

        buttonPanel.add(
                settingsButton
        );

        buttonPanel.add(
                logoutButton
        );

        buttonPanel.add(
                emptyPanel
        );

        centerPanel.add(
                buttonPanel,
                BorderLayout.CENTER
        );

        add(
                centerPanel,
                BorderLayout.CENTER
        );

        // =================================================
        // FOOTER
        // =================================================

        JPanel footerPanel =
                new JPanel(
                        new BorderLayout()
                );

        footerPanel.setBackground(
                new Color(35, 47, 62)
        );

        footerPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        12,
                        25,
                        12,
                        25
                )
        );

        JLabel footerLabel =
                new JLabel(
                        "Hotel Management System"
                );

        footerLabel.setForeground(
                Color.WHITE
        );

        footerLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        JLabel userLabel =
                new JLabel(
                        "Logged in as: "
                        + username
                );

        userLabel.setForeground(
                Color.WHITE
        );

        userLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        footerPanel.add(
                footerLabel,
                BorderLayout.WEST
        );

        footerPanel.add(
                userLabel,
                BorderLayout.EAST
        );

        add(
                footerPanel,
                BorderLayout.SOUTH
        );

        // =================================================
        // BUTTON ACTIONS
        // =================================================

        customerDetailsButton.addActionListener(
                e -> new CustomerDetails()
        );

        customerRequirementsButton.addActionListener(
                e -> new CustomerRequirements()
        );

        roomBookingButton.addActionListener(
                e -> new RoomBooking()
        );

        checkInButton.addActionListener(
                e -> new CheckIn()
        );

        checkOutButton.addActionListener(
                e -> new CheckOut()
        );

        billPaymentButton.addActionListener(
                e -> new BillPayment()
        );

        customerReportButton.addActionListener(
                e -> new CustomerReport()
        );

        bookingReportButton.addActionListener(
                e -> new BookingReport()
        );

        excelReportButton.addActionListener(
                e -> ExcelReport.generateReport(this)
        );

        settingsButton.addActionListener(
                e -> new Settings(
                        employeeName,
                        employeeRole,
                        username
                )
        );

        logoutButton.addActionListener(
                e -> logout()
        );

        // =================================================
        // SHOW DASHBOARD
        // =================================================

        setVisible(true);
    }

    // =====================================================
    // CREATE BUTTON
    // =====================================================

    private JButton createButton(
            String text) {

        JButton button =
                new JButton(text);

        button.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        17
                )
        );

        button.setFocusPainted(false);

        button.setBackground(
                Color.WHITE
        );

        button.setForeground(
                new Color(35, 47, 62)
        );

        button.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        210,
                                        215,
                                        220
                                )
                        ),
                        BorderFactory.createEmptyBorder(
                                15,
                                15,
                                15,
                                15
                        )
                )
        );

        button.setPreferredSize(
                new Dimension(
                        220,
                        100
                )
        );

        return button;
    }

    // =====================================================
    // LOGOUT
    // =====================================================

    private void logout() {

        int choice =
                JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to logout?",
                        "Logout",
                        JOptionPane.YES_NO_OPTION
                );

        if (choice ==
                JOptionPane.YES_OPTION) {

            dispose();

            new LoginFrame();
        }
    }
}