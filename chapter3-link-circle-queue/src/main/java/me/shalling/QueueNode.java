package me.shalling;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
public class QueueNode<T> implements Serializable {
  @Serial
  private static final long serialVersionUID = 1492079296340693940L;

  /**
   * @description 节点数据域定义
   */
  @NonNull
  private T dataDomain;

  /**
   * @description 指向下一个节点的记录
   */
  private QueueNode<T> next;
}
