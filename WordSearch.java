public class WordSearch{
    private char[][]data;

    /**Initialize the grid to the size specified
     *and fill all of the positions with '_'
     *@param row is the starting height of the WordSearch
     *@param col is the starting width of the WordSearch
     */
    public WordSearch(int rows, int cols){
      data = new char[rows][cols];
      clear();
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
}
