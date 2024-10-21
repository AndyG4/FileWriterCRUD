import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;


public class Main{
    static ArrayList<Persona> lista = new ArrayList<>();

    public static void main(String[] args){
        ListaMetodos listaMetodos = new ListaMetodos();
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in));){
            String personita;
            while(!((personita= br.readLine()).equalsIgnoreCase("exit"))){
                int edad = Integer.parseInt(br.readLine());
                lista.add(new Persona(personita,edad));
            }
            listaMetodos.imprimir(lista);
            try(FileWriter fileWriter = new FileWriter("C:\\Users\\elmic\\OneDrive\\Escritorio\\listaDePersonas.txt");){
                for(Persona persona: lista){
                    fileWriter.write(persona.toString()+"\n");
                }
                fileWriter.flush();
            }

            listaMetodos.reemplazarNombre(lista);
            listaMetodos.imprimir(lista);
            listaMetodos.eliminar(lista);
            listaMetodos.buscar(lista);
        }catch (IOException e){
            e.printStackTrace();
        }


    }

}
class Persona{
    String Name;
    int age;

    Persona(String nombre, int edad){
        this.Name = nombre;
        this.age = edad;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public String getNombre() {return this.Name;}

    public void setName(String name) {
        Name = name;
    }

    @Override
    public String toString(){
        return "Nombre: " + this.Name + ", Edad: " + this.age;
    }
}
class ListaMetodos{
    ListaMetodos(){}

    void imprimir(ArrayList<Persona> lista){
        for(Persona p : lista){
            System.out.println(p.toString());
        }
    }
    void buscar(ArrayList<Persona> lista){
        Scanner sc = new Scanner(System.in);
        String nombre = sc.nextLine();
        for(Persona p : lista){
            if(p.getNombre().equalsIgnoreCase(nombre) || p.getNombre().contains(nombre)){
                System.out.println(p.toString());
            }
        }
    }

    void reemplazarNombre(ArrayList<Persona> lista)  {
       Scanner sc = new Scanner(System.in);
       Scanner sc2= new Scanner(System.in);
       String nombre = sc.nextLine();
       String nombre2 = sc2.nextLine();
       for(Persona p : lista){
           if(p.getNombre().equalsIgnoreCase(nombre) || p.getNombre().contains(nombre)){
               p.setName(nombre2);
           }
       }
    }
    void eliminar(ArrayList<Persona> list) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce el nombre a eliminar:");
        String nombre = sc.nextLine();
        boolean eliminado = false;

        // Usamos un Iterator para evitar el ConcurrentModificationException
        var iterator = list.iterator();
        while (iterator.hasNext()) {
            Persona p = iterator.next();
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                iterator.remove();  // Eliminamos de forma segura
                eliminado = true;
                System.out.println("Se ha eliminado a " + p.getNombre());
            }
        }

        if (!eliminado) {
            System.out.println("No se encontró a la persona con el nombre " + nombre);
        } else {
            System.out.println("Lista actualizada con éxito:");
            imprimir(list);  // Imprime la lista actualizada
        }
    }

}
