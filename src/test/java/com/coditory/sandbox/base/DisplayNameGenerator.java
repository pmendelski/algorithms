package com.coditory.sandbox.base;

import java.lang.reflect.Method;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.jupiter.api.DisplayNameGenerator.Standard;
import static org.junit.jupiter.api.DisplayNameGenerator.parameterTypesAsString;

public class DisplayNameGenerator extends Standard {
    private final Set<Class<?>> nestedClasses = ConcurrentHashMap.newKeySet();

    @Override
    public String generateDisplayNameForClass(Class<?> testClass) {
        return replaceCamelCase(super.generateDisplayNameForClass(testClass));
    }

    @Override
    public String generateDisplayNameForNestedClass(Class<?> nestedClass) {
        nestedClasses.add(nestedClass);
        return replaceCamelCase(super.generateDisplayNameForNestedClass(nestedClass)) + "...";
    }

    @Override
    public String generateDisplayNameForMethod(Class<?> testClass, Method testMethod) {
        String params = testMethod.getParameterCount() == 0
                ? ""
                : parameterTypesAsString(testMethod);
        String prefix = nestedClasses.contains(testClass)
                ? (generateDisplayNameForClass(testClass) + " ")
                : "";
        return prefix + this.replaceCamelCase(testMethod.getName()) + params;
    }

    String replaceCamelCase(String camelCase) {
        StringBuilder result = new StringBuilder();
        result.append(camelCase.charAt(0));
        for (int i = 1; i < camelCase.length(); i++) {
            if (Character.isUpperCase(camelCase.charAt(i))) {
                result.append(' ');
                result.append(Character.toLowerCase(camelCase.charAt(i)));
            } else {
                result.append(camelCase.charAt(i));
            }
        }
        return result.toString();
    }
}
