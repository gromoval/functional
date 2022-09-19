package TryMonadIntermediate;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Represents an operation that may potentially fail with an exception
 */
@FunctionalInterface
interface ThrowableOperation<T> {
    T execute() throws Throwable;
}

/**
 * Represents an action that may potentially fail with an exception
 */
@FunctionalInterface
interface ThrowableConsumer<T, E extends Throwable> {
    void accept(T t) throws E;
}

/**
 * Represents the result of a computation that could have succeeded with a value of the type T
 * or failed with a Throwable.
 */
interface Try<T> {
    /**
     * Executes the given operation and returns the result wrapped in a Success or Failure
     */
    static <T> Try<T> of(ThrowableOperation<T> operation) {
        try {
            return new Success<>(operation.execute());
        } catch (Throwable t) {
            return new Failure<>(t);
        }
        // take it from the previous step
    }

    /**
     * Returns true if the original operation succeeded, otherwise returns false
     */
    boolean isSuccess();

    /**
     * Returns the resulting value if this is a Success, otherwise throws the original exception
     */
    T get() throws Throwable;

    /**
     * Returns the resulting value if this is a Success,
     * otherwise throws the original exception wrapped in a RuntimeException
     *
     * @throws RuntimeException that wraps the original exception
     */
    T getUnchecked();

    /**
     * Converts this to a non-empty Optional that wraps the resulting value if this is Success,
     * otherwise returns an empty Optional
     */
    Optional<T> toOptional();

    /**
     * Returns the given default value if this is a Failure,
     * otherwise returns the resulting value
     */
    T getOrElse(T defaultValue);

    /**
     * Returns the resulting value if it is a Success,
     * otherwise returns the result produced by the given supplier
     */
    T getOrElseSupply(Supplier<? extends T> supplier);

    /**
     * Returns the resulting value if this is a Success,
     * otherwise throws an exception produced by the exception supplier
     *
     * @throws Throwable produced by the exception supplier
     */
    <X extends Throwable> T getOrElseThrow(Supplier<? extends X> exceptionSupplier) throws X;

    /**
     * Applies the given action to the resulting value if it is a Success, otherwise does nothing
     *
     * @return the current Try object
     * @throws E if the action throws an exception
     */
    <E extends Throwable> Try<T> onSuccess(ThrowableConsumer<T, E> action) throws E;

    /**
     * Applies the given action to the exception if it is a Failure, otherwise does nothing
     *
     * @return the current Try object
     * @throws E if the action throws an exception
     */
    <E extends Throwable> Try<T> onFailure(ThrowableConsumer<Throwable, E> action) throws E;

    /**
     * Converts this Success into a Failure (which holds NoSuchElementException)
     * if it is a Success and the predicate doesn't match for the value,
     * otherwise returns this Try (Success or Failure)
     */
    Try<T> filter(Predicate<T> predicate);
}

/**
 * Represents a successful execution
 */
class Success<T> implements Try<T> {
    private final T value;

    Success(T value) {
        this.value = value;
    }

    @Override
    public boolean isSuccess() {
        return true;
        // take it from the previous step
    }

    @Override
    public T get() {
        return value;
        // take it from the previous step
    }

    @Override
    public T getUnchecked() {
        return value;
        // take it from the previous step
    }

    @Override
    public Optional<T> toOptional() {
        return Optional.of(value);
        // take it from the previous step
    }

    @Override
    public T getOrElse(T defaultValue) {
        return value;
        // write your code here
    }

    @Override
    public T getOrElseSupply(Supplier<? extends T> supplier) {
        return value;
        // write your code here
    }

    @Override
    public <X extends Throwable> T getOrElseThrow(Supplier<? extends X> exceptionSupplier) {
        return value;
        // write your code here
    }

    @Override
    public <E extends Throwable> Try<T> onSuccess(ThrowableConsumer<T, E> action) throws E {
        action.accept(value);
        return this;
        // write your code here
    }

    @Override
    public <E extends Throwable> Try<T> onFailure(ThrowableConsumer<Throwable, E> action) {
        return this;
        // write your code here
    }

    @Override
    public Try<T> filter(Predicate<T> predicate) {
        if (predicate.test(value)) {
            return this;
        } else {
            return new Failure<>(new NoSuchElementException());
        }
        // write your code here
    }

    @Override
    public String toString() {
        return "Success[" + value + "]";
    }
}

/**
 * Represents a failed execution
 */
class Failure<T> implements Try<T> {
    private final Throwable e;

    Failure(Throwable e) {
        this.e = e;
    }

    @Override
    public boolean isSuccess() {
        return false;
        // take it from the previous step
    }

    @Override
    public T get() throws Throwable {
        throw e;
        // take it from the previous step
    }

    @Override
    public T getUnchecked() {
        throw new RuntimeException(e);
        // take it from the previous step
    }

    @Override
    public Optional<T> toOptional() {
        return Optional.empty();
        // take it from the previous step
    }

    @Override
    public T getOrElse(T defaultValue) {
        return defaultValue;
        // write your code here
    }

    @Override
    public T getOrElseSupply(Supplier<? extends T> supplier) {
        return supplier.get();
        // write your code here
    }

    @Override
    public <X extends Throwable> T getOrElseThrow(Supplier<? extends X> exceptionSupplier) throws X {
        throw exceptionSupplier.get();
        // write your code here
    }

    @Override
    public <E extends Throwable> Try<T> onSuccess(ThrowableConsumer<T, E> action) {
        return this;
        // write your code here
    }

    @Override
    public <E extends Throwable> Try<T> onFailure(ThrowableConsumer<Throwable, E> action) throws E {
        action.accept(e);
        return this;
        // write your code here
    }

    @Override
    public Try<T> filter(Predicate<T> predicate) {
        return this;
        // write your code here
    }

    @Override
    public String toString() {
        return "Failure[" + e + "]";
    }
}

class Example {
    public static void main(String[] args) {
        Try<Integer> tryParse = Try.of(() -> Integer.parseInt("100")); // Success[100]
        System.out.println(tryParse);

        System.out.println(tryParse.getOrElse(200)); // 100
        System.out.println(tryParse.getOrElseSupply(() -> 10 * 20 * 30)); // 100
        System.out.println(tryParse.getOrElseThrow(IllegalArgumentException::new)); // 100

        // Failure[java.lang.NumberFormatException: For input string: "100K"]
        Try<Integer> tryParse2 = Try.of(() -> Integer.parseInt("100K"));
        System.out.println(tryParse2);

        System.out.println(tryParse2.getOrElse(0)); // 0
        System.out.println(tryParse2.getOrElseSupply(() -> 10 * 20 * 30)); // 6000
//        System.out.println(tryParse2.getOrElseThrow(IllegalArgumentException::new)); // throws IAE

        Try.of(() -> Integer.parseInt("100"))
                .onSuccess(value -> System.out.println("Parsed: " + value)) // will be executed
                .onFailure(e -> System.out.println("Cannot parse the value")); // won't be executed

        Try.of(() -> Integer.parseInt("100K"))
                .onSuccess(value -> System.out.println("Parsed: " + value))
                .onFailure(e -> System.out.println("Cannot parse the value"));

        int n1 = Try.of(() -> Integer.parseInt("100")) // Success[100]
                .filter(value -> value > 50) // Success[100]
                .getUnchecked(); // 100

        int n2 = Try.of(() -> Integer.parseInt("49")) // Success[100]
                .filter(value -> value > 50) // Failure[NoSuchElementException]
                .getUnchecked(); // throws a RuntimeException that wraps NoSuchElementException

        int n3 = Try.of(() -> Integer.parseInt("100K")) // Failure[NumberFormatException]
                .filter(value -> value > 50) // Failure[IllegalArgumentException]
                .getUnchecked(); // throws a RuntimeException that wraps IllegalArgumentException
    }
}