package bankify;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

public class ChangePassword extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPanel;

    // Password fields
    private RoundedPasswordField pwtCurrent, pwtNew, pwtConfirm;

    public ChangePassword() {
        setTitle("Bankify - Change Password");
        // Screen Size ပြောင်းလဲခြင်း
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());

        // Sidebar
        JPanel sidebar = createSidebar();

        // Content
        contentPanel = createContentPanel();

        getContentPane().add(sidebar, BorderLayout.WEST);
        getContentPane().add(contentPanel, BorderLayout.CENTER);
    }

    private JPanel createSidebar() {
        JPanel sidebar = new JPanel(new BorderLayout());
        // Sidebar Width ကို 1200 နဲ့ မျှအောင် 300 ထားပေးထားပါတယ်
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
            // Logo ကို ပိုကြီးအောင်လုပ်ထားပါတယ်
            Image img = logoIcon.getImage().getScaledInstance(240, 160, Image.SCALE_SMOOTH);
            logoLabel.setIcon(new ImageIcon(img));
        }
        header.add(logoLabel);
        header.add(Box.createVerticalStrut(20));

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
        transactionsBtn.addActionListener(e -> openTransactionsPage());
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

    private JPanel createContentPanel() {
        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(new Color(30,127,179));
        contentPanel.setLayout(null);

        // Header Panel - အချိုးအစား ချိန်ညှိခြင်း
        JPanel settingsHeaderPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 70, 70);
                g2.dispose();
            }
        };
        settingsHeaderPanel.setLayout(null);
        settingsHeaderPanel.setBackground(new Color(0, 191, 255));
        settingsHeaderPanel.setBounds(330, 60, 260, 70);

        JLabel settingsIconLabel = new JLabel("");
        settingsIconLabel.setBounds(20, 15, 40, 40);
        URL settingsIconURL = getClass().getResource("/Resources/change_password.png");
        if (settingsIconURL != null) {
            ImageIcon icon = new ImageIcon(settingsIconURL);
            Image img = icon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
            settingsIconLabel.setIcon(new ImageIcon(img));
        }
        settingsHeaderPanel.add(settingsIconLabel);

        JLabel settingsLabel = new JLabel("Change Password");
        settingsLabel.setBounds(65, 15, 180, 40);
        settingsLabel.setForeground(Color.WHITE);
        settingsLabel.setHorizontalAlignment(SwingConstants.LEFT);
        // Header Font Size ကို 22 သို့ တိုးထားပါတယ်
        settingsLabel.setFont(new Font("Tw Cen MT", Font.BOLD, 22));
        settingsHeaderPanel.add(settingsLabel);

        contentPanel.add(settingsHeaderPanel);

        // Back Button
        JButton btnBack = new JButton();
        btnBack.setBounds(30, 60, 70, 50);
        btnBack.setBackground(new Color(30, 127, 179));
        btnBack.setBorderPainted(false);
        btnBack.setFocusPainted(false);
        btnBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnBack.setIcon(new ImageIcon(ChangePassword.class.getResource("/Resources/exit_arrow.png")));
        btnBack.addActionListener(e -> {
            dispose();
            new MainSettings().setVisible(true);
        });
        contentPanel.add(btnBack);

        // Password Fields Positioning (1200x800 အတွက် Center ကျအောင် ချိန်ထားပါတယ်)
        pwtCurrent = new RoundedPasswordField();
        contentPanel.add(createLabeledField("Current Password", pwtCurrent, 200));

        pwtNew = new RoundedPasswordField();
        contentPanel.add(createLabeledField("New Password", pwtNew, 310));

        pwtConfirm = new RoundedPasswordField();
        contentPanel.add(createLabeledField("Confirm Password", pwtConfirm, 420));

        // OK Button
        RoundedCornerButton btnOK = new RoundedCornerButton("OK");
        btnOK.setBounds(400, 550, 130, 50);
        btnOK.setFont(new Font("Tw Cen MT", Font.BOLD, 18)); // Button font 18
        btnOK.addActionListener(e -> {
            if (validatePassword()) {
                JOptionPane.showMessageDialog(this,
                        "Password changed successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                dispose();
                new MainSettings().setVisible(true);
            }
        });

        contentPanel.add(btnOK);

        return contentPanel;
    }

    private JPanel createLabeledField(String labelText, RoundedPasswordField field, int y) {
        JPanel panel = new JPanel(null);
        panel.setOpaque(false);

        // Label Font 18 သို့ တိုးထားပါတယ်
        JLabel label = new JLabel(labelText);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Tw Cen MT", Font.BOLD, 18));
        label.setBounds(0, 0, 300, 25);
        panel.add(label);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 35, 330, 45); // Field ကို ပိုရှည်ပြီး ပိုမြင့်အောင်လုပ်ထားပါတယ်

        field.setBounds(0, 0, 330, 45);
        field.setEchoChar('•');
        field.setFont(new Font("Segoe UI", Font.PLAIN, 18)); // Input text font 18
        field.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 50));
        layeredPane.add(field, JLayeredPane.DEFAULT_LAYER);

        JLabel eyeLabel = new JLabel();
        eyeLabel.setBounds(285, 10, 25, 25);
        eyeLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        URL eyeURL = getClass().getResource("/Resources/eye.png");
        URL eyeClosedURL = getClass().getResource("/Resources/hide.png");

        if (eyeURL != null && eyeClosedURL != null) {
            ImageIcon openIcon = new ImageIcon(new ImageIcon(eyeURL).getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
            ImageIcon closedIcon = new ImageIcon(new ImageIcon(eyeClosedURL).getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
            eyeLabel.setIcon(closedIcon);

            eyeLabel.addMouseListener(new MouseAdapter() {
                boolean visible = false;
                @Override
                public void mouseClicked(MouseEvent e) {
                    visible = !visible;
                    field.setEchoChar(visible ? (char) 0 : '•');
                    eyeLabel.setIcon(visible ? openIcon : closedIcon);
                }
            });
        }
        layeredPane.add(eyeLabel, JLayeredPane.PALETTE_LAYER);
        panel.add(layeredPane);

        panel.setBounds(300, y, 330, 90); // Panel position ချိန်ညှိမှု
        return panel;
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
        btn.setMaximumSize(new Dimension(250, 60)); // Sidebar button ပိုကြီးအောင်လုပ်ထားပါတယ်
        btn.setPreferredSize(new Dimension(250, 60));
        btn.setBackground(new Color(30,127,179));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 16)); // Sidebar font 16
        btn.setHorizontalTextPosition(SwingConstants.RIGHT);
        btn.setIconTextGap(15);
        btn.setFocusPainted(false);

        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btn.setBackground(new Color(20,100,150)); }
            public void mouseExited(MouseEvent e) { btn.setBackground(new Color(30,127,179)); }
        });

        if (text.equals("Settings")) {
            btn.setBackground(new Color(0, 191, 255)); // Active style
        }
        return btn;
    }

    private class RoundedButton extends JButton {
        private static final long serialVersionUID = 1L;
        public RoundedButton(String text) {
            super(text);
            setContentAreaFilled(false);
            setBorderPainted(false);
            setFocusPainted(false);
            setOpaque(false);
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fillRoundRect(0,0,getWidth(),getHeight(),getHeight(),getHeight());
            g2.dispose();
            super.paintComponent(g);
        }
        @Override protected void paintBorder(Graphics g) {}
    }

    private class RoundedCornerButton extends JButton {
        private static final long serialVersionUID = 1L;
        private Color currentColor;
        public RoundedCornerButton(String text) {
            super(text);
            setContentAreaFilled(false); setBorderPainted(false); setFocusPainted(false); setOpaque(false);
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            currentColor = new Color(220,20,60);
            setForeground(Color.WHITE);
            addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e) { currentColor = currentColor.darker(); repaint(); }
                public void mouseExited(MouseEvent e) { currentColor = new Color(220,20,60); repaint(); }
            });
        }
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(currentColor);
            g2.fillRoundRect(0,0,getWidth(),getHeight(), getHeight(), getHeight());
            g2.dispose();
            super.paintComponent(g);
        }
    }

    private class RoundedPasswordField extends JPasswordField {
        private static final long serialVersionUID = 1L;
        public RoundedPasswordField() {
            setBorder(BorderFactory.createEmptyBorder(5,15,5,15));
        }
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(Color.WHITE);
            g2.fillRoundRect(0,0,getWidth(),getHeight(),getHeight(),getHeight());
            g2.setColor(Color.GRAY);
            g2.drawRoundRect(0,0,getWidth()-1,getHeight()-1,getHeight(),getHeight());
            g2.dispose();
            super.paintComponent(g);
        }
    }

    private boolean validatePassword() {
        String current = new String(pwtCurrent.getPassword());
        String newPass = new String(pwtNew.getPassword());
        String confirm = new String(pwtConfirm.getPassword());

        if (current.isEmpty() || newPass.isEmpty() || confirm.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        String strongPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&]).{8,}$";
        if (!newPass.matches(strongPattern)) {
            JOptionPane.showMessageDialog(this, "Password must be at least 8 characters with upper, lower, number and special char.", "Weak Password", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (!newPass.equals(confirm)) {
            JOptionPane.showMessageDialog(this, "Passwords do not match!", "Mismatch", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        String savedPassword = "Aung1234@@"; // dummy
        if (!current.equals(savedPassword)) {
            JOptionPane.showMessageDialog(this, "Current password is incorrect!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private void openHomePage() { new HomePage().setVisible(true); this.dispose(); }
    private void openDepositPage() { new DepositPage().setVisible(true); this.dispose(); }
    private void openWithdrawPage() { new WithdrawPage().setVisible(true); this.dispose(); }
    private void openTransferPage() { new TransferPage().setVisible(true); this.dispose(); }
    private void openTransactionsPage() {
        SwingUtilities.invokeLater(() -> {
            JFrame transactionsFrame = new JFrame("Bankify - Transactions");
            transactionsFrame.setSize(1200, 800);
            CardLayout cardLayout = new CardLayout();
            JPanel contentPanel = new JPanel(cardLayout);
            TransactionsPage transactionsPage = new TransactionsPage(cardLayout, contentPanel, transactionsFrame);
            contentPanel.add(transactionsPage, "Transactions");
            transactionsFrame.add(contentPanel);
            transactionsFrame.setLocationRelativeTo(this);
            transactionsFrame.setVisible(true);
            this.dispose();
        });
    }
    private void openSettingsPage() { new MainSettings().setVisible(true); this.dispose(); }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ChangePassword().setVisible(true));
    }
}