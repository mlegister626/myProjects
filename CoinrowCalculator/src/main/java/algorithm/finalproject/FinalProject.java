

package algorithm.finalproject;

import java.io.*;
import static java.lang.Integer.max;
import java.util.*;

/**
 *
 * @author mlegi
 */
public class FinalProject {
    public static int iter = 0;
    public static int rec = 0;
    public static int memo = 0;
    private static int[] memoArray;
public static int Fibonacci(double n){
    int count = 0;
    if(n == 0){
        return 0;
    }else if(n ==1){
        return 1;
    }
    else{
        long fibCurr= 1;
        long fibNext= 0;
        long fibPrev = 0;
        for(int i = 2; i <= n; i++){
            fibNext= fibCurr + fibPrev;
            fibPrev = fibCurr;
            fibCurr = fibNext;
            count++;
        }
        System.out.println("The fibonacci number at the " + (int)n + "th is: " + (fibNext));
        return count;
    }
   
}
public static long FibonacciIter1(long n){
    if(n == 0){
        return 0;
    }else if(n ==1){
        return 1;
    }
    else{
        long fibCurr= 1
                 ;
        long fibNext= 0;
        long fibPrev = 0;
        for(int i = 2; i <= n; i++){
            ++iter;
            fibNext= fibCurr + fibPrev;
            fibPrev = fibCurr;
            fibCurr = fibNext;
        }
        return fibCurr;
    }
   
}

public static long FibonacciRecursion1(long n){
    
    if(n ==0){
        return 0;
    }else if (n == 1){
        ++rec;
    return FibonacciRecursion1(n-1) + 1;
    }
    else{
       ++rec;
        return FibonacciRecursion1(n-1) + FibonacciRecursion1(n-2);
    }
}

 public static long MemoFibonacci1(long n, long fibMemo[]) {
     ++memo;
     long t = 0;
     if(fibMemo[(int)n] != -1){
         return fibMemo[(int)n];
     }else if(n ==0){
         return 0;
     }else if(n==1){
         return 1;
     }else{
        
        fibMemo[(int) n] = MemoFibonacci1(n - 1, fibMemo) + MemoFibonacci1(n - 2, fibMemo);
        return fibMemo[(int) n];
     }
             
 }
public static long[] fillArray(long [] n){
    for(int i =0; i < n.length; i++){
        n[i] = -1;
    }
    return n;
}
public static int coinRow(int n[]){
    
    int [] F= new int[n.length];
    F[0] = 0;
    F[1] = n[1];
    for(int i = 2; i < n.length; i++){
    F[i] = max(n[i] + F[i-2], F[i-1]);
    
    }
    return F[n.length -1];
}
public static int coinRowIter(int n[]){
    
    int [] F= new int[n.length];
    F[0] = 0;
    F[1] = n[1];
    for(int i = 2; i < n.length; i++){
    F[i] = max(n[i] + F[i-2], F[i-1]);
    iter++;
    }
    return F[n.length -1];
}
public static int coinRowRec(int n[], int i) {
    
    if (i == 0) {
        return 0;
    } else if (i == 1) {
        return n[1];
    } else {
        rec++;
        int includeCurrent = n[i] + coinRowRec(n, i - 2);
        int excludeCurrent = coinRowRec(n, i - 1);
        return Math.max(includeCurrent, excludeCurrent);
    }
}
  public static int coinRowMemo(int[] coinRow, int index, int[] memoArray) {
    if (index < 0) {
        return 0;
    }

    if (memoArray[index] != 0) {
        return memoArray[index];
    }

    int takeCurrent = coinRow[index] + coinRowMemo(coinRow, index - 2, memoArray);
    int skipCurrent = coinRowMemo(coinRow, index - 1, memoArray);

    memoArray[index] = Math.max(takeCurrent, skipCurrent);
    memo++;

    return memoArray[index];
}
 public static int countingPennies(int[][] pennies) {
    int[][] F = new int[pennies.length][pennies[0].length];
    F[0][0] = pennies[0][0];

    for (int i = 1; i < pennies.length; i++) {
        F[i][0] = F[i - 1][0] + pennies[i][0];
    }
    for (int j = 1; j < pennies[0].length; j++) {
        F[0][j] = F[0][j - 1] + pennies[0][j];
    }

    for (int i = 1; i < pennies.length; i++) {
        ++iter;
        for (int j = 1; j < pennies[0].length; j++) {
            F[i][j] = pennies[i][j] + Math.max(F[i][j - 1], F[i - 1][j]);
        }
    }
    return F[pennies.length - 1][pennies[0].length - 1];
}
  public static int countingPenniesRec(int[][] pennies, int row, int col) {  
        if (row == 0 && col == 0) {
            return pennies[0][0];
        }
        ++rec;
        if (row == 0) {
            return pennies[row][col] + countingPenniesRec(pennies, row, col - 1);
        }
        if (col == 0) {
            return pennies[row][col] + countingPenniesRec(pennies, row - 1, col);
        }
        
        int fromLeft = countingPenniesRec(pennies, row, col - 1);
        int fromAbove = countingPenniesRec(pennies, row - 1, col);
        return pennies[row][col] + Math.max(fromLeft, fromAbove);
    }
  public static int countingPenniesMemo(int[][] pennies, int row, int col, int[][] memos) {
        if (row == 0 && col == 0) {
            return pennies[0][0];
        }

        if (memos[row][col] != 0) {
            return memos[row][col];
        }
        memo++;
        int result;
        if (row == 0) {
            result = pennies[row][col] + countingPenniesMemo(pennies, row, col - 1, memos);
        } else if (col == 0) {
            result = pennies[row][col] + countingPenniesMemo(pennies, row - 1, col, memos);
        } else {
            
            int fromLeft = countingPenniesMemo(pennies, row, col - 1, memos);
            int fromAbove = countingPenniesMemo(pennies, row - 1, col, memos);
            result = pennies[row][col] + Math.max(fromLeft, fromAbove);
        }
        memos[row][col] = result;
        return result;
    }
   public static int[] doubleArray(int[] originalArray) {
        int originalLength = originalArray.length;
        int[] newArray = new int[2 * originalLength];

        System.arraycopy(originalArray, 0, newArray, 0, originalLength);

        System.arraycopy(originalArray, 0, newArray, originalLength, originalLength);

        return newArray;
    }
   public static String printArray(int[] array) {
        for (int value : array) {
            System.out.print(value + " ");
        }   
        return " ";
    }
    public static void main(String[] args) {
        long n = 13;
        long fibMemo[] = new long[1000];
        while (n <= 52){
            Fibonacci(n);
            System.out.println("The result of calculating the " + n +"th via Dynamic Programming is: " + FibonacciIter1(n));
            System.out.println("The result of calculating the " + n +"th via Recursion is: " + FibonacciRecursion1(n));
            fibMemo = fillArray(fibMemo);
            System.out.println("The result of calculating the "+ n + "th via Memoization is: " + MemoFibonacci1(n,fibMemo));
            System.out.println("\nNow we must calculate the run times by counting the calculations.");
            System.out.println("The counting the " + n +"th via Dynamic Programming is: " + iter);
            System.out.println("The counting the " + n +"th via Recursion is: " + rec);
            fibMemo = fillArray(fibMemo);
            System.out.println("The counting the "+ n + "th via Memoization is: " + memo);
            System.out.println();
            n += 13;
            iter =0;
            rec = 0; 
            memo = 0;
        }
      System.out.println("Now we'll continue the calculations with a coin row calculation\n");
      int[] coinRow = {0, 10, 1, 10, 5, 10, 25, 5};
     for (int i = 0; i < 3; i++) {
            int[] memoArray = new int[coinRow.length]; // Initialize memoArray here

            System.out.println("\nThe coin row of " + Arrays.toString(coinRow) + "\n has a result of: " + coinRow(coinRow));
            System.out.println("This coin row's result with iteratively is: " + coinRowIter(coinRow));
            System.out.println("This coin row's result with recursively is: " + coinRowRec(coinRow, coinRow.length - 1));
            System.out.println("This coin row's result with memoization is: " + coinRowMemo(coinRow, coinRow.length - 1, memoArray));

            System.out.println("\nNow we must calculate the run times by counting the calculations.");
            System.out.println("The counting the coin row operations via Dynamic Programming : " + iter);
            System.out.println("The counting the coin row operations via Recursion : " + rec);
            System.out.println("The counting the coin row operations via Memoization : " + memo);

            iter = 0;
            rec = 0;
            memo = 0;
            coinRow = doubleArray(coinRow);
        }
       
     
      System.out.println("Now we'll continue the caluclations with a Collecting Pennies calculation\n");
      int [][] pennyRows = {{2,5,2},{0,1,0},{5,3,7},{3,4,9}};
      
      System.out.println("The {{2,5,2},{0,1,0},{5,3,7},{3,4,9}} answer to this array is following: " + countingPennies(pennyRows));
      System.out.println("The counting pennies result done iteratively is: " + countingPennies(pennyRows));
      System.out.println("The counting pennies result done recurisvely is: " + countingPenniesRec(pennyRows,pennyRows.length-1,pennyRows[0].length-1));
      int numRows = pennyRows.length;
      int numCols = pennyRows[0].length;
      int[][] memos = new int[numRows][numCols];
      System.out.println("The counting pennies result done with memoization is: " + countingPenniesMemo(pennyRows, pennyRows.length-1, pennyRows[0].length -1, memos));
      
      System.out.println("\nNow we must calculate the run times by counting the calculations.");
      System.out.println("The counting the collecting coins operations via Dynamic Programming : " + iter);
      System.out.println("The counting the collecting coins operations operations via Recursion : " + rec);
      System.out.println("The counting the collecting coins operations operations via Memoization : " + memo);
      iter = 0;
      rec = 0;
      memo = 0;
      
      int [][] pennyRows1 = {{2,5,2,5},{0,1,0,1},{5,3,7,3},{3,4,9,4},{2,0,5,3}};      
      System.out.println("\nThe {{2,5,2,5},{0,1,0,1},{5,3,7,3},{3,4,9,4},{2,0,5,3}} answer to this array is following: " + countingPennies(pennyRows1));
      System.out.println("The counting pennies result done iteratively is: " + countingPennies(pennyRows1));
      System.out.println("The counting pennies result done recurisvely is: " + countingPenniesRec(pennyRows1,pennyRows1.length-1,pennyRows1[0].length-1));
      numRows = pennyRows1.length;
      numCols = pennyRows1[0].length;
      memos = new int[numRows][numCols];
      System.out.println("The counting pennies result done with memoization is: " + countingPenniesMemo(pennyRows1, pennyRows1.length-1, pennyRows1[0].length -1, memos));
      
      System.out.println("\nNow we must calculate the run times by counting the calculations.");
      System.out.println("The counting the collecting coins operations via Dynamic Programming : " + iter);
      System.out.println("The counting the collecting coins operations operations via Recursion : " + rec);
      System.out.println("The counting the collecting coins operations operations via Memoization : " + memo);
      iter = 0;
      rec = 0;
      memo = 0;
      int [][] pennyRows2 = {{2,5,2,5,4},{0,1,0,1,5},{5,3,7,3,3},{3,4,9,4,6},{2,0,5,3,7},{5,0,3,4,6}};      
      System.out.println("\nThe {{2,5,2,5,4},{0,1,0,1,5},{5,3,7,3,3},{3,4,9,4,6},{2,0,5,3,7},{5,0,3,4,6}} answer to this array is following: " + countingPennies(pennyRows2));
      System.out.println("The counting pennies result done iteratively is: " + countingPennies(pennyRows2));
      System.out.println("The counting pennies result done recurisvely is: " + countingPenniesRec(pennyRows2,pennyRows2.length-1,pennyRows2[0].length-1));
      numRows = pennyRows2.length;
      numCols = pennyRows2[0].length;
      memos = new int[numRows][numCols];
      System.out.println("The counting pennies result done with memoization is: " + countingPenniesMemo(pennyRows2, pennyRows2.length-1, pennyRows2[0].length -1, memos));
      
      System.out.println("\nNow we must calculate the run times by counting the calculations.");
      System.out.println("The counting the collecting coins operations via Dynamic Programming : " + iter);
      System.out.println("The counting the collecting coins operations operations via Recursion : " + rec);
      System.out.println("The counting the collecting coins operations operations via Memoization : " + memo);
      iter = 0;
      rec = 0;
      memo = 0;
      int [][] pennyRows3 = {{2,5,2,5,4,6},{0,1,0,1,5,7},{5,3,7,3,3,3},{3,4,9,4,6,1},{2,0,5,3,7,3},{5,0,3,4,6,8},{3,7,5,3,6,9}};      
      System.out.println("\nThe {{2,5,2,5,4},{0,1,0,1,5},{5,3,7,3,3},{3,4,9,4,6},{2,0,5,3,7},{5,0,3,4,6}} answer to this array is following: " + countingPennies(pennyRows3));
      System.out.println("The counting pennies result done iteratively is: " + countingPennies(pennyRows3));
      System.out.println("The counting pennies result done recurisvely is: " + countingPenniesRec(pennyRows3,pennyRows3.length-1,pennyRows3[0].length-1));
      numRows = pennyRows3.length;
      numCols = pennyRows3[0].length;
      memos = new int[numRows][numCols];
      System.out.println("The counting pennies result done with memoization is: " + countingPenniesMemo(pennyRows3, pennyRows3.length-1, pennyRows3[0].length -1, memos));
      
      System.out.println("\nNow we must calculate the run times by counting the calculations.");
      System.out.println("The counting the collecting coins operations via Dynamic Programming : " + iter);
      System.out.println("The counting the collecting coins operations operations via Recursion : " + rec);
      System.out.println("The counting the collecting coins operations operations via Memoization : " + memo);
      iter = 0;
      rec = 0;
      memo = 0;
    }
}
