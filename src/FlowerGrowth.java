import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FlowerGrowth extends JPanel implements ActionListener {

    private double petalLength = 30;
    private final double maxPetallength = 120;
    private final int centerRadius = 20;
    private final Timer timer;

    public FlowerGrowth() {
        setBackground(new Color(172, 216, 230));
        timer = new Timer(75, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 =  (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int xCenter = getWidth() / 2;
        int yCenter = getHeight() / 2;

        g2.setColor(Color.yellow);

        g2.fillOval(xCenter - centerRadius, yCenter - centerRadius, 2*centerRadius, 2*centerRadius);

        g2.setColor(Color.pink);
        int petalCount = 6;

        for(int i = 0; i < petalCount; i++) {
            double angle = 2 * Math.PI / petalCount * i;
            int x = (int) (xCenter + Math.cos(angle) * centerRadius);
            int y = (int) (yCenter + Math.sin(angle) * centerRadius);

            int petalWidth = 30;
            int petalHeight = (int) petalLength;

            g2.translate(x, y);
            g2.rotate(angle);
            g2.fillOval(-petalWidth / 2, -petalHeight, petalWidth, petalHeight);
            g2.rotate(-angle);
            g2.translate(-x, -y);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(petalLength < maxPetallength) {
            petalLength += 1.5;
            repaint();
        }
        else {
            timer.stop();
        }
    }

    public static void  main(String[] args){
        JFrame frame = new JFrame("Petal Growth Animation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(540, 400);
        frame.add(new FlowerGrowth());
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
