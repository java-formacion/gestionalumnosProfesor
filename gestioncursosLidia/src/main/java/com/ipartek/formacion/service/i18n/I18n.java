package com.ipartek.formacion.service.i18n;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;

import com.ipartek.formacion.pojo.Idioma;

public class I18n {

	public static final String getBrowserLocale(Locale localeBrowser){//detectar el lenguaje del navegador
		Locale localeEuskera=new Locale(Idioma.EUSKERA.getCodIdioma());//locale mejor que el idioma, español latino,castellano,etc..
		Locale localeIngles=new Locale(Idioma.INGLES.getCodIdioma());
		Locale localeCastellano=new Locale(Idioma.CASTELLANO.getCodIdioma());
		String result=Idioma.INGLES.getLocale();//POR DEFECTO EN INGLÉS
		if(localeBrowser!=null){
			if(localeBrowser.getLanguage().equals(localeEuskera.getLanguage())){//mirar si el objeto del navegador y con lo q vamos a comparar
				result=Idioma.EUSKERA.getLocale();
			}else{
				if(localeBrowser.getLanguage().equals(localeCastellano.getLanguage())){
					result=Idioma.CASTELLANO.getLocale();
				}
			}
		}
		return result;
	}
	//con el  idioma devuelva el local
	public static final Locale getStringLocale(final String lang){//cuando se envia param por metodo,se pone final para q no se pueda modificar
		Locale loc=new Locale(Idioma.INGLES.getCodIdioma());
		if(lang!=null){
			if(lang.equalsIgnoreCase(Idioma.CASTELLANO.getCodIdioma())){
				loc=new Locale(Idioma.CASTELLANO.getCodIdioma());
			}else{
				if(lang.equalsIgnoreCase(Idioma.EUSKERA.getCodIdioma())){
					loc=new Locale(Idioma.EUSKERA.getCodIdioma());//index.jsp
				}
			}
		}
		return loc;
	}
	public static String getString(String cadenaMensaje, Object...params){//lista de parámetros
		String msg="";
		try{
			msg=MessageFormat.format(cadenaMensaje, params);
		}catch(MissingResourceException e){//no encuentra ficheros de propeties
			msg="no existe el mensaje";
		}
		return msg;
	}
}
