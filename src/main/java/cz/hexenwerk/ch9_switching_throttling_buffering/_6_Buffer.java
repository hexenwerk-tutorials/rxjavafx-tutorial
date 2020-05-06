package cz.hexenwerk.ch9_switching_throttling_buffering;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class _6_Buffer
{
    public static void main(String[] args)
    {
        // 1.
        Observable.interval(300, TimeUnit.MILLISECONDS)
                .buffer(1, TimeUnit.SECONDS)
                .subscribe(System.out::println);

        try
        {
            Thread.sleep(10000);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        // 2.
        System.out.println("Another solution");
        Observable<Long> oneSecondInterval = Observable.interval(1, TimeUnit.SECONDS);

        Observable.interval(300, TimeUnit.MILLISECONDS)
                .buffer(oneSecondInterval)
                .subscribe(System.out::println);

        try
        {
            Thread.sleep(10000);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}