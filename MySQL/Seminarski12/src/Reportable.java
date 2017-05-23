import java.io.OutputStreamWriter;

public interface Reportable {
    void report(OutputStreamWriter outputStream);
    void printHeader(OutputStreamWriter outputStream);
    void printFooter(OutputStreamWriter outputStreamWriter);
}