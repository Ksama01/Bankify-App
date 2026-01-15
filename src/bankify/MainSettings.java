package bankify;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

public class MainSettings extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPanel;

    public MainSettings() {
        setTitle("Bankify - Settings");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());

        JPanel sidebar = createSidebar();
        contentPanel = createContentPanel();

        getContentPane().add(sidebar, BorderLayout.WEST);
        getContentPane().add(contentPanel, BorderLayout.CENTER);
    }

    private JPanel createSidebar() {
        JPanel sidebar = new JPanel(new BorderLayout());
        sidebar.setPreferredSize(new Dimension(300, 0));
        sidebar.setBackground(Color.WHITE);

        JPanel header = new JPanel();
        header.setBackground(Color.WHITE);
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        header.setBorder(BorderFactory.createEmptyBorder(25, 10, 10, 10));

        JLabel logoLabel = new JLabel();
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        URL logoURL = getClass().getResource("/Resources/bank_logo.jpg");
        if (logoURL != null) {
            ImageIcon logoIcon = new ImageIcon(logoURL);
            Image img = logoIcon.getImage().getScaledInstance(240, 160, Image.SCALE_SMOOTH);
            logoLabel.setIcon(new ImageIcon(img));
        }

        header.add(logoLabel);
        header.add(Box.createVerticalStrut(20));

        JPanel menuPanel = new JPanel();
        menuPanel.setBackground(Color.WHITE);
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));

        // Home Button
        RoundedButton homeBtn = createMenuButton("Home", "/Resources/home.png");
        homeBtn.addActionListener(e -> openHomePage());
        menuPanel.add(homeBtn);
        menuPanel.add(Box.createVerticalStrut(15));
        
        // Deposit Button
        RoundedButton depositBtn = createMenuButton("Deposit", "/Resources/deposit.png");
        depositBtn.addActionListener(e -> openDepositPage());
        menuPanel.add(depositBtn);
        menuPanel.add(Box.createVerticalStrut(15));
        
        // Withdraw Button
        RoundedButton withdrawBtn = createMenuButton("Withdraw", "/Resources/withdraw.png");
        withdrawBtn.addActionListener(e -> openWithdrawPage());
        menuPanel.add(withdrawBtn);
        menuPanel.add(Box.createVerticalStrut(15));
        
        // Transfer Button
        RoundedButton transferBtn = createMenuButton("Transfer", "/Resources/transfer.png");
        transferBtn.addActionListener(e -> openTransferPage());
        menuPanel.add(transferBtn);
        menuPanel.add(Box.createVerticalStrut(15));
        
        // Transactions Button
        RoundedButton transactionsBtn = createMenuButton("Transactions", "/Resources/transactions.png");
        transactionsBtn.addActionListener(e -> openTransactionsPage());
        menuPanel.add(transactionsBtn);
        menuPanel.add(Box.createVerticalStrut(15));
        
        // Settings Button (Active)
        RoundedButton settingsBtn = createActiveMenuButton("Settings", "/Resources/settings.png");
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
            Image img = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            btn.setIcon(new ImageIcon(img));
        }
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setMaximumSize(new Dimension(250, 60));
        btn.setPreferredSize(new Dimension(250, 60));
        btn.setBackground(new Color(30, 127, 179));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btn.setHorizontalTextPosition(SwingConstants.RIGHT);
        btn.setIconTextGap(15);
        btn.setFocusPainted(false);
        btn.addMouseListener(new MouseAdapter() {
            @Override public void mouseEntered(MouseEvent e) { btn.setBackground(new Color(20, 100, 150)); }
            @Override public void mouseExited(MouseEvent e) { btn.setBackground(new Color(30, 127, 179)); }
        });
        return btn;
    }

    private RoundedButton createActiveMenuButton(String text, String iconPath) {
        RoundedButton btn = new RoundedButton(text);
        URL iconURL = getClass().getResource(iconPath);
        if (iconURL != null) {
            ImageIcon icon = new ImageIcon(iconURL);
            Image img = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            btn.setIcon(new ImageIcon(img));
        }
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setMaximumSize(new Dimension(250, 60));
        btn.setPreferredSize(new Dimension(250, 60));
        btn.setBackground(new Color(0,191,255));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btn.setHorizontalTextPosition(SwingConstants.RIGHT);
        btn.setIconTextGap(15);
        btn.setHoverBackgroundColor(new Color(0,191,255));
        btn.setPressedBackgroundColor(new Color(0,191,255));
        return btn;
    }

    private JPanel createContentPanel() {
        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(new Color(30,127,179));
        contentPanel.setLayout(null);
        
        JPanel settingsHeaderPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), getHeight(), getHeight());
                g2.dispose();
            }
        };
        settingsHeaderPanel.setLayout(null);
        settingsHeaderPanel.setBackground(new Color(0, 191, 255));
        settingsHeaderPanel.setBounds(335, 50, 230, 70);
        
        JLabel lblNewLabel = new JLabel("");
        URL settingsIconURL = getClass().getResource("/Resources/settings2.png");
        if (settingsIconURL != null) {
            ImageIcon icon = new ImageIcon(settingsIconURL);
            Image scaledImage = icon.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
            lblNewLabel.setIcon(new ImageIcon(scaledImage));
        }
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(25, 12, 60, 45);
        settingsHeaderPanel.add(lblNewLabel);
        
        JLabel settingsLabel = new JLabel("Settings");
        settingsLabel.setForeground(Color.WHITE);
        settingsLabel.setFont(new Font("Tw Cen MT", Font.BOLD, 24));
        settingsLabel.setBounds(95, 12, 120, 45);
        settingsHeaderPanel.add(settingsLabel);
        contentPanel.add(settingsHeaderPanel);
        
        // My Profile
        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setIcon(new ImageIcon(MainSettings.class.getResource("/Resources/my_profile.png")));
        lblNewLabel_1.setBounds(150, 220, 60, 60);
        contentPanel.add(lblNewLabel_1);
        
        JButton btnNewButton = new RoundedButton2("My Profile");
        btnNewButton.setBackground(new Color(0, 191, 255));
        btnNewButton.setFont(new Font("Tw Cen MT", Font.BOLD, 18));
        btnNewButton.setBounds(110, 290, 140, 60); 
        btnNewButton.addActionListener(e -> openMyProfilePage());
        contentPanel.add(btnNewButton);
        
        // Account Type
        JLabel lblNewLabel_2 = new JLabel("");
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2.setIcon(new ImageIcon(MainSettings.class.getResource("/Resources/account_type.png")));
        lblNewLabel_2.setBounds(415, 220, 60, 60);
        contentPanel.add(lblNewLabel_2);
        
        JButton btnNewButton_1 = new RoundedButton2("<html><center>Account<br>Type</center></html>");
        btnNewButton_1.setBackground(new Color(0, 191, 255));
        btnNewButton_1.setFont(new Font("Tw Cen MT", Font.BOLD, 18));
        btnNewButton_1.setBounds(375, 290, 140, 60);
        btnNewButton_1.addActionListener(e -> openAccountTypePage());
        contentPanel.add(btnNewButton_1);
        
        // Change Password
        JLabel lblNewLabel_3 = new JLabel("");
        lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_3.setIcon(new ImageIcon(MainSettings.class.getResource("/Resources/change_password.png")));
        lblNewLabel_3.setBounds(680, 220, 60, 60);
        contentPanel.add(lblNewLabel_3);
        
        JButton btnNewButton_2 = new RoundedButton2("<html><center>Change<br>Password</center></html>");
        btnNewButton_2.setBackground(new Color(0, 191, 255));
        btnNewButton_2.setFont(new Font("Tw Cen MT", Font.BOLD, 18));
        btnNewButton_2.setBounds(640, 290, 140, 60);
        btnNewButton_2.addActionListener(e -> openChangePasswordPage());
        contentPanel.add(btnNewButton_2);
        
        // Deactivate Account
        JLabel lblNewLabel_4 = new JLabel("");
        lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_4.setIcon(new ImageIcon(MainSettings.class.getResource("/Resources/deactivate_account.png")));
        lblNewLabel_4.setBounds(280, 450, 60, 60);
        contentPanel.add(lblNewLabel_4);
        
        JButton btnNewButton_3 = new RoundedButton2("<html><center>Deactivate<br>Account</center></html>");
        btnNewButton_3.setBackground(new Color(0, 191, 255));
        btnNewButton_3.setFont(new Font("Tw Cen MT", Font.BOLD, 18));
        btnNewButton_3.setBounds(240, 520, 140, 60);
        btnNewButton_3.addActionListener(e -> openDeactivateAccountPage());
        contentPanel.add(btnNewButton_3);
        
        // Log Out
        JLabel lblNewLabel_5 = new JLabel("");
        lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_5.setIcon(new ImageIcon(MainSettings.class.getResource("/Resources/logout.png")));
        lblNewLabel_5.setBounds(550, 450, 60, 60);
        contentPanel.add(lblNewLabel_5);
        
        JButton btnNewButton_4 = new RoundedButton2("Log Out");
        btnNewButton_4.setBackground(new Color(0, 191, 255));
        btnNewButton_4.setFont(new Font("Tw Cen MT", Font.BOLD, 18));
        btnNewButton_4.setBounds(510, 520, 140, 60); 
        btnNewButton_4.addActionListener(e -> openLogoutPage());
        contentPanel.add(btnNewButton_4);
        
        return contentPanel;
    }

    // ===== Navigation Methods =====
    private void openMyProfilePage() {
        new MyProfile().setVisible(true);
        this.dispose();
    }

    private void openAccountTypePage() {
        new AccountType().setVisible(true);
        this.dispose();
    }

    private void openChangePasswordPage() {
        new ChangePassword().setVisible(true);
        this.dispose();
    }

    private void openDeactivateAccountPage() {
        new DeactivateAccount().setVisible(true);
        this.dispose();
    }

    private void openHomePage() {
        new HomePage().setVisible(true);
        this.dispose();
    }

    private void openDepositPage() {
        new DepositPage().setVisible(true);
        this.dispose();
    }

    private void openWithdrawPage() {
        new WithdrawPage().setVisible(true);
        this.dispose();
    }

    private void openTransferPage() {
        new TransferPage().setVisible(true);
        this.dispose();
    }

    private void openTransactionsPage() {
        SwingUtilities.invokeLater(() -> {
            JFrame transactionsFrame = new JFrame("Bankify - Transactions");
            transactionsFrame.setSize(1200, 800);
            transactionsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            CardLayout cardLayout = new CardLayout();
            JPanel container = new JPanel(cardLayout);
            TransactionsPage transactionsPage = new TransactionsPage(cardLayout, container, transactionsFrame);
            container.add(transactionsPage, "Transactions");
            transactionsFrame.add(container);
            transactionsFrame.setLocationRelativeTo(this);
            transactionsFrame.setVisible(true);
            this.dispose();
        });
    }
    
    private void openLogoutPage() {
        int confirm = JOptionPane.showConfirmDialog(
            this, "Are you sure you want to logout?", "Confirm Logout", JOptionPane.YES_NO_OPTION
        );
        if (confirm == JOptionPane.YES_OPTION) {
            this.dispose();
            new Login().setVisible(true); 
        }
    }

    // ===== Custom Components =====
    private class RoundedButton extends JButton {
        private static final long serialVersionUID = 1L;
        private Color hoverBackgroundColor, pressedBackgroundColor;
        public RoundedButton(String text) {
            super(text);
            setContentAreaFilled(false); setBorderPainted(false); setFocusPainted(false); setOpaque(false);
            setCursor(new Cursor(Cursor.HAND_CURSOR));
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
    
    private class RoundedButton2 extends JButton {
        private static final long serialVersionUID = 1L;
        private Color hoverColor = new Color(30, 150, 255);
        private Color pressedColor = new Color(20, 120, 200);
        private Color currentColor = new Color(0, 191, 255);
        public RoundedButton2(String text) {
            super(text);
            setContentAreaFilled(false); setBorderPainted(false); setFocusPainted(false); setOpaque(false);
            setCursor(new Cursor(Cursor.HAND_CURSOR));
            setForeground(Color.WHITE);
            addMouseListener(new MouseAdapter() {
                @Override public void mouseEntered(MouseEvent e) { currentColor = hoverColor; repaint(); }
                @Override public void mouseExited(MouseEvent e) { currentColor = new Color(0, 191, 255); repaint(); }
                @Override public void mousePressed(MouseEvent e) { currentColor = pressedColor; repaint(); }
                @Override public void mouseReleased(MouseEvent e) { 
                    currentColor = contains(e.getPoint()) ? hoverColor : new Color(0, 191, 255);
                    repaint(); 
                }
            });
        }
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(currentColor);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), getHeight(), getHeight());
            g2.dispose();
            super.paintComponent(g);
        }
        @Override public void setBackground(Color bg) { currentColor = bg; repaint(); }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainSettings().setVisible(true));
    }
}