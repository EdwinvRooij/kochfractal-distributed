package server.packets;

import server.ClientRunnable;
import server.packets.in.RequestCalcPacket;
import server.packets.in.ZoomPacket;
import server.packets.in.PressPacket;
import server.packets.in.DragPacket;

import java.util.HashMap;

/**
 * @author Edwin
 */

public abstract class PacketIn extends Packet {

    private static final HashMap<Integer, String> lastpack = new HashMap<Integer, String>();

    protected PacketIn(PacketType type) {
        super(type);
    }

    public static PacketIn parse(ClientRunnable client, String data) {
        PacketType type = null;

        // Trim unnecessary characters off of data string
        if (!data.startsWith("++") && data.contains("++")) {
            data = data.substring(data.indexOf("++"));
        }

        // Trim unnecessary characters off of data string
        if (!data.endsWith("==") && data.contains("==")) {
            data = data.substring(0, data.indexOf("=="));
        }

        // Partial packetstring
        if (data.startsWith("++") && !data.endsWith("==")) {
            lastpack.put(client.getID(), data);
        }

        if (!data.startsWith("++") && !lastpack.containsKey(client.getID())) {
            lastpack.put(client.getID(), lastpack.get(client.getID()) + data);
        }

        if (data.startsWith("++") && data.endsWith("==")) {
            // Reset if data string is correct
            lastpack.remove(client.getID());
        } else if (lastpack.containsKey(client.getID()) && lastpack.get(client.getID()).startsWith("++") && lastpack.get(client.getID()).endsWith("==")) {
            // Last pack is correct data string
            data = lastpack.get(client.getID());
            lastpack.remove(client.getID());
        } else {
            // Incorrect data string
            return null;
        }

        // Remove '++' and '=='
        data = data.substring(2, data.length() - 2);

        // String should at least have 2 characters (= packet type id)
        if (data.length() < 2) {
            return null;
        }

        // Get packet type
        try {
            type = PacketType.getByID(Integer.parseInt(data.substring(0, 2)));
        } catch (NumberFormatException ex) {
            return null;
        }

        // Remove type id
        data = data.substring(2);

        switch (type) {
            case REQUEST_START_CALC:
                return new RequestCalcPacket(data);
            case ZOOM:
                return new ZoomPacket(data);
            case PRESS:
                return new PressPacket(data);
            case DRAG:
                return new DragPacket(data);
        }

        return null;
    }
}
