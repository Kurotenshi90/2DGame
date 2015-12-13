package levelLoader;

import blocks.Block;
import blocks.BlockFactory;
import javafx.geometry.Pos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class LevelReader {


    public int[][] getMatrix() {
        return matrix;
    }

    public Block[][] returnMap() {
        Block[][] test = new Block[77][75];
        BlockFactory blockFactory = new BlockFactory();
        for (int j = 0; j < test[0].length; j++) {
            for (int i = 0; i < test.length; i++) {
                if(matrix[i][j] == 1)
                test[i][j] = blockFactory.returnBlock("grass");
                if(matrix[i][j] == 2)
                    test[i][j] = blockFactory.returnBlock("stone");
                if(matrix[i][j] == 0)
                    test[i][j] = blockFactory.returnBlock("empty");
                if(matrix[i][j] == 3)
                    test[i][j] = blockFactory.returnBlock("water");

            }
        }

        return test;
    }



    private int[][] matrix;
    private int size = -1;
    private int log10 = 0;
    private String numberFormat;

    public LevelReader(String filename) {
        try {
            readFile(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readFile(String filename) throws IOException {
        // If included in an Eclipse project.
        InputStream stream = ClassLoader.getSystemResourceAsStream(filename);
        BufferedReader buffer = new BufferedReader(new InputStreamReader(stream));

        // If in the same directory - Probably in your case...
        // Just comment out the 2 lines above this and uncomment the line
        // that follows.
        //BufferedReader buffer = new BufferedReader(new FileReader(filename));

        String line;
        int row = 0;

        while ((line = buffer.readLine()) != null) {
            String[] vals = line.trim().split("\\s+");

            // Lazy instantiation.
            if (matrix == null) {
                size = vals.length;
                matrix = new int[size][size];
                log10 = (int) Math.floor(Math.log10(size * size)) + 1;
                numberFormat = String.format("%%%dd", log10);
            }

            for (int col = 0; col < size; col++) {
                matrix[col][row] = Integer.parseInt(vals[col]);
            }

            row++;
        }
    }

}