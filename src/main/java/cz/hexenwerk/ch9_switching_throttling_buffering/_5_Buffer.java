package cz.hexenwerk.ch9_switching_throttling_buffering;

import io.reactivex.Observable;

public class _5_Buffer
{
    public static void main(String[] args)
    {
        Observable.range(1, 1000)
                .buffer(10)
                .subscribe(System.out::println);
    }
}