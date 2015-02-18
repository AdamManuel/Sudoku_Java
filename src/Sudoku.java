
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JApplet;
import javax.swing.JFrame;


public class Sudoku {
    public static void Start()
    {
        //Set Name
        JFrame frame = new JFrame("Soduko - Adam Manuel");
        //Set Icon
//        try {
//            frame.setIconImage(ImageIO.read(Suduko.class.getResourceAsStream("resources/Tile1024.png")));
//        } catch (IOException ex) {
//            Logger.getLogger(Suduko.class.getName()).log(Level.SEVERE, null, ex);
//        }
        frame.setSize(605, 630);
        JApplet applet = new Window();
        applet.init();
        applet.setFocusable(true);
        applet.start();
        frame.add(applet);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
    }
}
