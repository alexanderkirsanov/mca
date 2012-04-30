package ru.kirsanov.mca.analitics.rules.entities;

import ru.kirsanov.mca.analitics.rules.fuzzyset.FuzzySet;

/**
 * User: akirsanov
 * Date: 26.04.12 23:28
 */
public class Statement {
    private FuzzySet term;
    private Variable variable;

    public Variable getVariable() {
        return variable;
    }

    public void setVariable(Variable variable) {
        this.variable = variable;
    }


    public FuzzySet getTerm() {
        return this.term;
    }

    public void setTerm(FuzzySet term) {
        this.term = term;
    }
}
