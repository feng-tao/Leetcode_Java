/*
Source: https://leetcode.com/problems/remove-duplicates-from-sorted-list/
Date: 12/23/2016

********************************************************************************
Given a sorted linked list, delete all duplicates such that each element appear only once.

For example,
Given 1->1->2, return 1->2.
Given 1->1->2->3->3, return 1->2->3.
********************************************************************************

Note: 
No need to use dummy nodes because we are not possibly deleting the first node of the list
Make sure walker is not null. if walker is null, then walker.val and walker.next will throw exception
 */
package Leetcode_Java.linked_list_medium;


/**
 *
 * @author Borui Wang
 */
public class RemoveDuplicates {

    public class ListNode {

        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    //uses two pointers like in remove duplicates from sorted arrays
    //invariant head->unique elements -> i -> duplicates -> j -> unknown

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

         ListNode i = head;
         ListNode j = head;

        while (j != null) {
            //record current value
            int val = j.val;
            //j stops on the first different element
            while (j != null && j.val == val) {
                j = j.next;
            }
            i.next = j;
            i = i.next;
        }
        return head;
    }
    //this solution only keeps one pointer, if that is equal to its next, 
    public ListNode deleteDuplicatesOnePointer(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode walker = head;

        while (walker.next != null) {
            if (walker.val == walker.next.val) {
                walker.next = walker.next.next;
            } else {
                walker = walker.next;
            }
        }
        return head;
    }
}
