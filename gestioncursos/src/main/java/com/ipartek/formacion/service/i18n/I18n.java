package com.ipartek.formacion.service.i18n;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;

import com.ipartek.formacion.pojo.Idioma;

public class I18n {
	
	public static final String getBrowserLocale(Locale localeBrowser){
		String result= Idioma.INGLES.getLocale();
		if (localeBrowser!=null) {
			if (localeBrowser.getLanguage().equals(new Locale(Idioma.EUSKERA.getCodIdioma()).getLanguage())) {
			
			}
			else
			{if (localeBrowser.getLanguage().equals(new Locale(Idioma.CASTELLANO.getCodIdioma()).getLanguage())){
				
			}
				
			}
		}
		return result;
	 }
	public static Locale getStringLocale(final String lang){
		Locale loc = new Locale(Idioma.INGLES.getCodIdioma());
		if (lang!=null) {
			if(lang.equalsIgnoreCase(Idioma.CASTELLANO.getCodIdioma())){
				loc = new Locale(Idioma.CASTELLANO.getCodIdioma());
			}
			else {
				if(lang.equalsIgnoreCase(Idioma.EUSKERA.getCodIdioma())){
					loc = new Locale(Idioma.EUSKERA.getCodIdioma());
				}
			}
				
		}
		return loc;
	}
	
	public static String getString(String cadenaMensaje,Object... params) {
		String msg="";
		try {
			msg = MessageFormat.format(cadenaMensaje, params);
		} catch (MissingResourceException e) {
			msg= "no existe el mensaje";
		}
		return msg;
		
	}
	}
