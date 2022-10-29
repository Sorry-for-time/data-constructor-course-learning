import me.shalling.BFTree;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Stream;

/**
 * @author Shalling
 * @version v0.01
 * @description <h1>自定义二叉树测试</h1>
 * @package {PACKAGE_NAME}
 * @see <a href="https://github.com/Sorry-for-time">follow me on github</a>
 * @since 2022/10/28 23:46
 */
public class BFTreeTest {
  /**
   * 二叉树创建, 插入新节点测试
   */
  @Test
  public void insertNodeTest() {
    BFTree<Integer> integerBFTree = new BFTree<>();
    List<Integer> list = Stream.generate(() -> (int) ((Math.random()) * 100)).limit(20).toList();
    list.forEach(e -> System.out.print(e + "\t"));
    list.forEach(integerBFTree::insertNode);
    System.out.println();
    System.out.println();
    System.out.println(integerBFTree);
  }

  @Test
  public void traversTest() {
    BFTree<Integer> integerBFTree = new BFTree<>();
    // fill data
    Stream.generate(() -> (int) ((Math.random()) * 100)).limit(20).forEach(integerBFTree::insertNode);
    // use foreach
    integerBFTree.forEach(e -> System.out.print(e + "\t"));
    System.out.println();
    // use iterator
    for (Integer integer : integerBFTree) {
      System.out.print(integer + "\t");
    }
  }
}
