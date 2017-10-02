package ru.matevosyan;

import org.junit.Test;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Test two threads {@link ru.matevosyan.StopThread.CountChar} and {@link ru.matevosyan.StopThread.Timer}.
 * @author Matevosyan Vardan
 * @version 1.0
 * created on 06.08.2017
 */

public class StopThreadTest {

    /**
     * Start the program and check output.
     * @throws InterruptedException if thread is interrupted.
     * @throws IOException when stream get input or output exception.
     * @throws URISyntaxException when get file URI and get syntax exception.
     */

    @Test
    public void whenStartToStopThreadAndPassRunnableToTimerThanCheckResultsOnTheConsole() throws InterruptedException,
            IOException, URISyntaxException {
        ClassLoader classLoader = Setting.class.getClassLoader();
        Setting setting = new Setting();

        try (InputStream resourceAsStream = classLoader.getResourceAsStream("config.properties")) {
            setting.load(resourceAsStream);
        }

        List<String> bigBook = new ArrayList<>();
        String path = setting.getValue("text.txt");
        URL urlToRead = classLoader.getResource(path);

        assert urlToRead != null;
        File textFile = Paths.get(urlToRead.toURI()).toFile();
        try (BufferedReader bufReader = new BufferedReader(new InputStreamReader(new FileInputStream(textFile),
                "UTF-8"))) {
            while (bufReader.ready()) {
                bigBook.add(bufReader.readLine());
            }
        } catch (IOException ioe) {
            ioe.getMessage();
        }

        StopThread stopThread = new StopThread();

//        when time passing to the timer is to big.
        StopThread.CountChar countChar = stopThread.new CountChar(bigBook.toString());
        Thread timer = new Thread(stopThread.new Timer(20000000, countChar));

        Thread thread = new Thread(countChar);
        thread.start();
        timer.start();

        thread.join();
        timer.join();

//        another testing moment when time passing to the timer is to small.
        StopThread stopThread2 = new StopThread();
        StopThread.CountChar countChar2 = stopThread2.new CountChar(bigBook.toString());
        Thread timer2 = new Thread(stopThread2.new Timer(2000, countChar2));

        Thread thread2 = new Thread(countChar2);
        thread2.start();
        timer2.start();

        thread2.join();
        timer2.join();
    }

}