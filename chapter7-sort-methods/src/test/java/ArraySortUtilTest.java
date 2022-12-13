import me.shalling.ArraySortUtil;
import me.shalling.entity.Student;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static me.shalling.ArraySortUtil.bubbleSort;

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
    bubbleSort(templateArr);
    System.out.println(Arrays.toString(templateArr));
  }

  @Test
  public void sortStudentsScore() {
    final double[] scores = {56, 67, 90, 68, 88, 73, 73};
    final String[] names = {"Wayne", "Fox", "Jane", "Bruce", "Scott", "Lao", "Black"};
    Student[] students = new Student[scores.length];
    for (int i = 0; i < students.length; i++) {
      students[i] = new Student(scores[i], names[i]);
    }
    System.out.println("原始数组: ");
    for (Student student : students) {
      System.out.println("学生姓名 " + student.getName() + " 成绩" + student.getScore());
    }

    // 按照成绩升序排序(冒泡排序)
    bubbleSort(students, (s1, s2) -> -Double.compare(s1.getScore(), s2.getScore()));
    int location = 1; // 记录排名
    var prevStudent = students[0];
    System.out.println("排序数组: ");
    for (Student e : students) {
      if (e.compareTo(prevStudent) != 0) {
        location++;
      }
      prevStudent = e; // 更新记录
      System.out.println("排名: " + location + "学生姓名 " + e.getName() + " 成绩" + e.getScore());
    }
  }

  @Test
  public void quickSortTest() {
    ArraySortUtil.quickSort(templateArr);
    System.out.println(Arrays.toString(templateArr));
  }
}
