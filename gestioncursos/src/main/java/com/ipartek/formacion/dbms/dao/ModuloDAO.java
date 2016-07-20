package com.ipartek.formacion.dbms.dao;

import java.util.List;

import com.ipartek.formacion.pojo.Modulo;

/**
 * 
 * @author Modulo
 *
 */
public interface ModuloDAO {
  /**
   * @param codigo
   *          int codigoModulo
   * @return modulo
   */
  public Modulo getById(int codigo);

  /**
   * 
   * @param modulo
   *          Modulo
   * @return modulo actualizado
   */
  public Modulo update(Modulo modulo);

  /**
   * 
   * @param modulo
   *          Modulo
   * @return modulo creado
   */
  public Modulo create(Modulo modulo);

  /**
   * 
   * @param codigo
   *          codigo modulo
   */
  public void delete(int codigo);

  /**
   * 
   * @return lista de modulos
   */
  public List<Modulo> getAll();

}
