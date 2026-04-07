package BibliotecaCode.model;

public class Libro {

    private final String titulo;
    private final String autor;
    private final String editorial;
    private final int anio;
    private int stock;

    public Libro(String titulo) {
        this.titulo = titulo;
        this.autor = "";
        this.editorial = "";
        this.anio = 0;
    }

    public Libro(String titulo, String autor, String editorial, int anio) {
        this.anio = anio;
        this.autor = autor;
        this.editorial = editorial;
        this.titulo = titulo;
    }

    @Override
    public String toString() {
        return "Titulo: " + this.titulo + "\nAutor: " + this.autor + "\nEditorial: " + this.editorial + "\nAño: "
                + this.anio + "\nStock: " + this.stock;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj == null) || (getClass() != obj.getClass())) {
            return false;
        }
        Libro libro = (Libro) obj;
        return this.titulo.equals(libro.titulo);
    }

    public String getTitulo() {
        return titulo;
    }

    public void aumentarStock() {
        this.stock++;
    }

    public void disminuirStock() {
        this.stock--;
    }

    public int getStock() {
        return stock;
    }
}
