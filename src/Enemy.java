import java.awt.*;

public class Enemy {
    int x, y, size = 40;
    int speed = 2;
    boolean movingRight = true;
    
    public Enemy(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public void update() {
        if (movingRight) {
            x += speed;
            if (x > 700) movingRight = false;
        } else {
            x -= speed;
            if (x < 0) movingRight = true;
        }
    }
    
    public void draw(Graphics2D g2) {
        g2.setColor(Color.black);
        g2.fillOval(x, y, size, size);
    }
    
    public Rectangle getBounds() {
        return new Rectangle(x, y, size, size);
    }
}
