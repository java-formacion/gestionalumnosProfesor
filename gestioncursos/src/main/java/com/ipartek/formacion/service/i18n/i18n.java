package com.ipartek.formacion.service.i18n;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;

import com.ipartek.formacion.service.Idiomas;

public class i18n {
	
	public static final String getBrowserLocale(Locale localeBrowser){
		Locale localeEuskera = new Locale(Idiomas.EUSKERA.getCodIdioma());
		Locale localeIngles = new Locale(Idiomas.INGLES.getCodIdioma());
		Locale localeCastellano = new Locale(Idiomas.CASTELLANO.getCodIdioma());
		
		String result = Idiomas.INGLES.getLocale();
		if(localeBrowser!=null)
		{
			if(localeBrowser.getLanguage().equals(localeEuskera.getLanguage())){
				result= Idiomas.EUSKERA.getCodIdioma();
			}else{
				if(localeBrowser.getLanguage().equals(localeIngles.getLanguage())){
					result= Idiomas.INGLES.getCodIdioma();
			}else{
				if(localeBrowser.getLanguage().equals(localeCastellano.getLanguage())){
					result= Idiomas.CASTELLANO.getCodIdioma();
				}
			}
			}
		
		}
		return result;
	}
	
	public static final Locale getString(final String lang){
		Locale loc = new Locale(Idiomas.INGLES.getCodIdioma());
		
		if (lang != null){
			if(lang.equalsIgnoreCase(Idiomas.CASTELLANO.getCodIdioma())){
				loc = new Locale(Idiomas.CASTELLANO.getCodIdioma());
			}else{
				if(lang.equalsIgnoreCase(Idiomas.EUSKERA.getCodIdioma())){
					loc = new Locale(Idiomas.EUSKERA.getCodIdioma());
			}else{
				if(lang.equalsIgnoreCase(Idiomas.INGLES.getCodIdioma())){
					loc = new Locale(Idiomas.INGLES.getCodIdioma());
			}
			}
			}
		}
		
		return loc;
	}
	
	
	public static String getString(String cadenaMensaje,Object...params){
		String msg="";
		
		try{
			msg = MessageFormat.format(cadenaMensaje, params);
		}catch(MissingResourceException e){
			msg = "no existe el mensaje";
		}
		
		return msg;
	}

}
	

