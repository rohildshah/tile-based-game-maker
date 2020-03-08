import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;


public class Main extends JPanel implements KeyListener{

    final static Color BACKGROUND_COLOR = new Color(213, 224, 254);

    final static Color WALL_COLOR = new Color(101, 99, 113);

    final static Color PLAYER_COLOR = new Color(255, 115, 105);


    final static int TILE_SIZE = 50;
    final static int SCREEN_SIZE = 550;

    final static int SQUARES = SCREEN_SIZE/TILE_SIZE;
    final static int MIDDLE_POSITION = SQUARES/2;


    Map map = new Map();

    int topLeftRow;
    int topLeftCol;
    int botRightRow;
    int botRightCol;

    boolean moveLeft;
    boolean moveRight;
    boolean moveUp;
    boolean moveDown;

    int fullWidth;
    int fullLength;

    int amountOfPaints = 0;

    int relPlayerX = 1;
    int relPlayerY = 1;

    int absPlayerX = 1;
    int absPlayerY = 1;


    public void paintComponent(Graphics win) {
        ArrayList<ArrayList<String>> obstacles = map.getObstacles();

        if (amountOfPaints % 2 == 0) {
            boolean pCanLeft = obstacles.get(absPlayerY).get(absPlayerX - 1).equals(".");
            boolean pCanRight = obstacles.get(absPlayerY).get(absPlayerX + 1).equals(".");
            boolean pCanUp = obstacles.get(absPlayerY - 1).get(absPlayerX).equals(".");
            boolean pCanDown = obstacles.get(absPlayerY + 1).get(absPlayerX).equals(".");


            if (moveLeft && this.topLeftCol > 0 && pCanLeft && this.relPlayerX == MIDDLE_POSITION) {
                topLeftCol--;
                botRightCol--;
                absPlayerX--;
            } else if (moveRight && this.botRightCol < fullWidth && pCanRight && this.relPlayerX == MIDDLE_POSITION) {
                topLeftCol++;
                botRightCol++;
                absPlayerX++;
            } else if (moveDown && this.botRightRow < fullLength && pCanDown && this.relPlayerY == MIDDLE_POSITION) {
                topLeftRow++;
                botRightRow++;
                absPlayerY++;
            } else if (moveUp && this.topLeftRow > 0 && pCanUp && this.relPlayerY == MIDDLE_POSITION) {
                topLeftRow--;
                botRightRow--;
                absPlayerY--;

            } else if (moveLeft && this.topLeftCol == 0 && pCanLeft) {
                relPlayerX--;
                absPlayerX--;
            } else if (moveRight && this.botRightCol == fullWidth && pCanRight) {
                relPlayerX++;
                absPlayerX++;
            } else if (moveDown && this.botRightRow == fullLength && pCanDown) {
                relPlayerY++;
                absPlayerY++;
            } else if (moveUp && this.topLeftRow == 0 && pCanUp) {
                relPlayerY--;
                absPlayerY--;

            } else if (moveLeft && this.absPlayerX > fullWidth - (MIDDLE_POSITION +1) && pCanLeft) {
                relPlayerX--;
                absPlayerX--;
            } else if (moveRight && this.absPlayerX < MIDDLE_POSITION && pCanRight) {
                relPlayerX++;
                absPlayerX++;
            } else if (moveDown && this.absPlayerY < MIDDLE_POSITION && pCanDown) {
                relPlayerY++;
                absPlayerY++;
            } else if (moveUp && this.absPlayerY > fullLength - (MIDDLE_POSITION +1) && pCanUp) {
                relPlayerY--;
                absPlayerY--;
            }

        }

        int relLength = 0;
        for (int absLength = topLeftRow; absLength < botRightRow; absLength++) {
            int relWidth = 0;
            for (int absWidth = topLeftCol; absWidth < botRightCol; absWidth++) {
                if (obstacles.get(absLength).get(absWidth).equals("#")) {
                    win.setColor(WALL_COLOR);
                    win.fillRect(TILE_SIZE*relWidth, TILE_SIZE*relLength, TILE_SIZE, TILE_SIZE);
                }
                relWidth++;
            }
            relLength++;
        }

        win.setColor(PLAYER_COLOR);
        win.fillRect(TILE_SIZE* relPlayerX, TILE_SIZE* relPlayerY, TILE_SIZE, TILE_SIZE);

        amountOfPaints++;

    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 37) {
            moveLeft = true;
        } else if (e.getKeyCode() == 39) {
            moveRight = true;
        } else if (e.getKeyCode() == 40) {
            moveDown = true;
        } else if (e.getKeyCode() == 38) {
            moveUp = true;
        }
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == 37) {
            moveLeft = false;
        } else if (e.getKeyCode() == 39) {
            moveRight = false;
        } else if (e.getKeyCode() == 40) {
            moveDown = false;
        } else if (e.getKeyCode() == 38) {
            moveUp = false;
        }
    }

    public void keyTyped(KeyEvent e) {}


    public Main() {
        Levels.tester(map);
        map.printAll();

        fullLength = map.getLength();
        fullWidth = map.getWidth();

        topLeftRow = 0;
        topLeftCol = 0;

        botRightRow = Math.min(fullLength, SQUARES);

        botRightCol = Math.min(fullWidth, SQUARES);

        Timer timer = new Timer(30, arg0 -> repaint());
        timer.start();
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main panel = new Main();
            panel.setBackground(BACKGROUND_COLOR);

            panel.addKeyListener(panel);
            panel.setFocusable(true);

            JFrame frame = new JFrame("TileBasedGameMaker");
            frame.setSize(SCREEN_SIZE, SCREEN_SIZE+22);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(panel, BorderLayout.CENTER);
            frame.setVisible(true);
        });
    }
}
