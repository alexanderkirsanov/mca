package ru.kirsanov.mca.analitics.rules;

import ru.kirsanov.mca.analitics.rules.entities.Conclusion;
import ru.kirsanov.mca.analitics.rules.entities.Condition;

import java.util.List;

/**
 * User: akirsanov
 * Date: 26.04.12 22:19
 */
public class Rule {
    private final List<Conclusion> conclusions;
    private final List<Condition> conditions;

    public Rule(List<Conclusion> conclusions, List<Condition> conditions) {
        this.conclusions = conclusions;
        this.conditions = conditions;
    }

    public List<Conclusion> getConclusions() {
        return this.conclusions;
    }

    public List<Condition> getConditions() {
        return this.conditions;
    }
}
