package com.ipartek.formacion.service.i18n;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;

import javax.xml.soap.MessageFactory;

import com.ipartek.formacion.pojo.Idioma;

public class I18n {

	public static final String getBrowserLocale(Locale localeBrowser){
		Locale locCas = new Locale(Idioma.CASTELLANO.getCodIdioma());
		String result = Idioma.INGLES.getLocale();
		
		if(localeBrowser != null)
		{
			if(localeBrowser.getLanguage().equals(new Locale(Idioma.EUSKERA.getCodIdioma()).getLanguage()))
			{
				result = Idioma.EUSKERA.getLocale();
			}
			else
			{
				if(localeBrowser.getLanguage().equals(new Locale(Idioma.CASTELLANO.getCodIdioma()).getLanguage()))
				{
					result = Idioma.CASTELLANO.getLocale();
				}
			}
		}
		
		return result;
	}
	
	public static String getString(String cadenaMensaje, Object... params){
		String msg = "";
		
		try {
			
			
			msg = MessageFormat.format(cadenaMensaje, params);
		} catch (MissingResourceException e) {
			// TODO: handle exception
			msg = "no existe el mensaje";
		}
		
		return msg;
	}
	
	public static final Locale getStringLocale (final String lang){
		
		Locale loc = new Locale(Idioma.INGLES.getCodIdioma());
		if(lang != null)
		{
			if(lang.equals(Idioma.CASTELLANO.getCodIdioma()))
			{
				loc = new Locale(Idioma.CASTELLANO.getCodIdioma());
			}
			else
			{
				if(lang.equals(Idioma.EUSKERA.getCodIdioma()))
				{
					loc = new Locale(Idioma.EUSKERA.getCodIdioma());
				}
			}
		}
		return loc;
		
	}
	
}
