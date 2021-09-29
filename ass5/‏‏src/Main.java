import biuoop.DrawSurface;

import java.awt.Color;
import java.util.Random;

import biuoop.GUI;
import biuoop.Sleeper;

/**
 * The type Main.
 */
public class Main {
    /**
     * Random int.
     *
     * @param min the min
     * @param max the max
     * @return the int
     */
    public static int random(int min, int max) {
        return new Random().nextInt((max - min) + 1) + min;
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        GameEnvironment g = new GameEnvironment();
        int length = 5;

        // g.addCollidable(new Block(new Rectangle(new Point(80, 36), 100, 100)));
        //  g.addCollidable(new Block(new Rectangle(new Point(30, 36), 100, 100)));
        //  g.addCollidable(new Block(new Rectangle(new Point(240, 36), 100, 100)));
        //  g.addCollidable(new Block(new Rectangle(new Point(230, 230), 100, 100)));

        for (int i = 0; i < length; i++) {
            g.addCollidable(new Block(new Rectangle(new Point((double) random(100, 500),
                    (double) random(100, 200)), 100, 100)/*, new Color(127, 127, 127), 0)*/));
        }
        GUI gui = new GUI("title", 600, 400);
        g.addCollidable(new Block(new Rectangle(new Point(0, 0), 395, 5)
                /*new Color(255, 255, 255), 0*/));
        g.addCollidable(new Block(new Rectangle(new Point(5, 0), 5, 595)
                /*new Color(255, 255, 255), 0*/));
        g.addCollidable(new Block(new Rectangle(new Point(595, 5), 395, 5)
                /*new Color(255, 255, 255), 0*/));
        g.addCollidable(new Block(new Rectangle(new Point(0, 395), 5, 595)
                /*new Color(255, 255, 255), 0*/));

        Ball ball = new Ball(20, 20, 5, Color.RED, 600, 400);
        ball.setVelocity(new Velocity(4, 4)); //***if velocity is 0 its doesn't work*****\\
        ball.setGameEnvironment(g);
        Sleeper sleeper = new Sleeper();
        while (true) {
            DrawSurface d = gui.getDrawSurface();
            g.drawOnrec(d);
            ball.moveOneStep();
            ball.drawOn(d);
            sleeper.sleepFor(8);  // wait for 3 milliseconds.
            gui.show(d);
        }
    }
}