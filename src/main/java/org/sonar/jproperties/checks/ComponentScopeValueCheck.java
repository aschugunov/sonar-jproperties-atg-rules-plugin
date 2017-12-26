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

import com.google.common.collect.ImmutableSet;
import org.apache.commons.lang.StringUtils;
import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.plugins.jproperties.api.tree.PropertyTree;
import org.sonar.plugins.jproperties.api.visitors.DoubleDispatchVisitorCheck;
import org.sonar.squidbridge.annotations.SqaleConstantRemediation;

import java.util.Set;

import static org.sonar.jproperties.constants.Constants.REQUIRED_KEY;

/**
 * @author Andrei Chugunov.
 */

@Rule(
        key = "allowed-scope-value",
        priority = Priority.BLOCKER,
        name = "ATG scope should be have predefined values",
        tags = {"bug"})
@SqaleConstantRemediation("5min")
public class ComponentScopeValueCheck extends DoubleDispatchVisitorCheck {

    private static final Set<String> ALLOWED_SCOPE_VALUES = ImmutableSet.of("global", "session", "request", "prototype", "window");

    @Override
    public void visitProperty(PropertyTree tree) {
        super.visitProperty(tree);
        if (REQUIRED_KEY.equals(getKeyText(tree)) && !ALLOWED_SCOPE_VALUES.contains(getValueText(tree))) {
            addPreciseIssue(tree, "ATG scope should be have predefined values");
        }
    }

    private String getKeyText(PropertyTree tree) {
        return tree.key() == null ? StringUtils.EMPTY : tree.key().text();
    }

    private String getValueText(PropertyTree tree) {
        return tree.value() == null ? StringUtils.EMPTY : tree.value().text();
    }
}
