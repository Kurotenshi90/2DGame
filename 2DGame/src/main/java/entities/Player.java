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
    private int posX = 200;
    private int posY = 200;
    private int blockX;
    private int blockY;
    private Image player;
    private int speed = 4;

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
            posX -= speed;
            if (blocks[(posX / 32)][posY / 32].isSolid()) {
                posX = (posX / 32 + 1) * 32;
            }
            if (blocks[(posX / 32)][(posY + 31) / 32].isSolid()) {
                posX = (posX / 32 +1) * 32;
            }
        }
        if (input.contains("RIGHT") || input.contains("D")) {
            posX += speed;
            if (blocks[(posX / 32) + 1][posY / 32].isSolid()) {
                posX = (posX / 32) * 32;
            }
            if (blocks[(posX / 32) + 1][(posY + 31) / 32].isSolid()) {
                posX = (posX / 32) * 32;
            }
        }
        if (input.contains("UP") || input.contains("W")) {
            posY -= speed;
            if (blocks[(posX / 32)][(posY / 32)].isSolid()) {
                posY = (posY / 32 + 1) * 32;
            }
            if (blocks[((posX + 31)/ 32)][(posY / 32)].isSolid()) {
                posY = (posY / 32 + 1) * 32;
            }
        }
        if (input.contains("DOWN") || input.contains("S")) {
            posY += speed;
            if (blocks[(posX / 32)][(posY / 32) + 1].isSolid()) {
                posY = (posY / 32) * 32;
            }
            if (blocks[((posX + 31) / 32)][(posY / 32) + 1].isSolid()) {
                posY = (posY / 32) * 32;
            }
        }
        blockX = posX / tileSize;
        blockY = posY / tileSize;

        checkBlocksToDraw(tileSize, screenX, screenY, blocks[0].length, blocks.length);
        // block(blocks, tileSize);
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
        if (posX < 0)
            posX = 0;
            // half size of tilesize added is abit weird
        else if (posX > lengthArray * tileSize - halfTile)
            posX = lengthArray * tileSize - halfTile;
        // bepaal Yblock
        if (posY <= tileSize * halfScreenY)
            blockY = 0;
        else if (posY > tileSize * halfScreenY && posY < maxScreenY * tileSize)
            blockY = posY / tileSize - halfScreenY;
        else if (posY >= maxScreenY * tileSize)
            blockY = heightArray - screenY;
        // bepaal posY buiten box
        if (posY < 0)
            posY = 0;
            // half size of tilesize added is abit weird
        else if (posY > heightArray * tileSize - halfTile)
            posY = heightArray * tileSize - halfTile;
    }

    public Block[] block(Block[][] blocks, int tileSize) {
        Block[] blocksInRanged = new Block[8];
        blockX = posX / tileSize;
        blockY = posY / tileSize;
        blocksInRanged[0] = blocks[blockX + 1][blockY];
        blocksInRanged[1] = blocks[blockX][blockY + 1];
        blocksInRanged[2] = blocks[blockX - 1][blockY];
        blocksInRanged[3] = blocks[blockX][blockY - 1];
        blocksInRanged[4] = blocks[blockX + 1][blockY + 1];
        blocksInRanged[5] = blocks[blockX - 1][blockY - 1];
        blocksInRanged[6] = blocks[blockX - 1][blockY + 1];
        blocksInRanged[7] = blocks[blockX + 1][blockY - 1];

        return blocksInRanged;
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
