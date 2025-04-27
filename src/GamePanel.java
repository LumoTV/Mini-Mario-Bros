import javax.swing.JPanel;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable {
    
    final int tileSize = 48;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    Thread gameThread;
    KeyHandler keyHandler = new KeyHandler(this);
    Player player;
    ArrayList<Level> levels = new ArrayList<>();
    int currentLevelIndex = 0;
    int score = 0;
    Sound music = new Sound();
    String gameState = "menu"; // "menu", "play", "win"
    
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.cyan);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
        
        player = new Player(this, keyHandler);
        
        for (int i = 1; i <= 8; i++) {
            levels.add(new Level(i));
        }
        
        music.setFile("assets/background.wav");
        music.play();
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / 60;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }
    
    public void update() {
        if (gameState.equals("play")) {
            player.update();
            for (Enemy enemy : levels.get(currentLevelIndex).enemies) {
                enemy.update();
            }
            if (player.x > screenWidth) {
                nextLevel();
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        
        if (gameState.equals("menu")) {
            g2.setColor(Color.black);
            g2.setFont(new Font("Arial", Font.BOLD, 40));
            g2.drawString("Mini Mario Bros", 200, 200);
            g2.setFont(new Font("Arial", Font.PLAIN, 30));
            g2.drawString("Press ENTER to continue", 180, 300);
            g2.drawString("Press ESC to quit", 180, 350);
            
        } else if (gameState.equals("play")) {
            levels.get(currentLevelIndex).draw(g2);
            player.draw(g2);
            g2.setColor(Color.black);
            g2.drawString("Score: " + score, 650, 20);
            
        } else if (gameState.equals("win")) {
            g2.setColor(Color.green);
            g2.setFont(new Font("Arial", Font.BOLD, 50));
            g2.drawString("Congratulation ! You finish the game !", 120, 250);
            g2.setFont(new Font("Arial", Font.PLAIN, 30));
            g2.drawString("Score final : " + score, 250, 350);
        }
        
        g2.dispose();
    }
    
    private void nextLevel() {
        if (currentLevelIndex < levels.size() - 1) {
            currentLevelIndex++;
            player.resetPosition();
        } else {
            gameState = "win";
        }
    }
}
