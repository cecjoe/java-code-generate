package com.joe.code.common.tool;

public abstract class AbstractCodeTemplate implements CodeBack {

    protected final String EMPTY_POSITION_FOUR = "    ";

    protected void addNewLine(StringBuilder stringBuilder,Integer depth, String appendContent){

        for(int i = 0; i < depth; i++){
            stringBuilder.append(EMPTY_POSITION_FOUR);
        }

        stringBuilder.append(appendContent);
        forwardNextLine(stringBuilder);
    }

    protected void forwardNextLine(StringBuilder stringBuilder){

        stringBuilder.append("\r\n");
    }
}
