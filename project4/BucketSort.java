//Bucket Sort
//Project 4
import java.util.*; 

public class BucketSort {
	private int [] a;
	private int [] buckets;
	public Random random;

	public BucketSort(int size) {
		random = new Random();
		generateArray(size);
		buckets = new int [1001]; //automaically zeroed out

	}

	private void generateArray(int size) {
		a = new int[size];
		for (int i=0; i<size; i++) {
			a[i] = random.nextInt(1001);  //range of nextInt is [0,1001)
		}
	}

	public void display() {
		for (int i=0; i<a.length; i++) {
			System.out.println(a[i]);
		}
	}

	//scatter randomized array into buckets
	private void scatter() {
		for (int idx: a) {
			buckets[idx]++;
		}
	}
	
	//gather buckets data back into array a
	private void gather() {
		int aIndex = 0;
		for(int i = 0; i < buckets.length; i++) {
			for (int j = buckets[i]; j != 0; j-- ) {
				a[aIndex] = i;
				aIndex++;
			}
		}
		
	}

	private void testSort() {
		for(int i = 1; i < a.length; i++) {
			assert (a[i-1] <= a[i]);
		}
	}

	//start and end are both long numbers in nano seconds
	//divide by 1000000 to convert from nano to milliseconds
	private long getTimeInMilliSecs(long start, long end) {
		return (end - start) / 1000000; 
	}

	private void showRuntime(long start, long end) {
		long milliTime = getTimeInMilliSecs(start,end);
		System.out.printf("Sorting array of size %d took %d milliseconds\n",a.length, milliTime);
	} 

	private void runSort() {
		long startTime = System.nanoTime();
		scatter();
		gather();
		long endTime = System.nanoTime();
		testSort(); //check if the list was sorted correctly
		showRuntime(startTime, endTime);
	}

	public static void main(String[] args) {
		BucketSort b = new BucketSort(1000000); //1 million items
		b.runSort();
		b = new BucketSort(20000000); //20 million items
		b.runSort();
		b = new BucketSort(50000000); //50 million items
		b.runSort();
		b = new BucketSort(70000000); //70 million items
		b.runSort();

	}
}
