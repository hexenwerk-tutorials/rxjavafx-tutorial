package cz.hexenwerk.ch8_concurrency;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

public final class _2_multithread_with_subscribeOn
{
    public static void main(String[] args)
    {
        Observable.range(1, 5)
                .subscribeOn(Schedulers.newThread())
                .subscribe(i -> System.out.println("Receiving " + i + " on thread " + Thread.currentThread().getName()));

        try
        {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
