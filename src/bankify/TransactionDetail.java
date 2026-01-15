package bankify;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

public class TransactionDetail extends JPanel {

    public TransactionDetail(TransactionsPage.Tx tx, CardLayout cardLayout, JPanel contentPanel) {
        setLayout(new BorderLayout());
        setBackground(new Color(235, 238, 242));

        // ===== Create Sidebar =====
        JPanel sidebarPanel = createSidebar(cardLayout, contentPanel);
        add(sidebarPanel, BorderLayout.WEST);

        // ===== Create Main Content =====
        JPanel mainContent = createMainContent(tx, cardLayout, contentPanel);
        add(mainContent, BorderLayout.CENTER);
    }

    // ===== Sidebar Creation Method =====
    private JPanel createSidebar(CardLayout cardLayout, JPanel contentPanel) {
        JPanel sidebar = new JPanel(new BorderLayout());
        sidebar.setPreferredSize(new Dimension(280, 0)); // Increased width for 1200x800
        sidebar.setBackground(Color.WHITE);

        JPanel header = new JPanel();
        header.setBackground(Color.WHITE);
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        header.setBorder(BorderFactory.createEmptyBorder(30, 10, 20, 10));

        JLabel logoLabel = new JLabel();
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        URL logoURL = getClass().getResource("/Resources/bank_logo.jpg");
        if (logoURL != null) {
            ImageIcon logoIcon = new ImageIcon(logoURL);
            Image img = logoIcon.getImage().getScaledInstance(220, 140, Image.SCALE_SMOOTH);
            logoLabel.setIcon(new ImageIcon(img));
        }
        header.add(logoLabel);
        header.add(Box.createVerticalStrut(10));

        JPanel menuPanel = new JPanel();
        menuPanel.setBackground(Color.WHITE);
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));

        // Home button
        RoundedButton homeBtn = createMenuButton("Home", "/Resources/home.png");
        homeBtn.addActionListener(e -> openHomePage());
        menuPanel.add(homeBtn);
        menuPanel.add(Box.createVerticalStrut(15));
        
        // Deposit button
        RoundedButton depositBtn = createMenuButton("Deposit", "/Resources/deposit.png");
        depositBtn.addActionListener(e -> openDepositPage());
        menuPanel.add(depositBtn);
        menuPanel.add(Box.createVerticalStrut(15));
        
        // Withdraw button
        RoundedButton withdrawBtn = createMenuButton("Withdraw", "/Resources/withdraw.png");
        withdrawBtn.addActionListener(e -> openWithdrawPage());
        menuPanel.add(withdrawBtn);
        menuPanel.add(Box.createVerticalStrut(15));
        
        // Transfer button
        RoundedButton transferBtn = createMenuButton("Transfer", "/Resources/transfer.png");
        transferBtn.addActionListener(e -> openTransferPage());
        menuPanel.add(transferBtn);
        menuPanel.add(Box.createVerticalStrut(15));
        
        // Transactions button
        RoundedButton transactionsBtn = createMenuButton("Transactions", "/Resources/transactions.png");
        transactionsBtn.addActionListener(e -> {
            cardLayout.show(contentPanel, "Transactions");
        });
        menuPanel.add(transactionsBtn);
        menuPanel.add(Box.createVerticalStrut(15));
        
        // Settings button
        RoundedButton settingsBtn = createMenuButton("Settings", "/Resources/settings.png");
        settingsBtn.addActionListener(e -> openSettingsPage());
        menuPanel.add(settingsBtn);
        
        menuPanel.add(Box.createVerticalGlue());

        sidebar.add(header, BorderLayout.NORTH);
        sidebar.add(menuPanel, BorderLayout.CENTER);

        return sidebar;
    }


// ===== Menu Button Creation Method =====
    private RoundedButton createMenuButton(String text, String iconPath) {
        RoundedButton btn = new RoundedButton(text);
        URL iconURL = getClass().getResource(iconPath);
        if (iconURL != null) {
            ImageIcon icon = new ImageIcon(iconURL);
            Image img = icon.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
            btn.setIcon(new ImageIcon(img));
        }
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setMaximumSize(new Dimension(240, 60));
        btn.setPreferredSize(new Dimension(240, 60));
        btn.setBackground(new Color(30,127,179));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 18)); // Increased font size
        btn.setHorizontalTextPosition(SwingConstants.RIGHT);
        btn.setIconTextGap(15);
        btn.setFocusPainted(false);

        // Hover effect
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { 
                btn.setBackground(new Color(20,100,150)); 
            }
            public void mouseExited(MouseEvent e) { 
                btn.setBackground(new Color(30,127,179)); 
            }
            public void mousePressed(MouseEvent e) {
                btn.setBackground(new Color(15, 85, 130));
            }
            public void mouseReleased(MouseEvent e) {
                btn.setBackground(new Color(30,127,179));
            }
        });

        return btn;
    }

    // ===== Main Content Creation Method =====
    private JPanel createMainContent(TransactionsPage.Tx tx, CardLayout cardLayout, JPanel contentPanel) {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(235, 238, 242));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 40, 40));

        // ===== Title =====
        JLabel titleLabel = new JLabel("Transaction Detail");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 32)); // Increased font size
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createVerticalStrut(25));

        // ===== Transaction Card =====
        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(new BorderLayout());
        cardPanel.setBackground(Color.WHITE);
        cardPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
            BorderFactory.createEmptyBorder(30, 30, 30, 30)
        ));
        cardPanel.setMaximumSize(new Dimension(800, 500)); // Adjusted for 1200 width
        cardPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Transaction Type with icon
        JPanel typePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        typePanel.setBackground(Color.WHITE);
        
        String iconName = "";
        switch (tx.type.toLowerCase()) {
            case "deposit": iconName = "/Resources/deposit.png"; break;
            case "withdraw": iconName = "/Resources/withdraw.png"; break;
            case "transfer": case "send": case "receive": iconName = "/Resources/transfer.png"; break;
            default: iconName = "/Resources/transactions.png";
        }
        
        JLabel typeIcon = new JLabel();
        URL typeIconURL = getClass().getResource(iconName);
        if (typeIconURL != null) {
            ImageIcon icon = new ImageIcon(typeIconURL);
            Image img = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            typeIcon.setIcon(new ImageIcon(img));
        }
        typePanel.add(typeIcon);
        
        JLabel typeLabel = new JLabel(tx.type);
        typeLabel.setFont(new Font("Segoe UI", Font.BOLD, 26)); // Increased font size
        typeLabel.setForeground(getTypeColor(tx.type));
        typePanel.add(typeLabel);
        
        cardPanel.add(typePanel, BorderLayout.NORTH);


// ===== Details Panel =====
        JPanel detailsPanel = new JPanel(new GridBagLayout());
        detailsPanel.setBackground(Color.WHITE);
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Amount row
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0.4;
        detailsPanel.add(createDetailLabel("Amount:", true, 20), gbc);
        gbc.gridx = 1; gbc.weightx = 0.6;
        JLabel amountLabel = createDetailLabel(tx.amount, false, 28); // Larger amount
        amountLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        amountLabel.setForeground(tx.amount.startsWith("+") ? new Color(0, 150, 0) : new Color(200, 0, 0));
        detailsPanel.add(amountLabel, gbc);
        
        // Transaction ID row
        gbc.gridx = 0; gbc.gridy = 1;
        detailsPanel.add(createDetailLabel("Transaction ID:", true, 18), gbc);
        gbc.gridx = 1;
        detailsPanel.add(createDetailLabel(tx.transactionId, false, 18), gbc);
        
        // Date row
        gbc.gridx = 0; gbc.gridy = 2;
        detailsPanel.add(createDetailLabel("Date & Time:", true, 18), gbc);
        gbc.gridx = 1;
        detailsPanel.add(createDetailLabel(tx.date, false, 18), gbc);
        
        // Status row
        gbc.gridx = 0; gbc.gridy = 3;
        detailsPanel.add(createDetailLabel("Status:", true, 18), gbc);
        gbc.gridx = 1;
        JLabel statusLabel = createDetailLabel(tx.status, false, 20);
        statusLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        statusLabel.setForeground(tx.status.equals("Successful") ? new Color(0, 150, 0) : new Color(200, 0, 0));
        detailsPanel.add(statusLabel, gbc);
        
        cardPanel.add(detailsPanel, BorderLayout.CENTER);
        
        mainPanel.add(cardPanel);
        mainPanel.add(Box.createVerticalStrut(40));

     // ===== Action Buttons =====
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setBackground(new Color(235, 238, 242));
        buttonPanel.add(Box.createHorizontalGlue());
        
        RoundedButton backButton = new RoundedButton("← Back to Transactions");
        backButton.setPreferredSize(new Dimension(280, 55));
        backButton.setMinimumSize(new Dimension(280, 55));
        backButton.setMaximumSize(new Dimension(280, 55));
        backButton.setBackground(new Color(30, 127, 179));
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Segoe UI", Font.BOLD, 18)); 

        // === Hover Effect ထည့်သွင်းခြင်း ===
        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) { 
                backButton.setBackground(new Color(20, 100, 150)); // Mouse တင်ရင် အရောင်ရင့်သွားမည်
            }
            @Override
            public void mouseExited(MouseEvent e) { 
                backButton.setBackground(new Color(30, 127, 179)); // Mouse ဖယ်ရင် မူလအရောင်ပြန်ဖြစ်မည်
            }
        });

        backButton.addActionListener(e -> cardLayout.show(contentPanel, "Transactions"));
        buttonPanel.add(backButton);
        
        buttonPanel.add(Box.createHorizontalGlue());
        
        mainPanel.add(buttonPanel);
        mainPanel.add(Box.createVerticalGlue());

        return mainPanel;
    }

    private Color getTypeColor(String type) {
        switch (type.toLowerCase()) {
            case "deposit": case "receive": return new Color(0, 150, 0);
            case "withdraw": case "send": case "transfer": return new Color(200, 0, 0);
            default: return Color.BLACK;
        }
    }

    // Helper with font size parameter
    private JLabel createDetailLabel(String text, boolean isBold, int size) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", isBold ? Font.BOLD : Font.PLAIN, size));
        label.setForeground(isBold ? new Color(70, 70, 70) : Color.BLACK);
        return label;
    }
    
    // Existing helper for backward compatibility (if needed)
    private JLabel createDetailLabel(String text, boolean isBold) {
        return createDetailLabel(text, isBold, 14);
    }


// ===== Page Navigation Methods =====
    private void openHomePage() {
        SwingUtilities.invokeLater(() -> {
            HomePage homePage = new HomePage();
            homePage.setVisible(true);
            Window window = SwingUtilities.getWindowAncestor(this);
            if (window != null) window.dispose();
        });
    }
    
    private void openDepositPage() {
        SwingUtilities.invokeLater(() -> {
            DepositPage depositPage = new DepositPage();
            depositPage.setVisible(true);
            Window window = SwingUtilities.getWindowAncestor(this);
            if (window != null) window.dispose();
        });
    }
    
    private void openWithdrawPage() {
        SwingUtilities.invokeLater(() -> {
            WithdrawPage withdrawPage = new WithdrawPage();
            withdrawPage.setVisible(true);
            Window window = SwingUtilities.getWindowAncestor(this);
            if (window != null) window.dispose();
        });
    }
    
    private void openTransferPage() {
        SwingUtilities.invokeLater(() -> {
            TransferPage transferPage = new TransferPage();
            transferPage.setVisible(true);
            Window window = SwingUtilities.getWindowAncestor(this);
            if (window != null) window.dispose();
        });
    }
    
    private void openSettingsPage() {
        SwingUtilities.invokeLater(() -> {
            try {
                MainSettings settingsPage = new MainSettings();
                settingsPage.setVisible(true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Settings page not found.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Window window = SwingUtilities.getWindowAncestor(this);
            if (window != null) window.dispose();
        });
    }

    private static class RoundedButton extends JButton {
        public RoundedButton(String text) {
            super(text);
            setContentAreaFilled(false);
            setBorderPainted(false);
            setFocusPainted(false);
            setOpaque(false);
            setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            
            // ဒေါင့်ကို အပြည့်ဝိုင်းစေရန် arc width/height နေရာမှာ getHeight() ကို သုံးထားပါတယ်
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), getHeight(), getHeight()); 
            
            g2.dispose();
            super.paintComponent(g);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Transaction Detail Test");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1200, 800); // Updated test size

            CardLayout cardLayout = new CardLayout();
            JPanel contentPanel = new JPanel(cardLayout);

            TransactionsPage.Tx tx = new TransactionsPage.Tx("Deposit", "TX0012345678", "+500,000 MMK", 
                "1 Jan 2026 2:30 PM", "Successful", "/icons/user1.png");

            contentPanel.add(new TransactionDetail(tx, cardLayout, contentPanel), "Detail");

            frame.add(contentPanel);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}