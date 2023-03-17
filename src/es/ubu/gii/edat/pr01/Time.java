package es.ubu.gii.edat.pr01;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *  Clase que se encarga de calcular los tiempos de ejecucion de cada metodo para un numero distinto de elementos
 * @author Victor De Marco 
 * @author Diego Urbaneja
 *
 */
public class Time {



    public static void main(String[] args) throws InterruptedException { 

        List <Integer> lista2 = new ArrayList<>();
        
        Random rand = new Random();
        //Se repetira el bucle hasta que hayamos comprobado los tiempos de ejecucion con 100000 elementos dentro del array
        while ( lista2.size() < 100000) {

           
        	//Este bucle for sirve para ir añadiendo elementos al array de 100 en 100
                    for (int j = 0; j < 100; j++) {
                        lista2.add((int)rand.nextInt(9) + 1);
                    }

             //Aqui tranformamos nuestro Array en un vector 
            Object[] vector2 = lista2.toArray();


            long inicio = System.currentTimeMillis();
            //ElementoMayoritario.mayoritarioRecursivo(vector2);
            ElementoMayoritario.mayoritarioIterativo(vector2);
            long fin = System.currentTimeMillis();
            long tiempo1 =  ((fin - inicio));

            long inicio2 = System.currentTimeMillis();
            //ElementoMayoritario.mayoritarioIterativo(vector2);
            ElementoMayoritario.mayoritarioRecursivo(vector2);
            long fin2 = System.currentTimeMillis();

            long tiempo2 =  ((fin2 - inicio2));


            //System.out.println(" ITERATIVO: TAMAÑO DE MUESTRA = "+ vector2.length + " TARDA: " + tiempo +" segundos");
            //System.out.println(vector2.length + "; " +tiempo1 + "; " + tiempo2 );
            try (BufferedWriter escritor = new BufferedWriter(new FileWriter("prueba_Escritura2.csv", true))) {
                escritor.write(vector2.length + "; " + tiempo1 + "; " + tiempo2 + "\n");
            } catch (IOException e) {
                System.out.println("Ocurrió un error al escribir en el archivo: " + e.getMessage());
            }

        }
        System.out.println("Ha terminado");
         
         
    }
}
