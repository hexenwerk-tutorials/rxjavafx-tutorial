package com.github.pkrysztofiak.rxjavafxtutorial.exmples;

import io.reactivex.Observable;

public class Example029 {

	public static void main(String[] args) {
		Observable<String> source1 = Observable.just("two", "three");

		source1.startWith("one")
		.map(String::length)
		.toList()
		.subscribe(System.out::println);
	}
}