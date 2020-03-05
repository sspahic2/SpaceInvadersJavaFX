package sample;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class EnemyShipController {
    private ArrayList<EnemyShip> enemies = new ArrayList<>();
    private int numberOfEnemies = 10;
    private double time = 0, moveVar = 10, shootingPace = 0, x, y;
    private Group bullets;
    public EnemyShipController(double stageX, double stageY, Group b) {
        x = stageX;
        y = stageY;
        bullets = b;
        fill();
        new AnimationTimer() {
            @Override
            public void handle(long l) {
                move();
                shoot();
            }
        }.start();
    }

    private void fill() {

        enemies.add(new EnemyShip(x/6, y/6, "file:SpaceInvadersAlien.png"));
        enemies.add(new EnemyShip(x/6 + 35, y/6, "file:SpaceInvadersAlien.png"));
    }

    private void shoot() {
        if(shootingPace > 7) {
            for (EnemyShip es : enemies) {
                    bullets.getChildren().add(new Bullet(es.getBody(), "Enemy").getBullet());

            }
            shootingPace = 0;
        }

        shootingPace += 0.1;
    }



    public void move() {
        if(time > 5) {
            enemies.forEach(ships -> {
                ships.getBody().setX(ships.getBody().getX() + moveVar);
            });

            if(check() == 1) {
                enemies.forEach(es -> { es.getBody().setY(es.getBody().getY() + 10);});
                moveVar = -10;
            }
            else if(check() == 2){
                enemies.forEach(es -> { es.getBody().setY(es.getBody().getY() + 10);});
                moveVar = 10;
            }
            time = 0;
        }

        time += 0.08;
    }

    private int check() {
        for(EnemyShip es : enemies) {
            if(es.getBody().getX() > x - 35) {
                return 1;
            }
            else if(es.getBody().getX() < 5) {
                return 2;
            }
        }
        return 0;
    }

    public ArrayList<ImageView> getEnemies() {
        ArrayList<ImageView> result = new ArrayList<>();

        for(EnemyShip es : enemies) {
            result.add(es.getBody());
        }
        return result;
    }
}
