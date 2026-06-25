package org.onlydevs.hibento.file.hash;

import org.onlydevs.hibento.PojaGenerated;

@PojaGenerated
public record FileHash(FileHashAlgorithm algorithm, String value) {}
