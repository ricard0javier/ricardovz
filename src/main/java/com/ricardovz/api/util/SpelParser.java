package com.ricardovz.api.util;

import com.google.common.base.Joiner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Spring programming Expression Language Helper
 * Created by ricardo on 09/07/2015.
 */
@Service
@Slf4j
public class SpelParser {

    /**
     * The Expression parser
     */
    private ExpressionParser parser = new SpelExpressionParser();

    /**
     * The template resolver based on a context
     */
    private TemplateParserContext templateParserContext = new TemplateParserContext();

    /**
     * Parses a message template based on the provided context
     * @param messageTemplate message template containing zero or more placeholder, for instance, #{something}
     * @param context context to be used in the parsing process
     * @return message parsed
     */
    public String parse(String messageTemplate, Object context) {

        Expression exp = parser.parseExpression(messageTemplate, templateParserContext);
        EvaluationContext evaluationContext = new StandardEvaluationContext(context);

        // complete the message
        String message = exp.getValue(evaluationContext, String.class);
        log.debug(message);

        return message;
    }

    /**
     * Parses a message template based on the provided context
     * @param messageTemplatePath message templete containing zero or more placeholder, for instance, #{something}
     * @param context context to be used in the parsing process
     * @return message parsed
     */
    public String parse(Path messageTemplatePath, Object context) {

        List<String> allLines;

        try {

            allLines = Files.readAllLines(messageTemplatePath);

        } catch (IOException e) {
            log.warn("Provided path is not valid: '{}'", messageTemplatePath, e);
            return null;
        }

        String messageTemplate = Joiner.on(" ").join(allLines);

        return parse(messageTemplate, context);
    }
}
