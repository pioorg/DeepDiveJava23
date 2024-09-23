package org.przybyl.ddj23.concurrency.structuredConcurrency;


import java.util.Random;

public class FakeWorker {

    // seed is used here ONLY for demonstration purposes, don't copy and use that in your production code!
    private static final Random rand = new Random(42L);

    public static void sneakySleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
//       hardWork(d);
    }

    public static long hardWork(long millis) {
        long stop = System.nanoTime() + millis * 1_000_000;
        long mod = -1;
        while (System.nanoTime() < stop) {
//            if (Thread.interrupted()) {
//                throw new RuntimeException("I should have been interrupted exception, sorry!");
//            }
            long a = rand.nextLong(1_000_000_000, 1_000_000_000_000L);
            long b = rand.nextLong(1_000_000, 1_000_000_000);
            mod = a % b;
        }
        return mod;
    }
}
