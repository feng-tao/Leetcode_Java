/*
Source: https://leetcode.com/problems/binary-tree-level-order-traversal/

********************************************************************************
Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its level order traversal as:
[
  [3],
  [9,20],
  [15,7]
]
********************************************************************************

Solution:
1. use 2 queues: add root into q 1, poll, push its children into q2, poll, add their children to q1 ...
2. use 1 queue with dummy nodes, add root into q1, add # as dummy node to mark its end, poll until we see #, add children then # ...
3. use 1 queue (code below)

Follow up:
*how to reverse order level? return below instead
[
  [15,7],
  [9,20],
  [3],
]
instead of adding to the levels list do this levels.add(0,level) which reverses the order of adding

*what about zigzag level order traversal we return below?
[
  [3],
  [20,9],
  [15,7]
]
use a boolean variable fromLeft to make sure the adding direction on each level, then set fromLeft = !fromLeft after one level is finished
 */
package Leetcode_Java.tree_easy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author Borui Wang
 */
public class LevelOrderTraversal {

    static class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> levels = new ArrayList();
        Queue<TreeNode> queue = new LinkedList();
        queue.add(root);

        while (!queue.isEmpty()) {
            //size needs to be kept in the begining of the loop, because size changes as more elements are polled out of queue
            int size = queue.size();
            List<Integer> level = new ArrayList();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node != null) {
                    level.add(node.val);
                    queue.add(node.left);
                    queue.add(node.right);
                }
            }
            if (!level.isEmpty()) {
                levels.add(level);
            }
        }
        return levels;
    }
}
