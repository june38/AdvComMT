package com.practice;

public class AMY {
	static int x = 0;
	static int delay = 1000;
	public static void main(String[] args){

		Thread t1 = new Thread(new Runnable(){

			@Override
			public void run() {
				try {
					while(true){
						System.out.println(x);
						Thread.sleep(delay);
						//System.out.println(tweet.getStatus());
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		Thread t2 = new Thread(new Runnable(){

			@Override
			public void run() {
				try {
					while(true){
						x++;
						Thread.sleep(delay);
						//System.out.println(tweet.getStatus());
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		
		t1.start();
		t2.start();
	}
}
