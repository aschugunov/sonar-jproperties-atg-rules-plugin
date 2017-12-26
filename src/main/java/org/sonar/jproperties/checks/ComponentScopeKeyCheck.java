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
package org.sonar.jproperties.checks;

import com.google.common.collect.Lists;
import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.plugins.jproperties.api.tree.KeyTree;
import org.sonar.plugins.jproperties.api.tree.PropertiesTree;
import org.sonar.plugins.jproperties.api.visitors.DoubleDispatchVisitorCheck;
import org.sonar.squidbridge.annotations.SqaleConstantRemediation;

import java.util.List;

import static org.sonar.jproperties.constants.Constants.REQUIRED_KEY;

/**
 * @author Andrei Chugunov.
 */

@Rule(
        key = "required-key",
        priority = Priority.MAJOR,
        name = "Required key should be write explicit",
        tags = {"convention"})
@SqaleConstantRemediation("5min")
public class ComponentScopeKeyCheck extends DoubleDispatchVisitorCheck {

    private final List<String> keys = Lists.newArrayList();

    @Override
    public void visitProperties(PropertiesTree tree) {
        keys.clear();
        super.visitProperties(tree);
        if (!keys.contains(REQUIRED_KEY)) {
            addPreciseIssue(tree, "Have to add scope of component.");
        }
    }

    @Override
    public void visitKey(KeyTree tree) {
        keys.add(tree.text());
        super.visitKey(tree);
    }
}
