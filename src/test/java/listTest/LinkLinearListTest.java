package listTest;

import me.shalling.linkLinearList.LinkLinearList;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.function.Consumer;

/**
 * @author Shalling
 * @version v0.01
 * @description <h1>write comment here</h1>
 * @package {listTest}
 * @see <a href="https://github.com/Sorry-for-time">follow me on github</a>
 * @since 2022/9/24 18:54
 */
public class LinkLinearListTest {
  @Test
  public void linkLinearListTest() {
    LinkLinearList<String> stringLinkLinearList = new LinkLinearList<>(20);
    System.out.println(stringLinkLinearList.isEmpty() ? "当前列表为空" : "当前列表不为空"); // 当前列表为空

    // 填充元素
    for (int i = 1; i <= 10; ++i) {
      stringLinkLinearList.insertToList(String.valueOf(i), i);
    }
    System.out.println("初始填充元素: " + Arrays.toString(stringLinkLinearList.getFactItems()));

    // 更新节点的数据域
    stringLinkLinearList.updateItemByIndex("223", 1);
    stringLinkLinearList.updateItemByIndex("Wayne", 2);

    // 删除指定节位置的节点元素并打印
    System.out.println("被删除的元素: " + stringLinkLinearList.deleteItem(8)); // 8
    System.out.println("被删除的元素: " + stringLinkLinearList.deleteItem()); // 直接删除末尾元素: 10, 此时列表里少了 10 和 8

    System.out.println("=".repeat(23));
    stringLinkLinearList.insertToList("LIVE FREE OR DIE!"); // 直接在末尾插入元素
    stringLinkLinearList.insertToList("WE WANT"); // 直接在末尾插入元素
    // 调用顺序表的遍历方法->每输出 5个 进行换行
    stringLinkLinearList.forEach(new Consumer<>() {
      private static int wrapFlag = 1;

      @Override
      public void accept(String s) {
        System.out.print("| " + s + "\t");
        // 换行输出
        if (wrapFlag % 5 == 0) {
          System.out.print(" |");
          System.out.println();
        }
        wrapFlag++;
      }
    });

    // 添加迭代器测试(调用了 iterator)
    for (String s : stringLinkLinearList) {
      System.out.print("|" + s + "\t");
    }

    System.out.println("\n" + "=".repeat(23));
    // 取得顺序表中的实际元素组成的数组
    System.out.println(Arrays.toString(stringLinkLinearList.getFactItems()));
  }
}
