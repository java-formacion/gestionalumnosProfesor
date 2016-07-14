package com.ipartek.formacion.service.i18n;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;

import com.ipartek.formacion.pojo.Idioma;

public class I18n {

	public static final String getBrowserLocale(Locale locale){
		String result=Idioma.INGLES.getLocale();
		Locale localeEusk=new Locale(Idioma.EUSKERA.getCodIdioma());
		Locale localeEng=new Locale (Idioma.INGLES.getCodIdioma());
		Locale localeCast=new Locale(Idioma.CASTELLANO.getCodIdioma());
		if (locale!=null) {
			if (locale.getLanguage().equals(localeEusk.getLanguage())) {
				result=Idioma.EUSKERA.getLocale();
			}else {
				if (locale.getLanguage().equals(localeCast.getLanguage())) {
					result=Idioma.CASTELLANO.getLocale();
				}
			}
		}
		
		return result;
	}
	
	public static String getString(String s, Object o){
		
		String msg="";
		try {
			msg=MessageFormat.format(s, o);
		} catch (MissingResourceException e) {
			msg="no existe el mensaje";
		}
		return msg;
		
	}
	
	public static final Locale getStringLocale(final String lang){
		
		Locale loc=new Locale(Idioma.INGLES.getCodIdioma());
		if (lang!=null) {
			if (lang.equalsIgnoreCase(Idioma.CASTELLANO.getCodIdioma())) {
				loc=new Locale(Idioma.CASTELLANO.getCodIdioma());
			}else {
				if (lang.equalsIgnoreCase(Idioma.EUSKERA.getCodIdioma())) {
					loc=new Locale(Idioma.EUSKERA.getCodIdioma());
				}
			}
		}
		return loc;
	}
}
