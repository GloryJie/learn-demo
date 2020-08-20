package top.gloryjie.learn.leetcode.linklist;

/**
 * @author Jie
 * @since 2020/8/2
 */
public class Palindrome234 {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        // 只有两个节点的情况
        if (head.next.next == null) {
            return head.val == head.next.val;
        }
        ListNode one = head.next;
        ListNode two = head.next.next;

        while (two != null && two.next != null) {
            one = one.next;
            two = two.next.next;
        }

        ListNode first = head;
        ListNode second = reverseList(one); // 反转下半部分
        while (second != null) {
            if (second.val == first.val) {
                second = second.next;
                first = first.next;
            } else {
                return false;
            }
        }
        return true;
    }

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode tail = head;
        ListNode current = head.next;
        // 第一个节点执行null
        tail.next = null;
        // next只是一个临时节点
        ListNode next = null;
        while (current != null) {
            // 保留next链路
            next = current.next;
            // 反转
            current.next = tail;
            tail = current;

            current = next;
        }
        return tail;
    }

    public static void main(String[] args) {
        int[] array = {1,1,2,1};

        ListNode head = null;
        ListNode node = null;
        for (int i = 0; i < array.length; i++) {
            if (i == 0){
                head = new ListNode(array[i]);
                node = head;
            }else{
                node.next = new ListNode(array[i]);
                node = node.next;
            }
        }

        Palindrome234 solution = new Palindrome234();
        boolean result = solution.isPalindrome(head);
        System.out.println(result);

        int[] test = new int[]{1};

        int i = Integer.parseUnsignedInt("101", 2);
        System.out.println(i);

    }
}
