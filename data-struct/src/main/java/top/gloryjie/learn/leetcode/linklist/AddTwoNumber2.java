package top.gloryjie.learn.leetcode.linklist;

/**
 * @author Jie
 * @since 2020/8/2
 */
public class AddTwoNumber2 {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 进位标志
        boolean flag = false;
        ListNode head = null;
        ListNode current = null;
        int sum = 0;
        while(l1 != null && l2 != null){
            sum = l1.val + l2.val;
            if(flag){
                sum += 1;
                flag = false;
            }
            if(sum > 9){
                sum = sum % 10;
                flag = true;
            }
            if(head == null){
                head = new ListNode(sum);
                current = head;
            }else{
                current.next = new ListNode(sum);
                current = current.next;
            }
            l1 = l1.next;
            l2 = l2.next;
        }

        while(l1 != null){
            sum = l1.val;
            if(flag){
                sum += 1;
                flag = false;
            }
            if(sum > 9){
                sum = sum % 10;
                flag = true;
            }
            current.next = new ListNode(sum);
            current = current.next;
            l1 = l1.next;
        }
        while(l2 != null){
            sum = l2.val;
            if(flag){
                sum += 1;
                flag = false;
            }
            if(sum > 9){
                sum = sum % 10;
                flag = true;
            }
            current.next = new ListNode(sum);
            current = current.next;
            l2 = l2.next;
        }

        if(flag){
            current.next = new ListNode(1);
        }

        return head;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head == null || n <= 0 || head.next == null){
            return null;
        }
        ListNode nNode = head;
        for(int i=0;i<n;i++){
            if(nNode == null){
                return head;
            }
            nNode = nNode.next;
        }
        ListNode prev = head;
        while(nNode.next != null){
            prev = prev.next;
            nNode = nNode.next;
        }

        prev.next = prev.next.next;

        return head;
    }


    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
//        ListNode node2 = new ListNode(9);
//        node2.next = new ListNode(9);
//
        AddTwoNumber2 solution = new AddTwoNumber2();
//        ListNode result = solution.addTwoNumbers(node1, node2);
//
//        System.out.println(result.val);

        ListNode node = solution.removeNthFromEnd(node1, 1);
        System.out.println(node.val);
    }
}
