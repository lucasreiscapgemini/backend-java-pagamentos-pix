package com.pagamentos.apirest.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatacaoUtil {
	
	public static String obterDataFormatada(Date data) {
		String pattern = "dd/MM/yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

		return simpleDateFormat.format(data);
	}
	
	public static String obterPorcentagemFormatada(double porcentagem) {
		DecimalFormat df = new DecimalFormat("0.00%");
		return df.format(porcentagem); 
	}
}
