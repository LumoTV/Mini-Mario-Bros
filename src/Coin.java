import java.awt.*;

public class Coin {
    int x, y, size = 20;
    boolean collected = false;
    
    public Coin(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public void draw(Graphics2D g2) {
        if (!collected) {
            g2.setColor(Color.yellow);
            g2.fillOval(x, y, size, size);
        }
    }
    
    public Rectangle getBounds() {
        return new Rectangle(x, y, size, size);
    }
}
