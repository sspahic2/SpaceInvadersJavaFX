package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class EnemyShip{
    private ImageView body = new ImageView();
    private double x, y, time = 0, moveVar = 10, shootingPace = 0;
    private String shipType;
    private boolean isAlive = true;

    public EnemyShip(double stageX, double stageY, String whatShip) {
        this.x = stageX;
        this.y = stageY;
        shipType = whatShip;
        fill();

    }

    private void fill() {
        ImageView iv = new ImageView(new Image(shipType));
        iv.setFitWidth(25);
        iv.setFitHeight(25);
        iv.setX(x);
        iv.setY(y);
        body = iv;
    }


    public ImageView getBody() {
        return body;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public boolean isAlive() {
        return isAlive;
    }
}
