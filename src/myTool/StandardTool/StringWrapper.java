package myTool.StandardTool;

/**
 * Created with IntelliJ IDEA.
 * User: Nguyen Trung Hieu
 * Date: 10/3/12
 * Time: 7:46 PM
 * To change this template use File | Settings | File Templates.
 */
class StringWrapper extends AbstractSequence<Character> {
    private final CharSequence string;

    public static Sequence<Character> wrap(CharSequence string) {
        return new StringWrapper(string);
    }

    private StringWrapper(CharSequence string) {
        this.string = string;
    }

    public int size() {
        return string.length();
    }

    public Character get(int index) {
        return string.charAt(index);
    }
}
