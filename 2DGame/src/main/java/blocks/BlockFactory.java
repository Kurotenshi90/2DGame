package blocks;

/**
 * Created by Peter-Paul on 05/12/2015.
 */
public class BlockFactory {
    private Block grass = new Block("grass", true, 1);
    private Block water = new Block("water", false, 2);
    private Block stone = new Block("stone",true, 3);
    private Block empty = new Block("empty",false, 0);
    private Block grassdirt = new Block("grassdirt",true, 4);
    private Block cloud = new Block("cloud",true, 5);

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
        if ("grassdirt" == block){
            return grassdirt;
        }
        if ("cloud" == block){
            return cloud;
        }
        else{
            return empty;
        }
    }
}
