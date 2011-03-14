package com.force.maven.plugin;


import com.force.cliforce.MainConnectionManager;

public class MojoTestConnectionManager extends MainConnectionManager {
    @Override
    public boolean loadLoginProperties() {
        return true;
    }
}

