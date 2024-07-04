/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package livraria.model;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Pichau
 */
public class LivroTableModel extends AbstractTableModel {
    private final List<Livro> livros;
    private final String[] colunas = {"ID", "Título", "Autor", "Editora", "Categoria", "Descrição"};

    public LivroTableModel(List<Livro> livros) {
        this.livros = livros;
    }

    @Override
    public int getRowCount() {
        return livros.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Livro livro = livros.get(rowIndex);
        switch (columnIndex) {
            case 0: return livro.getId();
            case 1: return livro.getTitulo();
            case 2: return livro.getAutor();
            case 3: return livro.getEditora();
            case 4: return livro.getCategoria();
            case 5: return livro.getDescricao();
            default: return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }
}
