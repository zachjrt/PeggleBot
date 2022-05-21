package PersonalProjects;


import java.awt.*;
import java.awt.event.InputEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.Random;



public class Main {


    //Clicks the mouse at the given coordinates
    public static void click(int x, int y){
        try {
            Robot clickRobot = new Robot();
            clickRobot.mouseMove(x,y);
            try {
                Thread.sleep(300);
            } catch (Exception e) {}
            clickRobot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            clickRobot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        }
        catch (AWTException e) {
            e.printStackTrace();
        }


    }





    public static void main(String[] args) throws FileNotFoundException {



        //Checks these two points to tell if it is the end of the level
        EndCheck endCheck = new EndCheck(423, 183);
        EndCheck failCheck = new EndCheck(431, 172);


        //ScreenScan s0 = new ScreenScan(100, 120, 4, 6, 3);
        //ScreenScan s = new ScreenScan(100, 100, 6, 6, 2);
        //ScreenScan s2 = new ScreenScan(100, 120, 12, 12, 0);

        //Fastest effective
        ScreenScan s = new ScreenScan(210, 250, 4, 37, 1);


        int tries = 1;
        while (endCheck.finished != true || failCheck.finished != true){
            while (tries < 10){

                endCheck.EndCheck();
                failCheck.EndCheck();
                //Scans
                s.Scan();
                //Saves found pegs
                s.save("Try" + tries + ".txt");
                ArrayList<Integer> pegsToShoot = s.getFoundPegs();
                while (pegsToShoot.size() >= 1) {

                    int xFound = pegsToShoot.get(0);
                    int yFound = pegsToShoot.get(1);
                    if (s.colorCheck(xFound, yFound)){
                        click(xFound, yFound);
                        System.out.println("FIRE!");
                    }
                    else if (s.colorCheck(xFound, yFound) == false){
                        pegsToShoot.remove(0);
                        pegsToShoot.remove(0);
                        System.out.println("REMOVED");

                    }


                }
                tries++;

            }


        }


    }




}
