/**
 * The tetris class contains all the method to run a game of tetris. This class
 * contains methods that move the block according to the user input of the
 * arrows, a method that plays a full game of tetris, and a method which checks
 * for completed rows after a tetrad has been dropped and clears all the
 * completed rows.
 * 
 * @author Kevin Tzeng
 * @version 14 March 2016
 *
 */
public class Tetris implements ArrowListener
{
    private MyBoundedGrid<Block> grid;
    private BlockDisplay display;
    private Tetrad currentTetrad;
    int speed;
    int score;

    /**
     * The constructor for the Tetris class which takes in a row and a column
     * number.
     * 
     * @param row Specifies the number of rows in this tetris game.
     * @param col Specifies the number of columns in this tetris game.
     */
    public Tetris(int row, int col)
    {
        speed = 0;
        score = 0;
        grid = new MyBoundedGrid<Block>(row, col);
        currentTetrad = new Tetrad(grid);
        display = new BlockDisplay(grid);
        display.setArrowListener(this);
        display.setTitle("Tetris");
        display.showBlocks();
        play();
    }


    /**
     * The main method which creates a tetris object to start the game.
     * 
     * @param args The arguments.
     */
    public static void main(String[] args)
    {
        Tetris game = new Tetris(20, 10);
    }

    /**
     * The tetrad is rotated.
     */
    public void upPressed()
    {
        currentTetrad.rotate();
        display.showBlocks();
    }

    /**
     * The tetrad is translated down by one.
     */
    public void downPressed()
    {
        currentTetrad.translate(1, 0);
        display.showBlocks();
    }

    /**
     * The tetrad is translated to the left by one.
     */
    public void leftPressed()
    {
        currentTetrad.translate(0, -1);
        display.showBlocks();

    }

    /**
     * The tetrad is translated to the right by one.
     */
    public void rightPressed()
    {
        // TODO Auto-generated method stub
        currentTetrad.translate(0, 1);
        display.showBlocks();

    }

    /**
     * Plays a game of tetris and stops when the termination condition is met.
     */
    public void play()
    {
        while (true)
        {
            try
            {
                if (speed >= -250)
                {
                    Thread.sleep(speed + 500);
                }
                else
                {
                    Thread.sleep(250);
                }
                if (!currentTetrad.translate(1, 0))
                {
                    clearCompletedRows();
                    System.out.println(score);
                    currentTetrad = new Tetrad(grid);
                    speed -= 10;
                }
                display.showBlocks();
            } 
            catch (InterruptedException e)
            {//empty
            }
        }
    }

    /**
     * precondition: 0 <= row < number of rows Checks if a given row is complete
     * meaning there are blocks contained in every column with the given row
     * number.
     * 
     * @param row Specifies the row number which is being checked for
     * completion.
     * @return Returns true if every cell in the given row is occupied; eturns
     * false otherwise.
     */
    private boolean isCompleteRow(int row)
    {
        for (int i = 0; i < grid.getNumCols(); i++)
        {
            if (grid.get(new Location(row, i)) == null)
                return false;
        }
        return true;
    }

    /**
     * precondition: 0 <= row < number of rows; given row is full of blocks
     * postcondition: Every block in the given row has been removed, andevery
     * block above row has been moved down one row.
     * 
     * @param row Specifies the row from which all the blocks will be removed.
     */
    private void clearRow(int row)
    {
        Location[] rowLocs = new Location[grid.getNumCols()];

        for (int c = 0; c < grid.getNumCols(); c++)
            rowLocs[c] = new Location(row, c);

        Location[] shiftLocs = new Location[grid.getNumCols() * row];
        for (int r = 0; r < row; r++)
            for (int c = 0; c < grid.getNumCols(); c++)
            {
                shiftLocs[(row - 1 - r) * grid.getNumCols() + c] = new Location(
                        r, c);
            }
        display.showBlocks();

        for (Location loc : rowLocs)
            grid.get(loc).removeSelfFromGrid();

        for (Location loc : shiftLocs)
            if (grid.get(loc) != null)
                grid.get(loc).moveTo(
                        new Location(loc.getRow() + 1, loc.getCol()));
        currentTetrad = null;
        score++;
    }

    /**
     * Goes through each row and if any given row is complete this method then
     * clears the appropriate row. Postcondition: all completed rows have been
     * cleared.
     */
    private void clearCompletedRows()
    {
        for (int r = 0; r < grid.getNumRows(); r++)
        {
            if (isCompleteRow(r))
            {
                clearRow(r);
            }
        }
    }

}
