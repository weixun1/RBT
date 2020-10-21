/**
 * Lab 2: Debugging with Eclipse and Red Black Tree) <br />
 * The {@code ResearchGroup} class uses a 2D array to store the names of group members
 */
public class ResearchGroups {

    /**
     * Search a person to check whether he/she is in the groups
     * @param groups    {@code String[]} The 2D array of groups to be searched
     * @param name      {@code String} name of the person
     */
    public static void searchMember(String[][] groups, String name) {
        // TODO: Lab 2 Part 1-1 -- search and print the results here
    	
    	for(int i = 0; i < groups.length; i++)
    	{

    		for(int j = 0; j< groups[i].length; j++)
    		{
    			if(groups[i][j].compareTo(name) == 0)
    			{
    				System.out.print("Yes, "+name+" is on the list, group number is "+ ++i);
    				if(j == 0)
    				{
    					System.out.print(" and "+name+" is group leader.\n");
    				}
    				else
    				{
    					System.out.print("\n");
    				}
    			}
    		}
    	}
    	
    	
    }

    /**
     * Sort groups by number of members <br />
     * @param groups    (<code>String[][]</code>) The 2D array of groups to be sorted
     */
    public static void sortGroups(String[][] groups) {
        // TODO: Lab 2 Part 1-2 -- sort and print the results here. Reuse your heapsort
    	
    	int[] numbers = new int[groups.length];
    	
    	for(int i = 0; i < groups.length; i++)
    	{
    		numbers[i] = groups[i].length;
    	}
    	
    	
    	
    	int Shrinker = 1;
    	for(int itr = numbers.length;itr > 0;itr--)
    	{
    		int Temp1 = 0;
    		
    		
    	  	for(int i = numbers.length - Shrinker; i > 0; i--)
        	{

        		int j = (i-1)/2;
        		
        		if(numbers[j] > numbers[i])
        		{
        			int Temp = numbers[j];
        			numbers[j] = numbers[i];
        			numbers[i] = Temp;
        			i = numbers.length - Shrinker;
        		}
        	}

    	  	Temp1 = numbers[0];
    	  	for(int i = 0;i < numbers.length - 1; i++)
    	  	{
    	  		numbers[i] = numbers[i+1];
    	  		
    	  	}
    	  	numbers[numbers.length - 1] = Temp1;
    	  	Shrinker++;
    	  	
    	}
    	
        for (int n: numbers)
        {
            System.out.print(n+" ");
        }
        System.out.println();
    	
    	
    	
    }

    /**
     * Main entry
     * @param args      {@code String[]} Command line arguments
     */
    public static void main(String[] args) {
        String[][] groups = { {"Bob", "Carol", "Eric", "Matt"},             // 0
                              {"Jim", "Lucy", "Terry", "Brenda", "Ben"},    // 1
                              {"Susan", "Brad", "Jim"},                     // 2
                              {"Sue", "Wendy", "Sam"},                      // 3
                              {"Kate", "Jack", "James", "Sydney"},          // 4
                              {"Mohammad", "Tim", "Kian"},                  // 5
                              {"Emma", "Carol"},                            // 6
                              {"Nick", "Osama", "Harry", "Ben"},            // 7
                              {"Mary", "John", "Ricky"} };                  // 8

        ResearchGroups.searchMember(groups, "Jim");
        ResearchGroups.searchMember(groups, "Lucy");
        ResearchGroups.searchMember(groups, "John Doe");
        ResearchGroups.sortGroups(groups);
    }

}
