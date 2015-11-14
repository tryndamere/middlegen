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
package middlegen;

import java.beans.Introspector;

/**
 * Various static utility methods
 *
 * @author <a href="mailto:aslak.hellesoy@bekk.no">Aslak Helles�y</a>
 * @created 3. oktober 2001
 * @version $Id: Util.java,v 1.1 2009/03/27 02:17:43 dvzengch Exp $
 * @todo move to middlegen.jdbc package
 */
public class Util {

   /** Get static reference to Log4J Logger */
   private static org.apache.log4j.Category _log = org.apache.log4j.Category.getInstance(Util.class.getName());


   /**
    * Gets the QualifiedClassName attribute of the Util class
    *
    * @todo-javadoc Write javadocs for method parameter
    * @todo-javadoc Write javadocs for method parameter
    * @param packageName Describe what the parameter does
    * @param className Describe what the parameter does
    * @return The QualifiedClassName value
    */
   public static String getQualifiedClassName(String packageName, String className) {
      String result;
      if ("".equals(packageName)) {
         result = className;
      }
      else {
         result = packageName + "." + className;
      }
      return result;
   }


   /**
    * Describe what the method does
    *
    * @todo-javadoc Write javadocs for method
    * @todo-javadoc Write javadocs for method parameter
    * @todo-javadoc Write javadocs for method parameter
    * @todo-javadoc Write javadocs for return value
    * @param a Describe what the parameter does
    * @param b Describe what the parameter does
    * @return Describe the return value
    */
   public static boolean equals(Object a, Object b) {
      if (a == null && b == null) {
         return true;
      }
      if (a != null && a.equals(b)) {
         return true;
      }
      return false;
   }


   /**
    * Ensures that the string is not null
    *
    * @param s a string
    * @return an empty string if the given string was null, else the string.
    */
   public static String ensureNotNull(String s) {
      if (s == null) {
         return "";
      }
      return s;
   }


   /**
    * Converts a database name (table or column) to a java name (first letter
    * decapitalised). employee_name -> employeeName
    *
    * @todo-javadoc Write javadocs for method parameter
    * @todo-javadoc Write javadocs for method parameter
    * @param s Describe what the parameter does
    * @return the converted database name
    * @return != null
    * @pre s != null
    */
   public static String decapitalise(String s) {
      String result = Introspector.decapitalize(s);
      if ("class".equals(result)) {
         // "class" is illegal becauseOf Object.getClass() clash
         result = "clazz";
      }
      return result;
   }


   /**
    * Describe what the method does
    *
    * @todo-javadoc Write javadocs for method
    * @todo-javadoc Write javadocs for method parameter
    * @todo-javadoc Write javadocs for return value
    * @param s Describe what the parameter does
    * @return Describe the return value
    */
   public static boolean bool(String s) {
      return "true".equals(s);
   }


   /**
    * Describe what the method does
    *
    * @todo-javadoc Write javadocs for method
    * @todo-javadoc Write javadocs for method parameter
    * @todo-javadoc Write javadocs for return value
    * @param b Describe what the parameter does
    * @return Describe the return value
    */
   public static String string(boolean b) {
      return b ? "true" : "false";
   }


   /**
    * Describe what the method does
    *
    * @todo-javadoc Write javadocs for method
    * @todo-javadoc Write javadocs for method parameter
    * @todo-javadoc Write javadocs for return value
    * @param name Describe what the parameter does
    * @return Describe the return value
    */
   public static String pluralise(String name) {
      _log.debug("pluralise:" + name);
      String result = name;
      if (name.length() == 1) {
         // just append 's'
         result += 's';
      }
      else {
         if (!seemsPluralised(name)) {
            String lower = name.toLowerCase();
            char secondLast = lower.charAt(name.length() - 2);
            if (!isVowel(secondLast) && lower.endsWith("y")) {
               // city, body etc --> cities, bodies
               result = name.substring(0, name.length() - 1) + "ies";
            }
            else if (lower.endsWith("ch") || lower.endsWith("s")) {
               // switch --> switches  or bus --> buses
               result = name + "es";
            }
            else {
               result = name + "s";
            }
         }
      }
      _log.debug("pluralised " + name + " to " + result);
      return result;
   }


   /**
    * Describe what the method does
    *
    * @todo-javadoc Write javadocs for method
    * @todo-javadoc Write javadocs for method parameter
    * @todo-javadoc Write javadocs for return value
    * @param name Describe what the parameter does
    * @return Describe the return value
    */
   public static String singularise(String name) {
      _log.debug("singularise:" + name);
      String result = name;
      if (MiddlegenTask.getSingularize() && seemsPluralised(name)) {
         String lower = name.toLowerCase();
         if (lower.endsWith("ies")) {
            // cities --> city
            result = name.substring(0, name.length() - 3) + "y";
         }
         else if (lower.endsWith("ches") || lower.endsWith("ses")) {
            // switches --> switch or buses --> bus
            result = name.substring(0, name.length() - 2);
         }
         else if (lower.endsWith("s")) {
            // customers --> customer
            result = name.substring(0, name.length() - 1);
         }
      }
      _log.debug("singularised " + name + " to " + result);
      return result;
   }


   /**
    * Describe what the method does
    *
    * @todo-javadoc Write javadocs for method
    * @todo-javadoc Write javadocs for method parameter
    * @todo-javadoc Write javadocs for return value
    * @param s Describe what the parameter does
    * @return Describe the return value
    */
   public static String capitalise(String s) {
      if (s.equals("")) {
         return "";
      }
      if (s.length() == 1) {
         return s.toUpperCase();
      }
      else {
         String caps = s.substring(0, 1).toUpperCase();
         String rest = s.substring(1);
         return caps + rest;
      }
   }


   /**
    * Gets the Vowel attribute of the Util object
    *
    * @todo-javadoc Write javadocs for method parameter
    * @param c Describe what the parameter does
    * @return The Vowel value
    */
   private final static boolean isVowel(char c) {
      boolean vowel = false;
      vowel |= c == 'a';
      vowel |= c == 'e';
      vowel |= c == 'i';
      vowel |= c == 'o';
      vowel |= c == 'u';
      vowel |= c == 'y';
      return vowel;
   }


   /**
    * Describe what the method does
    *
    * @todo-javadoc Write javadocs for return value
    * @todo-javadoc Write javadocs for method
    * @todo-javadoc Write javadocs for method parameter
    * @param name Describe what the parameter does
    * @return Describe the return value
    */
   private static boolean seemsPluralised(String name) {
      name = name.toLowerCase();
      boolean pluralised = false;
      pluralised |= name.endsWith("es");
      pluralised |= name.endsWith("s");
      pluralised &= !(name.endsWith("ss") || name.endsWith("us"));
      return pluralised;
   }

}
