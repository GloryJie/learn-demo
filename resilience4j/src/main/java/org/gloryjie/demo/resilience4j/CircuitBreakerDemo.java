package org.gloryjie.demo.resilience4j;


import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;

import java.util.concurrent.Callable;
import java.util.function.BiFunction;

public class CircuitBreakerDemo {

    public static void main(String[] args) throws Exception {
        CircuitBreakerRegistry circuitBreakerRegistry = CircuitBreakerRegistry.ofDefaults();

        CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker("test");

        BiFunction<String,String,String> f = (s1,s2) -> s1 + s2;

        Callable<String> stringCallable = circuitBreaker.decorateCallable(() -> {
            return f.apply("1", "2");
        });

        System.out.println(stringCallable.call());
    }


}
