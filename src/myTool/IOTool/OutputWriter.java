package myTool.IOTool;

import myTool.DataStructureTool.IntCollection;
import myTool.DataStructureTool.IntIterator;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: Nguyen Trung Hieu
 * Date: 9/29/12
 * Time: 11:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class OutputWriter {
    private final PrintWriter writer;

    public OutputWriter(OutputStream outputStream) {
        writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
    }

    public OutputWriter(Writer writer) {
        this.writer = new PrintWriter(writer);
    }

    public void print(long[] array) {
        for (int i = 0; i < array.length; i++) {
            if (i != 0)
                writer.print(' ');
            writer.print(array[i]);
        }
    }

    public void printLine(long[] array) {
        print(array);
        writer.println();
    }

    public void print(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (i != 0)
                writer.print(' ');
            writer.print(array[i]);
        }
    }

    public void printLine(int[] array) {
        print(array);
        writer.println();
    }

    public void print(IntCollection collection) {
        boolean first = true;
        for (IntIterator iterator = collection.iterator(); iterator.isValid(); iterator.advance()) {
            if (first)
                first = false;
            else
                writer.print(' ');
            writer.print(iterator.value());
        }
    }

    public void printLine(IntCollection collection) {
        print(collection);
        writer.println();
    }

    public void printFormat(String format, Object...objects) {
        writer.printf(format, objects);
    }

    public void print(Object...objects) {
        for (int i = 0; i < objects.length; i++) {
            if (i != 0)
                writer.print(' ');
            writer.print(objects[i]);
        }
    }

    public void printLine(Object...objects) {
        print(objects);
        writer.println();
    }

    public void printLine(char[] array) {
        writer.println(array);
    }

    public void close() {
        writer.close();
    }

}