public class Warehouse {
	static void analyzeInventory(int[] sectionA, int[] sectionB){
		int sectionATotal = 0;
		int sectionBTotal = 0;
		int highestQuantity = sectionA[0];
		String highestSection = "Section A";
		int highestIndex = 0;

		for(int i=0;i<sectionA.length;i++){
			sectionATotal += sectionA[i];
			if(sectionA[i]>highestQuantity){
				highestQuantity=sectionA[i];
				highestSection="Section A";
				highestIndex=i;
			}
		}
		for(int i=0;i<sectionB.length;i++){
			sectionBTotal += sectionB[i];
			if(sectionB[i]>highestQuantity){
				highestQuantity=sectionB[i];
				highestSection="Section B";
				highestIndex=i;
			}
		}

		String status = sectionATotal==sectionBTotal ? "Balanced" : "Not Balanced";
		System.out.println("Section A Total: "+sectionATotal+" | Section B Total: "+sectionBTotal+" | Status: "+status+" | Highest Quantity: "+highestQuantity+" ("+highestSection+", Item "+(highestIndex+1)+")");
	}

	public static void main(String[] args) {
		int[] sectionA = {20,15,30};
		int[] sectionB = {25,10,30};
		Warehouse.analyzeInventory(sectionA, sectionB);
	}
}
