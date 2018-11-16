import java.util.*; //random, scanner, arraylist
import java.io.*; //file, filenotfoundexception
public class WordSearch{
  public static void main(String[] args) {
    Random seedRand = new Random();
    int Seed = Math.abs(seedRand.nextInt() % 10001);
    String fileName = "filename.txt";
    boolean Key = false;
    int row = 0;
    int column = 0;

    if(args.length < 3){
      System.out.println("Not enough command line arguments are specified:\nPlease provide the rows, columns, and file name of the text you wish to scan.\nPlease remember that row and column inputs should be positive values.\nPlease remember to give an existing file containing the words you want to add.");
    }else{
      try{
        if((Integer.parseInt(args[0]) <= 0) || (Integer.parseInt(args[1]) <= 0)){
          System.out.println("Row or column input is out of range. Please give positive values for the dimensions of your word search.");
        }else if(args.length == 3){
          row = Integer.parseInt(args[0]);
          column = Integer.parseInt(args[1]);
          fileName = args[2];
          WordSearch test = new WordSearch(row, column, fileName, Seed, Key);
          System.out.println(test.toString());
        }else if((Integer.parseInt(args[3]) > 10000) || (Integer.parseInt(args[3]) < 0)){
            System.out.println("The seed given is out of range: " + args[3] + "\nPlease give a seed value that is less than or equal to 10,000 and greater than or equal to 0.");
        }else if(args.length == 4){
          row = Integer.parseInt(args[0]);
          column = Integer.parseInt(args[1]);
          fileName = args[2];
          Seed = Integer.parseInt(args[3]);
          WordSearch test = new WordSearch(row, column, fileName, Seed, Key);
          System.out.println(test.toString());
        }else{
          row = Integer.parseInt(args[0]);
          column = Integer.parseInt(args[1]);
          fileName = args[2];
          Seed = Integer.parseInt(args[3]);
          if(args[4].toUpperCase().equals("KEY")){
            Key = true;
          }
          WordSearch test = new WordSearch(row, column, fileName, Seed, Key);
          System.out.println(test.toString());
        }
      }catch(FileNotFoundException e){
        System.out.println("The file you gave: " + fileName + " does not exist. Please give an existing file containing the words you want to add.");
      }
    }

  }

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

     public WordSearch(int rows, int cols, String filename, int randSeed, boolean key) throws FileNotFoundException{
       data = new char[rows][cols];
       clear();
       seed = randSeed;
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
       if(!key){
         fillRandomLetters();
       }
     }
    /*public WordSearch(int rows, int cols, String filename) throws FileNotFoundException{
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
*/
    /**Set all values in the WordSearch to underscores'_'*/
    private void clear(){
      for(int x = 0; x < data.length; x++){
        for(int y = 0; y < data[x].length; y++){
          data[x][y] = '_';
        }
      }
    }

    /**fills the empty spaces of the word search with random letters generated by random
    */
    private void fillRandomLetters(){
      for(int x = 0; x < data.length; x++){
        for(int y = 0; y < data[0].length; y++){
          if(data[x][y] == '_'){
            char letter = (char)('A' + Math.abs(randgen.nextInt() % 26));
            data[x][y] = letter;
          }
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
          if(data[x][y] == '_'){
            result += "  ";
          }else{
            result += "" + data[x][y] + " ";
          }
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
      for(int x = 0; x < 1000; x++){
        if(wordsLeft.size() != 0){
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
            if(added){
              wordsLeft.remove(Word);
            }
          }
        }
      }
    }
      /*[rowIncrement,colIncrement] examples:
      *[-1,1] would add up and the right because (row -1 each time, col + 1 each time)
      *[ 1,0] would add downwards because (row+1), with no col change
      *[ 0,-1] would add towards the left because (col - 1), with no row change


    *********************
    *                   *
    * OLD METHODS BELOW *
    *                   *
    *********************

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
