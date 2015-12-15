package imageHandler;

import blocks.Block;
import javafx.scene.image.Image;

/**
 * Created by Peter-Paul on 05/12/2015.
 */
public class ImageHandler {

    private final Image grass = new Image("grass.png");
    private final Image empty = new Image( "empty.png" );
    private final Image water = new Image( "water.png" );
    private final Image stone = new Image( "stone.png" );
    private final Image cloud = new Image( "cloud.png" );



    private final Image grassOnDirt = new Image( "grassdirt.png" );
    private final Image space = new Image( "background.png" );

    public Image returnImage(String type){
        if ("grass" == type){
            return returnGrass();
        }
        if ("space" == type){
            return returnSpace();
        }
        if ("empty" == type){
            return returnEmpty();
        }
        if ("water" == type){
            return returnWater();
        }
        if ("stone" == type){
            return returnStone();
        }
        if ("cloud" == type){
            return returnCloud();
        }
        if ("grassdirt" == type){
            return returnGrassOnDirt();
        }
        else {
            return returnEmpty();
        }
    }
    public Image returnGrassOnDirt() {
        return grassOnDirt;
    }
    public Image returnCloud() {
        return cloud;
    }
    public  Image returnSpace(){
        return space;
    }
    public  Image returnGrass(){
        return grass;
    }
    public  Image returnStone(){
        return stone;
    }
    public  Image returnWater(){
        return water;
    }
    public  Image returnEmpty(){
        return empty;
    }

}
