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
        lines.add( String.valueOf(components.size()) );

        Files.write(file.toPath(), lines);
    }
}
