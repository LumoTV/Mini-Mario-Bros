import java.awt.*;

public class Player {
    GamePanel gp;
    KeyHandler keyHandler;
    int x, y;
    int speed = 4;
    int size = 40;
    
    boolean jumping = false;
    double velocityY = 0;
    double gravity = 0.5;
    double jumpStrength = -10;
    
    public Player(GamePanel gp, KeyHandler keyHandler) {
        this.gp = gp;
        this.keyHandler = keyHandler;
        resetPosition();
    }
    
    public void resetPosition() {
        x = 0;
        y = gp.screenHeight - gp.tileSize;
    }

    public void update() {
        if (keyHandler.rightPressed) x += speed;
        if (keyHandler.leftPressed) x -= speed;
        
        if (keyHandler.upPressed && !jumping) {
            velocityY = jumpStrength;
            jumping = true;
        }
        
        velocityY += gravity;
        y += (int) velocityY;
        
        for (Platform platform : gp.levels.get(gp.currentLevelIndex).platforms) {
            if (getBounds().intersects(platform.getBounds())) {
                if (velocityY > 0) {
                    y = platform.y - size;
                    velocityY = 0;
                    jumping = false;
                }
            }
        }
        
        for (Coin coin : gp.levels.get(gp.currentLevelIndex).coins) {
            if (!coin.collected && getBounds().intersects(coin.getBounds())) {
                coin.collected = true;
                gp.score += 10;
            }
        }
        
        for (Enemy enemy : gp.levels.get(gp.currentLevelIndex).enemies) {
            if (getBounds().intersects(enemy.getBounds())) {
                System.out.println("Game Over !");
                System.exit(0);
            }
        }
    }

    public void draw(Graphics2D g2) {
        if (jumping) {
            g2.setColor(Color.orange);
        } else {
            g2.setColor(Color.red);
        }
        g2.fillRect(x, y, size, size);
    }
    
    public Rectangle getBounds() {
        return new Rectangle(x, y, size, size);
    }
}
