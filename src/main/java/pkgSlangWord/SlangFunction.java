/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgSlangWord;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 *
 * @author VMT
 */
public class SlangFunction {
    private HashMap<String, ArrayList<String>> slangWords;
    private SlangWordDAO slangWordDAO; 
    
    public SlangFunction()
    {
        slangWordDAO = new SlangWordDAO();
        slangWords = slangWordDAO.read();
        System.out.println("here");
        Set<String> keySet = slangWords.keySet();
        System.out.println("Loading slang word list" );
        for (String key : keySet) {
            System.out.println(key + " " + slangWords.get(key));
        }
        System.out.println("End loading  slang word list");
    }
    
    public HashMap<String, ArrayList<String>> getSlangWordList()
    {
        return slangWords;
    }
    
    public void CreateNewSlang(HashMap<String, ArrayList<String>> sl)
    {
        slangWordDAO.createNewSlang(sl);
    }
    public void save(HashMap<String, ArrayList<String>> sl)
    {
        slangWordDAO.save(sl);
        
    }
    
    public void reset()
    {
        slangWordDAO.copyFile();
        slangWordDAO.read();
    }
}
