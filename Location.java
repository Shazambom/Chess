public class Location{
    private int x;
    private int y;
    /**
     * Initalizes the Location object
     * @param x the x coordinate of the Location Object 
     * @param y the y coordinate of the Location Object
     */
    public Location(int x, int y){
        this.x = x;
        this.y = y;
    }
    /**
     * Gets the x coordinate of the Location Object
     * @return the int value x
     */
    public int getX(){
        return this.x;
    }
    /**
     * Gets the y coordinate of the Location Object
     * @return the int value y
     */
    public int getY(){
        return this.y;
    }
}
