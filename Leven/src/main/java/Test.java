import java.util.Date;

public class Test implements Runnable {
	
	int[] a = new int[1000*1000];
	int[] b = new int[1000*1000];
	int c;
	int number = 1;


	public static void main(String[] args){

		int[] a = new int[1000*1000];
		int[] b = new int[1000*1000];
		int c = 0;

		System.out.println("date = " + (new Date()).toString());

		for(int i = 0; i < 100*1000; i++){
			for(int j = 0; j < 100*1000; j++){
				if(a[i] < b[j])
					c = a[i] + b[j];
			}
		} 

		System.out.println(c);
		System.out.println("date = " + (new Date()).toString());

		Test r1 = new Test(0);
		Test r2 = new Test(1);

		//Test r3 = new Test(2);

		Thread t1 = new Thread(r1);
		Thread t2 = new Thread(r2);

		//Thread t3 = new Thread(r3);

		t1.start();
		t2.start();

		//t3.start();

		try{
			t1.join();
			t2.join();
			//t3.join();
		}

		catch(InterruptedException e){
			System.out.println("Exception");
		}
		System.out.println("date = " + (new Date()).toString());
	}


	Test(int nr){
		number = nr;
	}


	public void run ( ){
		for(int i = number * 100 * 1000; i < (number + 1) * 100*1000; i++){
			for(int j = 0; j < 100*1000; j++){
				if(a[i] < b[j])
					c = a[i] + b[j];
			}
		}
	}
}
