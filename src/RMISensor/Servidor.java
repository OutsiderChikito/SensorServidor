/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package RMISensor;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Servidor extends UnicastRemoteObject implements Sensor{
    
    public Servidor() throws RemoteException{
        super();
    }
    
    @Override
    public void sensorTemperatura(int a) throws RemoteException{
        System.out.println(a+"°C");
        if(a == 30) restart();
    }
    
    public static void restart(){
        System.out.println("Temperatura maxima alcanzada, iniciando reinicio automatico.");
        sleep(500);
        for (int i = 0; i < 5; i++) {
            System.out.print(".");
            sleep(1250);
        }
        System.out.println("\nReinicio realizado correctamente");
    }
    
    public static void sleep(int milsegs){
        try {
                Thread.sleep(milsegs);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }
    
    public static void main(String[] args) {
        try {
            // Crear el objeto servidor
            Servidor servidor = new Servidor();

            // Registrar el servicio en el registro RMI
            Registry registry = LocateRegistry.createRegistry(8080);
            registry.bind("SensorService", servidor);

            System.out.println("Servidor listo.");
            System.out.println("Escuchando lecturas de temperatura:");
        } catch (Exception e) {
            System.out.println("Error en el servidor: " + e.getMessage());
        }
    }
}
