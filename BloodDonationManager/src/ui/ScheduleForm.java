package ui;

import service.DonorService;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class ScheduleForm extends JPanel {
    private DonorService donorService;
    private JTextField donorIdField;
    private JSpinner dateSpinner;
    private JComboBox<String> statusCombo;

    public ScheduleForm(DonorService donorService) {
        this.donorService = donorService;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setBackground(new Color(255, 250, 250));

        // Title
        JPanel titlePanel = new JPanel(new FlowLayout());
        titlePanel.setOpaque(false);
        JLabel titleLabel = new JLabel("📅 Schedule Donation");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(new Color(200, 0, 0));
        titlePanel.add(titleLabel);
        add(titlePanel, BorderLayout.NORTH);

        // Form Panel with better styling
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(240, 220, 220), 2, true),
            BorderFactory.createEmptyBorder(30, 40, 30, 40)
        ));
        formPanel.setBackground(new Color(255, 255, 255));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(12, 15, 12, 15);

        Font labelFont = new Font("Segoe UI", Font.BOLD, 14);
        Font fieldFont = new Font("Segoe UI", Font.PLAIN, 13);

        // Donor ID
        gbc.gridx = 0; gbc.gridy = 0;
        JLabel donorLabel = new JLabel("🆔 Donor ID:");
        donorLabel.setFont(labelFont);
        donorLabel.setForeground(new Color(100, 100, 100));
        formPanel.add(donorLabel, gbc);
        gbc.gridx = 1;
        donorIdField = new JTextField(20);
        donorIdField.setFont(fieldFont);
        donorIdField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        formPanel.add(donorIdField, gbc);

        // Date
        gbc.gridx = 0; gbc.gridy = 1;
        JLabel dateLabel = new JLabel("📆 Date:");
        dateLabel.setFont(labelFont);
        dateLabel.setForeground(new Color(100, 100, 100));
        formPanel.add(dateLabel, gbc);
        gbc.gridx = 1;
        SpinnerDateModel dateModel = new SpinnerDateModel();
        dateSpinner = new JSpinner(dateModel);
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, "yyyy-MM-dd");
        dateSpinner.setEditor(dateEditor);
        dateSpinner.setFont(fieldFont);
        formPanel.add(dateSpinner, gbc);

        // Status (for updates)
        gbc.gridx = 0; gbc.gridy = 2;
        JLabel statusLabel = new JLabel("✓ Status:");
        statusLabel.setFont(labelFont);
        statusLabel.setForeground(new Color(100, 100, 100));
        formPanel.add(statusLabel, gbc);
        gbc.gridx = 1;
        String[] statuses = {"Scheduled", "Completed", "Cancelled"};
        statusCombo = new JComboBox<>(statuses);
        statusCombo.setFont(fieldFont);
        formPanel.add(statusCombo, gbc);

        JPanel wrapperPanel = new JPanel();
        wrapperPanel.setOpaque(false);
        wrapperPanel.add(formPanel);
        add(wrapperPanel, BorderLayout.CENTER);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 5, 0));
        
        JButton scheduleButton = createStyledButton("📅 Schedule Donation", new Color(0, 150, 0));
        JButton updateButton = createStyledButton("✓ Update Status", new Color(0, 120, 200));

        scheduleButton.addActionListener(e -> scheduleDonation());
        updateButton.addActionListener(e -> updateStatus());

        buttonPanel.add(scheduleButton);
        buttonPanel.add(Box.createHorizontalStrut(10));
        buttonPanel.add(updateButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private JButton createStyledButton(String text, Color color) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                if (getModel().isPressed()) {
                    g2d.setColor(color.darker());
                } else if (getModel().isRollover()) {
                    g2d.setColor(color.brighter());
                } else {
                    g2d.setColor(color);
                }
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                
                g2d.dispose();
                super.paintComponent(g);
            }
        };
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setPreferredSize(new Dimension(160, 40));
        return button;
    }

    private void scheduleDonation() {
        String donorId = donorIdField.getText().trim();
        
        if (donorId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter Donor ID!", 
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        java.util.Date utilDate = (java.util.Date) dateSpinner.getValue();
        LocalDate date = new java.sql.Date(utilDate.getTime()).toLocalDate();

        if (donorService.scheduleDonation(donorId, date)) {
            JOptionPane.showMessageDialog(this, "Donation scheduled successfully!", 
                "Success", JOptionPane.INFORMATION_MESSAGE);
            donorIdField.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Failed to schedule! Check if Donor ID exists.", 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateStatus() {
        String donorId = donorIdField.getText().trim();
        String status = (String) statusCombo.getSelectedItem();

        if (donorId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter Donor ID!", 
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (donorService.updateDonationStatus(donorId, status)) {
            JOptionPane.showMessageDialog(this, "Status updated successfully!", 
                "Success", JOptionPane.INFORMATION_MESSAGE);
            donorIdField.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Failed to update status!", 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
