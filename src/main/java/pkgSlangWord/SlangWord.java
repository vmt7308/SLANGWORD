/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgSlangWord;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author VMT
 */
public class SlangWord implements Serializable{
    private String slangWord;
    private ArrayList<String> define;
    
    public SlangWord(String slangWord, ArrayList<String> define){
        this.slangWord = slangWord;
        this.define = define;
    }
    
    public String getSlangWord(){
        return slangWord;
    }
    
    public void setSlangWord(String slangWord){
        this.slangWord = slangWord;
    }
    
    public ArrayList<String> getDefine(){
        return  define;
    }
    
    public void setDefine(ArrayList<String> define){
        this.define = define;
    }
}
