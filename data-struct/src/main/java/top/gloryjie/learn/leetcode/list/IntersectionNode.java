package top.gloryjie.learn.leetcode.list;

import org.junit.Test;

/**
 * 找到单向链表相交的节点
 *
 * @author jie
 * @since 2020/7/26
 */
public class IntersectionNode {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public ListNode solutionA(ListNode headA, ListNode headB) {
        ListNode t1 = headA;
        ListNode t2 = headB;

        while (t1 != t2) {
            t1 = t1.next == null ? headB : t1.next;
            t2 = t2.next == null ? headA : t2.next;
        }
        return t1;
    }


    /**
     * 思路:
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode solutionB(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode t1 = headA;
        ListNode t2 = headB;

        // 计算长度
        int countA = 1;
        int countB = 1;
        while ((t1 = t1.next) != null) {
            countA++;
        }
        while ((t2 = t2.next) != null) {
            countB++;
        }

        // 根据长度的差距n,将较长的链表先走n个位置
        t1 = headA;
        t2 = headB;
        if (countA > countB) {
            // headA队列长
            int step = countA - countB;
            while (step > 0) {
                t1 = t1.next;
                step--;
            }
        } else {
            int step = countB - countA;
            while (step > 0) {
                t2 = t2.next;
                step--;
            }
        }

        // 逐个遍历
        while (t1 != null && t2 != null && t1 != t2) {
            t1 = t1.next;
            t2 = t2.next;
        }

        return t1;
    }


    @Test
    public void testCorrect() {
        // 构造相同的节点
        ListNode fourth = new ListNode(4);
        fourth.next = new ListNode(5);

        // headA 链表: 1->2->3->4->5
        ListNode headA = new ListNode(1);
        headA.next = new ListNode(2);
        headA.next.next = new ListNode(3);
//        headA.next.next.next = fourth;

        // headB 链表: 7->6->4->5
        ListNode headB = new ListNode(7);
        headB.next = new ListNode(6);
//        headB.next.next = fourth;

        // 构造相同长度
//        headB.next.next = new ListNode(9);
//        headB.next.next.next = fourth;

        ListNode targetNode = this.solutionB(headA, headB);

        System.out.println(targetNode == fourth);

    }


}
