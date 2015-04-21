package writers;

import view.CircuitComponent;
import view.CircuitConnection;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

/**
 * Created by GR5 on 21.04.15.
 */
public abstract class CircuitWriter {
    public abstract void write(File file, Collection<CircuitComponent> components,
                               Collection<CircuitConnection> connections)  throws IOException;
}
