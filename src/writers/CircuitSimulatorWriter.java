package writers;

import view.CircuitComponent;
import view.CircuitConnection;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by GR5 on 21.04.15.
 */
public class CircuitSimulatorWriter extends CircuitWriter {
    public void write(File file, Collection<CircuitComponent> components,
                               Collection<CircuitConnection> connections) throws IOException {
        List<String> lines = new LinkedList<String>();

        /* Zapisywanie komponentów  */
        lines.add( String.valueOf(components.size()) );
        int idx = 0;
        for (CircuitComponent c: components) {
            c.setIdx(idx);

            StringBuilder sb = new StringBuilder();
            sb.append(String.valueOf(idx)).append(";");
            sb.append(c.getTypeCode()).append(";");
            sb.append(c.name).append(";");
            sb.append(String.valueOf(c.x)).append(";");
            sb.append(String.valueOf(c.y)).append(";");
            sb.append(String.valueOf(c.getOrientation())).append(";");
            sb.append(String.valueOf(c.getElectricalValue()));

            lines.add(sb.toString());

            idx++;
        }

        /* Zapisywanie komponentów  */
        lines.add( String.valueOf(connections.size()) );
        for (CircuitConnection c: connections) {
            StringBuilder sb = new StringBuilder();
            sb.append(c.getSrc().getParent().getIdx()).append(";");
            sb.append(c.getSrc().getParent().indexOf(c.getSrc())).append(";");
            sb.append(c.getDest().getParent().getIdx()).append(";");
            sb.append(c.getDest().getParent().indexOf(c.getDest())).append(";");
            lines.add(sb.toString());
        }

        Files.write(file.toPath(), lines);
    }
}
