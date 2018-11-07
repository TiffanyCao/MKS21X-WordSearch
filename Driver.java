public class Driver{
  public static void main(String[] args){
    WordSearch first = new WordSearch(4, 5);

    System.out.println("---Testing Constructor---");
    System.out.println("testing toString: should print a 4-by-5 empty word search");
    System.out.println(first.toString());

    System.out.println("---Testing addWordHorizontal---");
    System.out.println("testing add YES horizontally to row 2, column 0: should return true");
    System.out.println(first.addWordHorizontal("YES", 2, 0));
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
    System.out.println("word search modified: added S to lower right corner");
    System.out.println(first.toString());
    System.out.println("\ntesting add NO horizontally to row 3, column 3: should return false");
    System.out.println(first.addWordHorizontal("NO", 3, 3));
    System.out.println("word search should be the same as before:");
    System.out.println(first.toString());
  }
}
