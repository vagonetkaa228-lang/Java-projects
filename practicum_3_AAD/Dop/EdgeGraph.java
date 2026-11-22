package practicum_3_AAD.Dop;

import java.util.function.Consumer;

public interface EdgeGraph extends Graph {

    /** Обходит каждое добавленное ребро один раз (в том числе для неориентированного графа). */
    void forEachEdge(Consumer<WeightedEdge> consumer);

}
