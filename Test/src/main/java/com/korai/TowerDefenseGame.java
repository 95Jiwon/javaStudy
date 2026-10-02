package com.korai;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class TowerDefenseGame extends JFrame {

    public TowerDefenseGame() {
        setTitle("가벼운 자바 타워 디펜스 (웨이브 & 강화 시스템)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        GamePanel gamePanel = new GamePanel();
        add(gamePanel);
        pack();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TowerDefenseGame::new);
    }
}

// --- 게임 판넬 및 로직 ---
class GamePanel extends JPanel implements ActionListener {
    private final int WIDTH = 800;
    private final int HEIGHT = 600;

    private Timer gameTimer;
    private int gold = 150;
    private int health = 10;
    private int score = 0;
    private int waveSpawnTimer = 0;

    // 웨이브 시스템 변수
    private int currentWave = 1;
    private int enemiesKilledInWave = 0;
    private final int enemiesPerWave = 10; // 웨이브당 처치해야 할 적 수
    private boolean isWaveClearing = false; // 보상 선택 창이 떠 있을 때 일시정지용
    private List<RewardOption> currentRewards = null;

    private List<Enemy> enemies;
    private List<Tower> towers;
    private List<Bullet> bullets;

    private final List<Point> path;
    private Random random = new Random();

    public GamePanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(new Color(25, 25, 25)); // 눈이 편안한 어두운 배경
        setFocusable(true);

        enemies = new ArrayList<>();
        towers = new ArrayList<>();
        bullets = new ArrayList<>();

        // 적 이동 경로 설정 (조금 더 다듬어진 S자 경로)
        path = new ArrayList<>();
        path.add(new Point(0, 150));
        path.add(new Point(180, 150));
        path.add(new Point(180, 420));
        path.add(new Point(420, 420));
        path.add(new Point(420, 180));
        path.add(new Point(650, 180));
        path.add(new Point(650, 480));
        path.add(new Point(800, 480));

        // 마우스 클릭 이벤트
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    if (isWaveClearing) {
                        handleRewardClick(e.getX(), e.getY());
                    } else {
                        handleGameClick(e.getX(), e.getY());
                    }
                }
            }
        });

        gameTimer = new Timer(16, this);
        gameTimer.start();
    }

    private void handleGameClick(int x, int y) {
        // 1. 기존 타워 클릭 시 업그레이드 시도 (타워 크기가 작아졌으므로 클릭 반경 15로 조정)
        for (Tower tower : towers) {
            if (Math.hypot(tower.x - x, tower.y - y) < 15) {
                int upgradeCost = 20 + (tower.level * 10); // 레벨이 높을수록 업그레이드 비용 증가
                if (gold >= upgradeCost && tower.level < 5) {
                    gold -= upgradeCost;
                    tower.upgrade();
                }
                return;
            }
        }

        // 2. 빈 공간 건설 (비용: 40 골드)
        placeTower(x, y);
    }

    private void placeTower(int x, int y) {
        for (int i = 0; i < path.size() - 1; i++) {
            Point p1 = path.get(i);
            Point p2 = path.get(i + 1);
            if (isNearLine(x, y, p1.x, p1.y, p2.x, p2.y, 22)) {
                return;
            }
        }

        for (Tower t : towers) {
            if (Math.hypot(t.x - x, t.y - y) < 30) {
                return;
            }
        }

        if (gold >= 40) {
            towers.add(new Tower(x, y));
            gold -= 40;
        }
    }

    private boolean isNearLine(double px, double py, double x1, double y1, double x2, double y2, double dist) {
        double lineLen = Math.hypot(x2 - x1, y2 - y1);
        if (lineLen == 0) return Math.hypot(px - x1, py - y1) < dist;
        double u = ((px - x1) * (x2 - x1) + (py - y1) * (y2 - y1)) / (lineLen * lineLen);
        u = Math.max(0, Math.min(1, u));
        double ix = x1 + u * (x2 - x1);
        double iy = y1 + u * (y2 - y1);
        return Math.hypot(px - ix, py - iy) < dist;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!isWaveClearing) {
            updateGame();
        }
        repaint();
    }

    private void updateGame() {
        waveSpawnTimer++;
        // 웨이브가 진행될수록 적 스폰 주기가 아주 살짝 빨라짐
        int spawnInterval = Math.max(40, 90 - (currentWave * 5));
        if (waveSpawnTimer > spawnInterval) {
            // 웨이브가 높아질수록 적의 체력과 속도 증가
            int enemyHp = 80 + (currentWave * 25);
            double enemySpeed = 1.8 + (currentWave * 0.1);
            enemies.add(new Enemy(path, enemyHp, enemySpeed));
            waveSpawnTimer = 0;
        }

        Iterator<Enemy> enemyIterator = enemies.iterator();
        while (enemyIterator.hasNext()) {
            Enemy enemy = enemyIterator.next();
            enemy.update();
            if (enemy.isReachedEnd()) {
                health--;
                enemyIterator.remove();
            } else if (enemy.isDead()) {
                gold += 12 + (currentWave * 2);
                score += 10 * currentWave;
                enemyIterator.remove();

                // 웨이브 클리어 카운트 증가
                enemiesKilledInWave++;
                if (enemiesKilledInWave >= enemiesPerWave) {
                    triggerWaveClear();
                }
            }
        }

        for (Tower tower : towers) {
            tower.update(enemies, bullets);
        }

        Iterator<Bullet> bulletIterator = bullets.iterator();
        while (bulletIterator.hasNext()) {
            Bullet bullet = bulletIterator.next();
            bullet.update();
            if (bullet.hasHitTarget()) {
                bullet.getTarget().hit(bullet.getDamage());
                bulletIterator.remove();
            } else if (bullet.isOutOfBounds()) {
                bulletIterator.remove();
            }
        }

        if (health <= 0) {
            gameTimer.stop();
        }
    }

    private void triggerWaveClear() {
        isWaveClearing = true;
        enemies.clear(); // 화면에 남은 적 정리
        bullets.clear();

        // 3가지 무작위 보상 생성
        currentRewards = new ArrayList<>();
        currentRewards.add(new RewardOption("보너스 골드 +100", "골드를 100G 즉시 획득합니다.", () -> gold += 100));
        currentRewards.add(new RewardOption("전체 타워 공격력 강화", "모든 타워의 공격력이 10 증가합니다.", () -> {
            for (Tower t : towers) t.bonusDamage += 10;
        }));
        currentRewards.add(new RewardOption("타워 연사속도 단축", "모든 타워의 공격 속도가 빨라집니다.", () -> {
            for (Tower t : towers) t.bonusFireRate = Math.max(5, t.bonusFireRate - 3);
        }));
        currentRewards.add(new RewardOption("플레이어 체력 회복 +3", "기지 체력을 3 회복합니다.", () -> health = Math.min(20, health + 3)));
    }

    private void handleRewardClick(int x, int y) {
        // 보상 박스 좌표 계산 (화면 중앙 배치)
        int boxWidth = 500;
        int boxHeight = 300;
        int startX = (WIDTH - boxWidth) / 2;
        int startY = (HEIGHT - boxHeight) / 2;

        for (int i = 0; i < currentRewards.size(); i++) {
            int itemY = startY + 80 + (i * 50);
            // 클릭 영역 감지
            if (x >= startX + 20 && x <= startX + boxWidth - 20 && y >= itemY && y <= itemY + 40) {
                currentRewards.get(i).applyReward.run();
                // 다음 웨이브로 진행
                currentWave++;
                enemiesKilledInWave = 0;
                isWaveClearing = false;
                break;
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // --- 1. 길 그리기 ---
        g2d.setColor(new Color(60, 60, 60));
        g2d.setStroke(new BasicStroke(24, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        for (int i = 0; i < path.size() - 1; i++) {
            Point p1 = path.get(i);
            Point p2 = path.get(i + 1);
            g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
        }

        // --- 2. 타워 그리기 ---
        for (Tower tower : towers) {
            tower.draw(g2d);
        }

        // --- 3. 적 그리기 ---
        for (Enemy enemy : enemies) {
            enemy.draw(g2d);
        }

        // --- 4. 총알 그리기 ---
        for (Bullet bullet : bullets) {
            bullet.draw(g2d);
        }

        // --- 5. UI 및 정보 표시 ---
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("SansSerif", Font.BOLD, 15));
        g2d.drawString("골드: " + gold + " G", 20, 30);
        g2d.drawString("체력: " + health, 140, 30);
        g2d.drawString("점수: " + score, 230, 30);
        g2d.drawString("웨이브: " + currentWave + " (진행도: " + enemiesKilledInWave + "/" + enemiesPerWave + ")", 350, 30);
        g2d.drawString("[조작] 빈곳 클릭: 건설(40G) / 타워 클릭: 업그레이드(최대 Lv.5)", 20, HEIGHT - 15);

        // --- 6. 웨이브 클리어 보상 창 렌더링 ---
        if (isWaveClearing && currentRewards != null) {
            g2d.setColor(new Color(0, 0, 0, 220));
            g2d.fillRect(0, 0, WIDTH, HEIGHT);

            int boxWidth = 500;
            int boxHeight = 300;
            int startX = (WIDTH - boxWidth) / 2;
            int startY = (HEIGHT - boxHeight) / 2;

            g2d.setColor(new Color(45, 45, 45));
            g2d.fillRoundRect(startX, startY, boxWidth, boxHeight, 20, 20);
            g2d.setColor(Color.YELLOW);
            g2d.drawRoundRect(startX, startY, boxWidth, boxHeight, 20, 20);

            g2d.setFont(new Font("SansSerif", Font.BOLD, 22));
            g2d.drawString("✨ 웨이브 " + (currentWave) + " 클리어! 보상을 선택하세요", startX + 45, startY + 45);

            g2d.setFont(new Font("SansSerif", Font.PLAIN, 14));
            for (int i = 0; i < currentRewards.size(); i++) {
                int itemY = startY + 80 + (i * 50);
                g2d.setColor(new Color(70, 70, 90));
                g2d.fillRoundRect(startX + 20, itemY, boxWidth - 40, 40, 10, 10);
                g2d.setColor(Color.WHITE);
                g2d.drawString((i + 1) + ". " + currentRewards.get(i).title + " - " + currentRewards.get(i).desc, startX + 35, itemY + 25);
            }
        }

        // 게임 오버 화면
        if (health <= 0) {
            g2d.setColor(new Color(0, 0, 0, 210));
            g2d.fillRect(0, 0, WIDTH, HEIGHT);
            g2d.setColor(Color.RED);
            g2d.setFont(new Font("SansSerif", Font.BOLD, 48));
            g2d.drawString("GAME OVER", WIDTH / 2 - 130, HEIGHT / 2 - 20);
            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("SansSerif", Font.PLAIN, 20));
            g2d.drawString("최종 점수: " + score + " (도달 웨이브: " + currentWave + ")", WIDTH / 2 - 115, HEIGHT / 2 + 30);
        }
    }
}

// --- 보상 옵션 데이터를 위한 클래스 ---
class RewardOption {
    String title;
    String desc;
    Runnable applyReward;

    public RewardOption(String title, String desc, Runnable applyReward) {
        this.title = title;
        this.desc = desc;
        this.applyReward = applyReward;
    }
}

// --- 적(Enemy) 클래스 ---
class Enemy {
    private double x, y;
    private int hp;
    private int maxHp;
    private double speed;
    private List<Point> path;
    private int targetPointIndex = 1;

    public Enemy(List<Point> path, int hp, double speed) {
        this.path = path;
        this.hp = hp;
        this.maxHp = hp;
        this.speed = speed;
        this.x = path.get(0).x;
        this.y = path.get(0).y;
    }

    public void update() {
        if (targetPointIndex >= path.size()) return;

        Point target = path.get(targetPointIndex);
        double dx = target.x - x;
        double dy = target.y - y;
        double dist = Math.hypot(dx, dy);

        if (dist < speed) {
            x = target.x;
            y = target.y;
            targetPointIndex++;
        } else {
            x += (dx / dist) * speed;
            y += (dy / dist) * speed;
        }
    }

    public void draw(Graphics2D g2d) {
        // 크기를 줄여 눈의 피로도 감소 (반지름 9)
        g2d.setColor(new Color(220, 70, 70));
        g2d.fillOval((int)x - 9, (int)y - 9, 18, 18);

        // 체력바
        g2d.setColor(Color.DARK_GRAY);
        g2d.fillRect((int)x - 12, (int)y - 17, 24, 4);
        g2d.setColor(Color.GREEN);
        int healthBarWidth = (int) (24 * ((double) hp / maxHp));
        g2d.fillRect((int)x - 12, (int)y - 17, Math.max(0, healthBarWidth), 4);
    }

    public boolean isReachedEnd() {
        return targetPointIndex >= path.size();
    }

    public boolean isDead() {
        return hp <= 0;
    }

    public void hit(int damage) {
        hp -= damage;
    }

    public double getX() { return x; }
    public double getY() { return y; }
}

// --- 타워(Tower) 클래스 ---
class Tower {
    int x, y;
    int level = 1;
    private int range = 100;
    private int cooldown = 0;
    private int baseFireRate = 22;
    private int baseDamage = 20;

    // 버프에 의해 증가하는 값
    public int bonusDamage = 0;
    public int bonusFireRate = 0;

    public Tower(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void upgrade() {
        level++;
        range += 15;        // 레벨업당 사거리 증가
    }

    public void update(List<Enemy> enemies, List<Bullet> bullets) {
        if (cooldown > 0) {
            cooldown--;
        }

        Enemy target = null;
        for (Enemy enemy : enemies) {
            double dist = Math.hypot(enemy.getX() - x, enemy.getY() - y);
            if (dist <= range) {
                target = enemy;
                break;
            }
        }

        // 현재 레벨에 따른 총 데미지 및 연사력 계산
        int totalDamage = baseDamage + (level * 10) + bonusDamage;
        int currentFireRate = Math.max(8, baseFireRate - (level * 2) - bonusFireRate);

        if (target != null && cooldown == 0) {
            bullets.add(new Bullet(x, y, target, totalDamage));
            cooldown = currentFireRate;
        }
    }

    public void draw(Graphics2D g2d) {
        // 사거리 (은은하게 표시)
        g2d.setColor(new Color(255, 255, 255, 12));
        g2d.drawOval(x - range, range < 0 ? y : y - range, range * 2, range * 2);

        // 타워 크기를 작게 축소 ($20\times20$)
        Color towerColor;
        switch (level) {
            case 1: towerColor = new Color(70, 130, 240); break; // 파랑
            case 2: towerColor = new Color(50, 190, 110); break; // 초록
            case 3: towerColor = new Color(200, 160, 40); break; // 노랑
            case 4: towerColor = new Color(210, 90, 210); break; // 보라
            default: towerColor = new Color(240, 70, 70); break; // 빨강 (만렙 Lv.5)
        }

        g2d.setColor(towerColor);
        g2d.fillRect(x - 10, y - 10, 20, 20);
        g2d.setColor(Color.WHITE);
        g2d.drawRect(x - 10, y - 10, 20, 20);

        // 레벨 텍스트 표시
        g2d.setFont(new Font("SansSerif", Font.BOLD, 10));
        g2d.drawString("L" + level, x - 7, y + 4);
    }
}

// --- 총알(Bullet) 클래스 ---
class Bullet {
    private double x, y;
    private Enemy target;
    private double speed = 9.0;
    private int damage;

    public Bullet(int startX, int startY, Enemy target, int damage) {
        this.x = startX;
        this.y = startY;
        this.target = target;
        this.damage = damage;
    }

    public void update() {
        if (hasHitTarget()) return;

        double dx = target.getX() - x;
        double dy = target.getY() - y;
        double dist = Math.hypot(dx, dy);

        if (dist > 0) {
            x += (dx / dist) * speed;
            y += (dy / dist) * speed;
        }
    }

    public boolean hasHitTarget() {
        if (target.isDead() || target.isReachedEnd()) return true;
        return Math.hypot(target.getX() - x, target.getY() - y) < 8;
    }

    public boolean isOutOfBounds() {
        return x < 0 || x > 800 || y < 0 || y > 600;
    }

    public Enemy getTarget() {
        return target;
    }

    public int getDamage() {
        return damage;
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.ORANGE);
        g2d.fillOval((int)x - 3, (int)y - 3, 6, 6);
    }
}