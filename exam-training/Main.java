// package ru.fedinskiy;
import java.util.stream.Stream;

class Main {

  public static String glide(String s) {
    return "1";
  }
  public static String glide(String... s) {
    return "2";
  }
  public static String glide(Object o) {
    return "3";
  }
  public static String glide(String s, String t) {
    return "4";
  }

  static int mx(int s){
      for(int i=0; i<3; i++){
          s = s + i;
      }
      return s;
  }

  public static void main(String[] args){
      int s = 5;
      s += ++s + mx(s) + s;
      System.out.println(s);
  }
  public static void streams() {
    Stream<Integer> stream= Stream.of(1,2,3,4,5);
      stream.filter(i->i>3)
      .forEach(System.out::println);
  }
  public static void arrays(){
    int ia[][] = { {1, 2}, null };
      for (int i = 0; i < 2; i++)
         for (int j = 0; j < 2; j++)
            System.out.println(ia[i][j]);
   }
  public static void wtfChar() {
    Integer x=5;
    System.out.println(x);
    char val ='y';
    char wal ='x';
    System.out.println(wal+val);
    wal+=val;
    System.out.println(wal);
    String value ="yumm";
    System.out.println(value+val+x);
    System.out.println(x+val+value);
  }
  public static void semIf(){
    int x = 4;
    long y = x * 4 - x++;
    if(y<10) System.out.println("Too Low");
    else System.out.println("Just right");
    // else System.out.println("Too High");

  }
  public static void inst() {
    if(Integer.valueOf(5) instanceof Number){
      System.out.println("wow");
    }else{
      System.out.println("boo");
    }
  }
  public static void foreach() {
    final String[] names = new String[3];
    names[0] = "Lisa";
    names[1] = "Kevin";
    names[2] = "Roger";
    int i=0;
    for(String name : names) {
      System.out.print(++i);
      System.out.print(name + ", ");
    }
  }

  public static void branch(int hourOfDay) {
    System.out.println("The time is "+hourOfDay);
    if(hourOfDay<15){
      System.out.println("Good morning");
    }else if(hourOfDay<11){
      System.out.println("Good afternoon");
    }else{
      System.out.println("Good evening");
    }
  }
  public static void wtf() {
    short y=3;
    short x=7;
    x+=y;
    System.out.println(x);
  }
}
