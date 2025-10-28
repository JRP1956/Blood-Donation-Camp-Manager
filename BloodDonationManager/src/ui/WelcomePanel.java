package ui;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public class WelcomePanel extends JPanel {
    private final Consumer<String> navigate;

    public WelcomePanel(Consumer<String> navigate) {
        this.navigate = navigate;
        setLayout(new BorderLayout());
        setBackground(new Color(255, 245, 245));

        // Main content panel with gradient-like effect
        JPanel centerPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                try {
                    // Gradient background
                    g2d.setPaint(new GradientPaint(0, 0, new Color(255, 240, 240), 
                                                   0, getHeight(), new Color(250, 235, 235)));
                    g2d.fillRect(0, 0, getWidth(), getHeight());
                } finally {
                    g2d.dispose();
                }
            }
        };
        centerPanel.setLayout(new GridBagLayout());
        centerPanel.setOpaque(false);

        // Red heart icon (🩸 using text as icon)
        JLabel iconLabel = new JLabel("🩸");
        iconLabel.setFont(new Font("Serif", Font.PLAIN, 100));
        
        JLabel titleLabel = new JLabel("Blood Donation Camp Manager");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 36));
        titleLabel.setForeground(new Color(200, 0, 0));

        JLabel subtitleLabel = new JLabel("Saving Lives, One Donation at a Time");
        subtitleLabel.setFont(new Font("Segoe UI", Font.ITALIC, 20));
        subtitleLabel.setForeground(new Color(120, 120, 120));

        // Quick Actions panel with large buttons
        JPanel actionsPanel = new JPanel();
        actionsPanel.setOpaque(false);
        actionsPanel.setLayout(new GridLayout(2, 2, 16, 16));
        actionsPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 180, 180), 2, true),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        actionsPanel.add(createActionButton("➕ Register Donor", new Color(0, 150, 0), () -> navigate.accept("register")));
        actionsPanel.add(createActionButton("🔍 Search Donors", new Color(0, 120, 200), () -> navigate.accept("search")));
        actionsPanel.add(createActionButton("📅 Schedule Donation", new Color(220, 140, 0), () -> navigate.accept("schedule")));
        actionsPanel.add(createActionButton("📊 View Reports", new Color(200, 0, 0), () -> navigate.accept("report")));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridy = 0;
        centerPanel.add(iconLabel, gbc);
        gbc.gridy = 1;
        centerPanel.add(Box.createVerticalStrut(10), gbc);
        gbc.gridy = 2;
        centerPanel.add(titleLabel, gbc);
        gbc.gridy = 3;
        centerPanel.add(Box.createVerticalStrut(5), gbc);
        gbc.gridy = 4;
        centerPanel.add(subtitleLabel, gbc);
        gbc.gridy = 5;
        centerPanel.add(Box.createVerticalStrut(20), gbc);
        gbc.gridy = 6;
        centerPanel.add(actionsPanel, gbc);

        add(centerPanel, BorderLayout.CENTER);
    }

    private JButton createActionButton(String text, Color color, Runnable onClick) {
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
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 14, 14);
                g2d.dispose();
                super.paintComponent(g);
            }
        };
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setForeground(Color.WHITE);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setPreferredSize(new Dimension(260, 70));
        button.addActionListener(e -> onClick.run());
        return button;
    }
}

