package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;


public class PlayerShip{
    private ImageView body = new ImageView();
    private double x, y;
    private boolean isALive = true;

    PlayerShip(double x, double y) {
        this.x = x;
        this.y = y;
        body.setImage(new Image("file:playerShipImage.png"));
        body.setX(x);
        body.setY(y);
        body.setFitWidth(25);
        body.setFitHeight(15);
    }

    public ImageView getBody() {
        return body;
    }

    public void move(KeyCode code) {
        if(code == KeyCode.A) {
            body.setX(body.getX() - 5);
        }
        else if(code == KeyCode.D) {
            body.setX(body.getX() + 5);
        }

        if(check() == 1) {
            body.setX(body.getX() - 5);
        }

        else if(check() == 2) {
            body.setX(body.getX() + 5);
        }
    }

    private int check() {
        if(body.getX() >= x + x - 35) {
            return 1;
        }
        else if(body.getX() <= 0) {
            return 2;
        }
        return 0;
    }


    public Bullet shoot() {
        return new Bullet(body, "Player");
    }

    public boolean isALive() {
        return isALive;
    }

    public void setALive(boolean ALive) {
        isALive = ALive;
    }
}
