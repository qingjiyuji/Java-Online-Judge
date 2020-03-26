import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import java.io.*;
import java.util.Scanner;

public class MainTest {

    // 其中每一个test方法代表一个测试用例，data为输入数据。assertEquals中的字符串代表输出数据

    Main calculation = new Main();

    @Test(timeout = 2000)
    public void test() {
		String data = "2 1 2";
        InputStream stdin = System.in;
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
        try {
            System.setIn(new ByteArrayInputStream(data.getBytes()));
            calculation.main(null);
        } finally {
            System.setIn(stdin);
        }
        assertEquals("3", outContent.toString().trim());
		//assertTrue("NO\r\n".equals(outContent.toString())||"NO".equals(outContent.toString())); 		
    }
    @Test(timeout = 2000)
    public void test2() {
		String data = "2 5 6";
        InputStream stdin = System.in;
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
        try {
            System.setIn(new ByteArrayInputStream(data.getBytes()));
            calculation.main(null);
        } finally {
            System.setIn(stdin);
        }
        assertEquals("11", outContent.toString().trim());
		//assertTrue("NO\r\n".equals(outContent.toString())||"NO".equals(outContent.toString()));
    }
    @Test(timeout = 2000)
    public void test3() {
		String data = "2 5 -7";
        InputStream stdin = System.in;
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
        try {
            System.setIn(new ByteArrayInputStream(data.getBytes()));
            calculation.main(null);
        } finally {
            System.setIn(stdin);
        }
        assertEquals("-2", outContent.toString().trim());
		//assertTrue("NO\r\n".equals(outContent.toString())||"NO".equals(outContent.toString()));
    }
    @Test(timeout = 2000)
    public void test4() {
		String data = "2 9 0";
        InputStream stdin = System.in;
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
        try {
            System.setIn(new ByteArrayInputStream(data.getBytes()));
            calculation.main(null);
        } finally {
            System.setIn(stdin);
        }
        assertEquals("9", outContent.toString().trim());
		//assertTrue("NO\r\n".equals(outContent.toString())||"NO".equals(outContent.toString()));
    }
    @Test(timeout = 2000)
    public void test5() {
		String data = "2 9 10";
        InputStream stdin = System.in;
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
        try {
            System.setIn(new ByteArrayInputStream(data.getBytes()));
            calculation.main(null);
        } finally {
            System.setIn(stdin);
        }
        assertEquals("19", outContent.toString().trim());
		//assertTrue("NO\r\n".equals(outContent.toString())||"NO".equals(outContent.toString()));
    }

}