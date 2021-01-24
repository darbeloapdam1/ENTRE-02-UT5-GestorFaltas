import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Un objeto de esta clase permite registrar estudiantes de un
 * curso (leyendo la información de un fichero de texto) y 
 * emitir listados con las faltas de los estudiantes, justificar faltas, 
 * anular matrícula dependiendo del nº de faltas, .....
 * @author - Diego Arbeloa
 */
public class GestorFaltas {
    private Estudiante[] estudiantes;
    private int total;

    public GestorFaltas(int n) {
        estudiantes = new Estudiante[n];
    }

    /**
     * Devuelve true si el array de estudiantes está completo,
     * false en otro caso
     */
    public boolean cursoCompleto() {
        return total >= estudiantes.length;
    }

    /**
     *    Añade un nuevo estudiante solo si el curso no está completo y no existe ya otro
     *    estudiante igual (con los mismos apellidos). 
     *    Si no se puede añadir se muestra los mensajes adecuados 
     *    (diferentes en cada caso)
     *    
     *    El estudiante se añade de tal forma que queda insertado en orden alfabético de apellidos
     *    (de menor a mayor)
     *    !!OJO!! No hay que ordenar ni utilizar ningún algoritmo de ordenación
     *    Hay que insertar en orden 
     *    
     */
    public void addEstudiante(Estudiante nuevo) {
        if(!cursoCompleto()){
            if(buscarEstudiante(nuevo.getApellidos()) < 0){
                if(total == 0){
                    estudiantes[total] = nuevo;
                }else{
                    int pos = getPosicionDe(nuevo.getApellidos());
                    for(int i = total; i > pos; i--){
                        estudiantes[i] = estudiantes[i - 1];
                    }
                    estudiantes[pos] = nuevo;
                }
                total++;
            }else{
                System.out.println("Ya está registrado el estudiante " + nuevo.getApellidos() + " " + nuevo.getNombre()+ " en el curso");
            }
        }else{
            System.out.println("El curso ya está completo");
        }
    }

    /**
     * 
     *
     * @param  apellidos los apellidos del estudiante a colocar
     * @return  i/total devuelve la posicion del estudiante a colocar
     */
    private int getPosicionDe(String apellidos)
    {
        for(int i = 0; i < total; i++){
            if(estudiantes[i].getApellidos().compareTo(apellidos) > 0){
                return i;
            }
        }
        return total;
    }

    /**
     * buscar un estudiante por sus apellidos
     * Si está se devuelve la posición, si no está se devuelve -1
     * Es indiferente mayúsculas / minúsculas
     * Puesto que el curso está ordenado por apellido haremos la búsqueda más
     * eficiente
     *  
     */
    public int buscarEstudiante(String apellidos) {
        int izq = 0;
        int dch = total - 1;
        while(izq < dch){
            int mitad = (izq + dch) / 2;
            if(estudiantes[mitad].getApellidos().compareToIgnoreCase(apellidos) < 0){
                izq = mitad + 1;
            }else if(estudiantes[mitad].getApellidos().compareToIgnoreCase(apellidos) > 0){
                dch = mitad - 1;
            }else if(estudiantes[mitad].getApellidos().compareToIgnoreCase(apellidos) == 0){
                return mitad;
            }
        }
        return -1;
    }

    /**
     * Representación textual del curso
     * Utiliza StringBuilder como clase de apoyo.
     *  
     */
    public String toString() {
        StringBuilder sb = new StringBuilder("Relación de estudiantes (" + total + ")\n\n");
        for(int i = 0; i < total; i++){
            sb.append(estudiantes[i].toString() + "\n--------------------\n");
        }
        return sb.toString();
    }

    /**
     *  Se justifican las faltas del estudiante cuyos apellidos se proporcionan
     *  El método muestra un mensaje indicando a quién se ha justificado las faltas
     *  y cuántas
     *  
     *  Se asume todo correcto (el estudiante existe y el nº de faltas a
     *  justificar también)
     */
    public void justificarFaltas(String apellidos, int faltas) {
        int estudiante = getPosicionDe(apellidos) - 1;
        estudiantes[estudiante].justificar(faltas);
        System.out.println("Se han justificado " + faltas + " al alumno " + estudiantes[estudiante].getApellidos() + ", " + estudiantes[estudiante].getNombre());
    }

    /**
     * ordenar los estudiantes de mayor a menor nº de faltas injustificadas
     * si coinciden se tiene en cuenta las justificadas
     * Método de selección directa
     */
    public void ordenar() {
        for(int i = 0; i < total; i++){
            int posmin = i;
            for(int j = i + 1; j < total; j++){
                if(estudiantes[j].getFaltasNoJustificadas() > estudiantes[posmin].getFaltasNoJustificadas()){
                    posmin = j;
                }else if(estudiantes[j].getFaltasNoJustificadas() == estudiantes[posmin].getFaltasNoJustificadas()){
                    if(estudiantes[j].getFaltasJustificadas() > estudiantes[posmin].getFaltasJustificadas()){
                        posmin = j;
                    }
                }
            }
            Estudiante aux = estudiantes[posmin];
            estudiantes[posmin] = estudiantes[i];
            estudiantes[i] = aux;
        }
        System.out.println("Ordenado de > a < nº de faltas injustificadas");
    }

    /**
     * anular la matrícula (dar de baja) a 
     * aquellos estudiantes con 30 o más faltas injustificadas
     */
    public void anularMatricula() {
        for(int i = 0; i < total; i++){
            if(estudiantes[i].getFaltasNoJustificadas() >= 30){
                for(int j = i; j < total - 1; j++){
                    estudiantes[j] = estudiantes[j + 1];
                }
                total--;
                i--;
            }
        }
    }

    /**
     * Lee de un fichero de texto los datos de los estudiantes
     *   con ayuda de un objeto de la  clase Scanner
     *   y los guarda en el array. 
     */
    public void leerDeFichero() {
        Scanner sc = null;
        try {
            sc = new Scanner(new File("estudiantes.txt"));
            while (sc.hasNextLine()) {
                String linea = sc.nextLine();
                Estudiante estudiante = new Estudiante(linea);
                this.addEstudiante(estudiante);

            }

        }
        catch (IOException e) {
            System.out.println("Error al leer del fichero");
        }
        finally {
            if (sc != null) {
                sc.close();
            }
        }

    }

}
