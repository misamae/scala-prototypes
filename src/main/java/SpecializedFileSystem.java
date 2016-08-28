import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by meisam on 15/08/2016.
 */
public class SpecializedFileSystem {

    public static void main(String[] args) throws IOException, URISyntaxException {
        // zip file system => jar:file
        // e.g. jar:file:/meisam/data.zip

        String[] data = {
            "line 1",
            "line 1",
            "line 1",
            "line 1",
            "line 1",
            "line 1",
            "line 1",
        };

        try(FileSystem zfs = openZip(Paths.get("myData.zip"))) {
            copyToZip(zfs);
        } catch (Exception e) {
            System.out.println("error happened");
        }
    }

    private static FileSystem openZip(Path zipPath) throws URISyntaxException, IOException {
        Map<String, String> providerProps = new HashMap<>();
        providerProps.put("create", "true");

        URI zipUri = new URI("jar:file", zipPath.toUri().getPath(), null);

        return FileSystems.newFileSystem(zipUri, providerProps);
    }

    private static void copyToZip(FileSystem zfs) throws URISyntaxException, IOException {
        URI uri = SpecializedFileSystem.class.getResource("/sample.txt").toURI();
        Path sourcePath = Paths.get(uri);
        //the following is the same as the previous code
        //Paths is shortcut for Default FileSystem
//        Path sourcePath = FileSystems.getDefault().getPath("sample")

        Path targetPath = zfs.getPath("/sampleCopied.txt");

        Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);

        Path anotherSource = Paths.get("/Users/meisam/code/king/npd matching/npd_matching.xlsx");
        Path anotherTarget = zfs.getPath("/npd_matching.xlsx");

        Files.copy(anotherSource, anotherTarget, StandardCopyOption.REPLACE_EXISTING);
    }
}
