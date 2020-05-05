package cz.hexenwerk.ch8_concurrency;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

public final class _3_multithread_with_multiple_observers
{
    public static void main(String[] args)
    {
        Observable<Integer> source = Observable.range(1, 5)
                .subscribeOn(Schedulers.newThread());

        //Observer 1
        source.subscribe(i -> System.out.println("Observer 1 receiving " + i + " on thread " + Thread.currentThread().getName()));

        //Observer 2
        source.subscribe(i -> System.out.println("Observer 2 receiving " + i + " on thread " + Thread.currentThread().getName()));

        try
        {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
