public class Inheritance {
  public static void main(String[] args) {
    AfroElephant dumbo = new AfroElephant();
    System.out.println(dumbo.getAge());
    Rodent jerry = new Mouse();
    System.out.println(jerry.tailLength);
  }
}

class Rodent {
public int tailLength = 4;
}
 class Mouse extends Rodent {
public int tailLength = 8;
}


class Mammal {
  private final int myAge;
  public Mammal(int age) {
    myAge=age;
  }
  public Mammal(String age) {
    myAge=Integer.parseInt(age);
  }
  public int getAge(){
    return myAge;
  }
}
class Elephant extends Mammal {
public Elephant() {
super(10);
  }
}
class AfroElephant extends Elephant {
  public AfroElephant() {
    }
}
