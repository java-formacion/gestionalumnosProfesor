package com.ipartek.formacion.service.i18n;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;

import com.ipartek.formacion.pojo.Idioma;

/**
 * 
 * @author Curso
 *
 */
public class I18n {
  /**
   * Constructor private
   */
  private I18n() {
  }

  /**
   * Devuelve el locale del navegador
   * 
   * @param localeBrowser
   *          Locale
   * @return result
   */
  public static final String getBrowserLocale(Locale localeBrowser) {
    Locale localeEus = new Locale(Idioma.EUSKERA.getCodIdioma());
    Locale localeEs = new Locale(Idioma.CASTELLANO.getCodIdioma());
    Locale localeEn = new Locale(Idioma.INGLES.getCodIdioma());
    String result = Idioma.INGLES.getLocale();
    if (localeBrowser != null) {
      if (localeBrowser.getLanguage().equals(localeEus.getLanguage())) {
        result = Idioma.EUSKERA.getLocale();
      } else {
        if (localeBrowser.getLanguage().equals(localeEs.getLanguage())) {
          result = Idioma.CASTELLANO.getLocale();
        } else {
          if (localeBrowser.getLanguage().equals(localeEn.getLanguage())) {
            result = Idioma.INGLES.getLocale();
          }
        }
      }
    }
    return result;
  }

  /**
   * 
   * @param cadenaMensaje
   *          String
   * @param params
   *          Object...
   * @return msg
   */
  public static String getString(String cadenaMensaje, Object... params) {
    String msg = "";
    try {
      msg = MessageFormat.format(cadenaMensaje, params);
    } catch (MissingResourceException e) {
      msg = "No existe el mensaje.";
    } catch (Exception e) {

    }
    return msg;
  }

  /**
   * 
   * @param lang
   *          final String
   * 
   * @return loc
   */
  public static final Locale getStringLocale(final String lang) {
    Locale loc = new Locale(Idioma.INGLES.getCodIdioma());
    if (lang != null) {
      if (lang.equalsIgnoreCase(Idioma.CASTELLANO.getCodIdioma())) {
        loc = new Locale(Idioma.CASTELLANO.getCodIdioma());
      } else {
        if (lang.equalsIgnoreCase(Idioma.EUSKERA.getCodIdioma())) {
          loc = new Locale(Idioma.EUSKERA.getCodIdioma());
        }
      }
    }
    return loc;
  }
}
