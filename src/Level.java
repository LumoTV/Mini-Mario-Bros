import java.awt.*;
import java.util.ArrayList;

public class Level {
    int levelNumber;
    ArrayList<Platform> platforms = new ArrayList<>();
    ArrayList<Enemy> enemies = new ArrayList<>();
    ArrayList<Coin> coins = new ArrayList<>();
    
    public Level(int levelNumber) {
        this.levelNumber = levelNumber;
        generate();
    }
    
    public void generate() {
        platforms.add(new Platform(0, 500, 768, 50)); // Sol

        if (levelNumber >= 1) {
            platforms.add(new Platform(150, 400, 100, 20));
            coins.add(new Coin(170, 370));
            enemies.add(new Enemy(300, 460));
        }
        
        if (levelNumber >= 2) {
            platforms.add(new Platform(350, 350, 120, 20));
            coins.add(new Coin(370, 320));
        }
        
        if (levelNumber >= 3) {
            platforms.add(new Platform(500, 300, 120, 20));
            coins.add(new Coin(520, 270));
            enemies.add(new Enemy(600, 460));
        }
    }
    
    public void draw(Graphics2D g2) {
        for (Platform platform : platforms) {
            platform.draw(g2);
        }
        for (Coin coin : coins) {
            coin.draw(g2);
        }
        for (Enemy enemy : enemies) {
            enemy.draw(g2);
        }
        
        g2.setColor(Color.black);
        g2.drawString("Level " + levelNumber, 20, 20);
    }
}
