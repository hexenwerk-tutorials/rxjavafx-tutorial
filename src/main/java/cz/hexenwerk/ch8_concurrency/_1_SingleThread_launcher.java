package cz.hexenwerk.ch8_concurrency;

import io.reactivex.Observable;

public final class _1_SingleThread_launcher
{

    public static void main(String[] args)
    {
        Observable.range(1, 5)
                .subscribe(i -> System.out.println("Receiving " + i + " on thread " + Thread.currentThread().getName()));
    }
}
