package imageHandler;

import blocks.Block;
import javafx.scene.image.Image;

/**
 * Created by Peter-Paul on 05/12/2015.
 */
public class ImageHandler {

    private Image grass = new Image("grass.png");
    private Image empty = new Image( "empty.png" );
    private Image water = new Image( "water.png" );
    private Image stone = new Image( "stone.png" );
    private Image space = new Image( "space.png" );

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
        else {
            return returnEmpty();
        }
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
