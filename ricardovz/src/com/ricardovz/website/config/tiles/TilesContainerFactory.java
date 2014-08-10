package com.ricardovz.website.config.tiles;

import org.apache.tiles.TilesContainer;
import org.apache.tiles.definition.pattern.PatternDefinitionResolver;
import org.apache.tiles.definition.pattern.PrefixedPatternDefinitionResolver;
import org.apache.tiles.definition.pattern.regexp.RegexpDefinitionPatternMatcherFactory;
import org.apache.tiles.definition.pattern.wildcard.WildcardDefinitionPatternMatcherFactory;
import org.apache.tiles.evaluator.AttributeEvaluatorFactory;
import org.apache.tiles.extras.renderer.OptionsRenderer;
import org.apache.tiles.factory.BasicTilesContainerFactory;
import org.apache.tiles.request.ApplicationContext;
import org.apache.tiles.request.render.BasicRendererFactory;
import org.apache.tiles.request.render.Renderer;

public class TilesContainerFactory extends BasicTilesContainerFactory {

	@Override
	protected Renderer createTemplateAttributeRenderer(
			BasicRendererFactory rendererFactory,
			ApplicationContext applicationContext, TilesContainer container,
			AttributeEvaluatorFactory attributeEvaluatorFactory) {
		Renderer renderer =  super.createTemplateAttributeRenderer(rendererFactory,
				applicationContext, container, attributeEvaluatorFactory);
		OptionsRenderer optionsRenderer = new OptionsRenderer(applicationContext, renderer);
		return optionsRenderer;
	}
	
	@Override
	protected <T> PatternDefinitionResolver<T> createPatternDefinitionResolver(Class<T> cls) {
	    PrefixedPatternDefinitionResolver<T> r = new PrefixedPatternDefinitionResolver<T>();
	    r.registerDefinitionPatternMatcherFactory("WILDCARD", new WildcardDefinitionPatternMatcherFactory());
	    r.registerDefinitionPatternMatcherFactory("REGEXP", new RegexpDefinitionPatternMatcherFactory());
	    return r;
	}
	
}
