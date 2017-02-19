import java.awt.Color;

/**
 * Used to create the seven blocks used in the game. 
 * 
 * @author Kevin Tzeng 
 * @version 14 March 2016
 *
 */
public class Tetrad
{
    private Block[] blocks;
    private MyBoundedGrid<Block> myGrid;

    /**
     * Adds the blocks on the given grid to the given array of locations.
     * Precondition: locs is an array of location objects of length 4.
     * 
     * @param grid Specifies the grid where the blocks will be placed
     * @param locs Specifies the array of locations where the blocks will be
     * added to.
     */
    private void addToLocations(MyBoundedGrid<Block> grid, Location[] locs)
    {
        for (int i = 0; i < blocks.length; i++)
        {

            blocks[i].putSelfInGrid(grid, locs[i]);
        }
    }

    /**
     * The constructor for a Tetrad object which takes in a grid and adds a
     * random one of the seven tetrad objects.
     * 
     * @param grid The grid that the objects will be added to.
     */
    public Tetrad(MyBoundedGrid<Block> grid)
    {
        myGrid = grid;
        blocks = new Block[4];
        for (int i = 0; i < blocks.length; i++)
        {
            blocks[i] = new Block();
        }
        Color color;
        Location[] locs = new Location[4];
        int shape = (int) (Math.random() * 7);
        if (shape == 0)
        {
            color = Color.RED;
            locs[0] = new Location(2, 4);
            locs[1] = new Location(1, 4);
            locs[2] = new Location(0, 4);
            locs[3] = new Location(3, 4);
            addToLocations(myGrid, locs);
            for (Block bl : blocks)
            {
                bl.setColor(color);
            }
        }
        else if (shape == 1)
        {
            color = Color.GRAY;
            locs[0] = new Location(0, 5);
            locs[1] = new Location(0, 4);
            locs[2] = new Location(0, 6);
            locs[3] = new Location(1, 5);
            addToLocations(myGrid, locs);
            for (Block bl : blocks)
            {
                bl.setColor(color);
            }
        }
        else if (shape == 2)
        {
            color = Color.CYAN;
            locs[0] = new Location(0, 4);
            locs[1] = new Location(0, 5);
            locs[2] = new Location(1, 4);
            locs[3] = new Location(1, 5);
            addToLocations(myGrid, locs);
            for (Block bl : blocks)
            {
                bl.setColor(color);
            }
        }
        else if (shape == 3)
        {
            color = Color.YELLOW;
            locs[0] = new Location(2, 4);
            locs[1] = new Location(0, 4);
            locs[2] = new Location(1, 4);
            locs[3] = new Location(2, 5);
            addToLocations(myGrid, locs);
            for (Block bl : blocks)
            {
                bl.setColor(color);
            }
        }
        else if (shape == 4)
        {
            color = Color.MAGENTA;
            locs[0] = new Location(2, 5);
            locs[1] = new Location(0, 5);
            locs[2] = new Location(1, 5);
            locs[3] = new Location(2, 4);
            addToLocations(myGrid, locs);
            for (Block bl : blocks)
            {
                bl.setColor(color);
            }
        }
        else if (shape == 5)
        {
            color = Color.BLUE;
            locs[0] = new Location(0, 4);
            locs[1] = new Location(0, 5);
            locs[2] = new Location(1, 3);
            locs[3] = new Location(1, 4);
            addToLocations(myGrid, locs);
            for (Block bl : blocks)
            {
                bl.setColor(color);
            }
        }
        else if (shape == 6)
        {
            color = Color.GREEN;
            locs[0] = new Location(0, 5);
            locs[1] = new Location(0, 4);
            locs[2] = new Location(1, 5);
            locs[3] = new Location(1, 6);
            addToLocations(myGrid, locs);
            for (Block bl : blocks)
            {
                bl.setColor(color);
            }
        }
    }

    /**
     * Removes all the blocks in this tetrad object's internal array, adds the
     * locations of those block object to an array and returns that array.
     * precondition: Blocks are in the grid. Postcondition: Returns old
     * locations of blocks. Blocks have been removed from grid.
     * 
     * @return The location of all the block objects that were removed from the
     * grid.
     */
    private Location[] removeBlocks()
    {
        Location[] oldLocs = new Location[4];
        int i = 0;
        for (Block b : blocks)
        {
            oldLocs[i] = b.getLocation();
            i++;
            b.removeSelfFromGrid();
        }
        return oldLocs;
    }

    /**
     * Checks if all the locations contained in the given array are valid in the
     * grid and are also unoccupied. Returns true if each of the locs in the
     * given Location array is valid and empty in the grid; false otherwhise.
     * 
     * @param grid The grid which will be checked for the given array of
     * locations.
     * @param locs The array of location objects that will be checked for
     * validity.
     * @return true if each of the locs in the given Location array is valid and
     * empty in the grid; false otherwhise.
     */
    private boolean areEmpty(MyBoundedGrid<Block> grid, Location[] locs)
    {
        for (Location l : locs)
        {
            if (!(grid.isValid(l) && grid.get(l) == null))
            {
                return false;
            }
        }
        return true;
    }

    /**
     * Attempts to rotate this tetrad clockwise by 90 degrees about its center,
     * if necessary positions are empty; returns true if successful and false
     * otherwhise.
     * 
     * @return true if the attempt to rotate this tetrad clockwise by 90 degrees
     * about its center is successful and false otherwhise.
     */
    public boolean rotate()
    {
        MyBoundedGrid<Block> gr = blocks[0].getGrid();

        Location[] oldLocs = removeBlocks();
        Location[] newLocs = new Location[blocks.length];

        int row = oldLocs[newLocs.length/2].getRow();
        int col = oldLocs[newLocs.length/2].getCol();
        for(int i=0; i<newLocs.length; i++)
        {
            newLocs[i] = new Location(row-col+oldLocs[i].getCol(), row+col-oldLocs[i].getRow());
        }
        if (!areEmpty(gr, newLocs))
        {
            addToLocations(myGrid, oldLocs);
            return false;
        }
        addToLocations(myGrid, newLocs);
        return true;
    }

    /**
     * Attempts to move this tetrad deltaRow rows down and deltaCol columns to
     * the righ, if those positions are valid and empty, this method returns
     * true. Otherwhise returns false. If true is returned than a change in this
     * tetrad's location takes place.
     * 
     * @param deltaRow The number of rows down that this tetrad is going to
     * translated.
     * @param deltaCol The number of columns to the right that this tetrad is
     * going to be translated.
     * @return true if the attempt to move the tetrad down is successful; false
     * otherwhise.
     */
    public boolean translate(int deltaRow, int deltaCol)
    {
        Location[] locs = removeBlocks();
        Location[] temp = new Location[locs.length];
        for(int i = 0; i < locs.length; i++)
        {
            temp[i] = new Location(locs[i].getRow() + deltaRow, 
                locs[i].getCol() + deltaCol);
        }
        if(areEmpty(myGrid, temp))
        {
            addToLocations(myGrid, temp);
            return true;
        }
        else
        {
            addToLocations(myGrid, locs);
            return false;
        }
    }

}