package MP;

import MP.Packet.Packet0LoginRequest;
import MP.Packet.Packet1LoginAnswer;
import MP.Packet.Packet2Message;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.minlog.Log;

public class ClientNetworkListener extends Listener {
	private Client client;

	public void init(Client client) {
		this.client = client;
	}

	public void connected(Connection c) {
		Log.info("You have connected.");
		client.sendTCP(new Packet0LoginRequest());
	}

	public void disconnected(Connection c) {
		Log.info("You have disconnected.");
		client.close();
	}

	public void received(Connection c, Object o) {
		if (o instanceof Packet1LoginAnswer) {
			boolean answer = ((Packet1LoginAnswer) o).accepted;

			if (answer) {
				Log.info("The server has accepted you!");
			} else {
				c.close();
			}
		} // End of Packet1
	}
}