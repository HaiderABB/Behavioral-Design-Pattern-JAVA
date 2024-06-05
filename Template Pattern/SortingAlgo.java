import java.util.Arrays;

abstract class SortAlgorithm {

  // Template method
  public void sort(int[] array) {
    int n = array.length;
    for (int i = 0; i < n - 1; i++) {
      for (int j = 0; j < n - i - 1; j++) {
        if (compare(array[j], array[j + 1])) {
          swap(array, j, j + 1);
        }
      }
    }
  }

  protected abstract boolean compare(int a, int b);

  protected abstract void swap(int[] array, int idx1, int idx2);
}

class BubbleSort extends SortAlgorithm {

  @Override
  protected boolean compare(int a, int b) {
    return a > b;
  }

  @Override
  protected void swap(int[] array, int idx1, int idx2) {
    int temp = array[idx1];
    array[idx1] = array[idx2];
    array[idx2] = temp;
  }
}

class SelectionSort extends SortAlgorithm {

  @Override
  protected boolean compare(int a, int b) {
    return a < b; // Compare in ascending order
  }

  @Override
  protected void swap(int[] array, int idx1, int idx2) {
    int temp = array[idx1];
    array[idx1] = array[idx2];
    array[idx2] = temp;
  }
}

public class SortingAlgo {

  public static void main(String[] args) {
    int[] array1 = { 10, 5, 9, 25, 14 };
    int[] array2 = { 87, 31, 90, 46, 28 };

    SortAlgorithm bubbleSort = new BubbleSort();
    bubbleSort.sort(array1);
    System.out.println("After Bubble Sorting: " + Arrays.toString(array1));

    SortAlgorithm selectionSort = new SelectionSort();
    selectionSort.sort(array2);
    System.out.println("After Selection Sorting: " + Arrays.toString(array2));
  }
}