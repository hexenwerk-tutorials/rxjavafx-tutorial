package com.github.pkrysztofiak.rxjavafxtutorial.examples;

import io.reactivex.Observable;

public class Example031 {

	public static void main(String[] args) {
		Observable<String> source1 = Observable.just("one", "two");
		Observable<String> source2 = Observable.just("three", "four", "five");

		Observable.concat(source1, source2)
		.map(String::length)
		.toList()
		.subscribe(System.out::println);
	}
}