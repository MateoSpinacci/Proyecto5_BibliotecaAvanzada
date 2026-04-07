package BibliotecaCode.service;

import BibliotecaCode.model.Biblioteca;
import BibliotecaCode.model.EjemplarLibro;
import BibliotecaCode.model.Usuario;

public class ServiceGeneral {
    private final Biblioteca biblioteca;

    public ServiceGeneral(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }

    public void prestarLibro(Usuario usuario, String tituloLibro) {
        for (EjemplarLibro ejemplar : biblioteca.getEjemplares()) {
            if (ejemplar.getLibro().getTitulo().equals(tituloLibro) && ejemplar.isDisponible()) {
                ejemplar.setDisponible(false);
                ejemplar.setUsuarioPrestado(usuario);
                ejemplar.getLibro().disminuirStock();
                usuario.agregarLibro(ejemplar);
                System.out.println("ID del libro prestado: " + ejemplar.getId());
                return;
            }
        }
        System.out.println("No hay libros disponibles");
    }

    public void devolverLibro(Usuario usuarioDevuelve, int IDDevolver) {
        if (usuarioDevuelve.getLibros().isEmpty()) {
            System.out.println("El usuario " + usuarioDevuelve.getNombre() + " no tiene libros prestados");
        } else {
            for (EjemplarLibro ejemplar : usuarioDevuelve.getLibros()) {
                if (ejemplar.getId() == IDDevolver && !ejemplar.isDisponible()) {
                    ejemplar.setDisponible(true);
                    ejemplar.setUsuarioPrestado(null);
                    ejemplar.getLibro().aumentarStock();
                    usuarioDevuelve.getLibros().remove(ejemplar);
                    System.out.println("El libro " + ejemplar.getId() + " se ha devuelta exitosamente.");
                    return;
                }
            }
            System.out.println("El ID no coincide con ningún libro prestado a este usuario");
        }
    }

    public void mostrarLibroUsuario(int id) {
        Usuario usuario = biblioteca.buscarUsuario(id);
        if (usuario == null) {
            System.out.println("Usuario no encontrado");
        } else {
            if (!usuario.getLibros().isEmpty()) {
                for (EjemplarLibro ejemplar : usuario.getLibros()) {
                    System.out.println(ejemplar);
                }
            } else {
                System.out.println("El usuario " + usuario.getNombre() + " no tiene libros en posesión");
            }
        }
    }
}
