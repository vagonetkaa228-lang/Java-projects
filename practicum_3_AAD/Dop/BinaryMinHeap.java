package practicum_3_AAD.Dop;

import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * Собственная бинарная min-heap.
 *
 * <p>Компаратор задаёт приоритет элементов. Он сравнивает пару значений:
 * отрицательное число означает, что первый элемент должен идти раньше
 * второго; положительное — что позже; ноль — что у них одинаковый приоритет.
 * Куча поднимает элемент с наивысшим заданным приоритетом к корню и возвращает
 * его методом {@link #removeMin()}.</p>
 *
 * <p>Например, {@code Comparator.naturalOrder()} ставит меньшие числа раньше
 * больших. Для Дейкстры компаратор сравнивает записи сначала по расстоянию,
 * затем по номеру вершины, чтобы при равных расстояниях получить детерминированный
 * порядок извлечения.</p>
 */
public final class BinaryMinHeap<T> {
    private static final int DEFAULT_CAPACITY = 8;

    /** Правило сравнения, по которому выбирается следующий элемент для извлечения. */
    private final Comparator<? super T> comparator;
    private Object[] elements;
    private int size;

    public BinaryMinHeap(Comparator<? super T> comparator) {
        this(comparator, DEFAULT_CAPACITY);
    }

    public BinaryMinHeap(Comparator<? super T> comparator, int initialCapacity) {
        if (comparator == null) {
            throw new IllegalArgumentException("comparator must not be null");
        }
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("initialCapacity must be non-negative");
        }
        this.comparator = comparator;
        this.elements = new Object[Math.max(1, initialCapacity)];
    }

    public boolean isEmpty() { return size == 0; }
    public int size() { return size; }

    public void add(T value) {
        if (value == null) {
            throw new IllegalArgumentException("heap does not accept null values");
        }
        ensureCapacity();
        elements[size] = value;
        siftUp(size++);
    }

    public T removeMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("heap is empty");
        }

        T minimum = elementAt(0);
        size--;
        if (size > 0) {
            elements[0] = elements[size];
            elements[size] = null;
            siftDown(0);
        } else {
            elements[0] = null;
        }
        return minimum;
    }

    private void siftUp(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (comparator.compare(elementAt(index), elementAt(parent)) >= 0) return;
            swap(index, parent);
            index = parent;
        }
    }

    private void siftDown(int index) {
        while (true) {
            int left = index * 2 + 1;
            if (left >= size) return;
            int right = left + 1;
            int smallerChild = right < size
                    && comparator.compare(elementAt(right), elementAt(left)) < 0
                    ? right : left;
            if (comparator.compare(elementAt(index), elementAt(smallerChild)) <= 0) return;
            swap(index, smallerChild);
            index = smallerChild;
        }
    }

    private void swap(int first, int second) {
        Object value = elements[first];
        elements[first] = elements[second];
        elements[second] = value;
    }

    private void ensureCapacity() {
        if (size == elements.length) {
            int newCapacity = elements.length <= Integer.MAX_VALUE / 2
                    ? elements.length * 2 : Integer.MAX_VALUE;
            if (newCapacity == elements.length) {
                throw new IllegalStateException("heap capacity limit reached");
            }
            Object[] expanded = new Object[newCapacity];
            System.arraycopy(elements, 0, expanded, 0, size);
            elements = expanded;
        }
    }

    @SuppressWarnings("unchecked")
    private T elementAt(int index) {
        return (T) elements[index];
    }
}
