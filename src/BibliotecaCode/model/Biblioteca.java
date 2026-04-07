package BibliotecaCode.model;

import java.util.ArrayList;

public class Biblioteca {

    private final ArrayList<Libro> libros = new ArrayList<>();
    private final ArrayList<Usuario> usuarios =  new ArrayList<>();
    private final ArrayList<EjemplarLibro> ejemplares = new ArrayList<>();

    public void agregarLibro(Libro libro) {
        libros.add(libro);
    }

    public int buscarIndiceUsuario(int idUsuario) {
        return this.getUsuarios().indexOf(new Usuario(idUsuario));
    }

    public Usuario buscarUsuario(int id) {
        int indice = this.getUsuarios().indexOf(new Usuario(id));
        return (indice != -1) ? (this.getUsuarios().get(indice)) : (null);
    }

    public Libro buscarLibro(String titulo) {
        int indice = this.getLibros().indexOf(new Libro(titulo));
        return (indice != -1) ? (this.getLibros().get(indice)) : (null);
    }

    public int buscarIndiceLibro(String libroAEliminar) {
        return this.getLibros().indexOf(new Libro(libroAEliminar));
    }

    public void agregarEjemplar(EjemplarLibro ejemplar) {
        ejemplares.add(ejemplar);
    }

    public void eliminarLibro(int indice) {
        libros.remove(indice);
    }

    public int agregarUsuario(Usuario usuario) {
        usuarios.add(usuario);
        return usuario.getId();
    }

    public void eliminarUsuario(Usuario usuario) {
        usuarios.remove(usuario);
    }

    public ArrayList<Libro> getLibros() {
        return libros;
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public ArrayList<EjemplarLibro> getEjemplares() {
        return ejemplares;
    }
}