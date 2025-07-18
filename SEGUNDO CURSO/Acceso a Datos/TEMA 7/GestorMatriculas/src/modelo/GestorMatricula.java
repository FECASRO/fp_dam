/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MCCLA
 */
public class GestorMatricula {
    private Connection conexion;

    public GestorMatricula() {
        conexion = ConexionBD.conectar();
    }

    // Listar todas las matrículas
    public List<Matricula> listarMatriculas() {
        List<Matricula> lista = new ArrayList<>();
        String sql = "SELECT * FROM matriculas";
        try (Statement stmt = conexion.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Matricula(
                        rs.getString("DNI"),
                        rs.getString("NombreModulo"),
                        rs.getString("Curso"),
                        rs.getDouble("Nota")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar matrículas: " + e.getMessage());
        }
        return lista;
    }

    // Listar matrículas de un alumno por DNI
    public List<Matricula> listarMatriculasPorDNI(String dni) {
        List<Matricula> lista = new ArrayList<>();
        String sql = "SELECT * FROM matriculas WHERE DNI = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, dni);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                lista.add(new Matricula(
                        rs.getString("DNI"),
                        rs.getString("NombreModulo"),
                        rs.getString("Curso"),
                        rs.getDouble("Nota")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar por DNI: " + e.getMessage());
        }
        return lista;
    }

    // Agregar una nueva matrícula
    public void agregarMatricula(Matricula matricula) {
        String sql = "INSERT INTO matriculas (DNI, NombreModulo, Curso, Nota) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, matricula.getDni());
            stmt.setString(2, matricula.getNombreModulo());
            stmt.setString(3, matricula.getCurso());
            stmt.setDouble(4, matricula.getNota());
            stmt.executeUpdate();
            System.out.println("Matrícula añadida con éxito.");
        } catch (SQLException e) {
            System.out.println("Error al añadir matrícula: " + e.getMessage());
        }
    }
}