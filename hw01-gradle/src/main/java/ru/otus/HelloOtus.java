package ru.otus;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * To start the application:
 * ./gradlew build
 * java -jar ./hw01-gradle/build/libs/gradleHelloWorld-0.1.jar
 *
 */
public class HelloOtus {
    public static void main(String... args) {
        List<Integer> example = new ArrayList<>();
        int min = 0;
        int max = 100;
        for (int i = min; i < max; i++) {
            example.add(i);
        }

        System.out.println(Lists.reverse(example));
    }
}
