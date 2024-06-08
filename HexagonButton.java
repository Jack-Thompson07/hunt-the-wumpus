import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.util.HashSet;
import java.util.Set;

public class HexagonButton extends JButton implements ActionListener {
  ////////////////////////
  // Properties
  ////////////////////////
    private Set<Integer> unboldedSides;
    private Color backgroundColor;
    private Color clickColor;
    private double hexagonRadius;
    private boolean isPressed;
    private int[] cords;
    private GameController gc;
    
    ////////////////////////
    // Constructor
    ////////////////////////
    public HexagonButton(String text, int[] unboldedSides, Color backgroundColor, double hexagonRadius, int[] cords, GameController gc) {
        super(text);
        this.cords = cords;
        this.gc = gc;
        addActionListener(this);
        this.unboldedSides = new HashSet<>();
        for (int side : unboldedSides) {
            this.unboldedSides.add(side);
        }
        this.backgroundColor = backgroundColor;
        this.hexagonRadius = hexagonRadius;
        this.clickColor = backgroundColor.darker();
        this.isPressed = false;

        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setPreferredSize(new Dimension((int) (2 * hexagonRadius), (int) (2 * hexagonRadius)));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                isPressed = true;
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                isPressed = false;
                repaint();
            }
        });
    }
  ////////////////////////
  // Methods
  ////////////////////////
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();
        Path2D hexagon = createHexagon(width, height);

        g2.setColor(isPressed ? clickColor : backgroundColor);
        g2.fill(hexagon);

        FontMetrics metrics = g2.getFontMetrics(getFont());
        int x = (int) ((width - metrics.stringWidth(getText())) / 2.0);
        int y = (int) ((height - metrics.getHeight()) / 2.0) + metrics.getAscent();
        g2.setColor(getForeground());
        g2.drawString(getText(), x, y);

        g2.dispose();
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();
        int[] xPoints = new int[6];
        int[] yPoints = new int[6];
        calculateHexagonPoints(xPoints, yPoints, width, height);

        for (int i = 0; i < 6; i++) {
            if (!unboldedSides.contains(i)) {
                int next = (i + 5) % 6;//
                g2.setStroke(new BasicStroke(5));
                g2.drawLine(xPoints[(i + 4) % 6], yPoints[(i + 4) % 6], xPoints[next], yPoints[next]);
            }
        }

        g2.dispose();
    }

    private Path2D createHexagon(int width, int height) {
        int[] xPoints = new int[6];
        int[] yPoints = new int[6];
        calculateHexagonPoints(xPoints, yPoints, width, height);

        Path2D hexagon = new Path2D.Double();
        hexagon.moveTo(xPoints[0], yPoints[0]);
        for (int i = 1; i < 6; i++) {
            hexagon.lineTo(xPoints[i], yPoints[i]);
        }
        hexagon.closePath();

        return hexagon;
    }

    private void calculateHexagonPoints(int[] xPoints, int[] yPoints, int width, int height) {
        int centerX = width / 2;
        int centerY = height / 2;

        for (int i = 0; i < 6; i++) {
            double angle = Math.toRadians(60 * i);
            xPoints[i] = (int) (centerX + hexagonRadius * Math.cos(angle));
            yPoints[i] = (int) (centerY + hexagonRadius * Math.sin(angle));
        }
    }

    @Override
    public Dimension getPreferredSize() {
        int size = (int) (2 * hexagonRadius + 1);
        return new Dimension(size, size);
    }

    @Override
    public boolean contains(int x, int y) {
        int[] xPoints = new int[6];
        int[] yPoints = new int[6];
        calculateHexagonPoints(xPoints, yPoints, getWidth(), getHeight());

        Path2D hexagon = new Path2D.Double();
        hexagon.moveTo(xPoints[0], yPoints[0]);
        for (int i = 1; i < 6; i++) {
            hexagon.lineTo(xPoints[i], yPoints[i]);
        }
        hexagon.closePath();

        return hexagon.contains(new Point2D.Double(x, y));
    }

    @Override
      public void actionPerformed(ActionEvent e){
        System.out.println("Hex button pressed");
        this.gc.movePlayer(this.cords);
      }
}