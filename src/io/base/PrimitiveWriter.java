package io.base;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;

public class PrimitiveWriter {
	protected final OutputStream out;
	protected final ByteBuffer buf;
	public PrimitiveWriter(OutputStream out) {
		this(out, 8);
	}
	
	protected PrimitiveWriter(OutputStream out, int size) {
		if (size < 8)
			throw new IllegalArgumentException("Minimum Buffer Size is 8!");
		this.out = out;
		buf = ByteBuffer.wrap(new byte[size]);
	}
	
	protected synchronized void write() throws IOException {
		buf.flip();
		out.write(buf.array(), 0, buf.limit());
		buf.clear();
	}
	
	public synchronized void writeByte(byte b) throws IOException {
		buf.put(b);
		write();
	}
	
	public synchronized void writeChar(char c) throws IOException {
		buf.putChar(c);
		write();
	}
	
	public synchronized void writeShort(short s) throws IOException {
		buf.putShort(s);
		write();
	}
	
	public synchronized void writeInt(int i) throws IOException {
		buf.putInt(i);
		write();
	}
	
	public synchronized void writeLong(long l) throws IOException {
		buf.putLong(l);
		write();
	}
	
	public synchronized void writeFloat(float f) throws IOException {
		buf.putFloat(f);
		write();
	}
	
	public synchronized void writeDouble(double d) throws IOException {
		buf.putDouble(d);
		write();
	}
}
