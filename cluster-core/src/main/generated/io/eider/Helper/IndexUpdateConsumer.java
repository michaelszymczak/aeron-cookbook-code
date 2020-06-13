// EIDER HELPER GENERATED BY EIDER AT 2020-06-13T19:29:15.277380Z. DO NOT MODIFY
package io.eider.Helper;

import java.lang.FunctionalInterface;

@FunctionalInterface
public interface IndexUpdateConsumer<T> {
  /**
   * Accepts an index update for the given offset and type<T> value
   */
  void accept(int offset, T t);
}
