import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class Main extends JPanel implements ActionListener, KeyListener  {

    private int startAngle = 45;
    private int endAngle = 270;
    private boolean mouthOpen = true;

    private int pacmanX = 10;
    private int pacmanY = 15;

    private final int boxSize = 25;

    private String pacDir = "xPlus";

    private int score = 0;


    private boolean inGame = false;

    private int allPoints = 0;

    private boolean firstRunBoard = false;

    private int huntMode = 80;

    private int hearts = 3;

    private final int[][] boardBack = {
            { 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 },
            { 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2 },
            { 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2 },
            { 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2 },
            { 2, 1, 1, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 1, 1, 2 },
            { 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2 },
            { 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2 },
            { 2, 1, 2, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 2, 1, 2 },
            { 2, 2, 2, 1, 2, 1, 2, 2, 2, 0, 0, 2, 2, 2, 1, 2, 1, 2, 2, 2 },
            { 0, 1, 1, 1, 2, 1, 2, 0, 0, 0, 0, 0, 0, 2, 1, 2, 1, 1, 1, 0 },
            { 0, 1, 1, 1, 2, 1, 2, 0, 0, 0, 0, 0, 0, 2, 1, 2, 1, 1, 1, 0 },
            { 2, 2, 2, 1, 2, 1, 2, 0, 0, 0, 0, 0, 0, 2, 1, 2, 1, 2, 2, 2 },
            { 2, 1, 2, 1, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 1, 2, 1, 2 },
            { 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2 },
            { 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2 },
            { 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2 },
            { 2, 1, 1, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 1, 1, 2 },
            { 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2 },
            { 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2 },
            { 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 }
    };

    private int[][] board = {
            { 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 },
            { 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2 },
            { 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2 },
            { 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2 },
            { 2, 1, 1, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 1, 1, 2 },
            { 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2 },
            { 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2 },
            { 2, 1, 2, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 2, 1, 2 },
            { 2, 2, 2, 1, 2, 1, 2, 2, 2, 0, 0, 2, 2, 2, 1, 2, 1, 2, 2, 2 },
            { 0, 1, 1, 1, 2, 1, 2, 0, 0, 0, 0, 0, 0, 2, 1, 2, 1, 1, 1, 0 },
            { 0, 1, 1, 1, 2, 1, 2, 0, 0, 0, 0, 0, 0, 2, 1, 2, 1, 1, 1, 0 },
            { 2, 2, 2, 1, 2, 1, 2, 0, 0, 0, 0, 0, 0, 2, 1, 2, 1, 2, 2, 2 },
            { 2, 1, 2, 1, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 1, 2, 1, 2 },
            { 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2 },
            { 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2 },
            { 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2 },
            { 2, 1, 1, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 1, 1, 2 },
            { 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2 },
            { 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2 },
            { 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 }
    };

    private Ghost[] ghosts = new Ghost[0];

    public Main() {
        Timer timer = new Timer(200, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(Color.BLACK);

        DrawGameBoard(g);

        g.setColor(Color.YELLOW);

        if (huntMode < 80) {
            huntMode++;
            g.setColor(Color.blue);
        }

        Graphics2D g2d = (Graphics2D) g.create();

        // PacMan Draw
        drawPacMan(g2d);

        if (!inGame) {
            drawStartText(g);
        }

        // Ghost Draw
        for (Ghost ghost : ghosts) {
            drawGhost(g, ghost.ghostX, ghost.ghostY);
        }

        // Text Draw
        drawScore(g);
        drawHearts(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!inGame) return;

        if (score == allPoints) resetGame();

        int maxGhosts = 4;
        if (ghosts.length < maxGhosts) addGhost();

        for (int i = 0; i < ghosts.length; i++) {
            if (pacmanX == ghosts[i].ghostX && pacmanY == ghosts[i].ghostY) removeLife(i);
        }

        setPacMan();

        Random random = new Random();

        for (int i = 0; i < ghosts.length; i++) {
            setGhostMovement(i);

            setGhostDir(random.nextInt(4), i);
        }

        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT -> pacDir = "xMinus";
            case KeyEvent.VK_RIGHT -> pacDir = "xPlus";
            case KeyEvent.VK_UP -> pacDir = "yMinus";
            case KeyEvent.VK_DOWN -> pacDir = "yPlus";
            case KeyEvent.VK_SPACE -> inGame = true;
        }

        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }

    public void setPacMan() {
        if (mouthOpen) {
            startAngle = 45;
            endAngle = 270;
        } else {
            startAngle = 0;
            endAngle = 360;
        }
        mouthOpen = !mouthOpen;

        int resX = pacmanX;
        int resY = pacmanY;

        boolean allow = true;

        switch (pacDir) {
            case "xPlus" -> {
                resX += 1;
                if (resX > 19) resX = 0;
            }
            case "xMinus" -> {
                resX -= 1;
                if (resX < 0) resX = 19;
            }
            case "yPlus" -> {
                resY += 1;
                if (resY > 19) resY = 0;
            }
            case "yMinus" -> {
                resY -= 1;
                if (resY < 0) resY = 19;
            }
        }

        if (board[resY][resX] == 1) {
            board[resY][resX] = 0;
            score++;
        } else if (board[resY][resX] == 2) {
            allow = false;
        } else if (board[resY][resX] == 3) {
            board[resY][resX] = 0;
            huntMode = 0;
        }


        if (allow) {
            pacmanX = resX;
            pacmanY = resY;
        }
    }

    public void setGhostMovement(int i) {
        int resX = ghosts[i].ghostX;
        int resY = ghosts[i].ghostY;

        boolean allow = true;

        switch (ghosts[i].ghostDir) {
            case "xPlus" -> {
                resX += 1;
                if (resX > 19) resX = 0;

                if (board[resY][resX] == 2) {
                    ghosts[i].ghostDir = "yMinus";

                    allow = false;
                    setGhostMovement(i);
                }
            }
            case "xMinus" -> {
                resX -= 1;
                if (resX < 0) resX = 19;

                if (board[resY][resX] == 2) {
                    ghosts[i].ghostDir = "yPlus";

                    allow = false;
                    setGhostMovement(i);
                }
            }
            case "yPlus" -> {
                resY += 1;
                if (resY > 19) resY = 0;

                if (board[resY][resX] == 2) {
                    ghosts[i].ghostDir = "xPlus";

                    allow = false;
                    setGhostMovement(i);
                }
            }
            case "yMinus" -> {
                resY -= 1;
                if (resY < 0) resY = 19;

                if (board[resY][resX] == 2) {
                    ghosts[i].ghostDir = "xMinus";

                    allow = false;
                    setGhostMovement(i);
                }
            }
        }


        if (allow) {
            ghosts[i].ghostX = resX;
            ghosts[i].ghostY = resY;
        }
    }

    public void setGhostDir(int dirInt, int i) {
        switch (dirInt) {
            case 0 -> ghosts[i].ghostDir = "xPlus";
            case 1 -> ghosts[i].ghostDir = "xMinus";
            case 2 -> ghosts[i].ghostDir = "yPlus";
            case 3 -> ghosts[i].ghostDir = "yMinus";
        }
    }

    public void drawGhost(Graphics g, int x, int y) {
        g.setColor(Color.red);

        if (huntMode < 80) {
            huntMode++;
            g.setColor(Color.white);
        }
        g.fillOval(x * boxSize, y * boxSize, boxSize, boxSize);
    }

    public void drawPacMan(Graphics2D g2d) {
        g2d.translate((pacmanX * boxSize) + boxSize / 2, (pacmanY * boxSize) + boxSize / 2);

        switch (pacDir) {
            case "xPlus" -> g2d.rotate(Math.toRadians(0));
            case "xMinus" -> g2d.rotate(Math.toRadians(180));
            case "yPlus" -> g2d.rotate(Math.toRadians(90));
            case "yMinus" -> g2d.rotate(Math.toRadians(270));
        }

        g2d.translate(-boxSize / 2, -boxSize / 2);

        g2d.fillArc(1, 1, boxSize, boxSize, startAngle, endAngle);

        g2d.dispose();
    }

    public void drawStartText(Graphics g) {
        Font font = new Font("Arial", Font.BOLD, 20);
        g.setFont(font);
        g.setColor(Color.yellow);
        String gameText = "Drücke Leertaste um zu Starten!";
        g.drawString(gameText, 100, 135);
    }
    public void DrawGameBoard(Graphics g) {
        g.setColor(Color.WHITE);

        int lengthY = 20;
        for (int curBoxY = 0; curBoxY < lengthY; curBoxY++) {
            int lengthX = 20;
            for (int curBoxX = 0; curBoxX < lengthX; curBoxX++) {
                if (board[curBoxY][curBoxX] == 1) {
                    g.setColor(Color.WHITE);
                    g.fillOval(curBoxX * boxSize + 10, curBoxY * boxSize + 10, 5, 5);

                    if (!firstRunBoard) {
                        allPoints++;
                    }
                } else if (board[curBoxY][curBoxX] == 2) {
                    g.setColor(Color.BLUE);
                    g.fillRect(curBoxX * boxSize, curBoxY * boxSize, boxSize, boxSize);
                } else if (board[curBoxY][curBoxX] == 3) {
                    g.setColor(Color.BLUE);
                    g.fillOval(curBoxX * boxSize + 3, curBoxY * boxSize + 3, 15, 15);
                }
            }
        }

        firstRunBoard = true;
    }

    public void resetGame() {
        inGame = false;
        score = 0;
        hearts = 3;
        board = boardBack;

        pacDir = "xPlus";

        pacmanX = 10;
        pacmanY = 15;

        ghosts = new Ghost[0];

        repaint();
    }

    public void removeLife(int i) {
        if (huntMode < 80) {
            removeGhost(i);
            return;
        }
        hearts--;

        if (hearts <= 0) resetGame();

        pacDir = "xPlus";

        pacmanX = 10;
        pacmanY = 15;
    }

    public void removeGhost(int index) {
        if (index < 0 || index >= ghosts.length) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }

        ghosts = IntStream.range(0, ghosts.length)
                .filter(i -> i != index)
                .mapToObj(i -> ghosts[i])
                .toArray(Ghost[]::new);
    }

    public void addGhost() {
        Ghost[] newGhosts = Arrays.copyOf(ghosts, ghosts.length + 1);

        newGhosts[newGhosts.length - 1] = new Ghost();

        ghosts = newGhosts;
    }

    public void drawHearts(Graphics g) {
        Font font = new Font("Arial", Font.BOLD, 30);
        g.setFont(font);
        g.setColor(Color.red);
        g.drawString("• ".repeat(Math.max(0, hearts)), 30, 495);
    }
    public void drawScore(Graphics g) {
        Font font = new Font("Arial", Font.BOLD, 15);
        g.setFont(font);
        g.setColor(Color.white);
        String gameText = "Score: " + score;
        g.drawString(gameText, 425, 490);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Pacman Board");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(515, 535);
        Main pacmanBoard = new Main();
        frame.add(pacmanBoard);
        frame.setVisible(true);

        frame.setFocusable(true);
        frame.addKeyListener(pacmanBoard);
    }
}
