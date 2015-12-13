package blocks;

/**
 * Created by Peter-Paul on 05/12/2015.
 */
public class BlockFactory {
    private Block grass = new Block("grass", true);
    private Block water = new Block("water", false);
    private Block stone = new Block("stone",true);
    private Block empty = new Block("empty",false);

    public Block returnBlock(String block){
        if ("grass" == block){
            return grass;
        }
        if ("empty" == block){
            return empty;
        }
        if ("water" == block){
            return water;
        }
        if ("stone" == block){
            return stone;
        }
        else{
            return empty;
        }
    }
}
