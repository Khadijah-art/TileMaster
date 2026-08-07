/*
Tile Master
    Shape placing game where you earn points by placing shapes on a board. The game ends when the board is full or the player chooses to quit.
Made by: Khadijah and Dushvanth
*/

import java.util.*;
import java.io.*;
import Game.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Main {
    public static void main(String[] args) {
      Scanner input = new Scanner(System.in);
      //attributes
      int option = 0;
      int choice = 0;
      int row=0, col=0;
      int shape = -1;
      Board b1 = new Board ();//creating the board object
      boolean shapeHistory=false;
      String user;
        
      do{
        //display menu
        
            System.out.println("_______________________________________________________\n\t\tTile Master - Main Menu");
                  do{
              System.out.println("_______________________________________________________\n[1] New Game\n[2] Instructions\n[3] Quit\n_______________________________________________________");  
                    
                  try{
                    System.out.print("Your option[#]: ");
                    option = input.nextInt();
                  }
                  catch(InputMismatchException e){
                    System.out.println("input error");
                    input.next();//flush
                    option = 0;//make the do while loop again
                  }
        }while(option<1 || option>3);

          if(option == 1){//new game
                 user = username();
                 b1.makeBoard();

                 do{ 
                choice = menu(b1);//printing the menu that asks if the user wants to print shape, see history, reset board or quit

            if(choice == 1){//creating a shape
               b1.printBoard();//print board
             if(b1.getScore()<50){
        System.out.println("Square (5x5)\tLetter Z (5x5)\tStar(5x5)\t- Empty");//show what each index represents
    }
    else{
        System.out.println("Square (5x5)\tLetter Z (5x5)\tStar(5x5)\tHappy Face(5x5)\t");//show what each index represents
    }
              shapeHistory =true;
                do{
                    if(b1.getScore()<=50){
                  System.out.print("_______________________________________________________\nWhat shape would you like to make:\n[1] Square\n[2] Star\n[3] Letter Z\n[0] Stop\n_______________________________________________________\n[#]: ");//giving user shape options
                    }
                  else if(b1.getScore()>50){
                  System.out.print("_______________________________________________________\nWhat shape would you like to make:\n[1] Square\n[2] Star\n[3] Letter Z\n[4] Happy face\n[0] Stop\n_______________________________________________________\n[#]: ");//giving user shape options
                    
                  }
                  do{
                  try{
                  shape = input.nextInt();
                  } catch (InputMismatchException e){
                    System.out.println("Shape has to be a valid option on list!");
                    shape = -1;
                    input.next();
                  }
                  }while(shape>4 ||shape<0);

                  if(shape!=0)
                {System.out.println("\nEnter the top left coordinate of the shape (Row-Col): ");//asking user for the co ordinates
  
                do{
                  
                  System.out.print("\nEnter row number: ");//asking for row index
                  try{
                  row = input.nextInt();
                  } catch (InputMismatchException e){
                    System.out.println("Row number out of bounds!");
                    input.next();
                    row =15;
                      
                  } 
                }while((row>9||row<0));
  
                do{
                  System.out.print("\nEnter column number: ");//asking for column index
                    try{
                    col = input.nextInt();
                    }catch(InputMismatchException e){
                      System.out.println("Column number out of bounds!");
                      input.next();
                       col = 21;
                    }
                }while((col>15||col<0));
                }
                
                  if(shape==1)//placing the actual shape
                    {
                      b1.setShape("Square");
                      b1.placeShape(Shape.SQUARE, row, col);
                    }
                  else if(shape==2)
                    {
                      b1.setShape("Star");
                      b1.placeShape(Shape.STAR, row, col);
                    }
                  else if(shape==3) 
                    {
                      b1.setShape("Z");
                      b1.placeShape(Shape.LETTER_Z, row, col);
                    }

                  else if (shape==4){
                    if (b1.getScore()<=50){
                      System.out.println("You haven't unlocked the Happy shape yet.");
                    } else if (b1.getScore()>50){
                    b1.shape = "Happy";
                    b1.placeShape(Shape.HAPPY, row, col);}
                  }
                  
                  if(shape!=0)
                  {b1.printBoard();
                    if(b1.getScore()<50){
        System.out.println("Square (5x5)\tLetter Z (5x5)\tStar(5x5)\t- Empty");//show what each index represents
    }
    else{
        System.out.println("Square (5x5)\tLetter Z (5x5)\tStar(5x5)\tHappy Face(5x5)\t- Empty");//show what each index represents
    }
                  }
                  
               // historyAdd.add(moveHistory(b1,row,col));
                  
              }while(shape!=0 && b1.endGameCells()==false);
              LocalDateTime now = LocalDateTime.now();
              System.out.println("Game ended at: " + now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            
                  }
              else if(choice == 2){
                b1.history(user);
                if(shapeHistory ==true)
                {b1.showHistory();}
                else{
                  System.out.println("place a shape to create a history!");
                }
              }

            else if (choice==3){
              b1.makeBoard();//resets the board
              b1.printBoard();
            }
          } while(choice!=4);//end option 1
          }//end if
            
       else if (option==2){//if user wants to see main menu
                System.out.println("-- Your goal is to get as many points by placing the shapes on the 14 x 20 grid");
            }
        
    }while(option != 3);//end game if user chooses to quit in either menus
 System.out.println("Goodbye!");
      LocalDateTime now = LocalDateTime.now();
      System.out.println("Game ended at: " + now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        }//end main

//menu
  public static int menu(Board b1)
    {Scanner input = new Scanner (System.in);
    int c;
    do{
        System.out.print("_______________________________________________________\n[1] Draw a shape\n[2] Move History (" + b1.getMovesNum() + " moves)\n[3] New Board\n[4] Return to main menu\n_______________________________________________________\nYour choice [#]: ");
      
      try{
      c = input.nextInt();
      }
      catch(InputMismatchException e){
        System.out.println("Input error");
        input.next();
        c = 0;
      }
      }while(c>4 || c<1);
      return c;
  }//end method
  
//username
  public static String username(){
    Scanner input = new Scanner(System.in);
    String user="";
    //asking for username
    try { 
      System.out.print("Enter your username: ");
            user = input.next();
        }
    catch(InputMismatchException e){//invalid input error
      System.out.println("Invalid input");
    }

      return user;//print a welcome message
  }//end method





}