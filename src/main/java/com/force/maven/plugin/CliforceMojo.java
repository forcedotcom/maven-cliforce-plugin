package com.force.maven.plugin;


import com.force.cliforce.CLIForce;
import com.force.cliforce.MainModule;
import com.google.inject.Guice;
import com.google.inject.Module;
import com.sforce.ws.ConnectionException;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

import javax.servlet.ServletException;
import java.io.*;

/**
 * Execute a force script as part of a maven build.
 *
 * @goal exec
 */
public class CliforceMojo
        extends AbstractMojo {

    /**
     * Force environment name.
     *
     * @parameter expression="${force.env}"
     */
    String forceEnv;

    /**
     * Location of the file.
     *
     * @parameter expression="${force.script}"
     * @required
     */
    File forceScript;

    /**
     * Guice Module to use to wire up CLIForce.
     * TODO should this be configurable in the plugin?
     */
    Module guiceModule = new MainModule();

    public void execute()
            throws MojoExecutionException {
        if (forceScript.exists()) {

            try {
                FileInputStream in = new FileInputStream(forceScript);
                final CLIForce cliForce = Guice.createInjector(guiceModule).getInstance(CLIForce.class);
                cliForce.init(in, new PrintWriter(System.out, true));
                if (forceEnv != null) {
                    cliForce.setCurrentEnvironment(forceEnv);
                }
                cliForce.run();
                Thread.sleep(1000);
                System.out.println();
            } catch (FileNotFoundException e) {
                getLog().error("forceScript file not found", e);
            } catch (ServletException e) {
                getLog().error("error initializing force connection", e);
            } catch (IOException e) {
                getLog().error("error initializing force connection", e);
            } catch (InterruptedException e) {
                getLog().error(e);
            } catch (ConnectionException e) {
                getLog().error("Error initializing force connection", e);
            }

        } else {
            String msg = String.format("forceScript file: %s does not exist", forceScript.getAbsolutePath());
            getLog().error(msg);
            throw new MojoExecutionException(msg);
        }
    }
}
