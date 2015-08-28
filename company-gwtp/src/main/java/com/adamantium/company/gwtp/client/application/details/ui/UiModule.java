/*
 * Copyright 2011 ArcBees Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.adamantium.company.gwtp.client.application.details.ui;

import com.adamantium.company.gwtp.client.application.details.ui.linkmenu.LinkMenu;
import com.adamantium.company.gwtp.client.application.details.ui.tabs.RoundTabPanel;
import com.adamantium.company.gwtp.client.application.details.ui.tabs.SimpleTabPanel;
import com.adamantium.company.gwtp.client.application.details.ui.tabs.TabFactory;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.google.inject.Singleton;

/**
 * This module makes sure every binder required by our widgets are bound as singleton.
 */
public class UiModule extends AbstractGinModule {
    @Override
    protected void configure() {
        // Singleton binders
        bind(LinkMenu.Binder.class).in(Singleton.class);
        bind(RoundTabPanel.Binder.class).in(Singleton.class);
        bind(SimpleTabPanel.Binder.class).in(Singleton.class);

        install(new GinFactoryModuleBuilder().build(TabFactory.class));
    }
}
