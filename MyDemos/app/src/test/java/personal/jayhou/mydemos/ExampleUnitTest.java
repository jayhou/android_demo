package personal.jayhou.mydemos;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void hashMap_performance() {
        System.out.println("hashMap_performance");
        new Thread() {
            @Override
            public void run() {
                System.out.println("Hash Map start get");
            }
        }.start();
    }
}