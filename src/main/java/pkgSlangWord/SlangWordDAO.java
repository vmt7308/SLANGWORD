/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgSlangWord;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author VMT
 */
public class SlangWordDAO {

    private static final String FILENAME = "slang.txt";
    private static final String BACKUPFILE = "backup.txt";

    public HashMap<String, ArrayList<String>> read() {
        HashMap<String, ArrayList<String>> slangwords = new HashMap<String, ArrayList<String>>();

        FileInputStream fis = null;
        ObjectInputStream ois = null;

        String thisLine = null;

        try {
            // open input stream test.txt for reading purpose.
            FileReader fr = new FileReader(FILENAME);
            BufferedReader br = new BufferedReader(fr);

            while ((thisLine = br.readLine()) != null) {
                //System.out.println(thisLine);
                //separate slang and define 
                try {
                    String part[] = thisLine.split("`");
                    String newSLang = part[0];

                    ArrayList<String> subDefine = new ArrayList<String>();
                    if (part[1] != null) {

                        String subPart[] = part[1].split("\\|");
                        for (String s : subPart) {
                            subDefine.add(s.trim());
                        }

                    } else {
                        System.out.println("value null");
                        subDefine.add("");
                    }
                    slangwords.put(newSLang, subDefine);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeStream(fis);
            closeStream(ois);
        }
        return slangwords;
    }

    public void createNewSlang(HashMap<String, ArrayList<String>> sll) {

    }

    public void copyFile() {
        FileInputStream fis = null;
        FileOutputStream fos = null;

        String thisLine = null;

        try {
            fis = new FileInputStream(BACKUPFILE);
            fos = new FileOutputStream(FILENAME);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = fis.read(buffer)) > 0) {
                fos.write(buffer, 0, length);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeStream(fis);

        }
    }

    public synchronized void save(HashMap<String, ArrayList<String>> sll) {
        FileOutputStream fos = null;
        OutputStreamWriter osw = null;

        try {
            fos = new FileOutputStream(new File(FILENAME));
            osw = new OutputStreamWriter(fos);

            for (Map.Entry<String, ArrayList<String>> entry : sll.entrySet()) {
                StringBuilder sb = new StringBuilder();
                for (String s : entry.getValue()) {
                    sb.append(s);
                    if (s != entry.getValue().get(entry.getValue().size() - 1)) {
                        sb.append("| ");
                    }
                }
                String temp = entry.getKey().toString() + "`" + sb.toString() + "\n";

                osw.write(temp);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeStream(fos);
            closeStream(osw);
        }
    }

    private void closeStream(InputStream is) {
        if (is != null) {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void closeStream(OutputStream os) {
        if (os != null) {
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void closeStream(OutputStreamWriter os) {
        if (os != null) {
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
