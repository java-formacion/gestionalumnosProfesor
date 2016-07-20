package com.ipartek.formacion.dbms.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.ipartek.formacion.dbms.ConexionDB;
import com.ipartek.formacion.dbms.ConexionDBImp;
import com.ipartek.formacion.pojo.Modulo;

public class ModuloDAOImp implements ModuloDAO {
	private static final Logger LOG = Logger.getLogger(ModuloDAOImp.class);
	private ConexionDB myConexion;
	private static ModuloDAOImp INSTANCE = null;

	private ModuloDAOImp() {
		myConexion = ConexionDBImp.getInstance();
	}

	public static ModuloDAOImp getInstance() {
		if (INSTANCE == null) {
			createInstance();
		}
		return INSTANCE;
	}

	private synchronized static void createInstance() {
		if (INSTANCE == null) {
			INSTANCE = new ModuloDAOImp();
		}

	}

	@Override
	public Modulo getById(int codigo) {
		String sql = "SELECT codModulo, nombre, uFormativa, duracion" + " FROM modulo  WHERE codModulo=" + codigo;
		// ConexionDB dbConnection = ConexionDBImp.getInstance();
		// myConexion.conectar();
		Connection conexion = myConexion.getConexion();
		Modulo modulo = null;
		try {
			PreparedStatement pStm = conexion.prepareStatement(sql);
			ResultSet rs = pStm.executeQuery();
			while (rs.next()) {
				modulo = parseModulo(rs);
			}
		} catch (SQLException e) {
			LOG.fatal(e.getMessage());
		} finally {
			myConexion.desconectar();
		}
		return modulo;
	}

	private Modulo parseModulo(ResultSet rs) {
		Modulo modulo = null;
		modulo = new Modulo();
		try {
			modulo.setCodigo(rs.getInt("codModulo"));
			modulo.setNombre(rs.getString("nombre"));
			modulo.setReferencia(rs.getString("uFormativa"));
			modulo.getDuracion().setCodigo(rs.getInt("duracion"));
		} catch (SQLException e) {
			LOG.fatal(e.getMessage());
		}

		return modulo;
	}

	@Override
	public Modulo update(Modulo modulo) {
		// TODO terminar!!! y hacer los procedures en xampp
		Modulo modu = null;
		String sql = "{call updateModulo(?,?,?,?,?,?,?,?)}";
		// ConexionDB myConexion = ConexionDBImp.getInstance();
		// myConexion.conectar();

		try {
			CallableStatement cSmt = myConexion.getConexion().prepareCall(sql);
			cSmt.setInt("codigo", modulo.getCodigo());
			cSmt.setString("nombre", modulo.getNombre());
			cSmt.setString("uFormativa", modulo.getReferencia());
			cSmt.setInt("duracion", modulo.getDuracion().getDuracionHoras());
			cSmt.executeUpdate();
			modu = modulo;
		} catch (SQLException e) {
			modu = getById(modulo.getCodigo());
			LOG.fatal(e.getMessage());
		} finally {
			myConexion.desconectar();
		}

		return modu;
	}

	@Override
	public Modulo create(Modulo modulo) {
		Modulo modu = null;
		String sql = "{call insertModulo(?,?,?,?)}";
		// ConexionDB myConexion = ConexionDBImp.getInstance();
		// myConexion.conectar();
		Connection conexion = myConexion.getConexion();
		try {
			CallableStatement cSmt = conexion.prepareCall(sql);
			// cSmt.setInt("codigo", alumno.getCodigo());//el codigo NO se
			// pasa!!!
			cSmt.setString("nombre", modulo.getNombre());
			cSmt.setString("uFormativa", modulo.getReferencia());
			cSmt.setInt("duracion", modulo.getDuracion().getDuracionHoras());
			cSmt.executeUpdate();
			modu = modulo;
			modu.setCodigo(cSmt.getInt("codModulo"));
			modu = modulo;
		} catch (SQLException e) {
			LOG.fatal(e.getMessage());
		} finally {
			myConexion.desconectar();
		}

		return modu;
	}

	@Override
	public void delete(int codigo) {
		String sql = "{call deleteModulo(?)}";
		// ConexionDB myConexion = ConexionDBImp.getInstance();
		// myConexion.conectar();
		Connection conexion = myConexion.getConexion();
		try {
			CallableStatement cSmt = conexion.prepareCall(sql);
			cSmt.setInt("codigo", codigo);// el nombre del parametro del
											// procedimiento en xampp entre ""
			cSmt.executeUpdate();
		} catch (SQLException e) {
			LOG.fatal(e.getMessage());
		} finally {
			myConexion.desconectar();
		}

	}

	@Override
	public List<Modulo> getAll() {
		List<Modulo> modulos = null;
		String sql = "{call getAllModulo()}";
		// ConexionDB myConexion = ConexionDBImp.getInstance();
		Connection conection = myConexion.getConexion();
		try {
			Modulo modulo = null;
			CallableStatement cSmt = conection.prepareCall(sql);
			ResultSet rs = cSmt.executeQuery();
			modulos = new ArrayList<Modulo>();
			while (rs.next()) {
				modulo = parseModulo(rs);
				modulos.add(modulo);
			}
		} catch (SQLException e) {
			LOG.fatal(e.getMessage());
		} finally {
			myConexion.desconectar();
		}

		return modulos;
	}

}
