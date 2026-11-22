package practicum_3_AAD.Dop;




/** Структура непересекающихся множеств со сжатием путей и объединением по рангу. */
public final class DisjointSetUnion {
    private final int[] parent;
    private final byte[] rank;
    private int components;

    public DisjointSetUnion(int size) {
        if (size < 0) throw new IllegalArgumentException("size must be non-negative");
        parent = new int[size];
        rank = new byte[size];
        components = size;
        for (int i = 0; i < size; i++) parent[i] = i;
    }

    public int find(int value) {
        check(value);
        if (parent[value] != value) parent[value] = find(parent[value]);
        return parent[value];
    }

    /** Объединяет компоненты и возвращает true, если до этого они были разными. */
    public boolean union(int first, int second) {
        int rootFirst = find(first);
        int rootSecond = find(second);
        if (rootFirst == rootSecond) return false;
        if (rank[rootFirst] < rank[rootSecond]) {
            parent[rootFirst] = rootSecond;
        } else if (rank[rootFirst] > rank[rootSecond]) {
            parent[rootSecond] = rootFirst;
        } else {
            parent[rootSecond] = rootFirst;
            rank[rootFirst]++;
        }
        components--;
        return true;
    }

    public boolean connected(int first, int second) { return find(first) == find(second); }
    public int componentCount() { return components; }

    private void check(int value) {
        if (value < 0 || value >= parent.length) {
            throw new IndexOutOfBoundsException("value out of range: " + value);
        }
    }
}
