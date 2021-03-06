package server.packets.in;

import main.Vector2;
import server.packets.Packet;
import server.packets.PacketIn;
import server.packets.PacketType;
import main.ZoomType;

/**
 * @author Edwin
 */

public class ZoomPacket extends PacketIn {

    private ZoomType zoomtype;
    private Vector2 position;

    public ZoomPacket(String data) {
        super(PacketType.ZOOM);

        String[] args = data.split(Packet.separator);

        if (args.length < 3) {
            throw new IllegalArgumentException("Invalid packet format");
        }

        try {
            zoomtype = ZoomType.getFromId(Integer.parseInt(args[0]));
            double x = Double.parseDouble(args[1]);
            double y = Double.parseDouble(args[2]);
            position = new Vector2(x, y);
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Invalid packet data");
        }
    }

    public ZoomType getZoomType() {
        return this.zoomtype;
    }

    public Vector2 getPosition() {
        return this.position;
    }
}
