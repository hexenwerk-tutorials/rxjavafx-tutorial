package cz.hexenwerk.ch2;

import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;

public final class _7_ConnectableObservable
{

    public static void main(String[] args)
    {
        ConnectableObservable<String> source = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon").publish();
        source.subscribe(s -> System.out.println("Observer 1: " + s));
        source.subscribe(s -> System.out.println("Observer 2: " + s));
        source.connect();
    }
}