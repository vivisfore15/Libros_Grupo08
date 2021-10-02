package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Controlador.Conexion;

public class PrestamosDAO {

	Conexion cnn= new Conexion();
	Connection con=cnn.Conecta();
    PreparedStatement ps=null;
    ResultSet res=null;
    
    
    public boolean Inserta_prestamo(PrestamosDTO p) {
    
    	boolean resul=false;
    try {
    	String sql="insert into prestamos(Estudiante,Libro,Fecha_Prestamo,cantidad) values(?,?,?,?)";
    	ps=con.prepareStatement(sql);
    	ps.setString(1, p.getEstudiante());
    	ps.setString(2, p.getLibro());
    	ps.setString(3, p.getFecha_Prestamo());
    	ps.setInt(4, p.getCant());
    	resul=ps.executeUpdate()>0;
    	
    	if(resul) {
    	sql="select id_prestamos from prestamos order by Id_prestamos desc limit 1";
    	ps=con.prepareStatement(sql);
    	res=ps.executeQuery();
    	int id=0;
    	while(res.next()) {
    		id=res.getInt(1);
    	}
    	
    	sql="select precio from libro where isbn=?";
    	ps=con.prepareStatement(sql);
    	ps.setString(1, p.getLibro());
    	res=ps.executeQuery();
    	int precio=0;
    	while(res.next()) {
    		precio=res.getInt(1);
    	}
    	
    	sql="update prestamos set total=?*? where id_prestamos=?";
    	ps=con.prepareStatement(sql);
    	ps.setInt(1, precio);
    	ps.setInt(2, p.getCant());
    	ps.setInt(3, id);
    	resul=ps.executeUpdate()>0;
    	
    	sql="update libro set acumulado=acumulado+(?*?) where isbn=?";
    	ps=con.prepareStatement(sql);
    	ps.setInt(1, precio);
    	ps.setInt(2, p.getCant());
    	ps.setString(3, p.getLibro());
    	resul=ps.executeUpdate()>0;
    	}
    	}catch(SQLException ex) {
    		JOptionPane.showMessageDialog(null,"Error al insertar prestamo" +ex);
    	}
        return resul;
    }
    
    
    public ArrayList<PrestamosDTO> listaPrestamos(){
    	
    	ArrayList<PrestamosDTO> lista= new ArrayList<>();
    	PrestamosDTO pres=null;
    	try {
    	String sql="select * from prestamos";	
    	ps=con.prepareStatement(sql);
    	res=ps.executeQuery();
    	while(res.next()) {
    		pres= new PrestamosDTO(res.getInt(1),res.getString(2),res.getString(3),res.getString(4),res.getInt(5),res.getInt(6));
    		lista.add(pres);
    	}
    	}catch(SQLException ex) {
    		JOptionPane.showMessageDialog(null,"Error al consultar prestamos" +ex);
    	}
    	return lista;
    }
    
    
    
    
    
    
    
    
    
}
