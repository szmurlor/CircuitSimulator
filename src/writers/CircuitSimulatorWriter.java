package writers;

import view.CircuitComponent;
import view.CircuitConnection;
import view.CircuitSimulator;
import view.TerminalView;
import view.components.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.*;

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

        Files.write(file.toPath(), lines, Charset.defaultCharset());
    }

    @Override
    public void writeNgSpice(File file, Collection<CircuitComponent> coms, Collection<CircuitConnection> cons) throws IOException {

        //renumerateIdx(coms, cons);
        renumerateIdxV2(coms, cons);

        List<String> lines = new LinkedList<String>();
        lines.add("Moja symulacja");
        lines.add("\n");

        int imax = 0;
        for (CircuitComponent com: coms) {
            for (TerminalView t: com.getTerminals()) {
                if (t.getIdx() > imax)
                    imax = t.getIdx();
            }
            if (com instanceof ResistorView) {
                lines.add("R" + com.name + " " + com.getTerminals().get(0).getIdx() + " " + com.getTerminals().get(1).getIdx() + " " + com.getElectricalValue() );
            } else if (com instanceof CapacitorView) {
                lines.add("C" + com.name + " " + com.getTerminals().get(0).getIdx() + " " + com.getTerminals().get(1).getIdx() + " " + com.getElectricalValue() );
            } else if (com instanceof CoilView) {
                lines.add("L" + com.name + " " + com.getTerminals().get(0).getIdx() + " " + com.getTerminals().get(1).getIdx() + " " + com.getElectricalValue() );
            } else if (com instanceof CurrentSourceView) {
                lines.add("I" + com.name + " " + com.getTerminals().get(0).getIdx() + " " + com.getTerminals().get(1).getIdx() + " DC " + com.getElectricalValue() );
            } else if (com instanceof VoltageSourceView) {
                lines.add("V" + com.name + " " + com.getTerminals().get(0).getIdx() + " " + com.getTerminals().get(1).getIdx() + " DC " + com.getElectricalValue() );
            }
        }
        lines.add(".control");
        lines.add("op");
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= imax; i++) {
            sb.append("v(" + i + ") ");
        }
        lines.add("print " + sb.toString());
        lines.add("wrdata " + CircuitSimulator.wrdataNgSpice + " "  + sb.toString());
        lines.add(".endc");

        Files.write(file.toPath(), lines, Charset.defaultCharset());

    }

    private void renumerateIdxV2(Collection<CircuitComponent> coms, Collection<CircuitConnection> cons) {
        for (CircuitConnection con: cons) {
            con.setIdx(Integer.MAX_VALUE);
        }

        Set<CircuitConnection> done = new HashSet<CircuitConnection>();
        Set<CircuitConnection> current = new HashSet<CircuitConnection>();

        int idx = 0;
        for (CircuitComponent c: coms) {
            for (TerminalView t: c.getTerminals()) {
                Collection<CircuitConnection> tCons = findConnectionsWithTerminal(cons, t);
                boolean found = false;
                for (CircuitConnection con : tCons) {
                    if (done.contains(con)) {
                        t.setIdx(con.getIdx());
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    current.clear();
                    addConnectionsWithTerminalAndNeighbours(current, cons, t);

                    int imin = idx;
                    for (CircuitConnection cc : current) {
                        if (cc.getIdx() < idx)
                            imin = cc.getIdx();
                    }

                    if (imin == idx)
                        idx++;

                    t.setIdx(imin);
                    for (CircuitConnection cc : current) {
                        cc.setIdx(imin);
                    }
                    done.addAll(current);
                }
            }
        }
    }

    private void addConnectionsWithTerminalAndNeighbours(Set<CircuitConnection> res, Collection<CircuitConnection> cons, TerminalView t) {
        for (CircuitConnection c: findConnectionsWithTerminal(cons, t)) {
            if (!res.contains(c)) {
                res.add(c);

                if (c.getSrc().equals(t)) {
                    addConnectionsWithTerminalAndNeighbours(res, cons, c.getDest());
                } else {
                    addConnectionsWithTerminalAndNeighbours(res, cons, c.getSrc());
                }
            }
        }
    }

    private void renumerateIdx(Collection<CircuitComponent> coms, Collection<CircuitConnection> cons) {
        for (CircuitConnection con: cons) {
            con.setIdx(Integer.MAX_VALUE);
        }

        int idx = 0;
        for (CircuitComponent c: coms) {
            c.setIdx(idx);

            for (TerminalView t: c.getTerminals()) {
                Collection<CircuitConnection> tCons = findConnectionsWithTerminal(cons, t);
                int min = idx;
                for (CircuitConnection con: tCons) {
                    if (min > con.getIdx())
                        min = con.getIdx();
                }

                for (CircuitConnection con: tCons)
                    con.setIdx(min);

                t.setIdx(min);
                if (min == idx)
                    idx++;
            }
        }
    }

    private Collection<CircuitConnection> findConnectionsWithTerminal(Collection<CircuitConnection> cons, TerminalView t) {
        Collection<CircuitConnection> res = new LinkedList<CircuitConnection>();
        for (CircuitConnection con: cons) {
            if (con.getSrc().equals(t) || con.getDest().equals(t))
                res.add(con);
        }
        return res;
    }
}
