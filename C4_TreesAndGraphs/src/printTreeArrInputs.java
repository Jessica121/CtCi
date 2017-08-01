import java.util.*;
public class printTreeArrInputs {
	public static ArrayList<LinkedList<Integer>> allArrs(TreeNode n){
		ArrayList<LinkedList<Integer>> lists = new ArrayList<LinkedList<Integer>>();
		if(n == null){
			LinkedList<Integer> list = new LinkedList<Integer>();
			lists.add(list);
			return lists;
		}
//		注意prefix这里虽然只是一个值n.data，但weave时需要加入新的data固定，直到另一个list是0
		LinkedList<Integer> prefix = new LinkedList<Integer>();
		prefix.add(n.data);
		ArrayList<LinkedList<Integer>> left = allArrs(n.left);
		ArrayList<LinkedList<Integer>> right = allArrs(n.right);
//		remember its ArrayList, plus, each left and right as a list of possible arrays
		for(LinkedList<Integer> l:left){
			for(LinkedList<Integer> r:right){
				ArrayList<LinkedList<Integer>> results = new ArrayList<LinkedList<Integer>>();
//				新建一个ArrayList的原因是可以随时把recursive的结果append进去不用再折腾
//				== global static var
				weave(l , r , prefix, results);
				lists.addAll(results);
			}
		}
		return lists;
	}
	
//	注意所有的结果直接加入global var input 的 Arraylist 当中
	public static void weave(LinkedList<Integer> first, LinkedList<Integer> second, LinkedList<Integer> pref, ArrayList<LinkedList<Integer>> res){
		if(first.size() == 0 || second.size() == 0){
//			*你这样本来是一个序列不就分开了吗？
//			res.add(pref);
//			res.add(first);
//			res.add(second);
//			而且要复制一个prefix 不然全部变量被改变影响下面的内容
			LinkedList<Integer> list = (LinkedList<Integer>) pref.clone();
//			add 必须是包含的元素类型<下级>， add All 是和自己相同的类型<同级>
			list.addAll(first);
			list.addAll(second);
			res.add(list);
			return;
		}
		
		int firstF = first.removeFirst();
		pref.addLast(firstF);
		weave(first,second,pref,res);
		pref.removeLast();
		first.addFirst(firstF);
		
		int secondF = second.removeFirst();
		pref.addLast(secondF);
		weave(first, second, pref, res);
		pref.removeLast();
		second.addFirst(secondF);
//		没有return - -
		
	}

	public static void main(String[] args) {
		TreeNode r = new TreeNode(100);
		int[] arr = {100, 50, 20, 75, 150, 120, 170};
		for ( int a : arr){
			r.insertInOrder(a);
		}
		ArrayList<LinkedList<Integer>> res = allArrs(r);
		for(LinkedList<Integer> result : res){
			System.out.println(result);
		}
		System.out.println(res.size());
	}

}
