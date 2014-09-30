package topic5;

public class Box {
	private Object item;
	public void putIn(Object item){
		this.item = item;
	}
	public Object takeOut(){
		return item;
	}
	public static void main(String [] args){
		Box b = new Box();
		Integer item = new Integer(99);
		b.putIn(item);
		Integer outOftheBox = (Integer)b.takeOut();
		System.out.println(outOftheBox);
	}
}
