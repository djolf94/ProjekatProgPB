import java.io.OutputStreamWriter;
import java.util.List;

/**
 * Created by Nikola on 23.5.2017 for Seminarski12
 */
public class ReportPrinter {
    private OutputStreamWriter outputStream;

    public ReportPrinter(OutputStreamWriter outputStream){
        this.outputStream = outputStream;
    }

    public void printList(List<Reportable> list){
        if(list.size() > 0){
            list.get(0).printHeader(outputStream);
            for(Reportable r : list){
                r.report(outputStream);
            }
            list.get(0).printFooter(outputStream);
        }
    }
}

