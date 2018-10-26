package edu.iris.dmc.station.converter;

import java.io.IOException;

import edu.iris.dmc.station.mapper.MetadataConverterException;

public interface MetadataDocumentFormatConverter<T,S> {

	public S convert(T source) throws MetadataConverterException, IOException;

}
