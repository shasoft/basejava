package ru.javawebinar.basejava;

public class MainDeadlock {
    public static void main(String[] args) {
        String obj1 = "ААА";
        String obj2 = "БББ";
        runThreadWait("1", obj1, obj2);
        runThreadWait("2", obj2, obj1);
    }

    public static void runThreadWait(String threadId, Object obj1, Object obj2) {
        new Thread(() -> {
            System.out.println("<" + threadId + "> Ожидаем объект 1: " + obj1);
            synchronized (obj1) {
                System.out.println("<" + threadId + "> Объект 1 захвачен: " + obj1);
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                System.out.println("<" + threadId + "> Ожидаем объект 2: " + obj2);
                synchronized (obj2) {
                    System.out.println("<" + threadId + "> Объект 2 захвачен: " + obj2);
                }
            }
        }).start();
    }
}
