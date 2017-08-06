import java.util.Random;
public class randomTreeNode {
	public static class TNWS{	//short for treeNodeWithSize- -
		public TNWS left,right;
		private int value,leftS,rightS;
		public TNWS(int v){
			this.value = v;
			leftS = 0;
			rightS = 0;
		}
	
//		public TNWS root = null;
		
		public void add(int data){
			TNWS newNode = new TNWS(data);
//			if(root == null)	{
//				root = newNode;
//			}else 
				if(data <= this.value){
				if(this.left == null){
				this.left = newNode;
				}else{
					//这里一定注意是recursive，不可以直接给left或者right，可能已经有值！
					this.left.add(data);
				}
				leftS++;
			}else if(data > this.value){
				if(this.right == null){
					this.right =  newNode;
					}else{
						this.right.add(data);
					}
				rightS++;
			}
		}
		
		public int leftSize(){
			//这里注意getsize 之前树🌲有没有值
			return this == null? 0 : leftS;
		}
		
		public int rightSize(){
			//这里注意getsize 之前树🌲有没有值
			return this == null? 0 : rightS;
		}
		
		public int getWholeSize(){
			//这里注意getsize 之前树🌲有没有值
			return this == null? 0 : leftS+rightS+1;
		}
		
		public TNWS search(int data){
			if(this == null) return null;
			if(this.value == data)	return this;
			if(data <= this.value) 	return this.left.search(data);
			else	 return this.right.search(data);
		}
		
		public TNWS getRandom(){
			Random r = new Random();
			if(this.getWholeSize() == 0) return null;
			int i = r.nextInt(this.getWholeSize());
			return getRandNode(i);
		}
		
		public TNWS getRandNode(int i){
			if(i == this.leftSize())	return this;
			if(i < this.leftSize())	return this.left.getRandNode(i);
			else return this.right.getRandNode(i-leftS-1);
		}
	}
	
	public static void main (String[] args) throws Exception{
		int[] counts = new int[10];
		for(int i = 0;i < 1000000; i ++){
			TNWS root = new TNWS(1);
			int[] arr = {0, 6, 2, 3, 9, 4, 5, 8, 7};
			for(int a : arr){
				if(root == null) {
					System.out.print("null pointer!");
					break;
				}
				root.add(a);
			}
			int val = root.getRandom().value;
			counts[val]++;
		}
		for (int i = 0; i < counts.length; i++) {
			System.out.println(i + ": " + counts[i]);
		}
		
	}
	
}
