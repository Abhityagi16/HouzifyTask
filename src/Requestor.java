
public class Requestor {
	String address;
	int numOfImages, responseTime, totalTime;
	
	public Requestor(String address, int responseTime, int totalTime) {
		this.address = address;
		this.responseTime = responseTime;
		this.totalTime = totalTime;
		numOfImages = 1;
	}
	
	public void incrementImages() {
		numOfImages++;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void addResponseTime(int time) {
		responseTime += time;
	}
	
	public void addTotalTime(int time) {
		totalTime += time;
	}
	
	public void setNumberOfImages(int num) {
		numOfImages = num;
	}
	
	public int getNumberOfImages() {
		return numOfImages;
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Requestor) {
			return ((Requestor)obj).getAddress().equals(getAddress());
		}
		else return false;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String str = "Address : " + address + "\n Number of Images : " + numOfImages + "\n Average response time : " + (float)responseTime/numOfImages
				+ "\nAverage total time : " + (float)totalTime/numOfImages;
		return str;
	}
	
	public int hashCode(){
		return address.hashCode();
    }
	

}
