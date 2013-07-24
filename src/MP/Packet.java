package MP;

public class Packet {
	
	public static class Packet0LoginRequest {  }
	public static class Packet1LoginAnswer { boolean accepted = false; }
	public static class Packet2Message { String message; }
	public static class Packet3SendCoordinates { int x, y, clientID; }
	public static class Packet4UpdatePlayers {  }
}