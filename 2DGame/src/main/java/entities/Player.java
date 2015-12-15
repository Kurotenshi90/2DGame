package entities;

import blocks.Block;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;

/**
 * Created by Peter-Paul on 06/12/2015.
 */
public class Player {
    private int posX = 0*16;
    private int posY = 0*16;
    private int blockX;
    private int blockY;
    private Image player;
    private int jumpSpeed = 8;
    private int moveSpeed = 3;
    private int gravitie = 4;
    private int playerLenght = 24;
    private int playerHeight = 36;

    public Player() {
        player = new Image("player.png");
    }

    ArrayList<String> input = new ArrayList<String>();

    public void movePlayer(Scene theScene, int tileSize, int screenX, int screenY, Block[][] blocks) {
        theScene.setOnKeyPressed(

                new EventHandler<KeyEvent>() {
                    public void handle(KeyEvent e) {
                        String code = e.getCode().toString();
                        // only add once... prevent duplicates
                        if (!input.contains(code))
                            input.add(code);
                    }
                });

        theScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent e) {
                String code = e.getCode().toString();
                input.remove(code);
            }
        });

        if (input.contains("LEFT") || input.contains("A")) {
            posX -= moveSpeed;
            if (posX < 0) {
                posX = 0;
            }
            if (blocks[(posX / tileSize)][posY / tileSize].isSolid()) {
                posX = (posX / tileSize + 1) * tileSize;
            }
            if (blocks[(posX / tileSize)][(posY+12) / tileSize].isSolid()) {
                posX = (posX / tileSize + 1) * tileSize;
            }
            if (blocks[(posX / tileSize)][(posY+24) / tileSize].isSolid()) {
                posX = (posX / tileSize + 1) * tileSize;
            }
            if (blocks[(posX / tileSize)][(posY + 35) / tileSize].isSolid()) {
                posX = (posX / tileSize + 1) * tileSize;
            }
        }
        if (input.contains("RIGHT") || input.contains("D")) {

            posX += moveSpeed;
            if (posX + 8 >= (blocks.length - 1) * tileSize) {
                posX = (blocks.length - 1) * tileSize -8;
            }

            if (posX+8 < (blocks.length - 1) * tileSize) {
                if (blocks[((posX + 8) / tileSize) + 1][(posY) / tileSize].isSolid()) {
                    posX = ((posX / tileSize) * tileSize)+8;
                }
                if (blocks[((posX + 8) / tileSize) + 1][(posY + 12) / tileSize].isSolid()) {
                    posX = ((posX / tileSize) * tileSize)+8;
                }
                if (blocks[((posX + 8) / tileSize) + 1][(posY + 24) / tileSize].isSolid()) {
                    posX = ((posX / tileSize) * tileSize)+8;
                }
                if (blocks[((posX + 8) / tileSize) + 1][(posY + 35) / tileSize].isSolid()) {
                    posX = ((posX / tileSize) * tileSize)+8;
                }
            }
        }

        //if (input.contains("DOWN") || input.contains("S")) {
            posY += gravitie;
            if (posY >= (blocks[0].length - 1) * tileSize) {
                posY = (blocks[0].length - 1) * tileSize;
            }
            if (posY < (blocks[0].length - 1) * tileSize) {
                if (blocks[(posX / tileSize)][((posY +18) / tileSize) + 1].isSolid()) {
                    posY = (posY / tileSize) * tileSize - 4;
                }
                if (blocks[((posX + 12) / tileSize)][((posY +18) / tileSize) + 1].isSolid()) {
                    posY = (posY / tileSize) * tileSize - 4;
                }
                if (blocks[((posX + 23) / tileSize)][((posY +18) / tileSize) + 1].isSolid()) {
                    posY = (posY / tileSize) * tileSize - 4;
                }
            }
        //}

        if (input.contains("UP") || input.contains("W")) {
            posY -= jumpSpeed;
            if (posY < 0) {
                posY = 0;
            }
            if (blocks[(posX / tileSize)][((posY) / tileSize)].isSolid()) {
                posY = (posY / tileSize + 1) * tileSize;
            }
            if (blocks[((posX + 12) / tileSize)][(posY / tileSize)].isSolid()) {
                posY = (posY / tileSize + 1) * tileSize;
            }
            if (blocks[((posX + 23) / tileSize)][(posY / tileSize)].isSolid()) {
                posY = (posY / tileSize + 1) * tileSize;
            }
        }

        blockX = posX / tileSize;
        blockY = posY / tileSize;
//        System.out.println(posY);
       System.out.println(posX/32);

        checkBlocksToDraw(tileSize, screenX, screenY, blocks[0].length, blocks.length);
    }

    public void checkBlocksToDraw(int tileSize, int screenX, int screenY, int heightArray, int lengthArray) {
        int halfScreenX = screenX / 2;
        int halfScreenY = screenY / 2;
        int maxScreenX = lengthArray - halfScreenX;
        int maxScreenY = heightArray - halfScreenY;
        int halfTile = tileSize / 2;

        // bepaal Xblock
        if (posX <= tileSize * halfScreenX)
            blockX = 0;
        else if (posX > tileSize * halfScreenX && posX < maxScreenX * tileSize)
            blockX = posX / tileSize - halfScreenX;
        else if (posX >= maxScreenX * tileSize)
            blockX = lengthArray - screenX;
        // bepaal posX buiten box

        // bepaal Yblock
        if (posY <= tileSize * halfScreenY)
            blockY = 0;
        else if (posY > tileSize * halfScreenY && posY < maxScreenY * tileSize)
            blockY = posY / tileSize - halfScreenY;
        else if (posY >= maxScreenY * tileSize)
            blockY = heightArray - screenY;
            // bepaal posY buiten box

            // half size of tilesize added is abit weird
        else if (posY > heightArray * tileSize - halfTile)
            posY = heightArray * tileSize - halfTile;
    }

    public int getPosY() {
        return blockY;
    }

    public int getPosX() {
        return blockX;
    }

    public int getRealPosY() {
        return posY;
    }

    public int getRealPosX() {
        return posX;
    }

    public Image drawPlayer() {
        return player;
    }
}
