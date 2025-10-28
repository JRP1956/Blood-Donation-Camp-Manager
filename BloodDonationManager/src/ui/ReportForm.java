package ui;

import model.Donor;
import model.Donation;
import service.DonorService;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ReportForm extends JPanel {
    private DonorService donorService;

    public ReportForm(DonorService donorService) {
        this.donorService = donorService;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setBackground(new Color(255, 250, 250));

        // Title
        JPanel titlePanel = new JPanel(new FlowLayout());
        titlePanel.setOpaque(false);
        JLabel titleLabel = new JLabel("📊 Reports & Statistics");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(new Color(200, 0, 0));
        titlePanel.add(titleLabel);
        add(titlePanel, BorderLayout.NORTH);

        // Create tabbed pane for different reports with better styling
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Segoe UI", Font.BOLD, 13));
        tabbedPane.setBackground(new Color(255, 250, 250));
        
        tabbedPane.addTab("📊 Summary", createSummaryPanel());
        tabbedPane.addTab("👥 All Donors", createDonorsPanel());
        tabbedPane.addTab("📅 Donations", createDonationsPanel());
        tabbedPane.addTab("🩸 Blood Group Stats", createBloodGroupPanel());

        add(tabbedPane, BorderLayout.CENTER);
    }

    private JPanel createSummaryPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        panel.setBackground(new Color(255, 255, 255));

        List<Donor> donors = donorService.getAllDonors();
        List<Donation> donations = donorService.getAllDonations();

        Font labelFont = new Font("Segoe UI", Font.BOLD, 14);
        Font valueFont = new Font("Segoe UI", Font.PLAIN, 13);

        // Create stat cards
        addStatCard(panel, "Total Registered Donors:", String.valueOf(donors.size()), "👥", labelFont, valueFont);
        
        long completed = donations.stream().filter(d -> d.getStatus().equals("Completed")).count();
        addStatCard(panel, "Completed Donations:", String.valueOf(completed), "✓", labelFont, valueFont);
        
        long scheduled = donations.stream().filter(d -> d.getStatus().equals("Scheduled")).count();
        addStatCard(panel, "Pending Donations:", String.valueOf(scheduled), "⏳", labelFont, valueFont);

        double avgAge = donors.stream().mapToInt(Donor::getAge).average().orElse(0);
        addStatCard(panel, "Average Donor Age:", String.format("%.1f years", avgAge), "🎂", labelFont, valueFont);

        return panel;
    }

    private void addStatCard(JPanel parent, String label, String value, String icon, Font labelFont, Font valueFont) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(240, 220, 220), 2, true),
            BorderFactory.createEmptyBorder(15, 20, 15, 20)
        ));
        card.setBackground(new Color(255, 250, 250));
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
        
        JLabel iconLabel = new JLabel(icon + " " + label);
        iconLabel.setFont(labelFont);
        iconLabel.setForeground(new Color(100, 100, 100));
        iconLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        valueLabel.setForeground(new Color(200, 0, 0));
        valueLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        card.add(iconLabel);
        card.add(Box.createVerticalStrut(5));
        card.add(valueLabel);
        
        parent.add(card);
        parent.add(Box.createVerticalStrut(10));
    }

    private JPanel createDonorsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(255, 255, 255));
        
        String[] columns = {"Donor ID", "Name", "Age", "Blood Group", "Contact", "Location"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        JTable table = createStyledTable(model);

        List<Donor> donors = donorService.getAllDonors();
        for (Donor donor : donors) {
            Object[] row = {
                donor.getDonorId(),
                donor.getName(),
                donor.getAge(),
                donor.getBloodGroup(),
                donor.getContact(),
                donor.getLocation()
            };
            model.addRow(row);
        }

        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        return panel;
    }

    private JPanel createDonationsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(255, 255, 255));
        
        String[] columns = {"Donor ID", "Date", "Status"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        JTable table = createStyledTable(model);

        List<Donation> donations = donorService.getAllDonations();
        for (Donation donation : donations) {
            Object[] row = {
                donation.getDonorId(),
                donation.getDate(),
                donation.getStatus()
            };
            model.addRow(row);
        }

        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        return panel;
    }

    private JPanel createBloodGroupPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(255, 255, 255));
        
        String[] columns = {"Blood Group", "Count"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        JTable table = createStyledTable(model);

        List<Donor> donors = donorService.getAllDonors();
        Map<String, Long> bloodGroupStats = donors.stream()
            .collect(Collectors.groupingBy(Donor::getBloodGroup, Collectors.counting()));

        bloodGroupStats.forEach((bloodGroup, count) -> {
            Object[] row = {bloodGroup, count};
            model.addRow(row);
        });

        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        return panel;
    }

    private JTable createStyledTable(DefaultTableModel model) {
        JTable table = new JTable(model);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        table.setRowHeight(25);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        table.getTableHeader().setBackground(new Color(220, 50, 50));
        table.getTableHeader().setForeground(Color.WHITE);
        table.setGridColor(new Color(240, 240, 240));
        
        table.setDefaultRenderer(Object.class, new javax.swing.table.DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, 
                    boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (!isSelected) {
                    if (row % 2 == 0) {
                        c.setBackground(new Color(255, 250, 250));
                    } else {
                        c.setBackground(Color.WHITE);
                    }
                }
                return c;
            }
        });
        
        return table;
    }
}

