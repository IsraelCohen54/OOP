import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;
import java.util.Random;

/**
 * class for generating multiple bouncing balls.
 */
public class MultipleBouncingBallsAnimation {
    /**
     * converting array from strings to integers.
     *
     * @param numbers = getting the strings array.
     * @return array of integers.
     */
    public int[] convertStringsToInts(String[] numbers) {
        int[] arr = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            arr[i] = Integer.parseInt(numbers[i]);
        }
        return arr;
    }

    /**
     * generating array of balls with radiuses and speed as asked.
     *
     * @param radiuses       an array with the specified radiuses of the balls.
     * @param xhorizonBound  = the horizontal Bound of the balls.
     * @param yverticalBound = the vertical Bound of the balls.
     * @return array of balls including the radiuses and speed.
     */
    public Ball[] generatingBalls(int[] radiuses, int xhorizonBound, int yverticalBound) {
        Random rand = new Random();
        Ball[] balls = new Ball[radiuses.length];
        Color[] color = new Color[radiuses.length];
        for (int i = 0; i < radiuses.length; ++i) {
            color[i] = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
            balls[i] = new Ball(radiuses[i] + rand.nextInt(xhorizonBound - 2 * radiuses[i]),
                    radiuses[i] + rand.nextInt(yverticalBound - 2 * radiuses[i]),
                    radiuses[i], color[i], xhorizonBound, yverticalBound);
            if (balls[i].getSize() >= 50) {
                Velocity t = new Velocity(1, 1);
            } else {
                Velocity t = Velocity.fromAngleAndSpeed(rand.nextInt(360), 5 / (double) balls[i].getSize());
                balls[i].setVelocity(t);
            }
        }
        return balls;
    }

    /**
     * draw moving ball on a window.
     *
     * @param xhorizonBound  = the horizontal Bound of the balls.
     * @param yverticalBound = the vertical Bound of the balls.
     * @param args           = strings array containing numbers (radiuses).
     */
    public void drawBalls(String[] args, int xhorizonBound, int yverticalBound) {
        int[] radiuses = convertStringsToInts(args);
        Ball[] balls = generatingBalls(radiuses, xhorizonBound, yverticalBound);
        GUI gui = new GUI("title", xhorizonBound, yverticalBound);
        Sleeper sleeper = new Sleeper();
        while (true) {
            DrawSurface d = gui.getDrawSurface();
            for (int i = 0; i < balls.length; ++i) {
                balls[i].moveOneStep();
                balls[i].drawOn(d);
            }
            sleeper.sleepFor(5);  // wait for 5 milliseconds.
            gui.show(d);
        }
    }

    /**
     * the method run the program.
     *
     * @param args this array stores the user's input.
     */
    public static void main(String[] args) {
        MultipleBouncingBallsAnimation x = new MultipleBouncingBallsAnimation();
        int yverticalBound = 700;
        int xhorizonBound = 700;
        x.drawBalls(args, xhorizonBound, yverticalBound);
    }
}
