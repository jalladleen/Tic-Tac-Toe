
public class Layout {
	
	private int score;
	private String boardLayout; //will be used as the key attribute for every Layout object.

	
	//A constructor which initializes a new Layout object with the specified attributes boardLayout and score. 
	public Layout(String boardLayout, int score) {
		super();
		this.score = score;
		this.boardLayout = boardLayout;	
	}
	
	 public String getBoardLayout() {
		 
		 // Return the boardLayout key attribute stored in a Layout object
		 return boardLayout;
	 }
	 
	 public int getScore() {
		 
		 //Return the score stored in a Layout object.
		 return score;
	 }
}
