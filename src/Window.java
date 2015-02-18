
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JApplet;

public class Window extends JApplet implements MouseListener{

    Board GameBoard;
    Image Background;
    Image SelectedBlankTile;
    Image NormalNumbers[];    
    Image FixedNumbers[];    
    Image SelectedNumbers[];
    int Selectedx;
    int Selectedy;
    boolean hasSelected;
    public void init() 
    {
        GameBoard = new Board();
        GameBoard.CreateBoard();
        GameBoard.MakePlayable(50);
        GameBoard.setFixed();
        Selectedx = Selectedy = -1;
        hasSelected = false;
        NormalNumbers = new Image[9];
        FixedNumbers = new Image[9];
        SelectedNumbers = new Image[9];
        for (int i = 1; i < 10; i++) 
        {
            try {
                NormalNumbers[i-1] = ImageIO.read(getClass().getResourceAsStream("Resources/"+i+"Tile.png"));
                FixedNumbers[i-1] = ImageIO.read(getClass().getResourceAsStream("Resources/Fixed"+i+"Tile.png"));
                SelectedNumbers[i-1] = ImageIO.read(getClass().getResourceAsStream("Resources/Selected"+i+"Tile.png"));
            } catch (IOException e) 
            {
                System.err.println("Error: Error loading images");
            }
        }
        try {
            Background = ImageIO.read(getClass().getResourceAsStream("Resources/Background.png"));
            SelectedBlankTile = ImageIO.read(getClass().getResourceAsStream("Resources/SelectedBlankTile.png"));
        } catch (IOException e) {
            System.err.println("Error: Error loading images");
        }
        this.addMouseListener(this);
        this.setFocusable(true);
    }
    
    public void paint(Graphics g)
    {
        if(GameBoard.hasWon() == true)
        {
            System.out.println("YOU WIN");
        }
        g.drawImage(Background, 0, 0, this);
        for (int i = 0; i < 9; i++) 
        {
            for (int j = 0; j < 9; j++) 
            {
                int x = 0 + (i*66);
                int y = 150 + (j*50);
                if(i>2)
                    x+=(i/3)*3;
                if(GameBoard.getValue(i, j) > 0)
                {
                    if(GameBoard.isFixed(i, j))
                        g.drawImage(FixedNumbers[GameBoard.getValue(i, j)-1], x, y, this);
                    else if(i == Selectedx-1 && j == Selectedy-1 && hasSelected)
                        g.drawImage(SelectedNumbers[GameBoard.getValue(i, j)-1], x, y, this);
                    else
                        g.drawImage(NormalNumbers[GameBoard.getValue(i, j)-1], x, y, this);
                }
                else if(i == Selectedx-1 && j == Selectedy-1 && hasSelected)
                        g.drawImage(SelectedBlankTile, x, y, this);
            }
        }
    }
    
    public void mouseClicked(MouseEvent e) {
        int x = (e.getX()/66)+1;
        int y = (e.getY()-100)/50;
        System.out.println(x + " : " +y);
        if(!hasSelected && y>0)
            hasSelected = true;
        if(hasSelected && y==0)
            hasSelected = false;
        if(hasSelected)
        {
            Selectedx = x;
            Selectedy = y;
            if(GameBoard.isFixed(x-1, y-1))
            {
                Selectedx = Selectedy = -1;
                hasSelected = false;
            }
        }
        else if(Selectedx > -1)
        {
            GameBoard.setValue(Selectedx-1, Selectedy-1, x);
            Selectedx = Selectedy = -1;
        }
        repaint();
    }

    public void mousePressed(MouseEvent e) {
        //not used
    }

    public void mouseReleased(MouseEvent e) {
        //not used
    }

    public void mouseEntered(MouseEvent e) {
        //not used
    }

    public void mouseExited(MouseEvent e) {
        //not used
    }
    
    
}
