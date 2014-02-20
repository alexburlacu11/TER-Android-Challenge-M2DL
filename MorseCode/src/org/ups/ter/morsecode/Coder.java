package org.ups.ter.morsecode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.ups.ter.morsecode.Static.Morse;

public class Coder {
	private Map<Character,ArrayList<Morse>> charToMorse = new HashMap<Character,ArrayList<Morse>>();
	private Map<ArrayList<Morse>,Character> morseToChar = new HashMap<ArrayList<Morse>,Character>();
	
	public Coder(){
		initialize();
	}
	
	public ArrayList<Morse> encrypt(String s){
		ArrayList<Morse> value = new ArrayList<Morse>();
		
		for (char c : s.toCharArray()){
			value.addAll(charToMorse.get(c));
		}
		return value;
	}
	
	public String decrypt(ArrayList<Morse> input){
		ArrayList<Morse> letter = new ArrayList<Morse>();
		String s = "";
		
		Iterator<Morse> it = letter.iterator();
		while(it.hasNext())
		{
			Morse element = it.next();
			if (element != Morse.WORD_END) {
				letter.add(element);
			} else {
				s += " ";
			}
		    if (element == Morse.LETTER_END){
		    	s += morseToChar.get(element);
		    	letter.clear();
		    } 
		}
		return s;
	}
	
	
	public void initialize(){
		ArrayList<Morse>  value = new ArrayList<Morse>();
		addShort(value,1); addLong(value,1); value.add(Morse.LETTER_END);
		charToMorse.put('a',(ArrayList<Morse>) value.clone());
		morseToChar.put((ArrayList<Morse>) value.clone(),'a');
		value.clear();
		addLong(value,1); addShort(value,3); value.add(Morse.LETTER_END);
		charToMorse.put('b',(ArrayList<Morse>) value.clone());
		morseToChar.put((ArrayList<Morse>) value.clone(),'b');
		value.clear();
		addLong(value,1);addShort(value,1); addLong(value,1); addShort(value,1);value.add(Morse.LETTER_END);
		charToMorse.put('c',(ArrayList<Morse>) value.clone());
		morseToChar.put((ArrayList<Morse>) value.clone(),'c');
		value.clear();
		addLong(value,1);addShort(value,2); value.add(Morse.LETTER_END);
		charToMorse.put('d',(ArrayList<Morse>) value.clone());
		morseToChar.put((ArrayList<Morse>) value.clone(),'d');
		value.clear();
		addShort(value,1); value.add(Morse.LETTER_END);
		charToMorse.put('e',(ArrayList<Morse>) value.clone());
		morseToChar.put((ArrayList<Morse>) value.clone(),'e');
		value.clear();
		addShort(value,2);addLong(value,1);addShort(value,1); value.add(Morse.LETTER_END);
		charToMorse.put('f',(ArrayList<Morse>) value.clone());
		morseToChar.put((ArrayList<Morse>) value.clone(),'f');
		value.clear();
		addLong(value,2);addShort(value,1); value.add(Morse.LETTER_END);
		charToMorse.put('g',(ArrayList<Morse>) value.clone());
		morseToChar.put((ArrayList<Morse>) value.clone(),'g');
		value.clear();
		addShort(value,4);value.add(Morse.LETTER_END);
		charToMorse.put('h',(ArrayList<Morse>) value.clone());
		morseToChar.put((ArrayList<Morse>) value.clone(),'h');
		value.clear();
		addShort(value,2);value.add(Morse.LETTER_END);
		charToMorse.put('i',(ArrayList<Morse>) value.clone());
		morseToChar.put((ArrayList<Morse>) value.clone(),'i');
		value.clear();
		addShort(value,1); addLong(value,3); value.add(Morse.LETTER_END);
		charToMorse.put('j',(ArrayList<Morse>) value.clone());
		morseToChar.put((ArrayList<Morse>) value.clone(),'j');
		value.clear();
		addLong(value,1);addShort(value,1); addLong(value,1); value.add(Morse.LETTER_END);
		charToMorse.put('k',(ArrayList<Morse>) value.clone());
		morseToChar.put((ArrayList<Morse>) value.clone(),'k');
		value.clear();
		addShort(value,1); addLong(value,1); addShort(value,2); value.add(Morse.LETTER_END);
		charToMorse.put('l',(ArrayList<Morse>) value.clone());
		morseToChar.put((ArrayList<Morse>) value.clone(),'l');
		value.clear();
		addLong(value,2); value.add(Morse.LETTER_END);
		charToMorse.put('m',(ArrayList<Morse>) value.clone());
		morseToChar.put((ArrayList<Morse>) value.clone(),'m');
		value.clear();
		addLong(value,1); addShort(value,1); value.add(Morse.LETTER_END);
		charToMorse.put('n',(ArrayList<Morse>) value.clone());
		morseToChar.put((ArrayList<Morse>) value.clone(),'n');
		value.clear();
		addLong(value,3); value.add(Morse.LETTER_END);
		charToMorse.put('o',(ArrayList<Morse>) value.clone());
		morseToChar.put((ArrayList<Morse>) value.clone(),'o');
		value.clear();
		addShort(value,1); addLong(value,2); addShort(value,1); value.add(Morse.LETTER_END);
		charToMorse.put('p',(ArrayList<Morse>) value.clone());
		morseToChar.put((ArrayList<Morse>) value.clone(),'p');
		value.clear();
		addLong(value,2); addShort(value,1); addLong(value,3); value.add(Morse.LETTER_END);
		charToMorse.put('q',(ArrayList<Morse>) value.clone());
		morseToChar.put((ArrayList<Morse>) value.clone(),'q');
		value.clear();
		addShort(value,1); addLong(value,1); addShort(value,1); value.add(Morse.LETTER_END);
		charToMorse.put('r',(ArrayList<Morse>) value.clone());
		morseToChar.put((ArrayList<Morse>) value.clone(),'r');
		value.clear();
		addShort(value,3); value.add(Morse.LETTER_END);
		charToMorse.put('s',(ArrayList<Morse>) value.clone());
		morseToChar.put((ArrayList<Morse>) value.clone(),'s');
		value.clear();
		addLong(value,1);value.add(Morse.LETTER_END);
		charToMorse.put('t',(ArrayList<Morse>) value.clone());
		morseToChar.put((ArrayList<Morse>) value.clone(),'t');
		value.clear();
		addShort(value,2); addLong(value,1); value.add(Morse.LETTER_END);
		charToMorse.put('u',(ArrayList<Morse>) value.clone());
		morseToChar.put((ArrayList<Morse>) value.clone(),'u');
		value.clear();
		addShort(value,3); addLong(value,1); value.add(Morse.LETTER_END);
		charToMorse.put('v',(ArrayList<Morse>) value.clone());
		morseToChar.put((ArrayList<Morse>) value.clone(),'v');
		value.clear();
		addShort(value,1); addLong(value,2); value.add(Morse.LETTER_END);
		charToMorse.put('w',(ArrayList<Morse>) value.clone());
		morseToChar.put((ArrayList<Morse>) value.clone(),'w');
		value.clear();
		addLong(value,1);addShort(value,2); addLong(value,1);value.add(Morse.LETTER_END);
		charToMorse.put('x',(ArrayList<Morse>) value.clone());
		morseToChar.put((ArrayList<Morse>) value.clone(),'x');
		value.clear();
		addLong(value,1);addShort(value,1); addLong(value,2);value.add(Morse.LETTER_END);
		charToMorse.put('y',(ArrayList<Morse>) value.clone());
		morseToChar.put((ArrayList<Morse>) value.clone(),'y');
		value.clear();
		addLong(value,2);addShort(value,2);value.add(Morse.LETTER_END);
		charToMorse.put('z',(ArrayList<Morse>) value.clone());
		morseToChar.put((ArrayList<Morse>) value.clone(),'z');
		value.clear();
		addShort(value,1); addLong(value,4);value.add(Morse.LETTER_END);
		charToMorse.put('1',(ArrayList<Morse>) value.clone());
		morseToChar.put((ArrayList<Morse>) value.clone(),'1');
		value.clear();
		addShort(value,2); addLong(value,3);value.add(Morse.LETTER_END);
		charToMorse.put('2',(ArrayList<Morse>) value.clone());
		morseToChar.put((ArrayList<Morse>) value.clone(),'2');
		value.clear();
		addShort(value,3); addLong(value,2);value.add(Morse.LETTER_END);
		charToMorse.put('3',(ArrayList<Morse>) value.clone());
		morseToChar.put((ArrayList<Morse>) value.clone(),'3');
		value.clear();
		addShort(value,4); addLong(value,1);value.add(Morse.LETTER_END);
		charToMorse.put('4',(ArrayList<Morse>) value.clone());
		morseToChar.put((ArrayList<Morse>) value.clone(),'4');
		value.clear();
		addShort(value,5); value.add(Morse.LETTER_END);
		charToMorse.put('5',(ArrayList<Morse>) value.clone());
		morseToChar.put((ArrayList<Morse>) value.clone(),'5');
		value.clear();
		addLong(value,1);addShort(value,4);value.add(Morse.LETTER_END); 
		charToMorse.put('6',(ArrayList<Morse>) value.clone());
		morseToChar.put((ArrayList<Morse>) value.clone(),'6');
		value.clear();
		addLong(value,2);addShort(value,3);value.add(Morse.LETTER_END);
		charToMorse.put('7',(ArrayList<Morse>) value.clone());
		morseToChar.put((ArrayList<Morse>) value.clone(),'7');
		value.clear();
		addLong(value,3);addShort(value,2);value.add(Morse.LETTER_END);
		charToMorse.put('8',(ArrayList<Morse>) value.clone());
		morseToChar.put((ArrayList<Morse>) value.clone(),'8');
		value.clear();
		addLong(value,4);addShort(value,1);value.add(Morse.LETTER_END);
		charToMorse.put('9',(ArrayList<Morse>) value.clone());
		morseToChar.put((ArrayList<Morse>) value.clone(),'9');
		value.clear();
		addLong(value,5);value.add(Morse.LETTER_END);
		charToMorse.put('0',(ArrayList<Morse>) value.clone());
		morseToChar.put((ArrayList<Morse>) value.clone(),'0');
		value.clear();
		addShort(value,1); addLong(value,1); addShort(value,1); addLong(value,1);addShort(value,1); addLong(value,1);value.add(Morse.LETTER_END);
		charToMorse.put('.',(ArrayList<Morse>) value.clone());
		morseToChar.put((ArrayList<Morse>) value.clone(),'.');
		value.clear();
		value.add(Morse.WORD_END);
		charToMorse.put(' ',(ArrayList<Morse>) value.clone());
		morseToChar.put((ArrayList<Morse>) value.clone(),' ');
		value.clear();
	}
	
	
	private void addShort(ArrayList<Morse> value, int i){
		while (i > 0) {
			value.add(Morse.SHORT);
			i--;
		}
	}
	
	private void addLong(ArrayList<Morse> value, int i){
		while (i > 0) {
			value.add(Morse.LONG);
			i--;
		}
	}
}
