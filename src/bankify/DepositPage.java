package bankify;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.net.URL;

public class DepositPage extends JFrame {

    private static final long serialVersionUID = 1L;
    private JLabel lblBalance;
    private JTextField txtAgent;
    private JTextField txtAmount;
    private boolean balanceVisible = true;
    
    private double currentBalance = 100000.0; 

    public DepositPage() {
        setTitle("Bankify - Deposit");
        // Screen size updated to 1200x800
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());

        JPanel sidebar = createSidebar();
        JPanel contentPanel = createDepositContent();

        add(sidebar, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);
    }

    private JPanel createSidebar() {
        JPanel sidebar = new JPanel(new BorderLayout());
        sidebar.setPreferredSize(new Dimension(280, 0)); // Sidebar width optimized for 1200
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
            Image img = logoIcon.getImage().getScaledInstance(220, 150, Image.SCALE_SMOOTH);
            logoLabel.setIcon(new ImageIcon(img));
        }
        header.add(logoLabel);
        header.add(Box.createVerticalStrut(10));

        JPanel menuPanel = new JPanel();
        menuPanel.setBackground(Color.WHITE);
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        
        // Sidebar buttons with increased font size
        RoundedButton homeBtn = createMenuButton("Home", "/Resources/home.png");
        homeBtn.addActionListener(e -> openHomePage());
        menuPanel.add(homeBtn);
        menuPanel.add(Box.createVerticalStrut(15));
        
        RoundedButton depositBtn = createActiveMenuButton("Deposit", "/Resources/deposit.png");
        menuPanel.add(depositBtn);
        menuPanel.add(Box.createVerticalStrut(15));
        
        RoundedButton withdrawBtn = createMenuButton("Withdraw", "/Resources/withdraw.png");
        withdrawBtn.addActionListener(e -> openWithdrawPage());
        menuPanel.add(withdrawBtn);
        menuPanel.add(Box.createVerticalStrut(15));
        
        RoundedButton transferBtn = createMenuButton("Transfer", "/Resources/transfer.png");
        transferBtn.addActionListener(e -> openTransferPage());
        menuPanel.add(transferBtn);
        menuPanel.add(Box.createVerticalStrut(15));
        
        RoundedButton transactionsBtn = createMenuButton("Transactions", "/Resources/transactions.png");
        transactionsBtn.addActionListener(e -> openTransactionsPage());
        menuPanel.add(transactionsBtn);
        menuPanel.add(Box.createVerticalStrut(15));
        
        RoundedButton settingsBtn = createMenuButton("Settings", "/Resources/settings.png");
        settingsBtn.addActionListener(e -> openSettingsPage());
        menuPanel.add(settingsBtn);
        
        menuPanel.add(Box.createVerticalGlue());

        sidebar.add(header, BorderLayout.NORTH);
        sidebar.add(menuPanel, BorderLayout.CENTER);

        return sidebar;
    }

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
        btn.setBackground(new Color(30, 127, 179));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 18)); // Increased Font Size
        btn.setHorizontalTextPosition(SwingConstants.RIGHT);
        btn.setIconTextGap(15);
        
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btn.setBackground(new Color(20,100,150)); }
            public void mouseExited(MouseEvent e) { btn.setBackground(new Color(30,127,179)); }
        });
        return btn;
    }

    private RoundedButton createActiveMenuButton(String text, String iconPath) {
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
        btn.setBackground(new Color(0,191,255));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 18)); // Increased Font Size
        btn.setHorizontalTextPosition(SwingConstants.RIGHT);
        btn.setIconTextGap(15);
        btn.setHoverBackgroundColor(new Color(0,191,255));
        btn.setPressedBackgroundColor(new Color(0,191,255));
        return btn;
    }

    private JPanel createDepositContent() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(30, 127, 179)); 
        panel.setLayout(null);

        // User Profile Section
        JPanel userPanel = createRoundedPanel(new Color(0, 191, 255)); 
        userPanel.setBounds(70,50, 220, 80);
        userPanel.setLayout(null);

        JLabel lblUserIcon = createScaledImageLabel("/Resources/my_profile.png", 35, 35);
        lblUserIcon.setBounds(12,14,55,55);
        userPanel.add(lblUserIcon);

        JLabel lblUserName = new JLabel("Aung Aung");
        lblUserName.setForeground(Color.WHITE);
        lblUserName.setFont(new Font("Tw Cen MT", Font.BOLD, 22)); // Increased Font Size
        lblUserName.setBounds(70,22,140,35);
        userPanel.add(lblUserName);
        panel.add(userPanel);

        // Account Balance Section
        JPanel balancePanel = createRoundedPanel(new Color(0, 191, 255));
        balancePanel.setBounds(70,160,470, 80);
        balancePanel.setLayout(null);

        JLabel lblBalanceIcon = createScaledImageLabel("/Resources/Balance.png", 40, 40);
        lblBalanceIcon.setBounds(20, 20, 43, 43);
        balancePanel.add(lblBalanceIcon);

        lblBalance = new JLabel("Account Balance : " + String.format("%.2f", currentBalance) + " MMK");
        lblBalance.setForeground(Color.WHITE);
        lblBalance.setFont(new Font("Tw Cen MT", Font.BOLD, 22)); // Increased Font Size
        lblBalance.setBounds(75, 22, 370, 35);
        balancePanel.add(lblBalance);
        
        JButton eyeButton = new JButton();
        eyeButton.setBounds(410, 22, 35, 35);
        eyeButton.setContentAreaFilled(false); eyeButton.setBorderPainted(false); eyeButton.setFocusPainted(false);
        eyeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        URL eyeOpenURL = getClass().getResource("/Resources/eye.png");
        URL eyeClosedURL = getClass().getResource("/Resources/hide.png");

        if (eyeOpenURL != null && eyeClosedURL != null) {
            ImageIcon eyeOpenIcon = new ImageIcon(new ImageIcon(eyeOpenURL).getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
            ImageIcon eyeClosedIcon = new ImageIcon(new ImageIcon(eyeClosedURL).getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
            eyeButton.setIcon(eyeOpenIcon);
            eyeButton.addActionListener(e -> {
                balanceVisible = !balanceVisible;
                eyeButton.setIcon(balanceVisible ? eyeOpenIcon : eyeClosedIcon);
                lblBalance.setText(balanceVisible ? "Account Balance : " + String.format("%.2f", currentBalance) + " MMK" : "Account Balance : ****** MMK");
            });
        }
        balancePanel.add(eyeButton);
        panel.add(balancePanel);

        // Input Fields with Better Fonts
        JLabel lblAgent = new JLabel("Agent");
        lblAgent.setBounds(70, 280, 100, 30);
        lblAgent.setForeground(Color.WHITE);
        lblAgent.setFont(new Font("Tw Cen MT", Font.BOLD, 20)); // Increased Font Size
        panel.add(lblAgent);

        txtAgent = new JTextField();
        txtAgent.setBounds(70, 320, 600, 55); 
        txtAgent.setFont(new Font("Tw Cen MT", Font.PLAIN, 18));
        txtAgent.setForeground(Color.GRAY);
        txtAgent.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        txtAgent.setText("Enter ID");
        txtAgent.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) { if (txtAgent.getText().equals("Enter ID")) { txtAgent.setText(""); txtAgent.setForeground(Color.BLACK); } }
            public void focusLost(FocusEvent e) { if (txtAgent.getText().isEmpty()) { txtAgent.setText("Enter ID"); txtAgent.setForeground(Color.GRAY); } }
        });
        panel.add(txtAgent);

        JLabel lblAmount = new JLabel("Amount");
        lblAmount.setBounds(70, 400, 100, 30);
        lblAmount.setForeground(Color.WHITE);
        lblAmount.setFont(new Font("Tw Cen MT", Font.BOLD, 20)); // Increased Font Size
        panel.add(lblAmount);

        txtAmount = new JTextField();
        txtAmount.setBounds(70, 440, 600, 55);
        txtAmount.setFont(new Font("Tw Cen MT", Font.BOLD, 20));
        txtAmount.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        txtAmount.setText("0");
        txtAmount.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) { if (txtAmount.getText().equals("0")) txtAmount.setText(""); }
            public void focusLost(FocusEvent e) { if (txtAmount.getText().isEmpty()) txtAmount.setText("0"); }
        });
        panel.add(txtAmount);

        // Deposit Button
        RoundedButton btnDeposit = new RoundedButton("Deposit");
        btnDeposit.setBounds(285, 550, 180, 60);
        btnDeposit.setBackground(new Color(50, 205, 50));
        btnDeposit.setForeground(Color.WHITE);
        btnDeposit.setFont(new Font("Tw Cen MT", Font.BOLD, 22)); // Increased Font Size
        btnDeposit.addActionListener(e -> handleDeposit());
        panel.add(btnDeposit);

        return panel;
    }

    private void openHomePage() {
        HomePage homePage = new HomePage();
        homePage.setSize(1200, 800);
        homePage.setLocationRelativeTo(null);
        homePage.setVisible(true);
        this.dispose();
    }
    
    private void openWithdrawPage() {
        WithdrawPage withdrawPage = new WithdrawPage();
        withdrawPage.setSize(1200, 800);
        withdrawPage.setLocationRelativeTo(null);
        withdrawPage.setVisible(true);
        this.dispose();
    }
    
    private void openTransferPage() {
        TransferPage transferPage = new TransferPage();
        transferPage.setSize(1200, 800);
        transferPage.setLocationRelativeTo(null);
        transferPage.setVisible(true);
        this.dispose();
    }
    
    private void openTransactionsPage() {
        SwingUtilities.invokeLater(() -> {
            JFrame transactionsFrame = new JFrame("Bankify - Transactions");
            transactionsFrame.setSize(1200, 800);
            transactionsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            CardLayout cardLayout = new CardLayout();
            JPanel content = new JPanel(cardLayout);
            TransactionsPage tp = new TransactionsPage(cardLayout, content, transactionsFrame);
            content.add(tp, "Transactions");
            transactionsFrame.add(content);
            transactionsFrame.setLocationRelativeTo(this);
            transactionsFrame.setVisible(true);
            this.setVisible(false);
        });
    }
    
    private void openSettingsPage() {
        MainSettings settingsPage = new MainSettings();
        settingsPage.setSize(1200, 800);
        settingsPage.setLocationRelativeTo(null);
        settingsPage.setVisible(true);
        this.dispose();
    }

    private void handleDeposit() {
        String agent = txtAgent.getText().trim();
        String amountStr = txtAmount.getText().trim();

        if (agent.isEmpty() || agent.equals("Enter ID")) {
            JOptionPane.showMessageDialog(this, "Please enter Agent ID.");
            txtAgent.requestFocus(); return;
        }
        if (amountStr.isEmpty() || amountStr.equals("0")) {
            JOptionPane.showMessageDialog(this, "Please enter amount.");
            txtAmount.requestFocus(); return;
        }

        try {
            double depositAmount = Double.parseDouble(amountStr);
            if (depositAmount > 0) {
                currentBalance += depositAmount;
                if (balanceVisible) lblBalance.setText("Account Balance : " + String.format("%.2f", currentBalance) + " MMK");
                JOptionPane.showMessageDialog(this, "Successfully Deposited!");
                txtAgent.setText("Enter ID"); txtAgent.setForeground(Color.GRAY);
                txtAmount.setText("0");
            } else {
                JOptionPane.showMessageDialog(this, "Amount must be greater than 0.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid number for amount!");
        }
    }
 
    private JPanel createRoundedPanel(Color color) {
        JPanel panel = new JPanel() {
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(color);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 80, 80);
                g2.dispose();
            }
        };
        panel.setOpaque(false);
        return panel;
    }

    private JLabel createScaledImageLabel(String path, int width, int height) {
        try {
            URL url = getClass().getResource(path);
            if (url != null) {
                Image img = new ImageIcon(url).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
                return new JLabel(new ImageIcon(img));
            }
        } catch (Exception e) {}
        return new JLabel();
    }

    private class RoundedButton extends JButton {
        private Color hoverBackgroundColor;
        private Color pressedBackgroundColor;
        public RoundedButton(String text) {
            super(text);
            setContentAreaFilled(false); setBorderPainted(false); setFocusPainted(false); setOpaque(false);
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
        public void setHoverBackgroundColor(Color color) { this.hoverBackgroundColor = color; }
        public void setPressedBackgroundColor(Color color) { this.pressedBackgroundColor = color; }
        
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            ButtonModel model = getModel();
            Color currentColor = getBackground();
            if (model.isPressed() && pressedBackgroundColor != null) currentColor = pressedBackgroundColor;
            else if (model.isRollover() && hoverBackgroundColor != null) currentColor = hoverBackgroundColor;
            else if (model.isRollover() || model.isPressed()) currentColor = getBackground().darker();
            g2.setColor(currentColor);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), getHeight(), getHeight());
            g2.dispose();
            super.paintComponent(g);
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DepositPage().setVisible(true));
    }
}