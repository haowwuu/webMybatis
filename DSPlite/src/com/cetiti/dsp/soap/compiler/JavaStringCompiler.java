package com.cetiti.dsp.soap.compiler;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import javax.tools.JavaCompiler.CompilationTask;

/**
 * In-memory compile Java source code as String.
 * 
 * @author    Wuwuhao
 * @version   DSPlite V0.2.0, 2016-10-20
 * @see       
 * @since     DSPlite V0.2.0
 */
public class JavaStringCompiler {

	private JavaCompiler compiler;
	private StandardJavaFileManager stdManager;

	public JavaStringCompiler() {
		this.compiler = ToolProvider.getSystemJavaCompiler();
		this.stdManager = compiler.getStandardFileManager(null, null, null);
	}

	/**
	 * Compile a Java source file in memory.
	 * 
	 * @param fileName
	 *            Java file name, e.g. "Test.java"
	 * @param source
	 *            The source code as String.
	 * @return The compiled results as Map that contains class name as key,
	 *         class binary as value.
	 * @throws IOException
	 *             If compile error.
	 */
	public Map<String, byte[]> compile(String fileName, String source) throws Exception {
		try (MemoryJavaFileManager manager = new MemoryJavaFileManager(stdManager)) {
			JavaFileObject javaFileObject = manager.makeStringSource(fileName, source);
			
			URL classPath = Thread.currentThread().getContextClassLoader().getResource("");
			File file = new File(classPath.toURI());
			String filePath = file.getPath();
			String classpath = System.getProperty("java.class.path");
			char sp = classpath.charAt(classpath.indexOf(".jar")+4);
			filePath += sp+classpath;
			List<String> optionList = new ArrayList<String>();
			// set compiler's classpath to be same as the runtime's
			optionList.addAll(Arrays.asList("-classpath", filePath));
			
			CompilationTask task = compiler.getTask(null, manager, null, optionList, null, Arrays.asList(javaFileObject));
			Boolean result = task.call();
			if (result == null || !result.booleanValue()) {
				throw new RuntimeException("Compilation failed.");
			}
			return manager.getClassBytes();
		}
	}

	/**
	 * Load class from compiled classes.
	 * 
	 * @param name
	 *            Full class name.
	 * @param classBytes
	 *            Compiled results as a Map.
	 * @return The Class instance.
	 * @throws ClassNotFoundException
	 *             If class not found.
	 * @throws IOException
	 *             If load error.
	 */
	public Class<?> loadClass(String name, Map<String, byte[]> classBytes) throws ClassNotFoundException, IOException {
		try (MemoryClassLoader classLoader = new MemoryClassLoader(classBytes)) {
			return classLoader.loadClass(name);
		}
	}
}
