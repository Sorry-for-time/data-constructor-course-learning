package me.shalling.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 定义学生成绩体
 *
 * @author Shalling
 * @version v0.01
 * @see <a href="https://github.com/Sorry-for-time">follow me on github</a>
 * @since 2022/12/5 15:39
 */
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Student implements Comparable<Student> {
  /**
   * 学生成绩
   */
  private Double score;

  /**
   * 学生姓名
   */
  private String name;

  @Override
  public int compareTo(Student o) {
    // 定义比较方式, 分数从低到高
    return this.score.compareTo(o.score);
  }
}
