package cz.hexenwerk.ch2;

import io.reactivex.Observable;

public final class _2_Mapping_of_Observables
{

    public static void main(String[] args)
    {
        Observable<String> source = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon");

        /* OPERATORS */
        // 1. [5 4 5 5 7]
        source.map(String::length)
                .subscribe(System.out::println);

        // 2. [Alpha Gamma Delta Epsilon]
        source.filter(s -> s.length() >= 5)
                .subscribe(System.out::println);

        // 3.a. [5 4 7]
        source.map(String::length)
                .distinct()
                .subscribe(System.out::println);

        // 3.b. [Alpha Beta Epsilon]
        source.distinct(String::length)
                .subscribe(System.out::println);

        // 4. [Alpha Beta Gamma]
        source.take(3)
                .subscribe(System.out::println);

        // 5. [Alpha Beta Gamma Delta]
        source.takeUntil((String s) -> s.matches("D.*"))
                .subscribe(System.out::println);

        // 6. [5]
        source.count()
                .subscribe(System.out::println);

        // 7. [[Alpha, Beta, Gamma, Delta, Epsilon]]
        source.toList()
                .subscribe(System.out::println);

        // 8. [26]
        source.map(String::length)
                .reduce(0, (current, next) -> current + next)
                .subscribe(System.out::println);

        // 9. [0 5 9 14 19 26]
        source.map(String::length)
                .scan(0, (current, next) -> current + next)
                .subscribe(System.out::println);

        Observable<String> source2 = Observable.just("1/2/3", "4/5", "6/7/8");

        // 10. [1 2 3 4 5 6 7 8]
        source2.flatMap(s -> Observable.fromArray(s.split("/")))
                .subscribe(System.out::println);

        // 11. [6 9 21]
        source2.flatMapSingle(s ->
                Observable.fromArray(s.split("/"))
                        .map(Integer::valueOf)
                        .reduce(0, (curr, next) -> curr + next))
                .subscribe(System.out::println);
    }
}