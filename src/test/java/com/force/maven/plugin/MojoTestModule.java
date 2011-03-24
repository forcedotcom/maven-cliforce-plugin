package com.force.maven.plugin;


import com.force.cliforce.ConnectionManager;
import com.force.cliforce.MainModule;

import javax.inject.Singleton;
import java.util.Collections;
import java.util.Set;

public class MojoTestModule extends MainModule {

    @Override
    public void bindConnectionManager() {
        bind(ConnectionManager.class).to(MojoTestConnectionManager.class).in(Singleton.class);
    }

    @Override
    public Set<String> provideInternalPlugins() {
        return Collections.emptySet();
    }
}
