public class Driver{
  public static void main(String[] args){
    WordSearch first = new WordSearch(8, 5);

    System.out.println("---Testing Constructor---");
    System.out.println("testing toString: should print a 8-by-5 empty word search");
    System.out.println(first.toString());

    System.out.println("---Testing addWordHorizontal---");
    System.out.println("testing add YES horizontally to row 2, column 0: should return true");
    System.out.println(first.addWordHorizontal("YES", 2, 0));
    System.out.println("word search should be modified:");
    System.out.println(first.toString());
    System.out.println("\ntesting add NO horizontally to row 1, column 3: should return true");
    System.out.println(first.addWordHorizontal("NO", 1, 3));
    System.out.println("word search should be modified:");
    System.out.println(first.toString());

    System.out.println("\ntesting add AN horizontally to row -1, column 2: should return false");
    System.out.println(first.addWordHorizontal("AN", -1, 2));
    System.out.println("word search should be the same as before:");
    System.out.println(first.toString());

    System.out.println("\ntesting add YES horizontally to row 0, column 3: should return false");
    System.out.println(first.addWordHorizontal("YES", 0, 3));
    System.out.println("word search should be the same as before:");
    System.out.println(first.toString());

    System.out.println("\ntesting add YES horizontally to row 2, column 0 again: should return true");
    System.out.println(first.addWordHorizontal("YES", 2, 0));
    System.out.println("word search should be the same as before:");
    System.out.println(first.toString());
    System.out.println("\ntesting add YEA horizontally to row 2, column 0: should return false");
    System.out.println(first.addWordHorizontal("YEA", 2, 0));
    System.out.println("word search should be the same as before:");
    System.out.println(first.toString());

    first.addWordHorizontal("S", 3, 4);
    System.out.println("word search modified: added S to middle right corner");
    System.out.println(first.toString());
    System.out.println("\ntesting add NO horizontally to row 3, column 3: should return false");
    System.out.println(first.addWordHorizontal("NO", 3, 3));
    System.out.println("word search should be the same as before:");
    System.out.println(first.toString());

    System.out.println("\ntesting add ESA horzontally to row 2, column 1: should return true");
    System.out.println(first.addWordHorizontal("ESA", 2, 1));
    System.out.println("word search should be modified:");
    System.out.println(first.toString());

    System.out.println("\ntesting add YOS horizontally to row 2, column 0: should return false");
    System.out.println(first.addWordHorizontal("YOS", 2, 0));
    System.out.println("word search be the same as before:");
    System.out.println(first.toString());

    System.out.println("---Testing addWordVertical---");
    System.out.println("testing add NO vertically to row 0, column 0: should return true");
    System.out.println(first.addWordVertical("NO", 0, 0));
    System.out.println("word search should be modified:");
    System.out.println(first.toString());

    System.out.println("\ntesting add NOYS vertically to row 0, column 0: should return true");
    System.out.println(first.addWordVertical("NOYS", 0, 0));
    System.out.println("word search should be modified:");
    System.out.println(first.toString());

    System.out.println("\ntesting add AN vertically to row 1, column -2: should return false");
    System.out.println(first.addWordVertical("AN", 1, -2));
    System.out.println("word search should be the same as before:");
    System.out.println(first.toString());

    System.out.println("\ntesting add SUPER vertically to row 0, column 4: should return false");
    System.out.println(first.addWordVertical("SUPER", 0, 4));
    System.out.println("word search should be the same as before:");
    System.out.println(first.toString());

    System.out.println("\ntesting add SUPER vertically to row 2, column 2: should return true");
    System.out.println(first.addWordVertical("SUPER", 2, 2));
    System.out.println("word search should be modified:");
    System.out.println(first.toString());

    System.out.println("\ntesting add EYES vertically to row 0, column 1: should return true");
    System.out.println(first.addWordVertical("EYES", 0, 1));
    System.out.println("word search should be modified:");
    System.out.println(first.toString());

    System.out.println("\ntesting add POPE vertically to row 0, column 4: should return false");
    System.out.println(first.addWordVertical("POPE", 0, 4));
    System.out.println("word search should be the same as before");
    System.out.println(first.toString());

    WordSearch second = new WordSearch(10, 10);
    System.out.println("\n---Testing addWordDiagonal---");
    System.out.println("testing toString: should print a 10-by-10 empty word search");
    System.out.println(second.toString());

    System.out.println("\ntesting add WOWZERS diagonally to row 0, column 0: should return true");
    System.out.println(second.addWordDiagonal("WOWZERS", 0, 0));
    System.out.println("word search should be modified:");
    System.out.println(second.toString());

    System.out.println("\ntesting add NO diagonally to row -1, 3: should return false");
    System.out.println(second.addWordDiagonal("NO", -1, 3));
    System.out.println("word search should be the same as before:");
    System.out.println(second.toString());

    System.out.println("\ntesting add WOP diagonally to row 0, column 0: should return false");
    System.out.println(second.addWordDiagonal("WOP", 0, 0));
    System.out.println("word search should be the same as before:");
    System.out.println(second.toString());

    System.out.println("\ntesting add SEAL diagonally to row 6, column 6: should return true");
    System.out.println(second.addWordDiagonal("SEAL", 6, 6));
    System.out.println("word search should be modified:");
    System.out.println(second.toString());

    System.out.println("\ntesting add SEALS diagonally to row 6, column 6: should return false");
    System.out.println(second.addWordDiagonal("SEALS", 6, 6));
    System.out.println("word search should be the same as before:");
    System.out.println(second.toString());

    System.out.println("\ntesting add DIRECTNESS diagonally to row 3, column 0: should return false");
    System.out.println(second.addWordDiagonal("DIRECTNESS", 3, 0));
    System.out.println("word search should be the same as before:");
    System.out.println(second.toString());

    System.out.println("\ntesting add GOOD diagonally to row 0, column 7: should return false");
    System.out.println(second.addWordDiagonal("GOOD", 0, 7));
    System.out.println("word search should be the same as before:");
    System.out.println(second.toString());

    System.out.println("\ntesting add GOOD diagonally to row 2, column 3: should return true");
    System.out.println(second.addWordDiagonal("GOOD", 2, 3));
    System.out.println("word search should be modified:");
    System.out.println(second.toString());

    System.out.println("\ntesting add GOODIES diagonally to row 2, column 3: should return true");
    System.out.println(second.addWordDiagonal("GOODIES", 2, 3));
    System.out.println("word search should be modified:");
    System.out.println(second.toString());

    System.out.println("\ntesting add PEOPLE diagonally to row 4, column 0: should return true");
    System.out.println(second.addWordDiagonal("PEOPLE", 4, 0));
    System.out.println("word search should be modified:");
    System.out.println(second.toString());

    System.out.println("\ntesting add PUSH diagonally to row 9, column 0: should return false");
    System.out.println(second.addWordDiagonal("PUSH", 9, 0));
    System.out.println("word search should be the same as before:");
    System.out.println(second.toString());

    System.out.println("\ntesting add PULL diagonally to row 0, column 9: should return false");
    System.out.println(second.addWordDiagonal("PULL", 0, 9));
    System.out.println("word search should be the same as before:");
    System.out.println(second.toString());

  }
}
