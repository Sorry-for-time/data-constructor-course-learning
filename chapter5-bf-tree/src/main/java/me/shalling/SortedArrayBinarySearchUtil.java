package me.shalling;

import lombok.NonNull;

/**
 * 已排序数组二分查找工具类
 *
 * @author Shalling
 * @version v0.01
 * @see <a href="https://github.com/Sorry-for-time">follow me on github</a>
 * @since 2022/12/4 16:05
 */
public final class SortedArrayBinarySearchUtil {
  /**
   * 通过二分查找找出一个指定元素在已排序数组中的下标位置
   * 时间复杂度: O(Log2N)
   *
   * @param bucket 排序数组
   * @param infer  进行查找的元素
   * @param <T>    数组元素类型
   * @return 元素在数组中的下标, 如果未找到, 那么返回 -1;
   */
  public static <T extends Comparable<T>> int binarySearch(T[] bucket, @NonNull T infer) {
    int low = 0;
    int high = bucket.length - 1;
    while (low <= high) {
      int mid = (low + high) / 2;
      // 如果索引位置刚好等于 infer, 那么直接返回
      if (bucket[mid].compareTo(infer) == 0) {
        return mid;
      }
      // mid 位置比查找值大的情况
      if (bucket[mid].compareTo(infer) > 0) {
        high = mid - 1; // 匹配值小于 mid 所在位置的值, 所以可将 high 设置为 (mid-1) 而不是 mid, 即bucket[mid] >= infer
      } else if (bucket[mid].compareTo(infer) < 0) {
        low = mid + 1;
      }
    }
    return -1;
  }
}
