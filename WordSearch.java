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
        wordsToAdd.add(word.toUpperCase());
      }
      addAllWords();
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
        String word = in.nextLine();
        wordsToAdd.add(word.toUpperCase());
      }
      addAllWords();
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
        result += "|";
        for(int y = 0; y < data[x].length; y++){
          result += "" + data[x][y] + " ";
        } result += "|\n";
      } result += "Words: ";
      for(int x = 0; x < wordsAdded.size(); x++){
        if(x == wordsAdded.size() - 1){
          result += "" + wordsAdded.get(x);
        }else{
          result += "" + wordsAdded.get(x) + ", ";
        }
      } result += " (seed: " + this.seed + ")";
      return result;
    }


     /**Attempts to add a given word to the specified position of the WordGrid.
      *The word is added in the direction rowIncrement,colIncrement
      *Words must have a corresponding letter to match any letters that it overlaps.
      *
      *@param word is any text to be added to the word grid.
      *@param row is the vertical locaiton of where you want the word to start.
      *@param col is the horizontal location of where you want the word to start.
      *@param rowIncrement is -1,0, or 1 and represents the displacement of each letter in the row direction
      *@param colIncrement is -1,0, or 1 and represents the displacement of each letter in the col direction
      *@return true when: the word is added successfully.
      *        false when: the word doesn't fit, OR  rowchange and colchange are both 0,
      *        OR there are overlapping letters that do not match
      */
    private boolean addWord(String word, int row, int col, int rowIncrement, int colIncrement){
      if(row < 0 || row >= data.length){
        return false;
      }
      if(col < 0 || col >= data[0].length){
        return false;
      }
      if(rowIncrement == 0 && colIncrement == 0){
        return false;
      }
      if((col + (word.length() * colIncrement) > data[0].length) ||
         (col + (word.length() * colIncrement) < -1) ||
         (row + (word.length() * rowIncrement) > data.length) ||
         (row + (word.length() * rowIncrement) < -1)){
        return false;
      }
      if(rowIncrement == 0){
        for(int x = 0; x < word.length(); x++){
          if((data[row][(colIncrement * x) + col] != '_') &&
             (data[row][(colIncrement * x) + col] != word.charAt(x))){
            return false;
          }
        } for(int x = 0; x < word.length(); x++){
          data[row][(colIncrement * x) + col] = word.charAt(x);
        } wordsAdded.add(word);
        return true;
      }
      if(colIncrement == 0){
        for(int x = 0; x < word.length(); x++){
          if((data[(rowIncrement * x) + row][col] != '_') &&
             (data[(rowIncrement * x) + row][col] != word.charAt(x))){
            return false;
          }
        } for(int x = 0; x < word.length(); x++){
          data[(rowIncrement * x) + row][col] = word.charAt(x);
        } wordsAdded.add(word);
        return true;
      }
      for(int x = 0; x < word.length(); x++){
        if((data[(rowIncrement * x) + row][(colIncrement * x) + col] != '_') &&
           (data[(rowIncrement * x) + row][(colIncrement * x) + col] != word.charAt(x))){
          return false;
        }
      } for(int x = 0; x < word.length(); x++){
        data[(rowIncrement * x) + row][(colIncrement * x) + col] = word.charAt(x);
      } wordsAdded.add(word);
      return true;
    }

    private void addAllWords(){
      ArrayList<String> wordsLeft = new ArrayList<String>();
      for(int x = 0; x < wordsToAdd.size(); x++){
        wordsLeft.add(wordsToAdd.get(x));
      }
      while(wordsLeft.size() != 0){
        int index = Math.abs(randgen.nextInt() % wordsLeft.size());
        int rowInc = randgen.nextInt() % 2;
        int colInc = randgen.nextInt() % 2;
        boolean added = false;
        int tries = 0;
        while(tries < 1000 && !added){
          int Row = Math.abs(randgen.nextInt() % data.length);
          int Column = Math.abs(randgen.nextInt() % data[0].length);
          String Word = wordsLeft.get(index);
          tries++;
          added = addWord(Word, Row, Column, rowInc, colInc);
        } wordsLeft.remove(index);
      }
    }
      /*[rowIncrement,colIncrement] examples:
      *[-1,1] would add up and the right because (row -1 each time, col + 1 each time)
      *[ 1,0] would add downwards because (row+1), with no col change
      *[ 0,-1] would add towards the left because (col - 1), with no row change
      */


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
