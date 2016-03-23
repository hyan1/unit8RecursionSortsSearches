import java.awt.*;
import javax.swing.JPanel;

public class FractalTreeComponent extends JPanel
{    
    private double diffOfSize = .85;
    private double minSize = 5.0;
    private double aAngle = Math.toRadians(60);
    
    private final int PANEL_WIDTH = 700;
    private final int PANEL_HEIGHT = 700;
    private int current; 

    public FractalTreeComponent (int currentOrder)
    {
        current = currentOrder;
        setBackground (Color.black);
        setPreferredSize (new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
    }

    public void drawFractal (double angle, int x1, int y1, int x2, int y2, Graphics page)
    {
        double length1 = Math.sqrt((Math.pow(x2-x1, 2))+(Math.pow(y2-y1, 2)));

        int x3, y3; 
        int x4, y4; 

        double rightAngle, leftAngle;

        if(length1<minSize)
        {
            return;
        }
        else 
        {
            page.drawLine(x1, y1, x2, y2);

            double length2 = length1 * diffOfSize;
            
            rightAngle = angle + aAngle;
            x3 = x2 + (int)(length2*Math.sin(rightAngle));
            y3 = y2 + (int)(length2*Math.cos(rightAngle));
            page.drawLine(x2, y2, x3, y3);           
            leftAngle = angle - aAngle;
            x4 = x2 - (int)(length2*Math.sin(leftAngle));
            y4 = y2 - (int)(length2*Math.cos(leftAngle));
            page.drawLine(x2, y2, x4, y4);
            
            drawFractal(rightAngle+30, x2, y2, x3, y3, page);
            drawFractal(leftAngle-30, x2, y2, x4, y4, page);
        }         
    }
   
    public void paintComponent (Graphics page)
    {
        super.paintComponent (page);
        page.setColor (Color.green);
        
        int startX = 350, startY = 550;
        int endX = 350, endY = 460;
        drawFractal(Math.toRadians(90), startX, startY, endX, endY, page);
    }

    public void setOrder (int order)
    {
        current = order;
    }

    public int getOrder ()
    {
        return current;
    }
}