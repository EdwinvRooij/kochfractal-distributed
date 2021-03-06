package server.packets.in;

import main.Vector2;
import server.packets.Packet;
import server.packets.PacketIn;
import server.packets.PacketType;

/**
 * @author Edwin
 */

public class DragPacket extends PacketIn {

    private Vector2 position;

    public DragPacket(String data) {
        super(PacketType.DRAG);

        String[] args = data.split(Packet.separator);

        if (args.length < 2) {
            throw new IllegalArgumentException("Invalid packet format");
        }

        try {
            double x = Double.parseDouble(args[0]);
            double y = Double.parseDouble(args[1]);
            position = new Vector2(x, y);
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Invalid packet data");
        }
    }

    public Vector2 getPosition() {
        return this.position;
    }
}
