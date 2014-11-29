Compiling program:
	program is compiled using javac on mac terminal. 
	Using javac version "1.8.0_20"
	to compile program unzip project.zip 
	and run make in terminal or similar on windows machine
	the make file should handle compiling the .java file
	or you can use an IDE to compile the BucketSort.java file 
	
Running Program:
	program is run using java version
	java version "1.8.0_20"
	Java(TM) SE Runtime Environment (build 1.8.0_20-b26)
	Java HotSpot(TM) 64-Bit Server VM (build 25.20-b23, mixed mode)
	The command to run the program is
		Java -ea BucketSort
		-ea enables assertions for testing sorted array
		
File Contents:
	BucketSort.java contains 
		BuckSort class and main 
		BucketSort Members:
			int [] a
				array of ints randomonized between 1-1000 
			
			int [] buckets
				bucket array used for counting elements in a
			
			Randon random
				java Random class for generating random ints
				

		BucketSort methods:
			BucketSort(int size)
				constructor generates an array with size "size"
				
			generateArray(int size)
				generates an array of size size and populates 
				with random number in the range  0-1000
			
			display()
				prints the array to the screen one element per line
			
			scatter()
				transverses the array increases bucket 
				count using the elment as the 
				index for bucket array	 
			
			gather()
				trasveres buckets array and adds 
				the index of buckets n times. where n is 
				the element at the index
			
			testSort()
				testing if the list 
				was sorted correctly
				program will throw RunTimeError 
				if not sorted
			
			long getMilliTime(long start, long end)
				returns the total time it took to sort the list
				start is the start time in nanoseconds
				end is the end time in nanoseconds
				milliTime = (end - start)/100000 
				to get total run time in milliseconds 
				return 	millTime

			showRunTime(long start, long end)
				start and end time are the 
				start and end time of the sort
				displays the total time to the screen

			runSort()
				sets start time
				scatters a into buckets
				gathers buckets back into a
				sets end time
				tests if the list was sorted
				displays total runtime
			
			main(String[] args)
				run sort for 4 different size lists
					1. list of 1 million elements
					2. list of 20 million elements
					3. list of 500 million elements
					4. list of 700 million elements

