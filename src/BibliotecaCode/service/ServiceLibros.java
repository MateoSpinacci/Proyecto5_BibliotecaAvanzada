package BibliotecaCode.service;

import BibliotecaCode.model.Biblioteca;
import BibliotecaCode.model.EjemplarLibro;
import BibliotecaCode.model.Libro;
import java.util.Iterator;


public class ServiceLibros {

        private final Biblioteca biblioteca;

        public ServiceLibros(Biblioteca biblioteca) {
            this.biblioteca = biblioteca;
        }

        public void agregarLibro(String titulo, String autor, String editorial, int anio, int stock) {
            Libro libro = new Libro(titulo, autor, editorial, anio);
            biblioteca.agregarLibro(libro);
            this.agregarEjemplar(libro, stock);
        }

        public void agregarEjemplar(Libro libro, int stock) {
            for (int i=0; i<stock; i++) {
                EjemplarLibro ejemplar = new EjemplarLibro(libro);
                biblioteca.agregarEjemplar(ejemplar);
            }
        }

        public void eliminarLibro(int indice, int stockAEliminar) {
            Libro libro = biblioteca.getLibros().get(indice);
            String titulo = biblioteca.getLibros().get(indice).getTitulo();
            Iterator<EjemplarLibro> it = biblioteca.getEjemplares().iterator();
            int cantVueltas = 0;
            while (it.hasNext() && cantVueltas < stockAEliminar) {
                EjemplarLibro ejemplar = it.next();
                if (ejemplar.getLibro().getTitulo().equals(titulo) && ejemplar.isDisponible()) {
                    cantVueltas++;
                    it.remove();
                    libro.disminuirStock();
                }
            }
            if (biblioteca.getLibros().get(indice).getStock() == 0) {
                biblioteca.eliminarLibro(indice);
            }
        }
}
