package BibliotecaCode.model;

public class EjemplarLibro {

    private final int id;
    private static int idSiguiente = 100;
    private final Libro libro;
    private boolean disponible;
    private Usuario usuario;

    public EjemplarLibro(Libro libro) {
        this.libro = libro;
        this.id = idSiguiente++;
        this.disponible = true;
        libro.aumentarStock();
    }

    @Override
    public String toString() {
        String nombreUsuario = "No Prestado";
        if (usuario != null) {
            nombreUsuario = usuario.getNombre();
        }
        return "Libro: " + libro.getTitulo() + ", ID: " + this.id + ", Prestado a: " + nombreUsuario;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public void setUsuarioPrestado(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getId() {
        return id;
    }

    public Libro getLibro() {
        return libro;
    }
}
