package blocks;

import imageHandler.ImageHandler;
import javafx.scene.image.Image;

/**
 * Created by Peter-Paul on 05/12/2015.
 */
public class Block {
	private final boolean isSolid;
    private final Image image;
    private final ImageHandler imageHandler = new ImageHandler();

    public Block(String type, boolean isSolid){
    	image = imageHandler.returnImage(type);
    	this.isSolid = isSolid;
    }

    public Image getImage() {
        return image;
    }

	public boolean isSolid() {
		return isSolid;
	}
    
}
