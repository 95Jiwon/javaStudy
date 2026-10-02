import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BulletHellGame extends JPanel implements ActionListener, KeyListener {
    // 화면 크기
    private static final int WIDTH = 600;
    private static final int HEIGHT = 800;

    // 게임 루프 타이머 (약 60 FPS)
    private Timer timer;

    // 플레이어 정보
    private double playerX = WIDTH / 2.0;
    private double playerY = HEIGHT - 100;
    private double playerSpeed = 5.0;
    private final double PLAYER_RADIUS = 6.0;
    private final double PLAYER_VISUAL_RADIUS = 15.0;
    private int shootCooldown = 0; // 플레이어 공격 쿨타임

    // 키 입력 상태
    private boolean up, down, left, right, shift, space;

    // 탄막(총알) 리스트 (적 탄막 & 플레이어 탄막 분리)
    private List<Bullet> enemyBullets;
    private List<Bullet> playerBullets;

    // 보스(탄막 생성기) 정보
    private double bossX = WIDTH / 2.0;
    private double bossY = 100;
    private int bossMaxHealth = 500;
    private int bossHealth = 500;
    private int bossMoveTimer = 0;
    private int attackTimer = 0;

    // 게임 상태 (0: 진행중, 1: 게임오버, 2: 클리어)
    private int gameState = 0;
    private int score = 0;

    public BulletHellGame() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);

        enemyBullets = new ArrayList<>();
        playerBullets = new ArrayList<>();

        // 60 FPS 설정 (약 16ms)
        timer = new Timer(16, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameState == 0) {
            updateGame();
            repaint();
        }
    }

    private void updateGame() {
        score++;

        // 1. 플레이어 이동 처리 (Shift 누르면 저속 이동)
        double currentSpeed = shift ? playerSpeed * 0.4 : playerSpeed;
        if (up && playerY > PLAYER_VISUAL_RADIUS) playerY -= currentSpeed;
        if (down && playerY < HEIGHT - PLAYER_VISUAL_RADIUS * 2) playerY += currentSpeed;
        if (left && playerX > PLAYER_VISUAL_RADIUS) playerX -= currentSpeed;
        if (right && playerX < WIDTH - PLAYER_VISUAL_RADIUS) playerX += currentSpeed;

        // 2. 플레이어 공격 처리 (Space 키로 발사)
        if (shootCooldown > 0) shootCooldown--;
        if (space && shootCooldown == 0) {
            // 플레이어 탄막 2발 동시 발사 (양쪽)
            playerBullets.add(new Bullet(playerX - 8, playerY - 15, 0, -15.0, 4.0, Color.GREEN));
            playerBullets.add(new Bullet(playerX + 8, playerY - 15, 0, -15.0, 4.0, Color.GREEN));
            shootCooldown = 6; // 발사 주기 조절
        }

        // 플레이어 탄막 업데이트 및 보스 피격 판정
        Iterator<Bullet> pIter = playerBullets.iterator();
        while (pIter.hasNext()) {
            Bullet pb = pIter.next();
            pb.update();
            if (pb.y < -20) {
                pIter.remove();
                continue;
            }

            // 보스와 플레이어 탄막 간의 충돌 체크 (보스 반경 20 기준)
            double bdx = bossX - pb.x;
            double bdy = bossY - pb.y;
            if (Math.sqrt(bdx * bdx + bdy * bdy) < 25) {
                bossHealth--;
                pIter.remove();
                if (bossHealth <= 0) {
                    gameState = 2; // 클리어 상태로 전환
                }
            }
        }

        // 3. 보스 이동 패턴 (좌우 왕복)
        bossMoveTimer++;
        bossX = WIDTH / 2.0 + Math.sin(bossMoveTimer * 0.03) * 150;

        // 4. 탄막 생성 패턴 (체력에 따른 페이즈 분기)
        attackTimer++;
        boolean isPhase2 = bossHealth <= bossMaxHealth / 2; // 체력 50% 이하 시 페이즈 2 진입

        if (attackTimer % 25 == 0) {
            spawnSpiralPattern();
        }
        if (attackTimer % 70 == 0) {
            spawnAimedPattern();
        }

        // 페이즈 2에서 추가되는 강력한 신규 패턴
        if (isPhase2) {
            if (attackTimer % 40 == 0) {
                spawnRapidSpreadPattern();
            }
        }

        // 5. 적 탄막 위치 업데이트 및 플레이어 충돌 판정
        Iterator<Bullet> eIter = enemyBullets.iterator();
        while (eIter.hasNext()) {
            Bullet eb = eIter.next();
            eb.update();

            if (eb.x < -50 || eb.x > WIDTH + 50 || eb.y < -50 || eb.y > HEIGHT + 50) {
                eIter.remove();
                continue;
            }

            // 플레이어 피격 판정
            double dx = playerX - eb.x;
            double dy = playerY - eb.y;
            if (Math.sqrt(dx * dx + dy * dy) < PLAYER_RADIUS + eb.radius) {
                gameState = 1; // 게임 오버 상태로 전환
            }
        }
    }

    // 기본 패턴 1: 소용돌이(나선형) 탄막
    private void spawnSpiralPattern() {
        int arms = 5;
        for (int i = 0; i < arms; i++) {
            double angle = (attackTimer * 0.1) + (Math.PI * 2 / arms) * i;
            double speed = 3.5;
            enemyBullets.add(new Bullet(bossX, bossY, Math.cos(angle) * speed, Math.sin(angle) * speed, 4.0, Color.MAGENTA));
        }
    }

    // 기본 패턴 2: 플레이어 조준 탄막
    private void spawnAimedPattern() {
        double angleToPlayer = Math.atan2(playerY - bossY, playerX - bossX);
        int count = 5;
        for (int i = -count / 2; i <= count / 2; i++) {
            double angle = angleToPlayer + (i * 0.15);
            double speed = 4.5;
            enemyBullets.add(new Bullet(bossX, bossY, Math.cos(angle) * speed, Math.sin(angle) * speed, 5.0, Color.CYAN));
        }
    }

    // 페이즈 2 전용 신규 패턴: 고속 원형 확산 탄막 (체력 50% 이하 시 활성화)
    private void spawnRapidSpreadPattern() {
        int count = 16;
        double baseAngle = attackTimer * 0.05;
        for (int i = 0; i < count; i++) {
            double angle = baseAngle + (Math.PI * 2 / count) * i;
            double speed = 3.0;
            enemyBullets.add(new Bullet(bossX, bossY, Math.cos(angle) * speed, Math.sin(angle) * speed, 4.5, Color.ORANGE));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // 적 탄막 그리기
        for (Bullet b : enemyBullets) {
            g2d.setColor(b.color);
            g2d.fill(new Ellipse2D.Double(b.x - b.radius, b.y - b.radius, b.radius * 2, b.radius * 2));
        }

        // 플레이어 탄막 그리기
        for (Bullet pb : playerBullets) {
            g2d.setColor(pb.color);
            g2d.fill(new Ellipse2D.Double(pb.x - pb.radius, pb.y - pb.radius, pb.radius * 2, pb.radius * 2));
        }

        // 보스 그리기 (페이즈 2일 때 색상 변경: 적색 -> 주황/노랑 계열)
        boolean isPhase2 = bossHealth <= bossMaxHealth / 2;
        g2d.setColor(isPhase2 ? Color.YELLOW : Color.RED);
        g2d.fill(new Ellipse2D.Double(bossX - 20, bossY - 20, 40, 40));

        // 플레이어 시각적 외형 그리기
        g2d.setColor(Color.WHITE);
        g2d.fill(new Ellipse2D.Double(playerX - PLAYER_VISUAL_RADIUS, playerY - PLAYER_VISUAL_RADIUS, PLAYER_VISUAL_RADIUS * 2, PLAYER_VISUAL_RADIUS * 2));

        // 피격 판정점 (코어)
        g2d.setColor(shift ? Color.YELLOW : Color.CYAN);
        g2d.fill(new Ellipse2D.Double(playerX - PLAYER_RADIUS, playerY - PLAYER_RADIUS, PLAYER_RADIUS * 2, PLAYER_RADIUS * 2));

        // UI 표시 (점수, 조작법, 보스 체력바)
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("SansSerif", Font.BOLD, 14));
        g2d.drawString("Score: " + score, 20, 30);
        g2d.drawString("Move: 방향키 | Shoot: Space | Low Speed: Shift", 20, HEIGHT - 30);

        // 보스 체력바 그리기
        g2d.setColor(Color.GRAY);
        g2d.fillRect(150, 40, 300, 15);
        g2d.setColor(isPhase2 ? Color.ORANGE : Color.RED);
        int healthBarWidth = (int) (300 * ((double) bossHealth / bossMaxHealth));
        g2d.fillRect(150, 40, healthBarWidth, 15);
        g2d.setColor(Color.WHITE);
        g2d.drawRect(150, 40, 300, 15);
        g2d.drawString("BOSS HP", 75, 53);

        // 게임 오버 화면 (gameState == 1)
        if (gameState == 1) {
            drawOverlayMessage(g2d, "GAME OVER", "Press 'R' to Restart", Color.RED);
        }
        // 게임 클리어 화면 (gameState == 2)
        else if (gameState == 2) {
            drawOverlayMessage(g2d, "VICTORY!", "Press 'R' to Restart", Color.GREEN);
        }
    }

    private void drawOverlayMessage(Graphics2D g2d, String title, String sub, Color titleColor) {
        g2d.setColor(new Color(0, 0, 0, 180));
        g2d.fillRect(0, 0, WIDTH, HEIGHT);
        g2d.setColor(titleColor);
        g2d.setFont(new Font("SansSerif", Font.BOLD, 40));
        g2d.drawString(title, WIDTH / 2 - g2d.getFontMetrics().stringWidth(title) / 2, HEIGHT / 2 - 20);
        g2d.setFont(new Font("SansSerif", Font.PLAIN, 20));
        g2d.setColor(Color.WHITE);
        g2d.drawString(sub, WIDTH / 2 - g2d.getFontMetrics().stringWidth(sub) / 2, HEIGHT / 2 + 30);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_UP) up = true;
        if (code == KeyEvent.VK_DOWN) down = true;
        if (code == KeyEvent.VK_LEFT) left = true;
        if (code == KeyEvent.VK_RIGHT) right = true;
        if (code == KeyEvent.VK_SHIFT) shift = true;
        if (code == KeyEvent.VK_SPACE) space = true;

        if (code == KeyEvent.VK_R && gameState != 0) {
            // 게임 재시작 초기화
            gameState = 0;
            score = 0;
            bossHealth = bossMaxHealth;
            enemyBullets.clear();
            playerBullets.clear();
            playerX = WIDTH / 2.0;
            playerY = HEIGHT - 100;
            attackTimer = 0;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_UP) up = false;
        if (code == KeyEvent.VK_DOWN) down = false;
        if (code == KeyEvent.VK_LEFT) left = false;
        if (code == KeyEvent.VK_RIGHT) right = false;
        if (code == KeyEvent.VK_SHIFT) shift = false;
        if (code == KeyEvent.VK_SPACE) space = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    // 탄막 클래스
    private static class Bullet {
        double x, y, vx, vy, radius;
        Color color;

        public Bullet(double x, double y, double vx, double vy, double radius, Color color) {
            this.x = x;
            this.y = y;
            this.vx = vx;
            this.vy = vy;
            this.radius = radius;
            //this.color, this.color = color; // 오타 방지용 수정
            this.color = color;
        }

        public void update() {
            x += vx;
            y += vy;
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Java Bullet Hell Game - Boss Battle");
        BulletHellGame game = new BulletHellGame();
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}