public class TrafficSignal {
	static void findLongestStreak(String signalLog){
		if(signalLog.length()==0){
			return;
		}
		char longestColor = signalLog.charAt(0);
		int longestLength = 1;
		char currentColor = signalLog.charAt(0);
		int currentLength = 1;
		for(int i=1;i<signalLog.length();i++){
			if(signalLog.charAt(i)==currentColor){
				currentLength++;
			}
			else{
				currentColor=signalLog.charAt(i);
				currentLength=1;
			}
			if(currentLength>longestLength){
				longestColor=currentColor;
				longestLength=currentLength;
			}
		}
		System.out.println("Longest Streak: '"+longestColor+"' repeated "+longestLength+" times");
	}

	public static void main(String[] args) {
		TrafficSignal.findLongestStreak("RRGGGYRR");
		TrafficSignal.findLongestStreak("RRRRYYGG");
	}
}
