import java.io.BufferedReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by meisam on 15/08/2016.
 */
public class NIOTraining {

    // old java.io package is deprecated
    // only the File type is deprecated

    static void readData() throws IOException, URISyntaxException {

        URI uri = NIOTraining.class.getResource("sample.txt").toURI();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(uri))) {
            String inValue;
            while((inValue = br.readLine()) != null) {
                System.out.println(inValue);
            }
        }
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
        System.out.println("file in java.nio");
        // Files create, copy, delete
        // more factory method in the new package

        readData();
    }
}
