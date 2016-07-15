package com.ipartek.formacion.service.i18n;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;

import com.ipartek.formacion.pojo.Idioma;

public class I18n {
	private I18n() {}
	public static final String getBrowserLocale(Locale localeBrowser){
		Locale localeEuskera = new Locale(Idioma.EUSKERA.getCodIdioma());
		//	Locale localeIngles = new Locale(Idioma.INGLES.getCodIdioma());
		Locale localeCastellano = new Locale(Idioma.CASTELLANO.getCodIdioma());
		String result = Idioma.INGLES.getLocale();

		if(localeBrowser!=null){
			if(localeBrowser.getLanguage().equals(localeEuskera.getLanguage())){
				result = Idioma.EUSKERA.getLocale();
			}else{
				if(localeBrowser.getLanguage().equals(localeCastellano.getLanguage())){
					result = Idioma.CASTELLANO.getLocale();
				}
			}
		}
		return result;
	}
	public static final Locale getStringLocale(final String lang){
		Locale loc = new Locale(Idioma.INGLES.getCodIdioma());
		if(lang!=null){
			if(lang.equalsIgnoreCase(Idioma.CASTELLANO.getCodIdioma())){
				loc = new Locale(Idioma.CASTELLANO.getCodIdioma());
			}else{
				if(lang.equalsIgnoreCase(Idioma.EUSKERA.getCodIdioma())){
					loc = new Locale(Idioma.EUSKERA.getCodIdioma());
				}
			}
		}
		return loc;
	}
	public static String getString(String cadenaMensaje, Object... params){
		String msg ="";
		try{

			msg = MessageFormat.format(cadenaMensaje, params);
		} catch(MissingResourceException e){
			msg = "no existe el mensaje";
		}
		return msg;
	}
}
