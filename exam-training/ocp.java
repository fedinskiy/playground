import java.time.*;
import java.util.stream.*;
import java.util.*;
import java.util.function.*;
import java.time.*;
import java.time.format.*;
import java.text.MessageFormat;
public class ocp{
	public static void main(String[] args){
		dates();
		String one="Hello, {1} {0}!";
		String two=MessageFormat.format(one,"world","cruel");
		System.out.println(two);
		System.out.println(Morozov.getSon().showMoney());

		Person somePerson=((Function<String,Person>) Person::new).apply("someName");
		System.out.println(somePerson);
	}
	public static void dates(){
		DateTimeFormatter shortF = DateTimeFormatter
					   .ofLocalizedDateTime(FormatStyle.SHORT);
		DateTimeFormatter mediumF = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
		System.out.println(LocalDateTime.now().format(shortF));
		System.out.println(LocalDateTime.now().format(mediumF));
	}

	public static void stream(){
		Stream.iterate(1, x -> x++)
			.forEach(System.out::println);
	}
	
	public static void duration(){
		String d = Duration.ofDays(1).toString();
		String p = Period.ofDays(1).toString();
		System.out.println("Duration: "+d+", Period: "+p);
	}
	public static void fingers(){
		System.out.println("Hello, vim");
		Finger pinky=Finger.PINKY;
		for(Finger finger:Finger.values()){
			System.out.println(finger+" "+finger.countFalangs());
		}
	}
	enum Finger{
		THUMB(2),INDEX,MIDDLE,RING,PINKY;
		private final int falangs;
		Finger(){falangs=3;}
		Finger( int falangs){
			this.falangs=falangs;
		}
		public int countFalangs(){
			return falangs;
		}
	}
	
}
class Person{
	private final String name;
	public Person(String name){
		this.name=name;
	}
	String getName(){
		return name;
	}
	public String toString(){
		return name;
	}
}
class Erochin extends Person {
	public Erochin(String name){
		super(name);
	}
}
class Sychev extends Person implements Comparable<Erochin>{
	public Sychev(String name){
		super(name);
	}
//	@Override
	public int compareTo(Sychev other){
		return this.getName().compareTo(other.getName());
	}
	@Override
	public int compareTo(Erochin other){
		return -9000;
	}
}
class Morozov{
	private int money;
	private Morozov(){
		money=7;
	}
	public int showMoney(){
		return money;
	}
	
	class Pavlic extends Morozov{
		public Pavlic(){
			super();
			money=1;
		}
	}
	public static Morozov getSon(){
		Morozov father=new Morozov();
		System.out.println("father's money before son:"+father.showMoney());
		Pavlic pavlic= father.new Pavlic();
		System.out.println("father's money now:"+father.showMoney());
		return pavlic;
	}
}
class IsItFurry {
   static interface Mammal { }
   static class Furry implements Mammal { }
   static class Chipmunk extends Furry { }
   public static void furryCheck() {
      Chipmunk c = new Chipmunk();
      Mammal m = c;
      Furry f = c;
      int result = 0;
      if (c instanceof Mammal) result += 1;
      if (c instanceof Furry) result += 2;
      if (null instanceof Chipmunk) result += 4;
      System.out.println(result);
  }
}
