package HW5;

import java.awt.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.ListIterator;
import java.util.Map;

public class HW_5 <E>{
	private static ArrayList<String> dogs = new ArrayList<String>();
	//Instantiating a List object in which "Generic Type Invocation" is performed.
	final static String randomName[] = {"Golden Retriever","Pomeranian","Siberian Husky","Greyhound"};
	final static int age[] = {6,4,8,10};
	public static void main(String[]args){
		System.out.println("------Unsorted------");
		for(int i=0;i<randomName.length;i++){
			dogs.add(randomName[i]);
			//Invoking some "positional access" methods.
			System.out.println(dogs.get(i));
		}
		System.out.println("------Sorted------");
		Collections.sort(dogs);
		//Using some polymorphic method from the Collections class to manipulate the list.
		for(int i=0;i<randomName.length;i++){
			System.out.println(dogs.get(i));
		}
		ListIterator li = dogs.listIterator();
		System.out.println("------Forward------");
		//Using ListIterator to traverse the list.
		while(li.hasNext()){
	          System.out.println(li.next());
	        }
		System.out.println("------Backward------");
		//Using ListIterator to traverse the list.
		while(li.hasPrevious()){
	          System.out.println(li.previous());
	        }
		Collections.rotate(dogs, 1);
		System.out.println("------Rotated for 1 time------");
		//Using some polymorphic method from the Collections class to manipulate the list.
		for(int i=0;i<randomName.length;i++){
			System.out.println(dogs.get(i));
		}
		System.out.println("------Swap between dogs[2] and dogs[3]------");
		Collections.swap(dogs, 2, 3);
		for(int i=0;i<randomName.length;i++){
			System.out.println(dogs.get(i));
		}
		Collections.sort(dogs);
		//Instantiating a Map object in which "Generic Type Invocation" is performed.
		Map<String, Integer> m1 = new HashMap<String, Integer>();
		for(int i = 0; i < age.length; i++){
			m1.put(dogs.get(i),age[i]);
		}
		System.out.println("------Basic Operations of the Map------");
	    //Showing some basic operations of the map.
	    for(int i = 0; i < m1.size(); i++){
	    	System.out.print(i+1+". "+dogs.get(i)+"+++");
	    	System.out.println(m1.get(dogs.get(i)));
	    }
	   		
		
	}
}
