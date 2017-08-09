package chapter2;

import bean.TreeNode;

/**
 * 树是一种在实际编程中经常遇到的数据结构。它的逻辑很简单：除了根节点之外的每个节点只有一个父节点，根节点没有父节点；
 * 除了叶节点之外的所有节点只有一个或多个子节点，叶节点没有子节点。父节点和子节点之间用指针链接。
 * 由于树的操作设计大量的指针，因此与树有关的面试题都不太容易。
 *
 * 面试的时候提到的树，大部分都是二叉树。所谓二叉树是树的一种特殊结构，在二叉树中每个节点最多只能有两个子节点。
 * 在二叉树中最重要的操作莫过于遍历，即按照某一顺序访问树种的所有节点。通常树有如下几种遍历方式：
 * 	1. 前序遍历：先访问根节点，再访问左子节点，最后访问右子节点。
 * 	2. 中序遍历：先访问左子节点，再访问根节点，最后访问右子节点。
 * 	3. 后序遍历：先访问左子节点，再访问右节点，最后访问根节点。
 * 这3种遍历都有递归和循环两种不同的实现方法，每一种遍历的递归实现都比循环实现要简捷很多。
 *
 * 很多面试官喜欢直接或间接考查遍历（面试题39“二叉树的深度”、面试题18“树的子结构”、面试题25“二叉树中和为某一值的路径”）的具体代码实现，
 * 面试题6“重建二叉树”、面试题24“二叉树的后序遍历序列”也是考查遍历特点的理解。因此，应聘者应该对这3中遍历的6种实现方法都了如指掌。
 * 	1. 宽度优先遍历：先访问树的每一层节点，再访问树的第二层节点。。。一直到访问到最下面一层节点。在同一层节点中，从左到右的顺序依次访问。
 * 	我们可以对包括二叉树在内的所有树进行宽度优先遍历。面试题23“从上到下遍历二叉树”就是考察宽度优先遍历算法的题目。
 *
 * 二叉树有很多特例，二叉搜索树就是其中之一。在二叉搜索树种，左子节点总是小于或等于根节点，而右子节点总是大于或等于根节点。
 * 我们可以平均在O(logn)的时间内根据数值在二叉搜索树种找到一个节点。二叉搜索树的面试题有很多，比如面试题50“树中两个节点的最低公共祖先”、
 * 面试题27“二叉搜索树与双向链表”。
 *
 * 二叉树的另外两个特例是堆和红黑树。
 * 堆分为最大堆和最小堆。在最大堆中根节点的值最大，在最小堆中根节点的值最小。有很多需要快速查找到最大值或者最小值的问题都可以用堆来解决。
 * 红黑树是把树中的节点定义为红、黑两种颜色，并通过规则确保从根节点到叶节点的最长路径的长度不超过最短路径的两倍。与堆和红黑树相关的面试题，
 * 请参考面试题30“求最小的k个数字”
 *
 * 面试题6：重建二叉树
 * 题目：输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 * 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
 *
 * 本题考点：
 * 	1. 考查应聘者对二叉树的前序遍历、中序遍历的理解程度。只有对二叉树的不同遍历算法有了深刻的理解，应聘者才有可能在遍历序列中划分出左右子树对应的子序列。
 *  2. 考查应聘者分析复杂问题的能力。我们把构建二叉树的大问题分解为构建左右子树的两个小问题。我们小问题和大问题在本质上是一致的，因此可以用递归的方式解决。
 * Created by 郑元浩 on 2017/8/9.
 */
public class T6ReConstructBinaryTree {

    public static void main(String[] args) {
        int[] pre = {1,2,3,4,5,6,7};
        int[] in = {3,2,4,1,6,5,7};
        System.out.println(reConstructBinaryTree(pre, in));
    }

    /**
     * 在函数中，我们先根据前序遍历序列的第一个数字创建根节点，接下来在中序遍历序列中找到根节点的位置，这样子就能确定左、右子树节点的数量。
     * 在前序遍历和中序遍历的序列中划分了左、右子树节点的值之后，我们就可以递归地调用函数，区分别构建它的左右子树。
     *
     * @param pre 前序遍历
     * @param in 中序遍历
     * @return
     */
    public static TreeNode reConstructBinaryTree(int [] pre, int [] in) {
        if (pre == null || in == null) {
            return null;
        }
        return construct(pre, 0, pre.length - 1, in, 0, in.length - 1);
    }

    /**
     *
     * @param pre 前序遍历
     * @param ps  前序遍历的开始位置
     * @param pe  前序遍历的结束位置
     * @param in  中序遍历
     * @param is  中序遍历的开始位置
     * @param ie  中序遍历的结束位置
     * @return
     */
    public static TreeNode construct(int[] pre, int ps, int pe, int[] in, int is, int ie){
        if (ps > pe) {
            return null;
        }
        int rootVal = pre[ps]; // 前序遍历的根节点
        int rootIndex = is; // 根节点在中序遍历的下标
        while (rootIndex < ie && in[rootIndex] != rootVal) { // 确定下标的值，默认是可以找到的
            rootIndex++;
        }
        TreeNode root = new TreeNode(rootVal);
        root.left = construct(pre, ps + 1, ps + rootIndex - is, in, is, rootIndex - 1); // 递归构造左子树
        root.right = construct(pre, ps + rootIndex - is + 1, pe, in, rootIndex + 1, ie); // 递归构造右子树
        return root;
    }

}

