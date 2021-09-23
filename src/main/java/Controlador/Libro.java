package Controlador;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.swing.JOptionPane;

import Modelo.LibroDAO;
import Modelo.LibroDTO;

/**
 * Servlet implementation class Libro
 */
@WebServlet("/Libro")
@MultipartConfig
public class Libro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Libro() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		LibroDAO libDao=new LibroDAO();
		if(request.getParameter("insertar")!=null) {
			
			String codigo,titulo,editorial,autor;
			int paginas=0;
			codigo=request.getParameter("isbn");
			titulo=request.getParameter("titulo");
			editorial=request.getParameter("editorial");
			autor=request.getParameter("autor");
			if(!request.getParameter("paginas").equals("")) {
			paginas=Integer.parseInt(request.getParameter("paginas"));
			}
			LibroDTO lib=new LibroDTO(codigo,titulo,editorial,autor,paginas);
			if(libDao.Inserta_Libro(lib)) {
				JOptionPane.showMessageDialog(null, "Registro del Libro Exitoso...");
				response.sendRedirect("Libros.jsp");
			}else {
				JOptionPane.showMessageDialog(null, "No se Registro el Libro.");
				response.sendRedirect("Libros.jsp");
			}
			
		}
		
		if(request.getParameter("consultar")!=null) {
			
			String codigo,titulo,editorial,autor;
			int paginas;
			codigo=request.getParameter("codigo");
			LibroDTO lib=libDao.Buscar_Libro(codigo); 
			if(lib!=null) {
			codigo=lib.getIsbn();
			titulo=lib.getTitulo();
			editorial=lib.getEditorial();
			autor=lib.getAutor();
			paginas=lib.getNo_paginas();
			response.sendRedirect("Libros.jsp?codigo="+codigo+"&&titulo="+titulo+"&&editorial="+
			editorial+"&&autor="+autor+"&&paginas="+paginas);
			}else {
				response.sendRedirect("Libros.jsp?men=El Libro no se encontro.");
			}
			
		}
		
		if(request.getParameter("actualizar")!=null) {
			String codIsbn,titulo,editorial,autor;
			int paginas=0;
			codIsbn = request.getParameter("cod");
			titulo = request.getParameter("titulo");
			editorial = request.getParameter("editorial");
			autor = request.getParameter("autor");
			paginas = Integer.parseInt(request.getParameter("paginas"));
			LibroDTO libDto_Act = new LibroDTO(codIsbn,titulo,editorial,autor,paginas);
			if(libDao.Actualizar_Libro(libDto_Act)) {
				//JOptionPane.showMessageDialog(null, "Libro se Actualizo Exitosamente.");
				response.sendRedirect("Libros.jsp?men=Libro Actualizado Exitosamente.");
			}else {
				//JOptionPane.showMessageDialog(null, "El libro no se Modifico.");
				response.sendRedirect("Libros.jsp?men=El libro no se Modifico.");
			}
			}
			
			if(request.getParameter("eliminar")!=null) {
				String codIsbn;
				codIsbn = request.getParameter("cod");
				int op=JOptionPane.showConfirmDialog(null, "Desea Eliminar el Libro cod: "+codIsbn);
				if(op==0) {
				if(libDao.Eliminar_Libro(codIsbn)) {
					response.sendRedirect("Libros.jsp?men=Libro Eliminado");
				}else{
					response.sendRedirect("Libros.jsp?men=Libro no se elimino");
				}
				}else if(op==1){
					response.sendRedirect("Libros.jsp?men=Libro no se elimino");
				}else {
					response.sendRedirect("Libros.jsp?men=Accion Cancelada");
				} 
			}
			
			if(request.getParameter("cargar")!=null) {
				
				Part archivo= request.getPart("archivo");
				//String Url="C:\\Users\\vivis\\eclipse-workspace\\Prestamos_08\\src\\main\\webapp\\Documentos\\";
				//En Mysql: show variables like 'secure_file_priv'
				//String tipo= archivo.getContentType();
				//JOptionPane.showMessageDialog(null, tipo);
				String Url="C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/";
				
				if(archivo.getContentType().equals("application/vnd.ms-excel")) {
				try {
				InputStream file= archivo.getInputStream();
				File copia= new File(Url+"prueba08.csv");
				FileOutputStream escribir= new FileOutputStream(copia);
				int num=file.read();
				while(num !=-1) {
					escribir.write(num);
					num=file.read();
				}
				file.close();
				escribir.close();
				JOptionPane.showMessageDialog(null, "Se cargo el archivo correctamente");
				if(libDao.Cargar_Libros(Url+"prueba08.csv")) {
					response.sendRedirect("Libros.jsp?men=Se registro los Libros correctamente");
				}else
				{
					response.sendRedirect("Libros.jsp?men=No se registraron Libros");
				}
				}catch(Exception e) {
					JOptionPane.showMessageDialog(null, "Error al cargar el archivo: "+e);
					response.sendRedirect("Libros.jsp?men=Error al cargar el archivo: ");
					
				}
				}else
				{
					response.sendRedirect("Libros.jsp?men=Formato de Archivo no permitido");
					}
			}
			
			
			
			
			
		}
	}

