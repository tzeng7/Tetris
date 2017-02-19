import java.util.ArrayList;

/**
 * @author Kevin Tzeng
 * @version January 7, 2015
 * @param <E> java syntax
 */
public class MyBoundedGrid<E>
{
    private Object[][] occupantArray;  // the array storing the grid elements

    /**
     * Constructs an empty MyBoundedGrid with the given dimensions.
     * @Precondition:  rows > 0 and cols > 0.)
     * @param rows number of rows
     * @param cols number of cols
     */
    public MyBoundedGrid(int rows, int cols)
    {
        occupantArray = new Object[rows][cols];
    }

    /**
     * returns the number of rows
     * @return number of rows
     */
    public int getNumRows()
    {
        return occupantArray.length;
    }

    /**
     * returns the number of columns
     * @return the number of columns
     */
    public int getNumCols()
    {
        return occupantArray[0].length;
    }

    /**
     * @return true if loc is valid in this grid, false otherwise
     * @precondition:  loc is not null
     * @param loc new location
     */
    public boolean isValid(Location loc)
    {
        int row = loc.getRow();
        int col = loc.getCol();
        return(0<= row && row <this.getNumRows() && 
            0<= col && col< this.getNumCols());
    }

    /**
     * @return   the object at location loc or null if the location is unoccupied)
     * @precondition:  loc is valid in this gridon
     * @param   loc the locati
     */
    public E get(Location loc)
    {
        int row = loc.getRow();
        int col = loc.getCol();
        Object temp = this.occupantArray[row][col];
        return (E)temp;
    }

    /**
     * puts obj at location loc in this grid and 
     * returns the object previously at that location (or null if the
     * location is unoccupied)
     * @precondition:  loc is valid in this grid
     * @param   loc the location
     * @param   obj the object
     * @return  the temp object
     */
    public E put(Location loc, E obj)
    {
        int row = loc.getRow();
        int col = loc.getCol();
        Object temp = get(loc);
        occupantArray[row][col] = (Object)obj;
        return (E) temp;
    }

    /**
     * removes the object at location loc from this grid and
     * returns the object that was removed (or null if the
     * location is unoccupied
     * @precondition:  loc is valid in this grid
     * @param   loc the location
     * @return temp object
     */
    public E remove(Location loc)
    {
        int row= loc.getRow();
        int col= loc.getCol();
        Object temp = get(loc);
        occupantArray[row][col] = null;
        return (E) temp;
    }

    /**
     * @return temp an array list of all occupied locations in this grid
     */
    public ArrayList<Location> getOccupiedLocations()
    {
        ArrayList<Location> temp = new ArrayList<Location>();
        for(int i = 0; i < occupantArray.length; i++)
        {
            for(int j = 0; j < occupantArray[i].length; j++)
            {
                if(occupantArray[i][j]!= null)
                {
                    Location oloc = new Location(i,j);
                    temp.add(oloc);
                }
            }
        }
        return temp;
    }
}