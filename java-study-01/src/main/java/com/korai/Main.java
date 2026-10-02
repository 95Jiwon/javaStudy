package com.korai;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Main extends JPanel implements ActionListener {
    private static final int BOARD_WIDTH = 10;
    private static final int BOARD_HEIGHT = 20;
    private static final int CELL_SIZE = 30; // 블록 1칸의 크기(픽셀)

    private Timer timer;
    private boolean isGameOver = false;
    private int score = 0;

    private int[][] board = new int[BOARD_HEIGHT][BOARD_WIDTH];

    // 테트로미노 7가지 모양
    private static final int[][][] SHAPES = {
            {{0,0,0,0}, {1,1,1,1}, {0,0,0,0}, {0,0,0,0}}, // I
            {{1,1,0,0}, {1,1,0,0}, {0,0,0,0}, {0,0,0,0}}, // O
            {{0,1,0,0}, {1,1,1,0}, {0,0,0,0}, {0,0,0,0}}, // T
            {{0,0,1,0}, {1,1,1,0}, {0,0,0,0}, {0,0,0,0}}, // L
            {{1,0,0,0}, {1,1,1,0}, {0,0,0,0}, {0,0,0,0}}, // J
            {{0,1,1,0}, {1,1,0,0}, {0,0,0,0}, {0,0,0,0}}, // S
            {{1,1,0,0}, {0,1,1,0}, {0,0,0,0}, {0,0,0,0}}  // Z
    };

    // 블록 색상
    private static final Color[] COLORS = {
            Color.CYAN, Color.YELLOW, Color.MAGENTA,
            Color.ORANGE, Color.BLUE, Color.GREEN, Color.RED
    };

    private int[][] currentBlock;
    private Color currentColor;
    private int blockX = 3;
    private int blockY = 0;

    public Main() {
        setFocusable(true);
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(BOARD_WIDTH * CELL_SIZE + 150, BOARD_HEIGHT * CELL_SIZE));

        // 키보드 이벤트 리스너
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                // 게임 오버 상태일 때 R 키를 누르면 재시작
                if (isGameOver) {
                    if (e.getKeyCode() == KeyEvent.VK_R) {
                        resetGame();
                    }
                    return;
                }

                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        if (isValidMove(currentBlock, blockX - 1, blockY)) blockX--;
                        break;
                    case KeyEvent.VK_RIGHT:
                        if (isValidMove(currentBlock, blockX + 1, blockY)) blockX++;
                        break;
                    case KeyEvent.VK_DOWN:
                        moveDown();
                        break;
                    case KeyEvent.VK_UP: // 회전
                        int[][] rotated = rotateBlock(currentBlock);
                        if (isValidMove(rotated, blockX, blockY)) currentBlock = rotated;
                        break;
                    case KeyEvent.VK_SPACE: // 하드 드롭 (바닥까지 바로 내리기)
                        while (isValidMove(currentBlock, blockX, blockY + 1)) {
                            blockY++;
                        }
                        placeBlock();
                        break;
                }
                repaint(); // 화면 다시 그리기
            }
        });

        spawnNewBlock();

        // 0.5초(500ms)마다 주기적으로 블록을 아래로 떨어뜨리는 타이머 실행
        timer = new Timer(500, this);
        timer.start();
    }

    // 게임 재시작 (초기화)
    private void resetGame() {
        board = new int[BOARD_HEIGHT][BOARD_WIDTH];
        score = 0;
        isGameOver = false;
        spawnNewBlock();
        timer.start();
        repaint();
    }

    // 타이머 이벤트 (실시간 낙하)
    @Override
    public void actionPerformed(ActionEvent e) {
        if (!isGameOver) {
            moveDown();
            repaint();
        }
    }

    private void moveDown() {
        if (isValidMove(currentBlock, blockX, blockY + 1)) {
            blockY++;
        } else {
            placeBlock();
        }
    }

    private void spawnNewBlock() {
        Random random = new Random();
        int index = random.nextInt(SHAPES.length);
        currentBlock = cloneBlock(SHAPES[index]);
        currentColor = COLORS[index];
        blockX = 3;
        blockY = 0;

        if (!isValidMove(currentBlock, blockX, blockY)) {
            isGameOver = true;
            timer.stop();
        }
    }

    private void placeBlock() {
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                if (currentBlock[r][c] == 1) {
                    int boardY = blockY + r;
                    int boardX = blockX + c;
                    if (boardY >= 0 && boardY < BOARD_HEIGHT && boardX >= 0 && boardX < BOARD_WIDTH) {
                        board[boardY][boardX] = getColorIndex(currentColor) + 1;
                    }
                }
            }
        }
        clearLines();
        spawnNewBlock();
    }

    private int getColorIndex(Color color) {
        for (int i = 0; i < COLORS.length; i++) {
            if (COLORS[i].equals(color)) return i;
        }
        return 0;
    }

    private void clearLines() {
        int linesCleared = 0;
        for (int r = BOARD_HEIGHT - 1; r >= 0; r--) {
            boolean fullLine = true;
            for (int c = 0; c < BOARD_WIDTH; c++) {
                if (board[r][c] == 0) {
                    fullLine = false;
                    break;
                }
            }

            if (fullLine) {
                linesCleared++;
                for (int y = r; y > 0; y--) {
                    System.arraycopy(board[y - 1], 0, board[y], 0, BOARD_WIDTH);
                }
                for (int c = 0; c < BOARD_WIDTH; c++) {
                    board[0][c] = 0;
                }
                r++;
            }
        }
        score += linesCleared * 100;
    }

    private boolean isValidMove(int[][] block, int nextX, int nextY) {
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                if (block[r][c] == 1) {
                    int newX = nextX + c;
                    int newY = nextY + r;

                    if (newX < 0 || newX >= BOARD_WIDTH || newY >= BOARD_HEIGHT) return false;
                    if (newY < 0) continue;
                    if (board[newY][newX] != 0) return false;
                }
            }
        }
        return true;
    }

    private int[][] rotateBlock(int[][] block) {
        int[][] rotated = new int[4][4];
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                rotated[c][3 - r] = block[r][c];
            }
        }
        return rotated;
    }

    private int[][] cloneBlock(int[][] block) {
        int[][] copy = new int[4][4];
        for (int i = 0; i < 4; i++) {
            System.arraycopy(block[i], 0, copy[i], 0, 4);
        }
        return copy;
    }

    // Swing 화면 그리기 (UI 렌더링)
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // 1. 고정된 보드 블록 그리기
        for (int r = 0; r < BOARD_HEIGHT; r++) {
            for (int c = 0; c < BOARD_WIDTH; c++) {
                if (board[r][c] > 0) {
                    g.setColor(COLORS[board[r][c] - 1]);
                    g.fillRect(c * CELL_SIZE, r * CELL_SIZE, CELL_SIZE - 1, CELL_SIZE - 1);
                } else {
                    g.setColor(Color.DARK_GRAY);
                    g.drawRect(c * CELL_SIZE, r * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                }
            }
        }

        // 2. 현재 떨어진 블록 그리기
        if (currentBlock != null && !isGameOver) {
            g.setColor(currentColor);
            for (int r = 0; r < 4; r++) {
                for (int c = 0; c < 4; c++) {
                    if (currentBlock[r][c] == 1) {
                        int drawX = (blockX + c) * CELL_SIZE;
                        int drawY = (blockY + r) * CELL_SIZE;
                        g.fillRect(drawX, drawY, CELL_SIZE - 1, CELL_SIZE - 1);
                    }
                }
            }
        }

        // 3. 점수 및 정보 표시
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        int sideX = BOARD_WIDTH * CELL_SIZE + 20;
        g.drawString("SCORE", sideX, 50);
        g.drawString(String.valueOf(score), sideX, 80);

        // 4. 게임 오버 문구 및 재시작 안내
        if (isGameOver) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 30));
            g.drawString("GAME OVER", 30, BOARD_HEIGHT * CELL_SIZE / 2 - 20);

            g.setColor(Color.YELLOW);
            g.setFont(new Font("Arial", Font.BOLD, 16));
            g.drawString("Press [R] to Restart", 35, BOARD_HEIGHT * CELL_SIZE / 2 + 20);
        }
    }

    // 메인 함수: 윈도우 창(JFrame) 생성 및 실행
    public static void main(String[] args) {
        JFrame frame = new JFrame("Java Swing Tetris");
        Main tetrisGame = new Main();

        frame.add(tetrisGame);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // 화면 중앙에 배치
        frame.setResizable(false);
        frame.setVisible(true);
    }
}