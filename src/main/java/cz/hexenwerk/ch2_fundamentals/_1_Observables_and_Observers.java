package cz.hexenwerk.ch2_fundamentals;

import io.reactivex.Observable;
import io.reactivex.observers.ResourceObserver;

import java.util.Arrays;

public final class _1_Observables_and_Observers
{

    public static void main(String[] args)
    {
        /**************
         * OBSERVABLES
         *************/
        // 1.
        Observable<Integer> source = Observable.just(1, 2, 3, 4, 5);

        // 2.
        Observable<Integer> source2 = Observable.range(1, 5);

        // 3.
        Observable<Integer> source3 = Observable.fromIterable(Arrays.asList(1, 2, 3, 4, 5));

        /************
         * OBSERVERS
         ***********/
        // 1.
        source.subscribe(System.out::println);

        // 2. prefferedly handle onComplete() and onError()
        source2.subscribe(System.out::println,
                Throwable::printStackTrace,
                () -> System.out.println("Done!")
        );

        // 3. create own Observer
        source3.subscribe(new ResourceObserver<Integer>()
                          {
                              @Override
                              public void onComplete()
                              {
                                  System.out.println("Done!");
                              }

                              @Override
                              public void onError(Throwable e)
                              {
                                  e.printStackTrace();
                              }

                              @Override
                              public void onNext(Integer integer)
                              {
                                  System.out.println(integer);
                              }
                          }
        );
    }
}
