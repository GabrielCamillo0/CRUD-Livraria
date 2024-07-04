/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package livraria.controller;

/**
 *
 * @author Pichau
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import livraria.model.Livro;

public class LivroControle {

    private static final String INSERIR_LIVRO = "INSERT INTO livros (titulo, autor, editora, categoria, descricao) VALUES (?, ?, ?, ?, ?)";
    private static final String BUSCAR_TODOS_LIVROS = "SELECT * FROM livros";
    private static final String ATUALIZAR_LIVRO = "UPDATE livros SET titulo=?, autor=?, editora=?, categoria=?, descricao=? WHERE id=?";
    private static final String EXCLUIR_LIVRO = "DELETE FROM livros WHERE id=?";

    public void inserirLivro(Livro livro) throws SQLException {
        try (Connection conexao = ConexaoDB.conectar(); PreparedStatement ps = conexao.prepareStatement(INSERIR_LIVRO)) {
            ps.setString(1, livro.getTitulo());
            ps.setString(2, livro.getAutor());
            ps.setString(3, livro.getEditora());
            ps.setString(4, livro.getCategoria());
            ps.setString(5, livro.getDescricao());
            ps.executeUpdate();
        }
    }

    public List<Livro> buscarTodosLivros() throws SQLException {
        List<Livro> livros = new ArrayList<>();
        try (Connection conexao = ConexaoDB.conectar(); PreparedStatement ps = conexao.prepareStatement(BUSCAR_TODOS_LIVROS); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Livro livro = new Livro(
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getString("editora"),
                        rs.getString("categoria"),
                        rs.getString("descricao")
                );
                livro.setId(rs.getInt("id"));
                livros.add(livro);
            }
        }
        return livros;
    }

    public void atualizarLivro(Livro livro) throws SQLException {
        try (Connection conexao = ConexaoDB.conectar(); PreparedStatement ps = conexao.prepareStatement(ATUALIZAR_LIVRO)) {
            ps.setString(1, livro.getTitulo());
            ps.setString(2, livro.getAutor());
            ps.setString(3, livro.getEditora());
            ps.setString(4, livro.getCategoria());
            ps.setString(5, livro.getDescricao());
            ps.setInt(6, livro.getId());
            ps.executeUpdate();
        }
    }

    public void excluirLivro(int id) throws SQLException {
        try (Connection conexao = ConexaoDB.conectar(); PreparedStatement ps = conexao.prepareStatement(EXCLUIR_LIVRO)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
