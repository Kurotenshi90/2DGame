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
    public Block[][] blocks = levelReader.returnMap();
    public int screenX = 50;
    public int screenY = 40;
    public int tileSize = 16;

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
                player.movePlayer(theScene, tileSize, screenX, screenY,  blocks);

                double t = (currentNanoTime - startNanoTime) / 1000000000.0;


                int shakePosX = (player.getRealPosX() - ((player.getRealPosX() / tileSize) * tileSize));
                int shakePosY = (player.getRealPosY() - ((player.getRealPosY() / tileSize) * tileSize));
                // bepalen x pos scherm
                int extraSpaceX = 0;
                int halfScreenX = screenX / 2;
                int halfScreenY = screenY / 2;
                int maxScreenX = blocks.length - halfScreenX;
                int maxScreenY = blocks[0].length - halfScreenY;
                int halfTile = tileSize / 2;

                if (player.getRealPosX() <= tileSize * halfScreenX) {
                    shakePosX = 0;
                    extraSpaceX = screenX;
                } else if (player.getRealPosX() > tileSize * halfScreenX  && player.getRealPosX() < maxScreenX * tileSize) {
                    extraSpaceX = screenX + 1;
                } else if (player.getRealPosX() >= maxScreenX * tileSize) {
                    shakePosX = 0;
                    extraSpaceX = screenX;
                }

                int extraSpaceY = 0;
                if (player.getRealPosY() <= tileSize * halfScreenY) {
                    extraSpaceY = screenY;
                    shakePosY = 0;
                } else if (player.getRealPosY() > tileSize * halfScreenY && player.getRealPosY() < maxScreenY * tileSize) {
                    extraSpaceY = screenY + 1;
                } else if (player.getRealPosY() >= maxScreenY * tileSize) {
                    extraSpaceY = screenY;
                    shakePosY = 0;
                }
                //draw background
                double sizeMapPixX = (double)(1200-screenX*tileSize) / (double)((blocks.length * tileSize));
                double distancebackX = sizeMapPixX * player.getRealPosX();
                double sizeMapPixY = (double)(800-screenY*tileSize) / (double)((blocks[0].length * tileSize));
                double distancebackY = sizeMapPixY * player.getRealPosY();

                gc.drawImage(imageHandler.returnSpace(), -distancebackX, -distancebackY);

                System.out.println(player.getPosX());

                // draw tiles
                for (int j = player.getPosY(); j < extraSpaceY + player.getPosY(); j++) {
                    for (int i = player.getPosX(); i < extraSpaceX + player.getPosX(); i++) {
                        gc.drawImage(blocks[i][j].getImage(), tileSize * (i - player.getPosX()) - shakePosX, tileSize * (j - player.getPosY()) - shakePosY);
                    }
                }

                int drawPosX = player.getRealPosX();
                if (player.getRealPosX() >= tileSize*halfScreenX) {
                    drawPosX = tileSize*halfScreenX;
                }
                if (posX >= blocks.length - screenX) {
                    drawPosX = player.getRealPosX() - ((blocks.length - halfScreenX-1) * tileSize) + (halfScreenX-1) * tileSize ;
                }




                int drawPosY = player.getRealPosY();
                if (player.getRealPosY() >= tileSize*halfScreenY) {
                    drawPosY = tileSize*halfScreenY;
                }
                if (posY >= blocks[0].length - screenY) {
                    drawPosY = player.getRealPosY() - ((blocks[0].length - halfScreenY -1 ) * tileSize) + (halfScreenX-1) * tileSize ;
                }


                gc.drawImage(player.drawPlayer(), drawPosX, drawPosY);
            }
        }.start();
        theStage.show();
    }
}
