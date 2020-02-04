package de.flxw.admintools.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class FileCopy {

    public void copy(InputStream in, File file)
    {
        try
        {
            OutputStream out = new FileOutputStream(file);
            byte[] buf = new byte[1024];
            int len;
            while((len=in.read(buf))>0)
            {
                out.write(buf,0,len);
            }
            out.close();
            in.close();
        }
        catch(Exception e)
        {
            System.out.println("An Error occurred while coping the config file. Make sure that your config.yml is up to date. You can just delete the file or leave a look on the spigotmc site, if you don't want to delete your file");
        }
    }
}
