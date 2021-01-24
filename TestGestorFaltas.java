/**
 * Punto de entrada a la aplicaci�n
 */
public class TestGestorFaltas {
    /**
     * Se acepta como argumento del main() el n� m�ximo de estudiantes
     * y una vez creado el gestor de faltas se muestra la informaci�n solicitada
     * (ver enunciado)
     */
    public static void main(String[] args) {
        if(args.length != 1){
            System.out.println("Error en argumentos");
            System.out.println("Sintaxis: java TestGestorFaltas <max_estudiantes>");
        }else{
            GestorFaltas curso = new GestorFaltas(Integer.parseInt(args[0]));
            curso.leerDeFichero();
            System.out.println(curso.toString());
            curso.justificarFaltas("IRISO FLAMARIQUE", 6);
            curso.ordenar();
            System.out.println(curso.toString());
            curso.anularMatricula();
            System.out.println(curso.toString());
        }
    }

}
