package MP;

import MP.Packet.*;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.minlog.Log;

public class MPServer {
	private Server server;

	public MPServer(int serverPort) {
			server = new Server();
			registerPackets();
			server.addListener(new ServerNetworkListener());
			
			try {
			server.bind(serverPort);
			server.start();
			Log.info("Now listening for connections on port " + serverPort);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void registerPackets() {
		Kryo kryo = server.getKryo();
		kryo.register(Packet0LoginRequest.class);
		kryo.register(Packet1LoginAnswer.class);
		kryo.register(Packet2Message.class);
		kryo.register(Packet3SendCoordinates.class);
		kryo.register(Packet4UpdatePlayers.class);
	}
}