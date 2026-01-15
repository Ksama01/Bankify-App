package bankify;
import javax.swing.*;
import java.awt.*;

public class LoadingScreen extends JFrame {
    private static final long serialVersionUID = 1L;
    private JProgressBar progress;

    public LoadingScreen() {
        setTitle("Loading...");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setUndecorated(true); // border မပါအောင်

        // Gradient background
        JPanel panel = new GradientPanel();
        panel.setLayout(null);
        setContentPane(panel);

        // Logo
        JLabel lblLogo = new JLabel();
        lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
        lblLogo.setBounds(180, 90, 265, 195);
        lblLogo.setIcon(new ImageIcon(LoadingScreen.class.getResource("/Resources/loadinglogo.jpg")));
        panel.add(lblLogo);

        // App name
        //JLabel lblTitle = new JLabel("Bankify Bank");
       // lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        //lblTitle.setForeground(new Color(30, 127, 179));
       // lblTitle.setFont(new Font("Tw Cen MT", Font.BOLD | Font.ITALIC, 28));
        //lblTitle.setBounds(200, 250, 200, 30);
       // panel.add(lblTitle);

        // Progress bar (Only one — with text inside)
        progress = new JProgressBar();
        progress.setBounds(50, 320, 500, 20);
        progress.setForeground(new Color(30, 127, 179));
        progress.setBackground(new Color(0,0,0));
        progress.setFont(new Font("Tw Cen MT", Font.BOLD, 17));
        progress.setStringPainted(true);
        progress.setBorderPainted(false);
        progress.setOpaque(false);
        panel.add(progress);

        setVisible(true);
        fill();
    }

    class GradientPanel extends JPanel {
        /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
      g.setColor(new Color(255, 255, 255)); // ← background color
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    }


    // Loading animation and redirect
    public void fill() {
        int i = 0;
        try {
            while (i <= 100) {
                progress.setValue(i);
                progress.setString("Loading... " + i + "%");
                Thread.sleep(35); // speed
                i++;
            }
            dispose(); // close loading screen
            new Login().setVisible(true); // open Login page
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new LoadingScreen();
    }
}