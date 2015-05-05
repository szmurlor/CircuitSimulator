package writers;

import view.CircuitComponent;
import view.CircuitConnection;
import view.TerminalView;
import view.components.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by GR5 on 21.04.15.
 */
public class CircuitSimulatorReader {
    public void read(File selectedFile, Collection<CircuitComponent> comps, Collection<CircuitConnection> cons) throws Exception {
        List<CircuitComponent> tmpComps = new LinkedList<CircuitComponent>();
        List<CircuitConnection> tmpCons = new LinkedList<CircuitConnection>();

        List<String> lines = Files.readAllLines(selectedFile.toPath());
        int i = 0;

        int ncom = Integer.parseInt(lines.get(i++));
        for (int ic = 0; ic < ncom; ic++ ) {
            String s = lines.get(i++);
            String[] cols = s.split(";");

            if (cols.length == 7) {
                CircuitComponent cc = createComponent(cols);
                cc.setIdx(Integer.valueOf(cols[0]));
                cc.name = cols[2];
                cc.setElectricalValue(Double.parseDouble(cols[6]));
                if ("Vertical".equals(cols[5]))
                    cc.setOrientation(CircuitComponent.Orientation.Vertical);
                else
                    cc.setOrientation(CircuitComponent.Orientation.Horizontal);

                tmpComps.add(cc);
            } else {
                throw new Exception("Nieprawidłowy format pliku w linii " + i + ". Oczekiwałem 7 kolumn rozdzielonych średnikiem, a otrzymałem " + cols.length);
            }
        }
        comps.clear();;
        comps.addAll(tmpComps);

        int ncon = Integer.parseInt(lines.get(i++));
        for (int ic = 0; ic < ncon; ic++ ) {
            String s = lines.get(i++);
            String[] cols = s.split(";");

            if (cols.length == 4) {
                int idxSrc = Integer.parseInt(cols[0]);
                int iSrcTerm = Integer.parseInt(cols[1]);
                int idxDest = Integer.parseInt(cols[2]);
                int iDestTerm = Integer.parseInt(cols[3]);

                CircuitComponent cSrc = findByIdx(tmpComps, idxSrc);
                CircuitComponent cDest = findByIdx(tmpComps, idxDest);

                TerminalView src = cSrc.getTerminals().get(iSrcTerm);
                TerminalView dest = cDest.getTerminals().get(iDestTerm);

                CircuitConnection con = new CircuitConnection(src, dest);
                tmpCons.add(con);
            } else {
                throw new Exception("Nieprawidłowy format pliku w linii " + i + ". Oczekiwałem 7 kolumn rozdzielonych średnikiem, a otrzymałem " + cols.length);
            }
        }

        cons.clear();
        cons.addAll(tmpCons);
    }

    private CircuitComponent findByIdx(List<CircuitComponent> comps, int idx) {
        for (CircuitComponent cc: comps)
            if (cc.getIdx() == idx)
                return cc;

        return null;
    }

    private CircuitComponent createComponent(String[] cols) throws Exception {
        int x = Integer.parseInt(cols[3]);
        int y = Integer.parseInt(cols[4]);
        if ("R".equals(cols[1])) {
            return new ResistorView(x,y);
        } else if ("C".equals(cols[1])) {
            return new CapacitorView(x,y);
        } else if ("L".equals(cols[1])) {
            return new CoilView(x,y);
        } else if ("CS".equals(cols[1])) {
            return new CurrentSourceView(x,y);
        } else if ("VS".equals(cols[1])) {
            return new VoltageSourceView(x,y);
        } else if ("EMPTY".equals(cols[1])) {
            return new EmptyComponent(x,y);
        } else {
            throw new Exception("Nieznany typ komponentu: " + cols[1]);
        }
    }
}
