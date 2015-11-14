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

/**
 * Describe what this class does
 *
 * @author Aslak Helles�y
 * @created 3. april 2002
 * @todo-javadoc Write javadocs
 * @version $Id: DbNameConverterImpl.java,v 1.1 2009/03/27 02:17:41 dvzengch Exp $
 */
public class DbNameConverterImpl extends DbNameConverter {

   /**
    * Describe what the DbNameConverterImpl constructor does
    *
    * @todo-javadoc Write javadocs for constructor
    */
   protected DbNameConverterImpl() {
   }


   /**
    * Describe what the method does
    *
    * @todo-javadoc Write javadocs for method
    * @todo-javadoc Write javadocs for method parameter
    * @todo-javadoc Write javadocs for return value
    * @param columnName Describe what the parameter does
    * @return Describe the return value
    */
   public String columnNameToVariableName(String columnName) {
      return dbNameToVariableName(columnName);
   }


   /**
    * Describe what the method does
    *
    * @todo-javadoc Write javadocs for method
    * @todo-javadoc Write javadocs for method parameter
    * @todo-javadoc Write javadocs for return value
    * @param tableName Describe what the parameter does
    * @return Describe the return value
    */
   public String tableNameToVariableName(String tableName) {
      boolean useDbTableNames = MiddlegenTask.getUseDBTableNames();
      String varName = null;

      // If the use db table name variable is set then we want to make the
      // class name the same as the table name in the database.
      if (useDbTableNames) {
         if (tableName != null && "class".equals(tableName.toLowerCase())) {
            // "class" is illegal becauseOf Object.getClass() clash
            return "clazz";
         }
         else {
            varName = tableName;
         }
      }
      else {
         varName = dbNameToVariableName(tableName);
      }
      return varName;
   }


   /**
    * Converts a database name (table or column) to a java name (first letter
    * capitalised). employee_name -> EmployeeName
    *
    * @param s the database name to convert
    * @return the converted database name
    * @return != null
    * @pre s != null
    */
   protected String dbNameToVariableName(String s) {
      if ("".equals(s)) {
         return s;
      }
      StringBuffer result = new StringBuffer();

      boolean capitalize = true;
      boolean lastCapital = false;
      boolean lastDecapitalized = false;
      String p = null;
      for (int i = 0; i < s.length(); i++) {
         String c = s.substring(i, i + 1);
         if ("_".equals(c) || " ".equals(c)) {
            capitalize = true;
            continue;
         }

         if (c.toUpperCase().equals(c)) {
            if (lastDecapitalized && !lastCapital) {
               capitalize = true;
            }
            lastCapital = true;
         }
         else {
            lastCapital = false;
         }

         if (capitalize) {
            if (p == null || !p.equals("_")) {
               result.append(c.toUpperCase());
               capitalize = false;
               p = c;
            }
            else {
               result.append(c.toLowerCase());
               capitalize = false;
               p = c;
            }
         }
         else {
            result.append(c.toLowerCase());
            lastDecapitalized = true;
            p = c;
         }

      }
      String r = result.toString();
      return r;
   }

}
