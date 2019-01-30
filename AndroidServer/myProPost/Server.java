package myProPost;

import java.io.IOException;
import java.net.ServerSocket;

public class Server implements Runnable {
	 /*
	 * ���������� ������� Singleton
	 * {@link https://en.wikipedia.org/wiki/Singleton_pattern}
	 */
	 private static volatile Server instane = null;
	 /* ����, �� ������� ������ ��������� ���������� */
	 private final int SERVER_PORT = 8080;
	 /* �����, ������� ������������ ���������� �� ������� */
	 private ServerSocket serverSoket = null;
	 private Server() {}
	 public static Server getServer() {
	 if (instane == null) {
	 synchronized (Server.class) {
	 if (instane == null) {
	 instane = new Server();
	 }
	 }
	 }
	 return instane;
	 }

	 @Override
	 public void run() {
	 try {
	 /* ������� ��������� �����, ������� ��������� ���������� */
	 serverSoket = new ServerSocket(SERVER_PORT);
	 System.out.println("Start server on port: "+SERVER_PORT);
	 /*
	 * ����� ������ ���������� �� ������
	 */
	 while(true) {
	 ConnectionWorker worker = null;
	 try {
	 /* ���� ������ ���������� */
	 worker = new ConnectionWorker(serverSoket.accept());
	 System.out.println("Get client connection");
	 /* ��������� ����� �����, � ������� �������������� ���������� */
	 Thread t = new Thread(worker);
	 t.start();
	 } catch (Exception e) {
	 System.out.println("Connection error: "+e.getMessage());
	 }
	 }
	 } catch (IOException e) {
	 System.out.println("Cant start server on port "+SERVER_PORT+":"+e.getMessage());
	 } finally {
		 if (serverSoket != null) {
			 try {
			 serverSoket.close();
			 } catch (IOException e) {
			 }
			 }
			 }
			 }
	 }
