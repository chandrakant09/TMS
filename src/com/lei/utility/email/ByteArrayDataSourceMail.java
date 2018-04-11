package com.lei.utility.email;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import javax.activation.DataSource;
/**
 * 
 * @author Shrikant Kushwaha
 *
 */
class ByteArrayDataSourceMail implements DataSource {
	private byte[] data; // data for mail message
	private final String type; // mime type

	/**
	 * Create a DataSource from a String
	 * 
	 * @param data
	 *            is the contents of the mail message
	 * @param type
	 *            is the mime-type such as text/html
	 */
	ByteArrayDataSourceMail(final String data, final String type) {
		try {
			// Assumption that the string contains only ascii
			// characters ! Else just pass in a charset into this
			// constructor and use it in getBytes()
			this.data = data.getBytes("iso-8859-1");
		} catch (final UnsupportedEncodingException uex) {
		}
		this.type = type;
	}

	// DataSource interface methods
	/**
	 * This method returns an InputStream for the data to be read.
	 * 
	 * @return <code>InputStream</code>
	 * @throws IOException
	 */
	@Override
	public InputStream getInputStream() throws IOException {
		if (this.data == null) {
			throw new IOException("no data");
		}
		return new ByteArrayInputStream(this.data);
	}

	/**
	 * This method returns an OutputStream for the data to be sent.
	 * 
	 * @return <code>OutputStream</code>
	 * @throws IOException
	 */
	@Override
	public OutputStream getOutputStream() throws IOException {
		throw new IOException("cannot do this");
	}

	/**
	 * Abstract method of ByteArrayDataSourceMail must be implimented to compile
	 * code Not used
	 * 
	 * @return String contentType
	 */
	@Override
	public String getContentType() {
		return this.type;
	}

	/**
	 * Abstract method of ByteArrayDataSourceMail must be implimented to compile
	 * code
	 * 
	 * @return String name
	 */
	@Override
	public String getName() {
		return "dummy";
	}
}