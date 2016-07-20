package com.ipartek.formacion.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.ipartek.formacion.dbms.dao.ModuloDAO;
import com.ipartek.formacion.dbms.dao.ModuloDAOImp;
import com.ipartek.formacion.pojo.Modulo;

/**
 * 
 * @author Curso
 *
 */
public class ModuloServiceImp implements ModuloService {
  private final static Logger LOG = Logger.getLogger(ModuloServiceImp.class);
  private static ModuloServiceImp INSTANCE = null;
  private ModuloDAO moduloDao;

  /**
 * 
 */
  private ModuloServiceImp() {
    moduloDao = ModuloDAOImp.getInstance();
  }

  /**
   * 
   * @return instancia
   */
  public static ModuloServiceImp getInstance() {
    if (INSTANCE == null) {
      createInstance();
    }
    return INSTANCE;
  }

  /**
 * 
 */
  private synchronized static void createInstance() {
    if (INSTANCE == null) {
      INSTANCE = new ModuloServiceImp();
    }
  }

  /**
   * @Override
   * @return nada
   * @throws CloneNotSupportedException
   *           no se puede clonar
   */
  protected Object clone() throws CloneNotSupportedException {
    LOG.error("Error al clonar");
    throw new CloneNotSupportedException();
  }

  /**
   * @Override
   * @param modulo
   *          Modulo
   * @return modulo
   */
  public Modulo createModulo(Modulo modulo) {
    Modulo mod = moduloDao.create(modulo);
    return mod;
  }

  /**
   * @Override
   * @param codigo
   *          int
   * @return modulo
   */
  public Modulo getById(int codigo) {
    Modulo mod = moduloDao.getById(codigo);
    return mod;
  }

  /**
   * @Override
   * @param codigo
   *          int
   */
  public void delete(int codigo) {
    moduloDao.delete(codigo);
  }

  /**
   * @Override
   * @return lista de modulos
   */
  public List<Modulo> getAll() {
    return moduloDao.getAll();
  }

  /**
   * @Override
   * @param modulo
   *          Modulo
   * @return modulo
   */
  public Modulo update(Modulo modulo) {
    Modulo mod = moduloDao.update(modulo);
    return mod;
  }
}
