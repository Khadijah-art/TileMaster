package Game;
import Game.Shape;
import java.util.ArrayList;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Board{
  
  static int rows = 14;
  static int cols = 20;
  private int totalScore = 0;
  private int shapesDrawn = 0;
  public String shape;
  public int count;
   private ArrayList<String> moveHistory = new ArrayList<>();
  
  
  
  
  private char[][] grid = new char [rows][cols];//The actual grid and score map grid
  private int[][] scoreMap = new int [rows][cols];//the value in the 2d array of
  private String [][] color = new String[rows][cols];//the make sure that the shapes have different colors and 

  public int getMovesNum(){
    return this.shapesDrawn;
  }

  public void setShape(String shape){
    this.shape = shape;
  }

  public void makeBoard(){//initialize the board or reset the board
    scoreCells();//generates 20 random score cells for the board
    for (int row = 0; row < rows; row++){
      for (int col = 0; col < cols; col++){
        grid[row][col] = '-';
      }
    }
  }

  public void printBoard(){
    System.out.println("\nYOUR SCORE: " + this.totalScore);
    System.out.println();//move to next line
    System.out.println();
    System.out.print("\t");//spacing
    for (int c = 0; c < cols; c++) {//Show case indexes for column
        System.out.print(c + "\t");
    }
    System.out.println("");//move to next line

      for (int r = 0; r < rows; r++) {//Print each coloumn before row
        if (r < 10) {//show case the indexes for rows the numbers show before the dashes
            System.out.print(r + "  ");
        } else {
            System.out.print(r + " ");
        }
        
        for (int c = 0; c < cols; c++) {
          
          if(grid[r][c]=='-'){
            System.out.print("\t"+ grid[r][c]);//Print each coloumn before row
          } 
          else if (grid[r][c]=='O'){
             if ("Square".equals(color[r][c])) {
            System.out.print("\t" + Shape.COL_SQUARE +grid[r][c] +Shape.RESET);
            }
    
            else if ("Z".equals(color[r][c])) {
                System.out.print("\t" + Shape.COL_Z +grid[r][c] +Shape.RESET);
            }
    
            else if ("Star".equals(color[r][c])) {
                System.out.print("\t" +Shape.COL_STAR + grid[r][c] +Shape.RESET );
            }
          else if(this.totalScore>50 && "Happy".equals(color[r][c])){
            System.out.print("\t" + Shape.COL_HAPPY + grid[r][c] + Shape.RESET );
          }
        }
      }
        System.out.println("");//move to next line
        System.out.println();
    }
  
  }
  
    

    
  public void placeShape(boolean[][] shape, int row, int col){
    int currentScore=0;//to calculate score we get from current shape
    for(int r = 0; r<shape.length; r++){//go through the coloumns first before row
      for (int c = 0; c<shape[r].length; c++){
        if (shape[r][c]==true){//if its true in the mask array
          grid[row+r][col + c]= 'O';//when placing the shape and the dash turns to O
          color[row+r][col + c] = this.shape;
          if (scoreMap[row+r][col+c]>0){//if any of the cells have a score grid value then the score goes up by the that number
            totalScore += scoreMap[row+r][col+c];//total score goes up by that value in the position of the array
            currentScore += scoreMap[row+r][col+c];//total score goes up by that value in the position of the array
            
          }
          
          scoreMap[row+r][col+c] = 0;//reset score to zero after shape has been placed
        }
      }
    }
    shapesDrawn += 1;//shapes drawn goes up by one
    moveHistory.add(this.shape +" at (" +row + ", " +col + ")" + " | +" + currentScore +" points" + "Total: " + this.totalScore);//the history map gets added
    currentScore=0;
    
    if (this.totalScore>50){
            System.out.println("NEW SHAPE UNLOCKED!");//the happy shape is unlocked when the score is above 50
          }
  }

  public void history(String user){
       try{//writing history to file
    FileWriter fw = new FileWriter("UserInfo.txt");
    PrintWriter pw = new PrintWriter(fw);
         LocalDateTime now = LocalDateTime.now();
              pw.println("History documented at: " + now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    pw.println("\nusername: " + user+"\n");
           for(String s: moveHistory){
    pw.println(s);
           }
    pw.close();
    }
    catch(IOException e){//writing to file error
      System.out.println("error with history printing printing");
    }
    
  }

  public void scoreCells(){
    int count = 0;//count for the while loop starts at zero

    do{
      int row = (int)(Math.random()*14)+0;//Generate random number for the row and column
      int col = (int)(Math.random()*20)+0;

      if (scoreMap[row][col]==0){
        scoreMap[row][col] = (int)(Math.random()*9)+1;//if the value in the 2d array is zero then the value is changed to a random number (score) and this is done 20 times (20 diff cells)
      }
      count++;
    }while(count<20);    
  }

  public int getScore(){
    return this.totalScore;
  }

  public int getShapesDrawn(){
    return this.shapesDrawn;
  }

  public boolean endGameCells(){
    for (int r = 0; r<rows; r++){
      for (int c = 0; c<cols; c++){
        if (grid[r][c]=='-'){
          return false;
        } 
      }
    }
    return true;
}

   public void showHistory() {

    System.out.println("\n=== MOVE HISTORY ===");

    for (int i = 0; i < this.moveHistory.size(); i++) {
        System.out.println((i + 1) + ". " + moveHistory.get(i));
    }
}
  
}
