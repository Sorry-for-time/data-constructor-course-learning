import me.shalling.BFTree;
import me.shalling.SortedArrayBinarySearchUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
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

  @Test
  public void toListAndToStringTest() {
    BFTree<Integer> integerBFTree = new BFTree<>();
    // fill data
    Stream.generate(() -> (int) ((Math.random()) * 100)).limit(20).forEach(integerBFTree::insertNode);
    System.out.println(integerBFTree.toList());
    System.out.println(integerBFTree);
  }

  /**
   * 比较两棵树的大小(自定义比较)
   */
  @Test
  public void compareToTest() {
    BFTree<Integer> integerBFTree = new BFTree<>();
    BFTree<Integer> integerBFTree1 = new BFTree<>();
    // fill data
    Stream.generate(() -> (int) ((Math.random()) * 100)).limit(20).forEach(integerBFTree::insertNode);
    Stream.generate(() -> (int) ((Math.random()) * 100)).limit(20).forEach(integerBFTree1::insertNode);


    // noinspection EqualsWithItself
    System.out.println(integerBFTree.compareTo(integerBFTree)); // 0
    System.out.println(integerBFTree.compareTo(integerBFTree1));
    System.out.println(integerBFTree1.compareTo(integerBFTree));
  }

  /**
   * 将树置空设置
   */
  @Test
  public void clearTreeTest() {
    BFTree<Integer> integerBFTree = new BFTree<>();
    Stream.generate(() -> (int) ((Math.random()) * 100)).limit(20).forEach(integerBFTree::insertNode);
    System.out.println("树的节点数: " + integerBFTree.getLength());
    System.out.println(integerBFTree);
    integerBFTree.clear();
    System.out.println("树的节点数" + integerBFTree.getLength());
    System.out.println(integerBFTree);
  }

  /**
   * 使用递归取得树的高度以及使用使用递归的方式插入新的节点
   */
  @Test
  public void insertNodeByRecursionTest() {
    var bfTree = new BFTree<Integer>();
    var root = bfTree.getRoot();
    final var arr = new int[]{23, 42, 11, 20, 10, 33, 43, 9, 13, 8, 7, 6, 6, 6, 23};
    Arrays.stream(arr).forEachOrdered(e -> bfTree.insertNodeByRecursion(root, e));
    System.out.println(bfTree.toList());
    System.out.printf("树的高度: %d", bfTree.getTreeHeight(bfTree.getRoot(), 0)); // 树的高度: 7
  }

  @Test
  public void preOrderTest() {
    var bfTree = new BFTree<Integer>();
    final var arr = new int[]{23, 42, 11, 20, 10, 33, 43, 9, 13, 8, 7, 6, 6, 6, 23};
    Arrays.stream(arr).forEachOrdered(bfTree::insertNode);
    System.out.println(bfTree.toList());
    System.out.print("先根遍历结果: ");
    bfTree.preOrder(v -> System.out.print(v + "\t"));
    System.out.println();
    System.out.print("中根遍历结果: ");
    bfTree.midOrder(v -> System.out.print(v + "\t"));
  }

  @Test
  public void deleteTest() {
    var bfTree = new BFTree<Integer>();
    final var arr = new int[]{23, 42, 11, 20, 10, 33, 43, 9, 13, 8, 7, 6};
    Arrays.stream(arr).forEachOrdered(bfTree::insertNode);
    System.out.println("initial" + bfTree.toList());

    bfTree.deleteNode(23);
    System.out.println("delete 23" + bfTree.toList());

    bfTree.deleteNode(23);
    System.out.println("delete 23" + bfTree.toList());

    bfTree.deleteNode(42);
    System.out.println("delete 42" + bfTree.toList());

    bfTree.deleteNode(20);
    System.out.println("delete 20" + bfTree.toList());

    bfTree.deleteNode(13);
    System.out.println("delete 13" + bfTree.toList());

    bfTree.deleteNode(10);
    System.out.println("delete 10" + bfTree.toList());
  }

  /**
   * 通过二分查找找到在树中数匹配的节点, 并返回其节点引用
   */
  @Test
  public void findNodeByValueTest() {
    final var bftTree = new BFTree<Integer>(); /* 创建二叉树 */
    int[] ints = {12, 123, 231, 12, 32123123, 31, 12, 31};
    Arrays.stream(ints).forEachOrdered(bftTree::insertNode);
    System.out.println("排序情况: " + bftTree.toList()); /* 构建情况 */
    /* 查找一个存在的节点 */
    var res1 = bftTree.findNodeByValue(231).getDataDomain();
    System.out.println(res1);

    /* 查找一个不存在的节点 */
    var res2 = bftTree.findNodeByValue(1312314123);
    System.out.println(res2);

    Assertions.assertEquals(231, res1);
    Assertions.assertNull(res2);
  }

  /**
   * 排序数组二分查找方式
   */
  @Test
  public void arrayBinarySearchTest() {
    final var bftTree = new BFTree<Integer>(); /* 创建二叉树 */
    int[] ints = {12, 123, 231, 12, 32123123, 31, 12, 31};
    Arrays.stream(ints).forEachOrdered(bftTree::insertNode);
    var integerArray = new Integer[bftTree.getLength()];
    AtomicInteger i = new AtomicInteger();
    // 填充数组
    bftTree.forEach((e) -> integerArray[i.getAndIncrement()] = e);
    System.out.println("排序情况: " + Arrays.toString(integerArray)); // 排序情况: [12, 31, 123, 231, 32123123]

    int location = SortedArrayBinarySearchUtil.binarySearch(integerArray, 12);
    System.out.println("12, 所在的位置: " + location); // 0

    int location1 = SortedArrayBinarySearchUtil.binarySearch(integerArray, 32123123);
    System.out.println("32123123, 所在的位置: " + location1); // 4

    int location2 = SortedArrayBinarySearchUtil.binarySearch(integerArray, 123);
    System.out.println("123, 所在的位置: " + location2); // 2

    // 查找一个不存在的元素
    int location3 = SortedArrayBinarySearchUtil.binarySearch(integerArray, 223);
    System.out.println("223, 所在的位置: " + location3); // -2
  }
}
