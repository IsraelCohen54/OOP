import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;
import java.util.Random;

/**
 * the class run multiplied frames and two separate rectangle windows with balls.
 */
public class MultipleFramesBouncingBallsAnimation {
    /**
     * converting array from strings to integers.
     *
     * @param numbers = getting the strings array.
     * @return array of integers.
     */
    public static int[] convertStringsToInts(String[] numbers) {
        int[] arr = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            arr[i] = Integer.parseInt(numbers[i]);
        }
        return arr;
    }

    /**
     * generating array of balls with radiuses and speed as asked.
     *
     * @param radiuses       an array with one half radiuses of the balls.
     * @param xhorizonBound  = the horizontal Bound of the balls.
     * @param yverticalBound = the vertical Bound of the balls.
     * @param recbound       = the width of the rectangle.
     * @param startingbound  = the hight of the rectangle.
     * @return array of balls including the radiuses and speed.
     */
    public Ball[] generatingBalls(int[] radiuses, int xhorizonBound,
                                  int yverticalBound, int recbound, int startingbound) {
        Random rand = new Random();
        Ball[] balls = new Ball[radiuses.length];
        Color[] color = new Color[radiuses.length];
        for (int i = 0; i < radiuses.length; ++i) {
            color[i] = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
            balls[i] = new Ball(radiuses[i] + startingbound
                    + rand.nextInt(startingbound + recbound - 2 * radiuses[i]),
                    radiuses[i] + startingbound + rand.nextInt(startingbound + recbound - 2 * radiuses[i]),
                    radiuses[i], color[i], startingbound + recbound, startingbound + recbound);
            Velocity t = Velocity.fromAngleAndSpeed(rand.nextInt(
                    360), 20 / (double) balls[i].getSize());
            balls[i].setVelocity(t);
        }
        return balls;
    }

    /**
     * draw moving ball on a window.
     *
     * @param xhorizonBound  = the horizontal Bound of the balls.
     * @param yverticalBound = the vertical Bound of the balls.
     * @param args           = the radiuses of the balls.
     * @param recbound       = the width of the rectangle.
     * @param startingbound  = the hight of the rectangle.
     */
    public void drawBalls(String[] args, int xhorizonBound, int yverticalBound, int recbound, int startingbound) {
        GUI gui = new GUI("Mutiframes", xhorizonBound, yverticalBound);
        int[] radiuses = convertStringsToInts(args); //half of the raduises
        Sleeper sleeper = new Sleeper();
        int[] numbers = convertStringsToInts(args);
        int[] firsthalfradiueses = new int[numbers.length / 2];
        int[] secondhalfradiueses = new int[numbers.length / 2];
        for (int k = 0; k < numbers.length / 2; k++) {
            firsthalfradiueses[k] = numbers[k];
        }
        Ball[] balls1 = generatingBalls(firsthalfradiueses, xhorizonBound, yverticalBound, 450, 50);
        for (int k = 0; k < numbers.length / 2; k++) {
            secondhalfradiueses[k] = numbers[k + numbers.length / 2];
        }
        Ball[] balls2 = generatingBalls(secondhalfradiueses, xhorizonBound, yverticalBound, 150, 450);
        while (true) {
            DrawSurface d = gui.getDrawSurface();
            d.setColor(Color.gray);
            d.fillRectangle(50, 50, 450, 450);
            d.setColor(Color.yellow);
            d.fillRectangle(450, 450, 150, 150);
            for (int i = 0; i < balls1.length; ++i) {
                int k = 1;
                balls1[i].moveOneStep1(k);
                balls1[i].drawOn(d);
            }
            for (int i = 0; i < balls2.length; ++i) {
                int k = 2;
                balls2[i].moveOneStep1(k);
                balls2[i].drawOn(d);
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
        MultipleFramesBouncingBallsAnimation x = new MultipleFramesBouncingBallsAnimation();
        int yverticalBound = 700;
        int xhorizonBound = 700;
        int lenghtrectangle1 = 450;
        int start1 = 50;
        int lenghtrectangle2 = 600;
        int startfrom2 = 450;
        x.drawBalls(args, xhorizonBound, yverticalBound, lenghtrectangle2, startfrom2);
    }
}