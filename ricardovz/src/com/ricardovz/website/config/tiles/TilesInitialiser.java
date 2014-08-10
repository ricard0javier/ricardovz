package com.ricardovz.website.config.tiles;

import org.apache.tiles.factory.AbstractTilesContainerFactory;
import org.apache.tiles.request.ApplicationContext;
import org.apache.tiles.startup.AbstractTilesInitializer;

public class TilesInitialiser extends AbstractTilesInitializer {
	
	public TilesInitialiser() {}

	@Override
	protected AbstractTilesContainerFactory createContainerFactory(
			ApplicationContext context) {
		return new TilesContainerFactory();
	}

}
