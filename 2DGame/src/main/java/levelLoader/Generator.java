package levelLoader;

import General.GlobalValues;
import blocks.Block;
import blocks.BlockFactory;

public class Generator {
	private Block[][] map;
	private BlockFactory blockFactory = new BlockFactory();

	public Block[][] generateMap(int xSize, int ySize) {
		map = new Block[xSize][ySize];
		int fixedAir = ySize / (100 / GlobalValues.minAirBlockHeightPercentage);
		if (fixedAir < GlobalValues.minAirBlockHeightPercentage)
			fixedAir = GlobalValues.minAirBlockHeightPercentage;
		int grass = ySize - fixedAir;
		for (int x = 0; x < xSize; x++)
			for (int y = 0; y < ySize; y++) {
				if(y <= (fixedAir)){
					map[x][y] = blockFactory.returnBlock("empty");
				}else{
					if(x%4 == 0){
						map[x][y] = blockFactory.returnBlock("grass");

					}else{
					map[x][y] = blockFactory.returnBlock("stone");
					}
				}
			}

		return map;
	}

}
