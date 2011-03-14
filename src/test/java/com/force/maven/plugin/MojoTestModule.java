package com.force.maven.plugin;


import com.force.cliforce.ConnectionManager;
import com.force.cliforce.MainModule;

import javax.inject.Singleton;

public class MojoTestModule extends MainModule {

    @Override
    public void bindConnectionManager() {
        bind(ConnectionManager.class).to(MojoTestConnectionManager.class).in(Singleton.class);
    }

    @Override
    public String[] provideInternalPlugins() {
        return new String[0];
    }
}
