package PersonalProjects;

import java.awt.*;
import java.awt.event.InputEvent;

//This is to expand out the project later to check if the level is finished
public class EndCheck {
    int xCheck;
    int yCheck;
    boolean finished;

    public EndCheck(int x, int y){
        xCheck = x;
        yCheck = y;
        finished = false;
    }

    public void EndCheck(){
        try {
            //596, 42
            //501, 108
            Robot robot2 = new Robot();
            Color color = robot2.getPixelColor(xCheck, yCheck);
            System.out.println("Red   = " + color.getRed());
            System.out.println("Green = " + color.getGreen());
            System.out.println("Blue  = " + color.getBlue());

            if (color.getRed() == 99 && color.getGreen() == 247 && color.getBlue() == 255){
                System.out.println("END DETECTED");
                robot2.mouseMove(495, 485);
                try {
                    Thread.sleep(500);
                } catch (Exception e) {}
                robot2.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                try {
                    Thread.sleep(300);
                } catch (Exception e) {}
                robot2.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                try {
                    Thread.sleep(200);
                } catch (Exception e) {}
                robot2.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                finished = true;
            }
            //Red   = 101
            //Green = 245
            //Blue  = 249


        }
        catch (AWTException e) {
            e.printStackTrace();
        }
        ;
    }

    public boolean getFinished(){
        return finished;
    }
}
