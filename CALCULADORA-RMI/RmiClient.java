import java.rmi.*;
import java.rmi.registry.*;
import java.util.Scanner;
import java.net.*;

public class RmiClient {
	static public void main(String args[]) {
		ReceiveMessageInterface rmiServer;
		Registry registry;
		String serverAddress = args[0];
		String serverPort = args[1];
		String text = args[2];

		System.out.println("sending " + text + " to " + serverAddress + ":" + serverPort);
		System.out.println("\n********************CALCULADORA RMI*************************\n");
		try {
			// get the �registry�
			registry = LocateRegistry.getRegistry(serverAddress, (new Integer(serverPort)).intValue());
			// look up the remote object
			rmiServer = (ReceiveMessageInterface) (registry.lookup("rmiServer"));
			// call the remote method
			rmiServer.receiveMessage(text);
			Scanner scanner = new Scanner(System.in);
			int out, option, number1, number2, result;
			do {
				out = option = number1 = number2 = result = 0;
				System.out.println("\n");
				System.out.println("Digite o primeiro n�mero:");
				number1 = scanner.nextInt();
				System.out.println("Digite o segundo n�mero:");
				number2 = scanner.nextInt();
				System.out.println("Digite o n�mero referente a opera��o que desejada:");
				System.out.println("1-Somar");
				System.out.println("2-Subtra��o");
				System.out.println("3-Multiplica��o");
				System.out.println("4-Divis�o");
				option = scanner.nextInt();

				switch (option) {
				case 1:
					result = rmiServer.add(number1, number2);
					System.out.println("Resultado: " + result);
					System.out.println("\n");
					break;
				case 2:
					result = rmiServer.sub(number1, number2);
					System.out.println("Resultado: " + result);
					System.out.println("\n\n");
					break;
				case 3:
					result = rmiServer.mul(number1, number2);
					System.out.println("Resultado: " + result);
					System.out.println("\n");
					break;
				case 4:
					result = rmiServer.div(number1, number2);
					System.out.println("Resultado: " + result);
					System.out.println("\n");
					break;
				default:
					System.out.println("Op��o inv�lida.");
					break;
				}
				System.out.println("Continuar?");
				System.out.println("1-Sim");
				System.out.println("Qualquer outro n�mero - N�o");
				out = scanner.nextInt();
				System.out.println("\n");

			} while (out != 1);
			System.out.println("At� mais...");
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
