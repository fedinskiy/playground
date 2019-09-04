import java.util.*;
public class gener{
	public static void main(String[] a){
		List<? super B> list5 = new ArrayList<A>();//компилится
		list5.add(new A());//не комппилится
		list5.add(new B());//компилится
	}
}

class A{}
class B extends A{}
class C extends B{}
