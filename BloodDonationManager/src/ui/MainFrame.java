package ui;

import service.DonorService;
import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private DonorService donorService;
    private JPanel mainPanel;
    private CardLayout cardLayout;

    public MainFrame() {
        donorService = new DonorService();
        
        setTitle("🩸 Blood Donation Camp Manager");
        setSize(1000, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setBackground(new Color(255, 250, 250));

        // Create menu bar
        createMenuBar();

        // Create main panel with CardLayout
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Add different panels
        mainPanel.add(new WelcomePanel(view -> cardLayout.show(mainPanel, view)), "welcome");
        mainPanel.add(new RegistrationForm(donorService), "register");
        mainPanel.add(new SearchForm(donorService), "search");
        mainPanel.add(new ScheduleForm(donorService), "schedule");
        mainPanel.add(new ReportForm(donorService), "report");

        add(mainPanel);
        cardLayout.show(mainPanel, "welcome");
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(245, 245, 245));

        JMenu fileMenu = new JMenu("📁 File");
        fileMenu.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        exitItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitItem);

        JMenu manageMenu = new JMenu("⚙️ Manage");
        manageMenu.setFont(new Font("Segoe UI", Font.BOLD, 13));
        
        JMenuItem registerItem = new JMenuItem("➕ Register Donor");
        registerItem.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        registerItem.addActionListener(e -> cardLayout.show(mainPanel, "register"));
        
        JMenuItem searchItem = new JMenuItem("🔍 Search Donors");
        searchItem.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        searchItem.addActionListener(e -> cardLayout.show(mainPanel, "search"));
        
        JMenuItem scheduleItem = new JMenuItem("📅 Schedule Donation");
        scheduleItem.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        scheduleItem.addActionListener(e -> cardLayout.show(mainPanel, "schedule"));
        
        JMenuItem reportItem = new JMenuItem("📊 View Reports");
        reportItem.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        reportItem.addActionListener(e -> {
            ReportForm reportForm = new ReportForm(donorService);
            mainPanel.add(reportForm, "report");
            cardLayout.show(mainPanel, "report");
        });

        manageMenu.add(registerItem);
        manageMenu.add(searchItem);
        manageMenu.add(scheduleItem);
        manageMenu.add(reportItem);

        menuBar.add(fileMenu);
        menuBar.add(manageMenu);

        // Global Home shortcut (Cmd+H) and button
        JMenu navigateMenu = new JMenu("🏠 Home");
        navigateMenu.setFont(new Font("Segoe UI", Font.BOLD, 13));
        JMenuItem homeItem = new JMenuItem("Go to Home");
        homeItem.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        KeyStroke homeKey = KeyStroke.getKeyStroke('H', Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx());
        homeItem.setAccelerator(homeKey);
        homeItem.addActionListener(e -> cardLayout.show(mainPanel, "welcome"));
        navigateMenu.add(homeItem);

        menuBar.add(navigateMenu);

        setJMenuBar(menuBar);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new MainFrame().setVisible(true);
        });
    }
}

