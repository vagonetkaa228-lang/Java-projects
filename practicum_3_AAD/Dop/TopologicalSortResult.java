package practicum_3_AAD.Dop;


import java.util.List;

/** Топологический порядок, построенный алгоритмом Кана, и признак полноты порядка. */
public record TopologicalSortResult(List<Integer> order, boolean acyclic) {
    public TopologicalSortResult {
        order = List.copyOf(order);
    }
}
