package ui;

import model.Donor;
import service.DonorService;
import javax.swing.*;
import java.awt.*;

public class RegistrationForm extends JPanel {
    private DonorService donorService;
    private JTextField nameField, ageField, contactField, locationField;
    private JComboBox<String> bloodGroupCombo;

    public RegistrationForm(DonorService donorService) {
        this.donorService = donorService;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setBackground(new Color(255, 250, 250));

        // Title with icon
        JPanel titlePanel = new JPanel(new FlowLayout());
        titlePanel.setOpaque(false);
        JLabel titleLabel = new JLabel("➕ Donor Registration");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(new Color(200, 0, 0));
        titlePanel.add(titleLabel);
        add(titlePanel, BorderLayout.NORTH);

        // Form Panel with better styling
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(240, 220, 220), 2, true),
            BorderFactory.createEmptyBorder(20, 30, 20, 30)
        ));
        formPanel.setBackground(new Color(255, 255, 255));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 15, 10, 15);

        // Helper method to create styled labels
        Font labelFont = new Font("Segoe UI", Font.BOLD, 14);
        Font fieldFont = new Font("Segoe UI", Font.PLAIN, 13);

        // Name
        gbc.gridx = 0; gbc.gridy = 0;
        JLabel nameLabel = new JLabel("👤 Name:");
        nameLabel.setFont(labelFont);
        nameLabel.setForeground(new Color(100, 100, 100));
        formPanel.add(nameLabel, gbc);
        gbc.gridx = 1;
        nameField = new JTextField(25);
        nameField.setFont(fieldFont);
        nameField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(5, 8, 5, 8)
        ));
        formPanel.add(nameField, gbc);

        // Age
        gbc.gridx = 0; gbc.gridy = 1;
        JLabel ageLabel = new JLabel("🎂 Age:");
        ageLabel.setFont(labelFont);
        ageLabel.setForeground(new Color(100, 100, 100));
        formPanel.add(ageLabel, gbc);
        gbc.gridx = 1;
        ageField = new JTextField(25);
        ageField.setFont(fieldFont);
        ageField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(5, 8, 5, 8)
        ));
        formPanel.add(ageField, gbc);

        // Blood Group
        gbc.gridx = 0; gbc.gridy = 2;
        JLabel bloodLabel = new JLabel("🩸 Blood Group:");
        bloodLabel.setFont(labelFont);
        bloodLabel.setForeground(new Color(100, 100, 100));
        formPanel.add(bloodLabel, gbc);
        gbc.gridx = 1;
        String[] bloodGroups = {"A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"};
        bloodGroupCombo = new JComboBox<>(bloodGroups);
        bloodGroupCombo.setFont(fieldFont);
        formPanel.add(bloodGroupCombo, gbc);

        // Contact
        gbc.gridx = 0; gbc.gridy = 3;
        JLabel contactLabel = new JLabel("📞 Contact:");
        contactLabel.setFont(labelFont);
        contactLabel.setForeground(new Color(100, 100, 100));
        formPanel.add(contactLabel, gbc);
        gbc.gridx = 1;
        contactField = new JTextField(25);
        contactField.setFont(fieldFont);
        contactField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(5, 8, 5, 8)
        ));
        formPanel.add(contactField, gbc);

        // Location
        gbc.gridx = 0; gbc.gridy = 4;
        JLabel locationLabel = new JLabel("📍 Location:");
        locationLabel.setFont(labelFont);
        locationLabel.setForeground(new Color(100, 100, 100));
        formPanel.add(locationLabel, gbc);
        gbc.gridx = 1;
        locationField = new JTextField(25);
        locationField.setFont(fieldFont);
        locationField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(5, 8, 5, 8)
        ));
        formPanel.add(locationField, gbc);

        JPanel wrapperPanel = new JPanel();
        wrapperPanel.setOpaque(false);
        wrapperPanel.add(formPanel);
        add(wrapperPanel, BorderLayout.CENTER);

        // Button Panel with better styling
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 5, 0));
        
        JButton registerButton = createStyledButton("✓ Register Donor", new Color(0, 150, 0));
        JButton clearButton = createStyledButton("⟲ Clear", new Color(150, 150, 150));

        registerButton.addActionListener(e -> registerDonor());
        clearButton.addActionListener(e -> clearFields());

        buttonPanel.add(registerButton);
        buttonPanel.add(Box.createHorizontalStrut(10));
        buttonPanel.add(clearButton);
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
        button.setPreferredSize(new Dimension(140, 38));
        return button;
    }

    private void registerDonor() {
        try {
            String name = nameField.getText().trim();
            int age = Integer.parseInt(ageField.getText().trim());
            String bloodGroup = (String) bloodGroupCombo.getSelectedItem();
            String contact = contactField.getText().trim();
            String location = locationField.getText().trim();

            if (name.isEmpty() || contact.isEmpty() || location.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields!", 
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String donorId = donorService.generateNextDonorId();
            Donor donor = new Donor(donorId, name, age, bloodGroup, contact, location);

            if (donorService.registerDonor(donor)) {
                JOptionPane.showMessageDialog(this, 
                    "Donor registered successfully!\nDonor ID: " + donorId, 
                    "Success", JOptionPane.INFORMATION_MESSAGE);
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this, 
                    "Registration failed! Check age eligibility (18-65 years).", 
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid age!", 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        nameField.setText("");
        ageField.setText("");
        contactField.setText("");
        locationField.setText("");
        bloodGroupCombo.setSelectedIndex(0);
    }
}

