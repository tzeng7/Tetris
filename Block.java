import java.awt.Color;
/**
 * @author Kevin Tzeng
 * @version January 7, 2016
 */
public class Block
{
    private MyBoundedGrid<Block> grid;
    private Location location;
    private Color color;
    
    /**
     * constructs a blue block, because blue is the greatest color ever!
     */
    public Block()
    {
        color = Color.BLUE;
        grid = null;
        location = null;
    }

    
    /**
     * gets the color of this block
     * @return the color
     */
    public Color getColor()
    {
        return color;
    }

    
    /**
     * gets the color of this block to newColor.
     * @param   newColor the new color
     */
    public void setColor(Color newColor)
    {
        color = newColor;
    }

    /**
     * gets the grid of this block, or null if this block is not contained in a grid
     * @return the grid
     */
    public MyBoundedGrid<Block> getGrid()
    {
        return grid;
    }

    /**
     * gets the location of this block, or null if this block is not contained in a grid
     * @return the location
     */
    public Location getLocation()
    {
        return location;
    }

    /**
     * removes this block from its grid
     * @precondition:  this block is contained in a grid
     */
    public void removeSelfFromGrid()
    {
        grid.remove(location);
        location = null;
        grid = null;
    }

    /**
     * puts this block into location loc of grid gr
     *if there is another block at loc, it is removed
     *@precondition:  (1) this block is not contained in a grid
     *               (2) loc is valid in gr
     * @param   gr the grid
     * @param   loc the location
     */
    public void putSelfInGrid(MyBoundedGrid<Block> gr, Location loc)
    {
        if (gr.get(loc) == null)
        {
            gr.put(loc,this);
        }
        else
        {
            Object previous = gr.put(loc,this);
            if (previous !=null)
            {
                Block prevBlock = (Block)previous;
                prevBlock.grid = null;
                prevBlock.location = null;
            }
        }
        grid = gr;
        location = loc;
    }

        
    /**
     * moves this block to newLocation
     * if there is another block at newLocation, it is removed
     * @precondition:  (1) this block is contained in a grid
     *               (2) newLocation is valid in the grid of this block
     * @param newLocation the new location
     */ 
    public void moveTo(Location newLocation)
    {
        Block newBlock = grid.get(newLocation);
        if (newBlock!= null && newBlock != this)
        {
            newBlock.removeSelfFromGrid();
        }
        grid.remove(location);
        this.putSelfInGrid(grid, newLocation);
    }

    /**
     * returns a string with the location and color of this block
     * @return a string with location and color
     */
    public String toString()
    {
        return "Block[location=" + location + ",color=" + color + "]";
    }
}