package io.extended;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.CharBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;

import io.base.PrimitiveWriter;

public class ExtendedPrimitiveWriter extends PrimitiveWriter {

	private final CharBuffer cbuf;
	private final ShortBuffer sbuf;
	private final IntBuffer ibuf;
	private final LongBuffer lbuf;
	private final FloatBuffer fbuf;
	private final DoubleBuffer dbuf;
	
	public ExtendedPrimitiveWriter(OutputStream out, int bufferSize) {
		super(out,  bufferSize);
		cbuf = buf.asCharBuffer();
		sbuf = buf.asShortBuffer();
		ibuf = buf.asIntBuffer();
		lbuf = buf.asLongBuffer();
		fbuf = buf.asFloatBuffer();
		dbuf = buf.asDoubleBuffer();
	}
	
	public synchronized void writeBytes(byte[] byteArray) throws IOException {
		out.write(byteArray);
	}

	public synchronized void writeChars(char[] charArray) throws IOException {
		buf.clear();
		cbuf.clear();
		int limit = 0;
		int completed = 0;
		while ((completed+=limit) != charArray.length) {
			limit = (cbuf.capacity() < charArray.length - completed ? cbuf.capacity() : charArray.length - completed);
			cbuf.put(charArray, completed, limit);
			buf.position(limit*2);
			write();
			cbuf.rewind();
		}
	}
	
	public synchronized void writeShorts(short[] shortArray) throws IOException {
		buf.clear();
		sbuf.clear();
		int limit = 0;
		int completed = 0;
		while ((completed+=limit) != shortArray.length) {
			limit = (sbuf.capacity() < shortArray.length - completed ? sbuf.capacity() : shortArray.length - completed);
			sbuf.put(shortArray, completed, limit);
			buf.position(limit*2);
			write();
			sbuf.rewind();
		}
	}
	
	public synchronized void writeInts(int[] intArray) throws IOException {
		buf.clear();
		ibuf.clear();
		int limit = 0;
		int completed = 0;
		while ((completed+=limit) != intArray.length) {
			limit = (ibuf.capacity() < intArray.length - completed ? ibuf.capacity() : intArray.length - completed);
			ibuf.put(intArray, completed, limit);
			buf.position(limit*4);
			write();
			ibuf.rewind();
		}
	}
	
	public synchronized void writeLongs(long[] longArray) throws IOException {
		buf.clear();
		lbuf.clear();
		int limit = 0;
		int completed = 0;
		while ((completed+=limit) != longArray.length) {
			limit = (lbuf.capacity() < longArray.length - completed ? lbuf.capacity() : longArray.length - completed);
			lbuf.put(longArray, completed, limit);
			buf.position(limit*8);
			write();
			lbuf.rewind();
		}
	}
	
	public synchronized void writeFloats(float[] floatArray) throws IOException {
		buf.clear();
		fbuf.clear();
		int limit = 0;
		int completed = 0;
		while ((completed+=limit) != floatArray.length) {
			limit = (fbuf.capacity() < floatArray.length - completed ? fbuf.capacity() : floatArray.length - completed);
			fbuf.put(floatArray, completed, limit);
			buf.position(limit*4);
			write();
			fbuf.rewind();
		}
	}
	
	public synchronized void writeDouble(double[] doubleArray) throws IOException {
		buf.clear();
		dbuf.clear();
		int limit = 0;
		int completed = 0;
		while ((completed+=limit) != doubleArray.length) {
			limit = (dbuf.capacity() < doubleArray.length - completed ? dbuf.capacity() : doubleArray.length - completed);
			dbuf.put(doubleArray, completed, limit);
			buf.position(limit*8);
			write();
			dbuf.rewind();
		}
	}

	public synchronized void close() throws IOException {
		out.close();
	}
	
}
