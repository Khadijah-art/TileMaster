 //this class holds all the templates of the shapes and their colours!
package Game;

public class Shape{

  //attributes
  
   public static final boolean[][] SQUARE ={ 
    {true, true, true, true, true}, 
    {true, false, false, false, true}, 
    {true, false, false, false, true}, 
    {true, false, false, false, true}, 
    {true, true,true,true,true}
   };

   public static final boolean [][] LETTER_Z ={
    {true,true,true,true,true},
    {false,false,false,true,false},
    {false,false,true,false,false}, 
    {false,true,false,false,false}, 
    {true,true,true,true,true}
   };

  public static final boolean [][] STAR = new boolean[][] {
        {false, false, true,  false, false},
        {true,  false, true,  false, true },
        {false, true,  true,  true,  false},
        {true,  false, true,  false, true },
        {false, false, true,  false, false}
    };
   public static final boolean [][] HAPPY = new boolean[][] {
        {false, false, true,  false, false},
        {true,  false,false,  true, false },
        {false, true,  false,  false,  true},
        {true,  false, false,  true, false },
        {false, false, true,  false, false}
    };

    public static final String RESET       = "\033[0m";
    public static final String DARK        = "\033[90m";
    public static final String COL_SQUARE  = "\033[93m";
    public static final String COL_Z       = "\033[96m";
    public static final String COL_STAR    = "\033[95m";
    public static final String COL_HAPPY    = "\033[45m";
  
  

  
  public String reset(){
    return RESET;
  }


  
}