package blocks;

import imageHandler.ImageHandler;
import javafx.scene.image.Image;

/**
 * Created by Peter-Paul on 05/12/2015.
 */
public class Block {
    Image image;
    ImageHandler imageHandler = new ImageHandler();

    public Block(String type){
        setImage(type);

    }

    private void setImage(String type){
        image = imageHandler.returnImage(type);
    }

    public Image getImage() {
        return image;
    }
}
