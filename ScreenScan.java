package PersonalProjects;

import java.awt.*;
import java.awt.event.InputEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ScreenScan {
    int scanStartX;
    int scanStartY;
    int incX;
    int incY;
    int flux;
    boolean alive;
    ArrayList<Integer> foundPegs;



    //Constructor
    public ScreenScan(int startX, int startY, int incrementX, int incrementY, int fluxAmount){
        scanStartX = startX;
        scanStartY = startY;
        incX = incrementX;
        incY = incrementY;
        flux = fluxAmount;
        foundPegs = new ArrayList<Integer>();
        alive = true;

    }

    //Scans through the screen and keeps track of any area it finds that has orange
    public void Scan(){


        alive = false;
        try {
            Robot robot = new Robot();




            for (int y = scanStartY; y <= 540; y += incY) {
                for (int x = scanStartX; x <= 720; x += incX)   {


                        //robot.mouseMove(x, y);
                        int randomNumber = (int)(Math.random() * flux);
                        Color color = robot.getPixelColor(x, y + randomNumber);

                        //Checks for orange

                        if (colorCheck(x, y) == true){
                            foundPegs.add(x);
                            foundPegs.add(y);
                            shoot(x, y);
                        }

                }
            }
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    //returns the array of found pegs
    public ArrayList<Integer> getFoundPegs() {
        return foundPegs;
    }

    //Shoots at given coordinate
    public void shoot(int x, int y){
        try {
            Robot robot = new Robot();

                robot.mouseMove(x, y);
                try {
                    Thread.sleep(200);
                } catch (Exception e) {}
                robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);

        }catch (AWTException e) {
            e.printStackTrace();
        }

    }


    //Checks if the checked spot has the correct color of orange
    public boolean colorCheck(int x, int y){

        boolean orange = false;

        try {
            Robot robot = new Robot();
            Color color = robot.getPixelColor(x, y);

            if (color.getRed() > 125 && color.getGreen() < 85 && color.getBlue() < 45 && color.getGreen() > color.getBlue() && color.getRed() > color.getGreen() && color.getBlue() > 10 && color.getGreen() > 20 && (color.getRed() / color.getGreen()) > 1.5) {
                //sets to scan again if orange is found
                orange = true;

                System.out.println("FOUND ORANGE");
            }

        } catch (AWTException e) {
            e.printStackTrace();
        }

        return orange;
    }

    //Saves the found pegs
    public void save(String filename) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(new FileOutputStream(filename));
        for (int i = 0; i < foundPegs.size(); i += 2)
            pw.println("x:" + foundPegs.get(i) + " y:" + foundPegs.get(i + 1));
        pw.close();
    }
}
