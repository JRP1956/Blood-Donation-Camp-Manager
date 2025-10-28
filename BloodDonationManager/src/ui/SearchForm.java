package ui;

import model.Donor;
import service.DonorService;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class SearchForm extends JPanel {
    private DonorService donorService;
    private JComboBox<String> searchTypeCombo;
    private JTextField searchField;
    private JTable resultTable;
    private DefaultTableModel tableModel;

    public SearchForm(DonorService donorService) {
        this.donorService = donorService;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setBackground(new Color(255, 250, 250));

        // Title
        JPanel titlePanel = new JPanel(new FlowLayout());
        titlePanel.setOpaque(false);
        JLabel titleLabel = new JLabel("🔍 Search Donors");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(new Color(200, 0, 0));
        titlePanel.add(titleLabel);

        // Search Panel with better styling
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.X_AXIS));
        searchPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(240, 220, 220), 1),
            BorderFactory.createEmptyBorder(15, 20, 15, 20)
        ));
        searchPanel.setBackground(new Color(255, 255, 255));
        
        searchPanel.add(new JLabel("Search By:"));
        searchPanel.add(Box.createHorizontalStrut(10));
        
        String[] searchTypes = {"Blood Group", "Location", "Name"};
        searchTypeCombo = new JComboBox<>(searchTypes);
        searchTypeCombo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        searchPanel.add(searchTypeCombo);

        searchPanel.add(Box.createHorizontalStrut(10));
        
        searchField = new JTextField(20);
        searchField.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        searchField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(5, 8, 5, 8)
        ));
        searchPanel.add(searchField);

        searchPanel.add(Box.createHorizontalStrut(10));

        JButton searchButton = createStyledButton("🔍 Search", new Color(200, 0, 0));
        searchButton.addActionListener(e -> performSearch());
        searchButton.setPreferredSize(new Dimension(120, 38));
        searchPanel.add(searchButton);

        searchPanel.add(Box.createHorizontalStrut(10));

        JButton showAllButton = createStyledButton("📋 Show All", new Color(0, 120, 200));
        showAllButton.addActionListener(e -> showAllDonors());
        showAllButton.setPreferredSize(new Dimension(130, 38));
        searchPanel.add(showAllButton);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);
        topPanel.add(titlePanel, BorderLayout.NORTH);
        topPanel.add(searchPanel, BorderLayout.SOUTH);
        add(topPanel, BorderLayout.NORTH);

        // Create results table up front and place it in CENTER
        String[] columns = {"Donor ID", "Name", "Age", "Blood Group", "Contact", "Location"};
        tableModel = new DefaultTableModel(columns, 0);
        resultTable = new JTable(tableModel);
        
        // Style the table
        resultTable.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        resultTable.setRowHeight(25);
        resultTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        resultTable.getTableHeader().setBackground(new Color(220, 50, 50));
        resultTable.getTableHeader().setForeground(Color.WHITE);
        resultTable.setGridColor(new Color(240, 240, 240));
        
        // Alternating rows
        resultTable.setDefaultRenderer(Object.class, new javax.swing.table.DefaultTableCellRenderer() {
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

        JScrollPane scrollPane = new JScrollPane(resultTable);
        scrollPane.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(240, 220, 220), 1),
            "Search Results", 0, 0, new Font("Segoe UI", Font.BOLD, 14), 
            new Color(200, 0, 0)
        ));
        add(scrollPane, BorderLayout.CENTER);
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
        button.setFont(new Font("Segoe UI", Font.BOLD, 13));
        button.setForeground(Color.WHITE);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        return button;
    }

    private void performSearch() {
        String searchType = (String) searchTypeCombo.getSelectedItem();
        String searchValue = searchField.getText().trim();

        if (searchValue.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter search value!", 
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        List<Donor> results = null;
        switch (searchType) {
            case "Blood Group":
                results = donorService.searchDonorByBloodGroup(searchValue);
                break;
            case "Location":
                results = donorService.searchDonorByLocation(searchValue);
                break;
            case "Name":
                results = donorService.searchDonorByName(searchValue);
                break;
        }

        displayResults(results);
    }

    private void showAllDonors() {
        displayResults(donorService.getAllDonors());
    }

    private void displayResults(List<Donor> donors) {
        if (donors == null || donors.isEmpty()) {
            tableModel.setRowCount(0);
            JOptionPane.showMessageDialog(this, "No donors found!", 
                "Information", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        tableModel.setRowCount(0);
        for (Donor donor : donors) {
            Object[] row = {
                donor.getDonorId(),
                donor.getName(),
                donor.getAge(),
                donor.getBloodGroup(),
                donor.getContact(),
                donor.getLocation()
            };
            tableModel.addRow(row);
        }
        revalidate();
        repaint();
    }
}

