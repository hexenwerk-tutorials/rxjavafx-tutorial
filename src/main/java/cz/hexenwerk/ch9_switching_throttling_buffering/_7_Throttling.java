package cz.hexenwerk.ch9_switching_throttling_buffering;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class _7_Throttling
{
    public static void main(String[] args)
    {

        Observable.interval(300, TimeUnit.MILLISECONDS)
//                .throttleFirst(1, TimeUnit.SECONDS)
                .throttleLast(1, TimeUnit.SECONDS)
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