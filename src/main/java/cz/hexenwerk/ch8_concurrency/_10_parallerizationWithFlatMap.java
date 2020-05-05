package cz.hexenwerk.ch8_concurrency;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public final class _10_parallerizationWithFlatMap
{
    public static void main(String[] args)
    {
        Observable.range(1, 10)
                .flatMap(i -> Observable.just(i)
                        .subscribeOn(Schedulers.computation())
                        .map(_10_parallerizationWithFlatMap::runLongProcess)
                ).subscribe(i -> System.out.println("Received " +
                i + " on " + Thread.currentThread().getName())
        );

        try
        {
            Thread.sleep(10000);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    public static int runLongProcess(int i)
    {
        try
        {
            Thread.sleep((long) (Math.random() * 1000));
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return i;
    }
}
