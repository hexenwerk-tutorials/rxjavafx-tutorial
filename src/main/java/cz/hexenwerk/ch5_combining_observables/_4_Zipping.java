package cz.hexenwerk.ch5_combining_observables;

import io.reactivex.Observable;

/**
 * <h1>Zip</h1>
 * One way you can combine multiple Observables, even if they are different types, is by "zipping" their emissions together. Think of a zipper on a jacket and how the teeth pair up. From a reactive perspective, this means taking one emission from the first Observable, and one from a second Observable, and combining both emissions together in some way. <p/>
 * Take these two Observables, one emitting Strings and the other emitting Integers. For each String that is emitted, you can pair it with an emitted Integer and join them together somehow.
 * Notice that "A" paired with the "1", and "B" paired with the "2", and so on. Again, you are "zipping" them just like a jacket zipper. But take careful note of something: there are 6 letters emissions and 5 numbers emissions. What happened to that sixth letter "F" since it had no number to zip with? Since the two zipped sources do not have the same number of emissions, it was ignored the moment onComplete() was called by numbers. Logically, it will never have anything to pair with so it gave up and proceeded to skip it and call onComplete() down to the Subscriber.
 */
public final class _4_Zipping
{

    public static void main(String[] args)
    {

        Observable<String> letters = Observable.just("A", "B", "C", "D", "E", "F");
        Observable<Integer> numbers = Observable.just(1, 2, 3, 4, 5);

        // 1.
        Observable.zip(letters, numbers, (l, n) -> l + "-" + n)
                .subscribe(System.out::println,
                        Throwable::printStackTrace,
                        () -> System.out.println("Done!")
                );

        // 2.
        letters.zipWith(numbers, (l, n) -> l + "-" + n)
                .subscribe(System.out::println,
                        Throwable::printStackTrace,
                        () -> System.out.println("Done!")
                );
    }
}