package com.HotelManagement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.dynamicconn.Conn1;

public class CustomerRequirements extends JFrame implements ActionListener {

    private JLabel jl1, jl2, jl3, jl4, jl5, jl6, jl7;

    private JTextField jt1, jt2, jt3, jt4;

    private JComboBox<String> jc1, jc2;

    private JButton jb1, jb2, jb3;

    private JTable table;

    private DefaultTableModel model;

    public CustomerRequirements() {

        jl1 = new JLabel("CUSTOMER REQUIREMENTS");

        jl2 = new JLabel("Customer ID");
        jl3 = new JLabel("Persons");
        jl4 = new JLabel("Room Type");
        jl5 = new JLabel("Budget");
        jl6 = new JLabel("AC Required");
        jl7 = new JLabel("Days");

        jt1 = new JTextField();
        jt2 = new JTextField();
        jt3 = new JTextField();
        jt4 = new JTextField();

        jc1 = new JComboBox<String>(
                new String[]{"Single", "Double", "Deluxe"});

        jc2 = new JComboBox<String>(
                new String[]{"AC", "Non-AC"});

        jb1 = new JButton("Save");
        jb2 = new JButton("Clear");
        jb3 = new JButton("Refresh");

        setLayout(null);

        jl1.setBounds(280, 20, 300, 40);

        jl2.setBounds(50, 80, 120, 30);
        jt1.setBounds(170, 80, 200, 30);

        jl3.setBounds(400, 80, 100, 30);
        jt2.setBounds(500, 80, 150, 30);

        jl4.setBounds(50, 130, 120, 30);
        jc1.setBounds(170, 130, 200, 30);

        jl5.setBounds(400, 130, 100, 30);
        jt3.setBounds(500, 130, 150, 30);

        jl6.setBounds(50, 180, 120, 30);
        jc2.setBounds(170, 180, 200, 30);

        jl7.setBounds(400, 180, 100, 30);
        jt4.setBounds(500, 180, 150, 30);

        jb1.setBounds(220, 220, 100, 35);
        jb2.setBounds(330, 220, 100, 35);
        jb3.setBounds(440, 220, 100, 35);

        add(jl1);

        add(jl2);
        add(jt1);

        add(jl3);
        add(jt2);

        add(jl4);
        add(jc1);

        add(jl5);
        add(jt3);

        add(jl6);
        add(jc2);

        add(jl7);
        add(jt4);

        add(jb1);
        add(jb2);
        add(jb3);

        String[] columns = {
            "Customer ID",
            "Customer Name",
            "Persons",
            "Room Type",
            "Budget",
            "AC Required",
            "Days"
        };

        model = new DefaultTableModel(columns, 0);

        table = new JTable(model);

        JScrollPane sp = new JScrollPane(table);

        sp.setBounds(30, 280, 690, 200);

        add(sp);

        jb1.addActionListener(this);
        jb2.addActionListener(this);
        jb3.addActionListener(this);

        setTitle("Customer Requirements");

        setSize(750, 550);

        setVisible(true);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        loadRequirementData();
    }

    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource().equals(jb2)) {

            jt1.setText("");
            jt2.setText("");
            jt3.setText("");
            jt4.setText("");
        }

        if (ae.getSource().equals(jb1)) {

            String customerId = jt1.getText().trim();
            String persons = jt2.getText().trim();
            String roomType = jc1.getSelectedItem().toString();
            String budget = jt3.getText().trim();
            String acRequired = jc2.getSelectedItem().toString();
            String days = jt4.getText().trim();

            if (customerId.isEmpty() ||
                persons.isEmpty() ||
                budget.isEmpty() ||
                days.isEmpty()) {

                JOptionPane.showMessageDialog(
                    this,
                    "Please fill all details"
                );

                return;
            }

            try {

                Connection con = Conn1.mycon();

                PreparedStatement check = con.prepareStatement(
                    "SELECT customer_id FROM customer WHERE customer_id = ?"
                );

                check.setInt(1, Integer.parseInt(customerId));

                ResultSet rs = check.executeQuery();

                if (!rs.next()) {

                    JOptionPane.showMessageDialog(
                        this,
                        "Customer ID not found"
                    );

                    rs.close();
                    check.close();
                    con.close();

                    return;
                }

                rs.close();
                check.close();

                PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO customer_requirement " +
                    "(customer_id, persons, preferred_room_type, budget, ac_required, days) " +
                    "VALUES (?, ?, ?, ?, ?, ?)"
                );

                ps.setInt(1, Integer.parseInt(customerId));
                ps.setInt(2, Integer.parseInt(persons));
                ps.setString(3, roomType);
                ps.setDouble(4, Double.parseDouble(budget));
                ps.setString(5, acRequired);
                ps.setInt(6, Integer.parseInt(days));

                ps.executeUpdate();

                JOptionPane.showMessageDialog(
                    this,
                    "Requirement Saved Successfully"
                );

                jt1.setText("");
                jt2.setText("");
                jt3.setText("");
                jt4.setText("");

                ps.close();
                con.close();

                loadRequirementData();

            } catch (NumberFormatException e) {

                JOptionPane.showMessageDialog(
                    this,
                    "Please enter valid numbers"
                );

            } catch (Exception e) {

                e.printStackTrace();
            }
        }

        if (ae.getSource().equals(jb3)) {

            loadRequirementData();
        }
    }

    public void loadRequirementData() {

        try {

            Connection con = Conn1.mycon();

            PreparedStatement ps = con.prepareStatement(
                "SELECT cr.customer_id, c.name, " +
                "cr.persons, cr.preferred_room_type, " +
                "cr.budget, cr.ac_required, cr.days " +
                "FROM customer_requirement cr " +
                "INNER JOIN customer c " +
                "ON cr.customer_id = c.customer_id"
            );

            ResultSet rs = ps.executeQuery();

            model.setRowCount(0);

            while (rs.next()) {

                Object[] row = {

                    rs.getInt("customer_id"),

                    rs.getString("name"),

                    rs.getInt("persons"),

                    rs.getString("preferred_room_type"),

                    rs.getDouble("budget"),

                    rs.getString("ac_required"),

                    rs.getInt("days")
                };

                model.addRow(row);
            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}