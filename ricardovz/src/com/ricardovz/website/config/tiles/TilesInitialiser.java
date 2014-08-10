package com.ricardovz.website.config.tiles;

import org.apache.tiles.factory.AbstractTilesContainerFactory;
import org.apache.tiles.request.ApplicationContext;
import org.apache.tiles.startup.AbstractTilesInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TilesInitialiser extends AbstractTilesInitializer {
	static final Logger logger = LoggerFactory.getLogger(TilesContainerFactory.class);
	
	public TilesInitialiser() {}

	@Override
	protected AbstractTilesContainerFactory createContainerFactory(
			ApplicationContext context) {
		logger.info("Setting Tiles container");
		return new TilesContainerFactory();
	}

}
