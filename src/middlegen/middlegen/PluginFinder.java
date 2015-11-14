/*
 * Copyright (c) 2001, Aslak Helles�y, BEKK Consulting
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *
 * - Redistributions of source code must retain the above copyright notice,
 *   this list of conditions and the following disclaimer.
 *
 * - Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in the
 *   documentation and/or other materials provided with the distribution.
 *
 * - Neither the name of BEKK Consulting nor the names of its
 *   contributors may be used to endorse or promote products derived from
 *   this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE REGENTS OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
 * OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH
 * DAMAGE.
 */

/*
 * Change log
 *
 */
package middlegen;

import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * This class finds plugins on the classpath defined in the syste
 *
 * @author Aslak Helles�y
 * @created 27. mai 2002
 */
class PluginFinder {

	/**
	 * Describe what the method does
	 *
	 * @param middlegen Describe what the parameter does
	 * @param classpath Describe what the parameter does
	 * @todo-javadoc Write javadocs for method
	 * @todo-javadoc Write javadocs for method parameter
	 * @todo-javadoc Write javadocs for method parameter
	 */
	public static void registerPlugins(Middlegen middlegen, String classpath) {
		Collection pluginFiles = findPluginFiles(classpath);
		Iterator pluginFileIterator = pluginFiles.iterator();

		while (pluginFileIterator.hasNext()) {
			File file = (File)pluginFileIterator.next();

			try {
				InputStream deploymentDescriptor = null;

				if (file.isDirectory()) {
					deploymentDescriptor = new FileInputStream(new File(file, "META-INF" + File.separator + "middlegen-plugin.xml"));
				}
				else {
					JarFile jar = new JarFile(file);
					JarEntry middlegenPluginXml = jar.getJarEntry("META-INF/middlegen-plugin.xml");

					if (middlegenPluginXml != null) {
						deploymentDescriptor = jar.getInputStream(middlegenPluginXml);
					}
					else {
						//log.info("Skipping " + file.getAbsolutePath() + " (No META-INF/middlegen-plugin.xml in here)");
					}
				}
				if (deploymentDescriptor != null) {
					parse(deploymentDescriptor, middlegen);
				}
			} catch (IOException e) {
				throw new IllegalStateException("Error looking for plugins:" + e.getMessage());
			}
		}

//		log.info("Finished. Registered " + modules.size() + " modules.");
	}


	/**
	 * Describe what the method does
	 *
	 * @param deploymentDescriptor Describe what the parameter does
	 * @param middlegen Describe what the parameter does
	 * @todo-javadoc Write javadocs for method
	 * @todo-javadoc Write javadocs for method parameter
	 * @todo-javadoc Write javadocs for method parameter
	 */
	private static void parse(InputStream deploymentDescriptor, Middlegen middlegen) {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		factory.setValidating(false);

		try {
			SAXParser parser = factory.newSAXParser();

			parser.parse(deploymentDescriptor, new Handler(middlegen));
			deploymentDescriptor.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new IllegalStateException("Error reading META-INF/midldegen-plugin.xml" + Middlegen.BUGREPORT);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw new IllegalStateException("Error reading META-INF/midldegen-plugin.xml" + Middlegen.BUGREPORT);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			throw new IllegalStateException("Error reading META-INF/midldegen-plugin.xml" + Middlegen.BUGREPORT);
		} catch (SAXException e) {
			e.printStackTrace();
			throw new IllegalStateException("Error reading META-INF/midldegen-plugin.xml" + Middlegen.BUGREPORT);
		}
	}


	/**
	 * Describe what the method does
	 *
	 * @param classpath Describe what the parameter does
	 * @return Describe the return value
	 * @todo-javadoc Write javadocs for method
	 * @todo-javadoc Write javadocs for method parameter
	 * @todo-javadoc Write javadocs for return value
	 */
	private static Collection findPluginFiles(String classpath) {
		if (classpath == null) {
			throw new IllegalStateException("classpath can't be null");
		}

		Collection result = new ArrayList();

		StringTokenizer pathTokenizer = new StringTokenizer(classpath, System.getProperty("path.separator"));

		while (pathTokenizer.hasMoreTokens()) {
			File file = new File(pathTokenizer.nextToken());

			if (file.isDirectory()) {
				// a module doesn't have to be a jar. can be a straight directory too.
				if (new File(file, "META-INF" + File.separator + "middlegen-plugin.xml").exists()) {
					result.add(file);
				}
			}
			else if (file.getName().endsWith(".jar")) {
				result.add(file);
			}
		}
		return result;
	}


	/**
	 * Describe what this class does
	 *
	 * @author Aslak Helles�y
	 * @created 27. mai 2002
	 * @todo-javadoc Write javadocs
	 */
	private static class Handler extends DefaultHandler {
		/**
		 * @todo-javadoc Describe the field
		 */
		private final Middlegen _middlegen;


		/**
		 * Describe what the Handler constructor does
		 *
		 * @param middlegen Describe what the parameter does
		 * @todo-javadoc Write javadocs for constructor
		 * @todo-javadoc Write javadocs for method parameter
		 */
		public Handler(Middlegen middlegen) {
			_middlegen = middlegen;
		}


		/**
		 * Describe what the method does
		 *
		 * @param namespaceURI Describe what the parameter does
		 * @param localName Describe what the parameter does
		 * @param qName Describe what the parameter does
		 * @param attributes Describe what the parameter does
		 * @todo-javadoc Write javadocs for method
		 * @todo-javadoc Write javadocs for method parameter
		 * @todo-javadoc Write javadocs for method parameter
		 * @todo-javadoc Write javadocs for method parameter
		 * @todo-javadoc Write javadocs for method parameter
		 */
		public void startElement(String namespaceURI, String localName, String qName, Attributes attributes) {
			if (qName.equals("plugin")) {
				// Instantiate and register the plugin
				String pluginClassName = attributes.getValue("class-name");
				if (pluginClassName == null) {
					throw new IllegalStateException("Missing class-name attribute in middlegen-plugin.xml");
				}
				String pluginName = attributes.getValue("name");
				if (pluginName == null) {
					throw new IllegalStateException("Missing name attribute in middlegen-plugin.xml");
				}
				try {
					Class pluginClass = Class.forName(pluginClassName);
					_middlegen.registerPlugin(pluginName, pluginClass);
				} catch (Exception e) {
					e.printStackTrace();
					throw new IllegalStateException("Couldn't instantiate " + pluginClassName + ".");
				}
			}
		}
	}
}
