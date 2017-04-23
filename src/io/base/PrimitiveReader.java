package io.base;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class PrimitiveReader {
	protected final InputStream in;
	protected final ByteBuffer buf;

	public PrimitiveReader(InputStream in) {
		this(in, 8);
	}

	protected PrimitiveReader(InputStream in, int size) {
		if (size < 8)
			throw new IllegalArgumentException("Minimum Buffer Size is 8!");
		if (in == null)
			throw new NullPointerException("No InputStream was given!");
		this.in = in;
		buf = ByteBuffer.wrap(new byte[size]);
	}
	
	public synchronized void close() throws IOException {
		in.close();
	}

	protected synchronized int read(int len) throws IOException {
		buf.clear();
		int read = in.read(buf.array(), 0, len);
		if (read < 0)
			return read;
		buf.position(read);
		buf.flip();
		return read;
	}
	
	protected synchronized int read(int read_length, byte[] prepend) throws IOException {
		return read(read_length, prepend, 0, prepend.length);
	}
	
	protected synchronized int read(int read_length, byte[] prepend, int off, int len) throws IOException {
		if (len + read_length > buf.capacity())
			throw new IOException("Insufficient buffer size!");
		buf.clear();
		buf.put(prepend, off, len);
		int read = in.read(buf.array(), len, read_length-len);
		buf.position(read + len);
		return read+len;
	}

	public synchronized byte readByte() throws IOException {
		if (read(1)  != 1)
			throw new IOException("Unable to read byte");
		return buf.get();
	}

	public synchronized char readChar() throws IOException {
		if (read(2)  != 2)
			throw new IOException("Unable to read char");
		return buf.getChar();
	}

	public synchronized short readShort() throws IOException {
		if (read(2)  != 2)
			throw new IOException("Unable to read short");
		return buf.getShort();
	}

	public synchronized int readInt() throws IOException {
		if (read(4)  != 4)
			throw new IOException("Unable to read int");
		return buf.getInt();
	}

	public synchronized long readLong() throws IOException {
		if (read(8) != 8)
			throw new IOException("Unable to read long");
		return buf.getLong();
	}

	public synchronized float readFloat() throws IOException {
		if (read(4)  != 4)
			throw new IOException("Unable to read float");
		return buf.getFloat();
	}

	public synchronized double readDouble() throws IOException {
		if (read(8)  != 8)
			throw new IOException("Unable to read double");
		return buf.getDouble();
	}

}
