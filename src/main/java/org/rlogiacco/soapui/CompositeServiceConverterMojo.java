package org.rlogiacco.soapui;

/*
 * Copyright 2001-2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.io.File;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

import com.eviware.soapui.impl.wsdl.WsdlProjectPro;

/**
 * Goal which converts a SoapUIPro composite project file into a non composite project.
 * 
 * @goal convert
 * 
 * @phase process-sources
 */
public class CompositeServiceConverterMojo extends AbstractMojo {

    /**
     * @parameter expression="project.xml"
     * @required
     */
    private File outputFile;

    /**
     * @parameter 
     * @required
     */
    private File projectDirectory;

    public void execute() throws MojoExecutionException {
        try {
            WsdlProjectPro project = new WsdlProjectPro(projectDirectory.toString());
			outputFile.getParentFile().mkdirs();
			project.setComposite(false);
            project.saveIn(outputFile);
        } catch (Exception e) {
            throw new MojoExecutionException("XML creation failed", e);
        }
    }
}
