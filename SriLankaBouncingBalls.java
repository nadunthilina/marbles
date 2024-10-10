import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SriLankaBouncingBalls extends JPanel implements ActionListener {

    private final int BALL_SIZE = 50;   // Size of each ball
    private final String text = "SRI LANKA";
    private int[] yPositions = new int[text.length()];      // Y positions for each letter (ball)
    private int[] yDirections = new int[text.length()];     // Directions for each ball
    private final int[] bouncesLeft = new int[text.length()];  // Bounces left before stopping

    private Timer timer;
    private final int HOLD_POSITION = 250;  // Y position where balls will hold (middle of the page)

    public SriLankaBouncingBalls() {
        timer = new Timer(10, this);  // Timer to update the animation every 10ms
        timer.start();               // Start the timer

        // Initialize positions, directions, and bounce counts for each letter (ball)
        for (int i = 0; i < text.length(); i++) {
            yPositions[i] = (int)(Math.random() * 300);  // Random start position
            yDirections[i] = 2 + (int)(Math.random() * 3); // Random speed between 2 and 5
            bouncesLeft[i] = 10 + (int)(Math.random() * 10);  // Random bounce count between 10 and 20
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(new Font("Arial", Font.BOLD, 30));   // Set font for the text on the balls

        // Loop to draw each letter as a ball
        for (int i = 0; i < text.length(); i++) {
            int xPosition = 50 + i * (BALL_SIZE + 20);  // X position for each letter

            // Draw ball (circle)
            g.setColor(Color.ORANGE);
            g.fillOval(xPosition, yPositions[i], BALL_SIZE, BALL_SIZE);

            // Draw letter on top of the ball
            g.setColor(Color.BLACK);
            g.drawString(String.valueOf(text.charAt(i)), xPosition + 15, yPositions[i] + 35);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Update the position of each ball
        for (int i = 0; i < text.length(); i++) {
            // Only move the ball if it hasn't reached the holding position
            if (bouncesLeft[i] > 0) {
                yPositions[i] += yDirections[i];

                // Bounce the ball if it hits the top or bottom of the window
                if (yPositions[i] <= 0 || yPositions[i] >= getHeight() - BALL_SIZE) {
                    yDirections[i] = -yDirections[i];  // Reverse direction
                    bouncesLeft[i]--;  // Decrease the number of bounces left
                }
            } else {
                // Gradually move the ball to the HOLD_POSITION and stop there
                if (yPositions[i] < HOLD_POSITION) {
                    yPositions[i] += 2;  // Move down slowly
                } else if (yPositions[i] > HOLD_POSITION) {
                    yPositions[i] -= 2;  // Move up slowly
                }
            }
        }

        repaint();  // Redraw the panel with the updated ball positions
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("SRI LANKA Bouncing Balls Animation with Hold");
        SriLankaBouncingBalls animation = new SriLankaBouncingBalls();
        frame.add(animation);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
