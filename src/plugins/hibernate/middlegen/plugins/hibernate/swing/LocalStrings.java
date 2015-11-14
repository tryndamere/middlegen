/*
 * Copyright (c) 2001, Aslak Helles酶y, BEKK Consulting
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
package middlegen.plugins.hibernate.swing;

import java.util.ResourceBundle;

/**
 * Provide localized messages. This class should be processed xdoclet task with
 * externalizer subtask. This class is described with utf-8 encoding.
 *
 * @author Takashi Okamoto
 * @created 27 August 2004
 * @version 2.1
 * @msg.bundle
 *
 */
public class LocalStrings {

   // -----------------------------------------------------
   // -- Messages for JHibernateTableSettingsPanel.java ---
   // -----------------------------------------------------
   /**
    * @msg.bundle
    *       msg = " Key generator"
    *
    * @msg.bundle
    *       language = "ja"
    *       msg = " 涓汇偔銉笺伄鐢熸垚鏂规硶"
    *
    */
   public static final String KEY_GENERATOR = "key_generator";

   /**
    * @msg.bundle
    *       msg = " Domain class name"
    *
    * @msg.bundle
    *       language = "ja"
    *       msg = " 銉夈儭銈ゃ兂銈儵銈瑰悕"
    *
    */
   public static final String DOMAIN_CLASS_NAME = "domain_class_name";

   /**
    * @msg.bundle
    *       msg = " Schema name"
    *
    * @msg.bundle
    *       language = "ja"
    *       msg = " 銈广偔銉笺優鍚�"
    *
    */
   public static final String SCHEMA_NAME = "schema_name";

   /**
    * @msg.bundle
    *       msg = " Persister"
    *
    * @msg.bundle
    *       language = "ja"
    *       msg = "Persister銈儵銈�"
    *
    */
   public static final String PERSISTER = "persister";

   /**
    * @msg.bundle
    *       msg = " Class description"
    *
    * @msg.bundle
    *       language = "ja"
    *       msg = " 銈儵銈广伄瑾槑"
    *
    */
   public static final String CLASS_DESCRIPTION = "class_description";

   /**
    * @msg.bundle
    *       msg = " Extends"
    *
    * @msg.bundle
    *       language = "ja"
    *       msg = " 缍欐壙銇欍倠瑕偗銉┿偣"
    *
    */
   public static final String EXTENDS = "extends";

   /**
    * @msg.bundle
    *       msg = "Lifecycle interface"
    *
    * @msg.bundle
    *       language = "ja"
    *       msg = "Lifecycle銈ゃ兂銈裤儠銈с兗銈�"
    *
    */
   public static final String LIFECYCLE = "lifecycle";

   /**
    * @msg.bundle
    *       msg = "Validatable interface"
    *
    * @msg.bundle
    *       language = "ja"
    *       msg = "Validatable銈ゃ兂銈裤儠銈с兗銈�"
    *
    */
   public static final String VALIDATABLE = "validatable";

   /**
    * @msg.bundle
    *       msg = "Plain Compoundkey"
    *
    * @msg.bundle
    *       language = "ja"
    *       msg = "涓汇偔銉笺偗銉┿偣銈掔敓鎴�"
    *
    */
   public static final String COMPOUNDKEY = "compoundkey";

   /**
    * @msg.bundle
    *       msg = "Enable proxies"
    *
    * @msg.bundle
    *       language = "ja"
    *       msg = " 銉椼儹銈偡銈掍娇鐢�"
    *
    */
   public static final String PROXY = "proxy";

   /**
    * @msg.bundle
    *       msg = "Mutable"
    *
    * @msg.bundle
    *       language = "ja"
    *       msg = "鏇存柊鍙兘"
    *
    */
   public static final String MUTABLE = "mutable";

   /**
    * @msg.bundle
    *       msg = "Dynamic Update"
    *
    * @msg.bundle
    *       language = "ja"
    *       msg = "鏇存柊SQL鍕曠殑鐢熸垚"
    *
    */
   public static final String DYNAMIC_UPDATE = "dynamic_update";

   /**
    * @msg.bundle
    *       msg = "Dynamic Insert"
    *
    * @msg.bundle
    *       language = "ja"
    *       msg = "鎸垮叆SQL鍕曠殑鐢熸垚"
    *
    */
   public static final String DYNAMIC_INSERT = "dynamic_insert";

   /**
    * @msg.bundle
    *       msg = "Select Before Update"
    *
    * @msg.bundle
    *       language = "ja"
    *       msg = "update銇墠銇玸elect銈掑疅琛�"
    *
    */
   public static final String SELECT_BEFORE_UPDATE = "select_before_update";

   /**
    * @msg.bundle
    *       msg = " Class scope"
    *
    * @msg.bundle
    *       language = "ja"
    *       msg = " 銈儵銈广偣銈炽兗銉�"
    *
    */
   public static final String CLASS_SCOPE = "class_scope";

   /**
    * @msg.bundle
    *       msg = " Manage external class interfaces"
    *
    * @msg.bundle
    *       language = "ja"
    *       msg = " 澶栭儴銈ゃ兂銈裤儠銈с兗銈硅拷鍔�"
    *
    */
   public static final String EXTERNAL_INTERFACE = "external_interface";

   /**
    * @msg.bundle
    *       msg = " Batch Size "
    *
    * @msg.bundle
    *       language = "ja"
    *       msg = " 銉愩儍銉併偟銈ゃ偤 "
    *
    */
   public static final String BATCH_SIZE = "batch_size";

   /**
    * @msg.bundle
    *       msg = " Where "
    *
    * @msg.bundle
    *       language = "ja"
    *       msg = " WHERE鍙�"
    *
    */
   public static final String WHERE = "where";

   /**
    * @msg.bundle
    *       msg = "Generate Equals/Hash"
    *
    * @msg.bundle
    *       language = "ja"
    *       msg = "Equals/Hash鐢熸垚"
    *
    */
   public static final String GENERATE_EQUALS_HASH = "generate_equals_hash";

   /**
    * @msg.bundle
    *       msg = "Table Mapping Attributes"
    *
    * @msg.bundle
    *       language = "ja"
    *       msg = "琛ㄣ優銉冦償銉炽偘灞炴��"
    *
    */
   public static final String MAPPING_ATTRIBUTE = "mapping_attribute";

   /**
    * @msg.bundle
    *       msg = "Domain Class Meta Attributes"
    *
    * @msg.bundle
    *       language = "ja"
    *       msg = "銉夈儭銈ゃ兂銈儵銈广儭銈垮睘鎬�"
    *
    */
   public static final String CLASS_ATTRIBUTE = "class_attribute";

   /**
    * @msg.bundle
    *       msg = "Add"
    *
    * @msg.bundle
    *       language = "ja"
    *       msg = "杩藉姞"
    *
    */
   public static final String ADD = "add";

   /**
    * @msg.bundle
    *       msg = "Remove"
    *
    * @msg.bundle
    *       language = "ja"
    *       msg = "鍓婇櫎"
    *
    */
   public static final String REMOVE = "remove";

   // -----------------------------------------------------
   // -- Messages for JHibernateColumnSettingsPanel.java --
   // -----------------------------------------------------
   /**
    * @msg.bundle
    *       msg = "  Java property name"
    *
    * @msg.bundle
    *       language = "ja"
    *       msg = "Java銉椼儹銉戙儐銈�"
    *
    */
   public static final String JAVA_PROPERTY = "java_property";

   /**
    * @msg.bundle
    *       msg = "  Java type"
    *
    * @msg.bundle
    *       language = "ja"
    *       msg = "Java鍨�"
    *
    */
   public static final String JAVA_TYPE = "java_type";

   /**
    * @msg.bundle
    *       msg = "  Hibernate mapping specialty"
    *
    * @msg.bundle
    *       language = "ja"
    *       msg = "銉曘偅銉笺儷銉夈優銉冦償銉炽偘鐗规��"
    *
    */
   public static final String MAPPING_SPECIALTY = "mapping_specialty";

   /**
    * @msg.bundle
    *       msg = "Column updateable"
    *
    * @msg.bundle
    *       language = "ja"
    *       msg = "鍒楁洿鏂板彲"
    *
    */
   public static final String COLUMN_UPDATABLE = "column_updatable";

   /**
    * @msg.bundle
    *       msg = "Column insertable"
    *
    * @msg.bundle
    *       language = "ja"
    *       msg = "鍒楁尶鍏ュ彲"
    *
    */
   public static final String COLUMN_INSERTABLE = "column_insertable";

   /**
    * @msg.bundle
    *       msg = "  Field description"
    *
    * @msg.bundle
    *       language = "ja"
    *       msg = "銉曘偅銉笺儷銉夎鏄�"
    *
    */
   public static final String FIELD_DESCRIPTION = "field_description";

   /**
    * @msg.bundle
    *       msg = "  Field access method"
    *
    * @msg.bundle
    *       language = "ja"
    *       msg = "銉曘偅銉笺儷銉夈伕銇偄銈偦銈规柟娉�"
    *
    */
   public static final String FIELD_ACCESS_METHOD = "field_access_method";

   /**
    * @msg.bundle
    *       msg = "  Property get scope"
    *
    * @msg.bundle
    *       language = "ja"
    *       msg = "getter銈广偝銉笺儣"
    *
    */
   public static final String PROPERTY_GET_SCOPE = "property_get_scope";

   /**
    * @msg.bundle
    *       msg = "  Property set scope"
    *
    * @msg.bundle
    *       language = "ja"
    *       msg = "setter銈广偝銉笺儣"
    *
    */
   public static final String PROPERTY_SET_SCOPE = "property_set_scope";

   /**
    * @msg.bundle
    *       msg = "  Field scope"
    *
    * @msg.bundle
    *       language = "ja"
    *       msg = "銉曘偅銉笺儷銉夈偣銈炽兗銉�"
    *
    */
   public static final String FIELD_SET_SCOPE = "field_set_scope";

   /**
    * @msg.bundle
    *       msg = "Include in toString"
    *
    * @msg.bundle
    *       language = "ja"
    *       msg = "toString銇惈銈併倠"
    *
    */
   public static final String INCLUDE_TOSTRING = "include_tostring";

   /**
    * @msg.bundle
    *       msg = "Include in Equals/Hash"
    *
    * @msg.bundle
    *       language = "ja"
    *       msg = "Euqals/Hash銇惈銈併倠"
    *
    */
   public static final String INCLUDE_EQUALS_HASH = "include_equals_hash";

   /**
    * @msg.bundle
    *       msg = "Generate the property"
    *
    * @msg.bundle
    *       language = "ja"
    *       msg = "銉椼儹銉戙儐銈ｇ敓鎴�"
    *
    */
   public static final String GENERATE_PROPERTY = "generate_property";

   /**
    * @msg.bundle
    *       msg = "  Bean constraint property type"
    *
    * @msg.bundle
    *       language = "ja"
    *       msg = "Bean鍒剁磩銉椼儹銉戙儐銈ｅ瀷"
    *
    */
   public static final String CONSTRAINT_PROPERTY = "constraint_property";

   /**
    * @msg.bundle
    *       msg = "Column Mapping Attributes"
    *
    * @msg.bundle
    *       language = "ja"
    *       msg = "鍒椼優銉冦償銉炽偘灞炴��"
    *
    */
   public static final String COLUMN_MAPPING = "column_mapping";

   /**
    * @msg.bundle
    *       msg = "Domain Property Meta Attributes"
    *
    * @msg.bundle
    *       language = "ja"
    *       msg = "銉夈儭銈ゃ兂銉椼儹銉戙儐銈ｃ儭銈垮睘鎬�"
    *
    */
   public static final String PROPERTY_META_ATTR = "property_meta_attr";
   /**
    * @todo-javadoc Describe the field
    */
   private static ResourceBundle rb = ResourceBundle.getBundle(LocalStrings.class.getName() + "Messages");


   /**
    * Gets the Msg attribute of the LocalStrings class
    *
    * @todo-javadoc Write javadocs for method parameter
    * @param name Describe what the parameter does
    * @return The Msg value
    */
   public static String getMsg(String name) {
      return (String)rb.getString(name);
   }
}
