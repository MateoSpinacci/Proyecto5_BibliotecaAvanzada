package BibliotecaCode.service;

import BibliotecaCode.model.Biblioteca;
import BibliotecaCode.model.Usuario;
public class ServiceUsuarios {

    private final Biblioteca biblioteca;

    public ServiceUsuarios(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }

    public int agregarUsuario(String nombre, String apellido) {
        return biblioteca.agregarUsuario(new Usuario(nombre, apellido));
    }

    public void eliminarUsuario(int idUsuario) {
        Usuario usuario = biblioteca.buscarUsuario(idUsuario);
        if (usuario == null) {
            System.out.println("Usuario no encontrado");
        } else {
            if (usuario.getLibros().isEmpty()) {
                System.out.println("Usuario " + usuario.getNombre() + " " +
                        usuario.getApellido() + " eliminado exitosamente");
                biblioteca.eliminarUsuario(usuario);
            } else {
                System.out.println("No se puede eliminar al usuario debido a que tiene libros prestados.");
            }
        }
    }
}
