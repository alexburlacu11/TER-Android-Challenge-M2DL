package org.ups.ter.morsecode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.ups.ter.morsecode.Static.Morse;

public class Coder {
	private Map<Character,ArrayList<Morse>> charToMorse = new HashMap<Character,ArrayList<Morse>>();
	private Map<ArrayList<Morse>,Character> morseToChar = new HashMap<ArrayList<Morse>,Character>();
	
	Coder(){
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
		ArrayList<Morse> value = new ArrayList<Morse>();
		addShort(value,1); addLong(value,1); value.add(Morse.LETTER_END);
		charToMorse.put('a',value);
		morseToChar.put(value,'a');
		value.clear();
		addLong(value,1); addShort(value,3); value.add(Morse.LETTER_END);
		charToMorse.put('b',value);
		morseToChar.put(value,'b');
		value.clear();
		addLong(value,1);addShort(value,1); addLong(value,1); addShort(value,1);value.add(Morse.LETTER_END);
		charToMorse.put('c',value);
		morseToChar.put(value,'c');
		value.clear();
		addLong(value,1);addShort(value,2); value.add(Morse.LETTER_END);
		charToMorse.put('d',value);
		morseToChar.put(value,'d');
		value.clear();
		addShort(value,1); value.add(Morse.LETTER_END);
		charToMorse.put('e',value);
		morseToChar.put(value,'e');
		value.clear();
		addShort(value,2);addLong(value,1);addShort(value,1); value.add(Morse.LETTER_END);
		charToMorse.put('f',value);
		morseToChar.put(value,'f');
		value.clear();
		addLong(value,2);addShort(value,1); value.add(Morse.LETTER_END);
		charToMorse.put('g',value);
		morseToChar.put(value,'g');
		value.clear();
		addShort(value,4);value.add(Morse.LETTER_END);
		charToMorse.put('h',value);
		morseToChar.put(value,'h');
		value.clear();
		addShort(value,2);value.add(Morse.LETTER_END);
		charToMorse.put('i',value);
		morseToChar.put(value,'i');
		value.clear();
		addShort(value,1); addLong(value,3); value.add(Morse.LETTER_END);
		charToMorse.put('j',value);
		morseToChar.put(value,'j');
		value.clear();
		addLong(value,1);addShort(value,1); addLong(value,1); value.add(Morse.LETTER_END);
		charToMorse.put('k',value);
		morseToChar.put(value,'k');
		value.clear();
		addShort(value,1); addLong(value,1); addShort(value,2); value.add(Morse.LETTER_END);
		charToMorse.put('l',value);
		morseToChar.put(value,'l');
		value.clear();
		addLong(value,2); value.add(Morse.LETTER_END);
		charToMorse.put('m',value);
		morseToChar.put(value,'m');
		value.clear();
		addLong(value,1); addShort(value,1); value.add(Morse.LETTER_END);
		charToMorse.put('n',value);
		morseToChar.put(value,'n');
		value.clear();
		addLong(value,3); value.add(Morse.LETTER_END);
		charToMorse.put('o',value);
		morseToChar.put(value,'o');
		value.clear();
		addShort(value,1); addLong(value,2); addShort(value,1); value.add(Morse.LETTER_END);
		charToMorse.put('p',value);
		morseToChar.put(value,'p');
		value.clear();
		addLong(value,2); addShort(value,1); addLong(value,3); value.add(Morse.LETTER_END);
		charToMorse.put('q',value);
		morseToChar.put(value,'q');
		value.clear();
		addShort(value,1); addLong(value,1); addShort(value,1); value.add(Morse.LETTER_END);
		charToMorse.put('r',value);
		morseToChar.put(value,'r');
		value.clear();
		addShort(value,3); value.add(Morse.LETTER_END);
		charToMorse.put('s',value);
		morseToChar.put(value,'s');
		value.clear();
		addLong(value,1);value.add(Morse.LETTER_END);
		charToMorse.put('t',value);
		morseToChar.put(value,'t');
		value.clear();
		addShort(value,2); addLong(value,1); value.add(Morse.LETTER_END);
		charToMorse.put('u',value);
		morseToChar.put(value,'u');
		value.clear();
		addShort(value,3); addLong(value,1); value.add(Morse.LETTER_END);
		charToMorse.put('v',value);
		morseToChar.put(value,'v');
		value.clear();
		addShort(value,1); addLong(value,2); value.add(Morse.LETTER_END);
		charToMorse.put('w',value);
		morseToChar.put(value,'w');
		value.clear();
		addLong(value,1);addShort(value,2); addLong(value,1);value.add(Morse.LETTER_END);
		charToMorse.put('x',value);
		morseToChar.put(value,'x');
		value.clear();
		addLong(value,1);addShort(value,1); addLong(value,2);value.add(Morse.LETTER_END);
		charToMorse.put('y',value);
		morseToChar.put(value,'y');
		value.clear();
		addLong(value,2);addShort(value,2);value.add(Morse.LETTER_END);
		charToMorse.put('z',value);
		morseToChar.put(value,'z');
		value.clear();
		addShort(value,1); addLong(value,4);value.add(Morse.LETTER_END);
		charToMorse.put('1',value);
		morseToChar.put(value,'1');
		value.clear();
		addShort(value,2); addLong(value,3);value.add(Morse.LETTER_END);
		charToMorse.put('2',value);
		morseToChar.put(value,'2');
		value.clear();
		addShort(value,3); addLong(value,2);value.add(Morse.LETTER_END);
		charToMorse.put('3',value);
		morseToChar.put(value,'3');
		value.clear();
		addShort(value,4); addLong(value,1);value.add(Morse.LETTER_END);
		charToMorse.put('4',value);
		morseToChar.put(value,'4');
		value.clear();
		addShort(value,5); value.add(Morse.LETTER_END);
		charToMorse.put('5',value);
		morseToChar.put(value,'5');
		value.clear();
		addLong(value,1);addShort(value,4);value.add(Morse.LETTER_END); 
		charToMorse.put('6',value);
		morseToChar.put(value,'6');
		value.clear();
		addLong(value,2);addShort(value,3);value.add(Morse.LETTER_END);
		charToMorse.put('7',value);
		morseToChar.put(value,'7');
		value.clear();
		addLong(value,3);addShort(value,2);value.add(Morse.LETTER_END);
		charToMorse.put('8',value);
		morseToChar.put(value,'8');
		value.clear();
		addLong(value,4);addShort(value,1);value.add(Morse.LETTER_END);
		charToMorse.put('9',value);
		morseToChar.put(value,'9');
		value.clear();
		addLong(value,5);value.add(Morse.LETTER_END);
		charToMorse.put('0',value);
		morseToChar.put(value,'0');
		value.clear();
		addShort(value,1); addLong(value,1); addShort(value,1); addLong(value,1);addShort(value,1); addLong(value,1);value.add(Morse.LETTER_END);
		charToMorse.put('.',value);
		morseToChar.put(value,'.');
		value.clear();
		value.add(Morse.WORD_END);
		charToMorse.put(' ',value);
		morseToChar.put(value,' ');
		value.clear();
	}
	
	private void addShort(ArrayList<Morse> value, int i ){
		while (i>0){
			value.add(Morse.SHORT);
		}
	}
	
	private void addLong(ArrayList<Morse> value, int i ){
		while (i>0){
			value.add(Morse.LONG);
		}
	}
}
