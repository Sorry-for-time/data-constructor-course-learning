import me.shalling.ArraySortUtil;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * 排序方法测试
 *
 * @author Shalling
 * @version v0.01
 * @see <a href="https://github.com/Sorry-for-time">follow me on github</a>
 * @since 2022/12/4 23:37
 */
public class ArraySortUtilTest {
  /**
   * 模板测试数组
   */
  public static final Integer[] templateArr = {12, 43, 12, 23, 14, 12, 100, 17};

  @Test
  public void bubbleSortTest() {
    ArraySortUtil.bubbleSort(templateArr);
    System.out.println(Arrays.toString(templateArr));
  }

  @Test
  public void quickSortTest() {
    ArraySortUtil.quickSort(templateArr);
    System.out.println(Arrays.toString(templateArr));
  }

  @Test
  public void heapSortTest() {
    ArraySortUtil.heapSort(templateArr);
    System.out.println(Arrays.toString(templateArr));
  }

  @Test
  public void bucketSortTest() {
    ArraySortUtil.bucketSort(templateArr);
    System.out.println(Arrays.toString(templateArr));
  }
}
