import java.util.*; //random, scanner, arraylist
import java.io.*; //file, filenotfoundexception
public class WordSearch{
    private char[][]data;

    //the random seed used to produce this WordSearch
    private int seed;

    //a random Object to unify your random calls
    private Random randgen;

    //all words from a text file get added to wordsToAdd, indicating that they have not yet been added
    private ArrayList<String> wordsToAdd;

    //all words that were successfully added get moved into wordsAdded.
    private ArrayList<String> wordsAdded;

    /**Initialize the grid to the size specified
     *and fill all of the positions with '_'
     *@param row is the starting height of the WordSearch
     *@param col is the starting width of the WordSearch
     */
    public WordSearch(int rows, int cols, String filename) throws FileNotFoundException{
      data = new char[rows][cols];
      clear();
      seed = (int)System.currentTimeMillis();
      randgen = new Random(seed);
      wordsToAdd = new ArrayList<String>();
      wordsAdded = new ArrayList<String>();
      File f = new File(filename);
      Scanner in = new Scanner(f);
      while(in.hasNext()){
        String word = in.next();
        wordsToAdd.add(word);
      }
    }

    public WordSearch(int rows, int cols, String filename, int randSeed) throws FileNotFoundException{
      data = new char[rows][cols];
      seed = randSeed;
      randgen = new Random(seed);
      clear();
      wordsToAdd = new ArrayList<String>();
      wordsAdded = new ArrayList<String>();
      File f = new File(filename);
      Scanner in = new Scanner(f);
      while(in.hasNext()){
        String word = in.next();
        wordsToAdd.add(word);
      }
    }

    /**Set all values in the WordSearch to underscores'_'*/
    private void clear(){
      for(int x = 0; x < data.length; x++){
        for(int y = 0; y < data[x].length; y++){
          data[x][y] = '_';
        }
      }
    }

    /**Each row is a new line, there is a space between each letter
     *@return a String with each character separated by spaces, and rows
     *separated by newlines.
     */
    public String toString(){
      String result = "";
      for(int x = 0; x < data.length; x++){
        for(int y = 0; y < data[x].length; y++){
          result += "" + data[x][y] + " ";
        } result += "\n";
      } return result;
    }


    /**Attempts to add a given word to the specified position of the WordGrid.
     *The word is added from left to right, must fit on the WordGrid, and must
     *have a corresponding letter to match any letters that it overlaps.
     *
     *@param word is any text to be added to the word grid.
     *@param row is the vertical locaiton of where you want the word to start.
     *@param col is the horizontal location of where you want the word to start.
     *@return true when the word is added successfully. When the word doesn't fit,
     * or there are overlapping letters that do not match, then false is returned
     * and the board is NOT modified.
     */
    public boolean addWordHorizontal(String word, int row, int col){
      if(row < 0 || col < 0){
        return false;
      }
      if(row >= data.length || ((col + word.length() - 1) >= data[0].length)){
        return false;
      }
      if(checkHorizontal(word, row, col)){
        for(int x = 0; x < word.length(); x++){
          data[row][x + col] = word.charAt(x);
        } return true;
      } return false;
    }



    public boolean checkHorizontal(String word, int row, int col){
      boolean available = true;
      for(int x = 0; x < word.length(); x++){
        if((data[row][x + col] == '_') ||
           (data[row][x + col] == word.charAt(x))){
             available = true;
        }else{
          return false;
        }
      } return available;
    }
    /**Attempts to add a given word to the specified position of the WordGrid.
     *The word is added from top to bottom, must fit on the WordGrid, and must
     *have a corresponding letter to match any letters that it overlaps.
     *
     *@param word is any text to be added to the word grid.
     *@param row is the vertical locaiton of where you want the word to start.
     *@param col is the horizontal location of where you want the word to start.
     *@return true when the word is added successfully. When the word doesn't fit,
     *or there are overlapping letters that do not match, then false is returned.
     *and the board is NOT modified.
     */
    public boolean addWordVertical(String word, int row, int col){
      if(row < 0 || col < 0){
        return false;
      }
      if(col >= data[0].length || ((row + word.length() - 1) >= data.length)){
        return false;
      }
      if(checkVertical(word, row, col)){
        for(int x = 0; x < word.length(); x++){
          data[x + row][col] = word.charAt(x);
        } return true;
      } return false;
    }

    public boolean checkVertical(String word, int row, int col){
      boolean available = true;
      for(int x = 0; x < word.length(); x++){
        if((data[x + row][col] == '_') ||
           (data[x + row][col] == word.charAt(x))){
             available = true;
           }else{
             return false;
           }
      } return available;
    }

   /**Attempts to add a given word to the specified position of the WordGrid.
    *The word is added from top left to bottom right, must fit on the WordGrid,
    *and must have a corresponding letter to match any letters that it overlaps.
    *
    *@param word is any text to be added to the word grid.
    *@param row is the vertical locaiton of where you want the word to start.
    *@param col is the horizontal location of where you want the word to start.
    *@return true when the word is added successfully. When the word doesn't fit,
    *or there are overlapping letters that do not match, then false is returned.
    */
    public boolean addWordDiagonal(String word, int row, int col){
      if(row < 0 || col < 0){
        return false;
      }
      if(((col + word.length() - 1) >= data[0].length) ||
         ((row + word.length() - 1) >= data.length)){
        return false;
      }
      if(checkDiagonal(word, row, col)){
        for(int x = 0; x < word.length(); x++){
          data[row + x][col + x] = word.charAt(x);
        } return true;
      } return false;
    }

    public boolean checkDiagonal(String word, int row, int col){
      boolean available = true;
      for(int x = 0; x < word.length(); x++){
        if((data[row + x][col + x] == '_') || (data[row + x][col + x] == word.charAt(x))){
          available = true;
        }else{
          return false;
        }
      } return available;
    }

}
