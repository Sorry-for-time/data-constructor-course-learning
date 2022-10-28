package me.shalling;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Shalling
 * @version v0.01
 * @description <h1>树节点定义</h1>
 * @package {me.shalling}
 * @see <a href="https://github.com/Sorry-for-time">follow me on github</a>
 * @since 2022/10/28 23:31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TreeNode<T> {
  /**
   * 节点的数据域定义
   */
  private T dataDomain;

  /**
   * 节点的左子树节点定义
   */
  private TreeNode<T> leftChild;

  /**
   * 节点的右子树节点定义
   */
  private TreeNode<T> rightChild;
}
