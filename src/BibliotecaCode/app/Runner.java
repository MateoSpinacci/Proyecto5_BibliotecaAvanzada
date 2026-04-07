package BibliotecaCode.app;

import BibliotecaCode.model.Biblioteca;
import BibliotecaCode.model.Libro;
import BibliotecaCode.model.Usuario;
import BibliotecaCode.service.ServiceGeneral;
import BibliotecaCode.service.ServiceLibros;
import BibliotecaCode.service.ServiceUsuarios;
import BibliotecaCode.service.Verificadores;

import java.util.Scanner;

public class Runner {

    static Scanner sc = new Scanner(System.in);
    static Biblioteca biblioteca = new Biblioteca();
    static ServiceLibros serviceLibros = new ServiceLibros(biblioteca);
    static ServiceUsuarios serviceUsuarios = new ServiceUsuarios(biblioteca);
    static ServiceGeneral serviceGeneral = new ServiceGeneral(biblioteca);
    static Verificadores verificador = new Verificadores();

    static void main(String[] args) {
        int opcion = -1;
        do {
            opcion = verificador.verificarEntero(sc, menu());
            if (opcion < 0 || opcion > 11) {
                System.out.println("Ingrese un número válido");
                continue;
            }
            ejecutar(opcion);
        } while (opcion != 0);
    }

    public static String menu() {
        return """
                
                ----BIENVENIDO----
                1. Agregar Libro.
                2. Eliminar Libro.
                3. Agregar Usuario.
                4. Eliminar Usuario.
                5. Buscar Libro.
                6. Listar Libros.
                7. Prestar Libro.
                8. Devolver Libro.
                9. Buscar Usuario.
                10. Listar Usuarios.
                11. Listar Libros Por Usuario.
                0. Salir
                Opción:
                """;
    }

    public static void ejecutar(int opcion) {
        switch (opcion) {
            case 1:
                String titulo = verificador.verificarVacio(sc,"Ingrese titulo: ");
                if (biblioteca.getLibros().contains(new Libro(titulo))) {
                    String agrega = verificador.verificarVacio(sc, "Este libro ya existe, desea agregar stock del mismo? (Si/No): ").toLowerCase().trim();
                    switch (agrega) {
                        case "si":
                            int stock = verificador.verificarEntero(sc, "Ingrese stock: ");
                            Libro libro = biblioteca.getLibros().get(biblioteca.getLibros().indexOf(new Libro(titulo)));
                            serviceLibros.agregarEjemplar(libro, stock);
                            System.out.println("Stock agregado exitosamente, stock actual: " + libro.getStock());
                            break;
                        case "no":
                            System.out.println("Volviendo");
                            break;
                    }
                } else {
                    String autor = verificador.verificarVacio(sc, "Ingrese autor: ");
                    String editorial = verificador.verificarVacio(sc, "Ingrese editorial: ");
                    int anio = verificador.verificarEntero(sc, "Ingrese año: ");
                    int stock = verificador.verificarEntero(sc, "Ingrese stock: ");
                    serviceLibros.agregarLibro(titulo, autor, editorial, anio, stock);
                    System.out.println("Libro agregado exitosamente");
                }
                break;
            case 2:
                String libroAEliminar = verificador.verificarVacio(sc, "Ingrese el titulo del libro que desea eliminar: ");
                int indice = biblioteca.buscarIndiceLibro(libroAEliminar);
                if (indice == -1) {
                    System.out.println("Libro no encontrado");
                } else {
                    int stockDisponible = biblioteca.getLibros().get(indice).getStock();
                    System.out.println("\nEl libro " + libroAEliminar + " cuenta con un stock de: " + stockDisponible);
                    int stockAEliminar = verificador.verificarEntero(sc, "Ingrese la cantidad que desea eliminar: ");
                    while (0 >= stockAEliminar || stockAEliminar > stockDisponible) {
                        System.out.println("Error, la cantidad a eliminar debe esta entre 1 y " + stockDisponible);
                        System.out.println("Ingrese la cantidad que desea eliminar: ");
                        stockAEliminar = verificador.verificarEntero(sc, "Ingrese la cantidad que desea eliminar: ");
                    }
                    serviceLibros.eliminarLibro(indice, stockAEliminar);
                    System.out.println("Libros eliminados exitosamente");
                }

                break;
            case 3:
                String apellido = verificador.verificarVacio(sc, "Ingrese su apellido: ");
                String nombre = verificador.verificarVacio(sc, "Ingrese su nombre: ");
                int id = serviceUsuarios.agregarUsuario(nombre, apellido);
                System.out.println("Usuario agregado exitosamente, su ID es: " + id);
                break;
            case 4:
                int IdAEliminar = verificador.verificarEntero(sc, "Ingrese el ID del usuario a eliminar: ");
                serviceUsuarios.eliminarUsuario(IdAEliminar);
                break;
            case 5:
                String tituloABuscar = verificador.verificarVacio(sc, "Ingrese el titulo del libro a buscar: ");
                Libro libro = biblioteca.buscarLibro(tituloABuscar);
                if (libro != null) {
                    System.out.println("Libro encontrado exitosamente");
                    System.out.println(libro);
                } else {
                    System.out.println("Libro no encontrado");
                }
                break;
            case 6:
                if (!biblioteca.getLibros().isEmpty()) {
                    System.out.println("Libros de la biblioteca:\n");
                    System.out.println("--------------");
                    for (Libro mostrarLibro : biblioteca.getLibros()) {
                        System.out.println(mostrarLibro);
                        System.out.println("--------------");
                    }
                } else {
                    System.out.println("Todavía no hay libros cargados en el sistema");
                }
                break;
            case 7:
                int idAPrestar = verificador.verificarEntero(sc, "Ingrese el ID del usuario a prestar: ");
                Usuario usuario = biblioteca.buscarUsuario(idAPrestar);
                if (usuario != null) {
                    String tituloAPrestar = verificador.verificarVacio(sc, "Ingrese el titulo del libro a prestar: ");
                    if (biblioteca.buscarIndiceLibro(tituloAPrestar) != -1) {
                        serviceGeneral.prestarLibro(usuario, tituloAPrestar);
                        System.out.println("Libro prestado exitosamente");
                    } else {
                        System.out.println("Libro no encontrado");
                    }
                } else {
                    System.out.println("Usuario no encontrado");
                }
                break;
            case 8:
                int IdDevuelve = verificador.verificarEntero(sc, "Ingrese el ID del usuario que devuelve el libro: ");
                Usuario usuarioDevuelve = biblioteca.buscarUsuario(IdDevuelve);
                if (usuarioDevuelve != null) {
                    usuarioDevuelve.infoLibros();
                    System.out.println();
                    int IDDevolver = verificador.verificarEntero(sc, "Ingrese el ID del libro que desea devolver: ");
                    serviceGeneral.devolverLibro(usuarioDevuelve, IDDevolver);
                } else {
                    System.out.println("Usuario no encontrado");
                }
                break;
            case 9:
                int idBuscar = verificador.verificarEntero(sc, "Ingrese el ID del usuario a buscar: ");
                Usuario usuarioBuscado = biblioteca.buscarUsuario(idBuscar);
                if (usuarioBuscado != null) {
                    System.out.println("Usuario encontrado exitosamente");
                    System.out.println(usuarioBuscado);
                } else {
                    System.out.println("Usuario no encontrado");
                }
                break;
            case 10:
                if (!biblioteca.getUsuarios().isEmpty()) {
                    System.out.println("Usuarios de la biblioteca:\n");
                    System.out.println("--------------");
                    for (Usuario mostrarUsuario : biblioteca.getUsuarios()) {
                        System.out.println(mostrarUsuario);
                        System.out.println("--------------");
                    }
                } else {
                    System.out.println("Todavía no hay usuarios cargados en el sistema");
                }
                break;
            case 11:
                int idLibros = verificador.verificarEntero(sc, "Ingrese el ID del usuario cuyos libros desea buscar: ");
                serviceGeneral.mostrarLibroUsuario(idLibros);
                break;
        }
    }
}
