/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codeapp.model;

import codeapp.controller.LettersSoup_Controller;

public class Palabra {
	private int linea, col, orientacion;
	private String palabra;
	private LettersSoup_Controller sp;
	
	public Palabra(int linea, int col, int orientacion, String palabra, LettersSoup_Controller sp) {
		this.linea = linea;
		this.col = col;
		this.orientacion = orientacion;
		this.palabra = palabra;
		this.sp = sp;
	}
	
	public void meterEnTablero() {
		for(int i=0; i<palabra.length(); i++) {
			char letra = palabra.charAt(i);
			switch (orientacion) {
			case Orientacion.HORIZONTAL:				
				sp.soup[linea][col+i].setText(letra+"");
                                sp.soup[linea][col+i].setName(letra+"");
				break;
			case Orientacion.VERTICAL:
				sp.soup[linea+i][col].setText(letra+"");
                                sp.soup[linea+i][col].setName(letra+"");
				break;
			case Orientacion.DIAGONAL_DRC:
				sp.soup[linea+i][col+i].setText(letra+"");
                                sp.soup[linea+i][col+i].setName(letra+"");
				break;
			case Orientacion.DIAGONAL_IZQ:
				sp.soup[linea+i][col-i].setText(letra+"");
                                sp.soup[linea+i][col-i].setName(letra+"");
				break;
			case Orientacion.INV_HORIZONTAL:				
				sp.soup[linea][col-i].setText(letra+"");
                                sp.soup[linea][col-i].setName(letra+"");
				break;
			case Orientacion.INV_VERTICAL:
				sp.soup[linea-i][col].setText(letra+"");
                                sp.soup[linea-i][col].setName(letra+"");
				break;
			case Orientacion.INV_DIAGONAL_DRC:
				sp.soup[linea-i][col+i].setText(letra+"");
                                sp.soup[linea-i][col+i].setName(letra+"");
				break;
			case Orientacion.INV_DIAGONAL_IZQ:
				sp.soup[linea-i][col-i].setText(letra+"");
                                sp.soup[linea-i][col-i].setName(letra+"");
				break;
			}
		}
	}
	public int longitud() { return palabra.length(); }
	
	public int getLinea() { return linea; }
	public int getCol() { return col; }
	public int getOrientacion() { return orientacion; }
	public String getPalabra() { return palabra; }
}