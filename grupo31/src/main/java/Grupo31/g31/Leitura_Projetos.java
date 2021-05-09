package Grupo31.g31;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bouncycastle.crypto.macs.CMac;

import com.github.javaparser.ParseException;

public class Leitura_Projetos {

	static ArrayList <File> ficheiro = new ArrayList<>();
	static String dirName;
	static ArrayList <String> fimdojava = new ArrayList<>();
	static Create_Method cm = new Create_Method();
	static ArrayList <String> packName = new ArrayList <String>();
	static int it;
	public static ArrayList <String> packages = new ArrayList<>();
	public static int contadorClasses;
	static ArrayList <File> file = new ArrayList<>();

	

	public static String getDirName() { 
		return dirName;
	}

	public List <File> lista(String list) {

		dirName = list;
		File diretorio = new File(list);
		File[] listaFicheiros = diretorio.listFiles();
		ficheiro.addAll(Arrays.asList(listaFicheiros));
		it = 0;

		for (File i : listaFicheiros) {
			it ++;
			if (i.isFile() && i.getAbsolutePath().endsWith(".java")) {
				Path fim = Paths.get(i.getAbsolutePath());
				fimdojava.add(fim.getFileName().toString());	
				cm.fillmethod(i.getAbsoluteFile());
				contadorClasses++;
				String parentName= i.getParent();
				if(!packages.contains(parentName)) {
					packages.add(parentName);
				}

			} else if (i.isDirectory()) {
				ficheiro.addAll(lista(i.getAbsolutePath()));
				System.out.println("ficheiro"+ficheiro);
			}
		}
		return ficheiro;
	}

	public static ArrayList<String> getFimdojava() {
		return fimdojava;
	}
}