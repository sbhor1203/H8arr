package ProcessModel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Scanner;

import PriorityQueueController.PriorityQueueJF;
import PriorityQueueController.VectorHeap;

public class Paciente {

    PriorityQueueJF<String> queue = new PriorityQueueJF<String>();
    VectorHeap<String> vector = new VectorHeap(null)<String>();
    Scanner scanner = new Scanner(System.in);
    int type=0;
    
    /** 
     * @param args
     * @throws Exception
     * @param st
     */
    public static void main(String[] args) throws Exception {
        Paciente app = new Paciente();
        app.start();
    }

    /**
     * initialize the program
     */
    public void start(){
        menu();
    }


    /**
     * metodo de utulizar
     */
    public void menu(){
        System.out.println("Bienvenido a este programa por favor escoja la manera en la que quiere utilizar el porograma");
        System.out.println("(1) Vector Heap");
        System.out.println("(2) Collection Framework");
        int respuestausuario = scanner.nextInt();

        type=respuestausuario;

        readFile();

        Menu();
    }


    
    public void readFile(){
        File file = new File("pacientes.txt");
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(file));
            
            String st;
            while ((st = br.readLine()) != null){
                String[] line = st.split(",");
                    String paciente = line[2]+","+line[0]+","+line[1];
                if(type==1){
                    vector.add(paciente);
                }else if(type==2){
                    queue.add(paciente);
                }
            }            
            if(type==1){
                int count = vector.size();
                String str[] = new String[count];
                str = vector.string(vector, count);
                str = vector.orderHeap(str, count);
                vector.VectorHeaps(str, count, vector);

            }
        } catch (IOException e) {
            System.out.println(e);
            System.out.println("ARCHIVO NO ENCONTRADO");
        }
    }


    /**
     * menu to view pacients or remove
     */
    public void Menu(){
        int resp=0;
        while(resp!=3){
            System.out.println("1. Ver a los Pacientes");
            System.out.println("2. Siguiente turno de pacientes");
            System.out.println("3. Salir");
            resp = scanner.nextInt();
            switch(resp){
                case 1:
                    verpaciente();
                    break;
                case 2:
                    remove();
                    break;
                case 3:
                    System.out.println("gracias por usar el programa");
                    break;
            }
        }
    }

    /**
     * view all the pacients
     */
    public void verpaciente(){
        int size = vector.size();
        if(type==1){
            
            for(int i=0;i<size;i++){
                System.out.println(vector.get(i));
            }
        }else{
            System.out.println(queue);
        }
    }

    /**
     * remove the first pacient
     */
    public void remove(){
        if (type==1){
            System.out.println(vector.remove());
        }else{
            if(!queue.isEmpty()){
                System.out.println(queue.pushDownRoot());
            }else{
                System.out.println("Error, la lista de pacientes está vacía");
            }
        }
    }
    
}
