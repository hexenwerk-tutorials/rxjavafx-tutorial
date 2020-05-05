package cz.hexenwerk;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public final class MyApp
{
    public static void main(String[] args)
    {
        Observable.range(1, 10)
                .flatMap(i -> Observable.just(i)
                        .subscribeOn(Schedulers.computation())
                        .map(MyApp::runLongProcess)
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
