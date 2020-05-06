package cz.hexenwerk.ch9_switching_throttling_buffering;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class _8_Debouncing
{
    public static void main(String[] args)
    {

        Observable<String> source = Observable.concat(
                Observable.interval(100, TimeUnit.MILLISECONDS).take(10).map(i -> "A" + i),
                Observable.interval(2, TimeUnit.SECONDS).take(3).map(i -> "B" + i),
                Observable.interval(500, TimeUnit.MILLISECONDS).take(4).map(i -> "C" + i)
        );

        source.debounce(1, TimeUnit.SECONDS)
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