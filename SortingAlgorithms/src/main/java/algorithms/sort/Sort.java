/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package algorithms.sort;
import java.util.Arrays;
import java.util.Random;
/**
 *
 * @author mlegi
 */
public class Sort {
    

    public static void selectSort (int[] A){
        int temp; int count = 0;
        for (int i = 0; i < (A.length-1); i++)
    {
    int min = i;
        for (int j = i+1; j < A.length; j++)
        {
            count++;
            if (A[j] < A[min]) {
                min = j;
            }
        }
    swap(A, i, min);
    }
    }
    public static void bubbleSort(int[] A) {
        int i, j, temp;
        for (i = (A.length - 1); i >= 0; i--) {
            for (j = 1; j <= i; j++) {
                if (A[j - 1] > A[j])
                    swap(A, j - 1, j);
            }
        }
    }
    public static void bubbleSortImproved(int[] A) {
        int i, j, temp;
        boolean noSwaps;
        for (i = (A.length - 1); i >= 0; i--) {
            noSwaps = true;
            for (j = 1; j <= i; j++) {
                if (A[j - 1] > A[j]) {
                    swap(A, j - 1, j);
                    noSwaps = false;
                }
            }
        if (noSwaps) {break;}
        }
}
    public static void insertSort (int[] A)
    {
        int j, v;
        for (int i = 1; i <= (A.length - 1); i++)
        {
            j = i;
            v = A[i];
            while ((j > 0) && (v < A[j - 1])) {
                A[j] = A[j - 1];
                j--;
            }
        A[j] = v;
        }
}
    public static void quickSort (int[] A, int l, int r)
    {
    // Sorts the subarray of A from index l to index r
    // Uses the element at index r as the pivot
        int temp;
        int j;
        int v;
        int i, k;
        if (r > l) {
            int s = partition(A, l, r);
            quickSort(A, l, s -1);
            quickSort(A, s + 1, r);
            }
}
public static void quickSortMedianOf3(int[] A, int l, int r) {
// Sorts the subarray of A from index l to index r
// Uses the element at index r as the pivot
int temp;
int j;
int v;
int i, k;
if (r > l) {
int mid = (l + r)/2;
sort3Elements(A, l, mid, r);
swap(A, mid, r); // puts pivot at right end
int s = partition(A, l, r);
quickSortMedianOf3(A, l, s -1);
quickSortMedianOf3(A, s + 1, r);
}
}
public static int partition(int[] A, int l, int r) {
// returns index of pivot after it is switched into position
int temp;
int j;
int v;
int i, k;
v = A[r]; // pivot
i = l - 1;
j = r ;
while (true) {
i++;
while ((i < j) && (A[i] < v)) {i++;}
j--;
while ((j > i) && (A[j] > v)) {j--;}
if (i >= j) {break;}
swap(A, i, j);
}
swap(A, i, r);
return i;
}
public static void sort3Elements(int[] arr, int index1, int mid, int index2) {
if (arr[index1] > arr[mid])
swap(arr, index1, mid);
if (arr[mid] > arr[index2])
swap(arr, mid, index2);
if (arr[index1] > arr[mid])
swap(arr, index1, mid);
}
public static void shellSort (int[] A)
{
int i, j, h, v;
int N = A.length;
for (h = 1; h < N/9; h = 3*h + 1);
for ( ; h > 0; h /= 3)
{
for (i = h ; i < N; i++) {
v = A[i];
j = i;
while ((j >= h) && (A[j - h] > v))
{
A[j] = A[j - h];
j -= h;
}
A[j] = v;
}
}
}
public static void mergeSort (int[] A)
{
int i = 0;
int j = 0;
int k = 0;
if (A.length > 1) {
int [] B, C;
int bsize = A.length/2;
int csize = A.length - bsize;
B = new int[bsize];
C = new int[csize];
for (i = 0; i < bsize; i++ ) {
B[i] = A[i];
}
for (i = bsize; i < A.length; i++ ) {
C[i - bsize] = A[i];
}
mergeSort(B);
mergeSort(C);
merge(B,C,A);
}
}
public static void merge(int[] B, int[] C, int[] A) {
int i = 0;
int j = 0;
int k = 0;
int bsize = B.length;
int csize = C.length;
while ((i < bsize) && (j < csize))
{
if (B[i] <= C[j]) {
A[k] = B[i];
i++;
}
else {
A[k] = C[j];
j++;
}
k++;
}
if (i == bsize) {
for (int m = k; m < A.length; m++ ) {
A[m] = C[j];
j++;
}
}
else {
for (int m = k; m < A.length; m++ ) {
A[m] = B[i];
i++;
}
}
}
public static void swap(int[] arr, int index1, int index2) {
int temp = arr[index1];
arr[index1] = arr[index2];
arr[index2] = temp;
}
////////////////////////////// 3 types of sorts
public static void sorted(int []a){
    for(int i = 0; i < a.length; i++){
        a[i] = i;
    }
}
public static void reverseSorted(int[]a){
    for(int i = a.length -1 ; i > 0; i--){
        a[i] = i;
    }
}
public static void randomSort(int [] a){
    Random rand = new Random();
    for(int i = 0; i < a.length; i++){
        int rand1 = rand.nextInt(a.length);
        a[i]= rand1;
    }
}
    public static void main(String[] args) {
        int [] sorted1 = new int [30000];
        int [] reverse = new int [30000];
        int [] random = new int [30000];
        
        sorted(sorted1);
        reverseSorted(reverse);
        randomSort(random);
        long timer = System.currentTimeMillis();
        insertSort(sorted1);
        long endTime = System.currentTimeMillis();
        double totalTime = (endTime - timer) / 1000.0;
        System.out.println("Insertion Sort in order array " + totalTime);
        timer = System.currentTimeMillis();
        insertSort(reverse);
        endTime = System.currentTimeMillis();
        totalTime = (endTime - timer) / 1000.0;
        System.out.println("Insertion Sort reverse ordered array " + totalTime);
        timer = System.currentTimeMillis();
        insertSort(random);
        endTime = System.currentTimeMillis();
        totalTime = (endTime - timer) / 1000.0;
        System.out.println("Insertion Sort randomly sorted array " + totalTime);
        
        ////////////////////////////// insertSort^
        sorted(sorted1);
        reverseSorted(reverse);
        randomSort(random);
        timer = System.currentTimeMillis();
        bubbleSort(sorted1);
        endTime = System.currentTimeMillis();
        totalTime = (endTime - timer) / 1000.0;
        System.out.println("Bubble Sort ordered array " + totalTime);
        timer = System.currentTimeMillis();
        bubbleSort(reverse);
        endTime = System.currentTimeMillis();
        totalTime = (endTime - timer) / 1000.0;
        System.out.println("Bubble Sort reverse ordered array " + totalTime);
        timer = System.currentTimeMillis();
        bubbleSort(random);
        endTime = System.currentTimeMillis();
        totalTime = (endTime - timer) / 1000.0;
        System.out.println("Bubble Sort randomly ordered array " + totalTime);
        
        /////////////////////////bubbleSort^
        sorted(sorted1);
        reverseSorted(reverse);
        randomSort(random);
        timer = System.currentTimeMillis();
        bubbleSortImproved(sorted1);
        endTime = System.currentTimeMillis();
        totalTime = (endTime - timer) / 1000.0;
        System.out.println("Imporved Bubble Sort ordered array " + totalTime);
        timer = System.currentTimeMillis();
        bubbleSortImproved(reverse);
        endTime = System.currentTimeMillis();
        totalTime = (endTime - timer) / 1000.0;
        System.out.println("Imporved Bubble Sort reverse ordered array " + totalTime);
        timer = System.currentTimeMillis();
        bubbleSortImproved(random);
        endTime = System.currentTimeMillis();
        totalTime = (endTime - timer) / 1000.0;
        System.out.println("Imporved Bubble Sort randomly ordered array " + totalTime);
        
        ///////////////////////////improved bubble sort^
        sorted(sorted1);
        reverseSorted(reverse);
        randomSort(random);
        timer = System.currentTimeMillis();
        quickSort(sorted1, 0, sorted1.length-1);
        endTime = System.currentTimeMillis();
        totalTime = (endTime - timer) / 1000.0;
        System.out.println("Quick sort ordered array " + totalTime);
        timer = System.currentTimeMillis();
        quickSort(reverse, 0, reverse.length-1);
        endTime = System.currentTimeMillis();
        totalTime = (endTime - timer) / 1000.0;
        System.out.println("Quick Sort reverse ordered array " + totalTime);
        timer = System.currentTimeMillis();
        quickSort(random, 0, random.length-1);
        endTime = System.currentTimeMillis();
        totalTime = (endTime - timer) / 1000.0;
        System.out.println("Quick Sort randomly ordered array " + totalTime);
        
        /////////////////////////////Quick Sort ^^^
        sorted(sorted1);
        reverseSorted(reverse);
        randomSort(random);
        timer = System.currentTimeMillis();
        quickSortMedianOf3(sorted1, 0, sorted1.length-1);
        endTime = System.currentTimeMillis();
        totalTime = (endTime - timer) / 1000.0;
        System.out.println("Quick sort median of 3 ordered array " + totalTime);
        
        timer = System.currentTimeMillis();
        quickSortMedianOf3(reverse, 0, reverse.length-1);
        endTime = System.currentTimeMillis();
        totalTime = (endTime - timer) / 1000.0;
        System.out.println("Quick sort median of 3 reverse ordered array " + totalTime);
        
        timer = System.currentTimeMillis();
        quickSortMedianOf3(random, 0, random.length-1);
        endTime = System.currentTimeMillis();
        totalTime = (endTime - timer) / 1000.0;
        System.out.println("Quick sort median of 3 randomly ordered array " + totalTime);
        ///////////////// Quick Sort median of 3
        
        sorted(sorted1);
        reverseSorted(reverse);
        randomSort(random);
        timer = System.currentTimeMillis();
        mergeSort(sorted1);
        endTime = System.currentTimeMillis();
        totalTime = (endTime - timer) / 1000.0;
        System.out.println("Merge sorts ordered array " + totalTime);
        timer = System.currentTimeMillis();
        mergeSort(reverse);
        endTime = System.currentTimeMillis();
        totalTime = (endTime - timer) / 1000.0;
        System.out.println("Merge sorts reverse ordered array " + totalTime);
        timer = System.currentTimeMillis();
        mergeSort(random);
        endTime = System.currentTimeMillis();
        totalTime = (endTime - timer) / 1000.0;
        System.out.println("Merge sorts randomly ordered array " + totalTime);
        /////////////////////////////////merge ^^^^
        
        sorted(sorted1);
        reverseSorted(reverse);
        randomSort(random);
        timer = System.currentTimeMillis();
        shellSort(sorted1);
        endTime = System.currentTimeMillis();
        totalTime = (endTime - timer) / 1000.0;
        System.out.println("Shell sort ordered array " + totalTime);
        timer = System.currentTimeMillis();
        shellSort(sorted1);
        endTime = System.currentTimeMillis();
        totalTime = (endTime - timer) / 1000.0;
        System.out.println("Shell sort reverse ordered array " + totalTime);
        timer = System.currentTimeMillis();
        shellSort(sorted1);
        endTime = System.currentTimeMillis();
        totalTime = (endTime - timer) / 1000.0;
        System.out.println("Shell sort randomly ordered array " + totalTime);
        ///////////////////////shell sort^^^
        
        sorted(sorted1);
        reverseSorted(reverse);
        randomSort(random);
        timer = System.currentTimeMillis();
        Arrays.sort(sorted1);
        endTime = System.currentTimeMillis();
        totalTime = (endTime - timer) / 1000.0;
        System.out.println("Array.sort sort ordered array " + totalTime);
        timer = System.currentTimeMillis();
        Arrays.sort(reverse);
        endTime = System.currentTimeMillis();
        totalTime = (endTime - timer) / 1000.0;
        System.out.println("Array.sort sort reverse ordered array " + totalTime);
        timer = System.currentTimeMillis();
        Arrays.sort(random);
        endTime = System.currentTimeMillis();
        totalTime = (endTime - timer) / 1000.0;
        System.out.println("Array.sort sort randomly ordered array " + totalTime);
    }
}
