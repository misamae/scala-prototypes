import java.io.IOException;

/**
 * Created by meisam on 15/08/2016.
 */

public class MyAutoCloseable implements AutoCloseable {

    public void saySomething() throws IOException {
        throw new IOException("exception from saySomething");
//        System.out.println("something");
    }

    @Override
    public void close() throws IOException {
        System.out.println("close");

        throw new IOException("exception during close");
    }
}
