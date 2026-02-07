package DataStructureAlgorithm.Tree;
import java.util.*;
public class serilizationanddeserilization {
    class Node{
        int data;
        Node right;
        Node left;
    }
    Node head = new Node();
    // need to make the serilization method that take the node and return the list of elements of the tree
     public List<String> serilization(){
        List<String>ans = new ArrayList<>();
        helper(this.head ,ans);
        return ans;
        }
        private void helper(Node node,List<String>list){
            if(node == null){
                list.add("null");
                return;
            }
            list.add(String.valueOf(node.data));
            helper(node.left , list);
            helper(node.right , list);
        }
        public Node desirilization(List<String>list){
            Node newhead = new Node(Integer.valueOf(list.get(0)));
            return newhead;
        }

    public static void main(String[] args) {
       
    }
}
