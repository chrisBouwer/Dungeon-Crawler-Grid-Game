//Christiaan Bouwer
public class BFSnode {
	public boolean Mark;
	public BFSnode prev;
	
	public void setMark(boolean Mark){
		this.Mark = Mark;
	}
	
	public boolean getMark(){
		return Mark;
	}
	
	public BFSnode getPrev(){
		return prev;
	}
	
	public void setPrev(BFSnode prev){
		this.prev = prev;
	}
}
