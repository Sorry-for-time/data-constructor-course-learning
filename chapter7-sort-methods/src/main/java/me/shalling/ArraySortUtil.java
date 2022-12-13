package me.shalling;

import java.util.Comparator;

/**
 * 排序相关方法的实现类
 *
 * @author Shalling
 * @version v0.01
 * @see <a href="https://github.com/Sorry-for-time">follow me on github</a>
 * @since 2022/12/4 23:25
 */
public final class ArraySortUtil {
  /**
   * 冒泡排序
   *
   * @param bucket 待排序数组
   * @param <T>    实现了 Comparable 接口的数据类型
   */
  public static <T extends Comparable<T>> void bubbleSort(T[] bucket) {
    for (int i = 0; i <= bucket.length - 1; ++i) {
      for (int j = i + 1; j <= bucket.length - 1; j++) {
        if (bucket[i].compareTo(bucket[j]) > 0) {
          var tmp = bucket[i];
          bucket[i] = bucket[j];
          bucket[j] = tmp;
        }
      }
    }
  }

  /**
   * 冒泡排序
   *
   * @param bucket           待排序数组
   * @param customComparator 自定义排序规则
   * @param <T>              类型
   */
  public static <T extends Comparable<T>> void bubbleSort(T[] bucket, Comparator<T> customComparator) {
    for (int i = 0; i <= bucket.length - 1; ++i) {
      for (int j = i + 1; j <= bucket.length - 1; j++) {
        if (customComparator.compare(bucket[i], bucket[j]) > 0) {
          var tmp = bucket[i];
          bucket[i] = bucket[j];
          bucket[j] = tmp;
        }
      }
    }
  }

  /**
   * 快速排序
   *
   * @param bucket 待排序数组
   * @param <T>    实现了 Comparable 接口的数据类型
   */
  public static <T extends Comparable<T>> void quickSort(T[] bucket) {

  }

  /**
   * 堆排序
   *
   * @param bucket 待排序数组
   * @param <T>    实现了 Comparable 接口的数据类型
   */
  public static <T extends Comparable<T>> void heapSort(T[] bucket) {

  }

  /**
   * 桶排序
   *
   * @param bucket 待排序数组
   * @param <T>    实现了 Comparable 接口的数据类型
   */
  public static <T extends Comparable<T>> void bucketSort(T[] bucket) {

  }
}
