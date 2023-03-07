package me.shalling;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @author Shalling
 * @version v0.01
 * @see <a href="https://github.com/Sorry-for-time">follow me on github</a>
 * @since 2023/3/6 19:46
 */
public class FormulaParser {
  public static boolean isOperator(String str) {
    return switch (str) {
      case "+", "-", "*", "/" -> true;
      default -> false;
    };
  }

  public static List<String> getFormulaSuffixTokenList(String mathText, String separator) {
    var operatorStack = new Stack<String>();
    var parsedTokenList = new ArrayList<String>();
    Arrays.stream(mathText.split(separator))
      .map(String::strip)
      .filter(str -> !"".equals(str))
      .forEachOrdered(
        token -> {
          switch (token) {
            case "+", "-" -> {
              if (operatorStack.isEmpty()) {
                operatorStack.add(token);
              } else {
                while (!operatorStack.isEmpty() && !"(".equals(operatorStack.peek())) {
                  parsedTokenList.add(operatorStack.pop());
                }
                operatorStack.push(token);
              }
            }
            case "*", "/" -> {
              if (operatorStack.isEmpty()) {
                operatorStack.push(token);
              } else {
                while (!operatorStack.isEmpty() &&
                  ("*".equals(operatorStack.peek()) || "/".equals(operatorStack.peek()))
                ) {
                  parsedTokenList.add(operatorStack.pop());
                }
                operatorStack.push(token);
              }
            }
            case "(" -> operatorStack.push(token);
            case ")" -> {
              while (!operatorStack.isEmpty() && !"(".equals(operatorStack.peek())) {
                parsedTokenList.add(operatorStack.pop());
              }
              operatorStack.pop();
            }
            default -> parsedTokenList.add(token);
          }
        }
      );
    while (!operatorStack.isEmpty()) {
      parsedTokenList.add(operatorStack.pop());
    }

    return parsedTokenList;
  }

  /**
   * reduce the parsed suffix token list to get the formula calculate value
   *
   * @param tokens the formula parsed tokens
   * @return the parsed token reduce result
   */
  public static int reduceTokenList(List<String> tokens) {
    Stack<Integer> reduceStack = new Stack<>();
    tokens.forEach(s -> {
      if (!isOperator(s)) {
        reduceStack.push(Integer.parseInt(s));
      } else {
        Integer topFrame = reduceStack.peek();
        reduceStack.pop();
        Integer lowerFrame = reduceStack.pop();
        int newVal = switch (s) {
          case "+" -> lowerFrame + topFrame;
          case "-" -> lowerFrame - topFrame;
          case "*" -> lowerFrame * topFrame;
          case "/" -> lowerFrame / topFrame;
          default -> throw new RuntimeException("illegal operator: " + s);
        };
        reduceStack.push(newVal);
      }
    });
    return reduceStack.peek();
  }
}
