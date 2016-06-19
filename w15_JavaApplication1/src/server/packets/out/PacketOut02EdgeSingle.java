package server.packets.out;

import calculate.Edge;
import java.io.DataOutputStream;
import java.io.IOException;
import server.packets.Packet;
import server.packets.PacketOut;
import server.packets.PacketType;

/**
 * @author Cas Eliens
 */
public class PacketOut02EdgeSingle extends PacketOut {

    private int level;
    private Edge edge;
    private boolean allowMode;

    public PacketOut02EdgeSingle(int level, Edge edge, boolean allowMode) {
        super(PacketType.EDGE_SINGLE);

        this.level = level;
        this.edge = edge;
        this.allowMode = allowMode;
    }

    @Override
    public void sendData(DataOutputStream out) throws IOException {
        String msg = "++" + String.format("%02d", type.getID()) + level + Packet.separator + edge.X1 + Packet.separator + edge.Y1 + Packet.separator + edge.X2 + Packet.separator + edge.Y2 + Packet.separator + edge.getRGB().getX() + Packet.separator + edge.getRGB().getY() + Packet.separator + edge.getRGB().getZ() + Packet.separator + (allowMode ? 1 : 0) + "==\n";
        out.writeBytes(msg);
        out.flush();
    }

}