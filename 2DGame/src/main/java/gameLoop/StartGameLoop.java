package gameLoop;

import imageHandler.ImageHandler;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

/**
 * Created by Peter-Paul on 05/12/2015.
 */
public class StartGameLoop {
    final ImageHandler imageHandler = new ImageHandler();
    Canvas canvas = new Canvas( 512, 512 );
    final GraphicsContext gc = canvas.getGraphicsContext2D();
    final long startNanoTime = System.nanoTime();
    public void startGame(){
        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                double t = (currentNanoTime - startNanoTime) / 1000000000.0;

                double x = 232 + 128 * Math.cos(t);
                double y = 232 + 128 * Math.sin(t);

                // background image clears canvas
                gc.drawImage( imageHandler.returnSpace(), 0, 0 );
                gc.drawImage( imageHandler.returnEmpty(), x, y );
                gc.drawImage( imageHandler.returnEmpty(), 196, 196 );
            }
        }.start();
    }
}
