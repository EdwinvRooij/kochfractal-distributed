package client.packets.out;

import java.io.DataOutputStream;
import java.io.IOException;
import client.packets.Packet;
import client.packets.PacketOut;
import client.packets.PacketType;
import utils.Vector2;

/**
 * @author Cas Eliens
 */
public class PacketOut06Drag extends PacketOut {

    private Vector2 position;

    public PacketOut06Drag(double x, double y) {
        super(PacketType.DRAG);

        position = new Vector2(x, y);
    }

    @Override
    public void sendData(DataOutputStream out) throws IOException {
        out.writeBytes("++" + String.format("%02d", type.getID()) + position.getX() + Packet.separator + position.getY() + "==\n");
        out.flush();
    }

}