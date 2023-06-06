package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.Resume;

import java.lang.reflect.Method;

public class MainReflection {

    public static void main(String[] args) {
        try {
            Resume r = new Resume();
            Class<? extends Resume> resumeClassInfo = r.getClass();
            Method method = resumeClassInfo.getMethod("toString");
            Object result = method.invoke(r);
            System.out.println(result);
        } catch(Exception e) {

        }
    }
}