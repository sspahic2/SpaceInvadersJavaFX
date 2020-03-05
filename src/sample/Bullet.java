package sample;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;


public class Bullet {

    private ImageView shooter;
    private ImageView bullet = new ImageView();
    private double time = 0;
    public Bullet(ImageView shooter, String name) {
        this.shooter = shooter;
        shootingMechanism(bullet, name);
    }

    private void shootingMechanism(ImageView bullet, String name) {

        if(shooter.isDisabled()) {
            bullet.setDisable(true);
            bullet.relocate(500, 250);
        }
            bullet.setX(shooter.getX() + 10);
        if(name.equals("Player")) {
            bullet.setImage(new Image("file:redBullet.png"));
            bullet.setY(shooter.getY() - 25);
            bullet.setFitWidth(5);
            bullet.setFitHeight(20);
        }
        else if(name.equals("Enemy")) {
            bullet.setImage(new Image("file:blueBullet.png"));
            bullet.setY(shooter.getY() + 35);
            bullet.setFitWidth(5);
            bullet.setFitHeight(20);
        }

        new AnimationTimer() {
            @Override
            public void handle(long l) {
                updateBullet(name);
            }
        }.start();
    }

    private void updateBullet(String name) {
        if(time > 3) {
            if(Math.abs(bullet.getY()) > 500) {
                bullet.setDisable(true);
            }
            else {
                if(name.equals("Enemy")) {
                    bullet.setY(bullet.getY() + 10);
                }
                else if(name.equals("Player")) {
                    bullet.setY(bullet.getY() - 10);
                }
            }
            time = 0;
        }

        time += 0.4;
    }


    public ImageView getBullet() {
        return bullet;
    }
}
