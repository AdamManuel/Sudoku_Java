public class Board 
{
    private int[][] BoardValues;
    private boolean[][] FixedValues;
    public Board()
    {
        CreateBoard();
        setFixed();
    }
    
    public void CreateBoard()
    {
        BoardValues = new int[9][9];
        FixedValues = new boolean[9][9];
        for (int i = 0; i < 9; i++) 
        {
            for (int j = 0; j < 9; j++) 
            {
                int trys = 0;
                int totry = (int)(Math.random()*9+1);
                while(!Check3x3(totry, i, j) || !CheckColumn(totry, i) || !CheckRow(totry, j))
                {
                    totry = (int)(Math.random()*9+1);
                    trys ++;
                    if(trys > 20)
                    {
                        if(j == 0)
                        {
                            for (int k = 0; k < 9; k++) 
                            {
                                BoardValues[i-1][k] = 0;
                            }
                            i--;
                        }
                        else
                        {
                            for (int k = 0; k < 9; k++) 
                            {
                                BoardValues[i][k] = 0;
                            }
                        }
                        j = 0;
                    }
                }
                BoardValues[i][j] = totry;
            }
        }
    }
    
    public void setFixed()
    {
        for (int i = 0; i < 9; i++) 
        {
            for (int j = 0; j < 9; j++) 
            {
                FixedValues[i][j] = (BoardValues[i][j]>0);
            }
        }
    }
   
    /*
    1 - 2 - 3
    4 - 5 - 6
    7 - 8 - 9
    */
    public boolean Check3x3(int num, int x, int y)
    {
        String toCheck = "";
        x = ((x/3)+1)*3;
        y = ((y/3)+1)*3;
        for (int i = 1; i <= 3; i++) 
        {
            for (int j = 1; j <= 3; j++) 
            {
                toCheck+=BoardValues[x-i][y-j];
            }
        }
        return !toCheck.contains(num+"");
    }
    
    public void MakePlayable(int NumOfSpaces)
    {
        for (int i = 0; i < NumOfSpaces; i++) 
        {
            int x = (int)(Math.random()*9);
            int y = (int)(Math.random()*9);
            while(BoardValues[x][y] == 0)
            {
                x = (int)(Math.random()*9);
                y = (int)(Math.random()*9);
            }
            BoardValues[x][y] = 0;
        }
    }
    
    public boolean CheckColumn(int num, int Column)
    {
        String toCheck = "";
        for (int i = 0; i < BoardValues.length; i++) 
        {
            toCheck+=BoardValues[Column][i];
        }
        return !(toCheck.contains(num+""));
    }
    
    public boolean CheckRow(int num, int Row)
    {
        String toCheck = "";
        for (int i = 0; i < BoardValues.length; i++) 
        {
            toCheck+=BoardValues[i][Row];
        }
        return !(toCheck.contains(num+""));
    }
    
    public boolean hasWon()
    {
        //Checks if board is full
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if(BoardValues[i][j] < 1)
                    return false;
            }
        }
        //Check Rows And Columns
        for (int i = 0; i < 3; i++) 
        {
            int[] numsRow = new int[9];
            int[] numsCol = new int[9];
            for (int j = 0; j < 9; j++) 
            {
                if(numsRow[(BoardValues[i][j])-1] == 0)
                    numsRow[(BoardValues[i][j])-1] = 1;
                else
                    return false;
                if(numsCol[(BoardValues[j][i])-1] == 0)
                    numsCol[(BoardValues[j][i])-1] = 1;
                else
                    return false;
            }
        }
        //Check 3x3s
        for (int Largex = 0; Largex < 3; Largex++) 
        {
            for (int Largey = 0; Largey < 3; Largey++) 
            {   
                int[] nums = new int[9];
                for (int Smallx = 0; Smallx < 3; Smallx++) 
                {
                    for (int Smally = 0; Smally < 3; Smally++) 
                    {
                        int tempx = (Largex*3)+Smallx;
                        int tempy = (Largey*3)+Smally;
                        if(nums[(BoardValues[tempx][tempy])-1] == 0)
                            nums[(BoardValues[tempx][tempy])-1] = 1;
                        else
                            return false;
                    }
                }
            }
        }
        return true;
    }
    
    public int getValue(int x, int y)
    {
        return BoardValues[x][y];
    }
    
    public boolean isFixed(int x, int y)
    {
        return FixedValues[x][y];
    }
    
    public void setValue(int x, int y, int value)
    {
        BoardValues[x][y] = value;
    }
    
    public String toString()
    {
        String toReturn = "";
        for (int i = 0; i < 9; i++) 
        {
            for (int j = 0; j < 9; j++) 
            {
                if(BoardValues[i][j] == 0)
                    toReturn += " ";
                else
                    toReturn += BoardValues[i][j];
                if(j == 2 || j == 5)
                    toReturn += "|";
            }
            if(i == 2 || i == 5)
                toReturn +="\n---+---+---";
            toReturn+="\n";
        }
        return toReturn;
    }
}
