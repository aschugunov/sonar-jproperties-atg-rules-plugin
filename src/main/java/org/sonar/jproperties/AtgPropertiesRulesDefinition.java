/*
 * sonar-jproperties-atg-rules-plugin
 * Copyright (C) 2009-2017 SonarSource SA
 * mailto:info AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.sonar.jproperties;

import org.sonar.jproperties.checks.ComponentScopeKeyCheck;
import org.sonar.jproperties.checks.ComponentScopeValueCheck;
import org.sonar.plugins.jproperties.api.CustomJavaPropertiesRulesDefinition;

/**
 * Extension point to define a Java Properties rule repository.
 */
public class AtgPropertiesRulesDefinition extends CustomJavaPropertiesRulesDefinition {

  /**
   * Provide the repository name.
   */
  @Override
  public String repositoryName() {
    return "ATG Properties Custom Repository";
  }

  /**
   * Provide the repository key.
   */
  @Override
  public String repositoryKey() {
    return "custom-jproperties";
  }

  /**
   * Provide the list of classes implementing rules.
   */
  @Override
  public Class[] checkClasses() {
    return new Class[] {
      ComponentScopeKeyCheck.class,
      ComponentScopeValueCheck.class
    };
  }
}
