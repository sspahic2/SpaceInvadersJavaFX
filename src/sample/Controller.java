package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private final Stage primaryStage;
    @FXML private AnchorPane anchorPane;
    private final double stageX, stageY;
    private Group group = new Group(), player = new Group(), enemy = new Group(), bullets = new Group();

    public Controller(double x, double y, Stage stage) {
        this.stageX = x;
        this.stageY = y;
        primaryStage = stage;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PlayerShip playerShip = new PlayerShip(stageX/2, stageY - 30);
        EnemyShipController enemyShip = new EnemyShipController(stageX, stageY, bullets);
        player.getChildren().addAll(playerShip.getBody());
        enemy.getChildren().addAll(enemyShip.getEnemies());
        primaryStage.addEventFilter(KeyEvent.KEY_PRESSED, keyEvent -> {
            if(keyEvent.getCode() != KeyCode.SPACE) {
                playerShip.move(keyEvent.getCode());
            }
            else {
                bullets.getChildren().addAll(playerShip.shoot().getBullet());

            }
        });


        bullets.boundsInParentProperty().addListener(((observableValue, bounds, t1) -> {
            remove();
        }));
        group.getChildren().addAll(player, enemy, bullets);
        anchorPane.getChildren().addAll(group);
    }

    private void remove() {
        int removeEnemy = -1, removeBullet = -1;
        for(int i = 0; i < enemy.getChildren().size(); i++) {
            for(int j = 0; j < bullets.getChildren().size(); j++) {
                if(enemy.getChildren().get(i).intersects(bullets.getChildren().get(j).getBoundsInParent())) {
                    removeEnemy = i;
                    removeBullet = j;
                }
            }
        }
        if(removeEnemy != -1) {
            enemy.getChildren().get(removeEnemy).setDisable(true);
            enemy.getChildren().remove(removeEnemy);
        }
        if(removeBullet != -1) {
            bullets.getChildren().remove(removeBullet);
        }

        for(int j = 0; j < bullets.getChildren().size(); j++) {
            if (player.getChildren().get(0).intersects(bullets.getChildren().get(j).getBoundsInParent())) {
                Label popUp = new Label("GAME OVER");
                popUp.setScaleX(2);
                popUp.setScaleY(2);
                popUp.relocate(stageX/2 - 100, stageY/2 - 100);
                group.getChildren().add(popUp);

                group.getChildren().removeAll(enemy, player, bullets);
                //primaryStage.close();
            }
        }
    }
}
