package io.extended;

import java.io.IOException;
import java.io.InputStream;
import java.nio.CharBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;

import io.base.PrimitiveReader;

public class ExtendedPrimitiveReader extends PrimitiveReader {
	
	private final CharBuffer cbuf;
	private final ShortBuffer sbuf;
	private final IntBuffer ibuf;
	private final LongBuffer lbuf;
	private final FloatBuffer fbuf;
	private final DoubleBuffer dbuf;

	public ExtendedPrimitiveReader(InputStream in, int bufferSize) {
		super(in, bufferSize);
		cbuf = buf.asCharBuffer();
		sbuf = buf.asShortBuffer();
		ibuf = buf.asIntBuffer();
		lbuf = buf.asLongBuffer();
		fbuf = buf.asFloatBuffer();
		dbuf = buf.asDoubleBuffer();
	}
	
	public synchronized int readBytes(byte[] byteArray) throws IOException {
		return in.read(byteArray);
	}
	
	public synchronized int readChars(char[] charArray) throws IOException {
		int limit = buf.capacity();
		int completed = 0;
		int read = 0;
		byte[] underflow = new byte[1];
		limit -= limit % 2;
		do {
			limit = (limit/2 < charArray.length - completed ? limit : 2*(charArray.length - completed));
			read = read(limit, underflow, 0, read%2);
			cbuf.rewind();
			cbuf.limit(read/2);
			cbuf.get(charArray, completed, read/2);
			completed += read/2;
			buf.position(buf.limit() - read%2);
			buf.get(underflow, 0, read%2);
		} while (read == limit && completed != charArray.length);
		return completed;
	}
	
	public synchronized int readShorts(short[] shortArray) throws IOException {
		int limit = buf.capacity();
		int completed = 0;
		int read = 0;
		byte[] underflow = new byte[2-1];
		limit -= limit % 2;
		do {
			limit = (limit/2 < shortArray.length - completed ? limit : 2*(shortArray.length - completed));
			read = read(limit, underflow, 0, read%2);
			sbuf.rewind();
			sbuf.limit(read/2);
			sbuf.get(shortArray, completed, read/2);
			completed += read/2;
			buf.position(buf.limit() - read%2);
			buf.get(underflow, 0, read%2);
		} while (read == limit && completed != shortArray.length);
		return completed;
	}
	
	public synchronized int readInts(int[] intArray) throws IOException {
		int limit = buf.capacity();
		int completed = 0;
		int read = 0;
		byte[] underflow = new byte[4-1];
		limit -= limit % 4;
		do {
			limit = (limit/4 < intArray.length - completed ? limit : 4*(intArray.length - completed));
			read = read(limit, underflow, 0, read%4);
			ibuf.rewind();
			ibuf.limit(read/4);
			ibuf.get(intArray, completed, read/4);
			completed += read/4;
			buf.position(buf.limit() - read%4);
			buf.get(underflow, 0, read%4);
		} while (read == limit && completed != intArray.length);
		return completed;
	}
	
	public synchronized int readLongs(long[] longArray) throws IOException {
		int limit = buf.capacity();
		int completed = 0;
		int read = 0;
		byte[] underflow = new byte[8-1];
		limit -= limit % 8;
		do {
			limit = (limit/8 < longArray.length - completed ? limit : 8*(longArray.length - completed));
			read = read(limit, underflow, 0, read%8);
			lbuf.rewind();
			lbuf.limit(read/8);
			lbuf.get(longArray, completed, read/8);
			completed += read/8;
			buf.position(buf.limit() - read%8);
			buf.get(underflow, 0, read%8);
		} while (read != -1 && read != 0 && completed != longArray.length);
		return completed;
	}
	
	public synchronized int readFloats(float[] floatArray) throws IOException {
		int limit = buf.capacity();
		int completed = 0;
		int read = 0;
		byte[] underflow = new byte[4-1];
		limit -= limit % 4;
		do {
			limit = (limit/4 < floatArray.length - completed ? limit : 4*(floatArray.length - completed));
			read = read(limit, underflow, 0, read%4);
			fbuf.rewind();
			fbuf.limit(read/4);
			fbuf.get(floatArray, completed, read/4);
			completed += read/4;
			buf.position(buf.limit() - read%4);
			buf.get(underflow, 0, read%4);
		} while (read == limit && completed != floatArray.length);
		return completed;
	}
	
	public synchronized int readDoubles(double[] doubleArray) throws IOException {
		int limit = buf.capacity();
		int completed = 0;
		int read = 0;
		byte[] underflow = new byte[8-1];
		limit -= limit % 8;
		do {
			limit = (limit/8 < doubleArray.length - completed ? limit : 8*(doubleArray.length - completed));
			read = read(limit, underflow, 0, read%8);
			dbuf.rewind();
			dbuf.limit(read/8);
			dbuf.get(doubleArray, completed, read/8);
			completed += read/8;
			buf.position(buf.limit() - read%8);
			buf.get(underflow, 0, read%8);
		} while (read == limit && completed != doubleArray.length);
		return completed;
	}

}
