import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Level> levels;
    private int currentLevelIndex;

    public Game() {
        levels = new ArrayList<>();
        currentLevelIndex = 0;

        // Générer 50 niveaux
        for (int i = 0; i < 50; i++) {
            Level level = new Level(i + 1);
            levels.add(level);
        }
    }

    // Passer au niveau suivant
    public void nextLevel() {
        if (currentLevelIndex < levels.size() - 1) {
            currentLevelIndex++;
            loadLevel(currentLevelIndex);
        }
    }

    // Charger un niveau
    public void loadLevel(int levelIndex) {
        Level level = levels.get(levelIndex);
        // Charger et initialiser les éléments du niveau
    }

    // Mise à jour du jeu
    public void update() {
        // Mettre à jour la logique du jeu (mouvement, interactions, etc.)
    }

    // Rendu du jeu
    public void render(Graphics2D g2) {
        levels.get(currentLevelIndex).draw(g2);
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.loadLevel(0); // Charger le premier niveau
        // Démarrer le jeu
    }
}
