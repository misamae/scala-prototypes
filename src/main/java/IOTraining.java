import java.io.*;

public class IOTraining {

    // two types of streams:
    //   1. binary
    //   2. text (unicode)

    // streams are either read or write

    // InputStream => bytes
    // Reader => text


    // these streams are abstract classes
    // ByteInputStream and ByteOutputStream
    // PipedInputStream
    // FileInputStream


    // Reader concrete classes
    // CharArrayReader
    // StringReader
    // PipedReader
    // InputStreamReader => consume InputStream as text stream

    // InputStreamReader has a derived class FileReader
    // OutputStreamWriter has a derived class FileWriter

    void readFromStreamSingle() throws IOException {
        InputStream input = null;

        int intVal;

        while((intVal = input.read()) >= 0) {

            byte b = (byte)intVal;
        }
    }

    void readFromTextSingle() throws IOException {
        Reader reader = null;

        int intVal;

        while((intVal = reader.read()) >= 0) {
            char c = (char)intVal;

        }
    }

    void readFromStreamArray() throws IOException {
        InputStream input = null;

        int length;
        byte[] bArray = new byte[50];

        while ((length = input.read(bArray)) >= 0) {

            for(int i = 0; i < length; i++) {
                byte b = bArray[i];
                //do something with b
            }
        }
    }

    void writeToStream() throws IOException {
        OutputStream output = null;

        output.write(100);

//        byte[] bArray = new byte[50];
        byte[] bArray = {1, 2, 10};
        output.write(bArray);
    }

    void writeToWriter() throws IOException {
        Writer writer = null;

        writer.write(100);

        char[] cArray = {'c', 'd', 'e'};

        writer.write(cArray);
        writer.write("string writing");
    }

    // AutoCloseable interface

    // Try-with-resources

    static void tryWithResource() throws IOException {

        char[] buffer = new char[8];
        int length;
        try(Reader reader = new InputStreamReader(IOTraining.class.getResourceAsStream("sample.txt"))) {
            while((length = reader.read(buffer)) >= 0) {
                System.out.println("\nlength " + length);
                for(int i = 0; i < length; i++) {
                    System.out.print(buffer[i]);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }

    static void tryWithResourceCopy() throws IOException {

        char[] buffer = new char[8];
        int length;
        try(Reader reader = new InputStreamReader(IOTraining.class.getResourceAsStream("sample.txt"));
        Writer writer = new OutputStreamWriter(new FileOutputStream(new File("output.txt")))) {
            while((length = reader.read(buffer)) >= 0) {
                System.out.println("\nlength " + length);
                writer.write(buffer, 0, length);
            }
        } catch (IOException e) {
            System.out.println(e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }

    static void doCloseThing() {
        try(MyAutoCloseable ac = new MyAutoCloseable()) {
            ac.saySomething();
        } catch (IOException e){
            System.out.println(e.getClass().getSimpleName() + " - " + e.getMessage());

            for(Throwable t:e.getSuppressed()) {
                System.out.println(t.getClass().getSimpleName() + " - " + t.getMessage());
            }
        }
    }

    static void doChain(InputStream in) throws IOException {
        int length;
        char[] buffer = new char[128];

        try(InputStreamReader rdr = new InputStreamReader(in)) {
            while((length = rdr.read(buffer)) >= 0) {
                System.out.println("length is " + length);
                System.out.print(buffer);
            }
        }
    }

    static void bufferedReader() throws IOException {
        try(BufferedReader br = new BufferedReader(
                new FileReader(
                        new File("sample.txt")))) {

        }
    }

    // use BufferReader and BufferWriter

    public static void main(String[] args) throws IOException {
//        tryWithResource();
//        tryWithResourceCopy();
        doCloseThing();
    }
}

