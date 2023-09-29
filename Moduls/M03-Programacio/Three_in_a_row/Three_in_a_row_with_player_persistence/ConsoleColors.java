public interface ConsoleColors {
    // Reset
    public static final String reset = "\033[0m"; // Text Reset

    // Regular Colors
    public static final String black = "\033[0;30m"; // black
    public static final String red = "\033[0;31m"; // red
    public static final String green = "\033[0;32m"; // green
    public static final String yellow = "\033[0;33m"; // yellow
    public static final String blue = "\033[0;34m"; // blue
    public static final String purple = "\033[0;35m"; // purple
    public static final String cyan = "\033[0;36m"; // cyan
    public static final String white = "\033[0;37m"; // white

    // Bold
    public static final String black_BOLD = "\033[1;30m"; // black
    public static final String red_BOLD = "\033[1;31m"; // red
    public static final String green_BOLD = "\033[1;32m"; // green
    public static final String yellow_BOLD = "\033[1;33m"; // yellow
    public static final String blue_BOLD = "\033[1;34m"; // blue
    public static final String purple_BOLD = "\033[1;35m"; // purple
    public static final String cyan_BOLD = "\033[1;36m"; // cyan
    public static final String white_BOLD = "\033[1;37m"; // white

    // Underline
    public static final String black_UNDERLINED = "\033[4;30m"; // black
    public static final String red_UNDERLINED = "\033[4;31m"; // red
    public static final String green_UNDERLINED = "\033[4;32m"; // green
    public static final String yellow_UNDERLINED = "\033[4;33m"; // yellow
    public static final String blue_UNDERLINED = "\033[4;34m"; // blue
    public static final String purple_UNDERLINED = "\033[4;35m"; // purple
    public static final String cyan_UNDERLINED = "\033[4;36m"; // cyan
    public static final String white_UNDERLINED = "\033[4;37m"; // white

    // Background
    public static final String black_BACKGROUND = "\033[40m"; // black
    public static final String red_BACKGROUND = "\033[41m"; // red
    public static final String green_BACKGROUND = "\033[42m"; // green
    public static final String yellow_BACKGROUND = "\033[43m"; // yellow
    public static final String blue_BACKGROUND = "\033[44m"; // blue
    public static final String purple_BACKGROUND = "\033[45m"; // purple
    public static final String cyan_BACKGROUND = "\033[46m"; // cyan
    public static final String white_BACKGROUND = "\033[47m"; // white

    // High Intensity backgrounds
    public static final String black_BACKGROUND_BRIGHT = "\033[0;100m";// black
    public static final String red_BACKGROUND_BRIGHT = "\033[0;101m";// red
    public static final String green_BACKGROUND_BRIGHT = "\033[0;102m";// green
    public static final String yellow_BACKGROUND_BRIGHT = "\033[0;103m";// yellow
    public static final String blue_BACKGROUND_BRIGHT = "\033[0;104m";// blue
    public static final String purple_BACKGROUND_BRIGHT = "\033[0;105m"; // purple
    public static final String cyan_BACKGROUND_BRIGHT = "\033[0;106m"; // cyan
    public static final String white_BACKGROUND_BRIGHT = "\033[0;107m"; // white

    public static final String[] colors = {black,red,green,yellow,blue,purple,cyan,white,black_BOLD,red_BOLD,green_BOLD,yellow_BOLD,blue_BOLD,purple_BOLD,cyan_BOLD,white_BOLD,black,red,green,yellow,blue,purple,cyan,white};
}