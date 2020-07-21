// EIDER HELPER GENERATED BY EIDER AT 2020-07-21T16:25:46.064071Z. DO NOT MODIFY
package io.eider.util;

import java.lang.FunctionalInterface;

@FunctionalInterface
public interface IndexUpdateConsumer<T> {
  /**
   * Accepts an index update for the given offset and type<T> value
   */
  void accept(int offset, T t);
}
