package me.shalling;

import lombok.Getter;
import lombok.NonNull;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;
import java.util.function.Consumer;

/**
 * @author Shalling
 * @version v0.01
 * @description <h1>简易版二叉树实现</h1>
 * @package {me.shalling}
 * @see <a href="https://github.com/Sorry-for-time">follow me on github</a>
 * @since 2022/10/28 23:36
 */
public class BFTree<T extends Comparable<T>> implements Serializable, Iterable<T>, Comparable<BFTree<T>> {
  @Serial
  private static final long serialVersionUID = -4152677334247907325L;

  /**
   * 树根节点定义
   */
  @Getter
  private TreeNode<T> root;

  /**
   * 树节点数记录
   */
  @Getter
  private int length;

  public BFTree() {
  }

  public BFTree(TreeNode<T> root) {
    this.root = root;
  }

  /**
   * @param data 插入树中的新数据
   */
  public void insertNode(final T data) {
    // 如果为空树的情况, 那么直接将第一个节点设置为树根
    if (null == root) {
      this.root = new TreeNode<>(data, null, null);
      ++this.length;
    } else {
      TreeNode<T> current = root;
      while (true) {
        if (data.compareTo(current.getDataDomain()) == 0) {
          break;
        } else if (data.compareTo(current.getDataDomain()) < 0) {
          if (null == current.getLeftChild()) {
            current.setLeftChild(new TreeNode<>(data, null, null));
            this.length++;
            break;
          } else {
            current = current.getLeftChild();
          }
        } else {
          if (null == current.getRightChild()) {
            current.setRightChild(new TreeNode<>(data, null, null));
            this.length++;
            break;
          } else {
            current = current.getRightChild();
          }
        }
      }
    }
  }

  /**
   * 通过递归的方式插入新节点
   *
   * @param root 起始根节点
   * @param data 新的元素
   */
  public void insertNodeByRecursion(TreeNode<T> root, final T data) {
    // 如果根节点为空, 那么线进行赋值
    if (this.root == null) {
      this.root = new TreeNode<>(data);
      ++this.length;
      return;
    }

    // 更新 root 引用
    if (root == null) {
      root = this.root;
    }

    if (root.getDataDomain().compareTo(data) > 0) {
      if (root.getLeftChild() == null) {
        root.setLeftChild(new TreeNode<>(data));
        ++this.length;
      } else {
        insertNodeByRecursion(root.getLeftChild(), data);
      }
    } else if (root.getDataDomain().compareTo(data) < 0) {
      if (root.getRightChild() == null) {
        root.setRightChild(new TreeNode<>(data));
        ++this.length;
      } else {
        insertNodeByRecursion(root.getRightChild(), data);
      }
    }
  }

  /**
   * 获取树的高度
   *
   * @param root        递归变量起点
   * @param startHeight 树高起始值
   * @return 树高
   */
  public int getTreeHeight(final TreeNode<T> root, final int startHeight) {
    if (root == null) {
      return startHeight;
    }
    int leftHeight = getTreeHeight(root.getLeftChild(), startHeight + 1);
    int rightHeight = getTreeHeight(root.getRightChild(), startHeight + 1);
    return Math.max(leftHeight, rightHeight);
  }

  /**
   * 置空树
   */
  public void clear() {
    this.root = null;
    this.length = 0;
  }

  /**
   * 当前树是否为空
   *
   * @return 如果树为空, 返回 true, 否则返回 false
   */
  public boolean isEmpty() {
    return null == this.root;
  }

  /**
   * 通过递归执行中序遍历
   *
   * @param start    遍历起点
   * @param consumer 数据执行器
   */
  private void dorTraverse(TreeNode<T> start, Consumer<? super T> consumer) {
    // 如果节点不为空, 那么就是递归, 直到结束
    if (null != start) {
      dorTraverse(start.getLeftChild(), consumer);
      consumer.accept(start.getDataDomain());
      dorTraverse(start.getRightChild(), consumer);
    }
  }

  /**
   * 通过非递归的方式进行先序遍历 ---> 根左右
   *
   * @param action 执行器
   */
  public void preOrder(Consumer<T> action) {
    final var stack = new Stack<TreeNode<T>>(); /* 记录节点遍历到的位置 */
    var leftTree = this.root; /* 先取得树的根节点, 一开始遍历从根节点出发 */
    // 只要节点不为空或者纪录栈不为空, 那么就重复操作
    while (leftTree != null || !stack.empty()) {
      // 如果左树不为空, 那么就一路到底往左节点前进, 进行深度遍历
      while (leftTree != null) {
        action.accept(leftTree.getDataDomain()); /* 如果左树不为空, 那么就将其数据域传递给消费者函数 */
        stack.push(leftTree); /* 将当前节点推入到记录栈中, 因为后续要从中取出右节点进行遍历 */
        leftTree = leftTree.getLeftChild(); /* 更新左树的引用指向为下一个节点 */
      }

      // 将左树引用指向栈顶元素的右子树(顺便弹出栈顶元素), 进行下一轮遍历
      leftTree = stack.pop().getRightChild();
    }
  }

  /**
   * 通过非递归的方式实现中序遍历 --> 左根右
   *
   * @param action 执行器
   */
  public void midOrder(Consumer<T> action) {
    var tree = this.root;
    var stack = new Stack<TreeNode<T>>();
    while (tree != null || !stack.isEmpty()) {
      while (tree != null) {
        stack.push(tree); /* 将节点推到栈中 */
        tree = tree.getLeftChild(); /* 更新元素指向下一个节点, 因为时中序遍历, 所以一样指向左边的节点 */
      }
      /* 进行回溯, 遇到 null 就继续返回上一层, 因为外层循环判断为真的条件也包括只要 stack 不为空就行
       * 如果回溯到的节点不为空, 那么就表示其存在子树, 继续重复内层的 while 循环推到栈中
       * */
      tree = stack.peek().getRightChild();
      action.accept(stack.pop().getDataDomain()); /* 将栈顶元素的数据用域的值传递给执行器函数 */
    }
  }

  /**
   * 通过非递归遍历实现中序遍历
   *
   * @param action 执行器
   */
  public void midOrderWithRaw(@NonNull Consumer<TreeNode<T>> action) {
    var tree = this.root;
    var stack = new Stack<TreeNode<T>>();
    while (tree != null || !stack.isEmpty()) {
      while (tree != null) {
        stack.push(tree); /* 将节点推到栈中 */
        tree = tree.getLeftChild(); /* 更新元素指向下一个节点, 因为时中序遍历, 所以一样指向左边的节点 */
      }
      /* 进行回溯, 遇到 null 就继续返回上一层, 因为外层循环判断为真的条件也包括只要 stack 不为空就行
       * 如果回溯到的节点不为空, 那么就表示其存在子树, 继续重复内层的 while 循环推到栈中
       * */
      tree = stack.peek().getRightChild();
      action.accept(stack.pop()); /* 将栈顶元素的数据用域的值传递给执行器函数 */
    }
  }

  /**
   * 找出指定值的父节点, 如果恰好是根节点, 那么就返回父节点
   *
   * @param infer 参考值(要求实现了 comparable) 接口
   * @return 指定值的父节点, 如果不存在, 就返回 null
   */
  private TreeNode<T> getNodeParentOrRootNode(@NonNull T infer) {
    if (this.isEmpty()) {
      return null;
    }
    var current = this.root;
    while (current != null) {
      if (current.getDataDomain().compareTo(infer) == 0) {
        return current;
      } else {
        if (current.getDataDomain().compareTo(infer) > 0) {
          if (current.getLeftChild() != null) {
            if (current.getLeftChild().getDataDomain().compareTo(infer) == 0) {
              return current;
            } else {
              current = current.getLeftChild();
            }
          } else {
            break;
          }
        }
        // 节点在右子树的情况
        if (current.getDataDomain().compareTo(infer) < 0) {
          if (current.getRightChild() != null) {
            if (current.getRightChild().getDataDomain().compareTo(infer) == 0) {
              return current;
            } else {
              current = current.getRightChild();
            }
          } else {
            break;
          }
        }
      }
    }
    return null;
  }

  /**
   * 根据指定的参考值返回值域匹配的指定节点的引用
   *
   * @param infer 参考值要求实现了 Comparable 接口
   * @return 树中匹配值的节点, 否则返回 null
   */
  public TreeNode<T> findNodeByValue(@NonNull T infer) {
    if (this.root == null) {
      return null;
    }
    var current = this.root;
    while (current != null) {
      /* 树中节点与参与值进行的大小比较结果 */
      var compareValue = current.getDataDomain().compareTo(infer);
      if (compareValue == 0) { /* 节点值域与 infer 相等的情况 */
        break;
      } else if (compareValue > 0) { /* 节点值域大于 infer 的情况, 说明节点可能在左子树 */
        current = current.getLeftChild(); /* 切换到左子树 */
      } else { /* 节点值域小于 infer 的情况, 说明节点可能在右子树 */
        current = current.getRightChild(); /* 切换到右子树 */
      }
    }
    return current;
  }

  /**
   * 根据指定的值域删除树中已经存在的节点
   *
   * @param infer 参考值(要求实现了 Comparable 接口)
   */
  public void deleteNode(@NonNull T infer) {
    // 要删除的节点不存在的情况
    var deleteNodeParent = this.getNodeParentOrRootNode(infer);
    if (deleteNodeParent == null) {
      return;
    }

    // 要删除的节点为根节点情况
    if (deleteNodeParent == this.root) {
      var endLeftLeaf = this.root.getLeftChild();
      while (endLeftLeaf.getRightChild() != null) {
        endLeftLeaf = endLeftLeaf.getRightChild();
      }
      endLeftLeaf.setRightChild(root.getRightChild()); /* 将根节点右子树移动到根节点左子树的右叶子节点上 */
      this.root = this.root.getLeftChild(); /* 更新根节点 */
      --this.length; /* 更新记录 */
      return;
    }

    // 其它的情况(在左右子树内)
    if (deleteNodeParent.getLeftChild().getDataDomain().compareTo(infer) == 0) {
      var willDelete = deleteNodeParent.getLeftChild(); /* 需要进行删除的节点 */

      // 如果要删除的节点为一个叶子节点的情况
      if (willDelete.getLeftChild() == null && willDelete.getRightChild() == null) {
        deleteNodeParent.setLeftChild(null);
        --this.length;
        return;
      }

      var joinPoint = willDelete.getLeftChild(); /* 要删除节点的左子树 */
      var rightChildTree = willDelete.getRightChild(); /* 要删除节点的右子树 */
      // 如果要删除的节点的左节点不为空
      if (joinPoint != null) {
        // 通过循环找到底部靠右侧的叶子节点
        while (joinPoint.getRightChild() != null) {
          joinPoint = joinPoint.getRightChild();
        }
        joinPoint.setRightChild(rightChildTree);
        // 更新被删除的节点的父节点指向其删除节点的左孩子
        deleteNodeParent.setLeftChild(willDelete.getLeftChild());
      } else {
        // 如果待删除节点的左子树为空, 那么直接将其父节点的左孩子更新为其删除节点的右孩子(被删除的节点的左子树的右子树的值一定比被删除节点父节点的右子树来得小)
        deleteNodeParent.setLeftChild(rightChildTree);
      }

    }
    // 删除节点为父节点右子树的情况
    else {
      var it = deleteNodeParent.getRightChild();
      // 如果要删除的节点为一个叶子节点的情况
      if (it.getLeftChild() == null && it.getRightChild() == null) {
        deleteNodeParent.setRightChild(null);
        --this.length;
        return;
      }

      var joinPoint = it.getLeftChild(); /* 要删除节点的左子树 */
      var rightChildTree = it.getRightChild(); /* 要删除节点的右子树 */
      // 如果要删除的节点的左节点不为空
      if (joinPoint != null) {
        while (joinPoint.getRightChild() != null) {
          joinPoint = joinPoint.getRightChild();
        }
        joinPoint.setRightChild(rightChildTree);
        deleteNodeParent.setRightChild(rightChildTree);
      } else {
        deleteNodeParent.setRightChild(rightChildTree);
      }
    }
    // 更新记录域
    --this.length;
  }

  /**
   * 将树中的节点存储到列表上并进行返回, 这个列表的值为只读, 不允许进行修改
   *
   * @return 树中节点升序排列的只读列表
   */
  public List<T> toList() {
    ArrayList<T> tArrayList = new ArrayList<>();
    this.midOrder(tArrayList::add);
    return Collections.unmodifiableList(tArrayList);
  }

  @Override
  public Iterator<T> iterator() {
    return new Iterator<>() {
      private final Iterator<T> iterator = toList().iterator();

      @Override
      public boolean hasNext() {
        return iterator.hasNext();
      }

      @Override
      public T next() {
        return iterator.next();
      }
    };
  }

  @Override
  public void forEach(Consumer<? super T> action) {
    TreeNode<T> start = this.root;
    dorTraverse(start, action);
  }

  @Override
  public String toString() {
    return "BFTree{" +
      "root=" + root +
      ", length=" + length +
      '}';
  }

  @Override
  public int compareTo(BFTree<T> o) {
    // 如果指向了相同的地址, 那么就是相同的树
    if (o == this) {
      return 0;
    }
    // 先判断子节点个数
    if (this.length > o.length) {
      return this.length - o.length;
    } else if (this.length < o.length) {
      return this.length - o.length;
    } else {
      // 两棵树节点数相同的情况, 通过比较升序节点的每个值来判断
      Object[] oNodes = o.toList().toArray();
      Object[] origin = this.toList().toArray();
      for (int i = 0; i < origin.length; i++) {
        @SuppressWarnings("unchecked")
        var comp = (T) origin[i];
        @SuppressWarnings("unchecked")
        var comp1 = (T) oNodes[i];
        // 每个节点比较大小, 只要有一个节点的大小和另一个节点不相等, 那么就立即返回结果
        if (comp.compareTo(comp1) > 0) {
          return 1;
        } else if (comp.compareTo(comp1) < 0) {
          return -1;
        }
      }
      // 如果每个节点的大小比较均相等, 那么就认为是大小相等的树
      return 0;
    }
  }
}
