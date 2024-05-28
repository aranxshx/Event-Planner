import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FontLoader {

    private static final String FONT_PATH = "./assets/Fonts/";
    private static Map<String, Font> fonts = new HashMap<>();

    static {
        try {
            fonts.put("Inter-Black", Font.createFont(Font.TRUETYPE_FONT, new File(FONT_PATH + "Inter-Black.ttf")));
            fonts.put("Inter-Bold", Font.createFont(Font.TRUETYPE_FONT, new File(FONT_PATH + "Inter-Bold.ttf")));
            fonts.put("Inter-ExtraBold", Font.createFont(Font.TRUETYPE_FONT, new File(FONT_PATH + "Inter-ExtraBold.ttf")));
            fonts.put("Inter-ExtraLight", Font.createFont(Font.TRUETYPE_FONT, new File(FONT_PATH + "Inter-ExtraLight.ttf")));
            fonts.put("Inter-Medium", Font.createFont(Font.TRUETYPE_FONT, new File(FONT_PATH + "Inter-Medium.ttf")));
            fonts.put("Inter-Regular", Font.createFont(Font.TRUETYPE_FONT, new File(FONT_PATH + "Inter-Regular.ttf")));
            fonts.put("Inter-Semibold", Font.createFont(Font.TRUETYPE_FONT, new File(FONT_PATH + "Inter-Semibold.ttf")));
            fonts.put("Inter-Thin", Font.createFont(Font.TRUETYPE_FONT, new File(FONT_PATH + "Inter-Thin.ttf")));
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    public static Font getFont(String fontName, float size) {
        Font font = fonts.get(fontName);
        if (font != null) {
            return font.deriveFont(size);
        } else {
            throw new IllegalArgumentException("Font " + fontName + " not found.");
        }
    }
}
