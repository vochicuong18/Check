package company.demo.ultis;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class FileUtility {
    private File file;
    private FileWriter fw;
    private BufferedWriter bw;
    private BufferedReader br;

    public List<String> readFile(String filePath) {
        String line;
        FileReader fr;
        List<String> lines = new ArrayList<>();
        try {
            file = new File(filePath);
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            try {
                while ((line = br.readLine()) != null) {
                    lines.add(line);
                }
                fr.close();
                br.close();
            } catch (IOException ioex) {
                return null;
            }

        } catch (FileNotFoundException ex) {
            return null;
        }
        return lines;
    }

    public void writeFile(String filePath, String... lines) {
        file = new File(filePath);
        try {
            fw = new FileWriter(file);
            bw = new BufferedWriter(fw);
            for (String shipment : lines) {
                bw.write(shipment);
            }
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write(String path, String... content) {
        try {
            file = new File(path);
            bw = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(file), StandardCharsets.UTF_8));
            for (String str : content) {
                bw.write(str);
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write(String path, List<String> content){
        try {
            file = new File(path);
            fw = new FileWriter(file);
            bw = new BufferedWriter(fw);
            for(String str : content)
                bw.write(str);
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void copyFile(String from, String to, Boolean overwrite) {

        try {
            File fromFile = new File(from);
            File toFile = new File(to);

            if (!fromFile.exists()) {
                throw new IOException("File not found: " + from);
            }
            if (!fromFile.isFile()) {
                throw new IOException("Can't copy directories: " + from);
            }
            if (!fromFile.canRead()) {
                throw new IOException("Can't read file: " + from);
            }

            if (toFile.isDirectory()) {
                toFile = new File(toFile, fromFile.getName());
            }

            if (toFile.exists() && !overwrite) {
                throw new IOException("File already exists.");
            } else {
                String parent = toFile.getParent();
                if (parent == null) {
                    parent = System.getProperty("user.dir");
                }
                File dir = new File(parent);
                if (!dir.exists()) {
                    throw new IOException("Destination directory does not exist: " + parent);
                }
                if (dir.isFile()) {
                    throw new IOException("Destination is not a valid directory: " + parent);
                }
                if (!dir.canWrite()) {
                    throw new IOException("Can't write on destination: " + parent);
                }
            }

            FileInputStream fis = null;
            FileOutputStream fos = null;
            try {

                fis = new FileInputStream(fromFile);
                fos = new FileOutputStream(toFile);
                byte[] buffer = new byte[4096];
                int bytesRead;

                while ((bytesRead = fis.read(buffer)) != -1) {
                    fos.write(buffer, 0, bytesRead);
                }

            } finally {
                if (from != null) {
                    try {
                        fis.close();
                    } catch (IOException | NullPointerException e) {
                        e.printStackTrace();
                    }
                }
                if (to != null) {
                    try {
                        fos.close();
                    } catch (IOException | NullPointerException e) {
                        e.printStackTrace();
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteFolder(String folder) {
        deleteFolder(new File(folder));
    }

    public void deleteFolder(File folder) {
        if (folder.exists()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (int i = 0; i < files.length; i++) {
                    if (files[i].isDirectory()) {
                        deleteFolder(files[i]);
                    } else {
                        files[i].delete();
                    }
                }
            }
        }

    }

    public void createFolder(File folder){
        if(!folder.isDirectory())
            folder.mkdir();
    }

    public String[] getAllFileNameInFolder(String folder) {
        String[] lstFileName = null;
        file = new File(folder);
        if (file.exists()) {
            File[] files = file.listFiles();
            lstFileName = new String[files.length];
            if (files != null) {
                for (int i = 0; i < files.length; i++) {
                    lstFileName[i] = files[i].getName();
                }
            }
        }

        return lstFileName;
    }

    public List<String> read(String path){
        String line;
        List<String> content = new ArrayList<>();

        try {
            br = new BufferedReader(new FileReader(path));
            try {
                line = br.readLine();
                while(line!=null){
                    content.add(line);
                    line = br.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return content;
    }
}
