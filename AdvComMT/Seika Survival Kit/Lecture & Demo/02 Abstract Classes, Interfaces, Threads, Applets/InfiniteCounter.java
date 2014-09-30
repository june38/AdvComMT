package topic2;

public class InfiniteCounter extends Thread{
	private int delay = 1000;
	private int id;
	private int i = 0;
	
	public InfiniteCounter(int id,int delay){
		this.id = id; this.delay = delay;
	}
	
	public void run(){
	  try{
	    while(true){
			System.out.println(i+"\t(By Thread ID = "+id+")");
			i++;
			Thread.sleep(delay);
	    }
	  }catch(InterruptedException e){}
	}
	public static void main(String[] args){
		Thread t1 = new InfiniteCounter(1,1000);
		Thread t2 = new InfiniteCounter(2,2000);
		t1.start();
		t2.start();
	}
}
