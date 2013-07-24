package MP;

import MP.Packet.Packet0LoginRequest;
import MP.Packet.Packet1LoginAnswer;
import MP.Packet.Packet2Message;
import MP.Packet.Packet3UpdatePlayers;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;

public class MPClient {
	public Client client;

	public MPClient(String serverIP, int serverPort) {
		client = new Client();
		registerPackets();
		ClientNetworkListener nl = new ClientNetworkListener();
		nl.init(client);
		client.start();

		try {
			client.connect(10000, serverIP, serverPort);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void registerPackets() {
		Kryo kryo = client.getKryo();
		kryo.register(Packet0LoginRequest.class);
		kryo.register(Packet1LoginAnswer.class);
		kryo.register(Packet2Message.class);
		kryo.register(Packet3UpdatePlayers.class);
	}
}