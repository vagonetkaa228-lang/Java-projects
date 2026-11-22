package practicum_3_AAD.Dop;

@FunctionalInterface
public interface EdgeConsumer {

    void accept(int to, long weight);

}
