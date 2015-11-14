/*
 * Copyright (c) 2001, Aslak Hellesøy, BEKK Consulting
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
package middlegen;

import java.io.*;
import java.net.URL;
import java.net.MalformedURLException;
import java.util.Map;
import java.util.HashMap;
import java.util.Collection;
import java.text.MessageFormat;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ResourceNotFoundException;

/**
 * FileProducer objects hold all information required for the generation of one
 * file. Each FileProducer instance will generate one physical file.
 *
 * @author Aslak Hellesøy
 * @created 17. juni 2002
 * @todo-javadoc Write javadocs
 */
public final class FileProducer {
   /**
    * @todo-javadoc Describe the field
    */
   private File _destinationDir;
   /**
    * @todo-javadoc Describe the field
    */
   private String _destinationFileName;
   /**
    * @todo-javadoc Describe the field
    */
   private URL _template;
   /**
    * @todo-javadoc Describe the field
    */
   private final Map _contexMap = new HashMap();

   /**
    * @todo-javadoc Describe the field
    */
   private Map _tableElements = new HashMap();

   /**
    * @todo-javadoc Describe the field
    */
   private String _id;

   /**
    * @todo-javadoc Describe the field
    */
   private boolean _isCustom;

   /**
    * Get static reference to Log4J Logger
    */
   private static org.apache.log4j.Category _log = org.apache.log4j.Category.getInstance(FileProducer.class.getName());


   /**
    * Empty constructor. Used by Ant.
    */
   public FileProducer() {
      _isCustom = true;
   }


   /**
    * Describe what the DefaultFileProducer constructor does
    *
    * @param destinationFileName Describe what the parameter does
    * @param template Describe what the parameter does
    * @param destinationDir Describe what the parameter does
    * @todo-javadoc Write javadocs for method parameter
    * @todo-javadoc Write javadocs for constructor
    * @todo-javadoc Write javadocs for method parameter
    * @todo-javadoc Write javadocs for method parameter
    * @todo-javadoc Write javadocs for method parameter
    * @todo-javadoc Write javadocs for method parameter
    */
   public FileProducer(File destinationDir, String destinationFileName, URL template) {
      _isCustom = false;
      if (destinationDir == null) {
         throw new IllegalArgumentException("destinationDir can't be null");
      }
      if (destinationFileName == null) {
         throw new IllegalArgumentException("destinationFileName can't be null");
      }
      if (template == null) {
         throw new IllegalArgumentException("template can't be null");
      }
      setDestination(destinationDir);
      setFilename(destinationFileName);
      setTemplate(template);
      // Use the name of the template and strip away the gurba in front and the extension.
      int lastSlash = template.toString().lastIndexOf("/");
      int lastDot = template.toString().lastIndexOf(".");
      String id = template.toString().substring(lastSlash + 1, lastDot);
      setId(id);
   }


   /**
    * Sets the Id attribute of the FileProducer object
    *
    * @param id The new Id value
    */
   public void setId(String id) {
      _id = id;
   }


   /**
    * Sets the Destination attribute of the DefaultFileProducer object
    *
    * @param destinationDir The new Destination value
    */
   public void setDestination(File destinationDir) {
      _destinationDir = destinationDir;
   }


   /**
    * Sets the FileName attribute of the DefaultFileProducer object
    *
    * @param destinationFileName The new FileName value
    */
   public void setFilename(String destinationFileName) {
      _destinationFileName = destinationFileName;
   }


   /**
    * Sets the Template attribute of the DefaultFileProducer object
    *
    * @param template The new Template value
    */
   public void setTemplate(File template) {
      try {
         setTemplate(template.toURL());
      } catch (MalformedURLException e) {
         e.printStackTrace();
         throw new RuntimeException(e.getMessage());
      }
   }


   /**
    * Gets the Id attribute of the FileProducer object
    *
    * @return The Id value
    */
   public String getId() {
      return _id;
   }


   /**
    * Returns a copy of this FileProducer.
    *
    * @return a FileProducer with the *real* name
    * @todo-javadoc Write javadocs for method parameter
    * @todo-javadoc Write javadocs for method parameter
    */
   /*
    *  public FileProducer copy(TableDecorator tableDecorator) {
    *  String destinationFileName = getDestinationFileName();
    *  if (destinationFileName.indexOf("{0}") != -1) {
    *  destinationFileName = MessageFormat.format(destinationFileName, new String[]{tableDecorator.getReplaceName()});
    *  }
    *  / possibly use a deeper destination dir (typically for java classes)
    *  File destinationDir = new File(getDestinationDir(), tableDecorator.getSubDirPath());
    *  FileProducer result = new FileProducer(destinationDir, destinationFileName, getTemplate());
    *  return result;
    *  }
    */
   public boolean isGenerationPerTable() {
      return _destinationFileName.indexOf("{0}") != -1;
   }


   /**
    * Describe what the method does
    *
    * @exception IllegalStateException Describe the exception
    * @todo-javadoc Write javadocs for method
    * @todo-javadoc Write javadocs for exception
    */
   public void validate() throws IllegalStateException {
      if (_template == null) {
         String msg = "Please specify the template attribute in the fileproducer.";
         _log.error(msg);
         throw new IllegalStateException(msg);
      }
      if (_isCustom) {
         // Specified in Ant
         if (getId() != null) {
            // Overriding an existing template
            if (_destinationDir != null) {
               String msg = "In fileproducer with id=\"" + getId() + "\", destination should *not* be specified. " +
                     "The fileproducer is overriding an existing template in the plugin, " +
                     "but the plugin should still decide where to store the generated file. " +
                     _destinationDir.getAbsolutePath();
               _log.error(msg);
               throw new IllegalStateException(msg);
            }
            if (_destinationFileName != null) {
               String msg = "In fileproducer with id=\"" + getId() + "\", filename should *not* be specified. " +
                     "The fileproducer is overriding an existing template in the plugin, " +
                     "but the plugin should still decide how to name the generated file. " +
                     _destinationFileName;
               _log.error(msg);
               throw new IllegalStateException(msg);
            }
         }
         else {
            // Not overriding an existing template
            if (_destinationDir == null) {
               String msg = "Please specify the destination attribute in the fileproducer.";
               _log.error(msg);
               throw new IllegalStateException(msg);
            }
            if (_destinationDir == null) {
               String msg = "Please specify the filename attribute in the fileproducer.";
               _log.error(msg);
               throw new IllegalStateException(msg);
            }
         }
      }
      else {
         // Created by a plugin class
      }
   }


   /**
    * Describe the method
    *
    * @param tableElement Describe the method parameter
    * @todo-javadoc Describe the method
    * @todo-javadoc Describe the method parameter
    */
   public void addConfiguredTable(TableElement tableElement) {
      // actually we only care about the keys.
      _tableElements.put(tableElement.getName(), tableElement);
   }


   /**
    * Describe what the method does
    *
    * @param velocityEngine Describe what the parameter does
    * @param tableDecorator Describe what the parameter does
    * @exception MiddlegenException Describe the exception
    * @todo-javadoc Write javadocs for exception
    * @todo-javadoc Write javadocs for method
    * @todo-javadoc Write javadocs for method parameter
    * @todo-javadoc Write javadocs for method parameter
    */
   public void generateForTable(VelocityEngine velocityEngine, TableDecorator tableDecorator) throws MiddlegenException {
      // possibly use a deeper destination dir (typically for java classes)
      File destinationDir = new File(_destinationDir, tableDecorator.getSubDirPath());

      String destinationFileName = MessageFormat.format(_destinationFileName, new String[]{tableDecorator.getReplaceName()});
      File outputFile = new File(destinationDir, destinationFileName);

      getContextMap().put("table", tableDecorator);
      generate(velocityEngine, outputFile);
   }


   /**
    * Describe what the method does
    *
    * @param velocityEngine Describe what the parameter does
    * @param tableDecorators Describe what the parameter does
    * @exception MiddlegenException Describe the exception
    * @todo-javadoc Write javadocs for exception
    * @todo-javadoc Write javadocs for method
    * @todo-javadoc Write javadocs for method parameter
    * @todo-javadoc Write javadocs for method parameter
    */
   public void generateForTables(VelocityEngine velocityEngine, Collection tableDecorators) throws MiddlegenException {
      File outputFile = new File(_destinationDir, _destinationFileName);
      getContextMap().put("tables", tableDecorators);
      generate(velocityEngine, outputFile);
   }


   /**
    * Sets the Template attribute of the DefaultFileProducer object
    *
    * @param template The new Template value
    */
   void setTemplate(URL template) {
      _template = template;
   }


   /**
    * Gets the ContextMap attribute of the FileProducer object
    *
    * @return The ContextMap value
    */
   Map getContextMap() {
      return _contexMap;
   }


   /**
    * Copies destination props from another instance
    *
    * @param other Describe what the parameter does
    * @todo-javadoc Write javadocs for method parameter
    */
   void copyPropsFrom(FileProducer other) {
      _destinationDir = other._destinationDir;
      _destinationFileName = other._destinationFileName;
   }


   /**
    * Describe what the method does
    *
    * @param tableDecorator Describe what the parameter does
    * @return Describe the return value
    * @todo-javadoc Write javadocs for method
    * @todo-javadoc Write javadocs for method parameter
    * @todo-javadoc Write javadocs for return value
    */
   boolean accept(TableDecorator tableDecorator) {
      if (_tableElements.size() == 0) {
         // accept all tables if none are explicitly set.
         return true;
      }
      else {
         return _tableElements.containsKey(tableDecorator.getSqlName());
      }
   }


   /**
    * @todo reuse FileProducers and introduce a generateForTable method and a
    *      generateForTables method.
    * @param velocityEngine Describe what the parameter does
    * @param outputFile Describe what the parameter does
    * @exception MiddlegenException Describe the exception
    * @todo-javadoc Write javadocs for method parameter
    * @todo-javadoc Write javadocs for method parameter
    * @todo-javadoc Write javadocs for exception
    */
   private void generate(VelocityEngine velocityEngine, File outputFile) throws MiddlegenException {
      try {
         // Make a context from the map
         VelocityContext context = new VelocityContext(getContextMap());

         // Prepare output file
         outputFile.getParentFile().mkdirs();
         BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));

         // The template
         Reader templateReader = new BufferedReader(new InputStreamReader(_template.openStream()));

         _log.info("Generating " + outputFile.getAbsolutePath() + " using template from " + _template.toString());

         // Run Velocity
         boolean success = velocityEngine.evaluate(context, writer, "middlegen", templateReader);
         writer.flush();
         writer.close();
         if (!success) {
            throw new MiddlegenException("Velocity failed");
         }
      } catch (IOException e) {
         _log.error(e.getMessage(), e);
         throw new MiddlegenException(e.getMessage());
      } catch (ParseErrorException e) {
         _log.error(e.getMessage(), e);
         throw new MiddlegenException(e.getMessage());
      } catch (MethodInvocationException e) {
         _log.error(e.getMessage(), e);
         throw new MiddlegenException(e.getMessage());
      } catch (ResourceNotFoundException e) {
         _log.error(e.getMessage(), e);
         e.printStackTrace();
         throw new MiddlegenException(e.getMessage());
      }
   }
}
