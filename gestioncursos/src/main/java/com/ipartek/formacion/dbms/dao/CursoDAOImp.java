package com.ipartek.formacion.dbms.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
		}
		
		return null;
	}

	@Override
	public Curso updateCurso(Curso curso) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteCurso(int codigo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Curso createCurso(Curso curso) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Curso> getAll() {
		// TODO Auto-generated method stub
		return null;
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
