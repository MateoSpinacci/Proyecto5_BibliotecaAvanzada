package BibliotecaCode.model;

import java.util.ArrayList;

public class Usuario {

    private final String nombre;
    private final String apellido;
    private final int id;
    private static int idSiguiente = 10000;
    private final ArrayList<EjemplarLibro> libros = new ArrayList<>();

    public Usuario(int id) {
        this.id = id;
        this.nombre = "";
        this.apellido = "";
    }

    public Usuario(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.id = idSiguiente++;
    }

    public void agregarLibro(EjemplarLibro ejemplarLibro) {
        libros.add(ejemplarLibro);
    }

    @Override
    public String toString() {
        return "Nombre: " + this.nombre + "\nApellido: " + this.apellido + "\nId: " + this.id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj == null) || (getClass() != obj.getClass())) {
            return false;
        }
        Usuario usuario = (Usuario) obj;
        return usuario.getId() == this.id;
    }

    public ArrayList<EjemplarLibro> getLibros() {
        return libros;
    }

    public void infoLibros() {
        if (libros.isEmpty()) {
            return;
        }
        for (EjemplarLibro ejemplarLibro : libros) {
            if (!ejemplarLibro.isDisponible()) {
                System.out.println(ejemplarLibro);
            }
        }
    }

    public String getApellido() {
        return apellido;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
}
