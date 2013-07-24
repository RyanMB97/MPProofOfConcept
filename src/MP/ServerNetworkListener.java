package MP;

import MP.Packet.Packet0LoginRequest;
import MP.Packet.Packet1LoginAnswer;
import MP.Packet.Packet2Message;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.minlog.Log;

public class ServerNetworkListener extends Listener {
	public void connected(Connection c) {
		Log.info("Someone has connected to the Server.");
	}

	public void disconnected(Connection c) {
		Log.info("Someone has disconnected from the Server.");
	}

	public void received(Connection c, Object o) {
		if (o instanceof Packet0LoginRequest) {
			Packet1LoginAnswer loginAnswer = new Packet1LoginAnswer();
			loginAnswer.accepted = true;
			c.sendTCP(loginAnswer);
		} // End of Packet0

		if (o instanceof Packet2Message) {
			String message = ((Packet2Message) o).message;
			Log.info("Message: " + message);
		} // End of Packet2
	}
}