package design;

import java.awt.Color;
import javax.swing.ImageIcon;

//This abstract class contains the static design's attributes 
//This will make customization easier, if we change any value here 
//the whole application colors will be changed
public abstract class Design {

    public static Color BACK_COLOR = Color.DARK_GRAY;
    public static Color FONT_COLOR = Color.WHITE;
    public static final int FONT_SIZE_SMALL = 12;
    public static final int FONT_SIZE_MEDIUM = 16;
    public static final int FONT_SIZE_LARGE = 32;
    public static final String FONT_FAMILY = "Arial";
    public static final ImageIcon ICON = new ImageIcon("images/logo.png");
}