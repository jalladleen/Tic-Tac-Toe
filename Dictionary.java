import java.util.LinkedList;

public class Dictionary implements DictionaryADT{
    
    private LinkedList<Layout>[] T; // A linked list created to hold entered values
    private Layout node; //Stores the data before being stored inside the dictionary
    private int size; 
    private int deleted = 0;
    
    
	@SuppressWarnings("unchecked")
	public Dictionary(int size){
		
		this.size = size;
		int i = 0;
		T = (LinkedList<Layout>[]) new LinkedList[size];
		
		do{
			T[i] = new LinkedList<Layout>();
			i++;
			
		}while (i < size);
	}

	public int put(Layout data) throws DictionaryException {
		
		int totalSize = T[hashFunction(data.getBoardLayout())].size();
		int hashCode = hashFunction(data.getBoardLayout());
		
		//if T[hashFunction(data.getBoardLayout())] is null or an empty list.
		if(totalSize == 0 || T[hashCode] == null)
		{
			//Then, add the updated elements of data
			T[hashCode].add(data);
			
			
			//and return 0
			return 0;
		}
		
		//Otherwise, 
		else
		{
			int i = 0;
			
			//Loop through the linked list
			while (i < totalSize)
			{
				Layout currentNode = T[hashCode].get(i); 
				
				// Assign the node to current node in the linked list 
				node = currentNode;
				
				//If there is a collision 
				if (node.getBoardLayout() == data.getBoardLayout()) 
				{
					//Then throw an exception 
					throw new DictionaryException("Collision");
				}
				
				i++;
			}
			
			T[hashCode].add(data);
			return 1;
		} 
	}
	
    public void remove(String boardLayout) throws DictionaryException{
		
    	int hashCode = hashFunction(boardLayout);
    	int currSize = T[hashCode].size() - 1;
    	int i = 0;
    	
       
    	//Loop through the linked List
    	while ( i <= currSize){
            if (T[hashCode].get(i).getBoardLayout() == (boardLayout)){
            	
            	// Remove the object with key boardLayout from the dictionary
            	T[hashCode].remove(i);
            	
            	//Update the variable deleted to indicate that the object has been successfully delk
                deleted = 1;
                break;
           } 
            i++;
        }
    	
    	//throw a DictionaryException if there is no data item in the dictionary with this key.
        if (deleted == 0){
            throw new DictionaryException("boardLayout not found");
        }  
    }
    
    //private method for hashing the values
	private int hashFunction(String str){
		
		int length = str.length();
		int hashKey = 29;
		int i = 0;
		
		do
		{
			hashKey = (hashKey * hashKey) % size;
			hashKey += str.charAt(i);
			i++;
			
		}while( i < length);
		
		hashKey = hashKey % size;
		
		return hashKey;
	}
	
    @Override
    public int getScore(String boardLayout) {
        int hashKey = hashFunction(boardLayout);
        LinkedList<Layout> currentNode = T[hashKey];

        int iterator = 0;
        try {
            while (currentNode.get(iterator) != null) {
                if (currentNode.get(iterator).getBoardLayout().equals(boardLayout)) {
                    //returns the score stored in the object in the dictionary with key boardLayout
                	return currentNode.get(iterator).getScore();
                }
                iterator++;
            }
            //return -1 if there no object in the dictionary with that key.

            return -1;
        }
        catch (Exception e) { return -1; }
    }
    
    
}