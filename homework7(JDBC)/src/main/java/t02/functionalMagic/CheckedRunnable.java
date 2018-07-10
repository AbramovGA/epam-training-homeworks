package t02.functionalMagic;

import static t02.functionalMagic.Exceptional.sneakyThrow;

@FunctionalInterface
public interface CheckedRunnable extends io.vavr.CheckedRunnable {

    static CheckedRunnable narrow(CheckedRunnable checkedRunnable) {
        return checkedRunnable;
    }

    /**
     * Returns an unchecked function that will <em>sneaky throw</em> if an exceptions occurs when applying the function.
     *
     * @return a new Function0 that throws a {@code Throwable}.
     */
    default Runnable unchecked() {
        return () -> {
            try {
                run();
            } catch (Throwable t) {
                sneakyThrow(t);
            }
        };
    }
}
