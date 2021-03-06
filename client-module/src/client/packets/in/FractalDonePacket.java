package client.packets.in;

import client.packets.Packet;
import client.packets.PacketIn;
import client.packets.PacketType;

/**
 * @author Edwin
 */
public class FractalDonePacket extends PacketIn {

    private int level = 0;
    private boolean allowMode;

    public FractalDonePacket(String data) {
        super(PacketType.FRACTALDONE);

        String[] args = data.split(Packet.separator);

        if (args.length < 2) {
            throw new IllegalArgumentException("Invalid packet format");
        }

        try {
            this.level = Integer.parseInt(args[0]);
            allowMode = Integer.parseInt(args[1]) == 1;
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Invalid packet data");
        }
    }

    public int getLevel() {
        return this.level;
    }

    public boolean doAllowMode() {
        return this.allowMode;
    }
}
