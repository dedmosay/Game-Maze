package myProPost;

public class Main {
	 public static void main(String[] args) {
	 Server server = Server.getServer();
	 Thread t = new Thread(server);
	 t.start();
	 }
	} 