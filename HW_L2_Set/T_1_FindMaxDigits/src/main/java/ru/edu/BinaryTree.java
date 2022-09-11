package ru.edu;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree {

    private Node root;

    public boolean add(int value) {

        Node previous = null;
        Node tmp = root;
        int cmp = 0;

        while (tmp != null) {
            cmp = Integer.compare(value, tmp.value);
            if (cmp == 0) {
                return false;
            }
            previous = tmp;
            if (cmp < 0) {
                tmp = tmp.left;
            } else {
                tmp = tmp.right;
            }
        }
        Node newNode = new Node(value);
        if (previous == null) {
            root = newNode;
        } else {
            if (cmp < 0) {
                previous.left = newNode;
            } else {
                previous.right = newNode;
            }
        }
        newNode.parent = previous;
        return true;
    }

    /**
     * Find maximum elements not greater than upper bound.
     * Returns array with maximum elements.
     *
     * @param count      - count of maximums
     * @param upperBound - upper bound of maximums
     */
    public int[] findMaxDigits(int count, int upperBound) {
        int[] res = new int[count];
        int index = count - 1;
        int cmp = 0;

        Node tmp = root;
        Node prev = null;
        int tmpUpperBound = Integer.valueOf(upperBound);
        int maxValueInNode = findMax(tmp);

        while (tmp != null) {
            cmp = Integer.compare(tmpUpperBound, tmp.value);
            if (cmp == 0) {
                if (index < 0) {
                    return res;
                }
                res[index] = tmp.value;
                tmp = tmp.parent;
                tmpUpperBound--;
                index--;
            } else if (cmp < 0) {
                prev = tmp;
                tmp = tmp.left;
            } else { // cmp > 0
                prev = tmp;
                tmp = tmp.right;
            }
            if (maxValueInNode < upperBound && tmp == null) {
                while (index > -1) {
                    res[index] = prev.value;
                    prev = prev.parent;
                    index--;
                }
            }
        }
        return Arrays.stream(res).filter(x -> x != 0).toArray();
    }

    public void print() {
        System.out.print("Tree:");
        print(root);
        System.out.println();
    }

    private void print(Node node) {
        if (node != null) {
            print(node.left);
            System.out.print(" " + node.value);
            print(node.right);
        }
    }

    private int findMax(Node node) {
        if (node == null)
            return Integer.MIN_VALUE;

        int res = node.value;
        int lres = findMax(node.left);
        int rres = findMax(node.right);

        if (lres > res)
            res = lres;
        if (rres > res)
            res = rres;
        return res;
    }
}