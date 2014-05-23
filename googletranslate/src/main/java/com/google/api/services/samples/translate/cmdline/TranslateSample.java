/*
 * Copyright (c) 2013 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.google.api.services.samples.translate.cmdline;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.translate.Translate;
import com.google.api.services.translate.TranslateRequestInitializer; 
import com.google.api.services.translate.model.TranslationsListResponse;
import com.google.api.services.translate.model.TranslationsResource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Main class for the Translate API command line sample.
 */
public class TranslateSample {

  /**
   * Be sure to specify the name of your application. If the application name is {@code null} or
   * blank, the application will log a warning. Suggested format is "MyCompany-ProductName/1.0".
   */
  private static final String APPLICATION_NAME = "cmu-language-bridge";

  /** API Key for the registered developer project for your application. */
  private static final String API_KEY = "AIzaSyCAiTRTkZgUQ0ScUMgrxBGTAjACJ7HAwcY";

  /** Global instance of the JSON factory. */
  private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

  /** Global instance of the HTTP transport. */
  private static HttpTransport httpTransport;

  @SuppressWarnings("unused")
  private static Translate client;

  public static void main(String[] args) {
    try {
      // initialize the transport
      httpTransport = GoogleNetHttpTransport.newTrustedTransport();

      // set up global Translate instance
      client = new Translate.Builder(httpTransport, JSON_FACTORY, null)
          .setGoogleClientRequestInitializer(new TranslateRequestInitializer(API_KEY))
          .setApplicationName(APPLICATION_NAME).build();
      
      List<String> arg0 = new ArrayList<String>();
      arg0.add("<DOCTYPE HTML><html lang=\"en-x-mtfrom-zh\"><head></head><title></title><body>英国和<font size=\"1\">美国</font>政府分别谴责了在<font size=\"2\">中国</font><font size=\"3\">新疆</font>乌鲁木齐早市发生针对无辜平民的恐怖袭击.</body></html></DOCTYPE>");
      com.google.api.services.translate.Translate.Translations.List a = client.translations().list(arg0, "en");
      TranslationsListResponse b = a.execute();
      List<TranslationsResource> c = b.getTranslations();
      
      for (TranslationsResource d : c){
    	  System.out.println(d.getTranslatedText());
      }
      System.out.println("Success! Now add code here.");
      

    } catch (IOException e) {
      System.err.println(e.getMessage());
    } catch (Throwable t) {
      t.printStackTrace();
    }
    System.exit(1);
  }
}
