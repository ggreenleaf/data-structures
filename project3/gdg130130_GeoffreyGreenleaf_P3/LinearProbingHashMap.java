public class LinearProbingHashMap <K,V> 
{
		private static class Entry <K,V>
		{
			private K key; 
			private V val;
			private boolean deleted;

			public Entry()
			{
				key = null;
				val = null;
				deleted = false;
			}
			
			public Entry (K k, V v)
			{
				key = k;
				val = v;
				deleted = false;
			}
			
			public V getVal() { return val; }
			public K getKey() { return key; }

			public boolean compareKeys(K k)
			{
				return key.equals(k);
			}
			
			public void markDeleted()
			{
				deleted = true;
			}
			
			public boolean isDeleted()
			{
				return deleted;
			}


		}

		private Entry<K,V> table [];
		public int numEntries;

		public LinearProbingHashMap (int size) 
		{
			table = new Entry [size];
			numEntries = 0;
		}
			
		private int hash (K key, int size)
		{	
			return Math.abs(key.hashCode() % size);
		}
		
		public void insert (K key, V value)
		{				
			int index = hash(key,table.length);
			while(true)
			{
				if (table[index] == null) {
					table[index] = new Entry(key, value);
					numEntries++;
					break;
				}
				else {
					if (index == table.length - 1 )
						index = 0;
					else
						index++;
				}		
			}
			if (2*numEntries >= table.length) { //sum of total entries >= length of half the table
				rehash();
			//	/*numDeleted*/ = 0; //after rehash num marked for deletion is 0
			}

		}

		public V find (K key)
		{
			Entry<K,V> entry = findEntry(key);

			if (entry == null)
				return null;
			else
				return entry.getVal();
	 	}
	 	
	 	private Entry findEntry(K key)
	 	{
	 		int index = hash(key,table.length); //possible first index
			int loopCount = 0; //count number of interations if > then length of array throw exception
			Entry<K,V> entry = null;			
			while (loopCount != table.length) //table is full and item was not found
			{
				if (table[index] == null)
					return null;
				else {
					entry = table[index];
					if (entry.compareKeys(key))
						return entry;
					else {
						if (index >= table.length - 1)
							index = 0;
						else
							index++;
					}
				}
				loopCount++;
				
			}
			
			return entry;
	 	}

	 	public String toString()
	 	{
	 		StringBuilder sb = new StringBuilder("\n Index\t Key\t Value\t Marked Del\n");
	 		Entry<K,V> entry = null;
	 		for(int i = 0; i < table.length; i++)
	 		{
	 			try {
	 				entry = table[i];
	 				if (entry.isDeleted()) 
	 			 		sb.append(i + "\t| "+entry.getKey() + "\t| " + entry.getVal()+" | X\n");
	 			 	else
	 			 	sb.append(i + "\t| "+entry.getKey() + "\t| " + entry.getVal()+" |\n");
	 			}
	 			catch (NullPointerException e) {
	 				sb.append(i+"\t|\t|\t |\n");
	 			}
	 		
	 		sb.append("-------------------------------\n");
	 		}
	 	
	 	return new String(sb);
	 	}

		public boolean delete(K key)
		{
			if (key == null)
		 		throw new NullPointerException("key is null");
	
		 	Entry<K,V> entry = null;
		
		 	// try {
		 	// 	entry = findEntry(key);
		 	// } 	
		 	// catch (RuntimeException e)	{
		 	// 	return false;
		 	// }
		 	entry = findEntry(key);

		 	if (entry == null)
		 		return false;
		 	
		 	entry.markDeleted();
		 	return true;

		 }

		 private void rehash()
		 {
		 	Entry<K,V> newTable [] = new Entry [table.length*2];
		 	Entry<K,V> entry = null;
			
			for(int i = 0; i < table.length; i++) 
			{
				if (table[i] == null)
					continue;
				else {
					
					entry = table[i];
					if (entry.isDeleted()) {
						numEntries--; //if deleted decrease  numEntries by 1
						continue; //loop again without inserting entry
					}
				}
				//rehash entry and insert into newTable
			 	while(true)
				{
					int index = hash(entry.getKey(), newTable.length); //rehash entry
					if (newTable[index] == null) {
						newTable[index] = entry;
						break;
					}
					else {
						if (index == newTable.length -1 )
							index = 0;
						else
							index++;
					}		
				}
			}
			table = newTable;
			newTable = null;	//set null to get collected by GC	
		 }

	public static void main(String [] args)
	{
		//map with String key and value
		LinearProbingHashMap<String,String> map = new LinearProbingHashMap<>(7);

		map.insert("geoff", "green");
		map.insert("bill", "blue");
		map.insert("chris", "gold");
		System.out.println(map.find("geoff")); //testing find before rehash
		map.delete("bill"); //mark bill for deletion on rehash
		map.delete("key");
		System.out.println(map.toString());
		map.insert("Sean", "Red"); //insert key4 then rehash
		map.insert("Ryan","Black");
		System.out.println(map.find("geoff")); //testing find after rehash
		
		System.out.println(map.toString()); //should rehash before insert key7
		

	}
}