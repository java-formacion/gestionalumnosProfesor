package com.ipartek.formacion.dbms.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.ipartek.formacion.dbms.ConexionDB;
import com.ipartek.formacion.dbms.ConexionDBImp;
import com.ipartek.formacion.pojo.Curso;
import com.ipartek.formacion.pojo.TipoCurso;

public class CursoDAOImp implements CursoDAO{
	
	private static final Logger LOG=Logger.getLogger(CursoDAOImp.class);
	private ConexionDB dbConnection;
	private static CursoDAOImp INSTANCE;

	private CursoDAOImp(){
		dbConnection=ConexionDBImp.getInstance();
	}
	
	private synchronized static void createInstance(){
		if (INSTANCE==null) {
			INSTANCE=new CursoDAOImp();
		}
	}
	
	public static CursoDAOImp getInstance(){
		if (INSTANCE==null) {
			createInstance();
		}
		return INSTANCE;
	}
	
	
	@Override
	public Curso getById(int codigo) {
		
		String sql="SELECT c.codCurso, c.nombreCurso, c.codPatrocinador, t.tipoCurso "+
		"FROM curso c INNER JOIN tipoCurso t ON c.tipoCurso=t.codTipoCurso "+
				"WHERE c.codCurso="+codigo;
		Curso curso=null;
		
		Connection conexion=dbConnection.getConexion();
		try {
			PreparedStatement pStat=conexion.prepareStatement(sql);
			ResultSet rS=pStat.executeQuery();
			while (rS.next()) {
				curso=parseCurso(rS);
				
			}
		} catch (SQLException e) {
			LOG.fatal(e.getMessage());
		}finally{
			dbConnection.desconectar();
		}
		
		return curso;
	}

	@Override
	public Curso updateCurso(Curso curso) {
		Curso c=null;
		String sql="{call updateCurso(?,?,?,?)}";
		Connection conexion=dbConnection.getConexion();
		try {
			CallableStatement cStat=conexion.prepareCall(sql);
			cStat.setInt("codigo",curso.getCodigo());
			cStat.setString("nombreCurso", curso.getNombre());
			cStat.setString("codPatrocinador", curso.getCodPatrocinador());
			cStat.setInt("tipoCurso", curso.getTipoCurso().getCodigo());
			
			cStat.executeUpdate();
			c=curso;
			
		} catch (SQLException e) {
			c=getById(curso.getCodigo());
			LOG.fatal(e.getMessage());
		}finally {
			dbConnection.desconectar();
		}
		
		return c;
	}

	@Override
	public void deleteCurso(int codigo) {
		String sql="{call deleteCurso(?)}";
		Connection conexion=dbConnection.getConexion();
		try {
			CallableStatement cStat=conexion.prepareCall(sql);
			cStat.setInt("codigo", codigo);
			cStat.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO: handle exception
		}finally {
			dbConnection.desconectar();
		}
		
	}

	@Override
	public Curso createCurso(Curso curso) {
		Curso c=null;
		String sql="{call createCurso(?,?,?,?}";
		Connection conexion=dbConnection.getConexion();
		try {
			CallableStatement cStat=conexion.prepareCall(sql);
			cStat.setInt("codigo",curso.getCodigo());
			cStat.setString("nombreCurso", curso.getNombre());
			cStat.setString("codPatrocinador", curso.getCodPatrocinador());
			cStat.setInt("tipoCurso", curso.getTipoCurso().getCodigo());
			
			cStat.executeUpdate();
			
			
			c=curso;
			c.setCodigo(cStat.getInt("codigo"));
			
		} catch (SQLException e) {
			c=getById(curso.getCodigo());
			LOG.fatal(e.getMessage());
		}finally {
			dbConnection.desconectar();
		}
		return c;
	}

	@Override
	public List<Curso> getAll() {
		List <Curso> lCursos=new ArrayList<Curso>();
		String sql="{call getAllCurso()}";
		
		Connection conexion=dbConnection.getConexion();
		
		try {
			CallableStatement cStat=conexion.prepareCall(sql);
			ResultSet rS=cStat.executeQuery();
			while (rS.next()) {
				lCursos.add(parseCurso(rS));
				
			}
			
		} catch (SQLException e) {
			LOG.fatal(e.getMessage());
		}finally {
			dbConnection.desconectar();
		}
		
		
		
		
		return lCursos;
	}
	
	public Curso parseCurso(ResultSet rS) {
		Curso aux=new Curso();
		
		
		try {
			aux.setCodigo(rS.getInt("codCurso"));
			aux.setNombre(rS.getString("nombreCurso"));
			aux.setCodPatrocinador(rS.getString("codPatrocinador"));
						
			for (TipoCurso t : TipoCurso.values()) {
				if (t.getCodigo()==rS.getInt("tipoCurso")) {
					aux.setTipoCurso(t);
				}
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}
		return aux;
	}

}
