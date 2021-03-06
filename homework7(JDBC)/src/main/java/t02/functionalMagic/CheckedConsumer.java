package t02.functionalMagic;

import lombok.SneakyThrows;

import java.util.function.Consumer;

import static t02.functionalMagic.Exceptional.sneakyThrow;


@FunctionalInterface
public interface CheckedConsumer<T> extends io.vavr.CheckedConsumer<T> {

    static <T> CheckedConsumer<T> narrow(CheckedConsumer<T> consumer) {
        return consumer;
    }

    @SneakyThrows
    static <T> T doWith(T obj, CheckedConsumer<T> consumer) {
        consumer.accept(obj);
        return obj;
    }

    @SneakyThrows
    static <T extends AutoCloseable> void doWithAndCleanup(T obj,
                                                           CheckedConsumer<T> cns) {
        try (obj) {
            cns.accept(obj);
        }
    }

    /**
     * Returns an unchecked consumer that will <em>sneaky throw</em> if an exceptions occurs when applying the function.
     *
     * @return a new Consumer<T> that throws a {@code Throwable}.
     */
    default Consumer<T> unchecked() {
        return value -> {
            try {
                accept(value);
            } catch (Throwable t) {
                sneakyThrow(t);
            }
        };
    }
}
