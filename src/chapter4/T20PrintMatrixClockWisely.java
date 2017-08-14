package chapter4;

/**
 * 面试题20：顺时针打印矩阵
 * 题目：输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
 *
 * 思路：
 * 1. 打印第一圈的左上角的坐标是（1，1），第二圈的左上角的坐标是（2，2），依次类推。我们注意到，左上角的坐标中行标和列标总是相同的，于是可以在矩阵中选取左上角
 * 为(start,start)的一圈作为我们分析的目标。可以判断出循环继续的条件时columns>startX*2 && rows>startY*2。
 * 接下来就是打印一圈的功能，分为四步：（1）第一步从左到右打印一行，第二步从上到下打印一列，第三步从右到左打印一行，第四步从上到下打印一列。
 * 注意到每一步打印都需要一个前提条件：
 * （1）其中第一步总是需要的，因为打印一圈至少有一步。
 * （2）如果只有一行，那么就不用第二步了。也就是说第二步的前提条件是终止行号大于起始行号。
 * （3）第三步需要圈内至少有两行两列，也就是说除了要求终止行号大于起始行号之外，还要求终止列号大于起始列号。
 * （4）同理，第四步需要至少有三行两列，因此要求终止行号比起始行号至少大于2，同时终止列号大于起始列号。
 *
 * 测试用例：
 * 1. 数组多行多列
 * 2. 数组只有一行，只有一列，只有一行一列
 *
 * Created by 18710 on 2017/8/14.
 */
public class T20PrintMatrixClockWisely {

    /**
     *  输入一个矩阵，按照从外向里以顺时针的顺序依次打印每一个数字
     * @param numbers m*n的数组
     */
    public static void printMatrixClockWisely(int[][] numbers) {
        if (numbers == null || numbers.length == 0) {
            return;
        }
        int start = 0; // 起始点坐标
        int rows = numbers.length; // 行数
        int columns = numbers[0].length; // 列数
        while (rows > start * 2 && columns > start * 2) { // 终止条件
            print(numbers, rows, columns, start); // 每次打印一圈的数字
            start++;
        }
    }

    /**
     * 每次循环打印一圈数组
     * @param numbers 数组
     * @param rows 行数
     * @param columns 列数
     * @param start 起始位置
     */
    public static void print(int[][] numbers, int rows, int columns, int start) {
        int endY = columns - start - 1;
        int endX = rows - start - 1;
        // 打印从左到右的元素
        for (int i = start; i <= endY; i++) {
            System.out.println(numbers[start][i]);
        }
        // 打印从上到下的元素，条件是至少存在两列
        if (endX > start) {
            for (int i = start + 1; i < endX; i++) {
                System.out.println(numbers[i][endY]);
            }
        }
        // 打印从右到左的元素，条件是至少存在两行两列
        if (endX > start && endY > start) {
            for (int i = endY - 1; i >= start ; i--) {
                System.out.println(numbers[endX][i]);
            }
        }
        // 打印从下到上的元素，条件是至少存在三行两列
        if (endX - 1 > start && endY > start) {
            for (int i = endX - 1; i >= start + 1; i--) {
                System.out.println(numbers[i][start]);
            }
        }
    }

    public static void main(String[] args) {
        int[][] numbers = {{1,2,3},{8,9,4},{7,6,5}};
        printMatrixClockWisely(numbers);
    }

}
