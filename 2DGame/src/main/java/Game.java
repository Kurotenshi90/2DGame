/**
 * Created by Peter-Paul on 04/12/2015.
 */

import blocks.Block;
import entities.Player;
import imageHandler.ImageHandler;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import levelLoader.Generator;
import levelLoader.LevelReader;


public class Game extends Application {


    public static void main(String[] args) {
        launch(args);
    }
    public Generator generator = new Generator();
    public ImageHandler imageHandler = new ImageHandler();
    public Player player = new Player();
    public LevelReader levelReader = new LevelReader("level1");
    public Block[][] blocks;
    public int screenX = 20;
    public int screenY = 14;
    public int tileSize = 32;

    public int posX;
    public int posY;

    public void start(Stage theStage) {

        theStage.setTitle("Timeline Example");
        Group root = new Group();
        final Scene theScene = new Scene(root);
        theStage.setScene(theScene);
        Canvas canvas = new Canvas(tileSize * screenX, tileSize * screenY);
        root.getChildren().add(canvas);
        final GraphicsContext gc = canvas.getGraphicsContext2D();
        final long startNanoTime = System.nanoTime();


        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                posX = player.getPosX();
                posY = player.getPosY();
                blocks = generator.generateMap(100,50);
                player.movePlayer(theScene, tileSize, screenX, screenY,  blocks);

                double t = (currentNanoTime - startNanoTime) / 1000000000.0;


                int shakePosX = (player.getRealPosX() - ((player.getRealPosX() / tileSize) * tileSize));

                // bepalen x pos scherm
                int extraSpaceX = 0;
                if (player.getRealPosX() <= tileSize * 10) {
                    shakePosX = 0;
                    extraSpaceX = screenX;
                } else if (player.getRealPosX() > tileSize * 10  && player.getRealPosX() < 67 * tileSize) {
                    extraSpaceX = screenX + 1;
                } else if (player.getRealPosX() >= 67 * tileSize) {
                    shakePosX = 0;
                    extraSpaceX = screenX;
                }

                //bepalen y positie op het scherm
                int shakePosY = (player.getRealPosY() - ((player.getRealPosY() / tileSize) * tileSize));
                int extraSpaceY = 0;
                if (player.getRealPosY() <= tileSize * 7) {
                    extraSpaceY = screenY;
                    shakePosY = 0;
                } else if (player.getRealPosY() > tileSize * 7 && player.getRealPosY() < 67 * tileSize) {
                    extraSpaceY = screenY + 1;
                } else if (player.getRealPosY() >= 67 * tileSize) {
                    extraSpaceY = screenY;
                    shakePosY = 0;
                }
                //draw background
                gc.drawImage(imageHandler.returnSpace(), 0, 0);
                gc.drawImage(imageHandler.returnSpace(), 640, 0);

                // draw tiles
                for (int j = player.getPosY(); j < extraSpaceY + player.getPosY(); j++) {
                    for (int i = player.getPosX(); i < extraSpaceX + player.getPosX(); i++) {
                        gc.drawImage(blocks[i][j].getImage(), 32 * (i - player.getPosX()) - shakePosX, 32 * (j - player.getPosY()) - shakePosY);
                    }
                }

                int drawPosX = player.getRealPosX();
                if (player.getRealPosX() >= 320) {
                    drawPosX = 320;
                }
                if (posX >= blocks.length - screenX) {
                    drawPosX = player.getRealPosX() - ((blocks.length - 11) * 32) + 9 * tileSize ;
                }




                int drawPosY = player.getRealPosY();
                if (player.getRealPosY() >= 224) {
                    drawPosY = 224;
                }
                if (posY >= blocks[0].length - screenY) {
                    drawPosY = player.getRealPosY() - ((blocks[0].length - 8) * 32) + 6 * tileSize -16;
                }


                gc.drawImage(player.drawPlayer(), drawPosX, drawPosY);
            }
        }.start();
        theStage.show();
    }
}
