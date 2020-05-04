package cz.hexenwerk.ch5_combining_observables;

import io.reactivex.Observable;

public final class _1_Concatenation
{

    public static void main(String[] args)
    {
        Observable<String> source1 = Observable.just("Alpha", "Beta", "Gamma");
        Observable<String> source2 = Observable.just("Delta", "Epsilon");


        /* CONCATENATION */
        // 1.
        Observable.concat(source1, source2)
                .map(String::length)
                .toList()
                .subscribe(System.out::println);

        // 2.
        source1.concatWith(source2)
                .map(String::length)
                .toList()
                .subscribe(System.out::println);

        // 3.
        source1.startWith(source2)
                .subscribe(System.out::println);

    }
}