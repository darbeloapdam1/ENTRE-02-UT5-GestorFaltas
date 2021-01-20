/**
 * Un objeto de esta clase guarda la información de un estudiante
 *
 */
public class Estudiante {
    private final static String SEPARADOR = ",";
    private String nombre;
    private String apellidos;
    private int faltasNoJustificadas;
    private int faltasJustificadas;

    /**
     *  
     *  Inicializa los atributos a partir de la información recibida
     *  Esta información se encuentra en lineaDatos
     *  (ver enunciado) 
     *  
     */
    public Estudiante(String lineaDatos) {
        String[] datos = lineaDatos.split(SEPARADOR);
        this.nombre = nombreCompuesto(datos[0].trim());
        this.apellidos = datos[1].trim().toUpperCase();
        this.faltasNoJustificadas = Integer.parseInt(datos[2].trim());
        this.faltasJustificadas = Integer.parseInt(datos[3].trim());

    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  nombre   nombre del estudiante
     * @return     nombre del estudiante con formato correcto
     */
    private String nombreCompuesto(String nombre)
    {
        if(nombre.contains(" ")){
            String[] nombreSeparado = nombre.split(" ");
            String nombreCorrecto = "";
            for(int i = 0; i < (nombreSeparado.length - 1); i++){
                nombreCorrecto += nombreSeparado[i].substring(0,1).toUpperCase() + ".";
            }
            String primeraLetra = nombreSeparado[nombreSeparado.length - 1].substring(0,1).toUpperCase();
            String restoCadena = nombreSeparado[nombreSeparado.length - 1].substring(1);
            nombreCorrecto += primeraLetra + restoCadena;
            return nombreCorrecto;
        }else{
            String primeraLetra2 = nombre.substring(0,1).toUpperCase();
            String restoCadena2 = nombre.substring(1).toLowerCase();
            String nombreCorrecto2 = "";
            nombreCorrecto2 += primeraLetra2 + restoCadena2;
            return nombreCorrecto2;
        }
    }

    /**
     * accesor para el nombre completo
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * mutador para el nombre
     *  
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * accesor para los apellidos
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     *  mutador para los apellidos
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * accesor para el nº de faltas no justificadas
     */
    public int getFaltasNoJustificadas() {
        return faltasNoJustificadas;
    }

    /**
     * mutador para el nº de faltas no justificadas
     */
    public void setFaltasNoJustificadas(int faltasNoJustificadas) {
        this.faltasNoJustificadas = faltasNoJustificadas;
    }

    /**
     * accesor para el nº de faltas justificadas
     */
    public int getFaltasJustificadas() {
        return faltasJustificadas;
    }

    /**
     *  mutador para el nº de faltas justificadas
     */
    public void setFaltasJustificadas(int faltasJustificadas) {
        this.faltasJustificadas = faltasJustificadas;
    }

    /**
     *  se justifican n faltas que hasta el momento eran injustificadas
     *  Se asume n correcto
     */
    public void justificar(int n) {
        this.faltasNoJustificadas -= n;
        this.faltasJustificadas += n;
    }

    /**
     * Representación textual del estudiante
     * (ver enunciado)
     */
    public String toString() {
        String apercibimientos = "";
        if(this.faltasNoJustificadas >= 30){
            apercibimientos += "DIEZ VEINTE TREINTA";
        }else if(this.faltasNoJustificadas >= 20){
            apercibimientos += "DIEZ VEINTE";
        }else if(this.faltasNoJustificadas >= 10){
            apercibimientos += "DIEZ";
        }else{
            apercibimientos += "Sin apercibimientos";
        }
        String apellidosNombre = apellidos + "," + nombre;
        String str = String.format("%-24s %-35s %-25s %-35s %-25s %-35s %-25s %-35s","Apellidos y Nombre:",apellidosNombre,"\nFaltas No Justificadas:", faltasNoJustificadas,
        "\nFaltas Justificadas:", faltasJustificadas, "\nApercibimientos:",apercibimientos);
        return str;
    }

    public static void main(String[] args) {
        Estudiante e1 = new Estudiante("  ander ibai , Ruiz Sena , 12, 23");
        System.out.println(e1);
        System.out.println();
        Estudiante e2 = new Estudiante(
                " pedro josé andrés  ,  Troya Baztarrica , 42, 6 ");
        System.out.println(e2);
        System.out.println();
        Estudiante e3 = new Estudiante("  Javier  ,  Suescun  Andreu , 39, 9 ");
        System.out.println(e3);
        System.out.println();
        Estudiante e4 = new Estudiante("julen, Duque Puyal, 5, 55");
        System.out.println(e4);
        System.out.println();

        System.out.println("---------------------------------");
        e1.justificar(3);
        System.out.println(e1);
        System.out.println();

        System.out.println("---------------------------------");

        e3.justificar(12);
        System.out.println(e3);
        System.out.println();

    }

}
