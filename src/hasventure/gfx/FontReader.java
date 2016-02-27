/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hasventure.gfx;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author Tobias
 */
public class FontReader {
    
    private static Font f;
    
    public static Font loadFont(String path) throws FontFormatException, IOException{
        final ClassLoader classLoader = FontReader.class.getClassLoader();
        try (InputStream is = classLoader.getResourceAsStream("/fonts/Title.ttf")) {
            f = Font.createFont(Font.TRUETYPE_FONT, is);
            f = f.deriveFont(Font.BOLD, 16);
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(f);
            return f;
        } 
    }
}
